package models;

public class Supplier {

	private int idSupplier;
	private String name;
	private String address;
	private int phone;
	private String lastName;

	public Supplier() {
		super();
	}

	public Supplier(int idSupplier, String name, String address, int phone, String lastName) {
		super();
		this.idSupplier = idSupplier;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.lastName = lastName;
	}

	public int getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return name + " " + lastName;
	}

}
