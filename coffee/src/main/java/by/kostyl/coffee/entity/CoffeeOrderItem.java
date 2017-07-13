package by.kostyl.coffee.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "CoffeeOrderItem")
public class CoffeeOrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "quantity")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "COI_CO"))
	private CoffeeOrder coffeeOrder;
	@ManyToOne
	@JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "COI_CT"))
	private CoffeeType coffeeType;

	public CoffeeOrder getCoffeeOrder() {
		return coffeeOrder;
	}

	public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
		this.coffeeOrder = coffeeOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CoffeeType getCoffeeType() {
		return coffeeType;
	}

	public void setCoffeeType(CoffeeType coffeeType) {
		this.coffeeType = coffeeType;
	}

	public CoffeeOrderItem() {
		super();
	}

	public CoffeeOrderItem(int quantity, CoffeeOrder coffeeOrder, CoffeeType coffeeType) {
		super();
		this.quantity = quantity;
		this.coffeeOrder = coffeeOrder;
		this.coffeeType = coffeeType;
	}

}
