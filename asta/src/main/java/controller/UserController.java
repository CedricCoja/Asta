
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.UserTransaction;

import user.User;

@ManagedBean
@SessionScoped
public class UserController {

  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  private List<User> allUser;

  private static User user;

  public String deleteProfil() {

    try {
      user = getUser();
      utx.begin();
      user = em.merge(user);
      em.remove(user);
      utx.commit();
    } catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "/index.xhtml?faces-redirect=true";
  }

  public String saveProfil() {

    try {
      user = getUser();
      utx.begin();
      user = em.merge(user);
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

  public List<User> getAllUser() {
    return allUser;
  }

  public void setAllUser(List<User> allUser) {
    this.allUser = allUser;
  }

  public static void setUser(User login) {
    user = login;
  }

  public User getUser() {
    return user;
  }
}