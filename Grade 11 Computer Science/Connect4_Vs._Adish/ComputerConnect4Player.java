public class ComputerConnect4Player extends Player {
	private int depth; 

	
	public static final int[] HOW_GOOD = {0, 2, 10^2, 10^3, 10^8}; // index is # of unblocked four-in-row potentials

	// the closer a piece is to the center, the more 4-in-row permutations available.
	// generally center piece is most valuable
	private static final int[] movesByCol = { 3, 4, 2, 5, 1, 6, 0 }; 

	// creating Adish computer
	public ComputerConnect4Player(String name, int depth){
		super(name);
		this.depth = depth;
	}

	@Override
	public int getMove(Connect4State state, Connect4View view) {
		// First copy the game instance
		Connect4Game stateCopy = new Connect4Game(state.getPlayerNum(), state.getPlayers(), state.getBoard(), evaluate(state), movesDone(state));

		// pick the move
		// start alpha-beta with neg and pos infinities
		Connect4Move chosenMoveObj = pickMove(stateCopy, depth, -Integer.MAX_VALUE, Integer.MAX_VALUE, view);
		int chosenMove = chosenMoveObj.move;

		view.reportMove(chosenMove, state.getPlayerToMove().getName());

		return chosenMove;
	} 

	//Uses game tree search with alpha-beta pruning to pick player's move low and high define the current range for the best move
	
	private static Connect4Move pickMove(Connect4Game state, int depth, int low, int high, Connect4View view){
		Connect4Move[] movesArray; // order of moves

		// grab the available moves, sorted by value
		movesArray = checkMoves(state);

		// dummy move that will be replaced with evaluation
		Connect4Move bestMove = new Connect4Move(-Integer.MAX_VALUE, -10); 


		// Use alpha-beta pruning to pick the move
		for (int i = 0; i < 7 && bestMove.value < high; i++){
			// grab the move from list
			int column = movesArray[i].move;

			if (state.isValidMove(column)){
				Connect4Move currentMove;

				// grab value of current position to restore later
				int evalValue = state.grabEvalValue();

				state.makeMove(column);

//				 testing
								view.display(state);
								System.out.println("===============");
								System.out.println("Adish thinks it's # :" + evaluate(state));
								System.out.println("===============");


				if (state.gameIsOver()){
					// Is game over because board is full?
					if (state.isFull()){
						currentMove = new Connect4Move(0, column); // assign value of 0
					} 

					// if it's Adish's turn, then this must be a win scenario
					currentMove = new Connect4Move(HOW_GOOD[4], column);

				} 
				// keep going if depth available
				else if (depth >= 1){

					// Switch player perspective
					// Reduce depth by 1
					currentMove = pickMove(state, depth - 1, -high, -low, view);

					// transfer values back while changing perspective
					currentMove.value = (currentMove.value * -1);
					currentMove.move = column;

				} else { 
					currentMove = new Connect4Move(state.grabEvalValue(), column);
				}

				
				if (currentMove.value > bestMove.value){
					bestMove = currentMove; 
					low = Math.max(bestMove.value, low); 
				}

				// undo move before trying next move
				state.undoMove(column, evalValue);
			}

		}

		return bestMove;
	}


	
	private static Connect4Move[] checkMoves(Connect4Game state){
		int stateEval; // evaluation of current state based on unblocked 4 in rows
		Connect4Move[] movesArray = new Connect4Move[Connect4Game.COLS];

		stateEval = state.grabEvalValue();

		// go through each column in move list
		for (int i = 0; i < Connect4Game.COLS; i++){
			int theMove = movesByCol[i];

			movesArray[i] = new Connect4Move(-Integer.MAX_VALUE, theMove);
			if (state.isValidMove(theMove)){
				// try the move
				state.makeMove(theMove);

				// now evaluate the new state and store value to check against later
				movesArray[i].value = state.grabEvalValue();

				// undo the state before checking again
				state.undoMove(theMove, stateEval);
			} 
		}

		// sort the move lists by values
		for (int i = 1; i < Connect4Game.COLS; i++){
			for (int compare = i; (compare >=1 && movesArray[compare].value > 
			movesArray[compare - 1].value);
					compare--){
				// placeholder to prevent clobbering
				Connect4Move placeholder = movesArray[compare];
				movesArray[compare] = movesArray[compare - 1];
				movesArray[compare - 1] = placeholder;
			}

		}

		// new set of moves with updated values
		return movesArray;
	}


	private static int movesDone(Connect4State state){
		// count the pieces

		int counter = 0;
		for (int row = 0; row < Connect4Game.ROWS; row++){
			for (int column = 0; column < Connect4Game.COLS; column++){
				if (state.getBoard()[row][column] != Connect4Game.EMPTY) counter++;
			}
		}

		return counter;
	}

	//finds unblocked 4 circles in a row that can be played
	public static int evaluate(Connect4State state){
		// grab the checker pieces and board
		char opponent = Connect4State.CHECKERS[1 - state.getPlayerNum()];
		char player = Connect4State.CHECKERS[state.getPlayerNum()];

		char[][] board = state.getBoard();

		// value that evaluates the unblocked four-in-rows
		int totalEvaluation = 0;

		// Evaluate patterns for winning
		for (int checkColumn = 0; checkColumn < 3; checkColumn ++){
			// if 0 is empty, followed by 2 of my pieces and two more empty, this is a pattern
			if (board[0][checkColumn] == Connect4State.EMPTY &&
					board[0][checkColumn + 1] == player && 
					board[0][checkColumn + 2] == player &&
					board[0][checkColumn + 3] == Connect4State.EMPTY && 
					board[0][checkColumn + 4] == Connect4State.EMPTY){
				totalEvaluation += HOW_GOOD[3];
			} else if (board[0][checkColumn] == Connect4State.EMPTY &&
					board[0][checkColumn + 1] == Connect4State.EMPTY &&
					board[0][checkColumn + 2] == player &&
					board[0][checkColumn + 3] == player &&
					board[0][checkColumn + 4] == Connect4State.EMPTY){
				totalEvaluation += HOW_GOOD[3];
			}
		}


		// Evaluate unblocked verticals
		for (int column = 0; column < Connect4Game.COLS; column++){
			for (int row = 0; row < 3; row++){
				int compCount = 0;
				int oppCount = 0;

				for (int checkRow = row; checkRow < row + 4; checkRow++){
					if (board[checkRow][column] == player){
						compCount++;
					} else if (board[checkRow][column] == opponent){
						oppCount++;
					}
				}

				totalEvaluation = Connect4Game.applyWeights(oppCount, compCount, totalEvaluation);
			}
		}

		// Evaluate unblocked horizontals
		for (int column = 0; column <= 3; column++){
			for(int row = 0; row < Connect4Game.ROWS; row++){
				// counters for computer and opponent
				int compCount = 0;
				int oppCount = 0;

				for (int checkColumn = column; checkColumn < column + 4; checkColumn++){
					// check whose checker it is and increment their counter
					if (board[row][checkColumn] == player){
						compCount++;
					} else if (board[row][checkColumn] == opponent){
						oppCount++;
					}
				}

				totalEvaluation = Connect4Game.applyWeights(oppCount, compCount, totalEvaluation);
			}
		}

		// Evaluate unblocked diagonals
		// up to right diagonal start at most from row 2, column 3
		for (int column = 0; column < 4; column++){
			for (int row = 0; row < 3; row++){
				int compCount = 0;
				int oppCount = 0;

				int checkRow = row; // need a checkrow parameter for diag
				for (int checkColumn = column; checkRow < row + 4; checkColumn++){
					if (board[checkRow][checkColumn] == player){
						compCount++;
					} else if (board[checkRow][checkColumn] == opponent){
						oppCount++;
					}

					checkRow++; // adjust for diagonal
				}


				totalEvaluation = Connect4Game.applyWeights(oppCount, compCount, totalEvaluation);
			}
		}

		// Evaluate unblocked diagonals (down to right)
		for (int column = 0; column < 4; column++){
			for (int row = 3; row <= 5; row++){
				int compCount = 0;
				int oppCount = 0;

				int checkRow = row; // need a check row parameter for diagonal patterns
				for (int checkColumn = column; checkColumn < column + 4; checkColumn++){
					if (board[checkRow][checkColumn] == player){
						compCount++;
					} else if (board[checkRow][checkColumn] == opponent){
						oppCount++;
					}

					checkRow--; // adjust for diagonal
				}

				totalEvaluation = Connect4Game.applyWeights(oppCount, compCount, totalEvaluation);
			}
		}

		return totalEvaluation;

	}
}
