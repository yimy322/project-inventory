package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import RSMaterialComponent.RSButtonIconDos;
import RSMaterialComponent.RSButtonMaterialIconDos;
import hashTable.NodeHash;
import rojeru_san.efectos.ValoresEnum.ICONS;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import rojeru_san.complementos.RSButtonHover;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class vSales extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel lblDni;
	public JTextField txtDni;
	public JLabel lblNombres;
	public JTextField txtNombres;
	public JLabel lblApellidos;
	public JTextField txtApellidos;
	public JLabel lblTelefono;
	public JTextField txtTelefono;
	public JLabel lblEmail;
	public JTextField txtEmail;
	public JButton btnBuscarCliente;
	public JLabel lblCategoria;
	public JComboBox<NodeHash> cbCategoria;
	public JLabel lblProveedor;
	public JComboBox<NodeHash> cbProveedor;
	public JLabel lblProducto;
	public JComboBox<NodeHash> cbProducto;
	public JLabel lblCantidad;
	public JTextField txtCantidad;
	public JLabel lblMaximo;
	public JButton btnAgregarProducto;
	public JTable jTableProducts;
	public DefaultTableModel model;
	public JButton btnExcel;
	public JLabel lblCantidadProd;
	public JTextField txtCantidadProd;
	public JLabel lblTotalProd;
	public JTextField txtTotalProd;
	public JButton btnEliminarUltimo;
	public JButton btnEliminarTodo;

	/**
	 * Create the panel.
	 */
	public vSales() {
		
		setBackground(new Color(228, 233, 232));
		setSize(1186, 518);
		setLocation(0, 0);
		setBackground(new Color(228, 233, 232));
		setSize(1200, 480);
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Venta"));

		Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(30, 47, 40, 15);
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDni);

		txtDni = new JTextField();
		txtDni.setBounds(75, 40, 100, 30);
		txtDni.setBorder(roundedBorder);
		add(txtDni);
		txtDni.setColumns(10);
		
		btnBuscarCliente = new JButton(new ImageIcon(getClass().getResource("/images/lupa.png")));
		btnBuscarCliente.setBounds(185, 40, 50, 30);
		add(btnBuscarCliente);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(285, 47, 70, 15);
		lblNombres.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNombres);

		txtNombres = new JTextField();
		txtNombres.setBounds(365, 40, 120, 30);
		txtNombres.setColumns(10);
		txtNombres.setEditable(false);
		add(txtNombres);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(515, 47, 70, 15);
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(595, 40, 120, 30);
		txtApellidos.setColumns(10);
		txtApellidos.setEditable(false);
		add(txtApellidos);

		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(745, 47, 70, 15);
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(820, 40, 120, 30);
		txtTelefono.setColumns(10);
		txtTelefono.setEditable(false);
		add(txtTelefono);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(950, 47, 70, 15);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(1015, 40, 150, 30);
		txtEmail.setColumns(10);
		txtEmail.setEditable(false);
		add(txtEmail);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 90, 1180, 15);
		separator.setForeground(Color.BLACK);
		add(separator);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(43, 108, 70, 15);
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCategoria);
		
		cbCategoria = new JComboBox<NodeHash>();
		cbCategoria.setBounds(131, 103, 150, 24);
		cbCategoria.setEditable(false);
		add(cbCategoria);
		
		lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(320, 108, 90, 15);
		lblProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProveedor);
		
		cbProveedor = new JComboBox<NodeHash>();
		cbProveedor.setBounds(420, 103, 150, 24);
		cbProveedor.setEditable(false);
		add(cbProveedor);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(605, 108, 90, 15);
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProducto);
		
		cbProducto = new JComboBox<NodeHash>();
		cbProducto.setBounds(705, 103, 150, 24);
		cbProducto.setEditable(false);
		add(cbProducto);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(890, 108, 70, 15);
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(975, 103, 50, 30);
		txtCantidad.setColumns(10);
		txtCantidad.setEditable(false);
		add(txtCantidad);
		
		lblMaximo = new JLabel("Max. 0");
		lblMaximo.setBounds(1020, 108, 70, 15);
		lblMaximo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMaximo);
		
		btnAgregarProducto = new JButton(new ImageIcon(getClass().getResource("/images/add.png")));
		btnAgregarProducto.setBounds(1116, 103, 50, 30);
		add(btnAgregarProducto);
		
		btnEliminarUltimo = new JButton(new ImageIcon(getClass().getResource("/images/eliminar.png")));
		btnEliminarUltimo.setBounds(540, 140, 50, 30);
		add(btnEliminarUltimo);
		
		btnEliminarTodo = new JButton(new ImageIcon(getClass().getResource("/images/tacho.png")));
		btnEliminarTodo.setBounds(610, 140, 50, 30);
		add(btnEliminarTodo);
		
		jTableProducts = new JTable();
		model = new DefaultTableModel();
		model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Descripcion");
        model.addColumn("Precio");
        model.addColumn("Cantidad elegida");
        model.addColumn("Valor total");
        jTableProducts.setModel(model);
        
        jTableProducts.setPreferredScrollableViewportSize(new Dimension(610, 335));
        JScrollPane sp = new JScrollPane(jTableProducts);
        sp.setBounds(50, 180, 1100, 250);
        sp.setVisible(true);
        add(sp);
        
        lblCantidadProd = new JLabel("Cantidad Total");
        lblCantidadProd.setBounds(43, 437, 120, 15);
        lblCantidadProd.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCantidadProd);

		txtCantidadProd = new JTextField();
		txtCantidadProd.setBounds(170, 430, 150, 30);
		txtCantidadProd.setColumns(10);
		txtCantidadProd.setEditable(false);
		add(txtCantidadProd);
		
		lblTotalProd = new JLabel("Total S/.");
		lblTotalProd.setBounds(320, 437, 120, 15);
		lblTotalProd.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotalProd);

		txtTotalProd = new JTextField();
		txtTotalProd.setBounds(420, 430, 150, 30);
		txtTotalProd.setColumns(10);
		txtTotalProd.setEditable(false);
		add(txtTotalProd);
        
        btnExcel = new JButton("Venta", new ImageIcon(getClass().getResource("/images/camion.png")));
        btnExcel.setBounds(1020, 430, 130, 25);
		add(btnExcel);
		
	}
}
