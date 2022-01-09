import java.time.LocalDateTime;

import junit.framework.TestCase;

public class HumansTest extends TestCase {

	Human burns;
	Adult homer;
	Youth lisa;
	HenryWiseWoodStudent nemo;
	
	private void createSimpsons() {
		burns = new Human(1901, 03, 11, "Montgomery", "Burns", Gender.MALE);
		homer = new Adult(1987, 04, 19, "Homer", "Simpson", Gender.MALE,
				"Springfield Nuclear Power Plant", "Nuclear Safety Inspector");
		lisa = new Youth(2005, 01, 01, "Lisa", "Simpson", Gender.FEMALE, 3,
				"Springfield Elementary");
		nemo = new HenryWiseWoodStudent(1998, 04, 26,
				"Sine", "Nomine", Gender.FEMALE, 11, 138, "Mr. McDonald");
	}

	public void testConstructors() {
		createSimpsons();
		assertEquals(true, burns != null);
		assertEquals(true, homer != null);
		assertEquals(true, lisa != null);
		assertEquals(true, nemo != null);
	}
	
	public void testAccessors() {
		createSimpsons();

		assertEquals(1901, burns.getBirthYear());
		assertEquals(3, burns.getBirthMonth());
		assertEquals(11, burns.getBirthDay());
		assertEquals("Montgomery", burns.getFirstName());
		assertEquals("Burns", burns.getLastName());

		assertEquals(2005, lisa.getBirthYear());
		assertEquals(1, lisa.getBirthMonth());
		assertEquals(1, lisa.getBirthDay());
		assertEquals("Lisa", lisa.getFirstName());
		assertEquals("Simpson", lisa.getLastName());
		assertEquals(3, lisa.getSchoolGrade());
		assertEquals("Springfield Elementary", lisa.getSchoolName());

		assertEquals(1998, nemo.getBirthYear());
		assertEquals(04, nemo.getBirthMonth());
		assertEquals(26, nemo.getBirthDay());
		assertEquals("Sine", nemo.getFirstName());
		assertEquals("Nomine", nemo.getLastName());
		assertEquals(11, nemo.getSchoolGrade());
		assertEquals("Henry Wise Wood High School", nemo.getSchoolName());
	}

	public void testMutators() {
		createSimpsons();

		burns.setFirstName("Monty");
		burns.setLastName("Burnsy");
		assertEquals("Monty", burns.getFirstName());
		assertEquals("Burnsy", burns.getLastName());
		
		homer.setFirstName("Ned");
		homer.setLastName("Flanders");
		assertEquals("Ned", homer.getFirstName());
		assertEquals("Flanders", homer.getLastName());
	}
	
	public void testTypes() {
		createSimpsons();

		Human person1 = nemo;		
		assertEquals(true, person1 instanceof Human);
		assertEquals(true, person1 instanceof Youth);
		assertEquals(true, person1 instanceof HenryWiseWoodStudent);
		assertEquals(false, person1 instanceof Adult);

		Human person2 = homer;		
		assertEquals(true, person2 instanceof Human);
		assertEquals(false, person2 instanceof Youth);
		assertEquals(false, person2 instanceof HenryWiseWoodStudent);
		assertEquals(true, person2 instanceof Adult);

		Human person3 = burns;		
		assertEquals(true, person3 instanceof Human);
		assertEquals(false, person3 instanceof Youth);
		assertEquals(false, person3 instanceof HenryWiseWoodStudent);
		assertEquals(false, person3 instanceof Adult);

	}

	
	public void testCalculateCurrentAge() {
		;
		Adult bloggins;
		LocalDateTime testDate;
		LocalDateTime currentDate = LocalDateTime.now();

		testDate = currentDate.minusYears(40).minusMonths(1).minusDays(3);
		//should be 40 years 1 month 3 days old
		bloggins = new Adult(testDate.getYear(), testDate.getMonthValue(), testDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(40, bloggins.calculateCurrentAgeInYears());

		testDate = currentDate.minusYears(40).minusDays(1);
		//should be 40 years 1 day old
		bloggins = new Adult(testDate.getYear(), testDate.getMonthValue(), testDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(40, bloggins.calculateCurrentAgeInYears());

		testDate = currentDate.minusYears(40);
		//should be exactly 40 years old
		bloggins = new Adult(testDate.getYear(), testDate.getMonthValue(), testDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(40, bloggins.calculateCurrentAgeInYears());

		testDate = currentDate.minusYears(40).plusMonths(1);
		//should be 39 years 11 months old
		bloggins = new Adult(testDate.getYear(), testDate.getMonthValue(), testDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(39, bloggins.calculateCurrentAgeInYears());
		
		testDate = currentDate.minusYears(40).plusDays(1);
		//should be 39 years 364 days old
		bloggins = new Adult(testDate.getYear(), testDate.getMonthValue(), testDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(39, bloggins.calculateCurrentAgeInYears());

		testDate = currentDate.minusYears(40).plusMonths(1).minusDays(3);
		//should be 39 years 11 months 25-28 days old
		bloggins = new Adult(testDate.getYear(), testDate.getMonthValue(), testDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(39, bloggins.calculateCurrentAgeInYears());
	
	}
	
	
	public void testPublicIntroduction() {
		createSimpsons();
		
		Introducer introducer = new Introducer();
		System.out.println(introducer.createPublicIntroduction(burns));
		System.out.println(introducer.createPublicIntroduction(homer));
		System.out.println(introducer.createPublicIntroduction(lisa));
		System.out.println(introducer.createPublicIntroduction(nemo));		
	}

}
