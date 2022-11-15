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
from api.scripting.ScriptService import (Action, FoundItemResult,
                                         ProcessedItemResult)


def has_file_extension(item, ext):
    return item.fileName is not None and item.fileName.lower().endswith("." + ext)


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        if item.mediaType == "application/x-ms-dos-executable" or has_file_extension(item, "exe"):
            return FoundItemResult(action=Action.Skip)

        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        return ProcessedItemResult(action=Action.Include)