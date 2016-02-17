package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import assignment04.AnagramUtil;

/**
 * This class contains the code for completing timing experiments for 
 * the analysis document 
 * @author Kaitlin Hall and Michael Mackliet
 *
 */
public class SortUtilTiming {
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

		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);
		
		/**
		 * Code for Mergesort Threshold experiment
		 */
		
		
		
		/**
		 * Code for Quicksort Pivot experiment
		 */
			//TODO implement
		
		/**
		 * Code for quicksort vs. mergesort run-time experiment 
		 */
		//Test Best Case
		try(FileWriter fw = new FileWriter(new File("src/assignment05/merge_vs_quicksort_BEST.txt"))) { //open up a file writer so we can write to file.
			

			System.out.println("Best Case \n" + "size" + "\t" + "mergesort\tquicksort"); // print to console
			fw.write("Best Case \n" + "size" + "\t" + "mergesort\tquicksort" + "\n"); // write to file.
			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  
				size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
				
				
				// Do the experiment multiple times, and average out the results
				long mergesortTotalTime = 0;
				long quicksortTotalTime = 0;
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					

					// TIME IT!
					ArrayList<Integer> mergesortTest1 = SortUtil.generateBestCase(size);
					ArrayList<Integer> quicksortTest1 = SortUtil.generateBestCase(size);
					long start = System.nanoTime();
					SortUtil.mergesort(mergesortTest1, comparator);
					long stop = System.nanoTime();
					mergesortTotalTime += stop - start;
					
					start = System.nanoTime();
					SortUtil.quicksort(quicksortTest1, comparator);
					stop = System.nanoTime();
					quicksortTotalTime += stop - start;
				}
				double mergeAverageTime = mergesortTotalTime / (double)ITER_COUNT;
				double quickAverageTime = quicksortTotalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + mergeAverageTime + "\t" + quickAverageTime); // print to console
				fw.write(size + "\t" + mergeAverageTime + "\t" + quickAverageTime + "\n"); // write to file.
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Test Average Case
		try(FileWriter fw = new FileWriter(new File("src/assignment05/merge_vs_quicksort_AVERAGE.txt"))) { //open up a file writer so we can write to file.
			

			System.out.println("Average Case \n" + "size" + "\t" + "mergesort\tquicksort"); // print to console
			fw.write("Average Case \n" + "size" + "\t" + "mergesort\tquicksort" + "\n"); // write to file.
			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  
				size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
				
				
				// Do the experiment multiple times, and average out the results
				long mergesortTotalTime = 0;
				long quicksortTotalTime = 0;
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					

					// TIME IT!
					ArrayList<Integer> mergesortTest2 = SortUtil.generateAverageCase(size);
					ArrayList<Integer> quicksortTest2 = SortUtil.generateAverageCase(size);
					long start = System.nanoTime();
					SortUtil.mergesort(mergesortTest2, comparator);
					long stop = System.nanoTime();
					mergesortTotalTime += stop - start;
					
					start = System.nanoTime();
					SortUtil.quicksort(quicksortTest2, comparator);
					stop = System.nanoTime();
					quicksortTotalTime += stop - start;
				}
				double mergeAverageTime = mergesortTotalTime / (double)ITER_COUNT;
				double quickAverageTime = quicksortTotalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + mergeAverageTime + "\t" + quickAverageTime); // print to console
				fw.write(size + "\t" + mergeAverageTime + "\t" + quickAverageTime + "\n"); // write to file.
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Worst Case
		try(FileWriter fw = new FileWriter(new File("src/assignment05/merge_vs_quicksort_WORST.txt"))) { //open up a file writer so we can write to file.
			
			System.out.println("Worst Case \n" + "size" + "\t" + "mergesort\tquicksort"); // print to console
			fw.write("Worst Case \n" + "size" + "\t" + "mergesort\tquicksort" + "\n"); // write to file.
			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  
				size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
				
				
				// Do the experiment multiple times, and average out the results
				long mergesortTotalTime = 0;
				long quicksortTotalTime = 0;
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					

					// TIME IT!
					ArrayList<Integer> mergesortTest3 = SortUtil.generateWorstCase(size);
					ArrayList<Integer> quicksortTest3 = SortUtil.generateWorstCase(size);
					long start = System.nanoTime();
					SortUtil.mergesort(mergesortTest3, comparator);
					long stop = System.nanoTime();
					mergesortTotalTime += stop - start;
					
					start = System.nanoTime();
					SortUtil.quicksort(quicksortTest3, comparator);
					stop = System.nanoTime();
					quicksortTotalTime += stop - start;
				}
				double mergeAverageTime = mergesortTotalTime / (double)ITER_COUNT;
				double quickAverageTime = quicksortTotalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + mergeAverageTime + "\t" + quickAverageTime); // print to console
				fw.write(size + "\t" + mergeAverageTime + "\t" + quickAverageTime + "\n"); // write to file.
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

		
