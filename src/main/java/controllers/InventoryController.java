package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import linkedList.LinkedList;
import models.Product;
import services.ProductService;
import subViews.vInventory;

public class InventoryController implements ActionListener, KeyListener {

	private vInventory form;

	ProductService productService = new ProductService();
	// trae los registros
	LinkedList productos = productService.findAll();

	public InventoryController(vInventory form) {
		this.form = form;
		this.form.btnOrdenar.addActionListener(this);
		this.form.btnOrdenarId.addActionListener(this);
		this.form.btnExcel.addActionListener(this);
		this.form.textBusqueda.addKeyListener(this);
		init();
	}

	public void init() {
		refreshTable();
		

	}

	public void refreshTable() { //pinta los registros en la tabla de la vista
		// antes de cargar los registros, borramos al data para que no se repita
		for (int j = 0; j < this.form.jTableProducts.getRowCount(); j++) {
			this.form.model.removeRow(j);
			j -= 1;
		}
		Object ob[] = new Object[8];
		if (productos != null) {
			for (int i = 0; i < productos.size(); i++) {
				Product producto = (Product) productos.get(i);
				ob[0] = producto.getIdProduct();
				ob[1] = producto.getName();
				ob[2] = producto.getDescription();
				ob[3] = producto.getPrice();
				ob[4] = producto.getQuantity();
				ob[5] = producto.getCategory();
				ob[6] = producto.getSname() + " " + producto.getSlastName();
				this.form.model.addRow(ob);
			}
		}
		// seteamos el model al jtable
		this.form.jTableProducts.setModel(this.form.model);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void showView() {
		form.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
