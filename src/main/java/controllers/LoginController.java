package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import linkedList.LinkedList;
import models.User;
import services.UserService;
import views.FormLogin;
import views.FormMain;

public class LoginController implements ActionListener {

	private FormLogin form;

	// variable global para usarlo en el main
	public static User USER;
	UserService userService = new UserService();
	public LoginController(){
	}
	public LoginController(FormLogin form) {
		this.form = form;
		// se le ponen las acciones a los buttons
		this.form.btnExit.addActionListener(this);
		this.form.btnLogin.addActionListener(this);
	}

	// metodo para ingresar al sistema ADMIN ADMIN
	public void login() {
		// se valida las cajas
		if (form.textField.getText().trim().isEmpty() || form.passwordField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Completa todas las casillas", "Login", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// trae los usuarios usando la lista enlazada
		LinkedList usuarios = userService.findAll();
		// iteramos hasta verificar si las credenciales existen en la bd
		for (int i = 0; i < usuarios.size(); i++) {
			User usuario = (User) usuarios.get(i);
			System.out.println(usuario);
			if (form.textField.getText().equals(usuario.getUsername()) && encryptor(form.passwordField.getText(), 5).equals(usuario.getPassword())) {
				System.out.println("credenciales encontradas");
				// guardamos el usuario en la global
				USER = usuario;
				FormMain menu = new FormMain();
				MainController menuController = new MainController(menu);
				menuController.showView();
				closeView();
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Credenciales invalidas", "Error", JOptionPane.ERROR_MESSAGE);
	}

	// Primer metodo encryptador, revertidor de texto - recursivo
	public String getStrReverse(String palabra) {
		if (palabra.length() <= 1) {
			return palabra;
		}
		return getStrReverse(palabra.substring(1)) + palabra.charAt(0);
	}

	// Segundo metodo encryptador, ascii de texto - recursivo
	public String encryptorAscii(String clave, Boolean reversible) { // el parametro booleano refleja si
		if (clave.length() <= 0) { // el metodo actuara para revertir el efecto del encryptador ascii
			return clave;
		}
		int ascii = 32;
		if (reversible == null || reversible == false) {
			ascii = (int) clave.charAt(0) + 1;
		} else {
			ascii = (int) clave.charAt(0) - 1;
		}

		String letra = String.valueOf((char) ascii);
		return letra + encryptorAscii(clave.substring(1), reversible);
	}

	// sirve para encryptar segun los metodos anteriores, segun el nivel de
	// seguridad usara el metodo
	public String encryptor(String clave, int security) {
		String pass = "";
		for (int i = 0; i < security; i++) {
			pass = getStrReverse(clave);
			pass = encryptorAscii(pass, false);
			clave = pass;
		}
		return pass;
	}

	// sirve para revertir la encryptacion segun el nivel al que fue encryptada
	public String desencryptor(String clave, int security) {
		String pass = "";
		for (int i = 0; i < security; i++) {
			pass = getStrReverse(clave);
			pass = encryptorAscii(pass, true);
			clave = pass;
		}
		return pass;
	}

	// metodo para salir al sistema
	public void exit() {
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?", "Confirmación",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
		} else if (press == this.form.btnLogin) {
			login();
		}
	}

	// metodo para abrir la vista
	public void showView() {
		form.setVisible(true);
	}

	// metodo para cerrar la vista
	public void closeView() {
		form.dispose();
	}

}
