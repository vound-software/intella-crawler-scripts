# Intella crawler scripts

### Table of Contents  
* [Introduction](#introduction)
* [Script structure](#script-structure)
* [Supported languages](#supported-languages)
* [Supported features](#supported-features)
  * [Accessing item metadata (properties)](#accessing-item-metadata-properties)
  * [Accessing item extracted text and binary content](#accessing-item-extracted-text-and-binary-content)
  * [Modifying item properties](#modifying-item-properties)
  * [Accessing family-level information](#accessing-family-level-information)
* [Sample scripts](#sample-scripts)
  * [Basic](#basic)
  * [Advanced](#advanced)
* [API reference](#api-reference)
* [Debugging](#debugging)
* [Script log](#script-log)
* [Error handling](#error-handling)
* [Current limitations](#current-limitations)

## Introduction

Crawler script is a user defined script that will be executed during indexing for each processed item. It allows customization of data processing by invoking additional logic. Typical use cases are:
* Filtering out irrelevant items to reduce processing time, case size and number of items.
* Data enrichment (tags, custom columns, metadata modification).
* Integration with external systems.

## Script structure

Crawler script consists of a single class with four methods:
* `init`
* `itemFound`
* `itemProcessed`
* `shutdown`

The first method, `init`, is called when a script instance is initialized. This can be used to establish a database connection, open local files and so on. It's only called once per script instance (crawler).

The second method, `itemFound`, is called when a new item is found but has not been processed yet by the indexing engine. That means most of its properties are still unknown. Only the basic information can be accessed at this stage such as file name, size, md5 hash, location, type and so on. The main purpose of this method is filtering out unwanted items before they are processed in order to reduce indexing time.

The third method, `itemProcessed`, is called when the item has been fully processed but has not been added to the Intella database yet. Full metadata and extracted text are available at this stage.

In both methods, `itemFound` and `itemProcessed`, and  the script must return a value (action) that represents what needs to be done with the item:
* **Include** means that the item and all of its descendants should be fully processed and added to the database.
* **Skip** means that the item and all of its descendants should be skipped from processing completely. Text and binary content for this item will not be stored.
* **Stub** means that only the minimal metadata will be added to the case (file name, size, type), but its text and binary will not be stored in the case. The item itself and its descendants will still be fully processed. All descendants will be indexed in full.

If the item is skipped in `itemFound` method Intella will not process the item at all. As a result, `itemProcessed` method for such an item will not be called. That might save some indexing time.

The last method, `shutdown`, is called when a script instance is shut down. This can be used to clean up resources, close opened files. It's only called once per script instance (crawler).

The `init` and `shutdown` methods are optional and can be omitted.

The simplest possible crawler script is shown below (Python version, import declarations skipped). It does nothing and includes all items.

```python
class ScriptHandler(ScriptService.Iface):

    def init(self):
        pass

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        return ProcessedItemResult(action=Action.Include)
    
    def shutdown(self):
        pass

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

## Supported features

### Accessing item metadata (properties)

Only a limited number of metadata fields can be accessed in `itemFound` method. That includes fields such as file name, size, type, MD5 hash. However, a much larger list of fields is available in `itemProcessed` method. Please see the full list of supported fields in each method in the [scripting.thrift](api/scripting.thrift) file. See `FoundItem` class for the fields available in `itemFound` method and `ProcessedItem` class for the fields in `itemProcessed` method.

### Accessing item extracted text and binary content

The extracted text of an item can only be accessed in `itemProcessed` method. But the binary content is available in both methods.

To access the extracted text use `item.textFile` property. It contains a path to the extracted text file (encoding: UTF-8). Below you can see an example of a script that reads the item text and skips the item if it contains certain text (Python version):

```python
SKIP_TEXT = "text to exclude"

def itemProcessed(self, item):
    if item.textFile is not None:
        text = Path(item.textFile).read_text(encoding='utf-8')
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

It is possible to tag, modify properties and add custom columns at the same time. Just add the required parameters to the result object:
```python
def itemProcessed(self, item):
    <...>
    return ProcessedItemResult(action=Action.Include, modifiedItem=item, tags=tags, customColumns=[c1, c2])
```


### Accessing family-level information

_(Available in Intella 2.6.1 or later)_

To access the family-level information use `item.isTopLevelParent` property. It is `True` for top-level parents. It is `False` for all other items: children or items that don't belong to any family. Examples of top-level parents:
* File in a folder
* Email in a PST container

Examples of items that are not top-level parents:
* Email attachment (including embedded emails)
* Image embedded in a document
* PST container
* Folder in a disk image

`isTopLevelParent` property can be used to take families into account. For example, date filtering can be applied to top-level items only.


## Sample scripts

### Basic

The following script can be used to filter out items with a specific MD5 hash and emails that came from a specific IP address. The MD5 check can be done in either method, but it’s more efficient in the itemFound method. The IP address check can only be done in the itemProcessed method because the item needs to be processed to have full metadata.

Python version (see full version: [filter_md5.py](samples/basic/filter_md5.py)):

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

Groovy version (see full version: [filter_md5.groovy](samples/basic/filter_md5.groovy)):

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

Java version (see full version: [filter_md5.java](samples/basic/filter_md5.java)):
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

### Advanced

This is a collection of more advanced crawler scripts:
* Filtering:
  * [filter_type.py](samples/advanced/filter_type.py) - Filter items by type or file extension (Windows executables)
  * [filter_domain.py](samples/advanced/filter_domain.py) - Index emails sent to or received from a specific domain only.
  * [filter_date.py](samples/advanced/filter_date.py) - Index emails sent or received withing a specific date range only.
  * [filter_fs_path.py](samples/advanced/filter_fs_path.py) - Filter files in a disk image by path.
  * [filter_date_toplevel.py](samples/advanced/filter_date_toplevel.py) - Index top-level emails sent or received withing a specific date range only.
  * [filter_by_type_and_size.py](samples/advanced/filter_by_type_and_size.py) - Exclude items from processing based on their type and size.
  * [reprocess_excluded_items.py](samples/advanced/reprocess_excluded_items.py) - Reprocess previously excluded item via URI list.
* Data enrichment: 
  * [calc_sha256.py](samples/advanced/calc_sha256.py) - Calculate SHA-256 hash for all items and store it as a custom column.
  * [calc_multiple_hashes.py](samples/advanced/calc_multiple_hashes.py) - Calculate multiple hashes at once (SHA-1, SHA-256, SHA-512) for all items and store them as custom columns.
  * [extract_ip_address.py](samples/advanced/extract_ip_address.py) - Extract all IP addresses from item text and message headers using regular expression. Validate the result and store it as a custom column. Also create a tag for each found IP address.
  * [extract_mac.py](samples/advanced/extract_mac.py) - Extract all MAC addresses from item text and raw data using regular expression. Validate the result and store it as a custom column. Also create a tag for each found IP address. Retrieve MAC vendor names from an online source.
  * [access_raw_data.py](samples/advanced/access_raw_data.py) - Extract information from raw data.
  * [detect_grayscale.py](samples/advanced/detect_grayscale.py) - Detect grayscale images using OpenCV.
  * [unfurl_crawler_script.py](samples/advanced/unfurl_crawler_script.py) - Extract metadata from Google search URLs using [unfurl](https://github.com/obsidianforensics/unfurl) library.
  
Here is an example of data produced by [extract_mac.py](samples/advanced/extract_mac.py) script.

![Advanced Script Demo](images/adavanced_script.png?raw=true "Advanced Script Demo (extract_mac.py)")

## API reference

The file [scripting.thrift](api/scripting.thrift) contains the full API reference including the complete list of metadata fields.

Here is the list of language specific APIs generated from the thrift file:
* Python: [python](api/python)
* Java and Groovy: [java](api/java)

## Debugging

It is possible to debug a script using print-like statements. Intella will show the script output including errors in the case log, for example:
```python
def itemProcessed(self, item):
    print("-- Script -- Processing item with type: " + str(item.mediaType))
    return ProcessedItemResult(action=Action.Include)
```

## Script log

When the "Script Log" feature is turned on, Intella will generate a CSV file that contains all decisions made by a crawler script along with some item details. This can be used for either debugging or auditing purposes. A new file is created in the case log folder for each indexing run: `script-log-<DATE_TIME>.csv`

## Error handling

If an error occurs during the script execution, the error will be recorded in the exception report and the item will be marked as an "Exception" item (feature facet). Such exception items will be fully processed. If the script log is enabled, the item will be marked as an "Error" in the CSV file (Result: Error). The actual error message (including the stack trace) will be shown in the case log. Error handling will be improved the future versions.

## Current limitations

The current implementation of crawler scripts has certain limitations that you should be aware of:
* **Duplicates.** Crawler script will only be invoked for original items and never for exact (MD5) duplicates. Any decision that the script makes for the original item will automatically be applied to all duplicates. If the script skips an item all of its duplicates will be skipped automatically. Any tag, custom column or other type of modifications will also be applied to duplicates. Message hash is not taken into account when determining duplicates during indexing.
* **Top-level items.** Starting with Intella 2.6.1, a duplicate will be processed once if its family role is different from the original item. That allows the script to make different decisions for top-level parents and children. For example, the script can filter out top-level parents only (see [filter_date_toplevel.py](samples/advanced/filter_date_toplevel.py)).
* **Global variables.** Crawler scripts can use any features of the supported languages, but it’s important to know that different crawlers will use different instances of the crawler script. That means that if the script in crawler 1 makes a change to a global variable, another instance of the script in crawler 2 will not see it. Therefore, communication between different crawlers is currently limited. To overcome this limitation an external database like SQLite can be used. Note that each crawler can use up to 4 parallel processing threads. All these threads share the same script instance.
* **Performance.** Please be careful when using external services from a crawler script. Crawler script is invoked for each original item. Adding unnecessary delays to the script may hurt indexing performance. Crawler script statistics will be printed in the log file when the indexing is finished. It will tell how much time was spent in a script: total and per item. In future versions asynchronous execution of the script may be added to mitigate scripts whose execution incur some level of latency, e.g. due to accessing cloud services.
* Files encountered by a crawler may be pushed to another crawler. E.g. when a crawler processing a disk image encounters a PST. There is no guarantee that items are processed by the same script instance as their parents.
* If execution of a script takes more than 5 minutes for a single item it will be aborted.
