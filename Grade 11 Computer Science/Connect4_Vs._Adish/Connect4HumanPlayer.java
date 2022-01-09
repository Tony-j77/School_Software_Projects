public class Connect4HumanPlayer extends Player {

	// constructor to create human player
	public Connect4HumanPlayer(String name ) { 
		super(name);
	}
	
	@Override
	public int getMove(Connect4State state, Connect4View view) {
		return view.getUserMove(state);
	} 
	
}