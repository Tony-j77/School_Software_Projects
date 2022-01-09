//Tony Jiang
//Computer Science 30
//Henry Wise Wood High School
//2020-2021 Semester 2

import java.time.LocalDate;


public class Human {
    
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private String firstName;
    private String LastName;
    private Gender gender;
    LocalDate currentdate = LocalDate.now();
    public int currentDay = currentdate.getDayOfMonth();
    public int currentMonth = currentdate.getMonthValue();
    public int currentYear = currentdate.getYear();
    

    public Human(int newYear , int newMonth, int newDay, String  newFirstName, String newLastName, Gender newGender) {

        this.birthYear = newYear;
        this.birthMonth = newMonth;
        this.birthDay = newDay;
        this.firstName = newFirstName;
        this.LastName = newLastName;
        this.gender = newGender;
    
    }

	public int getBirthYear() {
        return birthYear;
    }
    
	public int getBirthMonth() {
		return birthMonth;
    }
    
	public int getBirthDay() {
		return birthDay;
    }
    
	public String getFirstName() {
		return firstName;
    }
    
	public String getLastName() {
		return LastName;
    }
    
    public Gender getGender() {
        return gender;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        this.LastName = newLastName;
    }

    public void setGender(Gender newGender) {
        this.gender = newGender;
    }

    public int calculateCurrentAgeInYears() {
        int age = currentYear - birthYear;


        if (currentMonth > birthMonth) {
                return age;
        } else if (currentMonth == birthMonth) {
            if (currentDay < birthDay) {
                return age-1;
            } else {
                return age;
            }
        } else {
            return age-1;
        }
    }

}
