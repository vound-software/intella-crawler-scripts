from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult


def has_file_extension(item, ext):
    return item.fileName is not None and item.fileName.lower().endswith("." + ext)


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        if item.mediaType == "application/x-ms-dos-executable" or has_file_extension(item, "exe"):
            return FoundItemResult(action=Action.Skip)

        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        return ProcessedItemResult(action=Action.Include)

