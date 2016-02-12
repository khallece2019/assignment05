package assignment05;

import java.util.ArrayList;
import java.util.Comparator;

public class SortUtil{
	private int threshold;
	
	public static <T> void insertionSort(T[] data, Comparator<? super T> comparator){

		for(int i=1; i < data.length; i++){
			T index = data[i];
			int j = i;
			
			while (j > 0 && comparator.compare(data[j - 1], index) > 0) {
				data[j] = data[j - 1];
				j--;
			}
			data[j] = index;
		}
	}
	
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator){
		
	}
	
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator){
		
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
