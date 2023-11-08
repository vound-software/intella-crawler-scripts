import os

from api.scripting import ScriptService
from api.scripting.ScriptService import (Action, FoundItemResult,
                                         ProcessedItemResult)


KB = 1024
MB = 1024 * 1024


def get_item_size(item):
    if item.binaryFile is not None:
        return os.path.getsize(item.binaryFile)

    return -1


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        # Any unknown binary file or executable
        if item.mediaType == "application/octet-stream" or item.mediaType == "application/x-ms-dos-executable":
            if item.isTopLevelParent:
                return FoundItemResult(action=Action.Stub)

        # MS Access Database (mdb)
        if item.mediaType == "application/vnd.ms-access":
            return FoundItemResult(action=Action.Stub)

        size = get_item_size(item)

        # Video file larger than 10mb
        if item.mediaType is not None and item.mediaType.startswith("video/") and size > 10 * MB:
            return FoundItemResult(action=Action.Stub)

        # PDFs larger than 20mb
        if item.mediaType == "application/pdf" and size > 20 * MB:
            return FoundItemResult(action=Action.Stub)

        # Excel spreadsheets larger than 30mb (both XLS and XLSX)
        if item.mediaType == "application/vnd.ms-excel" or item.mediaType == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
            if size > 30 * MB:
                return FoundItemResult(action=Action.Stub)

        # Small embedded images
        if item.mediaType is not None and item.mediaType.startswith("image/") and size < 10 * KB:
            if not item.isTopLevelParent:
                return FoundItemResult(action=Action.Stub)

        # include all other items
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        return ProcessedItemResult(action=Action.Include)