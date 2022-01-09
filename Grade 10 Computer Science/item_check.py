Python 3.8.3 (tags/v3.8.3:6f8c832, May 13 2020, 22:20:19) [MSC v.1925 32 bit (Intel)] on win32
Type "help", "copyright", "credits" or "license()" for more information.
>>> a = float(input('Weight of object in kilograms? '))
b = float(input('Length of object in cm? '))
c = float(input('Width of object in cm? '))
d = float(input('height of the object in cm? '))

area = (b*c*d)/100 

#conditions
if (area <= 0.1 and a <= 27):
    print('accepted')

if (area > 0.1 and a <= 27):
    print('too large')
    
if (area <= 0.1 and a > 27):
    print('too heavy')
    
if (area > 0.1 and a > 27):
    print('too heavy and too large')