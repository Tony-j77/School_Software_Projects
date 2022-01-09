Python 3.8.3 (tags/v3.8.3:6f8c832, May 13 2020, 22:20:19) [MSC v.1925 32 bit (Intel)] on win32
Type "help", "copyright", "credits" or "license()" for more information.
>>> a = float(input('How many hours have you worked this week? '))
b = float(input('Hourly wage? '))
c = input('Exempt taxes? ')

#overtime
if a > 40:
 y = (a-40)*1.5 + 40
elif a <= 40:
    y = a

#tax exemption
if c == "Y":
    c = 1
elif c == "N":
    c = 0.82

#calculation
netwage = (b*y*c)
print('Your net wage for this week is $ %0.2f' %netwage )
print('Good job!')