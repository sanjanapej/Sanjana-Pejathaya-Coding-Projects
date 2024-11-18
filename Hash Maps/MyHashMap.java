

import java.util.AbstractMap.SimpleEntry;
import java.util.*;

public class MyHashMap<K, V> {
    private static final int PRIME_TABLE_SIZES[] = { 5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,
            102877, 205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939,
            210719881, 421439783, 842879579, 1685759167 };
    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private LinkedList<SimpleEntry<K, V>>[] table;
    private int size;  //total number of elements stored in hash table
   
    private final float maxLoadFactor;  // max allowable size/capacity
 
    /**
     * Creates a new array of <code>LinkedList<SimpleEntry<K, V>></code> prime size
     * at least <code>capacity</code>.
     * 
     * @param capacity The minimum number of slots in the array.
     * @return The new array with all elements set to <code>null</code>.
     */
    @SuppressWarnings("unchecked")
    private LinkedList<SimpleEntry<K, V>>[] createTable(int capacity) {
        for (int primeCapacity : PRIME_TABLE_SIZES) {
            if (primeCapacity >= capacity) {
            	capacity = primeCapacity;
                break;
            }
        }
        LinkedList<SimpleEntry<K, V>> storage [] = new LinkedList [capacity];
        
        this.size = 0; 
        return storage;     
    }

    public MyHashMap (int capacity, float maxLoadFactor) {
        // To do: Implement this
    	this.table =  createTable(capacity);
    	this.maxLoadFactor = maxLoadFactor;
    	this.size = 0;
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public void clear() {
        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = null;
        }
        this.size = 0;
    }   
    
    public V get(K key) {

    	 int index = (key.hashCode() & Integer.MAX_VALUE) % table.length;
         LinkedList<SimpleEntry<K, V>> bucket = table[index];
         if (bucket != null) {
             for (SimpleEntry<K, V> entry : bucket) {
                 if (entry.getKey().equals(key)) {
                     return entry.getValue();
                 }
             }
         }
         return null;
    	
    }

    public V put(K key, V value) {
    	//if the key is null throw null pointer exception
    	if (key == null || value == null) {
    		throw new NullPointerException("Value cannot be null.");
    	}
    	//If a pair with a new key is added to the HashMap, the put method returns null.
    	 int index = (key.hashCode() & Integer.MAX_VALUE) % table.length;
         if (table[index] == null) {
             table[index] = new LinkedList<>();
         }
         //If the key was already present in the HashMap, its value gets updated, and the put method returns the previous value [This mimics the design of Java’s HashMap class].
         for (SimpleEntry<K, V> entry : table[index]) {
             if (entry.getKey().equals(key)) {
                 return entry.setValue(value);

             }
    	
    }
         table[index].add(new SimpleEntry<>(key, value));
         size++;
         checkLoadFactor();
         return null;
    }
    
    public V remove (K key) {
    	//method deletes the (key ,value) from the hash map. 
    	
    	int index = (key.hashCode() & Integer.MAX_VALUE) % table.length;
         LinkedList<SimpleEntry<K, V>> bucket = table[index];
         if (bucket != null) {
             Iterator<SimpleEntry<K, V>> iterator = bucket.iterator();
             while (iterator.hasNext()) {
                 SimpleEntry<K, V> entry = iterator.next();
                 if (entry.getKey().equals(key)) {
                     iterator.remove();
                     size--;
                     return entry.getValue();
                 }
             }
         }
       //Returns the previous value or `null` if there was no pair with this key.
         return null;
     }
    
    public boolean containsKey(K key) {
    	//returns true if the key is in the hash map. Returns false otherwise.
    	int index = (key.hashCode() & Integer.MAX_VALUE) % table.length;
         LinkedList<SimpleEntry<K, V>> bucket = table[index];
         // you should only examine a single bucket in order to get your answer.
         if (bucket != null) {
             for (SimpleEntry<K, V> entry : bucket) {
                 if (entry.getKey().equals(key)) {
                     return true;
                 }
             }
         }
         return false;
    }
    
    public boolean containsValue(V value) {
    	if (value == null)
    		throw new NullPointerException("Value cannot be null.");    	
        for (LinkedList<SimpleEntry<K, V>> bucket : this.table) {
            if (bucket == null)
                continue;
            for (SimpleEntry<K, V> entry : bucket) {
                if (entry.getValue().equals(value))
                    return true;
            }
        }
        return false;
    }

    public List<SimpleEntry<K, V>> getAllEntries(LinkedList<SimpleEntry<K, V>>[] hashTable) {
    	 ArrayList<SimpleEntry<K, V>> entries = new ArrayList<>();
         for (LinkedList<SimpleEntry<K, V>> bucket : hashTable) {
             if (bucket != null) {
                 entries.addAll(bucket);
             }
         }
         return entries;
    }

    private void resizeRehash() {
    	 LinkedList<SimpleEntry<K, V>>[] oldTable = this.table;
         this.table = createTable(this.table.length * 2);
         this.size = 0;
         //called whenever the maximum load factor is exceeded.  
         for (LinkedList<SimpleEntry<K, V>> bucket : oldTable) {
             if (bucket != null) {
                 for (SimpleEntry<K, V> entry : bucket) {
                     put(entry.getKey(), entry.getValue());
                 }
             }
         }
     }
    
    private void checkLoadFactor() {
    	if ((float) this.size/this.table.length >= this.maxLoadFactor)
    		resizeRehash();
    }
   
}
