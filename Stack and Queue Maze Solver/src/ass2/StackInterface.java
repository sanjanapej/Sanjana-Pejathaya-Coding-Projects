package ass2;

import java.util.NoSuchElementException;

/**
 * An interface that describes a stack abstract data type
 *
 * @author Benjamin Kuperman (Spring 2005, Spring 2012, Spring 2014)
 */

public interface StackInterface<T> {
    /**
     * Add an item onto the stack
     * 
     * @param item the data item to add (of type T)
     */
    void push(T item);

    /**
     * Remove the top item from the stack
     * 
     * @return the top item in the stack
     * @throws NoSuchElementException if the stack is empty
     */
    T pop() throws NoSuchElementException;

    /**
     * Display the top item from the stack without removing it
     * 
     * @return the top item in the stack
     * @throws NoSuchElementException if the stack is empty
     */
    T peek() throws NoSuchElementException;

    

    /**
     * Determine if the stack is empty
     * 
     * @return true if the size is 0, false otherwise
     */
    boolean isEmpty();

    

}
