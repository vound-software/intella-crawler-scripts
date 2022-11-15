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

from api.scripting import ScriptService
from api.scripting.ScriptService import (Action, CustomColumn,
                                         CustomColumnType, CustomColumnValue,
                                         FoundItemResult, ProcessedItemResult)

# Validate IPv4 address
ipv4_number = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
ipv4_pattern = (
    ipv4_number + "\\." + ipv4_number + "\\." + ipv4_number + "\\." + ipv4_number
)
ipv4_regex = re.compile(ipv4_pattern)

# Additional validation to catch some invalid IPs
def validate_ip(ip):
    if re.match(ipv4_regex, ip):
        # if first octet is 0, it's invalid
        if ip.startswith("0.") or ip.startswith("00.") or ip.startswith("000."):
            return False  # We don't want to include this IP
        # Remove leading zeros from first octet (e.g. 010.0.0.5 -> 10.0.0.5
        if ip.startswith("0") and len(ip.split(".")[0]) > 1:
            ip = ip.lstrip("0")
        if ip.endswith("000") or ip.endswith("00") or ip.endswith("0"):
            return False  # We do not want to include IPs with trailing zeros
        else:
            return True  # Valid IP
    else:
        return False  # Invalid IP


# Known private subnets
def is_private(ip):
    if ip.startswith("10."):  # Class A
        return True
    elif ip.startswith("172."):  # Class B
        ip_number = int(ip.split(".")[1])
        return 16 <= ip_number <= 31
    elif ip.startswith("192.168."):  # Class C
        return True
    else:
        return False

class ScriptHandler(ScriptService.Iface):
    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        custom_columns = []
        tags = set()
        ip_set = {}

        # extract from text
        if item.textFile is not None:
            text = Path(item.textFile).read_text()
            for ip in re.findall(ipv4_pattern, text):
                if validate_ip(ip):
                    if is_private(ip):
                        tags.add("private")
                    else:
                        tags.add("public")

        # extract from message headers
        if item.messageHeaders is not None:
            for ip in re.findall(ipv4_pattern, item.messageHeaders):
                if validate_ip(ip):
                    if is_private(ip):
                        tags.add("private")
                    else:
                        # use hostname for set to avoid duplicates
                        tags.add("public")

        if ip_set:
            ips_column = CustomColumn(
                "Extracted IPs",
                CustomColumnType.String,
                CustomColumnValue(value=", ".join(sorted(ip_set))),
            )
            custom_columns = [ips_column]

        for ip in ip_set:
            sub_group = "Private" if is_private(ip) else "Public"
            tags.add("Extracted IPs/" + sub_group + "/" + ip)

        return ProcessedItemResult(action=Action.Include, customColumns=custom_columns, tags=tags)
