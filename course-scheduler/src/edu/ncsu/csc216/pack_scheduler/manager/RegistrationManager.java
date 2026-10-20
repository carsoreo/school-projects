package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The registration manager class is responsible for
 * actions from the user.  These responsibilities include
 * user login and logouts, creating the registrar, and includes
 * an inner class reigstrar that extends the user class
 * 
 * @author angodwi2
 *
 */
public class RegistrationManager {
	
	/** instance of registration manager */
	private static RegistrationManager instance;
	/** Course catalog */
	private CourseCatalog courseCatalog;
	/** The student directory */
	private StudentDirectory studentDirectory;
	/** the registrar user */
	private User registrar;
	/** the current user */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** The properties file */
	private static final String PROP_FILE = "registrar.properties";
	/** The state that holds the FacultyDirectory */
	private FacultyDirectory facultyDirectory;

	/**
	 * Creates an empty registrar, new course catalog, and
	 * new student directory 
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}
	
	/**
	 * Creates a new registrar using the properties file
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Hash pw for the registrar manager
	 * @param pw is the password
	 * @return A new string of the password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Gets an instance of Registration Manager
	 * @return instance the instance of registration manager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Gets the course catalog
	 * @return courseCatalog the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Gets the student directory
	 * @return studentDirectory is the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Gets the faculty directory
	 * @return facultyDirectory directory for faculty.
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	
	/**
	 * Adds a faculty to a course by adding a Course object
	 * to a FacultySchedule of a given Faculty. Only executes if
	 * currentUser is not null and is the registrar.
	 * 
	 * @param c Course object to be added to the FacultySchedule.
	 * @param f Faculty object to get the FacultySchedule from.
	 * @return true if it was added successfully.
	 * @throws IllegalArgumentException if user is not registrar.
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().addCourseToSchedule(c);
			return true;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Removes a faculty to a course by removing a Course object
	 * to a FacultySchedule of a given Faculty. Only executes if
	 * currentUser is not null and is the registrar.
	 * 
	 * @param c Course object to be removed from the FacultySchedule.
	 * @param f Faculty object to get the FacultySchedule from.
	 * @return true if it was removed successfully.
	 * @throws IllegalArgumentException if the user is not registrar
	 * or null.
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().removeCourseFromSchedule(c);
			c.setInstructorId(null);
			return true;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Resets the schedule of a given Faculty parameter. Calls a method
	 * within Faculty which resets it's schedule. Only executes if
	 * currentUser is not null and is the registrar.
	 * 
	 * @param f Faculty with schedule to be reset.
	 */
	public void resetFacultySchedule(Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().resetSchedule();
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Allows the user to login using their student
	 * id or staff id and password
	 * @param id is the id of the user
	 * @param password is the password of the user
	 * @return true if the user is in the registrar
	 */
	public boolean login(String id, String password) {
		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);
		if(currentUser != null) {
			return false;
		}
		
		//Student login
		if(s != null) {
			if (!(s instanceof Student)) {
				return false;
			}
			String[][] sd = studentDirectory.getStudentDirectory();
			boolean inDirectory = false;
			for(int i = 0; i < sd.length; i++) {

				if (sd[i][2].equals(s.getId())){
					inDirectory = true;
				}
			}
			if(!inDirectory) {
				return false;
			}
			try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
				else {
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}	
		}
		if(currentUser != null) {
			return false;
		}
		
		//Faculty login
		if(f != null) {
			if (!(f instanceof Faculty)) {
				return false;
			}
			String[][] fd = facultyDirectory.getFacultyDirectory();
			boolean inDirectory = false;
			for(int i = 0; i < fd.length; i++) {

				if (fd[i][2].equals(f.getId())){
					inDirectory = true;
				}
			}
			if(!inDirectory) {
				return false;
			}
			try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			String localHashPW = new String(digest.digest());
				if (f.getPassword().equals(localHashPW)) {
					currentUser = f;
					return true;
				}
				else {
					return false;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}	
		}
		
		//Registrar login
		if (registrar.getId().equals(id)) {
				MessageDigest digest;
			try {
			digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
					return true;
			}
			else {
				return false;
			}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		throw new IllegalArgumentException("User doesn't exist.");
		//return false;
	}

	/**
	 * Allows the user to logout
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * gets the current user
	 * @return user the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * clears the data from the course catalog and directory
	 */
	public void clearData() {
		if(!(courseCatalog == null)) {
			courseCatalog.newCourseCatalog();
		}
		if(!(studentDirectory == null)) {
			studentDirectory.newStudentDirectory();
		}
		if(!(facultyDirectory == null)) {
			facultyDirectory.newFacultyDirectory();
		}
		currentUser = null;
	}
	
	/**
	 * Registrar class that extends the suer class to account for
	 * users in the registrar
	 * @author angodwi2
	 *
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * @param firstName is first name
		 * @param lastName is last name
		 * @param id is the id 
		 * @param email is the email
		 * @param hashPW is the hashed password
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
}
