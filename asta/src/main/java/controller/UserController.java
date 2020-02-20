
package controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import user.Role;
import user.User;

@ManagedBean
@SessionScoped
public class UserController {

  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  private DataModel<User> users;
  private User saveEntity = new User();

  private String firstName;
  private String lastName;
  private String email;
  private String password;

  @PostConstruct
  public void init() {
    try {
      utx.begin();
    } catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
      e.printStackTrace();
    }
    users = new ListDataModel<User>();

    em.persist(new User("Janek", "Bredehöft", "bredehoeft.janek@gmail.com", "hallo123", Role.ADMIN));
    em.persist(new User("Cedric", "Coja", "ceddyderteddy@poposex.com", "schalke04", Role.ADMIN));
    em.persist(new User("Louna", "Fehder", "lfehder@hs-bremerhaven.de", "passwort", Role.ADMIN));

    users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
    try {
      try {
        utx.commit();
      } catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
        e.printStackTrace();
      }
    } catch (SecurityException | IllegalStateException | HeuristicMixedException | HeuristicRollbackException e) {
      e.printStackTrace();
    }
  }

  public String register() {
    try {
      String valmail = "email";
      Query valuser = em.createQuery("select u from User u " + "where u.email = :" + valmail);
      String mail = null;
      valuser.setParameter(valmail, mail);
      if (valuser.getResultList().size() == 0) {
        utx.begin();
        em.persist(new User(firstName, lastName, email, password, Role.USER));
        users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
        utx.commit();
      }
    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    return "logintry";
  }

  public String deleteProfil() throws Throwable, SystemException {
    saveEntity = users.getRowData();
    utx.begin();
    saveEntity = em.merge(saveEntity);
    em.remove(saveEntity);
    users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
    return "index";
  }

  public String saveProfil() {
    try {
      utx.begin();
      saveEntity = em.merge(saveEntity);
      em.persist(saveEntity);
      users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
      utx.commit();
    } catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    return "profil";
  }

  public DataModel<User> getUsers() {
    return users;
  }

  public void setUsers(DataModel<User> users) {
    this.users = users;
  }

  public User getSaveEntity() {
    return saveEntity;
  }

  public void setSaveEntity(User saveEntity) {
    this.saveEntity = saveEntity;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}