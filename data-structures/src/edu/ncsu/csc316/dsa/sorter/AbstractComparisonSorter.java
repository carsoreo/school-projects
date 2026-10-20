package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * The code in this class was created using the pseudo code outlined in
 * the Workshop 1 assignment page.
 * Abstract class which contains methods to set a comparator to outline a
 * certain order or use a default natural order.
 * 
 * @author clorejol
 * @param <E> the generic type of data to sort
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	
	/** Object which holds a comparator that outlines natural order */
    private Comparator<E> comparator;
    
    /**
     * Constructor for AbstractComparisonSorter which sets the comparator
     * to use for sorting order.
     * @param comparator Object containing sorting order to be set.
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
     * Reads in two sets of data and calls the comparator to
     * compare the elements. Returns the corresponding integer
     * returned from the comparator.
     * 
     * @param data1 E element to be compared.
     * @param data2 E element to be compared against.
     * @return int returned from compare method from comparator.
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}
