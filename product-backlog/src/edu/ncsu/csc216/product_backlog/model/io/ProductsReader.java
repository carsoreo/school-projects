package edu.ncsu.csc216.product_backlog.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task;

/**
 * Reads products from a file and converts them into Task and Product objects. Returns the products
 * into an ArrayList of products to be used by other classes.
 * @author Carson Orejola
 *
 */
public class ProductsReader {
	
	/**
	 * Constructor for ProductsReader
	 */
	public ProductsReader() {
		//Intentionally left empty
	}
	
	/**
	 * Reads products from a file and returns them into an ArrayList of products. Ignores
	 * invalid products or tasks.
	 * @param file to read products from.
	 * @return productList ArrayList of products populated from the given file.
	 * @throws IllegalArgumentException if the file trying to be loaded does not exist.
	 */
	public static ArrayList<Product> readProductsFile(String file) {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			Scanner fileReader = new Scanner(new FileInputStream(file));
			fileReader.useDelimiter("\\r?\\n?[#] ");
			while(fileReader.hasNext()) {
				String productBody = fileReader.next();
				//System.out.print(productBody);
				Product product = processProduct(productBody);
				if (product == null) {
					break;
				}
				else {
					products.add(product);
				}
			}
			fileReader.close();
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		return products;
		
	}
	
	/**
	 * Reads text that is used to construct a product. Returns that constructed product.
	 * @param productText text that is parsed to get parameters to build product.
	 * @return product Product that was constructed from the given text.
	 */
	private static Product processProduct(String productText) {
		try {
			Scanner taskReader = new Scanner(productText);
			String productLine = taskReader.nextLine();
			//System.out.println(productLine);
			if (productLine.contains("*")) {
				taskReader.close();
				return null;
			}
			Product product = new Product(productLine);
			taskReader.useDelimiter("\\r?\\n?[*] ");
			
			while(taskReader.hasNext()) {
				try {
					Task task = processTask(taskReader.next());
					if (task == null) {
						break;
					}
					else {
						product.addTask(task);
					}
					
				}
				catch(Exception e) {
					//ignore
				}
			}
			taskReader.close();
			if (product.getTasks().size() == 0) {
				return null;
			}
			else {
				return product;
			}
			
		}
		catch(Exception e) {
			return null;
		}
		
		
	}
	
	/**
	 * Reads text that is used to construct a task. Returns that constructed task.
	 * @param taskText text that is parsed to get parameters to build task.
	 * @return task Task that was constructed from the given text.
	 */
	private static Task processTask(String taskText) {
		try {
			ArrayList<String> notes = new ArrayList<String>();
			Scanner noteParser = new Scanner(taskText);
			Scanner headParser = new Scanner(noteParser.nextLine());
			
			headParser.useDelimiter(",");
			String id = headParser.next();
			String state = headParser.next();
			String title = headParser.next();
			String type = headParser.next();
			String creator = headParser.next();
			String owner = headParser.next();
			String verified = headParser.next();
			int idFinal = Integer.parseInt(id);
			headParser.close();
			
			try {
				noteParser.useDelimiter("\\r?\\n?[-] ");
				while(noteParser.hasNext()) {
					 String note = noteParser.next();
					 String cleanStr = note.replaceAll("\r", "");
					 notes.add(cleanStr);
				}
				noteParser.close();
			}
			catch(Exception e) {
				//ignore
			}
			
			Task task = new Task(idFinal, state, title, type, creator, owner, verified, notes);
			return task;
		}
		catch(Exception e) {
			return null;
		}
		
	}
	
	
}
