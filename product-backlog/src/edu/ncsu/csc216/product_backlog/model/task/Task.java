package edu.ncsu.csc216.product_backlog.model.task;

import java.util.ArrayList;

import edu.ncsu.csc216.product_backlog.model.command.Command;

/**
 * Class which holds Task object which contains the context class Task, inner interface TaskState, and 6 inner
 * concrete classes for each state. These concrete classes implement TaskState and update based on command and
 * can return their own name. Task also includes in inner enumeration to handle all of the possible types of
 * tasks. Each Task object has a taskId, title, creator, owner, ArrayList of notes, current state, and boolean
 * check to see if it is verified.
 * @author clorejol
 *
 */
public class Task {
	
	/** ID of a Task */
	private int taskId;
	
	/** Title assigned to a Task */
	private String title;
	
	/** Creator assigned to a Task */
	private String creator;
	
	/** Owner assigned to a Task */
	private String owner;
	
	/** Boolean used to see if a Task is verified or not */
	private boolean isVerified;
	
	/** ArrayList of String(s) to hold several notes */
	private ArrayList<String> notes;
	
	/** Current state of a certain Task */
	private TaskState currentState;
	
	/** Type associated with the Task */
	private Type type;
	
	/** Instance of the ProcessingState() class to be used in the FSM */
	private final TaskState processingState = new ProcessingState();
	
	/** Instance of the OwnedState() class to be used in the FSM */
	private final TaskState ownedState = new OwnedState();
	
	/** Instance of the BacklogState() class to be used in the FSM */
	private final TaskState backlogState = new BacklogState();
	
	/** Instance of the DoneState() class to be used in the FSM */
	private final TaskState doneState = new DoneState();
	
	/** Instance of the RejectedState() class to be used in the FSM */
	private final TaskState rejectedState = new RejectedState();
	
	/** Instance of the VerifyingState() class to be used in the FSM */
	private final TaskState verifyingState = new VerifyingState();
	
	/** String name of the Backlog state */
	public static final String BACKLOG_NAME = "Backlog";
	
	/** String name of the Owned state */
	public static final String OWNED_NAME = "Owned";
	
	/** String name of the Processing state */
	public static final String PROCESSING_NAME = "Processing";
	
	/** String name of the Verifying state */
	public static final String VERIFYING_NAME = "Verifying";
	
	/** String name of the Done state */
	public static final String DONE_NAME = "Done";
	
	/** String name of the Rejected state */
	public static final String REJECTED_NAME = "Rejected";
	
	/** String name of the Feature Task type */
	public static final String FEATURE_NAME = "Feature";
	
	/** String name of the Bug Task type */
	public static final String BUG_NAME = "Bug";
	
	/** String name of the Technical Work Task type */
	public static final String TECHNICAL_WORK_NAME = "Technical Work";
	
	/** String name of the Knowledge Acquisition Task type */
	public static final String KNOWLEDGE_ACQUISITION_NAME = "Knowledge Acquisition";
	
	/** Short name constant for Feature */
	public static final String T_FEATURE = "F";
	
	/** Short name constant for Bug */
	public static final String T_BUG = "B";
	
	/** Short name constant for Technical Work */
	public static final String T_TECHNICAL_WORK = "TW";
	
	/** Short name for Knowledge Acquisition */
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	
	/** Constant used to name unowned Task(s) */
	public static final String UNOWNED = "unowned";
	
	/**
	 * Constructor for new Task that will start in the backlogState, setting the owner to unowned.
	 * Also adds the given note to a newly constructed ArrayList of notes. Handles errors in parameters.
	 * @param id the id of a Task to be constructed, determined by Id of most recent Task.
	 * @param title the title of a Task to be constructed, determined by user.
	 * @param type the Type of a Task be constructed, determined by user.
	 * @param creator the creator of a Task to be constructed, determined by user.
	 * @param note note added to a Task, provided by user.
	 * @throws IllegalArgumentException if any parameter are null or empty or if the id is zero or less.
	 */
	public Task(int id, String title, Type type, String creator, String note) {
		
		if (id <= 0 ||
			title == null ||
			type == null ||
			creator == null ||
			note == null ||
			"".equals(title) ||
			"".equals(creator) ||
			"".equals(note)) {
			
			throw new IllegalArgumentException("Invalid task information.");
			
		}
		else {
			setTaskId(id);
			setTitle(title);
			setCreator(creator);
			setOwner(UNOWNED);
			setNotes(new ArrayList<String>());
			setState(BACKLOG_NAME);
			this.type = type;
			addNoteToList(note);
		}
		
	}
	
	/**
	 * Constructor Tasks, using all parameters of a Task. Handles invalid inputs and ignores
	 * throws errors if found. 
	 * @param id the id of a Task to be constructed, determined by input.
	 * @param state the state of a Task to be constructed, determined by input.
	 * @param title the title of a Task to be constructed, determined by input.
	 * @param type the Type of a Task be constructed, determined by input.
	 * @param creator the creator of a Task to be constructed, determined by input.
	 * @param owner the owner of a Task to be constructed, determined by input.
	 * @param verified boolean check for a Task to see if it is verified, determined by input.
	 * @param notes notes constructed with a Task, provided by input.
	 * @throws IllegalArgumentException if any parameters are incompatible with the given state.
	 */
	public Task (int id, String state, String title, String type, String creator, String owner, String verified, ArrayList<String> notes) {
		
		//General parameters check
		if (id <= 0 ||
			state == null ||
			title == null ||
			type == null ||
			creator == null ||
			owner == null ||
			verified == null ||
			"".equals(state) ||
			"".equals(title) ||
			"".equals(type) ||
			"".equals(creator) ||
			"".equals(owner) ||
			"".equals(verified) ||
			notes == null ||
			notes.isEmpty()) {
			
			throw new IllegalArgumentException("Invalid task information.");
				
		}
		else {
			
			//Checking valid state, valid type
			if (!(BACKLOG_NAME.equals(state) ||
				OWNED_NAME.equals(state) ||
				PROCESSING_NAME.equals(state) ||
				VERIFYING_NAME.equals(state) ||
				DONE_NAME.equals(state) ||
				REJECTED_NAME.equals(state)) ||
				!(T_FEATURE.equals(type) ||
				T_BUG.equals(type) ||
				T_KNOWLEDGE_ACQUISITION.equals(type) ||
				T_TECHNICAL_WORK.equals(type))) {
				
				throw new IllegalArgumentException("Invalid task information.");
				
			}
			else {
				//Switch class to handle certain state and type parameter validity
				switch(state) {
				case OWNED_NAME:	
				case PROCESSING_NAME:
				case VERIFYING_NAME:
					if (UNOWNED.equals(owner)) {
						throw new IllegalArgumentException("Invalid task information.");
					}
					break;
				case DONE_NAME:
					if (UNOWNED.equals(owner)) {
						throw new IllegalArgumentException("Invalid task information.");
					}
					if ((type.equals(T_FEATURE) || type.equals(T_BUG) || type.equals(T_TECHNICAL_WORK)) && "false".equals(verified)) {
						throw new IllegalArgumentException("Invalid task information.");
					}
					if (type.equals(T_KNOWLEDGE_ACQUISITION) && "true".equals(verified)) {
						throw new IllegalArgumentException("Invalid task information.");
					}
					break;
				case BACKLOG_NAME:
				case REJECTED_NAME:
					if (!(owner.equals(UNOWNED))) {
						throw new IllegalArgumentException("Invalid task information.");
					}
					break;
				default: 
					throw new IllegalArgumentException("Invalid task information.");
				}
				//Setting the fields if all checks pass
				setTaskId(id);
				setState(state);
				setTitle(title);
				setTypeFromString(type);
				setCreator(creator);
				setOwner(owner);
				if ("true".equals(verified)) {
					setVerified("true");
				}
				else if ("false".equals(verified)) {
					setVerified("false");
				}
				setNotes(notes);
			}
		}
	}
		
	
	
	/**
	 * Returns the taskId of the Task.
	 * @return the taskId to be returned
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * Sets the taskId of the Task.
	 * @param taskId the taskId to set.
	 */
	private void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * Returns the title of the Task.
	 * @return the title to be returned.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the Task.
	 * @param title the title to set.
	 */
	private void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the creator of the Task.
	 * @return the creator to be returned.
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Sets the creator for the Task.
	 * @param creator the creator to be set.
	 */
	private void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * Returns the owner of the Task.
	 * @return the owner to be returned.
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of the Task.
	 * @param owner the owner to be set.
	 */
	private void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Returns the status of the isVerified boolean.
	 * @return the isVerified boolean value to be returned.
	 */
	public boolean isVerified() {
		return isVerified;
	}

	/**
	 * Sets the boolean of isVerified to the parameters.
	 * @param verified the string value to set isVerified to.
	 */
	private void setVerified(String verified) {
		if ("true".equals(verified)) {
			this.isVerified = true;
		}
		else if ("false".equals(verified)) {
			this.isVerified = false;
		}
	}

	/**
	 * Returns the ArrayList String of notes.
	 * @return the notes ArrayList to be returned.
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}

	/**
	 * Sets the ArrayList notes to an ArrayList provided by the parameter.
	 * @param notes the notes to set
	 */
	private void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}
	
	/**
	 * Adds a note to the list ensuring it is not null nor empty. Adds the state name
	 * to the note in brackets. 
	 * @param userNote the note to be added to the list.
	 * @return int to be returned.
	 */
	public int addNoteToList(String userNote) {
		if (userNote == null || "".equals(userNote)) {
			throw new IllegalArgumentException("Invalid task information.");
		}
		else {
			String noteHold = userNote;
			String noteFinal = "[" + getStateName() + "] " + noteHold; 
			notes.add(noteFinal);
			return 1;
		}
	}
	
	/**
	 * Returns the state name of the Task.
	 * @return stateName of the Task to be returned.
	 */
	public String getStateName() {
		String stateName = this.currentState.getStateName();
		return stateName;
	}
	
	/**
	 * Sets the state of the Task.
	 * @param state to be set.
	 */
	private void setState(String state) {
		if (BACKLOG_NAME.equals(state)) {
			this.currentState = backlogState;
		}
		else if (PROCESSING_NAME.equals(state)) {
			this.currentState = processingState;
		}
		else if (OWNED_NAME.equals(state)) {
			this.currentState = ownedState;
		}
		else if (DONE_NAME.equals(state)) {
			this.currentState = doneState;
		}
		else if (REJECTED_NAME.equals(state)) {
			this.currentState = rejectedState;
		}
		else if (VERIFYING_NAME.equals(state)) {
			this.currentState = verifyingState;
		}
	}
	
	/**
	 * Sets the type of the Task using a given String
	 * @param type String to be used to set type.
	 */
	private void setTypeFromString(String type) {
		if (T_FEATURE.equals(type)) {
			this.type = Type.FEATURE;
		}
		else if (T_BUG.equals(type)) {
			this.type = Type.BUG;
		}
		else if (T_TECHNICAL_WORK.equals(type)) {
			this.type = Type.TECHNICAL_WORK;
		}
		else if (T_KNOWLEDGE_ACQUISITION.equals(type)) {
			this.type = Type.KNOWLEDGE_ACQUISITION;
		}
	}
	
	/**
	 * Returns current Type enumeration.
	 * @return type Type enumeration to be returned.
	 */
	public Type getType() {
		return type;
		
	}
	
	/**
	 * Returns a short name of the type.
	 * @return shortName to be returned.
	 */
	public String getTypeShortName() {
		String shortName = null;
		if (this.type.equals(Type.BUG)) {
			shortName = T_BUG;
		}
		else if (this.type.equals(Type.FEATURE)) {
			shortName = T_FEATURE;
		}
		else if (this.type.equals(Type.KNOWLEDGE_ACQUISITION)) {
			shortName = T_KNOWLEDGE_ACQUISITION;
		}
		else if (this.type.equals(Type.TECHNICAL_WORK)) {
			shortName = T_TECHNICAL_WORK;
		}
		return shortName;
	}
	
	/**
	 * Returns a long name of the type.
	 * @return longName to be returned.
	 */
	public String getTypeLongName() {
		String longName = null;
		if (this.type.equals(Type.BUG)) {
			longName = BUG_NAME;
		}
		else if (this.type.equals(Type.FEATURE)) {
			longName = FEATURE_NAME;
		}
		else if (this.type.equals(Type.KNOWLEDGE_ACQUISITION)) {
			longName = KNOWLEDGE_ACQUISITION_NAME;
		}
		else if (this.type.equals(Type.TECHNICAL_WORK)) {
			longName = TECHNICAL_WORK_NAME;
		}
		return longName;
	}
	
	/**
	 * Drives the finite state machine to handle Task states.
	 * @param c Command to be used in FSM.
	 * @throws UnsupportedOperationException if transition is not valid for FSM.
	 */
	public void update(Command c) throws UnsupportedOperationException{
			currentState.updateState(c);
	}
	
	/**
	 * Returns notes of a Task as a String array.
	 * @return notesArray array of notes to be returned.
	 */
	public String[] getNotesArray() {
		
		int size = notes.size();
		String[] notesArray = new String[size];
		for (int i = 0; i < size; i++) {
			notesArray[i] = notes.get(i);
		}
		return notesArray;
		
	}
	
	/**
	 * Returns the notes as a list in a single String. Also appends the leading symbol.
	 * @return notesList String of notes to be returned.
	 */
	public String getNotesList() {
		String notesList = "";
		for(int i = 0; i < getNotesArray().length; i++) {
			notesList += "- " + getNotesArray()[i] + "\n";
		}
		return notesList;
		
	}
	
	/**
	 * Method which overwrites default Java toString() method.
	 * @return finalString String of Task to be returned.
	 */
	@Override
	public String toString() {
		String finalString = "";
		finalString += "* ";
		finalString += getTaskId() + ",";
		finalString += getStateName() + ",";
		finalString += getTitle() + ",";
		finalString += getTypeShortName() + ",";
		finalString += getCreator() + ",";
		finalString += getOwner() + ",";
		if (isVerified) {
			finalString += "true\n";
		}
		else {
			finalString += "false\n";
		}
		finalString += getNotesList();
		return finalString;
	}	
	
	/**
	 * Inner concrete class which controls the Backlog state.
	 * @author clorejol
	 */
	public class BacklogState implements TaskState {
		
		private BacklogState() {
			
		}
		
		@Override
		public void updateState(Command c) {
			switch(c.getCommand()) {
			case REJECT:
				currentState = rejectedState;
				addNoteToList(c.getNoteText());
				break;
			case CLAIM:
				setOwner(c.getOwner());
				currentState = ownedState;
				addNoteToList(c.getNoteText());
				break;
			default:
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}
		
		@Override
		public String getStateName() {
			return BACKLOG_NAME;
			
		}
		
	}
	
	/**
	 * Inner concrete class which controls the Owned state.
	 * @author clorejol
	 */
	public class OwnedState implements TaskState {
		
		private OwnedState() {
			
		}
		
		@Override
		public void updateState(Command c) {
			switch(c.getCommand()) {
			case REJECT:
				currentState = rejectedState;
				addNoteToList(c.getNoteText());
				break;
			case BACKLOG:
				setOwner(UNOWNED);
				currentState = backlogState;
				addNoteToList(c.getNoteText());
				break;
			case PROCESS:
				currentState = processingState;
				addNoteToList(c.getNoteText());
				break;
			default:
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}
		
		@Override
		public String getStateName() {
			return OWNED_NAME;
			
		}
		
	}
	
	/**
	 * Inner concrete class which controls the Verifying state.
	 * @author clorejol
	 */
	public class VerifyingState implements TaskState {
		
		private VerifyingState() {
			
		}
		
		@Override
		public void updateState(Command c) {
			switch(c.getCommand()) {
			case COMPLETE:
				setVerified("true");
				currentState = doneState;
				addNoteToList(c.getNoteText());
				break;
			case PROCESS:
				currentState = processingState;
				addNoteToList(c.getNoteText());
				break;
			default:
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}
		
		@Override
		public String getStateName() {
			return VERIFYING_NAME;
			
		}
		
	}
	
	/**
	 * Inner concrete class which controls the Processing state.
	 * @author clorejol
	 */
	public class ProcessingState implements TaskState {
		
		private ProcessingState() {
			
		}
		
		@Override
		public void updateState(Command c) {
			switch(c.getCommand()) {
			case VERIFY:
				if (getTypeShortName().equals(T_FEATURE) ||
					getTypeShortName().equals(T_TECHNICAL_WORK) ||
					getTypeShortName().equals(T_BUG)) {
					currentState = verifyingState;
					addNoteToList(c.getNoteText());
				}
				else if (getTypeShortName().equals(T_KNOWLEDGE_ACQUISITION)) {
					throw new UnsupportedOperationException("Invalid transition.");
				}
				break;
			case BACKLOG:
				setOwner(UNOWNED);
				currentState = backlogState;
				addNoteToList(c.getNoteText());
				break;
			case PROCESS:
				currentState = processingState;
				addNoteToList(c.getNoteText());
				break;
			case COMPLETE:
				if (getTypeShortName().equals(T_KNOWLEDGE_ACQUISITION)) {
					currentState = doneState;
					addNoteToList(c.getNoteText());
				}
				else {
					throw new UnsupportedOperationException("Invalid transition.");
				}
				break;
			default:
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}
		
		@Override
		public String getStateName() {
			return PROCESSING_NAME;
			
		}
		
	}
	
	/**
	 * Inner concrete class which controls the Done state.
	 * @author clorejol
	 */
	public class DoneState implements TaskState {
		
		private DoneState() {
			
		}
		
		@Override
		public void updateState(Command c) {
			switch(c.getCommand()) {
			case BACKLOG:
				setVerified("false");
				setOwner(UNOWNED);
				currentState = backlogState;
				addNoteToList(c.getNoteText());
				break;
			case PROCESS:
				setVerified("false");
				currentState = processingState;
				addNoteToList(c.getNoteText());
				break;
			default:
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}
		
		@Override
		public String getStateName() {
			return DONE_NAME;
			
		}
		
	}
	
	/**
	 * Inner concrete class which controls the Rejected state.
	 * @author clorejol
	 */
	public class RejectedState implements TaskState {
		
		private RejectedState() {
			
		}
		
		@Override
		public void updateState(Command c) {
			switch(c.getCommand()) {
			case BACKLOG:
				currentState = backlogState;
				addNoteToList(c.getNoteText());
				break;
			default:
				throw new UnsupportedOperationException("Invalid transition.");
			}
		}
		
		@Override
		public String getStateName() {
			return REJECTED_NAME;
			
		}
		
	}
	
	/**
	 * Interface for states in the Task State Pattern.  All 
	 * concrete task states must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TaskState {
		
		/**
		 * Update the Task based on the given Command
		 * An UnsupportedOperationException is thrown if the Command is not a
		 * is not a valid action for the given state.  
		 * @param c Command describing the action that will update the Task
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}
	
	/**
	 * Enumeration which holds the different Type(s)
	 * @author Carson Orejola
	 */
	public enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION } 
			
}

