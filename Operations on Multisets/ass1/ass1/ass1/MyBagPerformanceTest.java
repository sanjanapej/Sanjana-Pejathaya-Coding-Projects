package ass1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


class MyBagPerformanceTest {
	public static final boolean DEBUG = true;
	
	public final String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	         + "0123456789"
	         + "abcdefghijklmnopqrstuvxyz"; 
	public final String digitString = "12345678910";	
	
	//==============================================
	// Timing  -- performance
	@Timeout(20)
	@Test
	void testToStringEfficiency() {
		MyBag<Product> mybag = new MyBag<>(10000000);
    	for (int i=0; i<10000000; i++) {
    		mybag.add(new Product("Prod "+i, i));
		}
		assertTimeout(Duration.ofSeconds(10), () -> {	
			long start = System.currentTimeMillis();
			mybag.toString();
			long end = System.currentTimeMillis();
			if (DEBUG) System.out.println("\nTIMING: toString runs in: " + (end - start)/1000 + "sec");		   
	    });
	} 
	
	@Timeout(20)
	@Test
	void testCardinalityEfficiency() {
		MyBag<Book> bookBag1 = new MyBag<>(100000);
		MyBag<Book> bookBag2 = new MyBag<>(100000);
		
		for(int i=0; i<100000; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag1.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		for(int i=0; i<100000; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag2.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		assertTimeout(Duration.ofSeconds(10), () -> {	
			long start = System.currentTimeMillis();
			bookBag1.cardinality();
			long end = System.currentTimeMillis();
			if (DEBUG) System.out.println("\nTIMING: Difference runs in: " + (end - start)/1000 + "sec");		   
	    });
	} 
	
	
	@Timeout(20)
	@Test
	void testIntersectionEfficiency() {
		MyBag<Book> bookBag1 = new MyBag<>(100000);
		MyBag<Book> bookBag2 = new MyBag<>(100000);
		
		for(int i=0; i<100000; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag1.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		for(int i=0; i<100000; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag2.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		assertTimeout(Duration.ofSeconds(10), () -> {	
			long start = System.currentTimeMillis();
			bookBag1.intersection(bookBag2);
			long end = System.currentTimeMillis();
			if (DEBUG) System.out.println("\nTIMING: Intersection runs in: " + (end - start)/1000 + "sec");		   
	    });
	} 
	
	@Timeout(20)
	@Test
	void testUnionEfficiency() {
		MyBag<Book> bookBag1 = new MyBag<>(100000);
		MyBag<Book> bookBag2 = new MyBag<>(100000);
		
		for(int i=0; i<100000; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag1.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		for(int i=0; i<100000; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag2.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		assertTimeout(Duration.ofSeconds(10), () -> {	
			long start = System.currentTimeMillis();
			bookBag1.union(bookBag2);
			long end = System.currentTimeMillis();
			if (DEBUG) System.out.println("\nTIMING: Union runs in: " + (end - start)/1000 + "sec");		   
	    });
	} 

	@Timeout(20)
	@Test
	void testDifferenceEfficiency() {
		MyBag<Book> bookBag1 = new MyBag<>(100000);
		MyBag<Book> bookBag2 = new MyBag<>(100000);
		
		for(int i=0; i<100000; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag1.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		for(int i=0; i<100000; i++) {
			String randTitle = getRandString(alphaNumericString, 10);			
			String [] randAuthors = {getRandString(alphaNumericString, 10)};
			String randPublisher = getRandString(alphaNumericString, 10);
			String randISBN = getRandString(digitString, 10);
			int randRating = (int)(100 * Math.random());		
			bookBag2.add(new Book(randISBN, randAuthors, randTitle, randPublisher, (double) randRating));
		}
		assertTimeout(Duration.ofSeconds(10), () -> {	
			long start = System.currentTimeMillis();
			bookBag1.difference(bookBag2);
			long end = System.currentTimeMillis();
			if (DEBUG) System.out.println("\nTIMING: Difference runs in: " + (end - start)/1000 + "sec");		   
	    });
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
