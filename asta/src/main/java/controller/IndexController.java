
package controller;

import java.util.Date;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import event.Event;
import user.Role;
import user.Status;
import user.User;

@ManagedBean(value = "indexController")
@SessionScoped
public class IndexController {

  /*
   * Der Händler, der zu Beginn die Nutzer und Veranstaltungen erstellt,
   * die von vorn herein in der Datenbank gespeichert werden sollen
   */

  @PersistenceContext
  private EntityManager em;
  @Resource
  private UserTransaction utx;

  @PostConstruct
  public void init() {
    try {
      Query admin = em.createQuery("select u from User u where u.email = 'admin@asta.de'");
      Query event = em.createQuery("select e from Event e where e.designation ='Weserfähre'");
      if (admin.getResultList().size() == 0) {
        utx.begin();
        User user = addAdmin();
        em.persist(user);
        utx.commit();
      }
      if (event.getResultList().size() == 0) {
        utx.begin();
        Event newEvent = addEvent();
        em.persist(newEvent);
        utx.commit();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("deprecation")
  private Event addEvent() {
    Event event = new Event();
    event.setDesignation("Weserfähre");
    event.setDescription("Party auf einer Fähre");
    event.setPlace("Fährhaus");
    event.setDate(new Date(120, 4, 14));
    event.setPrice(10.00);
    event.setTime("19:30");
    return event;
  }

  private User addAdmin() {
    User user = new User();
    user.setFirstName("admin");
    user.setLastName("admin");
    user.setEmail("admin@asta.de");
    user.setPassword(RegisterController.bcryptHash("admin123"));
    user.setRole(Role.ADMIN);
    user.setStatus(Status.STUDENT);
    return user;
  }

  public String register() {
    return "register";
  }

  public String login() {
    return "logintry";
  }

  public String events() {
    return "allFeten";
  }

  public String home() {
    return "home";
  }
}
