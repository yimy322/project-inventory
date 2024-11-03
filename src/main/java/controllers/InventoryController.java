package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import subViews.vInventory;

public class InventoryController implements ActionListener{
	
	private vInventory form;

	public InventoryController(vInventory form) {
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
