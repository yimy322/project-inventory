package subViews;

import javax.swing.JPanel;
import java.awt.Color;
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

public class vSettings extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public vSettings() {
		setBorder(new TitledBorder(null, "Ajustes del Sistema", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(new Color(228, 233, 232));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(228, 233, 232));
		panel.setBounds(10, 25, 175, 438);
		add(panel);
		panel.setLayout(null);
		
		RSButtonMaterialIconDos btnmtrlcndsMiCuenta = new RSButtonMaterialIconDos();
		btnmtrlcndsMiCuenta.setForegroundIconHover(new Color(0, 0, 0));
		btnmtrlcndsMiCuenta.setForeground(new Color(0, 0, 0));
		btnmtrlcndsMiCuenta.setForegroundHover(new Color(0, 0, 0));
		btnmtrlcndsMiCuenta.setRippleColor(new Color(198, 225, 241));
		btnmtrlcndsMiCuenta.setForegroundIcon(new Color(0, 0, 0));
		btnmtrlcndsMiCuenta.setForegroundText(new Color(0, 0, 0));
		btnmtrlcndsMiCuenta.backgroundHover = new Color(228, 233, 232);
		btnmtrlcndsMiCuenta.setBackground(new Color(228, 233, 232));
		btnmtrlcndsMiCuenta.setFont(new Font("Dialog", Font.BOLD, 12));
		btnmtrlcndsMiCuenta.setText("Mi Cuenta");
		btnmtrlcndsMiCuenta.setIcons(ICONS.PERSON);
		btnmtrlcndsMiCuenta.setBounds(10, 11, 155, 50);
		panel.add(btnmtrlcndsMiCuenta);
		
		JLabel lblNewLabel = new JLabel("---------------------------");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(0, 82, 175, 14);
		panel.add(lblNewLabel);
		
		RSButtonMaterialIconDos btnmtrlcndsUsuarios = new RSButtonMaterialIconDos();
		btnmtrlcndsUsuarios.setText("Usuarios");
		btnmtrlcndsUsuarios.setRippleColor(new Color(198, 225, 241));
		btnmtrlcndsUsuarios.setIcons(ICONS.PEOPLE);
		btnmtrlcndsUsuarios.setForegroundText(Color.BLACK);
		btnmtrlcndsUsuarios.setForegroundIconHover(Color.BLACK);
		btnmtrlcndsUsuarios.setForegroundIcon(Color.BLACK);
		btnmtrlcndsUsuarios.setForegroundHover(Color.BLACK);
		btnmtrlcndsUsuarios.setForeground(Color.BLACK);
		btnmtrlcndsUsuarios.setFont(new Font("Dialog", Font.BOLD, 12));
		btnmtrlcndsUsuarios.backgroundHover = new Color(228, 233, 232);
		btnmtrlcndsUsuarios.setBackgroundHover(new Color(228, 233, 232));
		btnmtrlcndsUsuarios.setBackground(new Color(228, 233, 232));
		btnmtrlcndsUsuarios.setBounds(10, 120, 155, 50);
		panel.add(btnmtrlcndsUsuarios);
		
		RSButtonMaterialIconDos btnmtrlcndsAlmacen = new RSButtonMaterialIconDos();
		btnmtrlcndsAlmacen.setText("Almacen");
		btnmtrlcndsAlmacen.setRippleColor(new Color(198, 225, 241));
		btnmtrlcndsAlmacen.setIcons(ICONS.LOCAL_CONVENIENCE_STORE);
		btnmtrlcndsAlmacen.setForegroundText(Color.BLACK);
		btnmtrlcndsAlmacen.setForegroundIconHover(Color.BLACK);
		btnmtrlcndsAlmacen.setForegroundIcon(Color.BLACK);
		btnmtrlcndsAlmacen.setForegroundHover(Color.BLACK);
		btnmtrlcndsAlmacen.setForeground(Color.BLACK);
		btnmtrlcndsAlmacen.setFont(new Font("Dialog", Font.BOLD, 12));
		btnmtrlcndsAlmacen.backgroundHover = new Color(228, 233, 232);
		btnmtrlcndsAlmacen.setBackgroundHover(new Color(228, 233, 232));
		btnmtrlcndsAlmacen.setBackground(new Color(228, 233, 232));
		btnmtrlcndsAlmacen.setBounds(10, 191, 155, 50);
		panel.add(btnmtrlcndsAlmacen);
		
		RSButtonMaterialIconDos btnmtrlcndsAyuda = new RSButtonMaterialIconDos();
		btnmtrlcndsAyuda.setText("Ayuda");
		btnmtrlcndsAyuda.setRippleColor(new Color(198, 225, 241));
		btnmtrlcndsAyuda.setIcons(ICONS.HELP);
		btnmtrlcndsAyuda.setForegroundText(Color.BLACK);
		btnmtrlcndsAyuda.setForegroundIconHover(Color.BLACK);
		btnmtrlcndsAyuda.setForegroundIcon(Color.BLACK);
		btnmtrlcndsAyuda.setForegroundHover(Color.BLACK);
		btnmtrlcndsAyuda.setForeground(Color.BLACK);
		btnmtrlcndsAyuda.setFont(new Font("Dialog", Font.BOLD, 12));
		btnmtrlcndsAyuda.backgroundHover = new Color(228, 233, 232);
		btnmtrlcndsAyuda.setBackgroundHover(new Color(228, 233, 232));
		btnmtrlcndsAyuda.setBackground(new Color(228, 233, 232));
		btnmtrlcndsAyuda.setBounds(10, 262, 155, 50);
		panel.add(btnmtrlcndsAyuda);
		
		RSButtonMaterialIconDos btnmtrlcndsSalir = new RSButtonMaterialIconDos();
		btnmtrlcndsSalir.setText("Salir");
		btnmtrlcndsSalir.setRippleColor(new Color(198, 225, 241));
		btnmtrlcndsSalir.setIcons(ICONS.EXIT_TO_APP);
		btnmtrlcndsSalir.setForegroundText(Color.BLACK);
		btnmtrlcndsSalir.setForegroundIconHover(Color.BLACK);
		btnmtrlcndsSalir.setForegroundIcon(Color.BLACK);
		btnmtrlcndsSalir.setForegroundHover(Color.BLACK);
		btnmtrlcndsSalir.setForeground(Color.BLACK);
		btnmtrlcndsSalir.setFont(new Font("Dialog", Font.BOLD, 12));
		btnmtrlcndsSalir.backgroundHover = new Color(228, 233, 232);
		btnmtrlcndsSalir.setBackgroundHover(new Color(228, 233, 232));
		btnmtrlcndsSalir.setBackground(new Color(228, 233, 232));
		btnmtrlcndsSalir.setBounds(10, 338, 155, 50);
		panel.add(btnmtrlcndsSalir);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(195, 25, 981, 438);
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel_myAccount = new JPanel();
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
		
		JLabel lblNewLabel_3 = new JLabel("UNKNOWN");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(36, 330, 259, 51);
		panel_myAccount.add(lblNewLabel_3);
		
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
		
		JLabel lblNewLabel_5_5 = new JLabel("Nombre de Usuario:");
		lblNewLabel_5_5.setBounds(665, 77, 265, 28);
		panel_myAccount.add(lblNewLabel_5_5);
		
		JLabel lblNewLabel_5_5_1 = new JLabel("Password de Usuario:");
		lblNewLabel_5_5_1.setBounds(665, 127, 265, 28);
		panel_myAccount.add(lblNewLabel_5_5_1);
		
		JLabel lblNewLabel_5_5_2 = new JLabel("Nombre completo:");
		lblNewLabel_5_5_2.setBounds(665, 180, 265, 28);
		panel_myAccount.add(lblNewLabel_5_5_2);
		
		JLabel lblNewLabel_5_5_3 = new JLabel("telefono de usuario:");
		lblNewLabel_5_5_3.setBounds(665, 236, 265, 28);
		panel_myAccount.add(lblNewLabel_5_5_3);
		
		JLabel lblNewLabel_5_5_4 = new JLabel("Email de usuario");
		lblNewLabel_5_5_4.setBounds(665, 293, 265, 28);
		panel_myAccount.add(lblNewLabel_5_5_4);
		
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
		
		JPanel panel_Users = new JPanel();
		panel_Users.setBackground(new Color(255, 255, 255));
		layeredPane.add(panel_Users, "name_3353228152900");
		panel_Users.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 961, 416);
		panel_Users.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(271, 11, 675, 366);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_6 = new JLabel("User Name:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(10, 59, 84, 22);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Password:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6_1.setBounds(10, 102, 84, 22);
		panel_1.add(lblNewLabel_6_1);
		
	}
}
