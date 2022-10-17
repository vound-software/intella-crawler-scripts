# Intella crawler scripts

## Introduction

Crawler script is a user defined script that will be executed during indexing for each processed item. It allows customization of data processing by invoking additional logic. Typical use cases are:
* Filtering out irrelevant items to reduce processing time, case size and number of items.
* Data enrichment (tags, custom columns, metadata modification).
* Integration with external systems.

## Script structure

Crawler script consists of a single class with two methods:
* `itemFound`
* `itemProcessed`

The first method, `itemFound`, is called when a new item is found but has not been processed yet by the indexing engine. That means most of its properties are still unknown. Only the basic information can be accessed at this stage such as file name, size, md5 hash, location, type and so on. The main purpose of this method is filtering out unwanted items before they are processed in order to reduce indexing time.

The second method, `itemProcessed`, is called when the item has been fully processed but has not been added to the Intella database yet. Full metadata and extracted text are available at this stage.

In both methods the script must return a value (action) that represents what needs to be done with the item:
* **Include** means that the item and all of its descendants should be fully processed and added to the database.
* **Skip** means that the item and all of its descendants should be skipped from processing completely. Text and binary content for this item will not be stored.
* **Stub** means that only the minimal metadata will be added to the case (file name, size, type), but its text and binary will not be stored in the case. The item itself and its descendants will still be fully processed. All descendants will be indexed in full.

If the item is skipped in `itemFound` method Intella will not process the item at all. As a result, `itemProcessed` method for such an item will not be called. That might save some indexing time.

The simplest possible crawler script is shown below (Python version, import declarations skipped). It does nothing and includes all items.

```python
class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        return ProcessedItemResult(action=Action.Include)
```

## Supported languages

Crawler script can be written in one of the three supported languages:
* Java
* Python
* Groovy

Java crawler script is a single class that extends `ScriptServiceImpl` class. Any Java 17 syntax and core features can be used with no limitations. Third party library support will be added in a future version. The Java class should be compiled and packed in a JAR file.

Groovy is very similar to Java, but it does not need to be compiled and packed. Groovy 4 syntax and core features are supported. Third party library support will be added in a future version.

Python scripts can use Python 3.10 syntax and core features. Python does not need to be installed separately in the system, it’s already bundled with Intella installation. Python scripts can use third party libraries. To add a new library do this:
* Locate the Intella program folder, e.g. c:\Program Files\Vound\Intella 2.6
* In that folder locate the “python” sub folder, e.g. c:\Program Files\Vound\Intella 2.6\python
* In the “python” folder execute the following command (as administrator) to add a third party dependency (for example to add https://pypi.org/project/requests/):

```
> .\python -m pip install requests
```

## Sample script

The following script can be used to filter out items with a specific MD5 hash and emails that came from a specific IP address. The MD5 check can be done in either method, but it’s more efficient in the itemFound method. The IP address check can only be done in the itemProcessed method because the item needs to be processed to have full metadata.

Python version:

```python
class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        if item.md5 == "b4ce732f6e31d0384558e3e46437dae6":
            return FoundItemResult(action=Action.Skip)

       return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        if item.mediaType == "message/rfc822" and item.sourceIP == "213.176.226.102":
            return ProcessedItemResult(action=Action.Skip)

       return ProcessedItemResult(action=Action.Include)
```

Groovy version:

```groovy
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
```

Java version:
```java
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
```

## Supported features

### Accessing item metadata (properties)

Only a limited number of metadata fields can be accessed in `itemFound` method. That includes fields such as file name, size, type, MD5 hash. However, a much larger list of fields is available in `itemProcessed` method. Please see the full list of supported fields in each method in the scripting.thrift file. See `FoundItem` class for the fields available in `itemFound` method and `ProcessedItem` class for the fields in `itemProcessed` method.

### Accessing item extracted text and binary content

The extracted text of an item can only be accessed in `itemProcessed` method. But the binary content is available in both methods.

To access the extracted text use `item.textFile` property. It contains a path to the extracted text file. Below you can see an example of a script that reads the item text and skips the item if it contains certain text (Python version):

```python
SKIP_TEXT = "text to exclude"

def itemProcessed(self, item):
    if item.textFile is not None:
        text = Path(item.textFile).read_text()
        if self.SKIP_TEXT in text:
            return ProcessedItemResult(action=Action.Skip)

    return ProcessedItemResult(action=Action.Include)
```

To access the item binary content use `item.binaryFile` property. Here is an example that calculates file SHA256 hash and stores it as a custom column (Python version):

```python
def sha256(file):
    hash_sha256 = hashlib.sha256()
    with open(file, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            hash_sha256.update(chunk)
    return hash_sha256.hexdigest()

def itemProcessed(self, item):
    custom_columns = []

    if item.binaryFile is not None:
        file_sha256 = sha256(item.binaryFile)
        sha256_column = CustomColumn("SHA-256", CustomColumnType.String, CustomColumnValue(value=file_sha256))
        custom_columns = [sha256_column]

    return ProcessedItemResult(action=Action.Include, customColumns=custom_columns)
```

### Modifying item properties

Item modification can only be done in `itemProcessed` method.

In order to modify item properties an additional argument, `modifiedItem`, should be provided to the result object. The following example shows a script that will modify item message ID property:

```python
def itemProcessed(self, item):
    item.messageID = "my message ID"
    return ProcessedItemResult(action=Action.Include, modifiedItem=item)
```

To tag an item use `tags` parameter:

```python
def itemProcessed(self, item):
    tags = ["my tag"]
    return ProcessedItemResult(action=Action.Include, tags=tags)
```

To add a custom column use the “customColumns” parameter:

```python
def itemProcessed(self, item):
    c1 = CustomColumn("c1", CustomColumnType.String, CustomColumnValue(value="test"))
    c2 = CustomColumn("c2", CustomColumnType.Integer, CustomColumnValue(intValue=777))
    return ProcessedItemResult(action=Action.Include, customColumns=[c1, c2])
```

Custom columns don’t need to be created beforehand. The script will create any missing custom column automatically.

## Current limitations

The current implementation of crawler scripts has certain limitations that you should be aware of:
* **Duplicates.** Crawler script will only be invoked for original items and never for duplicates. Any decision that the script makes for the original item will automatically be applied to all duplicates.If the script skips an item all of its duplicates will be skipped automatically. Any tag, custom column or other type of modifications will also be applied to duplicates.
* **Global variables.** Crawler scripts can use any features of the supported languages but it’s important to know that different crawlers will use different instances of the crawler script. That means that if the script in crawler 1 makes a change to a global variable, another instance of the script in crawler 2 will not see it. Therefore communication between different crawlers is currently limited. To overcome this limitation an external database like SQLite can be used.
* **Performance.** Please be careful when using external services from a crawler script. Crawler script is invoked for each original item. Adding unnecessary delays to the script may hurt indexing performance. Crawler script statistics will be printed in the log file when the indexing is finished. It will tell how much time was spent in a script: total and per item. In future versions asynchronous execution of the script may be added to mitigate slow scripts.
