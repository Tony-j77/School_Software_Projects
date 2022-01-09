public class Introducer {

    private String firstName1;
    private String lastName1;
    private Gender gender1;
    private String occupation1;
    private String placeOfWork1;
    private int schoolGrade1;
    private String schoolName1;
    private int homeRoom1;
    private String homeRoomTeacher1;
    private int age;
    private String genderPronoun;
    
    public String createPublicIntroduction(Human person) {

        if  (person instanceof Youth && person instanceof HenryWiseWoodStudent == false) { //since a youth can also be a HWW student, this is for when a youth is only a youth 
            
            Youth y = (Youth)person;
            this.firstName1 = person.getFirstName();
            this.lastName1 = person.getLastName();
            this.gender1 = person.getGender();
            this.age = person.calculateCurrentAgeInYears();
            this.schoolGrade1 = y.getSchoolGrade();
            this.schoolName1 = y.getSchoolName();

            if(gender1 == Gender.FEMALE ) { //returns the correct pronouns
                genderPronoun = "She";
            } else {
                genderPronoun = "He";
            }

            return "I am pleased to introduce " + firstName1 + " " + lastName1 + ". " + genderPronoun + " is " + age + " years old and is a Grade " + schoolGrade1 + " student at " + schoolName1 + ".";
            
        } else if (person instanceof HenryWiseWoodStudent) { //for HWWW students
            HenryWiseWoodStudent h = (HenryWiseWoodStudent)person;
            this.firstName1 = person.getFirstName();
            this.lastName1 = person.getLastName();
            this.gender1 = person.getGender();
            this.age = person.calculateCurrentAgeInYears();
            this.homeRoom1 = h.getHomeRoom();
            this.homeRoomTeacher1 = h.getHomeRoomTeacher();
            this.schoolGrade1 = h.getSchoolGrade();
            this.schoolName1 = h.getSchoolName();

            if(gender1 == Gender.FEMALE ) {
                genderPronoun = "She";
            } else {
                genderPronoun = "He";
            }

            return "I am pleased to introduce " + firstName1 + " " + lastName1 + ". " + genderPronoun + " is " + age + " years old and is a Grade " + schoolGrade1 + " student at " + schoolName1 + ". " + genderPronoun + " belongs to " + homeRoomTeacher1 + "'s homeroom which is in Room " + homeRoom1 + ".";
        } else if (person instanceof Adult) {
            Adult a = (Adult)person;
            this.firstName1 = person.getFirstName();
            this.lastName1 = person.getLastName();
            this.gender1 = person.getGender();
            this.age = person.calculateCurrentAgeInYears();
            this.occupation1 = a.getOccupation();
            this.placeOfWork1 = a.getPlaceofWork();

            if(gender1 == Gender.FEMALE ) {
                genderPronoun = "She";
            } else {
                genderPronoun = "He";
            }

            return "I am pleased to introduce " + firstName1 + " " + lastName1 + ". " + genderPronoun + " is " + age + " years old and works at " + placeOfWork1 + " and is a " + occupation1 + ".";
            
        } else {
            this.firstName1 = person.getFirstName();
            this.lastName1 = person.getLastName();
            this.gender1 = person.getGender();
            this.age = person.calculateCurrentAgeInYears();

            if(gender1 == Gender.FEMALE ) {
                genderPronoun = "She";
            } else {
                genderPronoun = "He";
            }

            return "I am pleased to introduce " + firstName1 + " " + lastName1 + ". " + genderPronoun + " is " + age + " years old.";
        }
        
    }

}
