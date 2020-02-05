
package controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.UserTransaction;
import javax.ws.rs.NotSupportedException;

import org.omg.CORBA.SystemException;

import user.User;

public class UserController {

  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  private DataModel<User> user;
  private User saveEntity = new User();

  @PostConstruct
  public void init() {
    try {
      utx.begin();
    } catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
      e.printStackTrace();
    }
    user = new ListDataModel<User>();
    user.setWrappedData(em.createNamedQuery("SelectUser").getResultList());

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

  public String newUser() {
    saveEntity = new User();
    return "registrieren";
  }

  public String register() {
    try {
      utx.begin();
      saveEntity = em.merge(saveEntity);
      em.persist(saveEntity);
      user.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
      utx.commit();
    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException | SystemException | NotSupportedException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    return "login";
  }

  public String saveUser() {
    try {
      utx.begin();
      saveEntity = em.merge(saveEntity);
      em.persist(saveEntity);
      user.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
      utx.commit();
    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException | SystemException | NotSupportedException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    return "allUser";
  }

  public String saveProfil() {
    try {
      utx.begin();
      saveEntity = em.merge(saveEntity);
      em.persist(saveEntity);
      user.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
      utx.commit();
    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException | SystemException | NotSupportedException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    return "index";
  }

}
