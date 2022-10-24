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
