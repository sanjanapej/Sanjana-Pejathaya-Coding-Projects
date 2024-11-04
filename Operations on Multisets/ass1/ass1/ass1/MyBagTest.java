package ass1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;



class MyBagTest {
	public static final boolean DEBUG = true;
	
	public final String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	         + "0123456789"
	         + "abcdefghijklmnopqrstuvxyz"; 
	public final String digitString = "12345678910";
	
	@Test
	void testGetSize() {
		MyBag<Product> mybag = new MyBag<>(1);

		assertEquals(0, mybag.getSize());
		mybag.add(new Product("Milk", 2.15));
		assertEquals(1, mybag.getSize());
		mybag.add(new Product("Brittania", 2.15));
		assertEquals(1, mybag.getSize());
	}

	@Test
	void testIsEmpty() {
		MyBag<Product> mybag = new MyBag<>(10);
		
		// in assertEquals first is what we expect, second what the program produced
		assertEquals( 0, mybag.getSize());
		mybag.add(new Product("Milk", 2.15));
		assertFalse(mybag.isEmpty());
	}

	@Test
	void testAdd() {
		MyBag<Product> mybag = new MyBag<>(3);
		int initalSizeOfProducts = mybag.getSize();
		mybag.add(new Product("Milk", 2.15));
		assertEquals(initalSizeOfProducts + 1, mybag.getSize());
		Product p = new Product("Milk", 3.15);
		mybag.add(p);
		int pFrequecyBefore = mybag.getFrequencyOf(p);
		mybag.add(p);
		int pFrequencyAfter = mybag.getFrequencyOf(p);
		assertEquals(pFrequecyBefore+1, pFrequencyAfter);		
		assertFalse(mybag.add(new Product("Brittania", 5.34)));
	}

	@Test
	void testRemove() {
		MyBag<Product> mybag = new MyBag<>(2);
		assertFalse(mybag.remove(new Product("Brittania", 5.34)));
		Product p1 = new Product("Milk", 3.15);
		mybag.add(p1);
		Product p2 = new Product("Milk", 3.15);
		mybag.add(p2);
		int productSizeBefore = mybag.getSize();
		int pFrequecyBefore = mybag.getFrequencyOf(p1);
		mybag.remove(p1);
		int pFrequencyAfter = mybag.getFrequencyOf(p1);
		int productSizeAfter = mybag.getSize();
		assertEquals(pFrequecyBefore-1, pFrequencyAfter);
		assertEquals(productSizeBefore-1, productSizeAfter);
	}

	@Test
	void testClear() {
		MyBag<Product> mybag = new MyBag<>(2);
		mybag.add(new Product("Milk", 2.15));
		mybag.add(new Product("Bread", 4.15));
		mybag.clear();
		assertTrue(mybag.getSize() == 0);
	}

	@Test
	void testContains() {
		MyBag<Product> mybag = new MyBag<>(10);
		mybag.add(new Product("Milk", 2.15));
		mybag.add(new Product("Bread", 4.15));
		assertTrue(mybag.contains(new Product("Milk", 2.15)));
		assertFalse(mybag.contains(new Product("Chocolate Milk", 1.15)));
	}

	@Test
	void testGetFrequencyOf() {
		MyBag<Product> mybag = new MyBag<>(10);
		assertTrue(mybag.getFrequencyOf(new Product("Milk", 2.15)) == 0);

		mybag.add(new Product("Milk", 2.15));
		mybag.add(new Product("Bread", 4.15));
		mybag.add(new Product("Milk", 2.15));

		assertTrue(mybag.getFrequencyOf(new Product("Milk", 2.15)) == 2);
		assertTrue(mybag.getFrequencyOf(new Product("Bread", 4.15)) == 1);
	}	
	
	@Test
	void testSort() {		
		MyBag<Product> mybag = new MyBag<>(100);
		MyBag<Product> sameBag = new MyBag<>(100);
		int i=0; // we start at position 0
		for(; i<mybag.capacity/2; i++) {
			String randName = getRandString(alphaNumericString, 10);
			int randPrice = (int)(100 * Math.random());
			mybag.add(new Product(randName,randPrice));
			sameBag.add(new Product(randName,randPrice));
		}
		
		// the second half is filled with equal names
		for(; i<mybag.capacity; i++) {
			int randPrice = (int)(100 * Math.random());
			mybag.add(new Product("Same name",randPrice));
			sameBag.add(new Product("Same name",randPrice));
		}
		
		mybag.sort();
		sameBag.sort(new Comparator<Product>() {        
	        public int compare(Product p1, Product p2) {
	        	int byName = p1.getName().compareTo(p2.getName());
	        	if (byName == 0) {
	        		if (p1.getPrice() > p2.getPrice()) return 1;
	        		if (p1.getPrice() < p2.getPrice()) return -1;
	        		return 0;
	        	}    		
	            return byName;
	        }
	    });       
 
		assertEquals(mybag, sameBag);
		
		MyBag<Book> bookBag = new MyBag<>(100);
		MyBag<Book> sameBookBag = new MyBag<>(100);
		i=0; // we start at position 0
		for(; i<bookBag.capacity/2; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
			sameBookBag.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		
		// the second half is filled with equal isbn and title and authors
		for(; i<bookBag.capacity; i++) {
			String title = "Same title";			
			String [] authors = {"Same author"};
			String randPublisher = getRandString(alphaNumericString, 10);
			String ISBN = "111222333";
			int randRating = (int)(100 * Math.random());
			bookBag.add(new Book(ISBN, authors, title, randPublisher, (double) randRating));
			sameBookBag.add(new Book(ISBN, authors, title, randPublisher, (double) randRating));
		}
		
		bookBag.sort();
		sameBookBag.sort(new Comparator<Book>() {        
	        public int compare(Book b1, Book b2) {
	        	int res = b1.isbn.compareTo(b2.isbn);
	    		if (res !=0) return res;
	    		
	    		res = b1.title.compareTo(b2.title);
	    		if (res !=0) return res;
	    		
	    		res = b1.authorString().compareTo(b2.authorString());
	    		if (res !=0) return res;
	    		
	    		res = b1.publisher.compareTo(b2.publisher);
	    		if (res !=0) return res;
	    		
	    		if (b1.rating > b2.rating) return 1;
	    		if (b1.rating < b2.rating) return -1;		
	    		return 0;
	        }
	    });       
 
		assertEquals(bookBag, sameBookBag);			
	}
	
	//=================================================
	//Utility methods
	private static String getRandString(String alphabet, int len) {  
	  // create StringBuffer size of AlphaNumericString 
	  StringBuilder sb = new StringBuilder(len); 
	 
	  for (int i = 0; i < len; i++) { 	 
		  // generate a random number between 
		  // 0 to AlphaNumericString variable length 
		  int index = (int)(alphabet.length() * Math.random()); 
		  // choose a Character random from the alphabet
		  // add Character one by one in end of sb 
		  sb.append(alphabet.charAt(index)); 
	  } 
	 
	  return sb.toString(); 
	} 
}
