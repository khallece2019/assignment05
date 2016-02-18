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
	
	protected static class OrderStringLength implements Comparator<String>{

		@Override
		public int compare(String s1, String s2) {
			if(s1.length() > s2.length()){
				return 1;
			}
			else if(s1.length() == s2.length()){
				return 0;
			}
			else
				return -1;
		}
		
	}
	
	@Test
	public void test() {

		ArrayList<Integer> testList = SortUtil.generateWorstCase(10);
		SortUtil.quicksort(testList, new IntCompare());
		assertEquals(new Integer(10), testList.get(9));
		System.out.println("It prints stuff here");
	}
	
	@Test
	public void sortOnThesholdSizedList(){
		ArrayList<Integer> testList1 = SortUtil.generateWorstCase(SortUtil.getThresholdSize());
		ArrayList<Integer> testList2 = SortUtil.generateWorstCase(SortUtil.getThresholdSize());
		ArrayList<Integer> answer = SortUtil.generateWorstCase(SortUtil.getThresholdSize());
		answer.sort(new IntCompare());
		SortUtil.mergesort(testList1, new IntCompare());
		SortUtil.quicksort(testList2, new IntCompare());
		assertEquals(true, answer.equals(testList1));
		assertEquals(true, answer.equals(testList2));
	}
	
	@Test
	public void sortOnEmptyList(){
		ArrayList<Integer> testList1 = new ArrayList<Integer>();
		ArrayList<Integer> testList2 = new ArrayList<Integer>();
		ArrayList<Integer> answer = new ArrayList<Integer>();
		SortUtil.mergesort(testList1, new IntCompare());
		SortUtil.quicksort(testList2, new IntCompare());
		assertEquals(true, answer.equals(testList1));
		assertEquals(true, answer.equals(testList2));
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
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("in");
		list.add("car");
		list.add("eaten");
		list.add("horrible");
		list.add("zero");
		list.add("yellow");
		list.add("university");
		ArrayList<String> answer = new ArrayList<String>();
		ArrayList<String> quicksortList = new ArrayList<String>();
		for(int i= 0; i<list.size();i++){
			answer.add(list.get(i));
			quicksortList.add(list.get(i));
		}
		answer.sort(new OrderStringLength());
		SortUtil.quicksort(quicksortList, new OrderStringLength());
		SortUtil.mergesort(list, new OrderStringLength());
		assertEquals(true, answer.equals(list));	
		assertEquals(true, answer.equals(quicksortList));
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
		ArrayList<Integer> testList1 = SortUtil.generateWorstCase(100);
		ArrayList<Integer> answer = SortUtil.generateWorstCase(100);
		answer.sort(new IntCompare());
		SortUtil.mergesort(testList1, new IntCompare());
		assertEquals(true, answer.equals(testList1));
	}
	
	@Test
	public void quicksortWherePivotIsSmallestElement(){
		
	}
	
	@Test
	public void quicksortWherePivotIsLargestElement(){
		
	}
	
}
