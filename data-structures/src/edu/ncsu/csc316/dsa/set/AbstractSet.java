package edu.ncsu.csc316.dsa.set;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 10 assignment page or just given explicitly to use in the
 * Workshop 10 assignment page.
 * 
 * A skeletal implementation of the Set abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the set
 * abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the set
 */
public abstract class AbstractSet<E> implements Set<E> {

    @Override
    public void addAll(Set<E> other) {
        for(E element : other) {
            add(element);
        }
    }

    @Override
    public void retainAll(Set<E> other) {
        for(E element : this) {
            if(!other.contains(element)) {
                remove(element);
            }
        }
    }

    @Override
    public void removeAll(Set<E> other) {
        for(E element : other) {
            remove(element);
        }
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
}