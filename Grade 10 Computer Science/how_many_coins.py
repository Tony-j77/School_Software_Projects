Python 3.8.3 (tags/v3.8.3:6f8c832, May 13 2020, 22:20:19) [MSC v.1925 32 bit (Intel)] on win32
Type "help", "copyright", "credits" or "license()" for more information.
>>> a = float(input('Hello traveller! Enter the amount of bronze pieces you have: '))

#calculations
print(a//10000, "Gold pieces")
a = a%10000
print(a//100, "Silver pieces")
a = a%100
print(a//1, "Bronze pieces")
a = a%1