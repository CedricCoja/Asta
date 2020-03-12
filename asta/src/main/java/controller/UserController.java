
package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.Size;

import user.User;

@ManagedBean
@SessionScoped
public class UserController {

  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  private DataModel<User> users;
  private List<User> allUser;

  @Size(min = 3, max = 30)
  private String firstName;
  @Size(min = 3, max = 30)
  private String lastName;
  @Size(min = 6, max = 30)
  private String email;
  @Size(min = 3, max = 12)
  private String password;

  private int userID;

  private User user = new User();

  // public String deleteProfil() throws Throwable, SystemException {
  // User user;
  // em.remove(user);
  // users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
  // return "index";
  // }

  public String saveProfil() {

    // System.out.println(userID);
    // System.out.println(firstName);
    try {
      // userID = LoginController.getUserID();
      User tmpuser = getUser();
      utx.begin();
      tmpuser = em.merge(tmpuser);
      users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
      utx.commit();
    } catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    return "profile";
  }

  @SuppressWarnings({ "unchecked" })
  public String generateUsers() {
    Query all = em.createQuery("select u from User u");

    setAllUser(all.getResultList());
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("In der Tabelle sehen Sie " + "alle registrierten Nutzer."));

    return "allUser";
  }

  public DataModel<User> getUsers() {
    return users;
  }

  public void setUsers(DataModel<User> users) {
    this.users = users;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  public List<User> getAllUser() {
    return allUser;
  }

  public void setAllUser(List<User> allUser) {
    this.allUser = allUser;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}