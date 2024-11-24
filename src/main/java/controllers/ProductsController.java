package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import linkedList.LinkedList;
import models.Category;
import models.Supplier;
import services.CategoryService;
import services.SupplierService;
import subViews.vProducts;

public class ProductsController implements ActionListener {

	private vProducts form;
	CategoryService categoryService = new CategoryService();
	SupplierService supplierService = new SupplierService();

	public ProductsController(vProducts form) {
		this.form = form;
		init();
	}

	public void init() {
		loadCategories();
		loadSuppliers(); //Se llama al metodo para que aparezca los prov en el combo
	}

	public void loadCategories() {
		// cargar categorias
		LinkedList categorias = categoryService.findAll();
		// limpiamos el combo
		form.cbCategoria.removeAllItems();// se duplica en un caso por eso se limpia
		// iteramos
		for (int i = 0; i < categorias.size(); i++) {
			Category categoria = (Category) categorias.get(i);
			form.cbCategoria.addItem(categoria.getName());
		}
	}

	public void loadSuppliers() {
		LinkedList proveedores = supplierService.findAll();//Se obtiene una lista de proveedores de la base de datos
		form.cbProveedor.removeAllItems(); //Se limpia el combo
		for (int i = 0; i < proveedores.size(); i++) {//Itera la lista de proveedores
			Supplier proveedor = (Supplier) proveedores.get(i); // Retorna objeto de tipo proveedor
			form.cbProveedor.addItem(proveedor.getName() + " " + proveedor.getLastName()); //Se agrega cada proveedor al combo
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void showView() {
		form.setVisible(true);
	}

}
