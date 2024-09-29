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
import javax.swing.JComboBox;

public class vProducts extends JPanel {

	private static final long serialVersionUID = 1L;
	JTextField textId;
	JTextField txtNombre;
	JLabel lblNombre;
	JTextField txtDescripcion;
	JLabel lblDescripcion;
	JLabel lblPrecio;
	JTextField txtPrecio;
	JLabel lblCantidad;
	JTextField txtCantidad;
	JLabel lblCategoria;
	JComboBox cbCategoria;
	JLabel lblProveedor;
	JComboBox cbProveedor;
	JButton btnGuardar, btnLimpiar;
	JTable jTableProducts;
	DefaultTableModel model;
	JButton btnExcel;
	
	/**
	 * Create the panel.
	 */
	public vProducts() {
		setBackground(new Color(228, 233, 232));
		setSize(1200, 480);
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Administracion de Productos"));
		
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
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(185, 47, 70, 15);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(255, 40, 150, 30);
		txtNombre.setColumns(10);
		add(txtNombre);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(450, 47, 90, 15);
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(545, 40, 170, 30);
		txtDescripcion.setColumns(10);
		add(txtDescripcion);

		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(750, 47, 70, 15);
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(815, 40, 110, 30);
		txtPrecio.setColumns(10);
		add(txtPrecio);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(970, 47, 70, 15);
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(1045, 40, 110, 30);
		txtCantidad.setColumns(10);
		add(txtCantidad);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(43, 108, 70, 15);
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCategoria);
		
		cbCategoria = new JComboBox();
		cbCategoria.setBounds(131, 103, 150, 24);
		add(cbCategoria);
		
		lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(320, 108, 90, 15);
		lblProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProveedor);
		
		cbProveedor = new JComboBox();
		cbProveedor.setBounds(420, 103, 150, 24);
		add(cbProveedor);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(770, 101, 100, 25);
		add(btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(890, 101, 100, 25);
		add(btnLimpiar);
		
		jTableProducts = new JTable();
		model = new DefaultTableModel();
		model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Descripcion");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Categoria");
        model.addColumn("Proveedor");
        jTableProducts.setModel(model);
        
        jTableProducts.setPreferredScrollableViewportSize(new Dimension(610, 335));
        JScrollPane sp = new JScrollPane(jTableProducts);
        sp.setBounds(50, 165, 1100, 250);
        sp.setVisible(true);
        add(sp);
        
        btnExcel = new JButton("Exportar", new ImageIcon(getClass().getResource("/images/excel.png")));
        btnExcel.setBounds(1020, 430, 130, 25);
		add(btnExcel);
	
	}
}
