package ass2;

import java.util.NoSuchElementException;

public class MiniList <T>{
	private Node<T> head;
	private Node<T> tail;
	
	 // Inner class for nodesT
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    
}
    

//adds a new node to the front of the list 
public void addFirst(T data) {
	Node<T> newNode = new Node<>(data);
    newNode.next = head;
    head = newNode;
    if (tail == null) { // empty list 
        tail = newNode;
    }
}

//returns the data in the first node of the list without modifying the list
public T getFirst() {
    if (head == null) {
        throw new NoSuchElementException("List is empty");
    }
    return head.data;
}

//removes first node of the list and returns data from this node
public T removeFirst() {
    if (head == null) {
        throw new NoSuchElementException("List is empty");
    }
    T data = head.data;
    head = head.next; // move the head to the next node
    if (head == null) { // setting tail to null if there is no head
        tail = null;
    }
    return data;
}


//adds a new node with new data to the end of the list
public void addLast(T data) {
    Node<T> newNode = new Node<>(data);
    if (tail == null) { // List is empty
        head = tail = newNode;
    } else {
        tail.next = newNode;
        tail = newNode;
    }
}

public boolean isEmpty() {
	return head == null;
}
}

