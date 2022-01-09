public class Youth extends Human {

    private int schoolGrade;
    private String schoolName;
    
    public Youth(int newYear, int newMonth, int newDay, String newFirstName, String newLastName, Gender newGender, int newGrade, String newSchoolName) {
		super(newYear, newMonth, newDay, newFirstName, newLastName, newGender);
        //TODO Auto-generated constructor stub
        
        this.schoolGrade = newGrade;
        this.schoolName = newSchoolName;
    }


	public int getSchoolGrade() {
        return schoolGrade;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolGrade(int newSchoolGrade) {
        this.schoolGrade = newSchoolGrade;
    }

    public void setSchoolName(String newSchoolName) {
        this. schoolName = newSchoolName;
    }
}
