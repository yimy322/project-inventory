package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import subViews.vTransfers;

public class TransferController implements ActionListener{
	
	private vTransfers form;

	public TransferController(vTransfers form) {
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
