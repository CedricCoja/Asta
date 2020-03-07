
package event;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
  private String bezeichnung;

  @Size(min = 3, max = 255)
  @Column(name = "Beschreibung")
  private String beschreibung;

  @Size(min = 3, max = 20)
  @Column(name = "Veranstaltungsort")
  private String place;

  @Temporal(TemporalType.DATE)
  @Column(name = "date")
  private Date date;

  @Column(name = "time")
  private String time;

  public Event(String bezeichnung, String beschreibung, String place, Date date, String time) {
    this.bezeichnung = bezeichnung;
    this.beschreibung = beschreibung;
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

  public String getBezeichnung() {
    return bezeichnung;
  }

  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public void setBeschreibung(String beschreibung) {
    this.beschreibung = beschreibung;
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

  public String gettime() {
    return time;
  }

  public void settime(String time) {
    this.time = time;
  }
}
