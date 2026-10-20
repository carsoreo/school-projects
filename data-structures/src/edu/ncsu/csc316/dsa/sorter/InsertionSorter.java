package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * All of the code has either been derived from pseudo code or given from the
 * Workshop 1 assignment website.
 * 
 * @author Dr. King
 * @author clorejol
 * 
 * @param <E> Element to be read in and sorted.
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/**
	 * Constructor used for InsertionSorter when defining a certain order.
	 * @param comparator Comparator object outlining order.
	 */
	public InsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }
	
	/**
	 * Default constructor to be used if no order is defined.
	 */
	public InsertionSorter() {
		super(null);
	}

	@Override
	public void sort(E[] input) {
		
		for(int i = 1; i <= input.length - 1; i++) {
			
			E x = input[i];
			int j = i - 1;
			
			while(j >= 0 && compare(input[j], x) > 0) {
				input[j + 1] = input[j];
				j = j - 1;
			}
			input[j + 1] = x;
		}
		
	}
}
