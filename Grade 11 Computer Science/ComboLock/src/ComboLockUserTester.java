//Tony Jiang
//Computer Science 20 
//Henry Wise Wood High School
//2020-2021 semester 1

import java.util.Scanner;

public class ComboLockUserTester {
    
    public static void main(String[] args) {

        String password = "Tony";

        int secret1;
        int secret2;
        int secret3;

        secret1 = (int) ((Math.random() * ((60 - 1) + 1)) + 1);  // first generates a random number for the first number
        secret2 = (int) ((Math.random() * ((60 - 1) + 1)) + 1); // generates a random number for the second number
        secret3 = (int) ((Math.random() * ((60 - 1) + 1)) + 1); // genertates a random number for the third number
        if (secret2 == secret1) { // checks if number 1 and number 2 are somehow the same (very little chance)
            secret2 = (int) ((Math.random() * ((60 - 1) + 1)) + 1); // regenerates number 2 if it is the same as number 1 before. Now there is an even smaller chance that they are the same, but not impossible.
        } 
        if (secret2 == secret3 || secret1 == secret3) { //checks if number1 or number 2 are somehow the same as number 3 (very little chance)
            secret3 = (int) ((Math.random() * ((60 - 1) + 1)) + 1);   // regenerates number 3 if it is the same as number 1 or number 2 before. Now there is an even smaller chance that they are the same, but not impossible.
        }

        ComboLock lock = new ComboLock(secret1, secret2, secret3, password);

        Scanner in = new Scanner(System.in);
        boolean opened = false;

    
        while (!opened) {
            System.out.println("Choose one option below:");
            System.out.println("(a) Turn the lock to the right");
            System.out.println("(b) Turn the lock to the left");
            System.out.println("(c) Open the lock");
            System.out.println("(d) Retrieve the combination");
            String choice = in.nextLine();

            if ("a".equals(choice)) {
                System.out.println("Please enter a number 1-60:");
                int ticks = in.nextInt();

                if ((ticks < 0) || (ticks > 60)) {
                    System.out.println("Come on man! It's not that hard... I'm out!"); //invalid range
                    return;
                } else {
                    lock.turnRight(ticks); //call turnRight function from ComboLock.java
                }
            }

            if ("b".equals(choice)) {
                System.out.println("Please enter a number 1-60:");
                int ticks = in.nextInt();

                if ((ticks < 0) || (ticks > 60)) {
                    System.out.println("Come on man! It's not that hard... I'm out!"); //invalid range
                    return;
                } else {
                    lock.turnLeft(ticks);//call turnLeft function from ComboLock.java
                }

            }

            if ("c".equals(choice)) { // the user has to manually open the lock 
                if (lock.isOpen()) {
                    System.out.println("The lock is open!"); //if the lock is open, the program exits
                    return;
                } else {
                    System.out.println("The Lock is still locked!"); // if the lock is locked, the program continues
                }
            }

            if ("d".equals(choice)) {
                System.out.println("Enter Password:");
                String userPassword = in.nextLine();

                if ("Tony".equals(userPassword)) { //checks if password is correct
                    System.out.println("The combination is: " +secret1+", "+secret2+", "+secret3); //reveal password
                } else {
                    System.out.println("Wrong password!");
                }
            }
        }
        return; // if the lock is opened, the program stops
    }
}
