from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult
from pathlib import Path


class ScriptHandler(ScriptService.Iface):

    SKIP_TEXT = "text to exclude"

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        if item.textFile is not None:
            text = Path(item.textFile).read_text(encoding='utf-8')
            if self.SKIP_TEXT in text:
                return ProcessedItemResult(action=Action.Skip)

        return ProcessedItemResult(action=Action.Include)
