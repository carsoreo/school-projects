/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.backlog;

import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.io.ProductsReader;
import edu.ncsu.csc216.product_backlog.model.io.ProductsWriter;
import edu.ncsu.csc216.product_backlog.model.product.Product;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;
import edu.ncsu.csc216.product_backlog.model.task.Task;

/**
 * Manages the backlog of tasks to be completed by the user. Can load and save the
 * the backlog of products from a file. Can also check if products are duplicates, add products
 * delete products, edit products and clear products. Can also retrieve tasks as an array or
 * based on ID. Additionally, can execute commands and add tasks to products. Tasks can also be
 * deleted by ID. Can also reset the entire backlog.
 * @author clorejol
 */
public class BacklogManager {
	
	/** Instance of BacklogManager used to follow Singleton design pattern */
	private static BacklogManager singleton;
	
	/** Current product being used in BacklogManager */
 	private Product currentProduct;
	
 	/** ArrayList of products used by BacklogManager */
	private ArrayList<Product> products;
	
	/**
	 * Constructs the BacklogManager. Uses the Singleton design pattern.
	 * Should be only one instance of BacklogManager.
	 */
	private BacklogManager() {
		currentProduct = null;
		products = new ArrayList<Product>();
		
	}
	
	/**
	 * Returns the single instance of BacklogManager. Creates a new one to return if it does
	 * not exist yet.
	 * @return BacklogManager the current instance of BacklogManager. Will return a new one if
	 * does not exist.
	 */
	public static BacklogManager getInstance() {
		if (singleton == null) {
			singleton = new BacklogManager();
		}
		return singleton;
		
	}
	
	/**
	 * Saves the current product backlog to a given file name.
	 * @param file for backlog to be saved to.
	 * @throws IllegalArgumentException if current project is null or empty
	 */
	public void saveToFile(String file) {
		if (products.size() == 0 || products == null) {
			throw new IllegalArgumentException("Unable to save file.");
		}
		else {
			int size = products.size();
			for (int i = 0; i < size; i++) {
				if (products.get(i).getTasks().size() == 0) {
					throw new IllegalArgumentException("Unable to save file.");
				}
			}
			ProductsWriter.writeProductsToFile(file, products);
		}
	}
	
	/**
	 * Loads a product backlog from a given file.
	 * @param file used to populate product backlog.
	 */
	public void loadFromFile(String file) {
		ArrayList<Product> newProducts = ProductsReader.readProductsFile(file);
		int size = newProducts.size();
		for (int i = 0; i < size; i++) {
			products.add(newProducts.get(i));
			if (i == 0) {
				currentProduct = newProducts.get(i);
			}
		}
	}
	
	/**
	 * Loads a single product as the current product
	 * @param productName to be set as the current product
	 * @throws IllegalArgumentException if product trying to be selected is not in list
	 */
	public void loadProduct(String productName) {
		for(Product p: products) {
			if (p.getProductName().equals(productName)) {
				this.currentProduct = p;
				return;
			}
		}
		throw new IllegalArgumentException("Product not available.");
	}

	// Unused and unnecessary. Wouldn't this need to return a boolean. Was on UML Diagram.
//	/**
//	 * Checks if two products are duplicates of each other.
//	 * @param product to be checked as a duplicate
//	 */
//	private void isDuplicateProduct(String product) {
//		
	
	/**
	 * Builds a 2D array of tasks to be returned.
	 * @return finalArray 2D String array of tasks to be returned.
	 */
	public String[][] getTasksAsArray() {
		if (currentProduct == null) {
			String[][] finalArray = null;
			return finalArray;
		}
		else {
			int size = currentProduct.getTasks().size();
			String[][] finalArray = new String[size][4];
			for (int i = 0; i < size; i++) {
				finalArray[i][0] = String.valueOf(currentProduct.getTasks().get(i).getTaskId());
				finalArray[i][1] = currentProduct.getTasks().get(i).getStateName();
				finalArray[i][2] = currentProduct.getTasks().get(i).getTypeLongName();
				finalArray[i][3] = currentProduct.getTasks().get(i).getTitle();
			}
			return finalArray;
		}
	}
	
	/**
	 * Returns a task based on a given ID value.
	 * @param id int value used to locate certain Task.
	 * @return task Task value that corresponds to given ID.
	 */
	public Task getTaskById(int id) {
		if (currentProduct == null) {
			return null;
		}
		return currentProduct.getTaskById(id);
		
	}
	
	/**
	 * Executes a given command on a certain task given by the provided id.
	 * @param id int of a Task to be given a command 
	 * @param c Command to be given to a Task.
	 */
	public void executeCommand(int id, Command c) {
		currentProduct.getTaskById(id).update(c);
	}
	
	/**
	 * Deletes a task identified by a given ID.
	 * @param id int value used to find a certain Task
	 */
	public void deleteTaskById(int id) {
		currentProduct.getTasks().remove(currentProduct.getTaskById(id));
	}
	
	/**
	 * Attaches a task to a product which is constructed through given variables
	 * @param title String to be used to create task given by user
	 * @param type Type to be used to create task
	 * @param creator String name of the current user
	 * @param note String connected to task created by user
	 */
	public void addTaskToProduct(String title, Type type, String creator, String note) {
		currentProduct.addTask(title, type, creator, note);
	}
	
	/**
	 * Retrieves and returns the name of the product
	 * @return productName name of the product returned
	 */
	public String getProductName() {
		if (currentProduct == null) {
			return null;
		}
		return currentProduct.getProductName();
		
	}
	
	/**
	 * Returns a string array of products in the ProductBacklog
	 * @return productList String array of products in ProductBacklog
	 */
	public String[] getProductList() {
		int size = products.size();
		String[] productList = new String[size];
		for (int i = 0; i < size; i++) {
			productList[i] = products.get(i).getProductName();
		}
		return productList;
	}
	
	/**
	 * Empties products list. The current product also is set to null.
	 */
	public void clearProducts() {
		products = new ArrayList<Product>();
		currentProduct = null;
	}
	
	/**
	 * Updates the current product's name with a new given name.
	 * @param updateName new name to be applied to product.
	 */
	public void editProduct(String updateName) {
		if (currentProduct == null) {
			throw new IllegalArgumentException("No product selected.");
		}
		int size = products.size();
		for(Product p: products) {
			if (p.getProductName().equalsIgnoreCase(updateName) || updateName == null || "".equals(updateName)) {
				throw new IllegalArgumentException("Invalid product name.");
			}
		}
		for(int i = 0; i < size; i++) {
			if(products.get(i).equals(currentProduct)) {
				products.get(i).setProductName(updateName);
			}
		}
	}
	
	/**
	 * Creates a new product with a given name and attaches it to the end of the products list.
	 * @param productName name of the new product to be created.
	 */
	public void addProduct(String productName) {
		for(Product p: products) {
			if (productName == null || "".equals(productName) || p.getProductName().equalsIgnoreCase(productName)) {
				throw new IllegalArgumentException("Invalid product name.");
			}
		}
		products.add(new Product(productName));
		loadProduct(productName);
	}
	
	/**
	 * Deletes the current product and removes it from the list.
	 */
	public void deleteProduct() {
		int size = products.size();
		if (currentProduct == null) {
			throw new IllegalArgumentException("No product selected.");
		}
		for(int i = 0; i < size; i++) {
			if(products.get(i).equals(currentProduct)) {
				products.remove(i);
				break;
			}
		}
		if (products.size() != 0) {
			currentProduct = products.get(0);
		}
		else {
			currentProduct = null;
		}
	}
	
	/**
	 * Resets the backlog manager.
	 */
	protected void resetManager() {
		singleton = null;
	}
	
	
}
