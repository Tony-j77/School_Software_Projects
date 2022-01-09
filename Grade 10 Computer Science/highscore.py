a = int(input('What is the first number? '))
b = int(input('What is the last number? '))
sum = ((b-a)+1) * (a + b) / 2

if a < b:
    result1 = f"Sum: {a}"
    for i in range(a, b):
        result1 += f"+{i + 1}"

    print(f"{result1} = {sum}")

elif a > b:
    print('INVALID')




