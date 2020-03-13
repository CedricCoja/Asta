
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.UserTransaction;

import event.Event;

@ManagedBean(value = "eventController")
@SessionScoped
public class EventController {

  @PersistenceContext
  private EntityManager em;

  @Resource
  private UserTransaction utx;

  private DataModel<Event> events;

  private List<Event> allEvents;

  private static Event event;

  public String registerEvent() {
    try {
      utx.begin();
      event = getEvent();
      em.persist(event);
      utx.commit();
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage(event.getPlace()));
      return "newEvent";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "newEvent";
  }

  //  private Event addEvent() {
  //    event = new Event();
  //    event.setDescription(description);
  //    event.setDesignation(designation);
  //    event.setDate(date);
  //    event.setPlace(place);
  //    event.setTime(time);
  //    return event;
  //  }

  public String deleteEvent() {

    try {
      event = getEvent();
      utx.begin();
      event = em.merge(event);
      em.remove(event);
      utx.commit();
    } catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "allFeten";
  }

  public String saveEvent() {

    try {
      event = getEvent();
      utx.begin();
      event = em.merge(event);
      utx.commit();
    } catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
      e.printStackTrace();
    }
    return "allFeten";
  }

  @SuppressWarnings({ "unchecked" })
  public String generateEvents() {
    Query all = em.createQuery("select e from Event e");

    setAllEvents(all.getResultList());
    FacesContext context = FacesContext.getCurrentInstance();
    context.addMessage(null, new FacesMessage(allEvents.get(0).getPlace()));

    return "allFeten";
  }

  public DataModel<Event> getEvents() {
    return events;
  }

  public void setEvents(DataModel<Event> events) {
    this.events = events;
  }

  public List<Event> getAllEvents() {
    return allEvents;
  }

  public void setAllEvents(List<Event> allEvents) {
    this.allEvents = allEvents;
  }

  public Event getEvent() {
    return event;
  }

  public static void setEvent(Event newEvent) {
    event = newEvent;
  }

}
