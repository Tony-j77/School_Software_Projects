public class HenryWiseWoodStudent extends Youth {
 
    private int homeRoom;
    private String homeRoomTeacher;
    private String schoolName = "Henry Wise Wood High School"; //override

    public HenryWiseWoodStudent(int newYear, int newMonth, int newDay, String newFirstName, String newLastName, Gender newGender, int newGrade, int newHomeRoom, String newHomeRoomTeacher) { //constructor for hww student
        super(newYear, newMonth, newDay, newFirstName, newLastName, newGender, newGrade, newHomeRoomTeacher);

        this.homeRoomTeacher = newHomeRoomTeacher;
        this.homeRoom = newHomeRoom;
        
    }


	public int getHomeRoom() {
        return homeRoom;
    }

    public String getHomeRoomTeacher(){
        return homeRoomTeacher;
    }

    public String getSchoolName(){
        return schoolName;
    }

    public void setHomeRoom(int newHomeRoom) {
        this.homeRoom = newHomeRoom;
    }

    public void setHomeRoomTeacher(String newHomeRoomTeacher) {
        this.homeRoomTeacher = newHomeRoomTeacher;
    }


}
