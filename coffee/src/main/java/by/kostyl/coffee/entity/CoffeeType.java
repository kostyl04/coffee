package by.kostyl.coffee.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "CoffeeType")
public class CoffeeType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "type_name")
	private String name;
	@Column(name = "price")
	private double price;
	@Column(name = "disabled", length = 1)
	private Character disabled;
	@Transient
	private boolean selected;
	@Transient
	private int quantity=1;
	@Transient
	private double priceSumm;
	
	public double getPriceSumm() {
		return priceSumm;
	}

	public void setPriceSumm(double d) {
		this.priceSumm = d;
	}

	public CoffeeType(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public CoffeeType() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public char getDisabled() {
		return disabled;
	}

	public void setDisabled(char disabled) {
		this.disabled = disabled;
	}

	public boolean isDisabled() {
		return this.disabled=='Y' ? true : false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/*
	 * create table CoffeeType ( id int not null, -- pk type_name varchar(200)
	 * not null, -- название price double not null, -- цена disabled char(1), --
	 * если disabled = 'Y', то не показывать данный сорт в списке доступных
	 * сортов primary key (id) ) ENGINE=InnoDB;
	 */
}
