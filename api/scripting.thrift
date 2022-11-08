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

namespace java com.vound.intella.scripting.api

const string GENERATOR_VERSION = "1.0.0.5"

struct ScriptTZonedDateTime {
	1: i64 epochMili,
	2: string zoneId
}

struct RawDataEntry {
	1: string key,
	2: string value
}

struct PersonAccount {
	1: string name,
	2: string contact // email, chat ID or phone number
}

struct GeoLocation {
	1: double latitude,
	2: double longitude
}

struct FoundItem {

	// restricted set of attributes

	1: optional i64 id,
	2: optional string fileName,
	3: optional string uri,
	4: optional string md5, // read-only
	5: optional i64 size,
   6: optional string mediaType,
   7: optional string fsPath, // file system path, e.g. \Windows\System32 (disk image entries only)

   // External resources

   51: optional string binaryFile,

}

struct ProcessedItem {

	// Primary attributes

	1: optional i64 id,
	2: optional string fileName,
	3: optional string uri,
	4: optional string md5, // read-only
	5: optional i64 size,
   6: optional string mediaType,
	7: optional bool encrypted,
	8: optional bool decrypted,
	9: optional string password,
	10: optional string certificate,
	11: optional list<RawDataEntry> rawData,
	12: optional string contactName,
	13: optional string nativeID,
	14: optional string documentID, // load files
	15: optional string parentDocumentID, // load files
	16: optional GeoLocation geoLocation,
	17: optional string endAttach, // load files
	18: optional string textSnippet,
   19: optional bool recovered, // read-only
   20: optional bool orphan, // read-only
   21: optional bool embeddedImage, // read-only
   22: optional string fsPath,  // file system path, e.g. \Windows\System32 (disk image entries only)

   // External resources

   51: optional string binaryFile,
   52: optional string textFile,

   // Dates

   101: optional ScriptTZonedDateTime contentCreated,
   102: optional ScriptTZonedDateTime contentLastModified,
   103: optional ScriptTZonedDateTime contentLastAccessed,
   104: optional ScriptTZonedDateTime fileCreated,
   105: optional ScriptTZonedDateTime fileLastModified,
   106: optional ScriptTZonedDateTime fileLastAccessed,
   107: optional ScriptTZonedDateTime lastPrinted,
   108: optional ScriptTZonedDateTime sent,
   109: optional ScriptTZonedDateTime received,
   110: optional ScriptTZonedDateTime visited,
   111: optional ScriptTZonedDateTime called,
   112: optional ScriptTZonedDateTime started,
   113: optional ScriptTZonedDateTime ended,
   114: optional ScriptTZonedDateTime due,
   115: optional ScriptTZonedDateTime deleted,

	// Files and documents

	201: optional string title,
	202: optional string subject,
	203: optional i32 pageCount,
	204: optional list<PersonAccount> creators,
	205: optional list<PersonAccount> contributors,
	206: optional string organization,
	207: optional bool emptyDocument,

	// Emails and chats

	251: optional string messageID,
	252: optional string messageHeaders,
	253: optional set<string> conversationIndexSet,
	254: optional set<PersonAccount> messageFrom,
	255: optional set<PersonAccount> messageSenders,
	256: optional set<PersonAccount> messageTo,
	257: optional set<PersonAccount> messageCc,
	258: optional set<PersonAccount> messageBcc,
	259: optional set<PersonAccount> chatSenders,
	260: optional set<PersonAccount> chatReceivers,
	261: optional set<PersonAccount> chatAccounts,
	262: optional bool isRead,
	263: optional i32 durationSeconds,
	264: optional set<PersonAccount> incomingPhoneNumbers,
	265: optional set<PersonAccount> outgoingPhoneNumbers,
	266: optional set<PersonAccount> allPhoneNumbers,
	267: optional string sourceIP,
	268: optional i32 messageCount,
	269: optional set<string> chatProtocol,
	270: optional string chatConversationId,
	271: optional string intellaChatConversationId,
	272: optional string chatConversationTitle,

}

struct FoundItemResult {

	// What to do with the item: include, skip or stub. Default: Include.
	1: optional Action action,

}

struct ProcessedItemResult {

	// What to do with the item: include, skip or stub
	1: optional Action action,

	// Mdified version of the item if it was modified by the script. Null if the item was not modified.
	2: optional ProcessedItem modifiedItem,

	// Tags assigned by the script
	3: optional set<string> tags,

	// Custom columns assigned by the script
	4: optional list<CustomColumn> customColumns,

}

struct CustomColumn {

	1: optional string name,
	2: optional CustomColumnType type,
	3: optional CustomColumnValue value,

}

union CustomColumnValue {

	1: string value,
	2: i32 intValue,
	3: i64 longValue,
	4: double doubleValue,
	5: bool boolValue,
	6: ScriptTZonedDateTime dateTimeValue

}

enum CustomColumnType {

	String,
	Integer,
	Long,
	Double,
	Boolean,
	DateTime // ScriptTZonedDateTime

}

enum Action {

	/**
	 * Include and process the item and all of its descendants.
	 */
	Include,

	/**
	 * Skip processing this item and all of its descendants/children.
	 */
	Skip,

	/**
	 * Stub the item. Descendants/children will be processed.
	 */
	Stub

}

service ScriptService {
	string getVersion(),
	void init(),
	FoundItemResult itemFound(1:FoundItem item),
	ProcessedItemResult itemProcessed(1:ProcessedItem item),
	void shutdown()
}

service ControlService {
	void shutdown(),
	string ping(1:string hello)
}



