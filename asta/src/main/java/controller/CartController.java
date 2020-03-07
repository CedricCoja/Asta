
package controller;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
  private Cart saveEntity = new Cart();

  @PostConstruct
  public void init() {
    try {
      utx.begin();
    } catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    carts = new ListDataModel<Cart>();
    carts.setWrappedData(em.createNamedQuery("SelectTicket").getResultList());

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

  public String newCart() {
    saveEntity = new Cart();
    return "newTicket";
  }

  public String saveCart() {
    try {
      utx.begin();
    } catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    saveEntity = em.merge(saveEntity);
    em.persist(saveEntity);
    carts.setWrappedData(em.createNamedQuery("SelectTicket").getResultList());
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
    return "allEvent";
  }

  public DataModel<Cart> getCarts() {
    return carts;
  }

  public void setCarts(DataModel<Cart> carts) {
    this.carts = carts;
  }

  public Cart getSaveEntity() {
    return saveEntity;
  }

  public void setSaveEntity(Cart saveEntity) {
    this.saveEntity = saveEntity;
  }

}
