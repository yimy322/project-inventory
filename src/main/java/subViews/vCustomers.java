package subViews;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;

public class vCustomers extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField textId;
	JTextField txtNombres;
	JLabel lblNombres;
	JTextField txtApellidos;
	JLabel lblApellidos;
	JLabel lblTelefono;
	JTextField txtTelefono;
	JLabel lblEmail;
	JTextField txtEmail;
	JButton btnGuardar, btnLimpiar;
	JTable jTableCustomers;
	DefaultTableModel model;
	JButton btnExcel;
	
	/**
	 * Create the panel.
	 */
	public vCustomers() {
		setBackground(new Color(228, 233, 232));
		setSize(1186, 518);
		setLocation(0, 0);
		setBackground(new Color(228, 233, 232));
		setSize(1200, 480);
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Administracion de Clientes"));
		
		Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(30, 47, 40, 15);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblId);
		
		textId= new JTextField();
		textId.setBounds(75, 40, 60, 30);
		textId.setBorder(roundedBorder);
		textId.setEnabled(false);
		add(textId);
		textId.setColumns(10);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(185, 47, 70, 15);
		lblNombres.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(265, 40, 150, 30);
		txtNombres.setColumns(10);
		add(txtNombres);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(465, 47, 70, 15);
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(545, 40, 150, 30);
		txtApellidos.setColumns(10);
		add(txtApellidos);

		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(735, 47, 70, 15);
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(815, 40, 110, 30);
		txtTelefono.setColumns(10);
		add(txtTelefono);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(950, 47, 70, 15);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(1015, 40, 150, 30);
		txtEmail.setColumns(10);
		add(txtEmail);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(490, 101, 100, 25);
		add(btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(610, 101, 100, 25);
		add(btnLimpiar);
		
		//creacion de la tabla
		
		jTableCustomers = new JTable();
		model = new DefaultTableModel();
		model.addColumn("ID");
        model.addColumn("Nombres");
        model.addColumn("Apellidos");
        model.addColumn("Telefono");
        model.addColumn("Email");
        jTableCustomers.setModel(model);
        
        jTableCustomers.setPreferredScrollableViewportSize(new Dimension(610, 335));
        JScrollPane sp = new JScrollPane(jTableCustomers);
        sp.setBounds(50, 165, 1100, 250);
        sp.setVisible(true);
        add(sp);
        
        btnExcel = new JButton("Exportar", new ImageIcon(getClass().getResource("/images/excel.png")));
        btnExcel.setBounds(1020, 430, 130, 25);
		add(btnExcel);
		
	}
}
