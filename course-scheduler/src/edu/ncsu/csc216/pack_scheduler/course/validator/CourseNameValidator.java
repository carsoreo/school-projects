/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Validates the given course
 * name using an internal FSM.
 * @author Wil Crowder
 */
public class CourseNameValidator {
	
	/** Counter for letters in course name **/
	private int letterCounter = 0;
	
	/** Counter for digits in course name **/
	private int digitCounter = 0;
	
	/** The letter state of the FSM **/
	private State stateLetter = new LetterState();
	
	/** The number state of the FSM **/
	private State stateNumber = new NumberState();
	
	/** The initial state of the FSM **/
	private State stateInitial = new InitialState();
	
	/** The suffix state of the FSM **/
	private State stateSuffix = new SuffixState();
	
	/** The current state of the FSM **/
	private State currentState;

	/**
	 * Checks whether the course name is
	 * valid using the FSM.
	 * @param courseName The name of the course
	 * @return true if the course name is valid
	 * @throws InvalidTransitionException if an illegal transition is attempted.
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		letterCounter = 0;
		digitCounter = 0;
		currentState = stateInitial;
		int charIndex = 0;
		char c;
		while (charIndex < courseName.length()) {
			c = courseName.charAt(charIndex);
			if (Character.isLetter(c)) {
				try {
					currentState.onLetter();
				} catch (InvalidTransitionException e) {
					throw e;
				}
			}
			else if (Character.isDigit(c)) {
				try {
					currentState.onDigit();
				} catch (InvalidTransitionException e) {
					throw e;
				}
			}
			else {
				try {
					currentState.onOther();
				} catch (InvalidTransitionException e) {
					throw e;
				}
			}
			charIndex++;
		}
		if (currentState == stateNumber) {
			return digitCounter == 3;
		}
		else {
			return currentState == stateSuffix;
		}
	}
	
	/**
	 * Abstract state class upon which
	 * the other four state extend. Throws
	 * exception on character other than a
	 * letter or number.
	 * @author Wil Crowder
	 */
	public abstract class State {
		
		/**
		 * Function if next char is a letter.
		 * @throws InvalidTransitionException if invalid transition
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		
		/**
		 * Function if next char is a number.
		 * @throws InvalidTransitionException if invalid transition
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * If the next character is not a 
		 * letter or a number, throws
		 * an exception
		 * @throws InvalidTransitionException if next char is not letter or number (always)
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * The state of being a letter.
	 * If there are a valid number of letters,
	 * state can transition to adding another letter or
	 * add a digit and switch to digit state. If the number
	 * of letters is invalid an exception is thrown.
	 * @author Wil Crowder
	 */
	public class LetterState extends State {
		
		/** Max number of letters in a prefix **/
		private static final int MAX_PREFIX_LETTERS = 4;
		
		private LetterState() {
			
		}
		
		/**
		 * Stays in letter state if prefix length would stay
		 * within valid range, otherwise throws an exception.
		 */
		public void onLetter() throws InvalidTransitionException {
			if (letterCounter >= 1 && letterCounter < MAX_PREFIX_LETTERS) {
				currentState = stateLetter;
				letterCounter++;
			}
			else if (letterCounter >= MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		
		/**
		 * Transitions to the number state.
		 */
		public void onDigit() {
			currentState = stateNumber;
			digitCounter++;
		}
	}
	
	/**
	 * Throws exception on any transition, as no more
	 * characters can exist after the one letter suffix.
	 * @author Wil Crowder
	 */
	public class SuffixState extends State {
		
		private SuffixState() {
			
		}
		
		/**
		 * Throws exception, no more characters can exist.
		 * @throws InvalidTransitionException on any transition.
		 */
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		
		/**
		 * Throws exception, no more characters can exist.
		 * @throws InvalidTransitionException on any transition.
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
	
	/**
	 * The initial state of the FSM.
	 * If next character is letter the state changes to
	 * the letter state and letter counter is increased by 1.
	 * @author Wil Crowder
	 */
	public class InitialState extends State {
		
		private InitialState() {
			
		}
		
		/**
		 * Updates state to letter and increases letter counter.
		 */
		public void onLetter() {
			currentState = stateLetter;
			letterCounter++;
		}
		
		/**
		 * Invalid transition, throws exception.
		 * @throws InvalidTransitionException if the course doesn't start with a letter.
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	/**
	 * The number state of the FSM.
	 * On letter, transitions to suffix state if 
	 * there are a valid number of digits, otherwise throws
	 * exception. On digit, stays in number state and increases
	 * digit counter unless there are an invalid number of digits.
	 * @author Wil Crowder
	 */
	public class NumberState extends State {
		
		/** Required number of digits in the course numer **/
		private static final int COURSE_NUMBER_LENGTH = 3;
	
		private NumberState() {
			
		}
		
		/**
		 * Updates state to suffix unless digit
		 * counter is in an invalid range.
		 * @throws InvalidTransitionException if digit counter is in invalid range.
		 */
		public void onLetter() throws InvalidTransitionException {
			if (digitCounter < COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
			else if (digitCounter > COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			else {
				currentState = stateSuffix;
			}
		}
		
		/**
		 * Stays in number state if digit counter
		 * would stay in valid range. Otherwise throws
		 * InvalidTransitionException.
		 * @throws InvalidTransitionException if digit counter would stay in valid range.
		 */
		public void onDigit() throws InvalidTransitionException {
			if (digitCounter >= COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			else {
				currentState = stateNumber;
				digitCounter++;
			}
		}
	}
}
