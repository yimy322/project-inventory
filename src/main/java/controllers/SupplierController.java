package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import subViews.vSuppliers;

public class SupplierController implements ActionListener{
	
	private vSuppliers form;

	public SupplierController(vSuppliers form) {
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
