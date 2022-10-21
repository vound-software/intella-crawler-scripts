import com.vound.intella.scripting.api.*;
import com.vound.intella.scripting.impl.ScriptServiceImpl;


public class SampleScriptServiceImpl extends ScriptServiceImpl {

	@Override
	public FoundItemResult itemFound(FoundItem item) {
		if ("b4ce732f6e31d0384558e3e46437dae6".equals(item.md5)) {
			return new FoundItemResult().setAction(Action.Skip);
		}
		return new FoundItemResult().setAction(Action.Include);
	}

	@Override
	public ProcessedItemResult itemProcessed(ProcessedItem item) {
		if ("message/rfc822".equals(item.mediaType) && "213.176.226.102".equals(item.sourceIP)) {
			return new ProcessedItemResult().setAction(Action.Skip);
		}
		return new ProcessedItemResult().setAction(Action.Include);
	}
}