package subViews;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import RSMaterialComponent.RSButtonIconDos;
import RSMaterialComponent.RSButtonMaterialIconDos;
import rojeru_san.efectos.ValoresEnum.ICONS;
import javax.swing.border.TitledBorder;
import rojeru_san.complementos.RSButtonHover;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class vSales extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txt_productId;
	private JLabel lblCantidad;
	private JTextField txt_price;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public vSales() {
		setBackground(new Color(228, 233, 232));
		setSize(1186, 518);
		setLocation(0, 0);
		setBorder(new TitledBorder(null, "Realizar ventas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setBackground(new Color(228, 233, 232));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo del Producto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(67, 35, 147, 28);
		add(lblNewLabel);
		
		txt_productId = new JTextField();
		txt_productId.setBounds(224, 36, 178, 28);
		add(txt_productId);
		txt_productId.setColumns(10);
		
		JSpinner spn_quantity = new JSpinner();
		spn_quantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spn_quantity.setBounds(536, 35, 46, 28);
		add(spn_quantity);
		
		JLabel lblCantidad_1 = new JLabel("Cantidad:");
		lblCantidad_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad_1.setBounds(446, 35, 80, 28);
		add(lblCantidad_1);
		
		JLabel lblCantidad_1_1 = new JLabel("Precio:   S/.");
		lblCantidad_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad_1_1.setBounds(633, 34, 80, 28);
		add(lblCantidad_1_1);
		
		txt_price = new JTextField();
		txt_price.setColumns(10);
		txt_price.setBounds(723, 35, 125, 28);
		add(txt_price);
		
		RSButtonMaterialIconDos btnSales = new RSButtonMaterialIconDos();
		btnSales.backgroundHover = new Color(22, 160, 133);
		btnSales.setBackgroundHover(new Color(115, 198, 182));
		btnSales.setText("REGISTRAR");
		btnSales.setIcons(ICONS.ADD);
		btnSales.setForegroundText(Color.BLACK);
		btnSales.setForegroundIconHover(Color.WHITE);
		btnSales.setForegroundIcon(Color.BLACK);
		btnSales.setForegroundHover(Color.WHITE);
		btnSales.setForeground(Color.WHITE);
		btnSales.setBackground(new Color(125, 206, 160));
		btnSales.setBounds(932, 24, 170, 50);
		add(btnSales);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 102, 1088, 245);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCantidad_1_2 = new JLabel("TOTAL UNIDADES");
		lblCantidad_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad_1_2.setBounds(162, 369, 139, 28);
		add(lblCantidad_1_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(140, 408, 178, 28);
		add(textField);
		
		JLabel lblCantidad_1_2_1 = new JLabel("TOTAL DINERO");
		lblCantidad_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad_1_2_1.setBounds(516, 369, 139, 28);
		add(lblCantidad_1_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(494, 408, 178, 28);
		add(textField_1);
		
		RSButtonMaterialIconDos btnmtrlcndsVender = new RSButtonMaterialIconDos();
		btnmtrlcndsVender.setText("VENDER");
		btnmtrlcndsVender.setIcons(ICONS.SHOP_TWO);
		btnmtrlcndsVender.setForegroundText(Color.BLACK);
		btnmtrlcndsVender.setForegroundIconHover(Color.WHITE);
		btnmtrlcndsVender.setForegroundIcon(Color.BLACK);
		btnmtrlcndsVender.setForegroundHover(Color.WHITE);
		btnmtrlcndsVender.setForeground(Color.WHITE);
		btnmtrlcndsVender.backgroundHover = new Color(22, 160, 133);
		btnmtrlcndsVender.setBackgroundHover(new Color(22, 160, 133));
		btnmtrlcndsVender.setBackground(new Color(125, 206, 160));
		btnmtrlcndsVender.setBounds(970, 379, 170, 50);
		add(btnmtrlcndsVender);
	}
}
