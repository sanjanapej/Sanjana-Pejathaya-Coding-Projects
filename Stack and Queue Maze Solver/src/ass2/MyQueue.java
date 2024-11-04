package ass2;

import java.util.NoSuchElementException;

public class MyQueue<T> implements QueueInterface<T> {
    private MiniList<T> list = new MiniList<>();

    // adds an element to the end of the queue
    @Override
    public void enqueue(T data) {
        list.addLast(data);
    }

    // Removes and returns the element at the front of the queue
    // Throwing NoSuchElementException
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty"); 
        }
        return list.removeFirst();
    }

    // Without removing the front element, it gets returned 
    @Override
    public T front() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty"); // Throwing NoSuchElementException
        }
        return list.getFirst();
    }

    // Checks if the queue is empty
 // Checks if the queue is empty
    @Override
    public boolean isEmpty() {
        return list.isEmpty(); // Use MiniList's isEmpty method
    }

}

