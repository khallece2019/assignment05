package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * This class contains methods for recursively sorting a list of items of any type
 * using the mergesort and quicksort algorithms. This class also contains testing
 * code to determine the complexity of the best, average, and worst case runtimes
 * of these algorithms
 * @author Kaitlin Hall and Michael Mackliet
 *
 */
public class SortUtil{
	//Variable declarations 
	private static Random rand;
	private static int threshold;
	private static int thresholdSize = 16;
	
	/**
	 * This method performs an insertion sort on a list of data using 
	 * the specified comparator. This method will be invoked when N (list size) 
	 * is small enough to be more efficient than a recursive sort. 
	 * @param data -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 */
	public static <T> void insertionSort(ArrayList<T> data, Comparator<? super T> comparator){

		for(int i=1; i < data.size(); i++){
			T index = data.get(i);
			int j = i;
			
			while (j > 0 && comparator.compare(data.get(j-1), index) > 0) {
				data.set(j, data.get(j-1));
				j--;
			}
			data.set(j, index);
		}
	}
	
	/**
	 * This is the driver method for the mergesort method. It sets up the variables needed
	 * to run the recursive method mergeSortRecursive
	 * @param list --  list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 */
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator){
		int left = 0;
		int right = list.size();
		mergeSortRecursive(list, comparator, left, right);
	}
	
	/**
	 * This method performs a mergesort on a list of items until the number of items to be sorted becomes sufficiently small
	 * and then the method stops the recursive calls and switches to insertion sort
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 * @param left -- 
	 * @param right --
	 */
	private static <T> void mergeSortRecursive(ArrayList<T> list, Comparator<? super T> comparator, int left, int right){
		if(threshold<thresholdSize){
			insertionSort(list, comparator);
		}
		else{
			
		}
	}
	
	/**
	 * This is the driver method for the quicksort method. It sets up the variables needed 
	 * to run the recursive method quickSortRecursive 
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 */
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator){
		int left = 0;
		int right = list.size();
		quicksortRecursive(list, comparator, left, right);
	}
	
	/**
	 * This method performs a quicksort on a list of items until the number of items to be sorted becomes sufficiently small
	 * and then the method stops the recursive calls and switches to insertion sort
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 * @param left --
	 * @param right --
	 */
	private static <T> void quicksortRecursive(ArrayList<T> list, Comparator<? super T> comparator, int left, int right){
		if(threshold<thresholdSize){
			insertionSort(list, comparator);
		}
		else{
			
		}
	}
	
	
	/**
	 * This testing method generates a list of items from 1 to size in order to test the run-time 
	 * of the sorting algorithm for a best-case scenario (list is already sorted). 
	 * @param size -- number of items contained in the list
	 * @return -- list of size number of items.
	 */
	public static ArrayList<Integer> generateBestCase(int size){
		ArrayList<Integer> bestCase = new ArrayList<Integer>(); 
		for(int i = 1; i <= size; i++){
			bestCase.add(i); 
		}
		
		return bestCase; 
		
	}
	
	/**
	 * This testing method generates a list of items from 1 to size in a random order in order
	 * to test the run-time performance of the sorting algorithm in the average-case scenario. 
	 * @param size -- number of items contained in the list
	 * @return -- list of size number of items.
	 */
	public static ArrayList<Integer> generateAverageCase(int size){
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		ArrayList<Integer> averageCase = new ArrayList<Integer>(size); 
		for(int i = 1; i <= size; i++){
			averageCase.add(rand.nextInt(size)); 
		}
		
		return averageCase; 
	}
	
	/**
	 * This testing method generates a list of items from 1 to size in reverse order to
	 * test the run-time performance of a sorting algorithm in the worst-case scenario.  
	 * @param size -- number of items contained in the list
	 * @return -- list of size number of items.
	 */
	public static ArrayList<Integer> generateWorstCase(int size){
		ArrayList<Integer> worstCase = new ArrayList<Integer>(size); 
		for(int i = size; i >= 1; i--){
			worstCase.add(i); 
		}
		
		return worstCase; 
	}
}
