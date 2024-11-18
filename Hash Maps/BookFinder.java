

/* 
 * This uses a dataset from the GoodReads website. 
 * You can find the original data file at 
 *     https://www.kaggle.com/jealousleopard/goodreadsbooks 
 * I cut this down to 5 columns: isbn, authors, title, publisher, GoodReads rating.
 * I also cleaned up the data a bit; there were a few broken entries.
 * Note that the file is written with the UTF-8 character encoding. This is the default
 * for Python but needs to be specified for Java, as in 
 * 	new Scanner( new File(foo), "UTF-8" )
 */

import java.util.*;
import java.io.*;



/**
 * A BookFinder class holds mappings between ISBNs, titles, authors, and
 * publishers to books represented by a {@link BookData}.
 * 
 * @author Bob Geitz
 * @author Stephen Checkoway
 * @author Your names here...
 * @version 2021-06-30
 * 
 */
public class BookFinder {
	/*
     * You need to open the data file with a "UTF-8" flag, as in
     * 
     * Scanner scan = new Scanner( new File(s), "UTF-8");
     *
     * For each line of the data file you should create a new BookData with the
     * relevant fields. Add the newly created BookData to isbnToData with the isbn
     * as the key.
     * 
     * For the other maps, add the BookData to the ArrayList stored in the map with
     * the appropriate key (title, author, or publisher). If a book has multiple
     * authors, then each author's list should contain the BookData.
     */
	MyHashMap<String, BookData> isbnToData = new MyHashMap<String, BookData>();
	MyHashMap<String, ArrayList<BookData>> titleToData = new MyHashMap<String, ArrayList<BookData>>();
	MyHashMap<String, ArrayList<BookData>> authorToData = new MyHashMap<String, ArrayList<BookData>>();
	MyHashMap<String, ArrayList<BookData>> publisherToData = new MyHashMap<String, ArrayList<BookData>>();
	MyHashMap<Float, ArrayList<BookData>> ratingToData = new MyHashMap<Float, ArrayList<BookData>>();
   
	/**
     * Creates a BookFinder object by reading the data file at path.
     * 
     * This will be a comma-separated text file with 5 fields per line:
     * isbn,authors,title,publisher,rating
     * 
     * Multiple authors are separated by '/' characters: Frank Herbert/Domingo
     * Santos
     * 
     * @param path The file path for the input data file.
     */
    public BookFinder (String path) {
    	isbnToData = new MyHashMap<String, BookData>();
    	titleToData = new MyHashMap<String, ArrayList<BookData>>();
    	authorToData = new MyHashMap<String, ArrayList<BookData>>();
    	publisherToData = new MyHashMap<String, ArrayList<BookData>>();
    	ratingToData = new MyHashMap<Float, ArrayList<BookData>>();
    	
    	fillDataFromFile(path);
    }
    
    
    public BookFinder() {
    	isbnToData = new MyHashMap<String, BookData>();
    	titleToData = new MyHashMap<String, ArrayList<BookData>>();
    	authorToData = new MyHashMap<String, ArrayList<BookData>>();
    	publisherToData = new MyHashMap<String, ArrayList<BookData>>();
    	ratingToData = new MyHashMap<Float, ArrayList<BookData>>();       
    }
    
    private void fillDataFromFile(String path) {
    	Scanner scan = null;
	    try {
	        scan = new Scanner(new File(path), "UTF-8");
	    } catch (FileNotFoundException e) {
	        System.err.println("File not found");
	    }
	    
	    while (scan.hasNextLine()) {
	        String line = scan.nextLine();
	        String[] A = line.split(",");
	        String isbn = A[0];
	        String[] authors = A[1].split("/");
	        String title = A[2];
	        String publisher = A[3];
	        float rating = Float.parseFloat(A[4]);
	        
	        BookData bd = new BookData(isbn, authors, title, publisher, rating);
	        addBookByISBN(isbn, bd);	       
	        
	        addBookByTitle (title, bd);
	        for (String author : authors) {
	        	addBookByAuthor(author, bd);
	        }
	        addBookByPublisher(publisher, bd);
	        
	        addBookByRating(rating, bd);
	    }
    }
    
    public void addBookByISBN(String key, BookData bd) {
    	isbnToData.put(key,  bd);    	
    }
    
    public void addBookByTitle (String title, BookData bd) {
    	addBookToList (titleToData, title, bd);
    }
    
    public void addBookByAuthor (String author, BookData bd) {
    	addBookToList (authorToData, author, bd);
    }
    
    public void addBookByPublisher (String publisher, BookData bd) {
    	addBookToList (publisherToData, publisher, bd);
    }
    
    public void addBookByRating(Float rating, BookData bd) {
    	ArrayList<BookData> list = ratingToData.get(rating);
	    if (list == null) 
	        list = new ArrayList<BookData>();
	    list.add(bd);
	    ratingToData.put(rating, list);
    }
    
    private static void addBookToList(MyHashMap<String, ArrayList<BookData>> map, 
    		String key, BookData bd) {
    	ArrayList<BookData> list = map.get(key);
	    if (list == null) 
	        list = new ArrayList<BookData>();
	    list.add(bd);
	    map.put(key, list);	 
    }   

	/**
	 * Returns a list of books written by the author.
	 * 
	 * @param author The author to search for.
	 * @return A list of {@link BookData} objects written by author.
	 */
	public List<BookData> searchByAuthor(String author) {
	    /* Implement this. */
	    return authorToData.get(author);
	}

	/**
	 * Returns a list of books with the exact title.
	 * 
	 * @param title The title to search for.
	 * @return A list of {@link BookData} objects with the given title.
	 */
	public List<BookData> searchByTitle(String title) {
	    /* Implement this. */
	    return titleToData.get(title);
	}

	/**
	 * Returns a list of books published by publisher.
	 * 
	 * @param publisher The publisher to search for.
	 * @return A list of {@link BookData} published by the publisher.
	 */
	public List<BookData> searchByPublisher(String publisher) {
	    /* Implement this. */
	    return publisherToData.get(publisher);
	}
	
	/**
	 * Returns a list of books with the same rating
	 * 
	 * @param rating The value of book rating.
	 * @return A list of {@link BookData} with this rating.
	 */
	public List<BookData> searchByRating(Float rating) {
	    /* Implement this. */
	    return ratingToData.get(rating);
	}

	/**
	 * Returns a book corresponding to an ISBN, or null if no such book is in the
	 * database.
	 * 
	 * @param isbn The ISBN to search for.
	 * @return A {@link BookData} corresponding to the isbn, or null.
	 */
	public BookData searchByIsbn(String isbn) {
	    /* Implement this. */
	    return isbnToData.get(isbn);
	}
}
