/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.product.Product;


/**
 * Tests the ProductsReader's methods.
 * @author clorejol
 *
 */
class ProductsReaderTest {
		
	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.io.ProductsReader#readProductsFile(java.lang.String)}.
	 */
	@Test
	void testReadProductsFile() {
		ArrayList<Product> plist = null;

		plist = ProductsReader.readProductsFile("test-files/exp_task_backlog.txt");

		assertEquals("Product", plist.get(0).getProductName());
		assertEquals("creator3", plist.get(0).getTaskById(3).getCreator());
		
		assertEquals("A Product", plist.get(1).getProductName());
		assertEquals("- [Backlog] note02\n", plist.get(1).getTaskById(2).getNotesList());
		
		assertThrows(IllegalArgumentException.class, () -> ProductsReader.readProductsFile("test-files/thisfiledoesnotexist.txt"));
		
		plist = ProductsReader.readProductsFile("test-files/tasks4.txt");
		assertEquals(plist.size(), 0);
		
		//Testing constructor
		assertDoesNotThrow(() -> new ProductsReader());
	}

}
