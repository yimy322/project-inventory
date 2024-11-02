package main;
import controllers.LoginController;
import views.FormLogin;
public class Main {

	public static void main(String[] args) {
		
		FormLogin form = new FormLogin();
		LoginController loginController = new LoginController(form);
		loginController.showView();
	}

}
