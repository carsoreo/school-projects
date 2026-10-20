package edu.ncsu.csc216.product_backlog.model.command;

/**
 * Class which contains an enumeration inner class to determine what command is being ordered.
 * @author clorejol
 *
 */
public class Command {
	
	/** Contains a note for the command */
	private String note;
	
	/** Contains the owner of the command */
	private String owner;
	
	/** Error message for when a command is invalid */
	private static final String COMMAND_ERROR_MESSAGE = "Invalid command.";
	
	/** Value which holds the CommandValue */
	private CommandValue c;
	
	/**
	 * Constructor for command which uses a command value, owner, and text for notes. Handles invalid
	 * parameters and throws an exception accordingly.
	 * @param value value from enumeration attached to a certain command.
	 * @param owner owner of a claimed task.
	 * @param noteText text to be attached to a command.
	 * @throws IllegalArgumentException if CommandValue is null, noteText is null or empty,
	 * a claimed task is given no owner, there is an owner for anything besides a CLAIM command.
	 */
	public Command(CommandValue value, String owner, String noteText) {
		if (value == null ||
			noteText == null ||
			"".equals(noteText)) {
			
			throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
			
		}
		else {
			this.c = value;
			switch (getCommand()) {
			case CLAIM:
				if(owner == null || "".equals(owner)) {
					throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
				}
				break;
			default:
				if(owner != null) {
					throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE);
				}
				break;
			}
			this.note = noteText;
			this.owner = owner;
		}
	}
	
	/**
	 * Returns a CommandValue value of a command.
	 * @return value CommandValue value to be returned.
	 */
	public CommandValue getCommand() {
		return this.c;
		
	}
	
	/**
	 * Returns the note text for a command
	 * @return noteText String containing note text to be returned.
	 */
	public String getNoteText() {
		return this.note;
	}
	
	/**
	 * Returns the owner for a command.
	 * @return owner String containing owner data to be returned.
	 */
	public String getOwner() {
		return this.owner;
		
	}
	
	/**
	 * Inner enumeration which contains the command values to be used when construct Command.
	 * @author clorejol
	 *
	 */
	public enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }
}
