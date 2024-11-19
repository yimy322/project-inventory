package subViews;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class vSuppliers extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel lblId;
	public JTextField textId;
	public JLabel lblNombre;
	public JTextField txtNombre;
	public JLabel lblApellido;
	public JTextField txtApellido;
	public JLabel lblTelefono;
	public JTextField txtTelefono;
	public JLabel lblDireccion;
	public JTextField txtDireccion;
	public JButton btnGuardar, btnLimpiar;
	public JTable jTableCustomers;
	public DefaultTableModel model;
	public JButton btnExcel;

	/**
	 * Create the panel.
	 */
	public vSuppliers() {
		setLocation(0, 0);
		setBackground(new Color(228, 233, 232));
		setSize(1200, 480);
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Administracion de Proveedores"));

		Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

		lblId = new JLabel("ID");
		lblId.setBounds(30, 47, 40, 15);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblId);

		textId = new JTextField();
		textId.setBounds(75, 40, 60, 30);
		textId.setBorder(roundedBorder);
		textId.setEnabled(false);
		add(textId);
		textId.setColumns(10);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(185, 47, 70, 15);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(255, 40, 150, 30);
		txtNombre.setColumns(10);
		add(txtNombre);

		lblApellido = new JLabel("Apellidos");
		lblApellido.setBounds(442, 47, 90, 15);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(531, 40, 150, 30);
		txtApellido.setColumns(10);
		add(txtApellido);

		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(720, 47, 70, 15);
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(791, 40, 110, 30);
		txtTelefono.setColumns(10);
		add(txtTelefono);

		lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(950, 47, 70, 15);
		lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(1035, 40, 120, 30);
		txtDireccion.setColumns(10);
		add(txtDireccion);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(490, 101, 115, 25);
		add(btnGuardar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(610, 101, 115, 25);
		add(btnLimpiar);

		// creacion de la tabla

		jTableCustomers = new JTable();
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Nombres");
		model.addColumn("Apellidos");
		model.addColumn("Telefono");
		model.addColumn("Direccion");
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
