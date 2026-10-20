/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;

/**
 * Tests the Command class' methods.
 * @author clorejol
 *
 */
class CommandTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.command.Command#Command(edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testCommand() {
		assertThrows(IllegalArgumentException.class, () -> new Command(null, null, null));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.command.Command#getCommand()}.
	 */
	@Test
	void testGetCommand() {
		Command c = new Command(CommandValue.BACKLOG, null, "Thats a shame");
		CommandValue test = c.getCommand();
		assertEquals(test, CommandValue.BACKLOG);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.command.Command#getNoteText()}.
	 */
	@Test
	void testGetNoteText() {
		Command c = new Command(CommandValue.BACKLOG, null, "Thats a shame");
		String test = c.getNoteText();
		assertEquals("Thats a shame", test);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.command.Command#getOwner()}.
	 */
	@Test
	void testGetOwner() {
		Command c = new Command(CommandValue.BACKLOG, null, "Thats a shame");
		String test = c.getOwner();
		assertEquals(test, null);
	}

}
