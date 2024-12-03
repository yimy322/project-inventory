package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.LinkOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.crypto.spec.DESedeKeySpec;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import RSMaterialComponent.RSButtonMaterialIconDos;
import linkedList.LinkedList;
import models.Roles;
import models.User;
import rojeru_san.efectos.ValoresEnum.ICONS;
import services.RoleService;
import services.UserService;
import subViews.vSettings;

public class SettingsController implements ActionListener {

	private vSettings form;
	private String[] usOld;

	public SettingsController(vSettings form) {
		this.form = form;
		List<RSButtonMaterialIconDos> buttons = Arrays.asList(this.form.btnExit, this.form.btnAccountEditar, this.form.btnUsers, this.form.btnSecurity, this.form.btnAddUser, this.form.btnDeleteUser);
		buttons.forEach(button -> button.addActionListener(this));
		init();
	}
	//LoginController loginAction = new LoginController();
	//loginAction.desencryptor(LoginController.USER.getPassword(), 5)
	public void init() {
		this.form.txtNombreUsuario.setText(LoginController.USER.getUsername());
		this.form.txtPasswordUsuario.setText(LoginController.USER.getPassword());
		this.form.txtFirstName.setText(LoginController.USER.getFirstName());
		this.form.txtLastName.setText(LoginController.USER.getLastName());
		this.form.txtTelefonoUsuario.setText(String.valueOf(LoginController.USER.getPhone()));
		this.form.txtEmailUsuario.setText(LoginController.USER.getEmail());
		this.form.lblUsuarioSettings.setText(LoginController.USER.getUsername());
		boolean enableADMIN = this.form.txtNombreUsuario.getText().equals("ADMIN");
		List<RSButtonMaterialIconDos> buttons = Arrays.asList(this.form.btnUsers, this.form.btnSecurity);
		buttons.forEach(button -> button.setEnabled(enableADMIN));
		loadUserTable();
	}

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
		} else if (press == this.form.btnAccountEditar) {
			saveModified();
		}else if(press == this.form.btnAddUser) {
			saveInsertUser();
		}else if(press == this.form.btnDeleteUser) {
			deleteUser();
		}
	}

	// PERMITE EDITAR EL NOMBRE DE USUARIOS DEL BOTON
	public void editNameButtonn() {
		boolean opcion = this.form.btnAccountEditar.getText().toString().toLowerCase().equals("editar");

		if (opcion) {
			this.form.btnAccountEditar.setText("Guardar");
			this.form.btnAccountEditar.setIcons(ICONS.SAVE);
		} else {
			this.form.btnAccountEditar.setText("Editar");
			this.form.btnAccountEditar.setIcons(ICONS.EDIT);
		}
	}

	// PERMITE HABILITAR LA EDICION DE LA CAJA DE TEXTO DEPENDIENDO DEL ESTADO
	public void enableTextAccount() {
		LoginController loginAction = new LoginController();
		boolean opcion = this.form.btnAccountEditar.getText().toString().equals("Editar");
		List<JTextField> txtFields = Arrays.asList(this.form.txtNombreUsuario, this.form.txtPasswordUsuario,
				this.form.txtFirstName, this.form.txtLastName, this.form.txtTelefonoUsuario, this.form.txtEmailUsuario);
		txtFields.forEach(field -> field.setEditable(!opcion));
		if(!opcion)
			this.form.txtPasswordUsuario.setText(loginAction.desencryptor(LoginController.USER.getPassword(), 5));
		else
			this.form.txtPasswordUsuario.setText(LoginController.USER.getPassword());
	}

	// PERMITE OBTENER COMO ARREGLO DE STRING LAS CAJAS DE TEXTO DE LA DATA ACTUAL
	public String[] getUserActive() {
		String nickName = this.form.txtNombreUsuario.getText();
		String password = this.form.txtPasswordUsuario.getText();
		String firstName = this.form.txtFirstName.getText();
		String lastName = this.form.txtLastName.getText();
		String telephone = this.form.txtTelefonoUsuario.getText();
		String email = this.form.txtEmailUsuario.getText();
		String[] user = {password, nickName, firstName, lastName, telephone, email};
		return user;
	}

	// PERMITE COMPARA LOS DATOS ANTIGUOS Y LOS NUEVOS PARA VERIFICAR SI HAY CAMBIOS
	public boolean isModified(String[] usOld, String[] usNew) {
		boolean opcion = this.form.btnAccountEditar.getText().toString().equals("Editar");
		LoginController loginAction = new LoginController();
	    for (int i = 1; i < usNew.length; i++) {
	        if (!Objects.equals(usOld[i], usNew[i])) {
	            return true;
	        }
	    }
	    if(!opcion) {
	    	if(!Objects.equals(loginAction.encryptor(usNew[0], 5), usOld[0])) {
	    		return true;
	    	}
	    }
	    		
	    return false;
	}

	//PERMITE VERIFICAR LOS CAMBIOS, DE HABERLOS LOS GUARDA EN LA BASE DE DATOS
	public void saveModified() {
		boolean opcion = this.form.btnAccountEditar.getText().toString().toLowerCase().equals("editar");
		if(opcion) {
			usOld = getUserActive();
		}else {
			String[] usNew = getUserActive();
			if(isModified(usOld, usNew)) {
				if(!usOld[1].equals("ADMIN")) {
					UserService serviceSQL = new UserService();
					serviceSQL.updateUser(getUserAccount());
				}
			}
			usOld = getUserActive();
		}
		usOld = getUserActive();
		editNameButtonn();
		enableTextAccount();
		loadUserTable();
	}
	
	//PERMITE ALMACENAR EL VALOR DE LOS REGISTROS EN LA CUENTA ACTIVA EN UN OBJETO
	public User getUserAccount() {
		LoginController loginService = new LoginController();
		String nickName = this.form.txtNombreUsuario.getText();
		String password = this.form.txtPasswordUsuario.getText();
		String firstName = this.form.txtFirstName.getText();
		String lastName = this.form.txtLastName.getText();
		int phone = Integer.parseInt(this.form.txtTelefonoUsuario.getText());
		String email = this.form.txtEmailUsuario.getText();
		User user = new User(LoginController.USER.getIdUser(), nickName, loginService.encryptor(password, 5), firstName, lastName, phone, email);
		return user;
	}
	
	//PERMITE CARGAR LA TABLA DE LISTA DE USUARIOS EN LA BASE DE DATOS
	public void loadUserTable() {
		UserService userService = new UserService();
		DefaultTableModel model = new DefaultTableModel(new String[] {"NickName", "Password", "Nombre", "Apellido", "Telefono", "Email"},0);
		LinkedList usuarios = userService.findAll();
		for(int i=0; i<usuarios.size(); i++) {
			User us = (User)usuarios.get(i);
			model.addRow(new Object[] {
				us.getUsername(),
				us.getPassword(),
				us.getFirstName(),
				us.getLastName(),
				String.valueOf(us.getPhone()),
				us.getEmail()
			});
		}
		this.form.tableUsers.setModel(model);
	}
	
	//PERMITE ALAMCANAR EN UN OBJECTO LOS REGISTRO NUEVOS REFERENTES AL NUEVO USUARIO
	public User getUserInsert() {
		LoginController loginService = new LoginController();
		String nickName = this.form.txtUNickName.getText();
		String password = this.form.txtUPassword.getText();
		String firstName = this.form.txtUFirstName.getText();
		String lastName = this.form.txtULastName.getText();
		int phone = Integer.parseInt(this.form.txtUPhone.getText());
		String email = this.form.txtUEmail.getText();
		User user = new User(0, nickName, loginService.encryptor(password, 5), firstName, lastName, phone, email);
		return user;
	}
	
	//PERMITE VALIDAD QUE TODOS LOS CAMPOS ESTEN LLENOS PARA EVITAR REGISTROS NULOS
	public boolean isComplete() {
		if(!this.form.txtUNickName.getText().isEmpty() && !this.form.txtUPassword.getText().isEmpty() && !this.form.txtUFirstName.getText().isEmpty() &&
				!this.form.txtULastName.getText().isEmpty() && !this.form.txtUPhone.getText().isEmpty() && !this.form.txtUEmail.getText().isEmpty())
			return true;
		return false;
	}
	
	//PERMITE ASIGNAR UN ROL AL USUARIO
	public void insertRole(String tipeRole) {
		RoleService serviceSQL = new RoleService();
		loadUserTable();
		UserService userService = new UserService();
		LinkedList users = userService.findAll();
		User user = (User) users.get(users.size()-1);
		Roles role = new Roles(user.getIdUser(), tipeRole);
		serviceSQL.insertRole(role);
	}
	
	//PERMITE GUARDAR UN NUEVO REGISTRO DE USUARIOS, SIN PRIVILEGIOS DE ADMIN
	public void saveInsertUser() {
		LoginController loginService = new LoginController();
		if(isComplete()) {
			User user = getUserInsert();
			String password = loginService.encryptor(String.valueOf(JOptionPane.showInputDialog("CONFIRME SU PASSWORD, PORFAVOR!")), 5);
			if(password.equals(user.getPassword())) {
				UserService serviceSQL = new UserService();
				serviceSQL.insertUser(user);
				insertRole("ROLE_USER");
			}
		}
		loadUserTable();
	}
	
	//PERMITE REASIGNAR PERMISOS DE USUARIOS (ADMIN, USER)
	public void deleteRoles(String rol, int idUser) {
		RoleService serviceSQL = new RoleService();
		serviceSQL.deleteRole(rol, idUser);
	}
	
	//PERMITE ELIMINAR UN USUARIO DADO SU ID
	public void deleteUser() {
		if(this.form.tableUsers.getSelectedRow()<0)
			return;
		
		UserService userService = new UserService();
		LinkedList usuarios = userService.findAll();
		
		int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar la seleccion?", "Alerta", JOptionPane.YES_NO_OPTION);
		if(opcion == JOptionPane.YES_OPTION) {
			int idDelete = this.form.tableUsers.getSelectedRow();
			User user = (User) usuarios.get(idDelete);
			if(user == null) {
				JOptionPane.showMessageDialog(null, "El elemento no se encuentra en la lista");
				return;
			}else {
				UserService serviceSQL = new UserService();
				deleteRoles("ROLE_USER", user.getIdUser());
				deleteRoles("ROLE_ADMIN", user.getIdUser());
				serviceSQL.deleteUser(user.getIdUser());
			}
		}
		loadUserTable();
	}
	
	public void showView() {
		form.setVisible(true);
	}

}
