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
	
	//variable global para usarlo en el main
	public static User USER;
	UserService userService = new UserService();

	public LoginController(FormLogin form) {
		this.form = form;
		// se le ponen las acciones a los buttons
		this.form.btnExit.addActionListener(this);
		this.form.btnLogin.addActionListener(this);
	}

	// metodo para ingresar al sistema ADMIN ADMIN
	public void login() {
		//se valida las cajas
		if (form.textField.getText().equals("") || form.passwordField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Completa todas las casillas","Login", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //trae los usuarios usando la lista enlazada
        LinkedList usuarios = userService.findAll();
        //iteramos hasta verificar si las credenciales existen en la bd
        for (int i = 0; i < usuarios.size(); i++) {
        	User usuario = (User) usuarios.get(i);
        	System.out.println(usuario);
        	if (form.textField.getText().equals(usuario.getUsername()) && hashMethod(form.passwordField.getText()).equals(usuario.getPassword())) {
        		System.out.println("credenciales encontradas");
        		//guardamos el usuario en la global
        		USER = usuario;
        		FormMain menu = new FormMain();
        		MainController menuController = new MainController(menu);
        		menuController.showView();
        		closeView();
        	}else {
        		JOptionPane.showMessageDialog(null, "Credenciales invalidas","Error", JOptionPane.ERROR_MESSAGE);
        	}
        }
	}
	//es posible que haya colisiones
	public String hashMethod(String input) {
        long hashValue = 0;
        //se recorre
        for (int i = 0; i < input.length(); i++) {
            //obtenemos el ascii
            int charCode = input.charAt(i);
            //formular para calc un valor hash
            hashValue = (hashValue * 31) + charCode;
        }
        //convertimos a hexadecimal
        return Long.toHexString(hashValue);
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
