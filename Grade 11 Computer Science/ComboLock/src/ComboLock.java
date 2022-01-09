//Tony Jiang
//Computer Science 20 
//Henry Wise Wood High School
//2020-2021 semester 1

class ComboLock{
    
    private int secretOne;
    private int secretTwo;
    private int secretThree;
    private String password;
    private boolean position0 = true; // creating my own variables (not sure if this is allowed. Let me know if it's not)
    private boolean position1 = false;
    private boolean position2 = false;
    private boolean position3 = false;
    private int currentNumber = 0; //lock starts at 0 

    public ComboLock(String iPassword) {
        password = iPassword;
        secretOne = (int) ((Math.random() * ((60 - 1) + 1)) + 1);  // first generates a random number for the first number
        secretTwo = (int) ((Math.random() * ((60 - 1) + 1)) + 1); // generates a random number for the second number
        secretThree = (int) ((Math.random() * ((60 - 1) + 1)) + 1); // genertates a random number for the third number
        if (secretTwo == secretOne) { // checks if number 1 and number 2 are somehow the same (very little chance)
            secretTwo = (int) ((Math.random() * ((60 - 1) + 1)) + 1); // regenerates number 2 if it is the same as number 1 before. Now there is an even smaller chance that they are the same, but not impossible.
        } 
        if (secretTwo == secretThree || secretOne == secretThree) { //checks if number1 or number 2 are somehow the same as number 3 (very little chance)
            secretThree = (int) ((Math.random() * ((60 - 1) + 1)) + 1);   // regenerates number 3 if it is the same as number 1 or number 2 before. Now there is an even smaller chance that they are the same, but not impossible.
        }
    }

    public ComboLock(int secret1, int secret2, int secret3) {
        secretOne = secret1;
        secretTwo = secret2;
        secretThree = secret3;
        password = null;
    }

    public ComboLock(int secret1, int secret2, int secret3, String iPassword) {
        secretOne = secret1;
        secretTwo = secret2;
        secretThree = secret3;
        password = iPassword;
    }

    public void turnRight(int number) { // There are 2 turning to the right when opening lock
        if (position0) { // position 0 is any position because the first correct input has not been inputed yet
            currentNumber = number;
            if (currentNumber == secretOne) { //checks if the input number is the actually the right number
                position1 = true; 
                position0 = false;
            }
        }
        if (position2 == true) { //if the first and second position are both correct, the user can now go to the third number
            currentNumber = number; 
            if (currentNumber == secretThree) { //checks if the input number is the actually the right number
                position3 = true;
            } else {
                position3 = false;
            }
        }
    }

    public void turnLeft(int number) { // there is only 1 turning to the left when opening lock.
        if (position1 == true) { //if the first position is correct, the user can now go to the second number
            currentNumber = number;
            if (currentNumber == secretTwo) { //checks if the input number is the actually the right number
                position2 = true;
            } else {
                position2 = false;
            }
        }
    }

    public Boolean isOpen() {
        if (position1 && position2 && position3) { // if all 3 positions are set to true
            return true;
        } else {
            return false;
        }
    }

    public int[] forgotNumbers(String passwordGuess) {
        if (passwordGuess == password) {
            int[] arr = { secretOne, secretTwo, secretThree };
            return arr;
        } else {
            return null;
        }
    }

}







