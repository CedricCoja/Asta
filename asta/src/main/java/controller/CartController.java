
package controller;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import cart.Cart;

@ManagedBean(value = "cartController")
@SessionScoped
public class CartController {

  /* Der Händler, der den Warenkorb verwaltet */

  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  private DataModel<Cart> carts;

  private List<Cart> allCarts;

  public String registerCart(Integer event_id, String event_des, double unit_price, int amount) {
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage("läuft"));
    try {
      utx.begin();
      Cart newCart = addCart(event_id, event_des, unit_price, amount);
      em.persist(newCart);
      utx.commit();

      context.addMessage(null, new FacesMessage("Die Tickets für " + newCart.getEventDesignation() + " wurden erfolgreich ihrem Warenkorb hinzugefügt!"));
      generateCart();
      return "newCart";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "newCart";
  }

  private Cart addCart(Integer event_id, String event_des, double unit_price, int amount) {
    Cart newCart = new Cart();
    newCart.setEventID(event_id);
    newCart.setEventDesignation(event_des);
    newCart.setUnitPrice(unit_price);
    newCart.setAmount(amount);
    newCart.setTotalPrice((double) Math.round(unit_price * amount * 100) / 100);
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
   * public Integer getEventID() {
   * return eventID;
   * }
   * public void setEventID(Integer eventID) {
   * this.eventID = eventID;
   * }
   * public String getEventDescription() {
   * return eventDescription;
   * }
   * public void setEventDescription(String eventDescription) {
   * this.eventDescription = eventDescription;
   * }
   * public double getUnitPrice() {
   * return unitPrice;
   * }
   * public void setUnitPrice(double unitPrice) {
   * this.unitPrice = unitPrice;
   * }
   */

  /*
   * public double getTotalPrice() {
   * return totalPrice;
   * }
   * public void setTotalPrice(double totalPrice) {
   * this.totalPrice = totalPrice;
   * }
   */
}
