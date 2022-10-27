from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult

import datetime

START_DATE_MS = datetime.datetime(2010, 1, 1).timestamp() * 1000
END_DATE_MS = datetime.datetime(2010, 4, 30).timestamp() * 1000


def is_item_in_range(item):
    return is_attr_in_range(item.sent) or is_attr_in_range(item.received)


def is_attr_in_range(date_attr):
    #  timezone is NOT taken into account
    if date_attr is None:
        return False

    return START_DATE_MS <= date_attr.epochMili <= END_DATE_MS


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        if item.mediaType == "message/rfc822":
            # filter emails by date
            if is_item_in_range(item):
                return ProcessedItemResult(action=Action.Include)
            else:
                return ProcessedItemResult(action=Action.Skip)

        # include all other items
        return ProcessedItemResult(action=Action.Include)
