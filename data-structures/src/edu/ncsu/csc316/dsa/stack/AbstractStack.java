package edu.ncsu.csc316.dsa.stack;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 3 assignment page or just given explicitly to use in the
 * Workshop 3 assignment page.
 * 
 * A skeletal implementation of the Stack abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the stack abstract data type.
 * 
 * @author Dr. King
 *
 * @param <E> the type of elements stored in the stack
 */
public abstract class AbstractStack<E> implements Stack<E> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}