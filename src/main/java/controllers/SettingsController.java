package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import subViews.vSettings;

public class SettingsController implements ActionListener{

	private vSettings form;

	public SettingsController(vSettings form) {
		this.form = form;
		this.form.btnExit.addActionListener(this);
		init();
	}
	
	public void init() {
		this.form.lblNombreUsuario.setText(LoginController.USER.getUsername());
		this.form.lblPasswordUsuario.setText(LoginController.USER.getPassword());
		this.form.lblNombreCompleto.setText(LoginController.USER.getFirstName() +" "+ LoginController.USER.getLastName());
		this.form.lblTelefonoUsuario.setText(""+LoginController.USER.getPhone());
		this.form.lblEmailUsuario.setText(LoginController.USER.getEmail());
		this.form.lblUsuarioSettings.setText(LoginController.USER.getUsername());
	}
	
	public void exit() {
		int respuesta = JOptionPane.showConfirmDialog(null,"¿Estás seguro de que deseas salir?","Confirmación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// aca se captura el obj que llama al evento
		Object press = ae.getSource();
		if (press == this.form.btnExit) {
			exit();
		}
	}
	
	public void showView() {
		form.setVisible(true);
	}
	
}
