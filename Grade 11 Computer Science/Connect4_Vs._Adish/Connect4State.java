public interface Connect4State {
  public final static int ROWS = 6;            // Board height
  public final static int COLS = 7;            // Board width
  public final static char EMPTY = '.';        // Indicate empty place
  public final static char CHECKER0 = 'X';     // Indicate the first player's checker
  public final static char CHECKER1 = 'O';     // Indicate second player's checker
  public final static char [] CHECKERS = {CHECKER0, CHECKER1};
  
  // 2D array
  public char [][] getBoard();
  
  //2 player objects
  public Player [] getPlayers();
  
  //get number of the player that has the current turn
  public int getPlayerNum ();
  
  // gets the player who has a turn
  public Player getPlayerToMove();

 // move valid?
  public boolean isValidMove(int col);
  
  // make a move
  public void makeMove(int col);
  
 
  // is board full?
  public boolean isFull();

  // is the game over?
  public boolean gameIsOver();
}
