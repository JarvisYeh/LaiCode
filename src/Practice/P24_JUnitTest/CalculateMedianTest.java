package Practice.P24_JUnitTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateMedianTest {
	CalculateMedian cm;

	@Before
	public void setUp() {
		cm = new CalculateMedian();
	}

	@Test
	public void TestNPE() {
		int[] arr = null;
		double res = cm.getMedian(arr);
		// compare standard result with real result with 0.00001 error allowed
		assertEquals(0, res, 0.00001);
	}

	@Test
	public void TestEvenUnsorted() {
		int[] arr = {1, 5, 2, 7};
		double res = cm.getMedian(arr);
		assertEquals(3.5, res, 0.00001);
	}

	@Test
	public void TestOddUnsorted() {
		int[] arr = {1, 2, 7, 5, 9};
		double res = cm.getMedian(arr);
		assertEquals(5, res, 0.00001);
	}

	@Test
	public void TestIntBoundary() {
		int[] arr = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
		double res = cm.getMedian(arr);
		assertEquals(Integer.MAX_VALUE, res, 0.00001);
	}

	@Test
	public void Duplicate() {
		int[] arr = {4, 4, 4, 4};
		double res = cm.getMedian(arr);
		assertEquals(4, res, 0.00001);
	}
}