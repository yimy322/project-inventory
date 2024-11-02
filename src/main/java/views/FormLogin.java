package views;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import rojeru_san.efectos.ValoresEnum.ICONS;

public class FormLogin extends JFrame {

	public JTextField textField;
	public JPasswordField passwordField;
	public JButton btnLogin, btnExit;

	/**
	 * Create the application.
	 */
	public FormLogin() {
		setBounds(100, 100, 800, 480); //MODIFICACION DEL TAMAÑO DEL JPANEL
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //CENTRA EL FRAME
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(70, 130, 180), 1, true));
		panel.setBackground(new Color(51, 102, 153));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 153, 204));
		panel_1.setBounds(215, 20, 386, 399);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setBounds(0, 28, 386, 49);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 30));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(155, 125, 200, 31);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(39, 124, 130, 28);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(23, 192, 130, 28);
		panel_1.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 193, 200, 31);
		panel_1.add(passwordField);
		
		btnLogin = new JButton("Ingresar");
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnLogin.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		btnLogin.setBackground(new Color(173, 216, 230));
		btnLogin.setBounds(39, 289, 140, 31);
		panel_1.add(btnLogin);
		
		btnExit = new JButton("Salir");
		btnExit.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnExit.setForeground(new Color(0, 0, 0));
		btnExit.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		btnExit.setBackground(new Color(173, 216, 230));
		btnExit.setBounds(215, 289, 140, 31);
		panel_1.add(btnExit);
	}

}
