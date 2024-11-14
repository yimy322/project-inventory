package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import linkedList.LinkedList;
import models.Category;
import services.CategoryService;
import subViews.vProducts;

public class ProductsController implements ActionListener{
	
	private vProducts form;
	CategoryService categoryService = new CategoryService();

	public ProductsController(vProducts form) {
		this.form = form;
		init();
	}
	
	public void init() {
		//cargar categorias
		LinkedList categorias = categoryService.findAll();
		//limpiamos el combo
		form.cbCategoria.removeAllItems();//se duplica en un caso por eso se limpia
		//iteramos
		for (int i = 0; i < categorias.size(); i++) {
			Category categoria = (Category)categorias.get(i);;
			form.cbCategoria.addItem(categoria.getName());
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
