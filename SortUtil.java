package assignment05;

import java.util.ArrayList;
import java.util.Comparator;

public class SortUtil{
	private static int threshold;
	private static int thresholdSize = 16;
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
	
	//Driver method
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator){
		int left = 0;
		int right = list.size();
		mergeSortRecursive(list, comparator, left, right);
	}
	
	private static <T> void mergeSortRecursive(ArrayList<T> list, Comparator<? super T> comparator, int left, int right){
		if(threshold<thresholdSize){
			insertionSort(list, comparator);
		}
		else{
			
		}
	}
	
	//Driver method
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator){
		int left = 0;
		int right = list.size();
		quicksortRecursive(list, comparator, left, right);
	}
	
	private static <T> void quicksortRecursive(ArrayList<T> list, Comparator<? super T> comparator, int left, int right){
		if(threshold<thresholdSize){
			insertionSort(list, comparator);
		}
		else{
			
		}
	}
	
	
	public static ArrayList<Integer> generateBestCase(int size){
		return null;
	}
	
	public static ArrayList<Integer> generateAverageCase(int size){
		return null;
	}
	
	public static ArrayList<Integer> generateWorstCase(int size){
		return null;
	}
}
