package models;

public class Product {

	private int idProduct;
	private String name;
	private String description;
	private double price;
	private int quantity;
	private int idCategory;
	private int idSupplier;
	private String category;
	private String sName;
	private String sLastName;
	private int quantityRef;//trasient

	public Product() {
		super();
	}

	public Product(int idProduct, String name, String description, double price, int quantity, int idCategory,
			int idSupplier) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.idCategory = idCategory;
		this.idSupplier = idSupplier;
	}
	
	public Product(int idProduct, String name, String description, double price, int quantity, int idCategory,
			int idSupplier, String category, String sname, String slastName) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.idCategory = idCategory;
		this.idSupplier = idSupplier;
		this.category = category;
		sName = sname;
		sLastName = slastName;
	}
	
	public Product(Product product) {
		this.idProduct = product.getIdProduct();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.quantity = product.getQuantity();
		this.idCategory = product.getIdCategory();
		this.idSupplier = product.getIdSupplier();
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public int getIdSupplier() {
		return idSupplier;
	}

	public void setIdSupplier(int idSupplier) {
		this.idSupplier = idSupplier;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSname() {
		return sName;
	}

	public void setSname(String sname) {
		sName = sname;
	}

	public String getSlastName() {
		return sLastName;
	}

	public void setSlastName(String slastName) {
		sLastName = slastName;
	}

	public int getQuantityRef() {
		return quantityRef;
	}

	public void setQuantityRef(int quantityRef) {
		this.quantityRef = quantityRef;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false; 
	    }
	    Product other = (Product) obj;
	    return idProduct == other.idProduct &&  
	            name.equals(other.name) &&       
	            description.equals(other.description) && 
	            Double.compare(price, other.price) == 0 &&  
	            quantity == other.quantity &&      
	            idCategory == other.idCategory &&  
	            idSupplier == other.idSupplier &&  
	            category.equals(other.category) && 
	            sName.equals(other.sName) &&       
	            sLastName.equals(other.sLastName);
	}

}
