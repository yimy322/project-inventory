package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import subViews.vSales;

public class SalesController implements ActionListener {
	
	private vSales form;

	public SalesController(vSales form) {
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
