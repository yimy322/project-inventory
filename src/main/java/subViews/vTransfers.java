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
import javax.swing.JOptionPane;

import RSMaterialComponent.RSButtonMaterialIconDos;
import rojeru_san.efectos.ValoresEnum.ICONS;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractListModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class vTransfers extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTable tableTransfers;
	public JButton btnReport, btnUpdateFinal;
	public JTextField txtProductName, txtProductDescription;
	public JList listSuppliers;
	public JLabel lblTotal, lblIndicador;
	public RSButtonMaterialIconDos btnPlus, btnLess;
	public JSpinner spnQuantity;
	public JScrollPane spListSupplier;

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

		JLabel lblProductName = new JLabel("NOMBRE DEL PRODUCTO:");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProductName.setBounds(40, 29, 203, 30);
		lblProductName.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblProductName);
		
		tableTransfers = new JTable();

		tableTransfers.setPreferredScrollableViewportSize(new Dimension(610, 335));
		JScrollPane sp = new JScrollPane(tableTransfers);
		sp.setBounds(528, 37, 642, 383);
		sp.setVisible(true);
		add(sp);

		btnReport = new JButton("Generar reporte diario", new ImageIcon(getClass().getResource("/images/excel.png")));
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
		txtProductDescription.setEditable(false);
		txtProductDescription.setColumns(10);
		txtProductDescription.setBounds(40, 134, 412, 30);
		add(txtProductDescription);
		
		JLabel lblSupliers = new JLabel("PROVEEDORES:");
		lblSupliers.setHorizontalAlignment(SwingConstants.LEFT);
		lblSupliers.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSupliers.setBounds(40, 174, 129, 30);
		add(lblSupliers);
		
		spListSupplier = new JScrollPane();
		spListSupplier.setBounds(40, 205, 285, 121);
		add(spListSupplier);
		
		listSuppliers = new JList();
		spListSupplier.setViewportView(listSuppliers);
		
		lblTotal = new JLabel("0");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTotal.setBounds(335, 230, 117, 75);
		add(lblTotal);
		
		btnPlus = new RSButtonMaterialIconDos();
		btnPlus.setText("SUMAR");
		btnPlus.setIcons(ICONS.ADD);
		btnPlus.backgroundHover = new Color(51, 191, 11);
		btnPlus.setBackgroundHover(new Color(67, 194, 12));
		btnPlus.setBackground(new Color(51, 191, 11));
		btnPlus.setBounds(43, 405, 200, 50);
		add(btnPlus);
		
		btnLess = new RSButtonMaterialIconDos();
		btnLess.setText("RESTAR");
		btnLess.setIcons(ICONS.DELETE);
		btnLess.backgroundHover = new Color(240, 34, 50);
		btnLess.setBackgroundHover(new Color(255, 0, 0));
		btnLess.setBackground(new Color(240, 34, 50));
		btnLess.setBounds(266, 405, 200, 50);
		add(btnLess);
		
		JLabel lblCantidad = new JLabel("CANTIDAD:");
		lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidad.setBounds(40, 344, 129, 30);
		add(lblCantidad);
		
		spnQuantity = new JSpinner();
		spnQuantity.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spnQuantity.setFont(new Font("Tahoma", Font.BOLD, 24));
		spnQuantity.setBounds(211, 336, 117, 50);
		add(spnQuantity);
		
		lblIndicador = new JLabel("X");
		lblIndicador.setForeground(new Color(255, 0, 0));
		lblIndicador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIndicador.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblIndicador.setBounds(384, 336, 68, 50);
		add(lblIndicador);
		
		btnUpdateFinal = new JButton("");
		btnUpdateFinal.setIcon(new ImageIcon(vTransfers.class.getResource("/necesario/ver1.png")));
		btnUpdateFinal.setBackground(new Color(228, 233, 232));
		btnUpdateFinal.setBounds(528, 433, 57, 37);
		add(btnUpdateFinal);
	}
}
