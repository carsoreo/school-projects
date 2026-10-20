package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 10 assignment page or just given explicitly to use in the
 * Workshop 10 assignment page.
 * 
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author clorejol
 *
 */
public class UpTreeDisjointSetForestTest {
	
	/** Forest of UpTrees to test */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
        
        Position<String> two2 = set.makeSet("two");
        assertEquals("two", two2.getElement());
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));

        // you should draw out examples by hand (or use the examples
        // in the lecture slides or textbook) to help guide your test cases.
        // Be sure to perform find operations followed by union
        // operations to make sure the appropriate root notes are
        // returned and used when union-ing
        
        set.union(one, three);
        assertEquals(set.find("one").getElement(), "three");
        set.union(five, one);
        assertEquals(set.find("five").getElement(), "three");
        set.union(five, seven);
        set.union(nine, seven);
        
        set.union(two, four);
        set.union(two, six);
        set.union(two, eight);
        set.union(two, ten);
        
        assertEquals(set.find("two").getElement(), "four");
        
        assertEquals(set.find("five").getElement(), "three");
        
        assertEquals(set.find("seven").getElement(), "three");
        
        assertEquals(set.find("six").getElement(), "four");
        
        assertEquals(set.find("eight").getElement(), "four");
        
        assertEquals(set.find("four").getElement(), "four");
        
        
        assertNull(set.find("11"));
        
        set.union(two, one);
        
        assertEquals(set.find("five").getElement(), "three");
        
        assertEquals(set.find("two").getElement(), "three");
        
        
    }
}