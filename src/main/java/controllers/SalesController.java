package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import extras.Input;
import hashTable.HashTable;
import hashTable.NodeHash;
import linkedList.LinkedList;
import models.Category;
import models.Customer;
import models.Product;
import models.Supplier;
import services.CategoryService;
import services.CustomerService;
import services.ProductService;
import services.SupplierService;
import subViews.vSales;

public class SalesController implements ActionListener {

	private vSales form;
	CustomerService customerService = new CustomerService();
	ProductService productService = new ProductService();
	CategoryService categoryService = new CategoryService();
	SupplierService supplierService = new SupplierService();
	// trae los registros de la bd
	LinkedList clientes = customerService.findAll();
	HashTable clientesHash = new HashTable(10);// tam del ht

	// un default para el combo
	NodeHash nodeDefault = new NodeHash(0, "<SELECCIONAR>");
	NodeHash noEditable = new NodeHash(0, "No editable");
	LinkedList productos = productService.findAll();
	int cantidad = 0;
	int cantidadElegida = 0;
	int cantidadTotal = 0;
	double totalNeto = 0.00;
	
	public SalesController(vSales form) {
		this.form = form;
		this.form.btnBuscarCliente.addActionListener(this);
		this.form.cbCategoria.addActionListener(this);
		this.form.cbProveedor.addActionListener(this);
		this.form.cbProducto.addActionListener(this);
		this.form.btnAgregarProducto.addActionListener(this);
		init();
	}

	public void init() {
		Input.onlyNumbers(this.form.txtDni);
		Input.validateMaxLength(this.form.txtDni, 8);
		// se carga la data en el hashtable
		loadDataHashTable();
		loadCategories();
		loadSuppliers();
		// un default para el combo
		this.form.cbProducto.addItem(nodeDefault);
		this.form.txtCantidadProd.setText(cantidadTotal+"");
		this.form.txtTotalProd.setText(totalNeto+"");
	}

	public void searchCustomer() {
		if (this.form.txtDni.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingresar un DNI", "Ventas", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int dni = Integer.parseInt(this.form.txtDni.getText());
		NodeHash resultProducto = clientesHash.search(dni);
		if (resultProducto != null) {
			Customer producto = (Customer) resultProducto.value;
			this.form.txtNombres.setText(producto.getFirstName());
			this.form.txtApellidos.setText(producto.getLastName());
			this.form.txtTelefono.setText(producto.getPhone() + "");
			this.form.txtEmail.setText(producto.getEmail());
			return;
		}
		JOptionPane.showMessageDialog(null, "El DNI " + dni + " no existe, por favor verificar.", "Ventas",
				JOptionPane.WARNING_MESSAGE);
		clearCustomer();
	}

	public void loadDataHashTable() {
		for (int i = 0; i < clientes.size(); i++) {
			Customer producto = (Customer) clientes.get(i);
			// se crea el nodo
			NodeHash productoNode = new NodeHash(producto.getDni(), producto);
			// se agrega al hastTable
			clientesHash.addToHashTable(productoNode);
		}
	}

	public void loadCategories() {
		// cargar categorias
		LinkedList categorias = categoryService.findAll();
		// limpiamos el combo
		form.cbCategoria.removeAllItems();// se duplica en un caso por eso se limpia
		// un default para el combo
		form.cbCategoria.addItem(nodeDefault);
		// iteramos
		for (int i = 0; i < categorias.size(); i++) {
			Category categoria = (Category) categorias.get(i);
			// se crea el nodo
			NodeHash nodeCategoria = new NodeHash(categoria.getIdCategory(), categoria);
			// se agrega el nodo al combo, como tiene to string imprimira el tostring del
			// value en este caso del category
			form.cbCategoria.addItem(nodeCategoria);
		}
	}
	
	public void loadSuppliers() {
		LinkedList proveedores = supplierService.findAll();// Se obtiene una lista de proveedores de la base de datos
		form.cbProveedor.removeAllItems(); // Se limpia el combo
		// un default para el combo
		form.cbProveedor.addItem(nodeDefault);
		for (int i = 0; i < proveedores.size(); i++) {// Itera la lista de proveedores
			Supplier proveedor = (Supplier) proveedores.get(i); // Retorna objeto de tipo proveedor
			NodeHash nodeProveedor = new NodeHash(proveedor.getIdSupplier(), proveedor);
			form.cbProveedor.addItem(nodeProveedor); // Se agrega cada proveedor al combo
		}
	}
	
	public void loadProducts() {
		form.cbProducto.removeAllItems();
		form.cbProducto.addItem(nodeDefault);
		for (int i = 0; i < productos.size(); i++) {
			Product producto = (Product) productos.get(i);
			NodeHash nodeProducto= new NodeHash(producto.getIdProduct(), producto);
			//captura los id de los combos
			int indCategorias = searchIdCbm(form.cbCategoria.getSelectedItem());
			int indProveedores = searchIdCbm(form.cbProveedor.getSelectedItem());
			//en caso alguno de ellos tenga dato
			if(indCategorias>0 || indProveedores>0) {
				//los dos deben tener data para que se agregue al combo de productos
				if(producto.getIdCategory()==indCategorias && producto.getIdSupplier()==indProveedores) {
					//si matchea se agrega. EN CASO CONTRARIO, NO
					form.cbProducto.addItem(nodeProducto);
				}
			}
		}
	}
	
	public int searchIdCbm(Object obj) {
		NodeHash selectedItem = (NodeHash) obj;
		return selectedItem.key;
	}

	public void clearCustomer() {
		this.form.txtDni.setText("");
		this.form.txtNombres.setText("");
		this.form.txtApellidos.setText("");
		this.form.txtTelefono.setText("");
		this.form.txtEmail.setText("");
	}
	
	public void loadQuantity() {
		if(this.form.cbProducto.getSelectedItem()!=null) {
			if(!this.form.cbProducto.getSelectedItem().equals(nodeDefault)) {
				this.form.txtCantidad.setEditable(true);
				NodeHash node = (NodeHash)this.form.cbProducto.getSelectedItem();
				Product producto = (Product) node.value;
				cantidad = producto.getQuantity();
				this.form.lblMaximo.setText("Max. "+cantidad);
			}else {
				this.form.txtCantidad.setEditable(false);
				this.form.lblMaximo.setText("Max. 0");
				cantidad = 0;
			}
		}	
	}

	public void addProduct() {
		if(this.form.txtCantidad.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe indicar la cantidad", "Ventas",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		int cantidadActual = Integer.parseInt(this.form.txtCantidad.getText());
		if(cantidadActual<0) {
			JOptionPane.showMessageDialog(null, "La cantidad no puede ser negativa", "Ventas",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(cantidadActual>cantidad) {
			JOptionPane.showMessageDialog(null, "La cantidad no puede sobrepasar a la cantidad maxima", "Ventas",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		cantidadElegida = cantidadActual;
		//logica para agregar el producto
		int idProducto = searchIdCbm(form.cbProducto.getSelectedItem());
		//capturar desde la lista
		Product nuevoProducto = searchProductById(idProducto);
		addProducts(nuevoProducto);
		loadProducts();
		this.form.txtCantidad.setText("");
	}
	
	public Product searchProductById(int idProducto) {
		for (int i = 0; i < productos.size(); i++) {
			Product producto = (Product) productos.get(i);
			if(producto.getIdProduct()==idProducto) {
				return producto;
			}
		}
		return null;
	}
	
	public void addProducts(Product prod) {
		//se creo una copia del nuevo producto
		Product nuevoProducto = new Product(prod);
		//se calcula el total
		double total = prod.getPrice() * cantidadElegida;
		//se calcula la cantidad restante
		cantidad= prod.getQuantity() - cantidadElegida;
		//aca se actualiza la cantidad del producto
		nuevoProducto.setQuantity(cantidad);
		productos.update(prod, nuevoProducto);
		productos.print();	
		//-------
		this.form.lblMaximo.setText("Max. "+cantidad);
		//verificamos que en caso el producto exista se incremente la cantidad
		boolean productoExistente = existProduct(prod);
		//---
		if(!productoExistente) {
			Object ob[] = new Object[8];
			ob[0] = prod.getIdProduct();
			ob[1] = prod.getName();
			ob[2] = prod.getDescription();
			ob[3] = prod.getPrice();
			ob[4] = cantidadElegida;
			ob[5] = total;
			this.form.model.addRow(ob);
		}
		this.form.jTableProducts.setModel(this.form.model);
		//actualiza la cantidad
		updateQuantity(cantidadElegida);
		updateTotal();
	}
	
	public boolean existProduct(Product prod) {
		for (int i = 0; i < this.form.model.getRowCount(); i++) {
	        int idProductoTabla = (int) this.form.model.getValueAt(i, 0); // capturamos el id en la primera columna
	        if (idProductoTabla == prod.getIdProduct()) {
	            //si existe se incremente la cantidad
	            int cantidadExistente = (int) this.form.model.getValueAt(i, 4);
	            this.form.model.setValueAt(cantidadExistente + cantidadElegida, i, 4); //actualizamos la cantidad
	            this.form.model.setValueAt((cantidadExistente + cantidadElegida) * prod.getPrice(), i, 5); //actualizamos el total
	            return true;
	        }
	    }
		return false;
	}
	
	public void updateQuantity(int cant) {
		cantidadTotal+=cant;
		this.form.txtCantidadProd.setText(cantidadTotal+"");
	}
	
	public void updateTotal() {
		totalNeto = 0.00;
		for (int i = 0; i < this.form.model.getRowCount(); i++) {
			double totalPorProducto = (double) this.form.model.getValueAt(i, 5);
			totalNeto += totalPorProducto;
	    }
		this.form.txtTotalProd.setText(totalNeto+"");
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object press = ae.getSource();
		if (press == this.form.btnBuscarCliente) {
			searchCustomer();
		} else if (press == this.form.cbCategoria) {
			//si no se selecciono nada que no busque
			if(!this.form.cbCategoria.getSelectedItem().equals(nodeDefault))
				loadProducts();
		} else if (press == this.form.cbProveedor) {
			//si no se selecciono nada que no busque
			if(!this.form.cbProveedor.getSelectedItem().equals(nodeDefault))
				loadProducts();
		}else if (press == this.form.cbProducto) {
			//si no se selecciono nada que no busque
			loadQuantity();
		}else if (press == this.form.btnAgregarProducto) {
			addProduct();
		}
	}

	public void showView() {
		form.setVisible(true);
	}

}
