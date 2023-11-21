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

__author__ = 'Marco de Moulin - Vound Software'
__date__ = '20231121'
__version__ = 0.01
__description__ = """This script will tag all email items that have no subject or a single space subject."""

class ScriptHandler(ScriptService.Iface):
    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)
    
    def itemProcessed(self, item):
        if item.mediaType == "message/rfc822":
            if item.subject is None:
                return ProcessedItemResult(action=Action.Include, tags=["crawlerscript/No Subject"])
            elif item.subject == " ":
                return ProcessedItemResult(action=Action.Include, tags=["crawlerscript/Single Space Subject"])
            else:
                return ProcessedItemResult(action=Action.Include)
        else:
            return ProcessedItemResult(action=Action.Include)