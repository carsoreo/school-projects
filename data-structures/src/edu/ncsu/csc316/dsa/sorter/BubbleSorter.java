package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This code is derived from pseudo code outlined in the Workshop 1 assignment page.
 * Uses the Bubble Sort pattern to sort given elements into ascending order.
 * @author clorejol
 *
 * @param <E> Element to be read in and sorted.
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor for BubbleSorter if certain order is defined.
	 * @param comparator to be passed to Abstract class.
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Constructor if no parameter for the order is defined.
	 */
	public BubbleSorter() {
		super(null);
	}

	@Override
	public void sort(E[] input) {
		int n = input.length;
		boolean r = true;
		
		while(r) {
			r = false;
			for (int i = 1; i <= n - 1; i++) {
				if (compare(input[i], input[i - 1]) < 0) {
					E x = input[i - 1];
					input[i - 1] = input[i];
					input[i] = x;
					r = true;
				}
			}
		}
		
	}

}
