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
