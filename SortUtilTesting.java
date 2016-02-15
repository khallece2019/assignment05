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

		ArrayList<Integer> testList = SortUtil.generateWorstCase(123);
		SortUtil.quicksort(testList, new IntCompare());
		assertEquals(new Integer(10), testList.get(9));
	}
	
	@Test
	public void sortOnThesholdSizedList(){
		
	}
	
	@Test
	public void sortOnEmptyList(){
		
	}
	
	@Test
	public void sortOnListWithOneItem(){
		
	}
	
	@Test
	public void sortOnLargeRandomlyGeneratedList(){
		
	}
	
	@Test
	public void sortOnListOfObjectsThatDoNotUseNaturalOrdering(){
		
	}
	
	@Test
	public void sortOnAlreadySortedList(){
		
	}
	
	@Test
	public void mergesortOnWorstCaseList(){
		
	}
	
	@Test
	public void quicksortWherePivotIsSmallestElement(){
		
	}
	
	@Test
	public void quicksortWherePivotIsLargestElement(){
		
	}
	
}
