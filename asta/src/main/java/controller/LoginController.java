package controller;


import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import user.User;

@ManagedBean(name="loginController")
@SessionScoped
public class LoginController{
	private User user;
	private String email;
	private String password;
	
	@PersistenceContext
	private EntityManager em;
	@Resource
	private UserTransaction utx;
	
	public void login() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successful",  "Your message: " + email) );
		};
		
	public String logout() {
		return null;
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