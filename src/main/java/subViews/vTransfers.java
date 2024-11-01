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

public class vTransfers extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel lblProducto;
	JComboBox<String> cbProducto;
	JLabel lblTipo;
	JComboBox<String> cbTipo;
	JLabel lblFechaIni;
	JDateChooser calendarIni;
	JLabel lblFechaFin;
	JDateChooser calendarFin;
	JButton btnBuscar;
	JTable jTableTranslados;
	DefaultTableModel model;
	JButton btnReport;
	JLabel lblGanancias;
	JTextField txtGanancias;

	/**
	 * Create the panel.
	 */
	public vTransfers() {
		setBackground(new Color(228, 233, 232));
		setSize(1200, 480);
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
		"Translados"));

		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(30, 47, 80, 15);
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblProducto);

		cbProducto= new JComboBox<String>();
		cbProducto.setBounds(120, 40, 130, 30);
		add(cbProducto);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(270, 47, 80, 15);
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTipo);

		cbTipo= new JComboBox<String>();
		cbTipo.setBounds(340, 40, 130, 30);
		add(cbTipo);

		lblFechaIni = new JLabel("Fecha Inicio");
		lblFechaIni.setBounds(500, 47, 100, 15);
		lblFechaIni.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFechaIni);

		calendarIni= new JDateChooser();
		calendarIni.setBounds(600, 40, 120, 30);
		add(calendarIni);
		
		lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(740, 47, 100, 15);
		lblFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblFechaFin);

		calendarFin= new JDateChooser();
		calendarFin.setBounds(840, 40, 120, 30);
		add(calendarFin);

		btnBuscar= new JButton("Buscar");
		btnBuscar.setBounds(1030, 40, 120, 30);
		add(btnBuscar);
		
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
		sp.setBounds(30, 90, 1140, 320);
		sp.setVisible(true);
		add(sp);

		lblGanancias = new JLabel("Ganancias diarias");
		lblGanancias.setBounds(30, 440, 150, 15);
		lblGanancias.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblGanancias);

		txtGanancias= new JTextField();
		txtGanancias.setBounds(180, 430, 100, 30);
		txtGanancias.setEnabled(false);
		add(txtGanancias);
		txtGanancias.setColumns(10);

		btnReport = new JButton("Generar reporte diario", new ImageIcon(getClass().getResource("/images/pdf.png")));
		btnReport.setBounds(940, 430, 230, 25);
		add(btnReport);
		
		

	}

}
