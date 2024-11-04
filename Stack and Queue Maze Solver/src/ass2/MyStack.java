package ass2;

import java.util.NoSuchElementException;


public class MyStack<T> implements StackInterface<T> {
    private MiniList<T> list = new MiniList<>();

    // pushes an element onto the stack
    @Override
    public void push(T data) {
        list.addFirst(data);
    }

    // pops an element from the stack
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty"); // Change exception type to NoSuchElementException
        }
        return list.removeFirst();
    }

    // Without removing the top element, it gets returned
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty"); // Change exception type to NoSuchElementException
        }
        return list.getFirst();
    }

    // Checks if the stack is empty
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
