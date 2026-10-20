package edu.ncsu.csc316.cleaning.factory;
import java.util.Comparator;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.SearchTableMap;
import edu.ncsu.csc316.dsa.map.SkipListMap;
import edu.ncsu.csc316.dsa.map.UnorderedLinkedMap;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.map.search_tree.RedBlackTreeMap;
import edu.ncsu.csc316.dsa.map.search_tree.SplayTreeMap;
import edu.ncsu.csc316.dsa.sorter.MergeSorter;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * Factory for creating new data structure and algorithm instances
 * 
 * @author Dr. King
 *
 */
public class DSAFactory {

    /**
     * Returns a data structure that implements a map
     * 
     * @param <K>
     *            - the key type
     * @param <V>
     *            - the value type
     * @param comparator to compare keys in list
     * @return a data structure that implements a map
     */
    public static <K extends Comparable<K>, V> Map<K, V> getMap(Comparator<K> comparator) {
        return getLinearProbingHashMap();
    }

    /**
     * Returns a data structure that implements an index-based list
     * 
     * @param <E>
     *            - the element type
     * @return an index-based list
     */
    public static <E> List<E> getIndexedList() {
        return getSinglyLinkedList();
    }

    /**
     * Returns a comparison based sorter that uses a specified Comparator
     * 
     * @param comparator to use when sorting array
     * @param <E>
     *            - the element type
     * @return a comparison based sorter
     */
    public static <E extends Comparable<E>> Sorter<E> getComparisonSorter(Comparator<E> comparator) {
        return getMergeSorter(comparator);
    }

	/**
	 * Returns a skip list map
	 * 
	 * @param <K> type to use for key
	 * @param <V> type to use for value
	 * @param comparator a comparator to use when ordering entries by key
	 * @return a skip list map
	 */
	private static <K extends Comparable<K>, V> SkipListMap<K, V> getSkipListMap(Comparator<K> comparator) {
	    return new SkipListMap<K, V>(comparator);
	}

    /**
     * Returns a singly linked list with front pointer
     * 
     * @param <E> type to use for SinglyLinkedList
     * @return a singly linked list with front pointer
     */
    private static <E> SinglyLinkedList<E> getSinglyLinkedList() {
        return new SinglyLinkedList<E>();
    }
    
    /**
     * Returns a mergesorter
     * 
     * @param comparator a comparator to use when sorting
     * @param <E> type to use mergesort on
     * @return a mergesorter
     */
    private static <E extends Comparable<E>> Sorter<E> getMergeSorter(Comparator<E> comparator) {
        return new MergeSorter<E>(comparator);
    }
    
    /**
     * Returns an unordered linked map
     * 
     * @param <K> key
     * @param <V> value
     * @return an unordered linked map
     */
    private static <K, V> UnorderedLinkedMap<K, V> getUnorderedLinkedMap() {
        return new UnorderedLinkedMap<K, V>();
    }
    
    /**
     * Returns a search table
     * 
     * @param <K> key
     * @param <V> value
     * @param comparator a comparator to use when ordering entries by key
     * @return a search table
     */
    private static <K extends Comparable<K>, V> SearchTableMap<K, V> getSearchTableMap(Comparator<K> comparator) {
        return new SearchTableMap<K, V>(comparator);
    }
    
    /**
     * Returns a splay tree map
     * 
     * @param <K> key
     * @param <V> value
     * @param comparator a comparator to use when ordering entries by key
     * @return a splay tree map
     */
    private static <K extends Comparable<K>, V> SplayTreeMap<K, V> getSplayTreeMap(Comparator<K> comparator) {
        return new SplayTreeMap<K, V>(comparator);
    }
    
    /**
     * Returns a RedBlackTree map
     * 
     * @param <K> key
     * @param <V> value
     * @param comparator a comparator to use when ordering entries by key
     * @return a RedBlackTree map
     */
    private static <K extends Comparable<K>, V> RedBlackTreeMap<K, V> getRedBlackTreeMap(Comparator<K> comparator) {
        return new RedBlackTreeMap<K, V>(comparator);
    }
    
    /**
     * Returns a LinearProbingHashMap map
     * 
     * @param <K> key
     * @param <V> value
     * @return a LinearProbingHashMap
     */
    private static <K extends Comparable<K>, V> LinearProbingHashMap<K, V> getLinearProbingHashMap() {
        return new LinearProbingHashMap<K, V>();
    }



}