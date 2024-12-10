package models;

public class OrderDetail {
	private int idOrderDetail;
	private double unitPrice;
	private int quantity;
	private double total;
	private int idOrder;
	private int idProduct;
	private int idUser;

	public OrderDetail() {
		super();
	}

	public OrderDetail(int idOrderDetail, double unitPrice, int quantity, double total, int idOrder, int idProduct,
			int idUser) {
		super();
		this.idOrderDetail = idOrderDetail;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.total = total;
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.idUser = idUser;
	}

	public int getIdOrderDetail() {
		return idOrderDetail;
	}

	public void setIdOrderDetail(int idOrderDetail) {
		this.idOrderDetail = idOrderDetail;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}
