package subViews;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import RSMaterialComponent.RSButtonMaterialIconDos;
import rojeru_san.efectos.ValoresEnum.ICONS;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class vTransfers extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblProductName;
	public JTable jTableTranslados;
	DefaultTableModel model;
	JButton btnReport;
	public JTextField txtProductName, txtProductDescription;
	public JList list;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public vTransfers() {
		setLocation(0, 0);
		setBackground(new Color(228, 233, 232));
		setSize(1200, 480);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
		"Translados"));
		setLayout(null);

		lblProductName = new JLabel("NOMBRE DEL PRODUCTO:");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProductName.setBounds(40, 29, 203, 30);
		lblProductName.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblProductName);
		
		jTableTranslados = new JTable();
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Producto");
		model.addColumn("Tipo");
		model.addColumn("Fecha");
		model.addColumn("Proveedor");
		model.addColumn("Cantidad");
		model.addColumn("Cliente");
		jTableTranslados.setModel(model);

		jTableTranslados.setPreferredScrollableViewportSize(new Dimension(610, 335));
		JScrollPane sp = new JScrollPane(jTableTranslados);
		sp.setBounds(528, 37, 632, 383);
		sp.setVisible(true);
		add(sp);

		btnReport = new JButton("Generar reporte diario", new ImageIcon(getClass().getResource("/images/pdf.png")));
		btnReport.setBounds(940, 430, 230, 25);
		add(btnReport);
		
		txtProductName = new JTextField();
		txtProductName.setBounds(40, 58, 165, 30);
		add(txtProductName);
		txtProductName.setColumns(10);
		
		JLabel lblProductDescription = new JLabel("DESCRIPCION:");
		lblProductDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblProductDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProductDescription.setBounds(40, 106, 129, 30);
		add(lblProductDescription);
		
		txtProductDescription = new JTextField();
		txtProductDescription.setColumns(10);
		txtProductDescription.setBounds(40, 134, 412, 30);
		add(txtProductDescription);
		
		JLabel lblSupliers = new JLabel("PROVEEDORES:");
		lblSupliers.setHorizontalAlignment(SwingConstants.LEFT);
		lblSupliers.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSupliers.setBounds(40, 174, 129, 30);
		add(lblSupliers);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 205, 285, 121);
		add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		lblNewLabel = new JLabel("0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(335, 230, 117, 75);
		add(lblNewLabel);
		
		RSButtonMaterialIconDos btnmtrlcndsSumar = new RSButtonMaterialIconDos();
		btnmtrlcndsSumar.setText("SUMAR");
		btnmtrlcndsSumar.setIcons(ICONS.ADD);
		btnmtrlcndsSumar.backgroundHover = new Color(51, 191, 11);
		btnmtrlcndsSumar.setBackgroundHover(new Color(128, 255, 0));
		btnmtrlcndsSumar.setBackground(new Color(51, 191, 11));
		btnmtrlcndsSumar.setBounds(43, 405, 200, 50);
		add(btnmtrlcndsSumar);
		
		RSButtonMaterialIconDos btnmtrlcndsRestar = new RSButtonMaterialIconDos();
		btnmtrlcndsRestar.setText("RESTAR");
		btnmtrlcndsRestar.setIcons(ICONS.DELETE);
		btnmtrlcndsRestar.backgroundHover = new Color(240, 34, 50);
		btnmtrlcndsRestar.setBackgroundHover(new Color(255, 0, 0));
		btnmtrlcndsRestar.setBackground(new Color(240, 34, 50));
		btnmtrlcndsRestar.setBounds(266, 405, 200, 50);
		add(btnmtrlcndsRestar);
		
		JLabel lblCantidad = new JLabel("CANTIDAD:");
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidad.setBounds(40, 344, 129, 30);
		add(lblCantidad);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 24));
		spinner.setBounds(211, 336, 117, 50);
		add(spinner);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(new Color(255, 0, 0));
		lblX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblX.setBounds(384, 336, 68, 50);
		add(lblX);
		
		

	}
}
