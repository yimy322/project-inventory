package controllers;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import extras.Input;
import hashTable.NodeHash;
import linkedList.LinkedList;
import models.Category;
import models.Roles;
import models.User;
import rojeru_san.efectos.ValoresEnum.ICONS;
import services.RoleService;
import services.UserService;
import subViews.vSettings;

public class SettingsController implements ActionListener {

	private vSettings form;
	private String[] usOld;
	private boolean[] rolOld;
	LoginController loginController = new LoginController();
	UserService userServiceSQL = new UserService();
	RoleService roleServiceSQL = new RoleService();
	NodeHash nodeDefault = new NodeHash(0, "<SELECCIONAR>");
	NodeHash noEditable = new NodeHash(0, "No editable");
	
	public SettingsController(vSettings form) {
		this.form = form;
		List<RSButtonMaterialIconDos> buttons = Arrays.asList(this.form.btnExit, this.form.btnAccountEditar, this.form.btnUsers, this.form.btnSecurity, this.form.btnAddUser,
				this.form.btnDeleteUser, this.form.btnBackUp, this.form.btnRestore, this.form.btnSaveRole, this.form.btnGoVideo, this.form.btnSend);
		buttons.forEach(button -> button.addActionListener(this));
		this.form.cbxUsers.addActionListener(this);
		init();
	}
	public void init() {
		Input.onlyNumbers(this.form.txtTelefonoUsuario);
		Input.onlyNumbers(this.form.txtUPhone);
		Input.validateMaxLength(this.form.txtTelefonoUsuario, 9);
		Input.validateMaxLength(this.form.txtUPhone, 9);
		this.form.txtNombreUsuario.setText(LoginController.USER.getUsername());
		this.form.txtPasswordUsuario.setText(LoginController.USER.getPassword());
		this.form.txtFirstName.setText(LoginController.USER.getFirstName());
		this.form.txtLastName.setText(LoginController.USER.getLastName());
		this.form.txtTelefonoUsuario.setText(String.valueOf(LoginController.USER.getPhone()));
		this.form.txtEmailUsuario.setText(LoginController.USER.getEmail());
		this.form.lblUsuarioSettings.setText(LoginController.USER.getUsername());
		boolean enableADMIN = isAdmin();
		List<RSButtonMaterialIconDos> buttons = Arrays.asList(this.form.btnUsers, this.form.btnSecurity);
		buttons.forEach(button -> button.setEnabled(enableADMIN));
		loadUserTable();
		loadCbxUsers();
	}

	public void exit() {
		int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas salir?", "Confirmación",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	//VALIDA QUE SE HABILITEN OPCIONES DE ADMINISTRADOR SOLAMENTE PARA USUARIOS CON PERMISO DE ADMINISTRADOR
	public boolean isAdmin() {
		int idUser = LoginController.USER.getIdUser();
		LinkedList roles = roleServiceSQL.findAll();
		for(int i=0; i<roles.size(); i++) {
			Roles role = (Roles) roles.get(i);
			if(role.getIdUser() == idUser && role.getRole().equals("ROLE_ADMIN"))
				return true;
		}
		return false;
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
		}else if(press == this.form.cbxUsers){
			loadCbxRole();
		}else if(press == this.form.btnSaveRole) {
			saveModifiedRole();
		}else if(press == this.form.btnGoVideo){
			openTutorial();
		}else if(press == this.form.btnSend) {
			sendEmail();
		}
	}

	// PERMITE EDITAR EL NOMBRE DE USUARIOS DEL BOTON
	public void editNameButtonn() {
		boolean opcion = this.form.btnAccountEditar.getText().toString().toLowerCase().equals("editar");

		if (opcion) {
			this.form.btnAccountEditar.setText("GUARDAR");
			this.form.btnAccountEditar.setIcons(ICONS.SAVE);
		} else {
			this.form.btnAccountEditar.setText("EDITAR");
			this.form.btnAccountEditar.setIcons(ICONS.EDIT);
		}
	}

	// PERMITE HABILITAR LA EDICION DE LA CAJA DE TEXTO DEPENDIENDO DEL ESTADO
	public void enableTextAccount() {
		boolean opcion = this.form.btnAccountEditar.getText().toString().toUpperCase().equals("EDITAR");
		List<JTextField> txtFields = Arrays.asList(this.form.txtNombreUsuario, this.form.txtPasswordUsuario,
				this.form.txtFirstName, this.form.txtLastName, this.form.txtTelefonoUsuario, this.form.txtEmailUsuario);
		txtFields.forEach(field -> field.setEditable(!opcion));
		if(!opcion) {
			this.form.txtPasswordUsuario.setText(loginController.desencryptor(LoginController.USER.getPassword(), 5));
		}
		else {
			this.form.txtPasswordUsuario.setText(LoginController.USER.getPassword());
		}
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
		boolean opcion = this.form.btnAccountEditar.getText().toString().toUpperCase().equals("EDITAR");
	    for (int i = 1; i < usNew.length; i++) {
	        if (!Objects.equals(usOld[i], usNew[i])) {
	            return true;
	        }
	    }
	    if(!opcion) {
	    	if(!Objects.equals(loginController.encryptor(usNew[0], 5), usOld[0])) {
	    		return true;
	    	}
	    }
	    		
	    return false;
	}

	//PERMITE VERIFICAR LOS CAMBIOS, DE HABERLOS LOS GUARDA EN LA BASE DE DATOS
	public void saveModified() {
		boolean opcion = this.form.btnAccountEditar.getText().toString().toUpperCase().equals("EDITAR");
		if(opcion) {
			usOld = getUserActive();
			if(usOld[1].equals("ADMIN")) {
				JOptionPane.showMessageDialog(null, "No puede editar a SUPER USUARIO");
				return;
			}
		}else {
			String[] usNew = getUserActive();
			if(isModified(usOld, usNew)) {
				if(!usOld[1].equals("ADMIN")) {
					userServiceSQL.updateUser(getUserAccount());
					LoginController.USER = getUserAccount();
				}
			}else {
				JOptionPane.showMessageDialog(null, "No se encontraron cambios");
			}
		}
		editNameButtonn();
		enableTextAccount();
		loadUserTable();
	}
	
	//PERMITE ALMACENAR EL VALOR DE LOS REGISTROS EN LA CUENTA ACTIVA EN UN OBJETO
	public User getUserAccount() {
		String nickName = this.form.txtNombreUsuario.getText();
		String password = this.form.txtPasswordUsuario.getText();
		String firstName = this.form.txtFirstName.getText();
		String lastName = this.form.txtLastName.getText();
		int phone = Integer.parseInt(this.form.txtTelefonoUsuario.getText());
		String email = this.form.txtEmailUsuario.getText();
		User user = new User(LoginController.USER.getIdUser(), nickName, loginController.encryptor(password, 5), firstName, lastName, phone, email);
		return user;
	}
	
	//PERMITE CARGAR LA TABLA DE LISTA DE USUARIOS EN LA BASE DE DATOS
	public void loadUserTable() {
		DefaultTableModel model = new DefaultTableModel(new String[] {"NickName", "Password", "Nombre", "Apellido", "Telefono", "Email"},0);
		LinkedList usuarios = userServiceSQL.findAll();
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
		String nickName = this.form.txtUNickName.getText();
		String password = this.form.txtUPassword.getText();
		String firstName = this.form.txtUFirstName.getText();
		String lastName = this.form.txtULastName.getText();
		int phone = Integer.parseInt(this.form.txtUPhone.getText());
		String email = this.form.txtUEmail.getText();
		User user = new User(0, nickName, loginController.encryptor(password, 5), firstName, lastName, phone, email);
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
	public void insertRole(String typeRole) {
		loadUserTable();
		LinkedList users = userServiceSQL.findAll();
		User user = (User) users.get(users.size()-1);
		Roles role = new Roles(0, typeRole, user.getIdUser());
		roleServiceSQL.insertRole(role);
	}
	
	//PERMITE GUARDAR UN NUEVO REGISTRO DE USUARIOS, SIN PRIVILEGIOS DE ADMIN
	public void saveInsertUser() {
		if(isComplete()) {
			User user = getUserInsert();
			String password = loginController.encryptor(String.valueOf(JOptionPane.showInputDialog("CONFIRME SU PASSWORD, PORFAVOR!")), 5);
			if(password.equals(user.getPassword())) {
				userServiceSQL.insertUser(user);
				insertRole("ROLE_USER");
				clearUsers();
			}else {
				JOptionPane.showMessageDialog(null, "Confirmacion incorrecta, digite sus credenciales nuevamente porfavor!");
				this.form.txtUPassword.selectAll();
				this.form.txtUPassword.requestFocus();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Complete todos los campos por favor!");
		}
		loadUserTable();
		init();
	}
	
	//PERMITE LIMPIAR LAS CASILLAS DE INSERCCION DE DATOS DESPUES DE GUARDAR LA SELECCION
	public void clearUsers() {
		List<JTextField> textField = Arrays.asList(this.form.txtUNickName, this.form.txtUPassword, this.form.txtUFirstName, this.form.txtULastName, this.form.txtUPhone, this.form.txtUEmail);
		textField.forEach(field -> field.setText(""));
	}
	
	//PERMITE REASIGNAR PERMISOS DE USUARIOS (ADMIN, USER)
	public void deleteRoles(String rol, int idUser) {
		roleServiceSQL.deleteRole(rol, idUser);
	}
	
	//PERMITE ELIMINAR UN USUARIO DADO SU ID
	public void deleteUser() {
		if(this.form.tableUsers.getSelectedRow()<0) {
			clearUsers();
			return;
		}
		LinkedList usuarios = userServiceSQL.findAll();
		
		int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar la seleccion?", "Alerta", JOptionPane.YES_NO_OPTION);
		
		if(opcion == JOptionPane.YES_OPTION) {
			int idDelete = this.form.tableUsers.getSelectedRow();
			User user = (User) usuarios.get(idDelete);
			if(user == null) {
				JOptionPane.showMessageDialog(null, "El elemento no se encuentra en la lista");
				return;
			}else {
				if(user.getIdUser() == LoginController.USER.getIdUser()) {
					JOptionPane.showMessageDialog(null, "No puede eliminar el USER ACTIVO");
					return;
				}
				if(user.getUsername().equals("ADMIN")) {
					JOptionPane.showMessageDialog(null, "No puede eliminar el SUPER USER");
					return;
				}
				deleteRoles("ROLE_USER", user.getIdUser());
				deleteRoles("ROLE_ADMIN", user.getIdUser());
				userServiceSQL.deleteUser(user.getIdUser());
				loadUserTable();
				init();
			}
		}
	}
	
	//METODO PERMITE LLENAR EL COMBO BOX PARA EL ADMINISTRADOR, EN CASO DESEA CAMBIAR PERMISOS
	public void loadCbxUsers() {
		LinkedList users = userServiceSQL.findAll();
		// limpiamos el combo
		this.form.cbxUsers.removeAllItems();
		// un default para el combo
		this.form.cbxUsers.addItem(nodeDefault);
		// iteramos
		for (int i = 1; i < users.size(); i++) {
			User user = (User) users.get(i);
			// se crea el nodo
			NodeHash nodeUser = new NodeHash(user.getIdUser(), user.getUsername());
			// se agrega el nodo al combo, como tiene to string imprimira el tostring
			this.form.cbxUsers.addItem(nodeUser);
		}
	}
	
	//METODO PERMITE ASIGNAR LOS ROLES DE CADA USUARIO UNA VEZ SE SELECCIONES UN ITEM DEL COMBO BOX (USUARIO)
	public void loadCbxRole() {
		this.form.chckbxRoleAdmin.setSelected(false);
		this.form.chckbxRoleUser.setSelected(false);
		User user = getSelectedCbxUser();
		if(user == null) return;
		LinkedList roles = roleServiceSQL.findAll();
		for(int i=0; i<roles.size(); i++){
			Roles role = (Roles) roles.get(i);
			if(role.getIdUser() == user.getIdUser()) {
				if(role.getRole().equals("ROLE_ADMIN") && role.getRole().equals("ROLE_USER")) {
					this.form.chckbxRoleAdmin.setSelected(true);
					this.form.chckbxRoleUser.setSelected(true);
				}else if(role.getRole().equals("ROLE_ADMIN")) {
					this.form.chckbxRoleAdmin.setSelected(true);
				}else if(role.getRole().endsWith("ROLE_USER")){
					this.form.chckbxRoleUser.setSelected(true);
				}else {
					this.form.chckbxRoleAdmin.setSelected(false);
					this.form.chckbxRoleUser.setSelected(false);
				}
				rolOld = getRolesUser();
			}
		}
	}
	
	//PERMITE OBTENER EL OBJETO QUE HACE REFERENCIA AL ITEM DEL COMBO BOX
	public User getSelectedCbxUser() {
		if(this.form.cbxUsers.getSelectedItem() == null) return null;
		if (this.form.cbxUsers.getSelectedIndex() == 0)
			return null;
		LinkedList users = userServiceSQL.findAll();
		for (int i = 0; i < users.size(); i++) {
			User user = (User) users.get(i);
			if (user.getUsername().equals(this.form.cbxUsers.getSelectedItem().toString()))
				return user;
		}
		return null;
	}
	
	//PERMITE OBTENER UN ARREGLO DE BOOLEANOS QUE QUE VALIDAD SI LOS ROLES ADMIN O USER ESTAN ACTIVOS
	public boolean[] getRolesUser() {
		if(this.form.cbxUsers.getSelectedIndex() == 0)return null;
		
		boolean[] roles = {
				this.form.chckbxRoleAdmin.isSelected(),		//estado administrador si es true
				this.form.chckbxRoleUser.isSelected()		//estado usuarios si es true
		};
		return roles;
	}
	
	//PERMITE IDENTIFICAR SI SE HA MODIFICADO LOS PERMISOS
	public boolean isModifiedRoles(boolean[] rolsOld, boolean[] rolsNew) {
		if(rolsNew[0] != rolsOld[0])
			return true;
		if( rolsNew[1] != rolsOld[1])
			return true;
		return false;
	}
	
	//PERMITE ALMACENCAR EN LA BD LOS NUEVOS ROLES DEL USUARIOS, SI HAY MODIFICACIONES
	public void saveModifiedRole() {
		if(rolOld == null) {
			JOptionPane.showMessageDialog(null, "Debe Seleccionar un usuario");
			return;
		};
		
		boolean[] rolNew = getRolesUser();
		if(rolNew == null) {
			JOptionPane.showMessageDialog(null, "Debe Seleccionar un usuario");
			return;
		}
		if(isModifiedRoles(rolOld, rolNew)) {
			if(!rolNew[0] && !rolNew[1]) {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un rol para el usuario");
				return;
			}
			
			String rolAdmin = rolNew[0] ? "ROLE_ADMIN" : "" ;
			String rolUser = rolNew[1] ? "ROLE_USER" : "";
			User user = getSelectedCbxUser();
			
			if(!rolAdmin.isEmpty()) {
				roleServiceSQL.insertRole(new Roles(0, rolAdmin, user.getIdUser()));
			}else {
				roleServiceSQL.deleteRole("ROLE_ADMIN", user.getIdUser());
			}
			
			if(!rolUser.isEmpty()) {
				roleServiceSQL.insertRole(new Roles(0, rolUser, user.getIdUser()));
			}else {
				roleServiceSQL.deleteRole("ROLE_USER", user.getIdUser());
			}
			clearRoles();
			loadCbxRole();
		}else {
			JOptionPane.showMessageDialog(null, "No se registraron cambios");
		}
	}
	
	//PERMITE LIMPIAR DESPUES DEL CAMBIO DE ROLES
	public void clearRoles() {
		this.form.chckbxRoleAdmin.setSelected(false);
		this.form.chckbxRoleUser.setSelected(false);
		this.form.cbxUsers.setSelectedIndex(0);
	}
	
	//PERMITE VISUALIZAR UN VIDEO EXPLICATICO A YOUTUBE SOBRE EL FUNCIONAMIENTO DEL PROGRAMA
	public void openTutorial() {
		String youtubeURL = "https://www.youtube.com/watch?v=EE-xtCF3T94";
		try {
			Desktop.getDesktop().browse(new URI(youtubeURL));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//PERMITE ENCIAR UN CORREO A SOPORTE CON LA CONSULTA DEL PROGRAMA, el metodo puede extenderse a enviar correos reales, oero siendo
	//para usos explicativos, conviene solo expresar que se envio el mensaje de forma de confirmacion y sin logica por demas.
	public void sendEmail() {
		JOptionPane.showMessageDialog(null, "Enviado correctamente!, pronto nos contactaremos contigo.");
	}
	public void showView() {
		form.setVisible(true);
	}

}
