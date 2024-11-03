package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import subViews.vSettings;

public class SettingsController implements ActionListener{

	private vSettings form;

	public SettingsController(vSettings form) {
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
