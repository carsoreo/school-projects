package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 4 assignment page or just given explicitly to use in the
 * Workshop 4 assignment page.
 * 
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author clorejol
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] input) {
		if (input.length < 2) {
			return;
		}
		int mid = input.length / 2;
		
		@SuppressWarnings("unchecked")
		E[] left = (E[])(new Comparable[mid]);
		for (int i = 0; i <= mid - 1; i++) {
			left[i] = input[i];
		}

		@SuppressWarnings("unchecked")
		E[] right = (E[])(new Comparable[input.length - mid]);
		for (int i = mid; i <= input.length - 1; i++) {
			right[i - mid] = input[i];
		}
		
		sort(left);
		sort(right);
		
		merge(left, right, input);
	}
	
	/**
	 * Merges arrays together given the left portion, right portion
	 * and original array. Is a helper method for sort().
	 * 
	 * @param left array to merge.
	 * @param right array to merge.
	 * @param originalArray where the final array will be stored.
	 */
	private void merge(E[] left, E[] right, E[] originalArray) {
		int leftIndex = 0;
		int rightIndex = 0;
		
		while ((leftIndex + rightIndex) < originalArray.length) {
			if (rightIndex == right.length || leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0) {
				originalArray[leftIndex + rightIndex] = left[leftIndex];
				leftIndex++;
			}
			else {
				originalArray[leftIndex + rightIndex] = right[rightIndex];
				rightIndex++;
			}
		}
	}
}