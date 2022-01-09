from turtle import Screen, Turtle

def draw_box(t, x, y, size, fill_color):
    t.penup()  # no drawing! bad pen!
    t.goto(x, y)  # move the pen to a different position...let's go! move it along!
    t.pendown()  # resume drawing! good pen!

    t.fillcolor(fill_color)
    t.begin_fill()  # Shape drawn after this will be filled with this color

    for _ in range(4):
        t.forward(size)  # move forward
        t.right(90)  # turn pen right 90 degrees

    t.end_fill()  # fill squares

def draw_chess_board():
    square_color = 'yellow'  # first chess board square is yellow
    box_size = 80 #pixel size
    start_x = -width/2  # starting x position of the chess board
    start_y = height/2  # starting y position of the chess board

    for i in range(8):  # 8x8 chess board
        for j in range(8):
            draw_box(board, start_x + j * box_size, start_y - i * box_size, box_size, square_color)
            square_color = 'red' if square_color == 'yellow' else 'yellow'

        square_color = 'red' if square_color == 'yellow' else 'yellow'

screen = Screen()
width, height = screen.window_width(), screen.window_height()

board = Turtle()
board.speed('fastest')

draw_chess_board()

board.hideturtle()
screen.mainloop()