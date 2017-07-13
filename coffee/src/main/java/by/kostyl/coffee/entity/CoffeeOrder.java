package by.kostyl.coffee.entity;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name = "CoffeeOrder")
public class CoffeeOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "order_date")
	private Date orderDate;
	@Column(name = "name")
	private String name;
	@Column(name = "delivery_address")
	private String address;
	@Column(name = "cost")
	private double cost;
	@OneToMany(mappedBy="coffeeOrder",cascade=CascadeType.ALL)
	private Set<CoffeeOrderItem> items;

	public CoffeeOrder() {
		super();
	}

	public CoffeeOrder(Date orderDate, String name, String address, double cost) {
		super();
		this.orderDate = orderDate;
		this.name = name;
		this.address = address;
		this.cost = cost;

	}

	public void generateDate() {
		this.orderDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Set<CoffeeOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<CoffeeOrderItem> items) {
		this.items = items;
	}

}
