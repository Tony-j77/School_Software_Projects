import junit.framework.TestCase;

public class SumOfNumbersTest extends TestCase {

	public void test() {
		
		SumOfNumbers sn = new SumOfNumbers();

		assertEquals("1 + 2 + 3 + 4 + 5 = 15", sn.getSum(1,5));
		assertEquals("10 + 11 + 12 + 13 + 14 + 15 + 16 + 17 + 18 + 19 + 20 = 165", sn.getSum(10,20));
		assertEquals("21 = 21", sn.getSum(21,21));
		assertEquals("", sn.getSum(10,5));

	}
	
}
