
package controller;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
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
  public void init() throws Throwable, SystemException {
    utx.begin();
    tickets = new ListDataModel<Ticket>();
    tickets.setWrappedData(em.createNamedQuery("SelectTicket").getResultList());
    utx.commit();
  }

  public String newTicket() {
    saveEntity = new Ticket();
    return "newTicket";
  }

  public String saveTicket() throws Throwable, SystemException {
    utx.begin();
    saveEntity = em.merge(saveEntity);
    em.persist(saveEntity);
    tickets.setWrappedData(em.createNamedQuery("SelectTicket").getResultList());
    utx.commit();
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
