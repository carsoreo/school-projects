/**
 * 
 */
package edu.ncsu.csc216.product_backlog.model.task;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.product_backlog.model.command.Command;
import edu.ncsu.csc216.product_backlog.model.command.Command.CommandValue;
import edu.ncsu.csc216.product_backlog.model.task.Task.Type;

/**
 * Tests the Task class methods.
 * @author clorejol
 *
 */
class TaskTest {
	
	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.task.Task#Task(int, java.lang.String, edu.ncsu.csc216.product_backlog.model.task.Task.Type, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testTaskIntStringTypeStringString() {
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Get Good", null, "Carson", "Wow"));
		assertThrows(IllegalArgumentException.class, () -> new Task(-1, "Get Good", Type.FEATURE, "Carson", "Wow"));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, null, Type.FEATURE, "Carson", "Wow"));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "", Type.FEATURE, "Carson", "Wow"));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Get Good", Type.FEATURE, null, "Wow"));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Get Good", Type.FEATURE, "", "Wow"));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Get Good", Type.FEATURE, "Carson", null));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Get Good", Type.FEATURE, "Carson", ""));
		
		Task t = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		assertAll(
				() -> assertEquals(t.getTaskId(), 1),
				() -> assertEquals(t.getTitle(), "Get Good"),
				() -> assertEquals(t.getType(), Type.FEATURE),
				() -> assertEquals(t.getCreator(), "Carson"),
				() -> assertEquals(t.getNotes().size(), 1));

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.task.Task#Task(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	void testTaskIntStringStringStringStringStringStringArrayListOfString() {
		ArrayList<String> notes = new ArrayList<String>();
		ArrayList<String> notesEmpty = new ArrayList<String>();
		notes.add("[Backlog] Express carts always choose the shortest line. If there are multiple shortest lines, an express cart chooses the one with the smallest index.");

		//Testing valid types and states
		assertDoesNotThrow(() -> new Task(1, "Rejected", "Title", "F", "creator", "unowned", "false", notes));
		assertDoesNotThrow(() -> new Task(1, "Done", "Title", "F", "creator", "owner", "true", notes));
		assertDoesNotThrow(() -> new Task(1, "Owned", "Title", "F", "creator", "owner", "true", notes));
		assertDoesNotThrow(() -> new Task(1, "Rejected", "Title", "B", "creator", "unowned", "false", notes));
		assertDoesNotThrow(() -> new Task(1, "Rejected", "Title", "KA", "creator", "unowned", "false", notes));
		assertDoesNotThrow(() -> new Task(1, "Rejected", "Title", "TW", "creator", "unowned", "false", notes));
		assertDoesNotThrow(() -> new Task(1, "Processing", "Title", "TW", "creator", "owner", "false", notes));
		assertDoesNotThrow(() -> new Task(1, "Verifying", "Title", "TW", "creator", "owner", "false", notes));
		
		//Testing invalid types and states
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Rejected", "Title", "gibberish", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "gibberish", "Title", "gibberish", "creator", "unowned", "false", notes));
		
		//Testing invalid parameters
		assertThrows(IllegalArgumentException.class, () -> new Task(-1, "Backlog", "Title", "F", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "", "Title", "F", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, null, "Title", "F", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "", "F", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", null, "F", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", "", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", null, "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", "F", "", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", "F", null, "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", "F", "creator", "", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", "F", "creator", null, "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", "F", "creator", "unowned", "", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", "F", "creator", "unowned", null, notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Backlog", "Title", "F", "creator", "unowned", "false", notesEmpty));
		
		//Testing a valid task
		Task t = new Task(1, "Backlog", "Title", "F", "creator", "unowned", "false", notes);
		assertAll(
				() -> assertEquals(t.getTaskId(), 1),
				() -> assertEquals(t.getStateName(), "Backlog"),
				() -> assertEquals(t.getTitle(), "Title"),
				() -> assertEquals(t.getTypeShortName(), "F"),
				() -> assertEquals(t.getCreator(), "creator"),
				() -> assertEquals(t.getOwner(), "unowned"),
				() -> assertFalse(t.isVerified()),
				() -> assertEquals(t.getNotes(), notes));
		
		//Testing valid states with invalid parameters
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Owned", "Title", "F", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Done", "Title", "F", "creator", "unowned", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Done", "Title", "F", "creator", "owner", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Done", "Title", "B", "creator", "owner", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Done", "Title", "TW", "creator", "owner", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Done", "Title", "KA", "creator", "owner", "true", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Rejected", "Title", "F", "creator", "owner", "false", notes));
		assertThrows(IllegalArgumentException.class, () -> new Task(1, "Rejected", "Title", "F", "creator", "owner", "false", notes));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.task.Task#addNoteToList(java.lang.String)}.
	 */
	@Test
	void testAddNoteToList() {
		Task t = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		assertThrows(IllegalArgumentException.class, () -> t.addNoteToList(null));
		assertThrows(IllegalArgumentException.class, () -> t.addNoteToList(""));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.task.Task#getTypeShortName()}.
	 */
	@Test
	void testGetTypeShortName() {
		Task t1 = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		assertEquals("F", t1.getTypeShortName());
		Task t2 = new Task(1, "Get Good", Type.BUG, "Carson", "Wow");
		assertEquals("B", t2.getTypeShortName());
		Task t3 = new Task(1, "Get Good", Type.KNOWLEDGE_ACQUISITION, "Carson", "Wow");
		assertEquals("KA", t3.getTypeShortName());
		Task t4 = new Task(1, "Get Good", Type.TECHNICAL_WORK, "Carson", "Wow");
		assertEquals("TW", t4.getTypeShortName());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.task.Task#getTypeLongName()}.
	 */
	@Test
	void testGetTypeLongName() {
		Task t1 = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		assertEquals("Feature", t1.getTypeLongName());
		Task t2 = new Task(1, "Get Good", Type.BUG, "Carson", "Wow");
		assertEquals("Bug", t2.getTypeLongName());
		Task t3 = new Task(1, "Get Good", Type.KNOWLEDGE_ACQUISITION, "Carson", "Wow");
		assertEquals("Knowledge Acquisition", t3.getTypeLongName());
		Task t4 = new Task(1, "Get Good", Type.TECHNICAL_WORK, "Carson", "Wow");
		assertEquals("Technical Work", t4.getTypeLongName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.task.Task#update(edu.ncsu.csc216.product_backlog.model.command.Command)}.
	 */
	@Test
	void testUpdate() {
		ArrayList<String> notes = new ArrayList<String>();
		notes.add("dummy line");
		Task t1 = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		Task t2 = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		Task t3 = new Task(1, "Rejected", "Title", "F", "creator", "unowned", "false", notes);
		Task t4 = new Task(1, "Owned", "Title", "F", "creator", "owner", "false", notes);
		Task t5 = new Task(1, "Owned", "Title", "F", "creator", "owner", "false", notes);
		Task t6 = new Task(1, "Owned", "Title", "F", "creator", "owner", "false", notes);
		Task t7 = new Task(1, "Processing", "Title", "KA", "creator", "owner", "false", notes);
		Command c = new Command(CommandValue.BACKLOG, null, "Thats a shame");
		Command c1 = new Command(CommandValue.CLAIM, "clorejol", "This is a note");
		Command c2 = new Command(CommandValue.REJECT, null, "Thats a shame");
		Command c3 = new Command(CommandValue.VERIFY, null, "Thats a shame");
		Command c4 = new Command(CommandValue.COMPLETE, null, "Thats a shame");
		Command c5 = new Command(CommandValue.PROCESS, null, "Thats a shame");
		
		//Testing invalid moves
		assertThrows(UnsupportedOperationException.class, () -> t1.update(c));
		assertThrows(UnsupportedOperationException.class, () -> t3.update(c2));
		assertThrows(UnsupportedOperationException.class, () -> t4.update(c1));
		assertThrows(UnsupportedOperationException.class, () -> t7.update(c3));
		
		//Testing valid moves
		assertDoesNotThrow(() -> t1.update(c1));
		assertDoesNotThrow(() -> t1.update(c2));
		assertDoesNotThrow(() -> t1.update(c));
		assertDoesNotThrow(() -> t2.update(c2));
		
		assertDoesNotThrow(() -> t4.update(c));
		assertDoesNotThrow(() -> t4.update(c1));
		assertDoesNotThrow(() -> t4.update(c5));
		assertDoesNotThrow(() -> t4.update(c3));
		assertDoesNotThrow(() -> t4.update(c4));
		assertDoesNotThrow(() -> t4.update(c5));
		assertDoesNotThrow(() -> t4.update(c5));
		assertDoesNotThrow(() -> t4.update(c));
		
		assertDoesNotThrow(() -> t5.update(c));
		assertDoesNotThrow(() -> t5.update(c1));
		assertDoesNotThrow(() -> t5.update(c5));
		assertDoesNotThrow(() -> t5.update(c3));
		assertDoesNotThrow(() -> t5.update(c4));
		assertDoesNotThrow(() -> t5.update(c));
		
		assertDoesNotThrow(() -> t6.update(c));
		assertDoesNotThrow(() -> t6.update(c1));
		assertDoesNotThrow(() -> t6.update(c5));
		assertDoesNotThrow(() -> t6.update(c3));
		assertDoesNotThrow(() -> t6.update(c5));
		
		assertDoesNotThrow(() -> t7.update(c4));
		
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.product_backlog.model.task.Task#toString()}.
	 */
	@Test
	void testToString() {
		Task t = new Task(1, "Get Good", Type.FEATURE, "Carson", "Wow");
		assertEquals("* 1,Backlog,Get Good,F,Carson,unowned,false\n- [Backlog] Wow\n", t.toString());
	}

}
