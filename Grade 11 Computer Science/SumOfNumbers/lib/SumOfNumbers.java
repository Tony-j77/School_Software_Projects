//Tony Jiang
//Computer Science 20 
//Henry Wise Wood High School
//2020-2021 semester 1

import java.util.Scanner;

public class SumOfNumbers {
    
    public static void main(String[] args) {


        Scanner keyboard = new Scanner(System.in);
        
        int start = keyboard.nextInt();
        int end = keyboard.nextInt();

        double dend = (double)end;
        double dstart = (double)start;

        double n = (dend - dstart + 1); 
            
        double sum = (n/2)*(dend+dstart);

        int intsum = (int)sum; 

        for (int i = start; i < end; i++) {  
            System.out.print(i+" + ");
        
        }
        
        keyboard.close();
        
        System.out.print(end); 
        System.out.print(" = "+intsum); 

    }
    
	
}
