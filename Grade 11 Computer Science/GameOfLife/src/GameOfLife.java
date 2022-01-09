//Tony Jiang
//Computer Science 20
//Henry Wise Wood High School
//2020-2021 Semester 1

public class GameOfLife {
    
    private boolean [][] playingBoard;
    
    public GameOfLife(boolean[][] board) {
        playingBoard = board;
    }

    public void calculateNextGeneration() {
        boolean nextGeneration[][] = new boolean[playingBoard.length][playingBoard[0].length];
		
		int width = playingBoard.length;//width of board
		
		for(int x = 0; x < width; x++) { //goes through all cells by counting up on x
            
            int height = playingBoard[x].length; //height of playing board
            
            for(int y = 0; y < height; y++) { // goes through all cell by counting up on y
                
                nextGeneration[x][y] = playingBoard[x][y];//set value of board spaces to nextGeneration
                
                int alive = 0; // set to 0 alive to start
    

                for(int xSide = Math.max(0, x-1); xSide <= Math.min(x+1, width-1); xSide++) { // goes through neighbours of the current cell using max and min which is the simplest way to do also be able to check cells on the sides.
                    
                    for(int ySide = Math.max(0, y-1); ySide <= Math.min(y+1, height-1); ySide++) {
                        
                        if(xSide != x || ySide != y) {//if not the cell
                            
                            if(playingBoard[xSide][ySide]) {//if neighbor is alive, add 1 to int alive
								alive++;
                            
                            }
                        
                        }
                    
                    }
                
                }
				
				if(alive == 3) { //game rules as define by assignment sheet (D2L)
                    
                    nextGeneration[x][y] = true;
                
                }else if(alive >= 4) { 
                    
                    nextGeneration[x][y] = false;
                
                }else if(alive <= 1) {
                    
                    nextGeneration[x][y] = false;
                
                }
				
				
			}
		}
		
		for(int x = 0; x < nextGeneration.length; x++) { //Set the value of nextGeneration as the current playingBoard
            
            for(int y = 0; y < nextGeneration[x].length; y++) {
                
                playingBoard[x][y] = nextGeneration[x][y];
            
            }
        
        }
    
    }

    public void print() {
		for(int x = 0; x < playingBoard.length; x++) { //print cells one by one

			for(int y = 0; y < playingBoard[x].length; y++) {

                if(playingBoard[x][y]) 
                
					System.out.print("*");
                else 
                
                    System.out.print(".");
                    
            }
            
            System.out.println();// go to next row
            
        }
        
    }

    public void setAlive(int x, int y, boolean isAlive) {  // sets alive

		if(playingBoard != null) {
            
            
			if(x < playingBoard.length && x >= 0) { // if x and y are in the playing area
                
                if(y < playingBoard[x].length && y >= 0) {
                    
                    playingBoard[x][y] = isAlive;//cell state
                
                }
            
            }
        
        }
    
    }

    public boolean isAlive(int x, int y) { // checks alive
       
        if(playingBoard != null) {
            
            if(x < playingBoard.length && x >= 0) { // if x and y are in the playing area
                
                if(y < playingBoard[x].length && y >= 0) {
                    
                    return playingBoard[x][y]; //return state of cell
                
                }
            
            }
        
        }
		
        return false;
        
    
    }

}
