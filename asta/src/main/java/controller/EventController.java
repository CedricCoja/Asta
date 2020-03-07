
package controller;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.Size;

import event.Event;

@ManagedBean(value = "eventController")
@SessionScoped
public class EventController {

	@Size(min = 3, max = 20)
	private String designation;

	@Size(min = 3, max = 255)
	private String description;

	@Size(min = 3, max = 20)
	private String place;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "time")
	private String time;

	@PersistenceContext
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	private DataModel<Event> events;
	
	private List<Event> allEvents;

	public String registerEvent() {
		try {
			utx.begin();
			Event event = addEvent();
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

	private Event addEvent() {
		Event event = new Event();
		event.setDescription(description);
		event.setDesignation(designation);
		event.setDate(date);
		event.setPlace(place);
		event.setTime(time);
		return event;
	}
	
	@SuppressWarnings({ "unchecked" })
	public String generateEvents(){
		Query all = em.createQuery("select e from Event e");

		setAllEvents(all.getResultList());
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(allEvents.get(0).getPlace()));

		return "allFeten";
	}
	
	public List<Event> getAllEvents() {
		return allEvents;
	}

	public void setAllEvents(List<Event> allEvents) {
		this.allEvents = allEvents;
	}

	public DataModel<Event> getEvents() {
		return events;
	}

	public void setEvents(DataModel<Event> events) {
		this.events = events;
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

}
