package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import subViews.vCustomers;

public class CustomerController implements ActionListener{
	
	private vCustomers form;
	
	public CustomerController(vCustomers form) {
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
