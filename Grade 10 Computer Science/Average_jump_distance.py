Python 3.8.3 (tags/v3.8.3:6f8c832, May 13 2020, 22:20:19) [MSC v.1925 32 bit (Intel)] on win32
Type "help", "copyright", "credits" or "license()" for more information.
>>> a = float(input('Jump #1: '))
b = float(input('Jump #2: '))
c = float(input('Jump #3: '))
d = float(input('Jump #4: '))

list1 = [a,b,c,d]

#calculations
average = ((a+b+c+d-min(list1)))/3

print('The average distance of the the three longest jumps is %0.2f' %average )