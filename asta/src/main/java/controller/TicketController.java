
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

import ticket.Ticket;

@ManagedBean(value = "ticketController")
@SessionScoped
public class TicketController {

  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  private DataModel<Ticket> tickets;
  private Ticket saveEntity = new Ticket();

  @PostConstruct
  public void init() {
    try {
      utx.begin();
    } catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    tickets = new ListDataModel<Ticket>();
    tickets.setWrappedData(em.createNamedQuery("SelectTicket").getResultList());

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

  public String newTicket() {
    saveEntity = new Ticket();
    return "newTicket";
  }

  public String saveTicket() {
    try {
      utx.begin();
    } catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    saveEntity = em.merge(saveEntity);
    em.persist(saveEntity);
    tickets.setWrappedData(em.createNamedQuery("SelectTicket").getResultList());
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

  public DataModel<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(DataModel<Ticket> tickets) {
    this.tickets = tickets;
  }

  public Ticket getSaveEntity() {
    return saveEntity;
  }

  public void setSaveEntity(Ticket saveEntity) {
    this.saveEntity = saveEntity;
  }

}
