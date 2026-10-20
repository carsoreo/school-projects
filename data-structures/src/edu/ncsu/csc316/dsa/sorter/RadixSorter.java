package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data.
 * The code for this has been outlined in pseudo code given in the
 * Workshop 1 assignment page.
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	@Override
	public void sort(E[] input) {
		int k = 0;
		int n = input.length;
		for  (int i = 0; i <= n - 1; i++) {
			if (k < input[i].getId()) {
				k = input[i].getId();
			}
		}
		int x = (int) Math.ceil(Math.log(k + 1) / Math.log(10));
		
		int p = 1;
		
		for (int j = 1; j <= x; j++) {
			int tempB[] = new int[10]; 
			for (int i = 0; i <= n - 1; i++) {
				tempB[(input[i].getId() / p) % 10] = tempB[(input[i].getId() / p) % 10] + 1;
			}
			
			for (int i = 1; i <= 9; i++) {
				tempB[i] = tempB[i - 1] + tempB[i];
			}
			
			@SuppressWarnings("unchecked")
			E[] tempF = (E[])(new Identifiable[n]);
			
			for (int i = n - 1; i >= 0; i--) {
				tempF[ tempB[(input[i].getId() / p) % 10] - 1] = input[i];
				tempB[ (input[i].getId() / p) % 10] = tempB[(input[i].getId() / p) % 10] - 1;
			}
			
			for (int i = 0; i <= n - 1; i++) {
				input[i] = tempF[i];
			}
			
			p = p * 10;
		}
		
	
		
	}
	
}
