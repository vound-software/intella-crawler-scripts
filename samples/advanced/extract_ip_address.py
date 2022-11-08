#
# MIT License
#
# Copyright (c) 2022 Vound
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

from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult, CustomColumn, CustomColumnType, CustomColumnValue

from pathlib import Path

import re

# Validate IPv4 address
ipv4_number = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
ipv4_pattern = ipv4_number + "\\." + ipv4_number + "\\." + ipv4_number + "\\." + ipv4_number


# Additional validation to catch some invalid IPs
def validate_ip(ip):
    return not ip.startswith("0") and not ip.endswith(".0")


# Known private subnets, the list is not complete!
def is_private(ip):
    return ip.startswith("10.") or ip.startswith("127.") or ip.startswith("169.254.") or ip.startswith("192.168.")


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        custom_columns = []
        tags = set()
        ip_set = set()

        # extract from text
        if item.textFile is not None:
            text = Path(item.textFile).read_text()
            for ip in re.findall(ipv4_pattern, text):
                if validate_ip(ip):
                    ip_set.add(ip)

        # extract from message headers
        if item.messageHeaders is not None:
            for ip in re.findall(ipv4_pattern, item.messageHeaders):
                if validate_ip(ip):
                    ip_set.add(ip)

        if ip_set:
            ips_column = CustomColumn("Extracted IPs", CustomColumnType.String, CustomColumnValue(value=", " .join(sorted(ip_set))))
            custom_columns = [ips_column]

        for ip in ip_set:
            sub_group = "Private" if is_private(ip) else "Public"
            tags.add("Extracted IPs/" + sub_group + "/" + ip)

        return ProcessedItemResult(action=Action.Include, customColumns=custom_columns, tags=tags)
