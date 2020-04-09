
package controller;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.UserTransaction;
import javax.validation.constraints.Size;

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

  @Size(min = 3, max = 20)
  private String designation;

  @Size(min = 3, max = 255)
  private String description;

  @Size(min = 3, max = 20)
  private String place;

  @Temporal(TemporalType.DATE)
  private Date date;

  private String time;

  private double price;

  public String registerEvent() {
    try {
      utx.begin();
      Event newEvent = addEvent();
      em.persist(newEvent);
      utx.commit();
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage(null, new FacesMessage("Das Event " + newEvent.getDesignation() + " wurde erfolgreich erstellt!"));
      generateEvents();
      return "newEvent";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "newEvent";
  }

  private Event addEvent() {
    Event newEvent = new Event();
    newEvent.setDescription(description);
    newEvent.setDesignation(designation);
    newEvent.setDate(date);
    newEvent.setPlace(place);
    newEvent.setTime(time);
    return newEvent;
  }

  //  public String deleteEvent() {
  //
  //    try {
  //      event = getEvent();
  //      utx.begin();
  //      event = em.merge(event);
  //      em.remove(event);
  //      utx.commit();
  //    } catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
  //      e.printStackTrace();
  //    }
  //    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
  //    return "allFeten";
  //  }
  //
  //  public String saveEvent() {
  //
  //    try {
  //      event = getEvent();
  //      utx.begin();
  //      event = em.merge(event);
  //      utx.commit();
  //    } catch (SecurityException | IllegalStateException | HeuristicRollbackException | HeuristicMixedException | javax.transaction.NotSupportedException | javax.transaction.SystemException | javax.transaction.RollbackException e) {
  //      e.printStackTrace();
  //    }
  //    return "allFeten";
  //  }

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

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
