public class HenryWiseWoodStudent extends Youth {
 
    private int homeRoom;
    private String homeRoomTeacher;
    private String schoolName = "Henry Wise Wood High School";

    public HenryWiseWoodStudent(int newYear, int newMonth, int newDay, String newFirstName, String newLastName, Gender newGender, int newGrade, int newHomeRoom, String newHomeRoomTeacher) {
        super(newYear, newMonth, newDay, newFirstName, newLastName, newGender, newGrade, newHomeRoomTeacher);
        // TODO Auto-generated constructor stub

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
