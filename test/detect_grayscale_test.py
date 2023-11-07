import sys, cv2

img_file = sys.argv[1]
img = cv2.imread(img_file)
hsv = cv2.cvtColor(img, cv2.COLOR_RGB2HSV)
h, s, v = cv2.split(hsv)
print('Max saturation: ' + str(s.max()))