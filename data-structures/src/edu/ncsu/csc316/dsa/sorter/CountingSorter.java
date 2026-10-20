package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data.
 * The code in this class was created using the pseudo code outlined in
 * the Workshop 1 assignment page.
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	@Override
	public void sort(E[] input) {
		int min = input[0].getId();
		int max = input[0].getId();
		
		for (int i = 0; i < input.length; i++) {
			if (input[i].getId() < min) {
				min = input[i].getId();
			}
			if (input[i].getId() > max) {
				max = input[i].getId();
			}
		}
		int k = max - min + 1;
		
		int[] tempB = new int[k];
		
		for (int i = 0; i < input.length; i++) {
			tempB[input[i].getId() - min] = tempB[input[i].getId() - min] + 1;
		}
		
		for (int i = 1; i < k; i++) {
			tempB[i] = tempB[i - 1] + tempB[i];
		}
		
		@SuppressWarnings("unchecked")
		E[] tempF = (E[])(new Identifiable[input.length]);

		
		for(int i = input.length - 1; i >= 0; i--) {
			tempF[ tempB[ input[i].getId() - min] - 1] = input[i];
			tempB[ input[i].getId() - min] = tempB[ input[i].getId() - min ] - 1;
		}
		
		for (int i = 0; i < input.length; i++) {
			input[i] = tempF[i];
		}
		
	}
	
}
