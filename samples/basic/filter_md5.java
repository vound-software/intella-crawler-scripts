/**
 * MIT License
 *
 * Copyright (c) 2022 Vound
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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