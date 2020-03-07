
package controller;

import java.util.GregorianCalendar;

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
import javax.transaction.SystemException;
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
	private Event saveEntity = new Event();

	@PostConstruct
	public void init() {
		try {
			utx.begin();
		} catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		em.persist(new Event("Tour", "ganz coole Tour eigentlich", "Bremerhaven",
				new GregorianCalendar(1993, 01, 06).getTime(), "19:30"));
		em.persist(new Event("Orkie", "mit ganz viel Orkan", "Kistnerstraße",
				new GregorianCalendar(2010, 01, 06).getTime(), "10:45"));

		events = new ListDataModel<Event>();

		events.setWrappedData(em.createNamedQuery("SelectEvent").getResultList());

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

	public String newEvent() {
		saveEntity = new Event();
		return "newEvent";
	}

	public String saveEvent() throws Throwable, SystemException {
		utx.begin();
		saveEntity = em.merge(saveEntity);
		em.persist(saveEntity);
		events.setWrappedData(em.createNamedQuery("SelectEvent").getResultList());
		utx.commit();
		return "allEvent";
	}

	public DataModel<Event> getEvents() {
		return events;
	}

	public void setEvents(DataModel<Event> events) {
		this.events = events;
	}

	public Event getSaveEntity() {
		return saveEntity;
	}

	public void setSaveEntity(Event saveEntity) {
		this.saveEntity = saveEntity;
	}
}
