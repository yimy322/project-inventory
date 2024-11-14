package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import rojeru_san.rsbutton.RSButtonSelectedIcon;
import javax.swing.ImageIcon;
import RSMaterialComponent.RSButtonMaterialIconDos;
import rojeru_san.efectos.ValoresEnum.ICONS;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class vSettings extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6;
	private JPanel panel_help, panel_users, panel_myAccount, panel_security;
	private JLayeredPane layeredPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JLabel lblNombreUsuario, lblPasswordUsuario, lblNombreCompleto, lblTelefonoUsuario, lblEmailUsuario, lblUsuarioSettings;
	public RSButtonMaterialIconDos btnExit;
	
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
		
		RSButtonMaterialIconDos btn_myAccount = new RSButtonMaterialIconDos();
		btn_myAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_myAccount);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btn_myAccount.setForegroundIconHover(new Color(0, 0, 0));
		btn_myAccount.setForeground(new Color(0, 0, 0));
		btn_myAccount.setForegroundHover(new Color(0, 0, 0));
		btn_myAccount.setRippleColor(new Color(198, 225, 241));
		btn_myAccount.setForegroundIcon(new Color(0, 0, 0));
		btn_myAccount.setForegroundText(new Color(0, 0, 0));
		btn_myAccount.backgroundHover = new Color(228, 233, 232);
		btn_myAccount.setBackground(new Color(228, 233, 232));
		btn_myAccount.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_myAccount.setText("Mi Cuenta");
		btn_myAccount.setIcons(ICONS.PERSON);
		btn_myAccount.setBounds(10, 11, 155, 50);
		panel_Ajustes.add(btn_myAccount);
		
		JLabel lblNewLabel = new JLabel("---------------------------");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(0, 82, 175, 14);
		panel_Ajustes.add(lblNewLabel);
		
		RSButtonMaterialIconDos btn_users = new RSButtonMaterialIconDos();
		btn_users.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_users);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btn_users.setText("Usuarios");
		btn_users.setRippleColor(new Color(198, 225, 241));
		btn_users.setIcons(ICONS.PEOPLE);
		btn_users.setForegroundText(Color.BLACK);
		btn_users.setForegroundIconHover(Color.BLACK);
		btn_users.setForegroundIcon(Color.BLACK);
		btn_users.setForegroundHover(Color.BLACK);
		btn_users.setForeground(Color.BLACK);
		btn_users.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_users.backgroundHover = new Color(228, 233, 232);
		btn_users.setBackgroundHover(new Color(228, 233, 232));
		btn_users.setBackground(new Color(228, 233, 232));
		btn_users.setBounds(10, 120, 155, 50);
		panel_Ajustes.add(btn_users);
		
		RSButtonMaterialIconDos btn_security = new RSButtonMaterialIconDos();
		btn_security.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_security);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btn_security.setText("Seguridad");
		btn_security.setRippleColor(new Color(198, 225, 241));
		btn_security.setIcons(ICONS.SECURITY);
		btn_security.setForegroundText(Color.BLACK);
		btn_security.setForegroundIconHover(Color.BLACK);
		btn_security.setForegroundIcon(Color.BLACK);
		btn_security.setForegroundHover(Color.BLACK);
		btn_security.setForeground(Color.BLACK);
		btn_security.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_security.backgroundHover = new Color(228, 233, 232);
		btn_security.setBackgroundHover(new Color(228, 233, 232));
		btn_security.setBackground(new Color(228, 233, 232));
		btn_security.setBounds(10, 191, 155, 50);
		panel_Ajustes.add(btn_security);
		
		RSButtonMaterialIconDos btn_help = new RSButtonMaterialIconDos();
		btn_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_help);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btn_help.setText("Ayuda");
		btn_help.setRippleColor(new Color(198, 225, 241));
		btn_help.setIcons(ICONS.HELP);
		btn_help.setForegroundText(Color.BLACK);
		btn_help.setForegroundIconHover(Color.BLACK);
		btn_help.setForegroundIcon(Color.BLACK);
		btn_help.setForegroundHover(Color.BLACK);
		btn_help.setForeground(Color.BLACK);
		btn_help.setFont(new Font("Dialog", Font.BOLD, 12));
		btn_help.backgroundHover = new Color(228, 233, 232);
		btn_help.setBackgroundHover(new Color(228, 233, 232));
		btn_help.setBackground(new Color(228, 233, 232));
		btn_help.setBounds(10, 262, 155, 50);
		panel_Ajustes.add(btn_help);
		
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
		
		lblNombreUsuario = new JLabel("Nombre de Usuario:");
		lblNombreUsuario.setForeground(Color.BLUE);
		lblNombreUsuario.setBounds(665, 77, 265, 28);
		panel_myAccount.add(lblNombreUsuario);
		
		lblPasswordUsuario = new JLabel("Password de Usuario:");
		lblPasswordUsuario.setForeground(Color.BLUE);
		lblPasswordUsuario.setBounds(665, 127, 265, 28);
		panel_myAccount.add(lblPasswordUsuario);
		
		lblNombreCompleto = new JLabel("Nombre completo:");
		lblNombreCompleto.setForeground(Color.BLUE);
		lblNombreCompleto.setBounds(665, 180, 265, 28);
		panel_myAccount.add(lblNombreCompleto);
		
		lblTelefonoUsuario = new JLabel("telefono de usuario:");
		lblTelefonoUsuario.setForeground(Color.BLUE);
		lblTelefonoUsuario.setBounds(665, 236, 265, 28);
		panel_myAccount.add(lblTelefonoUsuario);
		
		lblEmailUsuario= new JLabel("Email de usuario");
		lblEmailUsuario.setForeground(Color.BLUE);
		lblEmailUsuario.setBounds(665, 293, 265, 28);
		panel_myAccount.add(lblEmailUsuario);
		
		RSButtonMaterialIconDos btnmtrlcndsAditar = new RSButtonMaterialIconDos();
		btnmtrlcndsAditar.setIcons(ICONS.EDIT);
		btnmtrlcndsAditar.setText("EDITAR");
		btnmtrlcndsAditar.setForegroundText(new Color(0, 0, 0));
		btnmtrlcndsAditar.setForegroundIconHover(new Color(0, 0, 0));
		btnmtrlcndsAditar.setForegroundIcon(new Color(0, 0, 0));
		btnmtrlcndsAditar.setForegroundHover(new Color(0, 0, 0));
		btnmtrlcndsAditar.setForeground(new Color(0, 0, 0));
		btnmtrlcndsAditar.setBackground(new Color(0, 158, 234));
		btnmtrlcndsAditar.setBounds(560, 351, 200, 50);
		panel_myAccount.add(btnmtrlcndsAditar);
		
		panel_users = new JPanel();
		panel_users.setBorder(new TitledBorder(null, "Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_users.setBackground(new Color(255, 255, 255));
		layeredPane.add(panel_users, "name_3353228152900");
		panel_users.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 196, 963, 231);
		panel_users.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_6 = new JLabel("User Name:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(72, 32, 84, 22);
		panel_users.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Password:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_1.setBounds(72, 83, 84, 22);
		panel_users.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_2 = new JLabel("Password:");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_2.setBounds(370, 28, 84, 22);
		panel_users.add(lblNewLabel_6_2);
		
		JLabel lblNewLabel_6_3 = new JLabel("Firt Name:");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_3.setBounds(370, 85, 84, 22);
		panel_users.add(lblNewLabel_6_3);
		
		JLabel lblNewLabel_6_4 = new JLabel("Last Name:");
		lblNewLabel_6_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_4.setBounds(671, 28, 84, 22);
		panel_users.add(lblNewLabel_6_4);
		
		JLabel lblNewLabel_6_5 = new JLabel("Telephone:");
		lblNewLabel_6_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_5.setBounds(671, 85, 84, 22);
		panel_users.add(lblNewLabel_6_5);
		
		JLabel lblNewLabel_6_5_1 = new JLabel("Email:");
		lblNewLabel_6_5_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_5_1.setBounds(72, 138, 84, 22);
		panel_users.add(lblNewLabel_6_5_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(166, 30, 149, 28);
		panel_users.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(166, 81, 149, 28);
		panel_users.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(464, 26, 149, 28);
		panel_users.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(464, 83, 149, 28);
		panel_users.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(765, 26, 149, 28);
		panel_users.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(765, 83, 149, 28);
		panel_users.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_6.setColumns(10);
		textField_6.setBounds(166, 136, 149, 28);
		panel_users.add(textField_6);
		
		RSButtonMaterialIconDos btnmtrlcndsAgregar = new RSButtonMaterialIconDos();
		btnmtrlcndsAgregar.setText("AGREGAR");
		btnmtrlcndsAgregar.setForegroundText(new Color(0, 0, 0));
		btnmtrlcndsAgregar.setForegroundIconHover(new Color(0, 0, 0));
		btnmtrlcndsAgregar.setForegroundIcon(new Color(0, 0, 0));
		btnmtrlcndsAgregar.setForegroundHover(new Color(0, 0, 0));
		btnmtrlcndsAgregar.setForeground(new Color(0, 0, 0));
		btnmtrlcndsAgregar.setBackground(new Color(0, 158, 234));
		btnmtrlcndsAgregar.setBounds(445, 135, 200, 50);
		panel_users.add(btnmtrlcndsAgregar);
		
		RSButtonMaterialIconDos btnmtrlcndsEliminar = new RSButtonMaterialIconDos();
		btnmtrlcndsEliminar.setText("ELIMINAR");
		btnmtrlcndsEliminar.setForegroundText(Color.BLACK);
		btnmtrlcndsEliminar.setForegroundIconHover(Color.BLACK);
		btnmtrlcndsEliminar.setForegroundIcon(Color.BLACK);
		btnmtrlcndsEliminar.setForegroundHover(Color.BLACK);
		btnmtrlcndsEliminar.setForeground(Color.BLACK);
		btnmtrlcndsEliminar.setBackground(new Color(255, 0, 128));
		btnmtrlcndsEliminar.setBounds(701, 135, 200, 50);
		panel_users.add(btnmtrlcndsEliminar);
		
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
