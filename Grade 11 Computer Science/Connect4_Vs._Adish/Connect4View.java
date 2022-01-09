public interface Connect4View {

	// display current board
	public void display (Connect4State state);

	//ask user for move
	public int getUserMove(Connect4State state);

	//Reports the move that a player has made.

	public void reportMove (int chosenMove, String name);

	
	// Ask the user the question and return the answer as an int

	 
	public int getIntAnswer (String question);

	//convey message
	public void reportToUser(String message);

	//ask and return question to user
	public String getAnswer(String question);
}
