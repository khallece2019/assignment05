package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * This class contains methods for recursively sorting a list of items of any type
 * using the mergesort and quicksort algorithms. This class also contains testing
 * code to determine the complexity of the best, average, and worst case run-times
 * of these algorithms
 * @author Kaitlin Hall and Michael Mackliet
 *
 */
public class SortUtil{
	//Variable declarations 
	private static Random rand;
	private static int thresholdSize = 3;
	
	/**
	 * This method performs an insertion sort on a list of data using 
	 * the specified comparator. This method will be invoked when N (list size) 
	 * is small enough to be more efficient than a recursive sort. 
	 * @param data -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 */
	public static <T> void insertionSort(ArrayList<T> data, Comparator<? super T> comparator, int left, int right){

		for(int i=left; i <= right; i++){
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
		int right = list.size()-1;
		mergeSortRecursive(list, comparator, left, right);
		return;
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
		if(right-left<=thresholdSize){
			insertionSort(list, comparator, left, right);
			return;
		}
		else{
			int mid = (left + right) / 2;
			mergeSortRecursive(list, comparator, left, mid);
			mergeSortRecursive(list, comparator,  mid+1, right);
			merge(list, comparator, left, mid, right);
		}
	}
	
	private static<T> void merge(ArrayList<T> list, Comparator<? super T> comparator, int left, int mid, int right){
		// create temp array for holding merged arr
		int i1 = left;
		int i2 = mid+1;
		ArrayList<T> temp = new ArrayList<T>();
		//put smaller of two sub-lists into temp
		while(i1 <= mid && i2 <= right){
			int comparison = comparator.compare(list.get(i1), list.get(i2));
			if(comparison<0){
				temp.add(list.get(i1));
				i1++;
			}
			else if(comparison == 0){
				temp.add(list.get(i1));
				temp.add(list.get(i2));
				i1++;
				i2++;
			}
			else{
				temp.add(list.get(i2));
				i2++;
			}
			
		}
		//copy anything left over from larger half to temp;
		while(i1<=mid){
			temp.add(list.get(i1));
			i1++;
		}
		while(i2<=right){
			temp.add(list.get(i2));
			i2++;
		}
		//copy temp over to list
		int j = 0;
		for(int i = left; i<=right; i++){
			list.set(i, temp.get(j));
			j++;
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
		int right = list.size() -1;
		
		quicksortRecursive(list, comparator, left, right);
		return;
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
//		if(right - left <= thresholdSize){
//			insertionSort(list, comparator, left, right);
//		}
		if(left >= right){
			return; 
		}
		else{
			int pivotIndex = partition(list, comparator, left, right);
			quicksortRecursive(list, comparator, left, pivotIndex-1);
			quicksortRecursive(list, comparator, pivotIndex+1, right); 
		}
		return;
	}
	
	private static <T> void swap(ArrayList<T> list, int i1, int i2){
		T temp = list.get(i1); 
		list.set(i1, list.get(i2)); 
		list.set(i2, temp); 
	}
	
	private static <T> int partition(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound){
		//set pivot equal to the value at the middle index of the array
		T pivot = list.get((rightBound - leftBound)/ 2 + leftBound);  
		int pivotIndex = (rightBound - leftBound)/ 2 + leftBound; 
		
		//find pivot, swap with rightBound
		swap(list, rightBound, pivotIndex);
		
		//L = leftBound, R = rightBound - 1;
		int left = leftBound;  
		int right = rightBound - 1; 
		
		int leftComparison = comparator.compare(list.get(left), pivot); 
		int rightComparison = comparator.compare(list.get(right), pivot); 
		
		while(left <= right){
			if(leftComparison <= 0){
				left++; 
				leftComparison = comparator.compare(list.get(left), pivot); 
				continue; // find next item > pivot
			}
			if(rightComparison >= 0){
				right--; 
				rightComparison = comparator.compare(list.get(right), pivot);
				continue; // find its “swapping partner”
			}
			
			//swap left and right-hand elements of list
			//(arr, L, R); // partners found, swap them
			swap(list, left, right); 
			left++; 
			right--; 
			
			leftComparison = comparator.compare(list.get(left), pivot); 
			rightComparison = comparator.compare(list.get(right), pivot); 
			
		}
		swap(list, left, rightBound); 
		pivotIndex = left;  
		return pivotIndex; 
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
