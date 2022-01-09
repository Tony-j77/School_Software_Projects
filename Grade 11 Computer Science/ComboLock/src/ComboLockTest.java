
import static org.junit.Assert.*;

import org.junit.Test;

public class ComboLockTest {
	/*
	 * Test out the constructor that only uses a password to see if it
	 * properly generates numbers
	 */
	@Test
	public void testPasswordConstructor() {
		ComboLock lock = new ComboLock("Kitty");
		//get the numbers that were generated randomly
		int[] numbers = lock.forgotNumbers("Kitty");
		
		//Test that there is no duplication in the numbers
		assertFalse(numbers[0]==numbers[1]&&numbers[1]==numbers[2]);
		
		//Test that the numbers are in a valid range
		assertTrue(1<=numbers[0] && numbers[0]<=60);
		assertTrue(1<=numbers[1] && numbers[0]<=60);
		assertTrue(1<=numbers[2] && numbers[0]<=60);

		//Test with three turns with correct numbers
		lock.turnRight(numbers[0]);
		lock.turnLeft(numbers[1]);
		lock.turnRight(numbers[2]);
		assertTrue(lock.isOpen());
		
		//Test with wrong turns right numbers
		lock.turnLeft(numbers[0]);
		lock.turnRight(numbers[1]);
		lock.turnLeft(numbers[2]);
		assertFalse(lock.isOpen());
		
		//Test with wrong turns to start and finishing with the right turns
		lock.turnLeft(numbers[0]);
		lock.turnRight(numbers[0]);
		lock.turnRight(numbers[2]);
		lock.turnRight(numbers[0]);
		lock.turnLeft(numbers[1]);
		lock.turnRight(numbers[2]);
		assertTrue(lock.isOpen());
		
		//Test with right numbers in reverse order
		lock.turnRight(numbers[2]);
		lock.turnLeft(numbers[1]);
		lock.turnRight(numbers[0]);
		assertFalse(lock.isOpen());
	}
	
	@Test
	public void testNumberConstructor() {
		ComboLock lock = new ComboLock(12,34,55);
		
		//Test with three turns with correct numbers
		lock.turnRight(12);
		lock.turnLeft(34);
		lock.turnRight(55);
		assertTrue(lock.isOpen());
		
		//Test with wrong turns right numbers
		lock.turnLeft(12);
		lock.turnRight(34);
		lock.turnLeft(55);
		assertFalse(lock.isOpen());
		
		//Test with wrong turns to start and finishing with the right turns
		lock.turnLeft(12);
		lock.turnRight(12);
		lock.turnRight(55);
		lock.turnRight(12);
		lock.turnLeft(34);
		lock.turnRight(55);
		assertTrue(lock.isOpen());
		
		//Test with right numbers in reverse order
		lock.turnRight(34);
		lock.turnLeft(55);
		lock.turnRight(12);
		assertFalse(lock.isOpen());
	}
	
	@Test
	public void testNumberAndPasswordConstructor() {
		ComboLock lock = new ComboLock(12,34,55,"Kitty");
		
		int[] numbers = lock.forgotNumbers("Kitty");
		
		//Test with three turns with correct numbers
		lock.turnRight(numbers[0]);
		lock.turnLeft(numbers[1]);
		lock.turnRight(numbers[2]);
		assertTrue(lock.isOpen());
		
		//Test with wrong turns right numbers
		lock.turnLeft(numbers[0]);
		lock.turnRight(numbers[1]);
		lock.turnLeft(numbers[2]);
		assertFalse(lock.isOpen());
		
		//Test with wrong turns to start and finishing with the right turns
		lock.turnLeft(numbers[0]);
		lock.turnRight(numbers[0]);
		lock.turnRight(numbers[2]);
		lock.turnRight(12);
		lock.turnLeft(34);
		lock.turnRight(55);
		assertTrue(lock.isOpen());
		
		//Test with right numbers in reverse order
		lock.turnRight(35);
		lock.turnLeft(55);
		lock.turnRight(12);
		assertFalse(lock.isOpen());
	}

}
