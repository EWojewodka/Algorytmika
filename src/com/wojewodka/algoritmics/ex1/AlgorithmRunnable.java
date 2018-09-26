/**
 * 
 */
package com.wojewodka.algoritmics.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.wojewodka.algoritmics.timer.AlgorithmTimer;

/**
 * @author Emil Wojewódka
 *
 * @since 26 wrz 2018
 */
public class AlgorithmRunnable {

	public static void main(String[] args) throws Exception {
		// Wrap bussiness logic - after execution print time in console.
		OddEvenArrayStore result = new AlgorithmTimer<OddEvenArrayStore>() {

			@Override
			protected OddEvenArrayStore execute() throws Exception {
				// read data from files
				Integer[] stackA = parseFileToIntArray("sortowanie_1.txt");
				Integer[] stackB = parseFileToIntArray("sortowanie_2.txt");
				Integer[] stackC = parseFileToIntArray("sortowanie_3.txt");
				// Merge arrays to one, complex data store.
				int[] merged = mergeIntArrays(stackA.length + stackB.length + stackC.length, stackA, stackB, stackC);
				return sort(merged);

			}
		}.runAlgorithm();
		System.out.println("Liczby parzyste");
		for (int i : result.even) {
			System.out.println(i);
		}
		System.out.println("---------------------");
		System.out.println("Liczby nieparzyste:");
		for (int i : result.odd) {
			System.out.println(i);
		}
	}

	private static OddEvenArrayStore sort(int[] array) {
		// Create instances of new array. I think it's more efficienty than using array
		// list, because if data length is bigger list will copy data to new array.
		Integer[] odd = new Integer[array.length];
		Integer[] even = new Integer[array.length];
		// Our index to real position of a place to insert
		int oddPositionToInsert = 0;
		int evenPositionToInsert = 0;

		// Itera every element from merged arrays
		for (int i = 0; i < array.length; i++) {
			int elementToInsert = array[i];

			// If integer is evenly
			if (elementToInsert % 2 == 0) {
				insertionSort(even, evenPositionToInsert, elementToInsert, false);
				evenPositionToInsert++;
			} else { // If integer is oddly
				insertionSort(odd, oddPositionToInsert, elementToInsert, true);
				oddPositionToInsert++;
			}
		}

		// Remove nulls from Integer array. There's many null, because we declare bigger
		// array than we need.
		return new OddEvenArrayStore(removeNullsFromArray(odd), removeNullsFromArray(even));
	}

	private static class OddEvenArrayStore {
		final Integer[] odd;
		final Integer[] even;

		public OddEvenArrayStore(Integer[] odd, Integer[] even) {
			this.odd = odd;
			this.even = even;
		}

	}

	private static Integer[] removeNullsFromArray(Integer[] array) {
		List<Integer> result = new ArrayList<>();
		// iterate every array element
		for (Integer obj : array) {
			// if its null - skip
			if (obj == null)
				continue;
			result.add(obj);
		}
		// Convert list to array
		return result.toArray(new Integer[result.size()]);
	}

	private static void insertionSort(Integer[] array, int positionToInsert, int elementToInsert,
			boolean isDescending) {
		// If array is empty, put element on 0 index and return
		if (positionToInsert == 0) {
			array[0] = elementToInsert;
			return;
		}
		int j = positionToInsert;

		// check condition for insertion sort. Note that we use isDescending variable.
		while (j > 0 && (isDescending ? array[j - 1] < elementToInsert : array[j - 1] > elementToInsert)) {
			array[j] = array[j - 1];
			j--;
		}
		array[j] = elementToInsert;
	}

	@SuppressWarnings("unused")
	private static int[] mergeIntArrays(Integer[]... arrays) {
		if (arrays == null || arrays.length == 0)
			return new int[0];
		int newSize = 0;
		for (Integer[] array : arrays) {
			newSize += array.length;
		}
		return mergeIntArrays(newSize, arrays);
	}

	private static int[] mergeIntArrays(int resultSize, Integer[]... arrays) {
		int[] result = new int[resultSize];
		int resultArrayIndex = 0;
		for (int i = 0; i < arrays.length; i++) {
			Integer[] array = arrays[i];
			int subArrayLen = array.length;
			for (int j = 0; j < subArrayLen; j++) {
				Integer integerToInsert = array[j];
				if (resultArrayIndex == 0) {
					resultArrayIndex++;
					result[0] = integerToInsert;
					continue;
				}
				result[resultArrayIndex++] = integerToInsert;
			}
		}
		return result;
	}

	private static Integer[] parseFileToIntArray(String fileName) throws FileNotFoundException {
		List<Integer> list = new ArrayList<>();
		try (Scanner sc = new Scanner(new File("resources/" + fileName))) {
			while (sc.hasNextInt())
				list.add(sc.nextInt());
		}
		return list.toArray(new Integer[list.size()]);
	}

}
