
package controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.Size;

import user.Role;
import user.User;

@ManagedBean
@SessionScoped
public class UserController {

<<<<<<< HEAD
	@PersistenceContext
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	private DataModel<User> users;
	private User saveEntity = new User();

	@PostConstruct
	public void init() {
		try {
			utx.begin();
		} catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		users = new ListDataModel<User>();

		em.persist(new User("Janek", "Bredehöft", "bredehoeft.janek@gmail.com", "hallo123"));
		em.persist(new User("Cedric", "Coja", "ceddyderteddy@poposex.com", "schalke04"));
		em.persist(new User("Louna", "Fehder", "lfehder@hs-bremerhaven.de", "passwort"));

		users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
		try {
			try {
				utx.commit();
			} catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException | IllegalStateException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
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
			System.out.println(saveEntity);
			saveEntity = em.merge(saveEntity);
			em.persist(saveEntity);
			users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
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

	// public String saveUser() {
	// try {
	// utx.begin();
	// saveEntity = em.merge(saveEntity);
	// em.persist(saveEntity);
	// user.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
	// utx.commit();
	// } catch (SecurityException | IllegalStateException |
	// HeuristicRollbackException | HeuristicMixedException |
	// javax.transaction.NotSupportedException | javax.transaction.SystemException |
	// javax.transaction.RollbackException e) {
	// e.printStackTrace();
	// }
	// return "allUser";
	// }

	public String saveProfil() {
		try {
			utx.begin();
			saveEntity = em.merge(saveEntity);
			em.persist(saveEntity);
			users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
		return "profil";
	}
=======
  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  private DataModel<User> users;
  private User saveEntity = new User();

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

  //  public String register() {
  //    try {
  //      String valmail = "email";
  //      Query valuser = em.createQuery("select u from User u " + "where u.email = :" + valmail);
  //      String mail = null;
  //      valuser.setParameter(valmail, mail);
  //      if (valuser.getResultList().size() == 0) {
  //        utx.begin();
  //        em.persist(new User(firstName, lastName, email, password, Role.USER));
  //        users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
  //        utx.commit();
  //      }
  //    } catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
  //      e.printStackTrace();
  //    }
  //    return "logintry";
  //  }

  //  public String deleteProfil() throws Throwable, SystemException {
  //    User user;
  //    em.remove(user);
  //    users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
  //    return "index";
  //  }

  public String saveProfil() {

    //    System.out.println(userID);
    //    System.out.println(firstName);
    try {
      // userID = LoginController.getUserID();
      user = em.find(User.class, userID);
      utx.begin();
      user = em.merge(user);
      em.persist(user);
      users.setWrappedData(em.createNamedQuery("SelectUser").getResultList());
      utx.commit();
    } catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    return "profile";
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
>>>>>>> master

}