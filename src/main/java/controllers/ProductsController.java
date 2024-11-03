package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import subViews.vProducts;

public class ProductsController implements ActionListener{
	
	private vProducts form;

	public ProductsController(vProducts form) {
		this.form = form;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void showView() {
		form.setVisible(true);
	}
	
}
