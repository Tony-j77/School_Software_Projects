public class Adult extends Human {

    public Adult(int newYear , int newMonth, int newDay, String  newFirstName, String newLastName, Gender newGender, String newWorkplace, String newOccupation) {
        super(newYear, newMonth, newDay, newFirstName, newLastName, newGender);
        // TODO Auto-generated constructor stub

        this.workplace = newWorkplace;
        this.occupation = newOccupation;
    }

	private String workplace;
    private String occupation;

	public String getPlaceofWork() {
        return workplace;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setWorkplace(String newWorkplace) {
        this.workplace = newWorkplace;
    }

    public void setOccupation(String newOccupation) {
        this.occupation = newOccupation;
    }

}
