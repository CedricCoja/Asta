package user;
public class User extends UserBase{
	private Integer id;
	private UserStatus status;
	private String firstName;
	private String lastName;
	private String email;
	private boolean student;
	private LoginData loginData;
	
	
	public LoginData getLoginData() {
		return loginData;
	}
	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
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
	public boolean isStudent() {
		return student;
	}
	public void setStudent(boolean student) {
		this.student = student;
	}
}
	
