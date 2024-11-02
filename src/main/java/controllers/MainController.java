package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.FormMain;

public class MainController implements ActionListener {
	
	private FormMain form;
	
	public MainController(FormMain form) {
		this.form = form;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void showView() {
		form.setVisible(true);
	}

	public void closeView() {
		form.dispose();
	}

}
