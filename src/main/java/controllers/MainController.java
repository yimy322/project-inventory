package controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import subViews.vCustomers;
import subViews.vInventory;
import subViews.vProducts;
import subViews.vSales;
import subViews.vSettings;
import subViews.vSuppliers;
import subViews.vTransfers;
import views.FormMain;

public class MainController implements ActionListener {
	
	private FormMain form;
	
	public MainController(FormMain form) {
		this.form = form;
		this.form.btnSales.addActionListener(this);
		this.form.btnCustomers.addActionListener(this);
		this.form.btnProducts.addActionListener(this);
		this.form.btnInventory.addActionListener(this);
		this.form.btnSuppliers.addActionListener(this);
		this.form.btnTransfers.addActionListener(this);
		this.form.btnSettings.addActionListener(this);
		init();
	}
	
	public void init() {
		this.form.lblActiveUser.setText(LoginController.USER.getUsername());
	}
	
	public void showSales(){
		vSales sales= new vSales();
		SalesController salesController = new SalesController(sales);
		salesController.showView();
		this.form.conteiner.removeAll();
		this.form.conteiner.add(sales, BorderLayout.CENTER);
		refreshViews();
	}
	
	public void showCustomers(){
		vCustomers customer= new vCustomers();
		CustomerController customerController = new CustomerController(customer);
		customerController.showView();
		this.form.conteiner.removeAll();
		this.form.conteiner.add(customer, BorderLayout.CENTER);
		refreshViews();
	}
	
	public void showProducts(){
		vProducts product= new vProducts();
		ProductsController productsController = new ProductsController(product);
		productsController.showView();
		this.form.conteiner.removeAll();
		this.form.conteiner.add(product, BorderLayout.CENTER);
		refreshViews();
	}
	
	public void showInventory(){
		vInventory inventory= new vInventory();
		InventoryController inventoryController = new InventoryController(inventory);
		inventoryController.showView();
		this.form.conteiner.removeAll();
		this.form.conteiner.add(inventory, BorderLayout.CENTER);
		refreshViews();
	}
	
	public void showSuppliers(){
		vSuppliers suppliers= new vSuppliers();
		SupplierController supplierController = new SupplierController(suppliers);
		supplierController.showView();
		this.form.conteiner.removeAll();
		this.form.conteiner.add(suppliers, BorderLayout.CENTER);
		refreshViews();
	}
	
	public void showTransfers(){
		vTransfers transfers= new vTransfers();
		TransferController transferController = new TransferController(transfers);
		transferController.showView();
		this.form.conteiner.removeAll();
		this.form.conteiner.add(transfers, BorderLayout.CENTER);
		refreshViews();
	}
	
	public void showSetting(){
		vSettings settings= new vSettings();
		SettingsController settingsController = new SettingsController(settings);
		settingsController.showView();
		this.form.conteiner.removeAll();
		this.form.conteiner.add(settings, BorderLayout.CENTER);
		refreshViews();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object press = ae.getSource();
		if (press == this.form.btnSales) {
			showSales();
		} else if (press == this.form.btnCustomers) {
			showCustomers();
		}else if (press == this.form.btnProducts) {
			showProducts();
		}else if (press == this.form.btnInventory) {
			showInventory();
		}else if (press == this.form.btnSuppliers) {
			showSuppliers();
		}else if (press == this.form.btnTransfers) {
			showTransfers();
		}else if (press == this.form.btnSettings) {
			showSetting();
		}
	}
	
	public void refreshViews() {
		this.form.conteiner.revalidate();
		this.form.conteiner.repaint();
	}

	public void showView() {
		form.setVisible(true);
	}

	public void closeView() {
		form.dispose();
	}

}
