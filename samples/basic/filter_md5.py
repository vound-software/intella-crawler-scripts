from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        if item.md5 == "b4ce732f6e31d0384558e3e46437dae6":
            return FoundItemResult(action=Action.Skip)

        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        if item.mediaType == "message/rfc822" and item.sourceIP == "213.176.226.102":
            return ProcessedItemResult(action=Action.Skip)

        return ProcessedItemResult(action=Action.Include)
