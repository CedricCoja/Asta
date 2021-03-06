
package controller;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import user.Role;
import user.Status;
import user.User;

@ManagedBean
@SessionScoped
public class RegisterController {

  /* Der Händler, der neue Benutzer erstellt */

  @Size(min = 3, max = 30)
  private String firstName;
  @Size(min = 3, max = 30)
  private String lastName;
  @Size(min = 6, max = 30)
  private String email;
  private String password;

  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  public String registerUser() {
    try {
      String search = "email";
      Query query = em.createQuery("select u from User u " + "where u.email = :" + search);
      query.setParameter(search, email);
      password = bcryptHash(password);
      if (query.getResultList().size() == 0) {
        utx.begin();
        User user = addUser();
        em.persist(user);
        utx.commit();
        return "logintry";
      } else {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("FAILURE", "Es existiert bereits ein Nutzer mit dieser E-Mail Adresse!"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "register";
  }

  public static String bcryptHash(String enteredPW) {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedpassword = passwordEncoder.encode(enteredPW);

    return hashedpassword;
  }

  private User addUser() {
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(Role.USER);
    if (email.contains("hs-bremerhaven.de")) {
      user.setStatus(Status.STUDENT);
    } else {
      user.setStatus(Status.EXTERN);
    }
    return user;
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

  public EntityManager getEm() {
    return em;
  }

  public void setEm(EntityManager em) {
    this.em = em;
  }

  public UserTransaction getUtx() {
    return utx;
  }

  public void setUtx(UserTransaction utx) {
    this.utx = utx;
  }

}
