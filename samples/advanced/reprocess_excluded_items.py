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
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult

import re

# item://source_id/uri
uri_regex = re.compile(r"item:\/\/([0-9a-z-]+)\/(.+)")

def load_from_csv(csv_file):
    uris = set()

    with open(csv_file, "r") as file:
        for full_uri in file:
            # remove the 'item://source/' prefix
            # example: item://123/file/path -> file/path
            m = re.match(uri_regex, full_uri)
            if m is not None:
                uris.add(m[2])

    return uris


class ScriptHandler(ScriptService.Iface):

    def init(self):
        # URI list with items that were previously skipped but now need to be included (reprocessed)
        self.items_to_include = load_from_csv("c:\\tmp\\items.csv")

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        # check if the item is in the "include" list
        if item.uri in self.items_to_include:
            return ProcessedItemResult(action=Action.Include, tags=["Included"])

        # other filters
        if item.mediaType == "image/png":
            return ProcessedItemResult(action=Action.Stub)

        return ProcessedItemResult(action=Action.Include)