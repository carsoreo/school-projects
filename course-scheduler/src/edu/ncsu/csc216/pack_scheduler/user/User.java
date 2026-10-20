package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * Represents a user in the registration manager
 * system.  Handles general getters and setters, such as getting the student
 * name and the student id.
 * @author angodwi2
 *
 */
public abstract class User {

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's unique ID */
	private String id;
	/** Student's email address */
	private String email;
	/** Student's hashed password */
	private String password;

	/**
	 * Constructor for the User class
	 * @param firstName is the first name
	 * @param lastName is the last name
	 * @param id is the id
	 * @param email is the email
	 * @param password is the password
	 */
	
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	

	/**
	 * Gets the student's first name.
	 * @return the student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the student's last name.
	 * @return the student's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the student's ID.
	 * @return the student's ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the student's email.
	 * @return the student's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the student's hashed password.
	 * @return the student's hashed password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student's first name.
	 * @param firstName the student's first name, may not be null or empty
	 * @throws IllegalArgumentException	if the first name is invalid
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)){
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name.
	 * @param lastName the student's first name, may not be null or empty
	 * @throws IllegalArgumentException if the last name is invalid
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the student's ID.
	 * @param id the student's ID, may not be null or empty
	 * @throws IllegalArgumentException if the ID is invalid
	 */
	private void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Sets the student's email.
	 * @param email the student's email, may not be null or empty,
	 *              must contain a '.' character after the first '@' character
	 * @throws IllegalArgumentException if the email is invalid
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.lastIndexOf('.') < email.indexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Sets the student's hashed password.
	 * @param password the student's hashed password, may not be null or empty
	 * @throws IllegalArgumentException if the password is invalid
	 */
	public void setPassword(String password) {
		if (password ==  null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

}