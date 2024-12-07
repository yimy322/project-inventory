package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import extras.Input;
import hashTable.HashTable;
import hashTable.Node;
import linkedList.LinkedList;
import models.Customer;
import models.Product;
import services.CustomerService;
import services.ProductService;
import subViews.vSales;

public class SalesController implements ActionListener {
	
	private vSales form;
	CustomerService customerService = new CustomerService();
	ProductService productService = new ProductService();
	
	// trae los registros de la bd
	LinkedList clientes = customerService.findAll();
	HashTable clientesHash = new HashTable(10);//tam del ht
	
	public SalesController(vSales form) {
		this.form = form;
		this.form.btnBuscarCliente.addActionListener(this);
		init();
	}
	
	public void init() {
		Input.onlyNumbers(this.form.txtDni);
		Input.validateMaxLength(this.form.txtDni, 8);
		//se carga la data en el hashtable
		loadDataHashTable();
		clientesHash.print();//para ver los clientes en el hashtable
	}
	
	public void searchCustomer() {
		if(this.form.txtDni.getText().trim().isEmpty() ) {
			JOptionPane.showMessageDialog(null, "Por favor, ingresar un DNI", "Ventas",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		int dni = Integer.parseInt(this.form.txtDni.getText());
		Node resultProducto = clientesHash.search(dni);
		if(resultProducto!=null) {
			Customer producto = (Customer)resultProducto.value;
			this.form.txtNombres.setText(producto.getFirstName());
			this.form.txtApellidos.setText(producto.getLastName());
			this.form.txtTelefono.setText(producto.getPhone()+"");
			this.form.txtEmail.setText(producto.getEmail());
			return;
		}
		JOptionPane.showMessageDialog(null, "El DNI "+dni+" no existe, por favor verificar.", "Ventas",
				JOptionPane.WARNING_MESSAGE);
		clearCustomer();
	}
	
	public void loadDataHashTable() {
		for (int i = 0; i < clientes.size(); i++) {
			Customer producto = (Customer) clientes.get(i);
			//se crea el nodo
			Node productoNode = new Node(producto.getDni(), producto);
			//se agrega al hastTable
			clientesHash.addToHashTable(productoNode);
		}
	}
	
	public void clearCustomer() {
		this.form.txtDni.setText("");
		this.form.txtNombres.setText("");
		this.form.txtApellidos.setText("");
		this.form.txtTelefono.setText("");
		this.form.txtEmail.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object press = ae.getSource();
		if (press == this.form.btnBuscarCliente) {
			searchCustomer();
		} 
	}
	
	public void showView() {
		form.setVisible(true);
	}

}
