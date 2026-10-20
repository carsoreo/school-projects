package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 8 assignment page or just given explicitly to use in the
 * Workshop 8 assignment page.
 * 
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author clorejol
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {
	
	/** Table which holds all entries */
    private TableEntry<K, V>[] table;
    
    /** Current size of the hash map */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isDeleted()) {
            	collection.add(table[i]);
            }
        }
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }
    
    /**
     * Method which returns true if a given bucket is available. False
     * if the bucket is not.
     * @param index of bucket to be checked.
     * @return boolean value true if the bucket is available, false if not.
     */
    private boolean isAvailable(int index) {
        return table[index] == null || table[index].isDeleted();
    }

    @Override
    public V bucketGet(int hash, K key) {
    	int index = findBucket(hash, key);
    	if (index < 0) {
    		return null;
    	}
    	return table[index].getValue();
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
    	int index = findBucket(hash, key);
    	if (index < 0) {
    		table[-1 * index - 1] = new TableEntry<K, V>(key, value);
    		size++;
    		return null;
    	}
    	else {
    		V oldValue = table[index].getValue();
    		table[index].setValue(value);
    		return oldValue;
    	}
    }
    
    /**
     * Method used to find a given bucket associated with a given key.
     * If found, the index is returned. A negative number will be returned
     * where the index should be.
     * @param index the index into the hash table. 
     * @param key the key of the entry to locate.
     * @return the index of the bucket with the given key or the negative index
     * of where the new entry should be.
     */
    private int findBucket(int index, K key) {
        int avail = -1;
        int j = index;
        do {
        	if (isAvailable(j)) {
        		if (avail == -1) {
        			avail = j;
        		}
        		if (table[j] == null) {
        			return -(avail + 1);
        		}
        	}
        	else if (table[j].getKey().equals(key)) {
        		return j;
        	}
        	j = (j + 1) % table.length;
        } while (j != index);
        return -(avail + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
    	int index = findBucket(hash, key);
    	if (index < 0) {
    		return null;
    	}
    	V oldValue = table[index].getValue();
    	table[index].setDeleted(true);
    	size--;
    	return oldValue;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }
    
    /**
     * This class has methods which have been coded using descriptions given in 
     * the Workshop 8 assignment page or just given explicitly to use in the
     * Workshop 8 assignment page.
     * 
     * @author clorejol
     *
     * @param <K> Key to be held in entry
     * @param <V> Value to be held in entry
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {
    	
    	/** Bool check to see if entry has been deleted */
        private boolean isDeleted;
        
        /**
         * Constructor for the TableEntry.
         * @param key to be held in the entry.
         * @param value to be held in the entry.
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }
        
        /**
         * Method to check if an entry to a deleted entry.
         * @return true if the entry is deleted, false if not.
         */
        public boolean isDeleted() {
            return isDeleted;
        }
        
        /**
         * Changes the status of the entry, making it deleted or
         * not deleted.
         * @param deleted boolean to change deleted status of entry.
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}