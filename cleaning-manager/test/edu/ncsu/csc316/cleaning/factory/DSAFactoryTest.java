package edu.ncsu.csc316.cleaning.factory;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the DSAFactory class
 * @author clorejol
 *
 */
public class DSAFactoryTest {
	
	/**
	 * Tests the entire class just by making sure everything is constructed fine
	 */
	@Test
	public void test() {
		
		try {
			new DSAFactory();
		} catch (Exception e) {
			fail("Something went wrong in construction");
		}
		
		try {
			DSAFactory.getComparisonSorter(null);
		} catch (Exception e) {
			fail("Something went wrong in construction");
		}
		
		try {
			DSAFactory.getIndexedList();
		} catch (Exception e) {
			fail("Something went wrong in construction");
		}
		
		try {
			DSAFactory.getMap(null);
		} catch (Exception e) {
			fail("Something went wrong in construction");
		}
		
		
	}

}
