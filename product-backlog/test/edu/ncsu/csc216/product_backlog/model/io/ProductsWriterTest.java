/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * Tests the ProductWriter's methods.
 * @author clorejol
 *
 */
class ProductsWriterTest {

	/**
	 * Helper method to compare files
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.io.ProductsWriter#writeProductsToFile(java.lang.String, java.util.List)}.
	 */
	@Test
	void testWriteProductsToFile() {
		
		ProductsWriter.writeProductsToFile("test-files/tasks1_actual.txt", ProductsReader.readProductsFile("test-files/tasks1.txt"));
		assertDoesNotThrow(() -> checkFiles("test-files/tasks1.txt", "test-files/tasks1_actual.txt"));
		
		assertThrows(IllegalArgumentException.class, () -> ProductsWriter.writeProductsToFile("", ProductsReader.readProductsFile("test-files/tasks1.txt")));
		
		//Testing constructor
		assertDoesNotThrow(() -> new ProductsWriter());

	}

}
