package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Test;

/**
 * This class is designed to test the methods defined in
 * the SortUtil class. 
 * @author Kaitlin Hall and Michael Mackliet 
 *
 */
public class SortUtilTesting {
	protected static class IntCompare implements Comparator<Integer>{

		@Override
		public int compare(Integer i1, Integer i2) {
			return i1.compareTo(i2);
		}
		
	}
	@Test
	public void test() {

		ArrayList<Integer> testList = SortUtil.generateWorstCase(10);
		SortUtil.mergesort(testList, new IntCompare());
		assertEquals(new Integer(3), testList.get(2));
	}

}
