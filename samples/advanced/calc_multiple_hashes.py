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

import hashlib

from api.scripting import ScriptService
from api.scripting.ScriptService import (Action, CustomColumn,
                                         CustomColumnType, CustomColumnValue,
                                         FoundItemResult, ProcessedItemResult)


def calc_hashes(file):
    hash_sha1 = hashlib.sha1()
    hash_sha256 = hashlib.sha256()
    hash_sha512 = hashlib.sha512()

    with open(file, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            hash_sha1.update(chunk)
            hash_sha256.update(chunk)
            hash_sha512.update(chunk)

    cc_sha1 = CustomColumn("SHA-1", CustomColumnType.String, CustomColumnValue(value=hash_sha1.hexdigest()))
    cc_sha256 = CustomColumn("SHA-256", CustomColumnType.String, CustomColumnValue(value=hash_sha256.hexdigest()))
    cc_sha512 = CustomColumn("SHA-512", CustomColumnType.String, CustomColumnValue(value=hash_sha512.hexdigest()))

    return [cc_sha1, cc_sha256, cc_sha512]


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        custom_columns = []

        if item.binaryFile is not None:
            custom_columns = calc_hashes(item.binaryFile)

        return ProcessedItemResult(action=Action.Include, customColumns=custom_columns)
