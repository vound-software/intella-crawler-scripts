import com.vound.intella.scripting.api.*
import com.vound.intella.scripting.impl.ScriptServiceImpl

class SampleScriptServiceImpl extends ScriptServiceImpl {

    FoundItemResult itemFound(FoundItem item) {
        if (item.md5 == "b4ce732f6e31d0384558e3e46437dae6") {
            return new FoundItemResult().setAction(Action.Skip);
        }
        return new FoundItemResult().setAction(Action.Include);
    }

    ProcessedItemResult itemProcessed(ProcessedItem item) {
        if (item.mediaType == "message/rfc822" && item.sourceIP == "213.176.226.102") {
            return new ProcessedItemResult().setAction(Action.Skip);
        }
        return new ProcessedItemResult().setAction(Action.Include);
    }
}
