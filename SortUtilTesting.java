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
		//not sure how to test this one since trying to access any index
		//will throw outofBounds exception (since it's empty)
	}
	
	@Test
	public void sortOnListWithOneItem(){
		ArrayList<Integer> mergeTestList = new ArrayList<Integer>(); 
		mergeTestList.add(new Integer(6)); 
		SortUtil.mergesort(mergeTestList, new IntCompare());
		assertEquals(new Integer(6), mergeTestList.get(0)); 
		
		ArrayList<Integer> quickTestList = new ArrayList<Integer>(); 
		quickTestList.add(new Integer(4)); 
		SortUtil.mergesort(quickTestList, new IntCompare());
		assertEquals(new Integer(4), quickTestList.get(0)); 
		
	}
	
	@Test
	public void sortOnLargeRandomlyGeneratedList(){
		ArrayList<Integer> mergeTestList = SortUtil.generateAverageCase(1000); 
		SortUtil.mergesort(mergeTestList, new IntCompare());
		for(int i = 0; i < mergeTestList.size() - 1; i++){
			if(mergeTestList.get(i) > mergeTestList.get(i + 1)){
				fail("Mergesort failed: should not happen"); 
			}
		}
		
		ArrayList<Integer> quickTestList = SortUtil.generateAverageCase(1000); 
		SortUtil.quicksort(quickTestList, new IntCompare());
		for(int i = 0; i < quickTestList.size() - 1; i++){
			if(quickTestList.get(i) > quickTestList.get(i + 1)){
				fail("Mergesort failed: should not happen"); 
			}
		}
		
	}
	
	@Test
	public void sortOnListOfObjectsThatDoNotUseNaturalOrdering(){
		
	}
	
	@Test
	public void sortOnAlreadySortedList(){
		ArrayList<Integer> mergeTestList = SortUtil.generateBestCase(100); 
		SortUtil.mergesort(mergeTestList, new IntCompare());
		for(int i = 0; i < mergeTestList.size() - 1; i++){
			if(mergeTestList.get(i) > mergeTestList.get(i + 1)){
				fail("Mergesort failed: should not happen"); 
			}
		}
		
		ArrayList<Integer> quickTestList = SortUtil.generateBestCase(100);
		SortUtil.quicksort(quickTestList, new IntCompare());
		for(int i = 0; i < quickTestList.size() - 1; i++){
			if(quickTestList.get(i) > quickTestList.get(i + 1)){
				fail("Quicksort failed: should not happen"); 
			}
		}
		
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
