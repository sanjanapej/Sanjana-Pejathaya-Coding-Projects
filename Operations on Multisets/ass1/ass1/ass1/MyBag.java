package ass1;

import java.util.Arrays;
import java.util.Comparator;

public class MyBag<E extends Comparable<E>> implements Bag<E> {
	//storage - array of elements of type E
	protected E [] elements;
	int capacity;
	int size;
	
	// Constructor: allocated storage, sets capacity and size
	@SuppressWarnings("unchecked")
	public MyBag(int capacity) {		
		this.elements = (E[]) new Comparable[capacity];
		this.capacity = capacity;
		this.size = 0;
	}
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {		
		return size==0;
	}

	@Override
	public boolean add(E element) {
		if (size == capacity) { // cannot add: array is out of capacity
			return false;
		}
		elements[size++] = element;
		return true;
	}
	
	private void remove (int index) {
		// this is only called if the size > 0 and we found the object at position index
		// shift first
		for(int i=index+1; i<size; i++ ) {
			elements[i-1]=elements[i];
		}
		// unnecessary but just in case
		elements[size-1] = null;
		size --;
	}
	
	@Override
	public boolean remove(E element) {
		// first find an object equal to element
		for (int i=0; i<size; i++) {
			if (elements[i].equals(element)) {
				remove (i);  // call private method which will remove the element at position i
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		for (int i=0; i< size; i++)
			elements[i] = null;
		size =0;			
	}

	@Override
	public boolean contains(E element) {
		for (int i=0; i<size; i++) {
			if (elements[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getFrequencyOf(E element) {
		int count = 0;
		for (int i=0; i<size; i++) {
			if (elements[i].equals(element)) {
				count ++;
			}
		}
		return count;
	}	
	
	// sorts using a default compareTo
	public void sort () {
		//sorts sub-array from 0 to size-1 (inclusive)		
		Arrays.sort(elements, 0, size);
	}
	
	// sorts using a specific comparator
	public void  sort (Comparator<E> comparator) {
		//sorts sub-array from 0 to size-1 (inclusive)	using custom Comparator	
		Arrays.sort(elements, 0, size, comparator);
	}
	
	public int cardinality() {
		int result = 0;
		if (size==0) return 0;
		this.sort();
		
		int i=1;		
		E previous = elements[0];
		result++;   
		while(i<size) {
			E current = elements[i];
			previous = elements[i-1];
			if (!current.equals(previous))
				result++;
			i++;			
		}		
		
		return result;
	}
	
	@Override
	public String toString() {
		// use StringBuilder -- not an immutable String
		StringBuilder sb = new StringBuilder("[");
		for (int i=0; i<size-1; i++)
			sb.append(elements[i] +", ");
		if (size > 0)
			sb.append(elements[size - 1]);
		sb.append("]");
		return sb.toString();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		MyBag<E> bag = (MyBag<E>)o;
		this.sort();
		bag.sort();
		if (this.size!=bag.size) return false;
		
		for (int i=0; i<this.size; i++) {
			if (!this.elements[i].equals(bag.elements[i]))
				return false;
		}
		return true;
	}
	
	public MyBag<E> union(MyBag<E> Y) {
	    // To ensure the comparison occurs in order, I will sort the bag
	    this.sort();
	    Y.sort();

	    MyBag<E> result = new MyBag<>(this.capacity + Y.capacity); 
	   
	    // Pointer for this bag
	    int i = 0; 
	    // Pointer for Y bag
	    int j = 0; 

	    // Iterate over both bags
	    while (i < this.size && j < Y.size) {
	        E elementThis = this.elements[i];
	        E elementY = Y.elements[j];

	        int compareResult = elementThis.compareTo(elementY);

	        if (compareResult == 0) {  // Compares elements to one another
	            // Add the element the maximum number of times
	            int countInThis = getFrequency1(elementThis, i);
	            int countInY = Y.getFrequency1(elementY, j);
	            int maxCount = Math.max(countInThis, countInY);

	            for (int k = 0; k < maxCount; k++) {
	                result.add(elementThis);
	            }

	            // Move both pointers forward 
	            i += countInThis;
	            j += countInY;

	        } else if (compareResult < 0) {  // elementThis < elementY
	            // Move the pointer forward
	            int countInThis = getFrequency1(elementThis, i);
	            for (int k = 0; k < countInThis; k++) {
	                result.add(elementThis);
	            }
	            i += countInThis;

	        } else {  // elementThis > elementY
	            int countInY = Y.getFrequency1(elementY, j);
	            for (int k = 0; k < countInY; k++) {
	                result.add(elementY);
	            }
	            j += countInY;
	        }
	    }

	    // Add remaining elements from this bag
	    while (i < this.size) {
	        E elementThis = this.elements[i];
	        int countInThis = getFrequency1(elementThis, i);
	        for (int k = 0; k < countInThis; k++) {
	            result.add(elementThis);
	        }
	        i += countInThis;
	    }

	    // Add remaining elements from Y bag
	    while (j < Y.size) {
	        E elementY = Y.elements[j];
	        int countInY = Y.getFrequency1(elementY, j);
	        for (int k = 0; k < countInY; k++) {
	            result.add(elementY);
	        }
	        j += countInY;
	    }

	    return result;
	}

	// Helper method for getFrequency1
	private int getFrequency1(E element, int startIndex) {
	    int count = 0;
	    while (startIndex + count < this.size && this.elements[startIndex + count].equals(element)) {
	        count++;
	    }
	    return count;
	}


	
	public MyBag<E> intersection(MyBag<E> Y) {
	    // Sort both bags for comparison
	    this.sort();
	    Y.sort();

	    MyBag<E> result = new MyBag<>(Math.min(this.capacity, Y.capacity)); // Create result with min capacity

	    int i = 0; 
	    int j = 0; 

	    // Iterate over both bags
	    while (i < this.size && j < Y.size) {
	        E elementThis = this.elements[i];
	        E elementY = Y.elements[j];

	        int compareResult = elementThis.compareTo(elementY);

	        if (compareResult == 0) {  // Equal elements
	            // Both bags contain this element, add the minimum frequency to the result
	            int countInThis = getFrequency(elementThis, i);
	            int countInY = Y.getFrequency(elementY, j);
	            int minCount = Math.min(countInThis, countInY);

	            // Add the common element minCount times to the result
	            for (int k = 0; k < minCount; k++) {
	                result.add(elementThis);
	            }

	            // Pointers move forward by their respective counts
	            i += countInThis;
	            j += countInY;

	        } else if (compareResult < 0) {  // elementThis < elementY
	            // Move the pointer in this bag forward since this element is not in Y
	            int countInThis = getFrequency(elementThis, i);
	            i += countInThis;

	        } else {  // elementThis > elementY
	            // Move the pointer in Y forward since this element is not in this bag
	            int countInY = Y.getFrequency(elementY, j);
	            j += countInY;
	        }
	    }

	    return result;
	}

	// Helper method 
	private int getFrequency(E element, int startIndex) {
	    int count = 0;
	    while (startIndex + count < this.size && this.elements[startIndex + count].equals(element)) {
	        count++;
	    }
	    return count;
	}

	
	public MyBag<E> difference(MyBag<E> Y) {
	    // Sort both bags to ensure ordered comparison
	    this.sort();
	    Y.sort();

	    MyBag<E> result = new MyBag<>(this.capacity); // Resulting bag with the same capacity as this

	    int i = 0; // Pointer for this bag
	    int j = 0; // Pointer for Y bag

	    // Use a while loop to iterate over both bags
	    while (i < this.size && j < Y.size) {
	        E elementThis = this.elements[i];
	        E elementY = Y.elements[j];

	        int compareResult = elementThis.compareTo(elementY);

	        if (compareResult == 0) {  // Elements are equal
	            // Element exists in both bags, remove all occurrences
	            int countInThis = getFrequency1(elementThis, i);
	            int countInY = Y.getFrequency1(elementY, j);

	            // If there are more occurrences of the element in `this`, only add the difference
	            if (countInThis > countInY) {
	                for (int k = 0; k < countInThis - countInY; k++) {
	                    result.add(elementThis);
	                }
	            }

	            // Move both pointers forward by their respective counts
	            i += countInThis;
	            j += countInY;

	        } else if (compareResult < 0) {  // elementThis < elementY
	            // Element exists only in `this`, add all occurrences to the result
	            int countInThis = getFrequency1(elementThis, i);
	            for (int k = 0; k < countInThis; k++) {
	                result.add(elementThis);
	            }
	            i += countInThis;

	        } else {  // elementThis > elementY
	            // Element exists only in Y, so move Y's pointer forward
	            int countInY = Y.getFrequency1(elementY, j);
	            j += countInY;
	        }
	    }

	    // Add remaining elements from `this` that weren't in Y
	    while (i < this.size) {
	        E elementThis = this.elements[i];
	        int countInThis = getFrequency1(elementThis, i);
	        for (int k = 0; k < countInThis; k++) {
	            result.add(elementThis);
	        }
	        i += countInThis;
	    }

	    return result;
	}

	// Helper method to get frequency of element starting from a given index
	private int getFrequency11(E element, int startIndex) {
	    int count = 0;
	    while (startIndex + count < this.size && this.elements[startIndex + count].equals(element)) {
	        count++;
	    }
	    return count;
	}


	
	public boolean isSubBagOf(MyBag<E> Y){		
		 for (int i = 0; i < this.size; i++) {
		        E element = this.elements[i];
		        int countInThis = this.getFrequencyOf(element);
		        int countInY = Y.getFrequencyOf(element);
		        
		        // If the current bag has more of an element than Y, it's not a sub-bag
		        if (countInThis > countInY) {
		            return false;
		        }
		    }
		    return true; // It's a sub-bag if all elements' counts are satisfied
	}
	
	public boolean isSuperBagOf(MyBag<E> Y){		
		return Y.isSubBagOf(this); // A bag Y is a sub-bag of this bag if and only if this bag is a super-bag of Y
	}
	
	public MyBag<E> sum(MyBag<E> Y){			
		 MyBag<E> result = new MyBag<>(this.capacity + Y.capacity); // new bag with combined capacity

		    // Add elements from this bag
		    for (int i = 0; i < this.size; i++) {
		        E element = this.elements[i];

		            result.add(element);
		        
		    }

		    // Add elements from Y
		    for (int i = 0; i < Y.size; i++) {
		        E element = Y.elements[i];
		        
		            result.add(element);
		        
		    }

		    return result;
	}
	
	public static void main(String [] args) {
		// fill the bag with products
		MyBag<Product> prodBag = new MyBag<Product>(4);
		System.out.println("Empty Bag of products: "+ prodBag);
		
		prodBag.add(new Product("Coke", 1.0));
		prodBag.add(new Product("Milk", 2.5));
		prodBag.add(new Product("Bread", 5.2));
		prodBag.add(new Product("Bread", 4.3));
		System.out.println("\nBag with 4 products: "+prodBag);
			
		prodBag.remove(new Product("Milk", 2.5));
		System.out.println("\nBag with 3 products: "+prodBag);
		
		prodBag.sort();
		System.out.println("\nProducts after sorting:");
		System.out.println(prodBag);
		
		
		// now fill the bag with books
		MyBag<Book> bookBag = new MyBag<Book>(4);
			
		String [] authors1 = {"King", "Queen", "Prince"};
		String [] authors2 = {"King", "Queen"};
			
		bookBag.add(new Book ("111", authors1, "Castle", "Morgan press", 0.5));
		bookBag.add(new Book ("222", authors2, "King's speech",
										"Morgan press", 0.8));
		bookBag.add(new Book ("111", authors1, "Castle", "Morgan press", 0.5));
		System.out.println("\n\nBag with 3 books: ");
		System.out.println(bookBag);
		
		int count = bookBag.getFrequencyOf(new Book ("111", authors1, "Castle",
										"Morgan press", 0.5));
		System.out.println("\nTotal books with isbn 111 :" + count); // expected 2
		
		
		bookBag.sort();
		System.out.println("\nBooks after sorting by title:");
		System.out.println(bookBag);		
		
	}

	
}



	
	
	
	


	