
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

	@Column(name = "Bruttopreis")
	private double price;

	@Column(name = "Anzahl")
	private int amount;

	public Cart() {
	}

	public Cart(Integer eventID, double price, int amount) {

		this.eventID = eventID;
		this.price = price;
		this.amount = amount;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
