Python 3.8.3 (tags/v3.8.3:6f8c832, May 13 2020, 22:20:19) [MSC v.1925 32 bit (Intel)] on win32
Type "help", "copyright", "credits" or "license()" for more information.
>>> answer1 = input('Would you like a small or large sandwich? ')
if answer1 == "L":
    answer1 = 4.00
elif answer1 == "S":
    answer1 = 2.50
answer2 = input('Would you like cheese? ')
if answer2 == "Y":
    answer2 = 0.50
elif answer2 == "N":
    answer2 = 0.00
answer3 = input('Would you like tomatoes? ')
if answer3 == "Y":
    answer3 = 0.25
elif answer3 == "N":
    answer3 = 0.00
answer4 = input('Would you like lettuce? ')
if answer4 == "Y":
    answer4 = 0.10
elif answer4 == "N":
    answer4 = 0.00
answer5 = input('Would you like onions? ')
if answer5 == "Y":
    answer5 = 0.10
elif answer5 == "N":
    answer5 = 0.00
answer6 = input('Ketchup? ')
answer6 = input('Mayonnaise? ')
answer6 = input('mustard? ')

price = (answer1+answer2+answer3+answer4+answer5)
print('Your total is $ %0.2f' %price)