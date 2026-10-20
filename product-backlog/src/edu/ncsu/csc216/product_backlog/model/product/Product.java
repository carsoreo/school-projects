/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.product;

import java.util.ArrayList;
import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;
import edu.ncsu.csc216.product_backlog.model.task.Task;

/**
 * Class which can construct a product, handle the name of a product, a counter, and a list of tasks. Tasks
 * can be added from this class and products names can be changed and retrieved. The task
 * counter can also be set and tasks can be retried by ID. This class can also execute commands
 * on tasks based on a given ID. The list can be emptied and the tasks can also be deleted.
 * @author clorejol
 */
public class Product {
	
	/** Name of the product */
	private String productName;
	
	/** Counter used when adding tasks to index them correctly*/
	private int counter;
	
	/** ArrayList of tasks managed by the Product */
	private ArrayList<Task> tasks = new ArrayList<Task>();;
	
	/**
	 * Constructor for Product. Creates a new empty List and sets the counter to 1.
	 * @param name of the Product to be constructed.
	 * @throws IllegalArgumentException if name is null or empty.
	 */
	public Product(String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid product name.");
		}
		this.counter = 1;
		emptyList();
		setProductName(name);
	}
	
	/**
	 * Sets the name of the Product object to a given parameter.
	 * @param name of the Product to be set.
	 * @throws IllegalArgumentException if there is an error setting the name.
	 */
	public void setProductName(String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid product name.");
		}
		this.productName = name;
	}
	
	/**
	 * Returns the name of the product.
	 * @return productName name of the product to be returned.
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * Sets the task counter by using the largest task ID and adds 1.
	 */
	private void setTaskCounter() {
		if (tasks.isEmpty()) {
			this.counter = 1;
		}
		else {
			this.counter = tasks.get(tasks.size() - 1).getTaskId() + 1; 
		}
	}
	
	/**
	 * Empties the current list of tasks.
	 */
	private void emptyList() {
		this.tasks = new ArrayList<Task>();
	}
	
	/**
	 * Adds a task to the list of tasks and sorts it correctly.
	 * @param task Task to be added to list of tasks.
	 * @throws IllegalArgumentException if task with same ID already exists in list.
	 */
	public void addTask(Task task) {
		if (tasks.isEmpty()) {
			tasks.add(0, task);
			setTaskCounter();
		}
		else {
			boolean idAbove = false;
			boolean idBelow = false;
			int size = tasks.size();
			for(int i = 0; i < size; i++) {
				
				//Checks if tasks ID is a duplicate
				if (task.getTaskId() == tasks.get(i).getTaskId()) {
					throw new IllegalArgumentException("Task cannot be added.");
				}
				
				//Checks if task ID is greater than current task ID of task list, stays true
				if (task.getTaskId() > tasks.get(i).getTaskId()) {
					idAbove = true;
				}
				
				//Checks if task ID is less than current task ID of task list
				if (task.getTaskId() < tasks.get(i).getTaskId()) {
					idBelow = true;
				}
				else {
					idBelow = false;
				}
				
				//If tasks was above and now below another tasks ID, add task at i - 1
				//If reaches end of loop and is still above, adds it at the end. It is the highest ID.
				//If reaches end of loop and is only below, but no errors were thrown, adds it at the beginning. It is the lowest ID.
				if (idAbove && idBelow) {
					tasks.add(i, task);
					setTaskCounter();
					return;
				}
				else if (idAbove && i == (size - 1)) {
					tasks.add(task);
					return;
				}
				else if (idBelow && !idAbove && i == (size - 1)) {
					tasks.add(0, task);
					return;
				}
			}
		}
	}
	
	/**
	 * Adds a new task to the list with parameters specified by the user. This includes
	 * a title, type, creator, and note. The ID assigned to this task is the current 
	 * value of the counter. Also updates the counter.
	 * @param title of the task given by the user.
	 * @param type of the task selected by the user.
	 * @param creator of the task determined by the current user.
	 * @param note of task given by user.
	 */
	public void addTask(String title, Type type, String creator, String note) {
		setTaskCounter();
		Task t = new Task(counter, title, type, creator, note);
		addTask(t);
		
	}
	
	/**
	 * Returns the list of Task objects for the product.
	 * @return taskList ArrayList of Task objects to be returned.
	 */
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	
	/**
	 * Returns a task based on a user given ID.
	 * @param id connected to task.
	 * @return task Task object correlating to given ID. Returns null if
	 * no task is found with the given Id.
	 */
	public Task getTaskById(int id) {
		for(int i = 0; i < tasks.size(); i++) {
			if (id == tasks.get(i).getTaskId()) {
				return tasks.get(i);
			}
		}
		return null;
		
	}
	
	/**
	 * Executes a given command on a certain task based on a given ID.
	 * @param id to be used to specify which task to execute the command on.
	 * @param c Command to be used to update Task object.
	 */
	public void executeCommand(int id, Command c) {
		if (tasks.isEmpty()) {
			return;
		}
		else {
			getTaskById(id).update(c);
		}	
	}
	
	/**
	 * Finds a specific task using it's ID and deletes it from the list.
	 * @param id used to specify which task to delete.
	 */
	public void deleteTaskById(int id) {
		for(int i = 0; i < tasks.size(); i++) {
			if (id == tasks.get(i).getTaskId()) {
				tasks.remove(i);
			}
		}
	}
	
}
