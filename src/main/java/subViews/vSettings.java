package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import RSMaterialComponent.RSButtonMaterialIconDos;
import rojeru_san.efectos.ValoresEnum.ICONS;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class vSettings extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTable tableUsers;
	public JTextField txtUNickName, txtUPassword, txtUFirstName, txtULastName, txtUPhone, txtUEmail;
	private JPanel panel_help, panel_users, panel_myAccount, panel_security;
	private JLayeredPane layeredPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JTextField txtNombreUsuario, txtPasswordUsuario, txtFirstName, txtLastName, txtTelefonoUsuario, txtEmailUsuario;
	public JLabel lblUsuarioSettings;
	public RSButtonMaterialIconDos btnExit, btnAccountEditar, btnUsers, btnSecurity, btnAddUser, btnDeleteUser;
	
	/**
	 * Create the panel.
	 */
	public vSettings() {
		setSize(1186, 518);
		setLocation(0, 0);
		setBorder(new TitledBorder(null, "Ajustes del Sistema", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(new Color(228, 233, 232));
		setLayout(null);
		
		JPanel panel_Ajustes = new JPanel();
		panel_Ajustes.setBackground(new Color(228, 233, 232));
		panel_Ajustes.setBounds(10, 25, 175, 438);
		add(panel_Ajustes);
		panel_Ajustes.setLayout(null);
		
		RSButtonMaterialIconDos btnMyAccount = new RSButtonMaterialIconDos();
		btnMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_myAccount);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnMyAccount.setForegroundIconHover(new Color(0, 0, 0));
		btnMyAccount.setForeground(new Color(0, 0, 0));
		btnMyAccount.setForegroundHover(new Color(0, 0, 0));
		btnMyAccount.setRippleColor(new Color(198, 225, 241));
		btnMyAccount.setForegroundIcon(new Color(0, 0, 0));
		btnMyAccount.setForegroundText(new Color(0, 0, 0));
		btnMyAccount.backgroundHover = new Color(228, 233, 232);
		btnMyAccount.setBackground(new Color(228, 233, 232));
		btnMyAccount.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMyAccount.setText("Mi Cuenta");
		btnMyAccount.setIcons(ICONS.PERSON);
		btnMyAccount.setBounds(10, 11, 155, 50);
		panel_Ajustes.add(btnMyAccount);
		
		JLabel lblNewLabel = new JLabel("---------------------------");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(0, 82, 175, 14);
		panel_Ajustes.add(lblNewLabel);
		
		btnUsers = new RSButtonMaterialIconDos();
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_users);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnUsers.setText("Usuarios");
		btnUsers.setRippleColor(new Color(198, 225, 241));
		btnUsers.setIcons(ICONS.PEOPLE);
		btnUsers.setForegroundText(Color.BLACK);
		btnUsers.setForegroundIconHover(Color.BLACK);
		btnUsers.setForegroundIcon(Color.BLACK);
		btnUsers.setForegroundHover(Color.BLACK);
		btnUsers.setForeground(Color.BLACK);
		btnUsers.setFont(new Font("Dialog", Font.BOLD, 12));
		btnUsers.backgroundHover = new Color(228, 233, 232);
		btnUsers.setBackgroundHover(new Color(228, 233, 232));
		btnUsers.setBackground(new Color(228, 233, 232));
		btnUsers.setBounds(10, 120, 155, 50);
		panel_Ajustes.add(btnUsers);
		
		btnSecurity = new RSButtonMaterialIconDos();
		btnSecurity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_security);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnSecurity.setText("Seguridad");
		btnSecurity.setRippleColor(new Color(198, 225, 241));
		btnSecurity.setIcons(ICONS.SECURITY);
		btnSecurity.setForegroundText(Color.BLACK);
		btnSecurity.setForegroundIconHover(Color.BLACK);
		btnSecurity.setForegroundIcon(Color.BLACK);
		btnSecurity.setForegroundHover(Color.BLACK);
		btnSecurity.setForeground(Color.BLACK);
		btnSecurity.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSecurity.backgroundHover = new Color(228, 233, 232);
		btnSecurity.setBackgroundHover(new Color(228, 233, 232));
		btnSecurity.setBackground(new Color(228, 233, 232));
		btnSecurity.setBounds(10, 191, 155, 50);
		panel_Ajustes.add(btnSecurity);
		
		RSButtonMaterialIconDos btnHelp = new RSButtonMaterialIconDos();
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_help);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnHelp.setText("Ayuda");
		btnHelp.setRippleColor(new Color(198, 225, 241));
		btnHelp.setIcons(ICONS.HELP);
		btnHelp.setForegroundText(Color.BLACK);
		btnHelp.setForegroundIconHover(Color.BLACK);
		btnHelp.setForegroundIcon(Color.BLACK);
		btnHelp.setForegroundHover(Color.BLACK);
		btnHelp.setForeground(Color.BLACK);
		btnHelp.setFont(new Font("Dialog", Font.BOLD, 12));
		btnHelp.backgroundHover = new Color(228, 233, 232);
		btnHelp.setBackgroundHover(new Color(228, 233, 232));
		btnHelp.setBackground(new Color(228, 233, 232));
		btnHelp.setBounds(10, 262, 155, 50);
		panel_Ajustes.add(btnHelp);
		
		btnExit = new RSButtonMaterialIconDos();
		btnExit.setText("Salir");
		btnExit.setRippleColor(new Color(198, 225, 241));
		btnExit.setIcons(ICONS.EXIT_TO_APP);
		btnExit.setForegroundText(Color.BLACK);
		btnExit.setForegroundIconHover(Color.BLACK);
		btnExit.setForegroundIcon(Color.BLACK);
		btnExit.setForegroundHover(Color.BLACK);
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExit.backgroundHover = new Color(228, 233, 232);
		btnExit.setBackgroundHover(new Color(228, 233, 232));
		btnExit.setBackground(new Color(228, 233, 232));
		btnExit.setBounds(10, 338, 155, 50);
		panel_Ajustes.add(btnExit);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(195, 25, 983, 438);
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		panel_myAccount = new JPanel();
		panel_myAccount.setBorder(new TitledBorder(null, "Mi Cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_myAccount.setBackground(new Color(255, 255, 255));
		layeredPane.add(panel_myAccount, "name_1962304772800");
		panel_myAccount.setLayout(null);
		
		ImageIcon originalIcon = new ImageIcon(vSettings.class.getResource("/necesario/iconoFoto.png"));
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(resizedIcon);
		lblNewLabel_1.setBounds(36, 38, 259, 233);
		panel_myAccount.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("USUARIO ACTIVO");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setForeground(new Color(0, 0, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(vSettings.class.getResource("/necesario/ver1.png")));
		lblNewLabel_2.setBounds(36, 292, 259, 21);
		panel_myAccount.add(lblNewLabel_2);
		
		lblUsuarioSettings = new JLabel("UNKNOWN");
		lblUsuarioSettings.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsuarioSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarioSettings.setBounds(36, 330, 259, 51);
		panel_myAccount.add(lblUsuarioSettings);
		
		JLabel lblNewLabel_4 = new JLabel("DETALLES");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(560, 29, 206, 21);
		panel_myAccount.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nombre de Usuario:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(446, 76, 183, 28);
		panel_myAccount.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Password:");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5_1.setBounds(446, 126, 183, 28);
		panel_myAccount.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Full Name:");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5_2.setBounds(446, 179, 183, 28);
		panel_myAccount.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Telefono:");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5_3.setBounds(446, 236, 183, 28);
		panel_myAccount.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_5_4 = new JLabel("Email:");
		lblNewLabel_5_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5_4.setBounds(446, 292, 183, 28);
		panel_myAccount.add(lblNewLabel_5_4);
		
		txtNombreUsuario = new JTextField("Nombre de Usuario:");
		txtNombreUsuario.setEditable(false);
		txtNombreUsuario.setForeground(Color.BLUE);
		txtNombreUsuario.setBounds(665, 77, 265, 28);
		panel_myAccount.add(txtNombreUsuario);
		
		txtPasswordUsuario = new JTextField("Password de Usuario:");
		txtPasswordUsuario.setEditable(false);
		txtPasswordUsuario.setForeground(Color.BLUE);
		txtPasswordUsuario.setBounds(665, 127, 265, 28);
		panel_myAccount.add(txtPasswordUsuario);
		
		txtFirstName = new JTextField("Nombre completo:");
		txtFirstName.setEditable(false);
		txtFirstName.setForeground(Color.BLUE);
		txtFirstName.setBounds(665, 180, 128, 28);
		panel_myAccount.add(txtFirstName);
		
		txtTelefonoUsuario = new JTextField("telefono de usuario:");
		txtTelefonoUsuario.setEditable(false);
		txtTelefonoUsuario.setForeground(Color.BLUE);
		txtTelefonoUsuario.setBounds(665, 236, 265, 28);
		panel_myAccount.add(txtTelefonoUsuario);
		
		txtEmailUsuario= new JTextField("Email de usuario");
		txtEmailUsuario.setEditable(false);
		txtEmailUsuario.setForeground(Color.BLUE);
		txtEmailUsuario.setBounds(665, 293, 265, 28);
		panel_myAccount.add(txtEmailUsuario);
		
		btnAccountEditar = new RSButtonMaterialIconDos();
		btnAccountEditar.setIcons(ICONS.EDIT);
		btnAccountEditar.setText("EDITAR");
		btnAccountEditar.setForegroundText(new Color(0, 0, 0));
		btnAccountEditar.setForegroundIconHover(new Color(0, 0, 0));
		btnAccountEditar.setForegroundIcon(new Color(0, 0, 0));
		btnAccountEditar.setForegroundHover(new Color(0, 0, 0));
		btnAccountEditar.setForeground(new Color(0, 0, 0));
		btnAccountEditar.setBackground(new Color(0, 158, 234));
		btnAccountEditar.setBounds(560, 351, 200, 50);
		panel_myAccount.add(btnAccountEditar);
		
		txtLastName = new JTextField("Nombre completo:");
		txtLastName.setForeground(Color.BLUE);
		txtLastName.setEditable(false);
		txtLastName.setBounds(803, 181, 128, 28);
		panel_myAccount.add(txtLastName);
		
		panel_users = new JPanel();
		panel_users.setBorder(new TitledBorder(null, "Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_users.setBackground(new Color(255, 255, 255));
		layeredPane.add(panel_users, "name_3353228152900");
		panel_users.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 196, 963, 231);
		panel_users.add(scrollPane);
		
		tableUsers = new JTable();
		scrollPane.setViewportView(tableUsers);
		
		JLabel lblNewLabel_6 = new JLabel("User Name:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(72, 32, 84, 22);
		panel_users.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Password:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_1.setBounds(72, 83, 84, 22);
		panel_users.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_3 = new JLabel("Firt Name:");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_3.setBounds(72, 139, 84, 22);
		panel_users.add(lblNewLabel_6_3);
		
		JLabel lblNewLabel_6_4 = new JLabel("Last Name:");
		lblNewLabel_6_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_4.setBounds(370, 34, 84, 22);
		panel_users.add(lblNewLabel_6_4);
		
		JLabel lblNewLabel_6_5 = new JLabel("Telephone:");
		lblNewLabel_6_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_5.setBounds(677, 32, 84, 22);
		panel_users.add(lblNewLabel_6_5);
		
		JLabel lblNewLabel_6_5_1 = new JLabel("Email:");
		lblNewLabel_6_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_5_1.setBounds(370, 79, 84, 22);
		panel_users.add(lblNewLabel_6_5_1);
		
		txtUNickName = new JTextField();
		txtUNickName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUNickName.setBounds(166, 30, 149, 28);
		panel_users.add(txtUNickName);
		txtUNickName.setColumns(10);
		
		txtUPassword = new JTextField();
		txtUPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUPassword.setColumns(10);
		txtUPassword.setBounds(166, 81, 149, 28);
		panel_users.add(txtUPassword);
		
		txtUFirstName = new JTextField();
		txtUFirstName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUFirstName.setColumns(10);
		txtUFirstName.setBounds(166, 137, 149, 28);
		panel_users.add(txtUFirstName);
		
		txtULastName = new JTextField();
		txtULastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtULastName.setColumns(10);
		txtULastName.setBounds(464, 32, 149, 28);
		panel_users.add(txtULastName);
		
		txtUPhone = new JTextField();
		txtUPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUPhone.setColumns(10);
		txtUPhone.setBounds(771, 30, 149, 28);
		panel_users.add(txtUPhone);
		
		txtUEmail = new JTextField();
		txtUEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUEmail.setColumns(10);
		txtUEmail.setBounds(464, 77, 456, 28);
		panel_users.add(txtUEmail);
		
		btnAddUser = new RSButtonMaterialIconDos();
		btnAddUser.setText("AGREGAR");
		btnAddUser.setForegroundText(new Color(0, 0, 0));
		btnAddUser.setForegroundIconHover(new Color(0, 0, 0));
		btnAddUser.setForegroundIcon(new Color(0, 0, 0));
		btnAddUser.setForegroundHover(new Color(0, 0, 0));
		btnAddUser.setForeground(new Color(0, 0, 0));
		btnAddUser.setBackground(new Color(0, 158, 234));
		btnAddUser.setBounds(445, 135, 200, 50);
		panel_users.add(btnAddUser);
		
		btnDeleteUser = new RSButtonMaterialIconDos();
		btnDeleteUser.backgroundHover = new Color(255, 0, 128);
		btnDeleteUser.setBackgroundHover(new Color(255, 128, 128));
		btnDeleteUser.setText("ELIMINAR");
		btnDeleteUser.setForegroundText(Color.BLACK);
		btnDeleteUser.setForegroundIconHover(Color.BLACK);
		btnDeleteUser.setForegroundIcon(Color.BLACK);
		btnDeleteUser.setForegroundHover(Color.BLACK);
		btnDeleteUser.setForeground(Color.BLACK);
		btnDeleteUser.setBackground(new Color(255, 0, 128));
		btnDeleteUser.setBounds(701, 135, 200, 50);
		panel_users.add(btnDeleteUser);
		
		panel_help = new JPanel();
		panel_help.setBorder(new TitledBorder(null, "Soporte y Ayuda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_help.setBackground(new Color(255, 255, 255));
		layeredPane.add(panel_help, "name_522177470715100");
		panel_help.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("SOPORTE Y AYUDA");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_7.setBounds(57, 24, 248, 25);
		panel_help.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8_1 = new JLabel("Guia y Seguimiento:");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8_1.setBounds(67, 68, 248, 25);
		panel_help.add(lblNewLabel_8_1);
		
		RSButtonMaterialIconDos btnmtrlcndsVerVideo = new RSButtonMaterialIconDos();
		btnmtrlcndsVerVideo.setIcons(ICONS.PLAY_ARROW);
		btnmtrlcndsVerVideo.setBackground(new Color(255, 0, 0));
		btnmtrlcndsVerVideo.setText("VER VIDEO");
		btnmtrlcndsVerVideo.setBounds(77, 141, 182, 50);
		panel_help.add(btnmtrlcndsVerVideo);
		
		JLabel lblNewLabel_9 = new JLabel("Si cuentas con dudas del funcionamiento del sistema, consulta al video tutorial.");
		lblNewLabel_9.setFont(new Font("Monospaced", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(77, 114, 755, 14);
		panel_help.add(lblNewLabel_9);
		
		JLabel lblNewLabel_8_1_1 = new JLabel("Mas Dudas:");
		lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_8_1_1.setBounds(67, 223, 248, 25);
		panel_help.add(lblNewLabel_8_1_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new TitledBorder(null, "Realizar Consulta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setBounds(77, 270, 647, 122);
		panel_help.add(textArea);
		
		RSButtonMaterialIconDos btnmtrlcndsEnviar = new RSButtonMaterialIconDos();
		btnmtrlcndsEnviar.setIcons(ICONS.SEND);
		btnmtrlcndsEnviar.setText("ENVIAR");
		btnmtrlcndsEnviar.setBackground(new Color(0, 158, 234));
		btnmtrlcndsEnviar.setBounds(747, 310, 173, 50);
		panel_help.add(btnmtrlcndsEnviar);
		
		panel_security = new JPanel();
		panel_security.setBorder(new TitledBorder(null, "Opciones de Seguridad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_security.setBackground(new Color(255, 255, 255));
		layeredPane.add(panel_security, "name_534000352279600");
		panel_security.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Cambiar Permisos:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8.setBounds(51, 39, 169, 27);
		panel_security.add(lblNewLabel_8);
		
		JLabel lbl_userActive = new JLabel("UNKNOWN");
		lbl_userActive.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_userActive.setBounds(208, 104, 130, 27);
		panel_security.add(lbl_userActive);
		
		JRadioButton rdbtn_operator = new JRadioButton("OPERADOR");
		buttonGroup.add(rdbtn_operator);
		rdbtn_operator.setBackground(new Color(255, 255, 255));
		rdbtn_operator.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtn_operator.setBounds(374, 77, 169, 27);
		panel_security.add(rdbtn_operator);
		
		JRadioButton rdbtn_admin = new JRadioButton("ADMINISTRADOR");
		buttonGroup.add(rdbtn_admin);
		rdbtn_admin.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtn_admin.setBackground(Color.WHITE);
		rdbtn_admin.setBounds(374, 128, 169, 27);
		panel_security.add(rdbtn_admin);
		
		JLabel lblNewLabel_8_2 = new JLabel("Configuracion Avanzada:");
		lblNewLabel_8_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8_2.setBounds(66, 264, 232, 27);
		panel_security.add(lblNewLabel_8_2);
		
		RSButtonMaterialIconDos btn_backUp = new RSButtonMaterialIconDos();
		btn_backUp.setIcons(ICONS.PERM_DATA_SETTING);
		btn_backUp.setText("RESPALDO");
		btn_backUp.setBackground(new Color(0, 0, 0));
		btn_backUp.setBounds(130, 323, 200, 50);
		panel_security.add(btn_backUp);
		
		RSButtonMaterialIconDos btn_restore = new RSButtonMaterialIconDos();
		btn_restore.setIcons(ICONS.RESTORE);
		btn_restore.setText("RESTAURAR");
		btn_restore.setBackground(Color.BLACK);
		btn_restore.setBounds(600, 323, 200, 50);
		panel_security.add(btn_restore);
		
		RSButtonMaterialIconDos btn_save = new RSButtonMaterialIconDos();
		btn_save.setIcons(ICONS.SAVE);
		btn_save.setText("GUARDAR");
		btn_save.setBackground(Color.BLACK);
		btn_save.setBounds(636, 88, 200, 50);
		panel_security.add(btn_save);
		
	}
}
