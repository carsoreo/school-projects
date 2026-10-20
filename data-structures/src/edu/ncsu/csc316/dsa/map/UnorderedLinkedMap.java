package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 5 assignment page or just given explicitly to use in the
 * Workshop 5 assignment page.
 * 
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author clorejol
 * 
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {
	
	/** Positional List value which holds Map entries, used to hold values for Map */
    private PositionalList<Entry<K, V>> list;
    
    /**
     * Constructor for UnorderedLinkMap. Builds a new Positional Linked List
     * which holds Map entries.
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    private Position<Entry<K, V>> lookUp(K key) {
    	if (list.positions() == null || key == null) {
    		return null;
    	}
        for (Position<Entry<K, V>> value : list.positions()) {
        	if (value == null || value.getElement() == null || value.getElement().getKey() == null) {
        		return null;
        	}
        	if (key.equals(value.getElement().getKey())) {
        		return value;
        	}
        }
        return null;
    }

    @Override
    public V get(K key) {
    	if (key == null) {
    		return null;
    	}
        Position<Entry<K, V>> p = lookUp(key);
        if (p == null) {
        	return null;
        }
        moveToFront(p);
        return p.getElement().getValue();
    }
    
    private void moveToFront(Position<Entry<K, V>> position) {
    	if (position == null) {
    		return;
    	}
        list.addFirst(list.remove(position));
    }

    @Override
    public V put(K key, V value) {
    	if (key == null || value == null) {
    		return null;
    	}
        Position<Entry<K, V>> p = lookUp(key);
        if (p == null) {
        	MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
        	list.addFirst(entry);
        	return null;
        }
        else {
        	MapEntry<K, V> entry = new MapEntry<K, V>(key, value);
        	V tempVal = list.remove(p).getValue();
        	list.addFirst(entry).getElement().getValue();
        	return tempVal;
        }
    }
    
    @Override
    public V remove(K key) {
    	if (key == null) {
    		return null;
    	}
        Position<Entry<K, V>> p = lookUp(key);
        if (p == null) {
        	return null;
        }
        return list.remove(p).getValue();
       
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    
}