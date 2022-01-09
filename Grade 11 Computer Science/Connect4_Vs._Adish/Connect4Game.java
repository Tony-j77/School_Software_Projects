import java.util.Arrays;

public class Connect4Game implements Connect4State{
	private char[][] board;
	private Player [] players;
	private int playerToMoveNum; // 0 or 1 for which player to go

	private int latestRow = -1; // latest row added to by makeMove method
	private int latestCol = -1; // latest column added to by makeMove method

	private int movesDone; // number of moves made

	private int evalValue; // evaluation of unblocked four-in-row for both players


	//game constructor
	public Connect4Game(int playerNum, Player [] thePlayers){
		board = new char[ROWS][COLS];

		// fills board with empty slots
		for (char[] row : board){
			Arrays.fill(row, EMPTY);
		}

		playerToMoveNum = playerNum;
		players = thePlayers;

		movesDone = 0;
		evalValue = 0;
	}

	// construct board with input states
	public Connect4Game(int playerNum, Player[] thePlayers, char[][] initialBoard, int movesMade, int unblockedTotal){
		// Initialize board with rows and columns
		board = new char[ROWS][COLS];

		// Replicate the board
		for (int row = 0; row < ROWS; row++){
			for (int column = 0; column < COLS; column++){
				board[row][column] = initialBoard[row][column];
			}
		}

		// Replicate the players and moves made etc.
		playerToMoveNum = playerNum;
		players = thePlayers;

		// Replicate the evaluation value
		movesDone = movesMade;
		evalValue = unblockedTotal;
	}


	@Override
	public char[][] getBoard() {
		return board;
	}

	@Override
	public Player[] getPlayers() {
		return players;
	}

	@Override
	public int getPlayerNum() {
		return playerToMoveNum;
	}

	// # of moves made by player so far
	public int getMovesPlayed(){
		return movesDone;
	}
	// returns evaluation value for each position
	public int grabEvalValue(){
		return evalValue;
	}

	@Override
	public Player getPlayerToMove() {
		return players[playerToMoveNum];
	}

	@Override
	public boolean isValidMove(int col) {
		// move is valid if the top column isn't full
		return !isColumnFull(col);
	}

	//Adish makes a move :)
	@Override
	public void makeMove(int col) {
		// first check if the move is valid
		if (isValidMove(col)){
			int openRow = findOpenRow(col);

			// Switch player
			playerToMoveNum = 1 - playerToMoveNum;

			// Switch evaluation for player and Adish 
			evalValue = -1 * evalValue;

			// Evaluation steps
			evalValue = evalValue - evalAdjust(openRow, col); // adjust the evaluation for the move
			board[openRow][col] = CHECKERS[getPlayerNum()]; // add the checker
			evalValue = evalValue + evalAdjust(openRow, col); // reevaluate with new piece in place

			//moves done
			movesDone++;

			// Update latest row/cols
			latestRow = openRow;
			latestCol = col;
		} else { 
			throw new IllegalStateException("Column is full!");
		}
	}

	//find first empty slot
	private int findOpenRow(int col){
		// find the first row that isn't filled
		for (int i = 0; i < ROWS; i++){
			if (board[i][col] == EMPTY){
				return i;
			}
		}

		return -1;
	}

	//finds first occupied slot
	private int findTop(int col){
		// find the top of the closed row
		int row = ROWS - 1;

		while (board[row][col] == EMPTY && row > 0){
			row--;
		}

		return row;

	}


	//calliberates the position after every move from player
	private int evalAdjust(int openRow, int column){
		// declare offsets for position evaluation
		int leftOffset, rightOffset, leftBound, rightBound;

		// grab the players
		char opponent = CHECKERS[(1 - this.playerToMoveNum)];
		char mainPlayer = CHECKERS[this.playerToMoveNum];

		leftBound = Math.max(column - 3, 0);
		rightBound = Math.min(6, column + 3);

		// evaluate horizontal possibilities
		int horizValue = evalPossibilities(mainPlayer, opponent, leftBound, rightBound,
				openRow, 0);

		// declare values for diag
		leftOffset = Math.min(Math.min(openRow, column), 3);
		rightOffset = Math.min(Math.min(5 - openRow, 6 - column), 3);
		int offsetOpenRow = openRow - leftOffset;
		int offsetRightColumn = column + rightOffset;
		int offsetLeftColumn = column - leftOffset;
		int diagonalDelta = 1;


		// evaluate diagonal 1
		int diagValueOne = evalPossibilities(mainPlayer, opponent, offsetLeftColumn, offsetRightColumn, 
				offsetOpenRow, diagonalDelta);

		// change offset values for next diagonal
		leftOffset = Math.min(Math.min(5 - openRow, column), 3);
		rightOffset = Math.min(Math.min(openRow, 6 - column), 3);

		offsetOpenRow = openRow + leftOffset;
		offsetRightColumn = column + rightOffset;
		offsetLeftColumn = column - leftOffset;
		diagonalDelta = -1;

		// evaluate diagonal 2 (opp direction)
		int diagValueTwo = evalPossibilities(mainPlayer, opponent, offsetLeftColumn, 
				offsetRightColumn, offsetOpenRow, diagonalDelta);

		// evaluate vertical Connect 4 possibilities
		int verticalValue = connect4Verticals(mainPlayer, opponent, openRow, column);

		// now return the total value of horizontal, vertical and diagonals
		int sum = verticalValue + horizValue + diagValueOne + diagValueTwo;

		return sum;
	}

	private int connect4Verticals(char mainPlayer, char opponent, int row, int column){
		// the bottom of a connect 4 is minimum row 0 or 3 checkers down
		int possibleBottom;
		possibleBottom = Math.max(0, row - 3);
		int possibleTop = possibleBottom + 4;

		// counters to calculate eval values
		int playerCount = 0;
		int opponentCount = 0;
		int verticalValue = 0;

		// Check for the Connect 4 from the bottom up
		for (int checkRow = possibleBottom; checkRow < possibleTop; checkRow++){
			if (board[checkRow][column] == opponent){
				opponentCount = opponentCount + 1;
			} else if (board[checkRow][column] == mainPlayer){
				playerCount = playerCount + 1;
			}
		}

		// if there isn't the other player's piece in the way, weight by position strength
		verticalValue = applyWeights(playerCount, opponentCount, verticalValue);

		// return this sum for the analysis of the verticals
		return verticalValue;
	}



	//applying weights
	public static int applyWeights(int playerCount, int opponentCount, int sum){
		// apply the weights based on the previous connect 4 possibilities
		if (playerCount == 0){
			sum = sum - ComputerConnect4Player.HOW_GOOD[opponentCount];
		} else if (opponentCount == 0) {
			sum = sum + ComputerConnect4Player.HOW_GOOD[playerCount];
		}

		return sum;
	}


	private int evalPossibilities(char mainPlayer, char opponent, int leftBound, 
			int rightBound, int currentRow, int offsetRow){

		// declare local variables
		int boundDiff = rightBound - leftBound;
		int opponentCount = 0;
		int playerCount = 0;
		int sum = 0;
		int checkColumn = leftBound;
		int checkRow = currentRow; 

		// -4 or 4 depending on which type of diagonal
		// 0 if checking horizontal
		int diagonalDelta = offsetRow * 4;

		if (boundDiff < 3) {
			return 0;
		}

		// ++ for row and column for diagonals
		// ++ for column for horizontals
		for (checkColumn = checkColumn; 
				checkColumn <= leftBound + 3;
				checkRow += offsetRow) {

			// check whose pieces belong to whom
			if (board[checkRow][checkColumn] == opponent){
				opponentCount = opponentCount + 1;
			} else if (board[checkRow][checkColumn] == mainPlayer){
				playerCount = playerCount + 1;
			}
			checkColumn = checkColumn + 1;

		}

		// apply the weights based on the previous connect 4 possibilities
		sum = applyWeights(playerCount, opponentCount, sum);

		// ++ for row and column for diagonals
		// ++ for column for horizontals
		for (checkColumn = checkColumn;
				checkColumn <= rightBound;
				checkRow += offsetRow){
			if (board[(checkRow - diagonalDelta)][(checkColumn - 4)] == opponent){
				opponentCount = opponentCount -1;
			} 

			if (board[(checkRow - diagonalDelta)][(checkColumn - 4)] == mainPlayer) {
				playerCount = playerCount -1;
			}

			if (board[checkRow][checkColumn] == opponent){
				opponentCount = opponentCount + 1;
			}

			if (board[checkRow][checkColumn] == mainPlayer) {
				playerCount = playerCount + 1;
			}

			// apply the weights 
			sum = applyWeights(playerCount, opponentCount, sum);

			checkColumn = checkColumn + 1;
		}


		//	    System.out.println("total value is " + sum + "\n");
		return sum;
	}

	//creating new state after every move
	public void undoMove(int column, int stateEval){
		int row = this.findTop(column);

		// change back to empty
		board[row][column] = EMPTY;

		// change other parameters to original
		playerToMoveNum = 1 - playerToMoveNum;

		evalValue = stateEval;
		movesDone--;
	}

	// column full?
	private boolean isColumnFull(int col) {
		return !(board[ROWS - 1][col] == EMPTY);
	}

	// board full?
	@Override
	public boolean isFull() {
		return (movesDone == ROWS * COLS);
	}


	// auto win avaliable?
	private boolean checkForFour(int row, int column,
			int rowOffset, int colOffset){

		int winCounter = 0; // counts to 4 for win

		// Find opp ends for the possible Connect 4
		int oppRow = 3 * rowOffset + row; 
		int oppColumn = 3 * colOffset + column;

		// conditions where Connect 4 is impossible
		// less than 7 moves (counting both players)
		// adjusted offset for row/col is < 0 or > maximum
		if ( (movesDone < 7 ) || (oppRow >= ROWS) || (oppColumn >= COLS) ||
				(oppRow < 0) || (oppColumn < 0) ||
				(row < 0) || (column < 0) || 
				(row >= ROWS) || (column >= COLS)){
			return false;
		}

		for (int i = 1; i < 5; i++){
			if (board[row][column] == CHECKERS[playerToMoveNum]){

				winCounter++;
			}

			// Adjust offsets and look for the next piece 
			// that would lead to a four-in-row.
			row += rowOffset;
			column += colOffset;
		}

		// Got a connect 4!
		return (winCounter == 4);
	}

	@Override
	public boolean gameIsOver() {
		// Check if game is complete
		if ( isFull() ){
			return true;
		}

		// Check vertical four-in-row
		if ( checkForFour(latestRow, latestCol, -1, 0)) return true;

		for (int offset = 0; offset < 4; offset++){
			// Check horizontal four-in-row
			if ( checkForFour(latestRow, latestCol - offset, 0, 1)) return true;

			// Check diagonal via lower right
			if ( checkForFour(latestRow - offset, latestCol + offset, 1, -1)) return true;

			// Check diagonal via upper right
			if ( checkForFour(latestRow - offset, latestCol - offset, 1, 1)) return true;
		}

		return false;
	}

	//test function
	public static void main(String[] args){
		Player[] players = new Player[2];
		players[0] = new Connect4HumanPlayer("Test1");
		players[1] = new Connect4HumanPlayer("Test2");

		// Initialize test game
		Connect4Game gameOne = new Connect4Game(0, players);
		Connect4Game gameTwo = new Connect4Game(0, players);
		Connect4View view = new Connect4ViewGraphical();

		while (!gameOne.gameIsOver()){
			int column = gameTwo.getPlayerToMove().getMove(gameTwo, view);
			gameTwo.makeMove(column);

			int evaluation = gameOne.grabEvalValue();
			gameOne.makeMove(column); // make the same move

			gameOne.undoMove(column, evaluation);
			gameOne.makeMove(column);

			int new_eval = gameOne.grabEvalValue();
			int compEval = ComputerConnect4Player.evaluate(gameTwo);
			
			System.out.println("The following evaluation numbers in the game should match.");
			System.out.println("Player One " + new_eval);
			System.out.println("Player Two " + compEval);
			view.display(gameOne);


		}
	}

}
