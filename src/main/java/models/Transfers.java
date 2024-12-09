package models;

public class Transfers {
	private int idTransfers, quantity, idUser, idProduct;
	private String fecha, typeTransfers;
	
	private String nameProduct, userName;
	private int quantityProduct;
	
	private String nameSupplier;
	private int total;
	
	public Transfers(int idTransfers, int quantity, int idUser, int idProduct, String fecha,
			String typeTransfers) {
		this.idTransfers = idTransfers;
		this.quantity = quantity;
		this.idUser = idUser;
		this.idProduct = idProduct;
		this.fecha = fecha;
		this.typeTransfers = typeTransfers;
	}
	
	public Transfers(int idTransfers, int quantity, int idUser, int idProduct, String fecha,
			String typeTransfers, String nameProduct, String userName, int quantityProduct, String nameSupplier, int total) {
		this.idTransfers = idTransfers;
		this.quantity = quantity;
		this.idUser = idUser;
		this.idProduct = idProduct;
		this.fecha = fecha;
		this.typeTransfers = typeTransfers;
		this.nameProduct = nameProduct;
		this.userName = userName;
		this.quantityProduct = quantityProduct;
		this.nameSupplier = nameSupplier;
		this.total = total;
	}

	public String getNameSupplier() {
		return nameSupplier;
	}

	public void setNameSupplier(String nameSupplier) {
		this.nameSupplier = nameSupplier;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getQuantityProduct() {
		return quantityProduct;
	}

	public void setQuantityProduct(int quantityProduct) {
		this.quantityProduct = quantityProduct;
	}

	public int getIdTransfers() {
		return idTransfers;
	}
	public void setIdTransfers(int idTransfers) {
		this.idTransfers = idTransfers;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTypeTransfers() {
		return typeTransfers;
	}
	public void setTypeTransfers(String typeTransfers) {
		this.typeTransfers = typeTransfers;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
