/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.backlog;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * Tests BacklogManager's methods.
 * @author Carson Orejola
 *
 */
class BacklogManagerTest {


	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#getInstance()}.
	 */
	@Test
	void testGetInstance() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		assertDoesNotThrow(() -> b.loadFromFile("test-files/exp_task_backlog.txt"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#loadProduct(java.lang.String)}.
	 */
	@Test
	void testLoadProduct() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		assertDoesNotThrow(() -> b.loadProduct("Product"));
		assertThrows(IllegalArgumentException.class, () -> b.loadProduct("this product does not exist"));
		
		b.loadProduct("Product");
		assertEquals("Product", b.getProductName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#getTasksAsArray()}.
	 */
	@Test
	void testGetTasksAsArray() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		assertDoesNotThrow(() -> b.getTasksAsArray());
		assertDoesNotThrow(() -> b.getTaskById(1));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#getProductName()}.
	 */
	@Test
	void testGetProductName() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		assertEquals("Product", b.getProductName());
		
		b.clearProducts();
		assertEquals(null, b.getProductName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#getProductList()}.
	 */
	@Test
	void testGetProductList() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		String[] array = BacklogManager.getInstance().getProductList();
		assertEquals(2, array.length);
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#clearProducts()}.
	 */
	@Test
	void testClearProducts() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		b.clearProducts();
		
		assertEquals(0, BacklogManager.getInstance().getProductList().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#editProduct(java.lang.String)}.
	 */
	@Test
	void testEditProduct() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		
		b.editProduct("New Product");
		assertEquals("New Product", b.getProductName());
		
		assertThrows(IllegalArgumentException.class, () -> b.editProduct(""));
		assertThrows(IllegalArgumentException.class, () -> b.editProduct(null));
		
		b.clearProducts();
		assertThrows(IllegalArgumentException.class, () -> b.editProduct("Ok"));
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#addProduct(java.lang.String)}.
	 */
	@Test
	void testAddProduct() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		b.addProduct("New Product");
		
		assertEquals(3, BacklogManager.getInstance().getProductList().length);
		
		assertThrows(IllegalArgumentException.class, () -> b.addProduct(""));
		assertThrows(IllegalArgumentException.class, () -> b.addProduct(null));
		assertThrows(IllegalArgumentException.class, () -> b.addProduct("Product"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#deleteProduct()}.
	 */
	@Test
	void testDeleteProduct() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		b.addProduct("New Product");
		
		b.deleteProduct();
		
		assertEquals(2, BacklogManager.getInstance().getProductList().length);
		
		b.deleteProduct();
		b.deleteProduct();
		
		assertEquals(0, BacklogManager.getInstance().getProductList().length);
		
		assertThrows(IllegalArgumentException.class, () -> b.deleteProduct());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#resetManager()}.
	 */
	@Test
	void testResetManager() {
		BacklogManager b = BacklogManager.getInstance();
		b.resetManager();
		assertEquals(null, b.getProductName());
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#saveToFile()}.
	 */
	@Test
	void testSaveToFile() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		
		assertDoesNotThrow(() -> b.saveToFile("test-files/BackManTest.txt"));
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#addTaskToProduct()}.
	 */
	@Test
	void testAddTaskToProduct() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		
		assertThrows(IllegalArgumentException.class, () -> b.addTaskToProduct(null, null, null, null));
		assertDoesNotThrow(() -> b.addTaskToProduct("title", Type.BUG, "creator", "note"));
		assertEquals(b.getTaskById(4).getTitle(), "title");
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#deleteTaskById()}.
	 */
	@Test
	void testDeleteTaskById() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		
		assertDoesNotThrow(() -> b.deleteTaskById(1));
		assertEquals(b.getTasksAsArray()[0][1], "Backlog");
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.backlog.BacklogManager#executeCommand()}.
	 */
	@Test
	void testExecuteCommand() {
		BacklogManager b = BacklogManager.getInstance();
		b.clearProducts();
		b.loadFromFile("test-files/exp_task_backlog.txt");
		Command c = new Command(CommandValue.CLAIM, "owner", "note");
		assertDoesNotThrow(() -> b.executeCommand(1, c));
		assertEquals(b.getTaskById(1).getOwner(), "owner");
	}

}
