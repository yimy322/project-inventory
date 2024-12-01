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

public class vInventory extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField textBusqueda;
	public JLabel lblLupa;
	public JTable jTableProducts;
	public DefaultTableModel model;
	public JButton btnExcel, btnCsv,btnJson;
	public 	JLabel lblTotal;
	public JTextField textTotal;
	public 	JLabel lblValor;
	public JTextField textValor;
	public JLabel lblMasCategoria;
	public JTextField textMasCategoria;
	public 	JLabel lblMenosCategoria;
	public JTextField textMenosCategoria;
	public 	JLabel lblUltIngreso;
	public JTextField textUltIngreso;
	public JLabel lblUtlSalida;
	public JTextField textUtlSalida;
	public JButton btnOrdenar;
	public JButton btnOrdenarId;
	/**
	 * Create the panel.
	 */
	public vInventory() {
		
		setBackground(new Color(228, 233, 232));
		setSize(1186, 518);
		setLocation(0, 0);
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
				"Inventario"));
		
		Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

		textBusqueda = new JTextField();
		textBusqueda.setBounds(30, 38, 180, 30);
		textBusqueda.setBorder(roundedBorder);
		add(textBusqueda);
		textBusqueda.setColumns(10);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/lupa.png"));
		lblLupa = new JLabel(icon);
		lblLupa.setBounds(190, 47, 70, 15);
		lblLupa.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLupa);
		
		//buttons
		btnOrdenar = new JButton("Ordenar por nombre");
		btnOrdenar.setBounds(250, 38, 180, 25);
		add(btnOrdenar);
		
		btnOrdenarId = new JButton("Ordenar por Id");
		btnOrdenarId.setBounds(450, 38, 180, 25);
		add(btnOrdenarId);
		
		ImageIcon iconExcel = new ImageIcon(getClass().getResource("/images/excel.png"));
		btnExcel = new JButton(iconExcel);
		btnExcel.setBounds(1118, 38, 50, 25);
		add(btnExcel);
		
		ImageIcon iconCsv = new ImageIcon(getClass().getResource("/images/pdf.png"));
		btnCsv = new JButton(iconCsv);
		btnCsv.setBounds(1050, 38, 50, 25);
		add(btnCsv);
		
		ImageIcon iconJson = new ImageIcon(getClass().getResource("/images/json.png"));
		btnJson = new JButton(iconJson);
		btnJson.setBounds(982, 38, 50, 25);
		add(btnJson);
		
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
        sp.setBounds(30, 90, 1140, 290);
        sp.setVisible(true);
        add(sp);
        
        lblTotal = new JLabel("Total de productos");
        lblTotal.setBounds(30, 410, 150, 15);
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotal);

		textTotal = new JTextField();
		textTotal.setBounds(30, 430, 150, 30);
		textTotal.setColumns(10);
		textTotal.setEnabled(false);
		add(textTotal);
		
		lblValor = new JLabel("Valor total");
		lblValor.setBounds(225, 410, 150, 15);
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblValor);

		textValor = new JTextField();
		textValor.setBounds(225, 430, 150, 30);
		textValor.setColumns(10);
		textValor.setEnabled(false);
		add(textValor);
		
		lblMasCategoria = new JLabel("Cate. mas popular");
		lblMasCategoria.setBounds(425, 410, 150, 15);
		lblMasCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMasCategoria);

		textMasCategoria = new JTextField();
		textMasCategoria.setBounds(425, 430, 150, 30);
		textMasCategoria.setColumns(10);
		textMasCategoria.setEnabled(false);
		add(textMasCategoria);
		
		lblMenosCategoria = new JLabel("Cate. menos popular");
		lblMenosCategoria.setBounds(625, 410, 150, 15);
		lblMenosCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblMenosCategoria);

		textMenosCategoria = new JTextField();
		textMenosCategoria.setBounds(625, 430, 150, 30);
		textMenosCategoria.setColumns(10);
		textMenosCategoria.setEnabled(false);
		add(textMenosCategoria);
		
		lblUltIngreso = new JLabel("Ultimo ingreso");
		lblUltIngreso.setBounds(825, 410, 150, 15);
		lblUltIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUltIngreso);

		textUltIngreso = new JTextField();
		textUltIngreso.setBounds(825, 430, 150, 30);
		textUltIngreso.setColumns(10);
		textUltIngreso.setEnabled(false);
		add(textUltIngreso);
		
		lblUtlSalida = new JLabel("Ultima salida");
		lblUtlSalida.setBounds(1024, 410, 150, 15);
		lblUtlSalida.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblUtlSalida);

		textUtlSalida = new JTextField();
		textUtlSalida.setBounds(1024, 430, 150, 30);
		textUtlSalida.setColumns(10);
		textUtlSalida.setEnabled(false);
		add(textUtlSalida);
		
	}

}
