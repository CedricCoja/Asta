
package cart;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = "SelectTicket", query = "Select t from Cart t")
@Entity
@Table(name = "TICKET")
public class Cart implements Serializable {
	private static final long serialVersionUID = 7719675659305229219L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Cart_ID", nullable = false, unique = true)
	private Integer cartID;

	@Column(name = "Event_ID", nullable = false)
	private Integer eventID;

	@Column(name = "Event_Description")
	private String eventDesignation;
	
	@Column(name = "Einzelpreis")
	private double unitPrice;

	@Column(name = "Anzahl")
	private int amount;
	
	@Column(name = "Gesamtpreis")
	private double totalPrice;

	public Cart() {
	}

	public Cart(Integer eventID, String eventDescription, double unitPrice, int amount, double totalPrice) {

		this.eventID = eventID;
		this.eventDesignation = eventDesignation;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.totalPrice = totalPrice;
	}

	public Integer getCartID() {
		return cartID;
	}

	public void setCartID(Integer cartID) {
		this.cartID = cartID;
	}

	public Integer getEventID() {
		return eventID;
	}

	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}

	
	public String getEventDesignation() {
		return eventDesignation;
	}

	public void setEventDesignation(String eventDesignation) {
		this.eventDesignation = eventDesignation;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
