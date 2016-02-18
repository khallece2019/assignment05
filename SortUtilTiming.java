package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import assignment05.SortUtilTesting.IntCompare;

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
		int lowerBound = 1; 
		int upperBound = 10; 
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
			//This loop calls mergesort repeatedly on the items in a temp arrayList
			for(long j = 0; j < timesToLoop; j++){
				ArrayList<Integer> temp = new ArrayList<Integer>(); 
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
		}
	

	//Code to write the values contained in the output data array to a file. 
	try(FileWriter outputWriter = new FileWriter(new File("mergesortTiming.csv"))){
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
				long timesToLoop = 1000;
				int lowerBound = 1; 
				int upperBound = 10; 
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
					
					for(long j = 0; j < timesToLoop; j++){
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
				}
			

			//Code to write the values contained in the output data array to a file. 
			try(FileWriter outputWriter = new FileWriter(new File("quicksortTimingMethod1.csv"))){
				outputWriter.write("Timing of mergesort() on randomly generated arrayList, size (N) where N is number"
						+ " of items contained in the list, time(ns)\n");
				for(int i = 0; i < (upperBound-lowerBound) + 1; i++){
					outputWriter.write("Trial 1," + outputData[0][i] + "," + outputData[1][i] + "\n");
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}

	public static void main(String[] args) {
		SortUtilTiming.thresholdExperiment();
		SortUtilTiming.pivotExperiment();
		

	}

}
