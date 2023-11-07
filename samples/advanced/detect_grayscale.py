import cv2

from api.scripting import ScriptService
from api.scripting.ScriptService import (Action, CustomColumn,
                                         CustomColumnType, CustomColumnValue,
                                         FoundItemResult, ProcessedItemResult)


def get_max_saturation(img_file):
    img = cv2.imread(img_file)
    hsv = cv2.cvtColor(img, cv2.COLOR_RGB2HSV)
    h, s, v = cv2.split(hsv)
    return s.max()


class ScriptHandler(ScriptService.Iface):

    def itemFound(self, item):
        return FoundItemResult(action=Action.Include)

    def itemProcessed(self, item):
        custom_columns = []
        tags = set()

        if item.binaryFile is not None and item.mediaType is not None and item.mediaType.startswith("image/"):
            max_saturation = get_max_saturation(item.binaryFile)
            sat_column = CustomColumn("Color Saturation", CustomColumnType.Integer, CustomColumnValue(intValue=max_saturation))
            custom_columns = [sat_column]

            if max_saturation <= 20:
                tags.add("Color Detection/Grayscale")
            else:
                tags.add("Color Detection/Color")

        return ProcessedItemResult(action=Action.Include, customColumns=custom_columns, tags=tags)

