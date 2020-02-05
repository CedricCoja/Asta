
package user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@NamedQuery(name = "SelectUser", query = "Select u from User u")
@Entity
@Table(name = "USER")

@ManagedBean
@SessionScoped
public class User implements Serializable {

  private static final long serialVersionUID = 4467006451749712622L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "User_ID", nullable = false, unique = true)
  private Integer userID;

  @Column(name = "Rolle")
  private String role;

  @Column(name = "Status")
  private String status;

  @Size(min = 3, max = 20)
  @Column(name = "Vorname")
  private String firstName;

  @Size(min = 3, max = 20)
  @Column(name = "Nachname")
  private String lastName;

  @Size(min = 3, max = 20)
  @Column(name = "E-Mail")
  private String email;

  @Size(min = 3, max = 20)
  @Column(name = "Passwort")
  private String password;

  //  private boolean student;

  //  private LoginData loginData;
  //
  //  public LoginData getLoginData() {
  //    return loginData;
  //  }
  //
  //  public void setLoginData(LoginData loginData) {
  //    this.loginData = loginData;
  //  }

  public Integer getId() {
    return userID;
  }

  public void setId(Integer id) {
    this.userID = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
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

  //  public boolean isStudent() {
  //    return student;
  //  }
  //
  //  public void setStudent(boolean student) {
  //    this.student = student;
  //  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
