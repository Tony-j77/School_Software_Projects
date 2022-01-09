package people;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import junit.framework.TestCase;

public class HumanSortTest extends TestCase {
	
	Human burns;
	Adult homer;
	Youth lisa;
	HenryWiseWoodStudent nemo;
	
	private Human[] createHumans() {
		int graduateBirthYear = LocalDateTime.now().getYear() - 18;
		String schoolName = "Henry Wise Wood High School";
		Human[] humans = {				
				new Adult(1934, 11, 7, "Folco", "Gaukrogers", Gender.MALE, schoolName, "Principal"),
				
				//HenryWiseWoodStudent(int birthYear, int birthMonth, int birthDay, String firstName, String lastName, Gender gender, int schoolGrade, int homeRoom, String homeRoomTeacher)
				new HenryWiseWoodStudent(graduateBirthYear, 6, 4, "Banazir", "Rumble", Gender.MALE, 12, 310, "Wehnes"),
				
				new Youth(graduateBirthYear, 11, 17, "Munderic", "Wanderfoot", Gender.MALE, 12, schoolName),
				new Adult(1946, 4, 23, "Ceufroy", "Longbottom", Gender.MALE, schoolName, "Teacher"),
				new Youth(1931, 8, 20, "Meginhard", "Chubb", Gender.MALE, 12, schoolName),
				new Youth(graduateBirthYear, 12, 16, "Tolman", "Burns", Gender.MALE, 12, schoolName),
				new Adult(1901, 3, 14, "Fulvus", "Hayward", Gender.MALE, schoolName, "Teacher"),
				new Adult(1946, 1, 3, "Charles ", "Silverstring", Gender.MALE, schoolName, "Teacher"),
				new Adult(1943, 4, 16, "Berenger", "Featherbottom", Gender.MALE, schoolName, "Teacher"),
				new Adult(1926, 7, 14, "Waltgaud", "Headstrong", Gender.MALE, schoolName, "Librarian"),
				new HenryWiseWoodStudent(graduateBirthYear, 6, 3, "Neela", "Burrowes", Gender.FEMALE, 12, 304, "Brunelle"),
				new Youth(graduateBirthYear, 12, 16, "Alura", "Rumblebelly", Gender.FEMALE, 0, schoolName),
				new Adult(1948, 12, 24, "Liutgarde", "Sackville", Gender.FEMALE, schoolName, "Teacher"),
				new Youth(graduateBirthYear - 18, 3, 17, "Belinda", "Brockhouse", Gender.FEMALE, 12, schoolName),
				new Adult(1921, 9, 16, "Esmeralda", "Longfoot", Gender.FEMALE, schoolName, "Teacher"),
				new Youth(graduateBirthYear, 7, 21, "Lauren", "Langham", Gender.FEMALE, 12, schoolName),
				new Youth(graduateBirthYear, 5, 31, "Esmeralda", "Fairbairn", Gender.FEMALE, 12, schoolName),
				new Adult(1936, 1, 20, "Nantechildis", "Tunnelly", Gender.FEMALE, schoolName, "Councilor"),
				new HenryWiseWoodStudent(graduateBirthYear, 12, 19, "Marcovefa", "Bunce", Gender.FEMALE, 12, 301, "Marshall"),
				new Adult(1968, 5, 27, "Hamesindis", "Fallohide", Gender.FEMALE, schoolName, "Facility Operator")
		};
		return humans;
	}
	
	public void testSortByAge() {

		Comparator<Human> c = Human.AGE_ORDER;
		Human[] humans = createHumans();
		
		Arrays.sort(humans, c);
		assertEquals(true, verifySortByAge(humans));		
	}

	public void testSortByName() {

		Comparator<Human> c = Human.NAME_ORDER;
		Human[] humans = createHumans();
		
        Arrays.sort(humans, c);
		assertEquals(true, verifySortByName(humans));
				
	}
	
	public void testSortByAsemblyOrder() {

		Comparator<Human> c = Human.ASSEMBLY_ORDER;
		Human[] humans = createHumans();

		Arrays.sort(humans, c);

		System.out.println("\nVerifySortByAssemblyOrder");
		System.out.println("----------------");

		for (int i = 0; i < humans.length; i++) {

			System.out.println(toLastNameFirstName(humans[i]));
			
		}

		assertEquals("Bunce", humans[0].getLastName());
		assertEquals("Burrowes", humans[1].getLastName());
		assertEquals("Rumble", humans[2].getLastName());
		assertEquals("Brockhouse", humans[3].getLastName());
		assertEquals("Burns", humans[4].getLastName());
		assertEquals("Chubb", humans[5].getLastName());
		assertEquals("Fairbairn", humans[6].getLastName());
		assertEquals("Langham", humans[7].getLastName());
		assertEquals("Rumblebelly", humans[8].getLastName());
		assertEquals("Wanderfoot", humans[9].getLastName());
		assertEquals("Fallohide", humans[10].getLastName());
		assertEquals("Featherbottom", humans[11].getLastName());
		assertEquals("Gaukrogers", humans[12].getLastName());
		assertEquals("Hayward", humans[13].getLastName());
		assertEquals("Headstrong", humans[14].getLastName());
		assertEquals("Longbottom", humans[15].getLastName());
		assertEquals("Longfoot", humans[16].getLastName());
		assertEquals("Sackville", humans[17].getLastName());
		assertEquals("Silverstring", humans[18].getLastName());
		assertEquals("Tunnelly", humans[19].getLastName());
		
	}

	public void testComparable() {
		
		Human[] humans = createHumans();
		ArrayList<Human> humansList = new ArrayList<Human>(Arrays.asList(humans));
		
		humansList.sort(null);
		Human[] sortedHumans = new Human[humansList.size()];
		humansList.toArray(sortedHumans);
		assertEquals(true, verifySortByAge(sortedHumans));		
	}
	
	private boolean verifySortByAge(Human[] humans) {

		System.out.println("\nVerifySortByAge");
		System.out.println("---------------");
		
		for (int i = 0; i < humans.length - 1; i++) {
			
			System.out.println(toLastNameFirstName(humans[i]));
			
			if (humans[i].getBirthYear() > humans[i+1].getBirthYear()) {
				return false;
			}
			else if (humans[i].getBirthYear() == humans[i+1].getBirthYear()) {				
				if (humans[i].getBirthMonth() > humans[i+1].getBirthMonth()) {
					return false;
				}
				else if (humans[i].getBirthMonth() == humans[i+1].getBirthMonth()) {

					if (humans[i].getBirthDay() > humans[i+1].getBirthDay()) {
						return false;
					}
				}
			}			
		}
		
		return true;
	}
	
	private boolean verifySortByName(Human[] humans) {

		System.out.println("\nVerifySortByName");
		System.out.println("----------------");

		for (int i = 0; i < humans.length - 1; i++) {

			System.out.println(toLastNameFirstName(humans[i]));
			
			if (humans[i].getLastName().compareTo(humans[i+1].getLastName()) > 0) {
				return false; 
			}
			else if (humans[i].getLastName().compareTo(humans[i+1].getLastName()) == 0){
				if (humans[i].getFirstName().compareTo(humans[i+1].getFirstName()) >  0) {
					return false;
				}; 
			}			
		}
		
		return true;
	}
		
	private String toLastNameFirstName(Human h) {
		return String.format("%-20s%-20s%-10s (%04d/%02d/%02d)", h.getLastName(), h.getFirstName(), h.getClass().getName(), h.getBirthYear(), h.getBirthMonth(), h.getBirthDay());				
	}
	
}