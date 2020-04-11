
package controller;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.UserTransaction;

import cart.Cart;

@ManagedBean(value = "cartController")
@SessionScoped
public class CartController {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	private DataModel<Cart> carts;

	private List<Cart> allCarts;

	private int amount;
	/*
	 * private Integer eventID;
	 * 
	 * private String eventDescription;
	 * 
	 * private double unitPrice;
	 * 
	 * private double totalPrice;
	 */

	public String registerCart(Integer eid, String edes, double up, int am) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("läuft"));
		try {
			utx.begin();
			Cart newCart = addCart(eid, edes, up, am);
			em.persist(newCart);
			utx.commit();
			
			context.addMessage(null, new FacesMessage("Die Tickets für " + newCart.getEventDescription()
					+ " wurden erfolgreich ihrem Warenkorb hinzugefügt!"));
			generateCart();
			return "newCart";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newCart";
	}

	private Cart addCart(Integer eid, String edes, double up, int am) {
		Cart newCart = new Cart();
		newCart.setEventID(eid);
		newCart.setEventDescription(edes);
		newCart.setUnitPrice(up);
		newCart.setAmount(am);
		newCart.setTotalPrice(Math.round(up * am));
		return newCart;
	}

	@SuppressWarnings({ "unchecked" })
	public String generateCart() {
		Query all = em.createQuery("Select t from Cart t");

		setAllCarts(all.getResultList());
		/*
		 * FacesContext context = FacesContext.getCurrentInstance();
		 * context.addMessage(null, new FacesMessage(allCarts.get(0).getPlace()));
		 */

		return "cart";
	}

	public DataModel<Cart> getCarts() {
		return carts;
	}

	public void setCarts(DataModel<Cart> carts) {
		this.carts = carts;
	}

	public List<Cart> getAllCarts() {
		return allCarts;
	}

	public void setAllCarts(List<Cart> allCarts) {
		this.allCarts = allCarts;
	}
/*
	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
*/
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
/*
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
*/
}
