package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import RSMaterialComponent.RSButtonMaterialIconDos;
import linkedList.LinkedList;
import models.Transfers;
import pilas.Pilas;
import models.Product;
import services.ProductService;
import services.SupplierService;
import services.TranfersService;
import subViews.vTransfers;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransferController implements ActionListener, KeyListener, MouseListener, ChangeListener {
	
	private vTransfers form;
	TranfersService transfersServiceSQL = new TranfersService();
	SupplierService supplierServiceSQL = new SupplierService();
	ProductService productServiceSQL = new ProductService();
	
	public TransferController(vTransfers form) {
		this.form = form;
		List<RSButtonMaterialIconDos> buttons1 = Arrays.asList(this.form.btnLess, this.form.btnPlus);
		buttons1.forEach(button -> button.addActionListener(this));
		List<JButton> buttons2 = Arrays.asList(this.form.btnReport, this.form.btnUpdateFinal);
		buttons2.forEach(button -> button.addActionListener(this));
		this.form.listSuppliers.addMouseListener(this);
		this.form.txtProductName.addKeyListener(this);
		this.form.spnQuantity.addChangeListener(this);;
		init();
	}
	
	//PERMITE INICIALIZAR TODO COMO ACTUALIZAR LA TABLA DE PRODUCTOS O LISTA DE PROVEEDORES
	public void init() {
		loadTable();
	}
	
	//PERMITE CARGAR LA TABLA DE PRODUCTOS ACTUALIADOS
	public void loadTable(){
		DefaultTableModel model1 = new DefaultTableModel(new String[] {"Nombre Producto", "Cantidad", "Movimiento", "Total", "Proveedor", "Fecha", "Tipo", "Usuario"},0);
		Pilas transfers = transfersServiceSQL.findAll();
		
		for(int i=0; i<transfers.size(); i++) {
			Transfers t = (Transfers) transfers.get(i);
				model1.addRow(new Object[] {
						t.getNameProduct(),
						t.getQuantityProduct(),
						t.getQuantity(),
						t.getTotal(),
						t.getNameSupplier(),
						t.getFecha(),
						t.getTypeTransfers(),
						t.getUserName()
					});
		}
		this.form.tableTransfers.setModel(model1);
	}
	
	//PERMITE ACTUALIZAR ULTIMO ELEMENTO AGREGADO
	public Transfers getTransfersFinal() {
		Pilas transfers = transfersServiceSQL.findAll();
		if(transfers.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay registro aun, la pila esta vacia");
			return null;
		}else {
			return (Transfers) transfers.peek();
		}
	}
	
	//PERMITE ESCRIBIR LOS DATOS DEL ULTIMO INGRESO EN LAS CAJAS DE TEXTO DE TRANSLADOS
	public void fillInfoFinal() {
		Transfers transfer = getTransfersFinal();
		if(transfer == null) {
			return;
		}else {
			this.form.txtProductName.setText(transfer.getNameProduct());
			this.form.spnQuantity.setValue(transfer.getQuantity());
			loadListSupplier();
			refreshIndicator();
		}
	}
	
	//PERMITE LIMPIAR LAS CAJAS DE TEXTO
	public void clear() {
		this.form.txtProductName.setText("");
		this.form.spnQuantity.setValue(0);
		this.form.lblIndicador.setText("X");
		this.form.lblIndicador.setForeground(new Color(255, 0, 0));
		this.form.lblTotal.setText("0");
		this.form.txtProductDescription.setText("");
	}
	
	//VERIFICA QUE TODOS LOS CAMPOS DE LLENADO ESTEN COMPLETOS ANTES DE PROCEDER
	public boolean validated() {
		if(!this.form.txtProductName.getText().isEmpty() && (int)this.form.spnQuantity.getValue()>0 && this.form.lblIndicador.getText().equals("S"))
			return true;
		return false;
	}
	
	//PERMITE SUMAR A UN DETERMINADO PRODUCTO DADO SU NOMBRE UNICO Y SU PROVEEDOR
	public void addQuantity() {
		if(!validated()) {
			JOptionPane.showMessageDialog(null, "Complete los todos los campos por favor!");
			return;
		}
		if(this.form.listSuppliers.getModel() == null) {
			return;
		}
		Object[] selectedItems = this.form.listSuppliers.getSelectedValues();
		LinkedList products = productServiceSQL.findAll();
		for(int i=0; i<selectedItems.length; i++) {
			for (int j = 0; j < products.size(); j++) {
				Product product = (Product) products.get(j);
				if(this.form.txtProductName.getText().toLowerCase().equals(product.getName().toLowerCase()) && selectedItems[i].toString().toLowerCase().equals(product.getSname().toLowerCase().toString())) {
					String nameP = this.form.txtProductName.getText();
					int cant = (int) this.form.spnQuantity.getValue();
					
					//permite encontrar la hora y fecha actual en formato mysql
					LocalDateTime currentDateTime = LocalDateTime.now();
					Timestamp sqlTimestamp = Timestamp.valueOf(currentDateTime);
					//ISNTANCIAMOS LA CLASE TRANSEFERS Y LLENAMOS CON DATOS
					Transfers transfers = new Transfers(0, cant, (cant+product.getQuantity()), LoginController.USER.getIdUser(), product.getIdProduct(), sqlTimestamp.toString(), "INGRESO", product.getName(), LoginController.USER.getUsername(), product.getQuantity(), product.getSname());
					//INGRESAMOS LOS DATOS A LA BASE DE DATOS, REGISTRO DE TRANSLADOS
					transfersServiceSQL.insert(transfers);
					//PERMITE SUMAR O ACTUALIZAR LOS DATOS EN BASE DE DATOS RESPECTO A UN PRODUCTO
					transfersServiceSQL.plusQuantity(nameP, cant, product.getIdSupplier());
					clear();
					loadTable();
				}
			}
		}
	}
	
	//PERMITE RESTAR EN CANTIDAD SEGUN SU NOMBRE UNICO Y DETERMINADOS PROVEEDORES
	public void subtractQuantity() {
		
	}
	
	//PERMITE LISTAR EN DE PROVEEDORES LA EL JLIST
	public void loadListSupplier() {
		if (!this.form.txtProductName.getText().isEmpty()) {
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			LinkedList products = productServiceSQL.findAll();
			for (int i = 0; i < products.size(); i++) {
				Product product = (Product) products.get(i);
				if(this.form.txtProductName.getText().toLowerCase().equals(product.getName().toLowerCase())) {
					this.form.txtProductDescription.setText(product.getDescription());
					listModel.addElement(product.getSname());
				}
			}
			this.form.listSuppliers.setModel(listModel);
		}else {
			clear();
		}
		
	}
	
	//PERMITE OBTENER LOS ITEM DE LA LISTA Y RELACIONARLE SU DEBIDA CANTIDAD SEGUN EL REGISTEO DEL PROVEEDOR
	public int getQuantityProduct() {
		if(this.form.listSuppliers.getModel() == null) {
			return 0;
		}
		Object[] selectedItems = this.form.listSuppliers.getSelectedValues();
		LinkedList products = productServiceSQL.findAll();
		int cantSumar = 0;
		for(int i=0; i<selectedItems.length; i++) {
			for (int j = 0; j < products.size(); j++) {
				Product product = (Product) products.get(j);
				if(this.form.txtProductName.getText().toLowerCase().equals(product.getName().toLowerCase()) && selectedItems[i].toString().toLowerCase().equals(product.getSname().toLowerCase().toString())) {
					cantSumar += product.getQuantity();
					this.form.txtProductDescription.setText(product.getDescription());
				}
			}
		}
		//PERMITE ESTABLECER UNA TAMAÑO MAXIMO DEPENDIEDO DE LA SUMA TOTAL PARA EL JsPINNER
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, cantSumar, 1);
		this.form.spnQuantity.setModel(spinnerModel);
		
		return cantSumar;
	}
	
	//PERMITE VALIDAR QUE EXISTE CANTIDAD SUFICIENTE PARA RESTAR UNA DETERMINADA CANTIDAD
	public boolean sufficientQuantity() {
		int total = Integer.parseInt(this.form.lblTotal.getText());
		int actual = (int) this.form.spnQuantity.getValue();
		if(actual > 0 && actual <= total)
			return true;
		
		return false;
	}
	
	//PERMITE ACTUALIZAR EL INDICADOR DEPENDIENDO DE LA CANTIDAD INGRESADA
	public void refreshIndicator() {
		if(sufficientQuantity()) {
			this.form.lblIndicador.setText("S");
			this.form.lblIndicador.setForeground(new Color(0, 0, 255));
		}else {
			this.form.lblIndicador.setText("X");
			this.form.lblIndicador.setForeground(new Color(255, 0, 0));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object press = ae.getSource();
		if (press == this.form.btnPlus) {
			addQuantity();
		}else if(press == this.form.btnUpdateFinal) {
			fillInfoFinal();
		}
	}
	
	public void showView() {
		form.setVisible(true);
	}
	

	//permite controlar el evento que busca en tipo real los proovedores correspodientes a un producto
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		loadListSupplier();
	}

	//permite controlar los eventos referentes a los clik en el Jlist
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object pressItem = e.getSource();
		if(pressItem == this.form.listSuppliers) {
			this.form.lblTotal.setText(String.valueOf(getQuantityProduct()));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//controla el enevento de el JSpinner
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		refreshIndicator();
	}
}
