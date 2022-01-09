//Tony Jiang
//Computer Science 20 
//Henry Wise Wood High School
//2020-2021 semester 1

public class SumOfNumbers {
    
    public String getSum(int start, int end) { 

        int sum = 0;
        String sumString = "";

        for (int i = start; i < end; i++) {  
            
            sumString = sumString + i + " + ";  // adds a new number with a plus sign behind it  
        }

        for (int x = start; x <= end; x++) { // stores the sum of the numbers
            sum += x;
        }

        if (start <= end) {
        return sumString + end + " = " + sum; // adds last number if the numbers are valid 
        } else {
            return ""; // returns empty if the numbers are invalid 
        }
    }

    
}

