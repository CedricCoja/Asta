
package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import user.User;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {
  private User user;
  private String email;
  private String password;
  private String firstName;
  private int userID;

  @PersistenceContext
  private EntityManager em;
  @Resource
  private UserTransaction utx;

  @SuppressWarnings({ "unchecked" })
  public String login() {

    Query login = em.createQuery("select u from User u " + "where u.email = :email and u.password = :password ");
    login.setParameter("email", email);
    login.setParameter("password", password);

    List<User> users = login.getResultList();

    if (users.size() == 1) {
      user = users.get(0);
      firstName = user.getFirstName();
      setUserID(user.getUserID());
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage("Successful", "Willkommen " + firstName));
      context.getExternalContext().getSessionMap().put("user", user);
      UserController.setUser(user);
      return "/home.xhtml?faces-redirect=true";
    } else {
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage("FAILURE", "E-Mail oder Passwort falsch! - Bitte versuchen Sie es erneut!"));
      return null;
    }
  }

  public boolean loggedIn() {
    if (user != null) {
      return true;
    }
    return false;
  }

  // public boolean isAdmin() {
  // if (user.getRole() == Role.ADMIN) {
  // return true;
  // }
  // return false;
  // }

  public String logout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "/index.xhtml?faces-redirect=true";
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

}