
package event;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@NamedQuery(name = "SelectEvent", query = "Select e from Event e")
@Entity
@Table(name = "EVENT")
public class Event implements Serializable {

  private static final long serialVersionUID = 4031966842159109874L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Event_ID", nullable = false, unique = true)
  private Integer eventID;

  @Size(min = 3, max = 20)
  @Column(name = "Bezeichnung")
  private String designation;

  @Size(min = 3, max = 255)
  @Column(name = "Beschreibung")
  private String description;

  @Size(min = 3, max = 20)
  @Column(name = "Veranstaltungsort")
  private String place;

  @Temporal(TemporalType.DATE)
  @Column(name = "date")
  private Date date;

  @Column(name = "time")
  private String time;

  public Event(String designation, String description, String place, Date date, String time) {
    this.designation = designation;
    this.description = description;
    this.place = place;
    this.date = date;
    this.time = time;
  }

  public Event() {
    //
  }

  public Integer getEventID() {
    return eventID;
  }

  public void setEventID(Integer eventID) {
    this.eventID = eventID;
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

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
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
}
