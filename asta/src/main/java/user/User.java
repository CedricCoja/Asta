
package user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NamedQuery(name = "SelectUser", query = "Select u from User u")
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 4467006451749712622L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserID", nullable = false, unique = true)
	private Integer userID;

	@Column(name = "Rolle")
	private Role role = Role.USER;

	@Column(name = "Status")
	private Status status;

	@Size(min = 3, max = 20)
	@Column(name = "Vorname")
	private String firstName;

	@Size(min = 3, max = 20)
	@Column(name = "Nachname")
	private String lastName;

	@Size(min = 3, max = 40)
	@Column(name = "EMail", unique = true)
	private String email;

	@Size(min = 3, max = 20)
	@Column(name = "Passwort")
	private String password;

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		if (email.contains("hs-bremerhaven.de")) {
			this.status = Status.STUDENT;
		} else {
			this.status = Status.EXTERN;
		}
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public static String bcryptHash(String enteredPW) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedpassword = passwordEncoder.encode(enteredPW);

		return hashedpassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
