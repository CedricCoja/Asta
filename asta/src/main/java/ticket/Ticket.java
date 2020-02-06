
package ticket;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = "SelectTicket", query = "Select t from Ticket t")
@Entity
@Table(name = "TICKET")

public class Ticket implements Serializable {

  private static final long serialVersionUID = 7719675659305229219L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Ticket_ID", nullable = false, unique = true)
  private Integer ticketID;

  @Column(name = "Bezeichnung")
  private String bezeichnung;

  @Column(name = "Beschreibung")
  private String beschreibung;

  @Column(name = "Bruttopreis")
  private double bruttopreis;

  @Column(name = "Anzahl")
  private int anzahl;

  public Ticket() {
  }

  public Ticket(String bezeichnung, String beschreibung, double bruttopreis, int anzahl) {

    this.bezeichnung = bezeichnung;
    this.beschreibung = beschreibung;
    this.bruttopreis = bruttopreis;
    this.anzahl = anzahl;
  }

  public Integer getTicketID() {
    return ticketID;
  }

  public void setTicketID(Integer ticketID) {
    this.ticketID = ticketID;
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

  public double getBruttopreis() {
    return bruttopreis;
  }

  public void setBruttopreis(double bruttopreis) {
    this.bruttopreis = bruttopreis;
  }

  public int getAnzahl() {
    return anzahl;
  }

  public void setAnzahl(int anzahl) {
    this.anzahl = anzahl;
  }
}
