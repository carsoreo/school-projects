/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.io;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;

/**
 * Writes products to a specified file.
 * @author clorejol
 */
public class ProductsWriter {
	
	/**
	 * Constructor for ProductsWriter
	 */
	public ProductsWriter() {
		//Intentionally left empty
	}
	
	/**
	 * Writes products from a list to a given file name.
	 * @param file file to be written to.
	 * @param list ArrayList of products to be written to the given file.
	 * @throws IllegalArgumentException if there are any errors or exceptions with writing to a file.
	 */
	public static void writeProductsToFile(String file, ArrayList<Product> list) {
		try {
			PrintStream fileWriter = new PrintStream(new File(file));
			for (Product a : list) {
	    		fileWriter.print("# " + a.getProductName() + "\n");
	    		for (Task t: a.getTasks()) {
	    			fileWriter.print(t.toString());
	    		}
	    	}
	    	
	    	fileWriter.close();
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		
		
	}
	
}
