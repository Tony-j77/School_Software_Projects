import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BankDataLayer {
    //creating two accounts, chequing and savings, and a balance variable initializaing at 0.0   
   private BankAccount chequing = new BankAccount("BankAccount00");
   private BankAccount savings = new BankAccount("BankAccount01");
   private double balance = 0.0;
   
   public BankDataLayer (){
         try {
           File myObj = new File("transactions");
           if (myObj.createNewFile()) {
               System.out.println("File created: " + myObj.getName());
           } 
           else{
               System.out.println("transactions.txt file already exists");
           }
           }catch (IOException e) {
               System.out.println("An error occurred.");
               e.printStackTrace();
           }
   }
   
       
       public double getBalance(String account){ //method for getting the balance for both accounts, chequing and savings 
           if(account.equals("chequing")){ 
               balance = chequing.getBalance(); //getting balance for chequing account
               
           }
           else if(account.equals("savings")){
               balance = savings.getBalance(); //getting balance for savings account
              
           }
       return balance; //returning the balance for the account that has been selected by the user
       
   }
       
       public void addTransaction(String account, double amount, String action, LocalDateTime time, boolean isFile){ //method for adding transaction to chequing and savings account
           if(account.equals("chequing")){ //chequing account transaction
               if(action.equals("withdraw")){
                  chequing.withdraw(amount); //withdrawing amount from chequing account
                      if(!isFile){
                      writeTransaction(account,  time,  amount, action);
                          }
               }
               else if(action.equals("deposit")){
                   chequing.deposit(amount); //depositing amount to chequing account
                      if(!isFile){
                      writeTransaction(account,  time,  amount, action);
                   }
                 
               }
                   
               }
       
           
           else if(account.equals("savings")){ //savings account transaction
             
               if(action.equals("withdraw")){
                   savings.withdraw(amount); //withdrawing amount from savings account
                      if(!isFile){
                      writeTransaction(account,  time,  amount, action);
                   }
                   
               }
               else if(action.equals("deposit")){
                   savings.deposit(amount); //depositing amount to savings account
                      if(!isFile){
                      writeTransaction(account,  time,  amount, action);
                   }
                   
               }
          }
           
           
       
   }
       
       public boolean isNegative(String account){ //method for having a negative balance for chequing and savings account
           if(account.equals("chequing")){
               return chequing.isOverDrawn(); //chequing account is overdrawn 
           }
           else if(account.equals("savings")){
               return savings.isOverDrawn(); //savings account is overdrawn
           }
           else{
               return false; //return false if it is anything else
           }
           
       }
       
       public ArrayList<Transaction> getTransaction(String account, LocalDateTime start,LocalDateTime end  ){ //get transactions in an array list from start date to end date for chequing and savings account
           if(account.equals("chequing")){
               return chequing.getTransactions(start, end); //returning chequing transactions from start date to end date
           }
           else if(account.equals("savings")){
               return savings.getTransactions(start, end); //returning savings transactions from start date to end date
               
           }
           else{
               return null; //return null or nothing if it is anything else
           }
       }
       
       private void writeTransaction (String bankAccount, LocalDateTime time, double amount, String action){
       try {
           
           BufferedWriter writer = new BufferedWriter(new FileWriter("transactions", true));
           writer.write(bankAccount + ";" + time + ";" + amount + ";" + action + ";" );
           writer.newLine();
           writer.close();
           
       } catch (IOException ex) {
           System.out.println("An error occurred.");
                   ex.printStackTrace();
       }
           
           
       }
       
       public void readTransaction (){
            File myObj = new File("transactions");
               
           try {
           Scanner myReader = new Scanner(myObj);
           while(myReader.hasNextLine()){
                String str = myReader.nextLine();
                parseString(str);
               
           }
           myReader.close();
       }   catch (FileNotFoundException ex) {
               System.out.println("An error has happened");
       }
       }
   
       private void parseString(String str) {
           Scanner myReader = new Scanner(str);
           myReader.useDelimiter(";");
           String bankAccount, action; 
           LocalDateTime time;
           double amount;
           bankAccount = myReader.next();
           time = LocalDateTime.parse(myReader.next());
           amount = Double.parseDouble(myReader.next());
           action = myReader.next();
           myReader.close();
           addTransaction(bankAccount,  amount,  action, time, true);
           
           
           
       }
   }