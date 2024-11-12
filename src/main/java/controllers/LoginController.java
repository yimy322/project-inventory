package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
		//se valida las cajas
		if (form.textField.getText().equals("") || form.passwordField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Completa todas las casillas","Login", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println(change(form.passwordField.getText().toUpperCase())+"-----");
        
		FormMain menu = new FormMain();
		MainController menuController = new MainController(menu);
		menuController.showView();
		closeView();
	}
	//intercambia de posicion la contra, el primero con el ultimo, etc..
	public String change(String pass) {
		char[] caracteres = pass.toCharArray();
		int i = 0;//inicio
        int j = caracteres.length - 1;//final
        while (i < j) {
        	//intercambio
            char temp = caracteres[i];
            caracteres[i] = caracteres[j];
            caracteres[j] = temp;
            i++;
            j--;
        }
		return new String(caracteres);
	}

	// metodo para salir al sistema
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
