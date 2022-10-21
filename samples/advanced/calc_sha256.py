from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult, CustomColumn, CustomColumnType, CustomColumnValue

import hashlib


def sha256(file):
    hash_sha256 = hashlib.sha256()
    with open(file, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            hash_sha256.update(chunk)
    return hash_sha256.hexdigest()


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        custom_columns = []

        if item.binaryFile is not None:
            file_sha256 = sha256(item.binaryFile)
            sha256_column = CustomColumn("SHA-256", CustomColumnType.String, CustomColumnValue(value=file_sha256))
            custom_columns = [sha256_column]

        return ProcessedItemResult(action=Action.Include, customColumns=custom_columns)
