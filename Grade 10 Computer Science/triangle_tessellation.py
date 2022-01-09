height          = int(input("height of triangle? "))
cols            = int(input("number of columns? "))
rows            = int(input("number or rows?"))

MAX_PAGE_WIDTH: int = 19 * 8


def get_even_number(num):
    return num % 2 == 0

triangle        = ""

for column_row in range(rows):
    half_space      = height - 1
    stars           = 1

    for row_of_triangle in range(height):
        if get_even_number(column_row):
            for column in range(cols):
                triangle += " " * (half_space + 1) + "*" * stars + " " * half_space
        else:
            for column in range(cols + 1):
                if(not(column == 0)):
                    triangle += " " * (half_space + 1)

                if(column == 0 or column >= cols):
                    sub = row_of_triangle
                    if(column >= cols):
                        sub += 1
                    triangle += "*" * (stars - sub)
                else:
                    triangle += "*" * stars

                if(column < cols):
                    triangle += " " * half_space

        half_space      -= 1
        stars           += 2
        triangle        += "\n"



print(triangle)
print('wow so many triangles!')