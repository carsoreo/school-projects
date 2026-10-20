package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * The code for this has been outlined in pseudo code given in the
 * Workshop 1 assignment page.
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
	/**
	 * Constructor for SelectionSorter which reads a comparator object to be
	 * passed to the Abstract class.
	 * 
	 * @param comparator Object containing order to be passed to super class
	 */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    /**
     * Default construct for SelectionSorter when no comparator is defined.
     */
    public SelectionSorter() {
        super(null);
    }
	
	/**
	 * This method is derived from pseudo code taken from the Workshop 1
	 * assignment page.
	 */
	@Override
	public void sort(E[] input) {
		int n = input.length;
		for (int i = 0; i <= n - 1; i++) {
			int min = i;
			for (int j = i + 1; j <= n - 1; j++) {
				if (compare(input[j], input[min]) < 0) {
					min = j;
				}
			}
			if (i != min) {
				E x = input[i];
				input[i] = input[min];
				input[min] = x;
			}
		}
		
	}
}
