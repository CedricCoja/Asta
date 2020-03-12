
package controller;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import user.Role;
import user.Status;
import user.User;

@ManagedBean(value = "indexController")
@SessionScoped
public class IndexController {

  @PersistenceContext
  private EntityManager em;
  @Resource
  private UserTransaction utx;

  @PostConstruct
  public void init() {
    try {
      Query admin = em.createQuery("select u from User u where u.email = 'admin@asta.de'");
      if (admin.getResultList().size() == 0) {
        utx.begin();
        User user = addAdmin();
        em.persist(user);
        utx.commit();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private User addAdmin() {
    User user = new User();
    user.setFirstName("admin");
    user.setLastName("admin");
    user.setEmail("admin@asta.de");
    user.setPassword("admin123");
    user.setRole(Role.ADMIN);
    user.setStatus(Status.STUDENT);
    return user;
  }

  public String register() {
    System.out.println("Hallo");
    return "allUser";
  }

  public String login() {
    return "logintry";
  }

  public String events() {
    return "allFeten";
  }

  public String aboutUs() {
    return "ueberuns";
  }
}
