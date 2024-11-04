package ass1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyBagOperationsTest {
	public static final boolean DEBUG = true;
	
	public final String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	         + "0123456789"
	         + "abcdefghijklmnopqrstuvxyz"; 
	public final String digitString = "12345678910";
	
//==============================================================
	// Multi-set operations
	@Test
	void testUnion() {
		Character [] arrX =  {'l', 'l', 'm', 'm', 'n', 'n', 'n', 'n'};
		Character [] arrY =  {'l', 'm', 'm', 'm', 'n'};
		MyBag<Character> X = new MyBag<>(10);		
		for (int i=0; i<arrX.length; i++) {
			X.add(arrX[i]);
		}
		
		MyBag<Character> Y = new MyBag<>(10);		
		for (int i=0; i<arrY.length; i++) {
			Y.add(arrY[i]);
		}
		
		Character [] arrUnion = {'l', 'l', 'm', 'm', 'm', 'n', 'n', 'n', 'n' };
		MyBag<Character> union = new MyBag<>(10);		
		for (int i=0; i<arrUnion.length; i++) {
			union.add(arrUnion[i]);
		}
		
		if (DEBUG) System.out.println(System.lineSeparator()+ X 
				+ " UNION " + Y + " = " + X.union(Y));
		assertEquals(union, X.union(Y));		
	}

	@Test
	void testIntersection() {
		Character [] arrX =  {'l', 'l', 'm', 'm', 'n', 'n', 'n', 'n'};
		Character [] arrY =  {'l', 'm', 'm', 'm', 'n'};
		MyBag<Character> X = new MyBag<>(10);		
		for (int i=0; i<arrX.length; i++) {
			X.add(arrX[i]);
		}
		MyBag<Character> Y = new MyBag<>(10);		
		for (int i=0; i<arrY.length; i++) {
			Y.add(arrY[i]);
		}
		
		Character [] arrIntersect = {'l', 'm', 'm', 'n'};
		MyBag<Character> intersect = new MyBag<>(10);		
		for (int i=0; i<arrIntersect.length; i++) {
			intersect.add(arrIntersect[i]);
		}
		if (DEBUG) System.out.println(System.lineSeparator()+ X +
				" INTERSECT " +Y +" = "+ X.intersection(Y));
		assertEquals(intersect, X.intersection(Y));		
	}

	@Test
	void testDifference() {
		Character [] arrX =  {'l', 'l', 'm', 'm', 'n', 'n', 'n', 'n'};
		Character [] arrY =  {'l', 'm', 'm', 'm', 'n'};		
		
		MyBag<Character> X = new MyBag<>(10);		
		for (int i=0; i<arrX.length; i++) {
			X.add(arrX[i]);
		}
		MyBag<Character> Y = new MyBag<>(10);		
		for (int i=0; i<arrY.length; i++) {
			Y.add(arrY[i]);
		}
		
		Character [] arrDiff = {'l', 'n', 'n', 'n'};
		MyBag<Character> diff = new MyBag<>(10);		
		for (int i=0; i<arrDiff.length; i++) {
			diff.add(arrDiff[i]);
		}
		if (DEBUG) System.out.println(System.lineSeparator()+ X +
				" MINUS " +Y +" = "+ X.difference(Y));
		assertEquals(diff, X.difference(Y));
	}

	@Test
	void testIsSubBagOf() {
		Character [] arrX =  {'l', 'l', 'm', 'm', 'n', 'n', 'n', 'n'};
		Character [] arrY =  {'l', 'm', 'm', 'm', 'n'};
		Character [] arrZ =  {'l', 'm', 'm', 'n'};
		
		MyBag<Character> X = new MyBag<>(10);		
		for (int i=0; i<arrX.length; i++) {
			X.add(arrX[i]);
		}
		MyBag<Character> Y = new MyBag<>(10);		
		for (int i=0; i<arrY.length; i++) {
			Y.add(arrY[i]);
		}
		MyBag<Character> Z = new MyBag<>(10);		
		for (int i=0; i<arrZ.length; i++) {
			Z.add(arrZ[i]);
		}
		
		if (DEBUG) {
			System.out.println("\nSets:");
			System.out.println("X:"+X + ", Y:" + Y + ", Z:"+Z);
			System.out.println("X sub-multiset of Y?: "+ X.isSubBagOf(Y));		
			System.out.println("Y sub-multiset of X?: "+ Y.isSubBagOf(X));
			System.out.println("Z sub-multiset of X?: "+ Z.isSubBagOf(X));
			System.out.println("Z sub-multiset of Y?: "+ Z.isSubBagOf(Y));
		}
		assertTrue(Z.isSubBagOf(X));
		assertTrue(Z.isSubBagOf(Y));
		assertFalse(X.isSubBagOf(Y));
		assertFalse(Y.isSubBagOf(X));
	}

	@Test
	void testIsSuperBagOf() {
		Character [] arrX =  {'l', 'l', 'm', 'm', 'n', 'n', 'n', 'n'};
		Character [] arrY =  {'l', 'm', 'm', 'm', 'n'};
		Character [] arrZ =  {'l', 'm', 'm', 'n'};
		
		MyBag<Character> X = new MyBag<>(10);		
		for (int i=0; i<arrX.length; i++) {
			X.add(arrX[i]);
		}
		MyBag<Character> Y = new MyBag<>(10);		
		for (int i=0; i<arrY.length; i++) {
			Y.add(arrY[i]);
		}
		MyBag<Character> Z = new MyBag<>(10);		
		for (int i=0; i<arrZ.length; i++) {
			Z.add(arrZ[i]);
		}
		
		if (DEBUG) {
			System.out.println("\nSets:");
			System.out.println("X:"+X + ", Y:" + Y + ", Z:"+Z);
			System.out.println("X super-multiset of Y?: "+ X.isSuperBagOf(Y));
			System.out.println("X super-multiset of Z?: "+ X.isSuperBagOf(Z));
			System.out.println("Y super-multiset of Z?: "+ Y.isSuperBagOf(Z));
			System.out.println("Z super-multiset of Y?: "+ Z.isSuperBagOf(Y));
		}
		assertTrue(X.isSuperBagOf(Z));
		assertTrue(Y.isSuperBagOf(Z));
		assertFalse(X.isSuperBagOf(Y));
		assertFalse(Z.isSuperBagOf(Y));
	}

	@Test
	void testSum() {
		Character [] arrX =  {'l', 'l', 'm', 'm', 'n', 'n', 'n', 'n'};
		Character [] arrY =  {'l', 'm', 'm', 'm', 'n'};
		MyBag<Character> X = new MyBag<>(10);		
		for (int i=0; i<arrX.length; i++) {
			X.add(arrX[i]);
		}
		
		MyBag<Character> Y = new MyBag<>(10);		
		for (int i=0; i<arrY.length; i++) {
			Y.add(arrY[i]);
		}
		
		Character [] arrSum = {'l', 'l', 'l', 'm', 'm','m','m','m', 'n', 'n', 'n', 'n', 'n'};
		MyBag<Character> sum = new MyBag<>(15);		
		for (int i=0; i<arrSum.length; i++) {
			sum.add(arrSum[i]);
		}
		
		assertEquals(sum, X.sum(Y));
	}
	
	@Test
	void testCardinality() {
		Character [] arrX =  {'l', 'l', 'm', 'm', 'n', 'n', 'n', 'n'};
		Character [] arrY =  {'l', 'n', 'n', 'n', 'n'};
		Character [] arrZ =  {'m', 'm', 'm', 'm'};
		
		MyBag<Character> X = new MyBag<>(10);		
		for (int i=0; i<arrX.length; i++) {
			X.add(arrX[i]);
		}
		MyBag<Character> Y = new MyBag<>(10);		
		for (int i=0; i<arrY.length; i++) {
			Y.add(arrY[i]);
		}
		MyBag<Character> Z = new MyBag<>(10);		
		for (int i=0; i<arrZ.length; i++) {
			Z.add(arrZ[i]);
		}
		MyBag<Character> empty = new MyBag<>(10);
		
		// in assertEquals first is what we expect, second what the program produced
		assertEquals( 3, X.cardinality());
		assertEquals( 2, Y.cardinality());
		assertEquals( 1, Z.cardinality());
		assertEquals( 0, empty.cardinality());
	}	
}
