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

from api.scripting import ScriptService
from api.scripting.ScriptService import (Action, CustomColumn,
                                         CustomColumnType, CustomColumnValue,
                                         FoundItemResult, ProcessedItemResult)


# convert raw data structure to plain text
def raw_data_to_text(raw_data):
    text = ""
    if raw_data is not None:
        for entry in raw_data:
            text += entry.key + ": " + entry.value + "\n"

    return text


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        custom_columns = []
        tags = set()

        # extract message class from raw data into a custom column
        if item.rawData is not None:
            for entry in item.rawData:
                if entry.key == "PR_MESSAGE_CLASS":
                    custom_columns.append(CustomColumn("PST Message Class", CustomColumnType.String, CustomColumnValue(value=entry.value)))

        # detect keywords in raw data
        raw_data_text = raw_data_to_text(item.rawData)
        if "@lists.debian.org" in raw_data_text:
            tags.add("Detected Keywords/lists.debian.org")

        return ProcessedItemResult(action=Action.Include, customColumns=custom_columns, tags=tags)