package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import assignment05.SortUtilTesting.IntCompare;
import java.util.Comparator;
import java.util.Random;

//import assignment04.AnagramUtil; //unless we're submitting assignment 4 code with our timing this needs to be done differently

/**
 * This class contains the code for completing timing experiments for 
 * the analysis document 
 * @author Kaitlin Hall and Michael Mackliet
 *
 */
public class SortUtilTiming {
	public static <T> void thresholdExperiment(){
		//Variable declarations 
		ArrayList<Integer> list; 
		long startTime, stopTime;
		long avgTime = 0; 
		long timesToLoop = 1000;
		int lowerBound = 5; 
		int upperBound = 15; 
		final int CAPACITY = (int)Math.pow(2, 20); 
		long[][] outputData = new long[2][(upperBound-lowerBound) + 1];
		
		//Start up computer stuff to ensure clean test 
		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		
		list = SortUtil.generateAverageCase(CAPACITY); 
		ArrayList<Integer> temp; 
		
		//Outer for loop controls the size of N. 
		for(int i = lowerBound; i <= upperBound; i++){
			int size = (int)Math.pow(2, i); 
			avgTime = 0;
			//This loop calls mergesort repeatedly on the items in a temp arrayList
			for(int j = 0; j < timesToLoop; j++){
				temp = new ArrayList<Integer>(); 
				//This loop copies size number of items into the temp array to be sorted 
				//size is determined by the outer for loop 
				for(int k = 0; k < size; k++){
					temp.add(list.get(k)); 
				}
				startTime = System.nanoTime();
				SortUtil.mergesort(temp, new IntCompare());
				stopTime = System.nanoTime();
				avgTime += (stopTime - startTime);
				
				temp.clear();
			}
			
			//Store size of N and run-time for that N into an array 
			avgTime = avgTime/timesToLoop;
			outputData[0][i-lowerBound] = size;
			outputData[1][i-lowerBound] = avgTime;
			System.out.println("Just Finished 2 to the " + i);
		}
	

	//Code to write the values contained in the output data array to a file. 
	try(FileWriter outputWriter = new FileWriter(new File("mergesortTimingThresh100.csv"))){
		outputWriter.write("Timing of mergesort() on randomly generated arrayList, size (N) where N is number"
				+ " of items contained in the list, time(ns)\n");
		for(int i = 0; i < (upperBound-lowerBound) + 1; i++){
			outputWriter.write("Trial 1," + outputData[0][i] + "," + outputData[1][i] + "\n");
		}
	} catch (IOException e){
		e.printStackTrace();
	}
}
		
	
	public static <T> void pivotExperiment(){
		//Variable declarations 
				ArrayList<Integer> list; 
				long startTime, stopTime;
				long avgTime = 0; 
				long timesToLoop = 100;
				int lowerBound = 10; 
				int upperBound = 20; 
				final int CAPACITY = (int)Math.pow(2, 20); 
				long[][] outputData = new long[2][(upperBound-lowerBound) + 1];
				
				//Start up computer stuff to ensure clean test 
				startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1000000000) { // empty block
				}
				
				list = SortUtil.generateAverageCase(CAPACITY); 
				
				//Outer for loop controls the size of N. 
				for(int i = lowerBound; i <= upperBound; i++){
					int size = (int)Math.pow(2, i); 
					avgTime = 0;
					//This loop calls quicksort repeatedly on the items in a temp arrayList
					
					for(int j = 0; j < timesToLoop; j++){
						ArrayList<Integer> temp = new ArrayList<Integer>(); 
						//This loop copies size number of items into the temp array to be sorted 
						//size is determined by the outer for loop 
						for(int k = 0; k < size; k++){
							temp.add(list.get(k)); 
						}
						startTime = System.nanoTime();
						SortUtil.quicksort(temp, new IntCompare());
						stopTime = System.nanoTime();
						avgTime += (stopTime - startTime);
						
						temp.clear();
					}
				
					//Store size of N and run-time for that N into an array 
					avgTime = avgTime/timesToLoop;
					outputData[0][i-lowerBound] = size;
					outputData[1][i-lowerBound] = avgTime;
					System.out.println("Just Finished 2 to the " + i);
				}
			

			//Code to write the values contained in the output data array to a file. 
			try(FileWriter outputWriter = new FileWriter(new File("quicksortTimingRandom.csv"))){
				outputWriter.write("Timing of mergesort() on randomly generated arrayList, size (N) where N is number"
						+ " of items contained in the list, time(ns)\n");
				for(int i = 0; i < (upperBound-lowerBound) + 1; i++){
					outputWriter.write("Trial 1," + outputData[0][i] + "," + outputData[1][i] + "\n");
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}

	private static final int ITER_COUNT = 1000;
	private static Random rand;
	private static final int listSize = 10000;
	private static Comparator<Integer> comparator = new IntCompare();
	
	protected static class IntCompare implements Comparator<Integer>{

		@Override
		public int compare(Integer i1, Integer i2) {
			return i1.compareTo(i2);
		}
		
	}
	public static void main(String[] args) {
		//Calls function to determine optimal threshold size
		SortUtilTiming.thresholdExperiment();
		
		//Calls method to determine optimal pivot-picking method
		//SortUtilTiming.pivotExperiment();

		/**
		 * Code for quicksort vs. mergesort run-time experiment 
		 */
		
//		long startTime = System.nanoTime();
//		while (System.nanoTime() - startTime < 1000000000);
//		//Test Best Case
//		try(FileWriter fw = new FileWriter(new File("src/assignment05/merge_vs_quicksort_BEST.txt"))) { //open up a file writer so we can write to file.
//			
//
//			System.out.println("Best Case \n" + "size" + "\t" + "mergesort\tquicksort"); // print to console
//			fw.write("Best Case \n" + "size" + "\t" + "mergesort\tquicksort" + "\n"); // write to file.
//			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
//				int size = (int) Math.pow(2, exp); // or ..  
//				size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
//				
//				
//				// Do the experiment multiple times, and average out the results
//				long mergesortTotalTime = 0;
//				long quicksortTotalTime = 0;
//				for (int iter = 0; iter < ITER_COUNT; iter++) {
//					
//
//					// TIME IT!
//					ArrayList<Integer> mergesortTest1 = SortUtil.generateBestCase(size);
//					ArrayList<Integer> quicksortTest1 = SortUtil.generateBestCase(size);
//					long start = System.nanoTime();
//					SortUtil.mergesort(mergesortTest1, comparator);
//					long stop = System.nanoTime();
//					mergesortTotalTime += stop - start;
//					
//					start = System.nanoTime();
//					SortUtil.quicksort(quicksortTest1, comparator);
//					stop = System.nanoTime();
//					quicksortTotalTime += stop - start;
//				}
//				double mergeAverageTime = mergesortTotalTime / (double)ITER_COUNT;
//				double quickAverageTime = quicksortTotalTime / (double)ITER_COUNT;
//				System.out.println(size + "\t" + mergeAverageTime + "\t" + quickAverageTime); // print to console
//				fw.write(size + "\t" + mergeAverageTime + "\t" + quickAverageTime + "\n"); // write to file.
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		//Test Average Case
//		try(FileWriter fw = new FileWriter(new File("src/assignment05/merge_vs_quicksort_AVERAGE.txt"))) { //open up a file writer so we can write to file.
//
//			System.out.println("Average Case \n" + "size" + "\t" + "mergesort\tquicksort"); // print to console
//			fw.write("Average Case \n" + "size" + "\t" + "mergesort\tquicksort" + "\n"); // write to file.
//			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
//				int size = (int) Math.pow(2, exp); // or ..  
//				size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
//				
//				
//				// Do the experiment multiple times, and average out the results
//				long mergesortTotalTime = 0;
//				long quicksortTotalTime = 0;
//				for (int iter = 0; iter < ITER_COUNT; iter++) {
//					
//
//					// TIME IT!
//					ArrayList<Integer> mergesortTest2 = SortUtil.generateAverageCase(size);
//					ArrayList<Integer> quicksortTest2 = SortUtil.generateAverageCase(size);
//					long start = System.nanoTime();
//					SortUtil.mergesort(mergesortTest2, comparator);
//					long stop = System.nanoTime();
//					mergesortTotalTime += stop - start;
//					
//					start = System.nanoTime();
//					SortUtil.quicksort(quicksortTest2, comparator);
//					stop = System.nanoTime();
//					quicksortTotalTime += stop - start;
//				}
//				double mergeAverageTime = mergesortTotalTime / (double)ITER_COUNT;
//				double quickAverageTime = quicksortTotalTime / (double)ITER_COUNT;
//				System.out.println(size + "\t" + mergeAverageTime + "\t" + quickAverageTime); // print to console
//				fw.write(size + "\t" + mergeAverageTime + "\t" + quickAverageTime + "\n"); // write to file.
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		//Worst Case
//		try(FileWriter fw = new FileWriter(new File("src/assignment05/merge_vs_quicksort_WORST.txt"))) { //open up a file writer so we can write to file.
//			
//			System.out.println("Worst Case \n" + "size" + "\t" + "mergesort\tquicksort"); // print to console
//			fw.write("Worst Case \n" + "size" + "\t" + "mergesort\tquicksort" + "\n"); // write to file.
//			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
//				int size = (int) Math.pow(2, exp); // or ..  
//				size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
//				
//				
//				// Do the experiment multiple times, and average out the results
//				long mergesortTotalTime = 0;
//				long quicksortTotalTime = 0;
//				for (int iter = 0; iter < ITER_COUNT; iter++) {
//					
//
//					// TIME IT!
//					ArrayList<Integer> mergesortTest3 = SortUtil.generateWorstCase(size);
//					ArrayList<Integer> quicksortTest3 = SortUtil.generateWorstCase(size);
//					long start = System.nanoTime();
//					SortUtil.mergesort(mergesortTest3, comparator);
//					long stop = System.nanoTime();
//					mergesortTotalTime += stop - start;
//					
//					start = System.nanoTime();
//					SortUtil.quicksort(quicksortTest3, comparator);
//					stop = System.nanoTime();
//					quicksortTotalTime += stop - start;
//				}
//				double mergeAverageTime = mergesortTotalTime / (double)ITER_COUNT;
//				double quickAverageTime = quicksortTotalTime / (double)ITER_COUNT;
//				System.out.println(size + "\t" + mergeAverageTime + "\t" + quickAverageTime); // print to console
//				fw.write(size + "\t" + mergeAverageTime + "\t" + quickAverageTime + "\n"); // write to file.
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
	}
}

		
