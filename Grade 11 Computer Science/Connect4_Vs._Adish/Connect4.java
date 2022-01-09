//Tony Jiang and Adish Shah
//Computer Science 20
//Henry Wise Wood High School
//2020-2021 Semester 1

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Connect4 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String answer = "";
		Connect4View view;


		view = new Connect4ViewGraphical();
	

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// this is your screen size
		JFrame userWin = new JFrame(); // creates jframe f //this is your screen size
		userWin.setUndecorated(true); // removes the surrounding border
		ImageIcon image = new ImageIcon("lose.png"); // imports the image
		JLabel lbl = new JLabel(image); // puts the image into a jlabel
		userWin.getContentPane().add(lbl); // puts label inside the jframe
		userWin.setSize(image.getIconWidth(), image.getIconHeight()); // gets h and w of image and sets jframe to the
																		// size
		int x = (screenSize.width - userWin.getSize().width) / 2; // These two lines are the dimensions
		int y = (screenSize.height - userWin.getSize().height) / 2;// of the center of the screen
		userWin.setLocation(x, y); // sets the location of the jframe //makes the jframe visible

		JFrame userLose = new JFrame(); // creates jframe f
		userLose.setUndecorated(true); // removes the surrounding border
		ImageIcon image1 = new ImageIcon("win.png"); // imports the image
		JLabel lbl1 = new JLabel(image1); // puts the image into a jlabel
		userLose.getContentPane().add(lbl1); // puts label inside the jframe
		userLose.setSize(image1.getIconWidth(), image1.getIconHeight()); // gets h and w of image and sets jframe to the
																			// size
		userLose.setLocation(x, y); // sets the location of the jframe //makes the jframe visible
		Player[] players = new Player[2];

		// Initialize the game
		// Computer - for computer

		String playerName = view.getAnswer("Enter your name.");

		players[0] = new Connect4HumanPlayer(playerName);

		JFrame neutral = new JFrame(); // creates jframe f
		neutral.setUndecorated(true); // removes the surrounding border
		ImageIcon image2 = new ImageIcon("image5.png"); // imports the image
		JLabel lbl2 = new JLabel(image2); // puts the image into a jlabel
		neutral.getContentPane().add(lbl2); // puts label inside the jframe
		neutral.setSize(image2.getIconWidth(), image1.getIconHeight()); // gets h and w of image and sets jframe to the
																		// size
		neutral.setLocation(x, y); // sets the location of the jframe //makes the jframe visible

		int askDepth = view.getIntAnswer("Please select how good Adish will be at Connect 4 from 1 to 5 (1 means Adish is bad)");
		
		neutral.setVisible(true);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		neutral.setVisible(false);


		players[1] = new ComputerConnect4Player("Adish", askDepth);


		Connect4Game state = new Connect4Game(0, players); 

		view.display(state);

		// Hold current game state
		while (!state.gameIsOver()){
			int move = state.getPlayerToMove().getMove(state, view);

			state.makeMove(move);
			view.display(state);

		}

		// The game is over
		// declare the winner!
		view.reportToUser(state.getPlayers()[1 - state.getPlayerNum()].getName() + " won!");
		if (state.getPlayers()[1 - state.getPlayerNum()].getName() == "Adish" ) {
			userLose.setVisible(true);

		} else {
			userWin.setVisible(true);

		}
		
	}
}
