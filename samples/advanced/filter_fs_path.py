from api.scripting import ScriptService
from api.scripting.ScriptService import Action, FoundItemResult, ProcessedItemResult


class ScriptHandler(ScriptService.Iface):

    FOLDERS_TO_SKIP = [
        "Windows\\Help",
        "ProgramData\\Microsoft\\Media Player",
        "Users\\Default",
        "Users\\Public"
    ]

    def init(self):
        # convert paths to lower-case
        new_set = set()
        for path in self.FOLDERS_TO_SKIP:
            new_set.add(path.lower())

        self.FOLDERS_TO_SKIP = new_set

    def itemFound(self, item):
        if item.fsPath is not None and item.fsPath.lower().strip("\\") in self.FOLDERS_TO_SKIP:
            return FoundItemResult(action=Action.Skip)

        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        return ProcessedItemResult(action=Action.Include)
