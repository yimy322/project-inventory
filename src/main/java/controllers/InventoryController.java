package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import linkedList.LinkedList;
import linkedList.Node;
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

	public void refreshTable() { // pinta los registros en la tabla de la vista
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

	// intercambia la data del node, de la lista xd
	private void intercambiar(Node i, Node j) {
		Object temp = i.data;
		i.data = j.data;
		j.data = temp;
	}

	// ordena por nombre
	public void shell() {
		int intervalo, i, j, k;
		int n = productos.size();
		intervalo = n / 2;
		while (intervalo > 0) {
			for (i = intervalo; i < n; i++) {
				j = i - intervalo;
				while (j >= 0) {
					k = j + intervalo;
					Node iNode = productos.getNode(j);
					Node JNode = productos.getNode(k);
					Product primerValor = (Product) productos.get(j);
					Product segundoValor = (Product) productos.get(k);
					// el compareto retorna un int, si es menor significa que esta ordenado
					// alfabeticamente, en caso se cambia de pos
					if (primerValor.getName().compareTo(segundoValor.getName()) < 0)
						j = -1;
					else {
						intercambiar(iNode, JNode);
						j -= intervalo;
					}
				}
			}
			intervalo = intervalo / 2;
		}
	}

	// metodo para ordenar por id
	public void shellById() {
		int intervalo, i, j, k;
		int n = productos.size();
		intervalo = n / 2;
		while (intervalo > 0) {
			for (i = intervalo; i < n; i++) {
				j = i - intervalo;
				while (j >= 0) {
					k = j + intervalo;
					Node iNode = productos.getNode(j);
					Node JNode = productos.getNode(k);
					Product primerValor = (Product) productos.get(j);
					Product segundoValor = (Product) productos.get(k);
					if (primerValor.getIdProduct() < segundoValor.getIdProduct())
						j = -1;
					else {
						intercambiar(iNode, JNode);
						j -= intervalo;
					}
				}
			}
			intervalo = intervalo / 2;
		}
	}

	public void sortByName() {
		shell();
		refreshTable();
	}

	public void sortById() {
		shellById();
		refreshTable();
	}

	//metodo de busqueda binaria
	public int search(String valor) {
		productos = productService.findAll();
		shell();
		int central, bajo, alto;
		Product valorCentral;
		bajo = 0;
		alto = productos.size() - 1;
		while (bajo <= alto) {
			central = (bajo + alto) / 2;
			valorCentral = (Product) productos.get(central);
			if (valorCentral.getName().matches("(?i)(.*)" + valor + "(.*)"))
				return central;
			else if (valor.compareTo(valorCentral.getName()) < 0)
				alto = central - 1;
			else
				bajo = central + 1;
		}
		return -1; // elemento no encontrado
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
