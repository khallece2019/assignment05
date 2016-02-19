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
	private static int thresholdSize = 100;
	
	public static int getThresholdSize(){
		return thresholdSize;
	}
	
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
		ArrayList<T> temp = new ArrayList<T>(); 
		int left = 0;
		int right = list.size()-1;
		mergeSortRecursive(list, temp, comparator, left, right);
		return;
	}
	
	/**
	 * This method performs a mergesort on a list of items until the number of items to be sorted becomes sufficiently small
	 * and then the method stops the recursive calls and switches to insertion sort
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 * @param left -- lower bound of the list
	 * @param right -- upper bound of the list
	 */
	private static <T> void mergeSortRecursive(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> comparator, int left, int right){
		if(left>=right){
			return; 
		}
		
		int mid = (left + right) / 2;
		if(right-left<=thresholdSize){
			insertionSort(list, comparator, left, right);
			return;
		}
		else{
			mergeSortRecursive(list, temp, comparator, left, mid);
			mergeSortRecursive(list, temp, comparator,  mid+1, right);
			merge(list, temp, comparator, left, mid, right);
		}
	}
	
	/**
	 * This method performs the merge step of the recursive mergesort. The method is invoked
	 * after the base cases for the left sub-arrayList and the right sub-arrayList have 
	 * been reached
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 * @param left -- lower bound of the left sub-arrayList
	 * @param mid -- marks division between left and right sub-arrayLists 
	 * @param right -- upper bound of the right sub-arrayList
	 */
	private static<T> void merge(ArrayList<T> list, ArrayList<T> temp, Comparator<? super T> comparator, int left, int mid, int right){
		// create temp array for holding merged arr
		int i1 = left;
		int i2 = mid+1;
		
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
		if(list.size() == 0 || list.size() == 1){
			return; 
		}
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
	 * @param left -- lower bound of the list
	 * @param right -- upper bound of the list
	 */
	private static <T> void quicksortRecursive(ArrayList<T> list, Comparator<? super T> comparator, int left, int right){
		if(right-left<=thresholdSize){
			insertionSort(list, comparator, left, right);
			return;
		}
		
		else{
			int pivotIndex = partition(list, comparator, left, right);
			quicksortRecursive(list, comparator, left, pivotIndex-1);
			quicksortRecursive(list, comparator, pivotIndex+1, right); 
		}
		return;
	}
	
	/**
	 * This helper method performs a swap between to items in the arrayList. 
	 * It is used in both mergesort and quicksort 
	 * @param list -- list of objects of any type 
	 * @param i1 -- index where the first value to be swapped occurs. 
	 * @param i2 -- index where the second value to be swapped occurs. 
	 */
	private static <T> void swap(ArrayList<T> list, int i1, int i2){
		T temp = list.get(i1); 
		list.set(i1, list.get(i2)); 
		list.set(i2, temp); 
	}
	
	/**
	 * Computes the median of the values at the lowest, middle, and highest
	 * indexes in the ArrayList. The index of the median of the three is used
	 * as the pivot index  
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 * @param leftBound -- lower bound of the list
	 * @param rightBound -- upper bound of the list
	 * @return -- index where pivot value occurs
	 */
	private static <T> int medianOfThreePivot(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound){
		ArrayList<T> temp = new ArrayList<T>();
		int mid = (rightBound-leftBound)/2 + leftBound;
		temp.add(list.get(rightBound));
		temp.add(list.get(leftBound));
		temp.add(list.get(mid));
		SortUtil.insertionSort(temp, comparator, 0, 2);
		T pivot = temp.get(1);
		int pivotIndex;
		if(pivot.equals(list.get(rightBound))){
			pivotIndex = rightBound;
		}
		else if(pivot.equals(list.get(leftBound))){
			pivotIndex = leftBound;
		}
		else{
			pivotIndex = mid;
		}
		
		return pivotIndex; 
	}
	
	/**
	 * This method randomly selects and index in the arrayList. 
	 * The value at this index will be used as a pivot value. 
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 * @param leftBound -- lower bound of the list
	 * @param rightBound -- upper bound of the list
	 * @return -- index where pivot value occurs
	 */
	private static <T> int randomPivot(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int pivotIndex = rand.nextInt(1+(rightBound-leftBound))+leftBound;
		return pivotIndex; 
	}
	
	/**
	 * This method returns the index of the middle of the arrayList. 
	 * The value at this index will be used as the pivot value. 
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 * @param leftBound -- lower bound of the list
	 * @param rightBound -- upper bound of the list
	 * @return -- index where pivot value occurs
	 */
	private static <T> int middlePivot(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound){
		int pivotIndex = (rightBound - leftBound)/ 2 + leftBound; 
		return pivotIndex; 
	}
	
	/**
	 * This method performs the partition step of the recursive quicksort function. This method is called before
	 * the recursive case meaning each successive recursive call will also invoke the partition method.
	 * @param list -- list of objects of any type 
	 * @param comparator -- comparator object that specifies how objects in the list should be compared. 
	 * @param leftBound -- lower bound of the list
	 * @param rightBound -- upper bound of the list
	 * @return value of the pivot index to be used in the next recursive call 
	 */
	private static <T> int partition(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound){
		//Different methods of determining the pivot value (comment out methods not being used). 
		int pivotIndex = SortUtil.medianOfThreePivot(list, comparator, leftBound, rightBound);
		//SortUtil.middlePivot(list, comparator, leftBound, rightBound); 
		//SortUtil.randomPivot(list, comparator, leftBound, rightBound); 
		
		T pivot = list.get(pivotIndex); 
		
		//find pivot, swap with rightBound
		swap(list, rightBound, pivotIndex);
		
		//L = leftBound, R = rightBound - 1;
		int left = leftBound;  
		int right = rightBound - 1; 
		
		int leftComparison = comparator.compare(list.get(left), pivot); 
		int rightComparison = comparator.compare(list.get(right), pivot); 
		
		while(left <= right){
			leftComparison = comparator.compare(list.get(left), pivot);
			if(leftComparison <= 0){
				left++; 
				continue; // find next item > pivot
			}
			rightComparison = comparator.compare(list.get(right), pivot);
			if(rightComparison >= 0){
				right--; 
				continue; // find its “swapping partner”
			}
			
			//swap left and right-hand elements of list
			//(arr, L, R); // partners found, swap them
			swap(list, left, right); 
			left++; 
			right--; 			
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
