package ass2;

import java.util.NoSuchElementException;

/**
 * An parameterized interface for the Queue Abstract Data Type
 *
 * @author Benjamin Kuperman (Spring 2005, Spring 2012, Spring 2014)
 */

public interface QueueInterface<T> {
    /**
     * Add an item to the queue
     * 
     * @param item the data item to add (of type T)
     */
    void enqueue(T item);

    /**
     * Remove the front item from the queue
     * 
     * @return the front item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T dequeue() throws NoSuchElementException;

    /**
     * Return the front item in the queue without removing it
     * 
     * @return the front item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T front() throws NoSuchElementException;

   
    /**
     * Determine if the queue is empty
     * 
     * @return true if the size is 0, false otherwise
     */
    boolean isEmpty();
}
