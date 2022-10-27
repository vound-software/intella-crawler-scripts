import json
from urllib import request
from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult, CustomColumn, CustomColumnType, CustomColumnValue

from pathlib import Path

import re

# mac pattern following EUI-48 standard
mac_pattern = re.compile(r"(([0-9a-fA-F]{2}[:-]){5}([0-9a-fA-F]{2}))")


def extract_macs(text, mac_set):
    found = False

    for mac in re.findall(mac_pattern, text):
        mac = mac[0]  # get the first element of a tuple

        if validate_mac(mac):
            mac_set.add(mac)
            found = True

        if len(mac_set) >= 10:
            print("Found 10 mac addresses, exiting")
            return True

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


# Do api calls to get the mac address vendor at macvendorlookup.com with the first 3 octets of the MAC address
def get_mac_vendor(mac):
    url = "https://macvendorlookup.com/api/v2/" + mac[0:8]
    response = request.urlopen(url)
    data = response.read()
    # if the response is not 200, return the response code
    if response.getcode() != 200:
        return "Unknown"
    else:
        text = data.decode('utf-8')
        json_data = json.loads(text)
        if json_data[0]["company"] == "":
            return "Unknown"
        else:
            return json_data[0]['company']


class ScriptHandler(ScriptService.Iface):

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
                # print(" --- " + str(mac))
                mac_address = mac.upper()
                mac_vendor = get_mac_vendor(mac)  # http call
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
