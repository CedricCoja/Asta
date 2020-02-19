
package controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

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

}