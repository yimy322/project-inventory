package models;

import java.util.Date;

public class Order {
	private int idOrder;
	private String orderDate;
	private int idCustomer;
	private int type;

	public Order() {
		super();
	}

	public Order(int idOrder, String orderDate, int idCustomer, int type) {
		super();
		this.idOrder = idOrder;
		this.orderDate = orderDate;
		this.idCustomer = idCustomer;
		this.type = type;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
