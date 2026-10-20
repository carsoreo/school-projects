/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * Tests the Product class' method.
 * @author Carson Orejola
 *
 */
class ProductTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.product.Product#Product(java.lang.String)}.
	 */
	@Test
	void testProduct() {
		
		
		//Test valid Product
		assertDoesNotThrow(() -> new Product("Product"));
		
		//Test invalid product
		assertThrows(IllegalArgumentException.class, () -> new Product(""));
		assertThrows(IllegalArgumentException.class, () -> new Product(null));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.product.Product#setProductName(java.lang.String)}.
	 */
	@Test
	void testSetProductName() {
		//Products to be used in testing
		Product p = new Product("Product");
		
		//Testing valid setter
		p.setProductName("Amazing");
		assertEquals(p.getProductName(), "Amazing");
		
		//Testing invalid uses
		assertThrows(IllegalArgumentException.class, () -> p.setProductName(null));
		assertThrows(IllegalArgumentException.class, () -> p.setProductName(""));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.product.Product#getProductName()}.
	 */
	@Test
	void testGetProductName() {
		//Products to be used in testing
		Product p = new Product("Product");
		//Making sure getter works
		assertEquals(p.getProductName(), "Product");
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.product.Product#addTask(edu.ncsu.csc216.product_backlog.model.task.Task)}.
	 */
	@Test
	void testAddTaskTask() {
		Product p = new Product("Product");
		
		Product p2 = new Product("Product");
		Task t1 = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		p2.addTask(t1);
		
		Task t = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		Task t2 = new Task(2, "Task", Type.FEATURE, "Carson", "Note");
		
		Product p3 = new Product("Product");
		Task t5 = new Task(5, "Task", Type.FEATURE, "Carson", "Note");
		p3.addTask(t1);
		p3.addTask(t5);
		
		Product p4 = new Product("Product");
		p4.addTask(t5);
		
		//Testing valid add
		
		//Adds empty
		assertDoesNotThrow(() -> p.addTask(t));
		//Adds Highest
		assertDoesNotThrow(() -> p2.addTask(t2));
		//Adds Middle
		assertDoesNotThrow(() -> p3.addTask(t2));
		//Adds Lowest
		assertDoesNotThrow(() -> p4.addTask(t1));
		
		//Testing invalid add
		assertThrows(IllegalArgumentException.class, () -> p2.addTask(t));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.product.Product#addTask(java.lang.String, edu.ncsu.csc216.product_backlog.model.task.Task.Type, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testAddTaskStringTypeStringString() {
		Product p2 = new Product("Product");
		Task t1 = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		p2.addTask(t1);
		
		p2.addTask("Title", Type.FEATURE, "Carson", "Note");
		assertEquals(2, p2.getTasks().size());
		assertEquals(t1, p2.getTasks().get(0));
		assertEquals(2, p2.getTasks().get(1).getTaskId());
		
		
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.product.Product#getTaskById(int)}.
	 */
	@Test
	void testGetTaskById() {
		Product p2 = new Product("Product");
		p2.addTask("1", Type.FEATURE, "Carson", "Note");
		p2.addTask("2", Type.FEATURE, "Carson", "Note2");
		p2.addTask("3", Type.FEATURE, "Carson", "Note3");
		
		//Valid tests
		assertEquals("1", p2.getTaskById(1).getTitle());
		assertEquals("2", p2.getTaskById(2).getTitle());
		assertEquals("3", p2.getTaskById(3).getTitle());
		
		//Invalid test
		assertEquals(null, p2.getTaskById(4));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.product.Product#executeCommand(int, edu.ncsu.csc216.product_backlog.model.command.Command)}.
	 */
	@Test
	void testExecuteCommand() {
		Product p2 = new Product("Product");
		Command c = new Command(CommandValue.CLAIM, "Owner", "Thats a shame");
		p2.addTask("1", Type.FEATURE, "Carson", "Note");
		p2.addTask("2", Type.FEATURE, "Carson", "Note2");
		p2.addTask("3", Type.FEATURE, "Carson", "Note3");
		
		assertDoesNotThrow(() -> p2.executeCommand(1, c));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.product.Product#deleteTaskById(int)}.
	 */
	@Test
	void testDeleteTaskById() {
		Product p2 = new Product("Product");
		p2.addTask("1", Type.FEATURE, "Carson", "Note");
		p2.addTask("2", Type.FEATURE, "Carson", "Note2");
		p2.addTask("3", Type.FEATURE, "Carson", "Note3");
		
		Product p3 = new Product("Product");
		p3.addTask("1", Type.FEATURE, "Carson", "Note");
		p3.addTask("2", Type.FEATURE, "Carson", "Note2");
		p3.addTask("3", Type.FEATURE, "Carson", "Note3");
		
		//Valid tests
		assertEquals(3, p2.getTasks().size());
		
		p2.deleteTaskById(2);
		assertEquals("3", p2.getTasks().get(1).getTitle());
		assertEquals(2, p2.getTasks().size());
		
		p2.deleteTaskById(3);
		assertEquals("1", p2.getTasks().get(0).getTitle());
		assertEquals(1, p2.getTasks().size());
		
		p2.deleteTaskById(1);
		assertEquals(0, p2.getTasks().size());
		
	}

}
