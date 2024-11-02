package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.FormLogin;
import views.FormMain;

public class LoginController implements ActionListener {

	private FormLogin form;

	public LoginController(FormLogin form) {
		this.form = form;
		// se le ponen las acciones a los buttons
		this.form.btnExit.addActionListener(this);
		this.form.btnLogin.addActionListener(this);
	}

	// metodo para ingresar al sistema
	public void login() {
		System.out.println("aca ira la logica de ingresar al sistema");
		FormMain menu = new FormMain();
		MainController menuController = new MainController(menu);
		menuController.showView();
		closeView();
	}

	// metodo para salir al sistema
	public void exit() {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// aca se captura el obj que llama al evento
		Object press = ae.getSource();
		if (press == this.form.btnExit) {
			exit();
		} else if (press == this.form.btnLogin) {
			login();
		}
	}

	//metodo para abrir la vista
	public void showView() {
		form.setVisible(true);
	}

	//metodo para cerrar la vista
	public void closeView() {
		form.dispose();
	}

}
