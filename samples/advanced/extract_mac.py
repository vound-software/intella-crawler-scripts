#
# MIT License
#
# Copyright Vound, LLC (http://www.vound-software.com/).
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
#

import re
from pathlib import Path
from urllib import request

from api.scripting import ScriptService
from api.scripting.ScriptService import (Action, CustomColumn,
                                         CustomColumnType, CustomColumnValue,
                                         FoundItemResult, ProcessedItemResult)

# mac pattern following EUI-48 standard
mac_pattern = re.compile(r"(([0-9a-fA-F]{2}[:-]{1}){5}([0-9a-fA-F]{2}))")
known_vendors = {}


def init_vendor_database():
    with request.urlopen("https://gitlab.com/wireshark/wireshark/-/raw/master/manuf") as response:
        data = response.read()

        # parse data
        for line in data.decode('utf-8').splitlines():
            # skip if line is empty or a comment
            if line.startswith("#") or not line.strip():
                continue

            line = line.split("\t")
            # Do not include mac vendor IEEE Registration Authority
            if line[-1] == "IEEE Registration Authority":
                # add XX:YY:ZZ only
                continue
            # # remove suffixes from mac addresses (e.g. 38:3A:21:90:00:00/28)
            if re.search(r"\/\d{2}$", line[0]):
                line[0] = line[0][:-3]
                # remove 00 from the end of the mac address
                if line[0][-2:] == "00":
                    line[0] = line[0][:-3]
                    if line[0][-2:] == "00":
                        line[0] = line[0][:-3]


            vendor_mac = line[0].upper()
            vendor_name = line[-1]
            known_vendors[vendor_mac] = vendor_name


def extract_macs(text, mac_set):
    found = False

    for mac in re.findall(mac_pattern, text):
        mac = mac[0]  # get the first element of a tuple

        if validate_mac(mac):
            mac_set.add(mac)
            found = True

    return found


# Additional validation to catch some invalid mac addresses
def validate_mac(mac):
    return mac != "00:00:00:00:00:00" and mac != "00-00-00-00-00-00" \
           and mac.lower() != "ff:ff:ff:ff:ff:ff" and mac.lower() != "ff-ff-ff-ff-ff-ff"


# according to the Ranges of group and locally administered addresses
# see https://en.wikipedia.org/wiki/MAC_address
def get_type(mac):
    if mac[1].lower() in "26ae37bf":
        return "Locally Administered"
    else:
        return "Universally Administered"


# according to the Ranges of group and locally administered addresses
# see https://en.wikipedia.org/wiki/MAC_address
def get_multicast(mac):
    if mac[1].lower() in "159d37bf":
        return "Multicast"
    else:
        return "Unicast"


def get_mac_vendor(mac):
    if len(mac) < 8:
        return "Unknown too small"
    mask = mac.upper().replace("-", ":")
    # if the mac address matches the beginning of a known vendor
    for vendor_mac in known_vendors:
        if mask.startswith(vendor_mac):
            return known_vendors[vendor_mac]
    return known_vendors.get(mask, "Unknown") # return "Unknown" if not found

class ScriptHandler(ScriptService.Iface):

    def init(self):
        init_vendor_database()
        print("Initialized known vendor database: " + str(len(known_vendors)) + " vendors")

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        custom_columns = []
        tags = set()
        mac_set = set()
        found_in_text = False
        found_in_rawdata = False

        # extract from text
        if item.textFile is not None:
            text = Path(item.textFile).read_text()
            if extract_macs(text, mac_set):
                found_in_text = True

        # extract from raw data
        if item.rawData is not None:
            for entry in item.rawData:
                if extract_macs(entry.value, mac_set):
                    found_in_rawdata = True

        if mac_set:
            mac_addresses = set()
            mac_vendors = set()

            for mac in mac_set:
                mac_address = mac.upper()
                mac_vendor = get_mac_vendor(mac)
                mac_type = get_type(mac)
                mac_multicast = get_multicast(mac)

                tags.add("MAC/Addresses/" + mac_address)
                tags.add("MAC/Vendors/" + mac_vendor)
                tags.add("MAC/Types/" + mac_type)
                tags.add("MAC/Multicast/" + mac_multicast)

                mac_addresses.add(mac_address)
                mac_vendors.add(mac_vendor)

            # define custom columns
            mac_column = CustomColumn("MAC Addresses", CustomColumnType.String, CustomColumnValue(value=", " .join(sorted(mac_addresses))))
            mac_column_vendor = CustomColumn("MAC Vendors", CustomColumnType.String, CustomColumnValue(value=", " .join(sorted(mac_vendors))))

            # add custom columns
            custom_columns.append(mac_column)
            custom_columns.append(mac_column_vendor)

        if found_in_text:
            tags.add("MAC/Found in Text")

        if found_in_rawdata:
            tags.add("MAC/Found in Raw Data")

        return ProcessedItemResult(action=Action.Include, customColumns=custom_columns, tags=tags)
