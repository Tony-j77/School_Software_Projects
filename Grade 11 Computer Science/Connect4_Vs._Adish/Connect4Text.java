import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect4Text implements Connect4View{
	private Scanner input; 

	public Connect4Text(){
		input = new Scanner(System.in);
	}

	/**  
	 * Displays the current board
	 * @param state current state of the game
	 */
	public void display(Connect4State state){
		char [][] board = state.getBoard();

		for (int row = state.ROWS - 1; row > -1; row--){
			System.out.print(row + " |");
			for (int column = 0; column < state.COLS; column++){
				System.out.print(board[row][column]);
				System.out.print(" ");
			}
			System.out.println("\n");
		}

		System.out.print("  ");
		for (int column = 0; column < state.COLS; column++){
			System.out.print(" "+column);
		}

		System.out.println("\n");

	}

	// ask user for a move
	public int getUserMove(Connect4State state){
		Scanner column;
		int choose;

		System.out.println();
		System.out.println("Please pick a column");

		column = new Scanner(System.in);
		
		// validity checks
		for(;;) {
		    if(!column.hasNextInt() ) { 
		        System.out.println("Integers from 0 to 6 allowed."); 
				System.out.println("Please pick a column");
		        column.next(); // discard
		        continue;
		    } 
		    choose = column.nextInt();
		    if( (choose < 0) || (choose > state.COLS - 1) ) {
				System.out.println("Illegal column. Please try again");
				System.out.println("Please pick a column");
		        continue;
		    }
		    break;
		}

		return choose;
	}

	// confirm a move has been made
	public void reportMove (int chosenMove, String name){
		System.out.println("\n" + name + " chooses the column " + chosenMove);
	}

	// ask user q and returns it as a int
	public int getIntAnswer (String question){
		int answer = 0;
		boolean valid = false;

		// Ask question
		System.out.println(question + " ");

		while (!valid){
			try { 
				answer = input.nextInt();
				valid = true; 
			} catch (NumberFormatException ex) {
				reportToUser("Error: "+ ex + " Please enter an integer");
				valid = false;
			}
		}

		return answer;

	}

	//convey msg
	public void reportToUser(String message){
		System.out.println(message);
	}

	//ask question and return answer
	public String getAnswer(String question){
		System.out.println(question + " ");

		return input.nextLine();

	}

}