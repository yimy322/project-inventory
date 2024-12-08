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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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

public class TransferController implements ActionListener, KeyListener, MouseListener {
	
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
		this.form.spListSupplier.addKeyListener(this);
		init();
	}
	
	//PERMITE INICIALIZAR TODO COMO ACTUALIZAR LA TABLA DE PRODUCTOS O LISTA DE PROVEEDORES
	public void init() {
		loadTable();
	}
	
	//PERMITE CARGAR LA TABLA DE PRODUCTOS ACTUALIADOS
	public void loadTable(){
		DefaultTableModel model1 = new DefaultTableModel(new String[] {"Nombre Producto", "Cantidad", "Movimiento", "Total", "Fecha", "Tipo", "Usuario"},0);
		Pilas transfers = transfersServiceSQL.findAll();
		
		for(int i=0; i<transfers.size(); i++) {
			Transfers t = (Transfers) transfers.get(i);
				model1.addRow(new Object[] {
						t.getNameProduct(),
						t.getQuantityProduct(),
						t.getQuantity(),
						t.getTotal(),
						t.getFecha(),
						t.getTypeTransfers(),
						t.getUserName()
					});
		}
		this.form.tableTransfers.setModel(model1);
	}
	
	//PERMITE ACTUALIZAR ULTIMO ELEMENTO AGREGADO
	public Transfers getTransfersFinal() {
		Pilas transfers = new Pilas();
		if(transfers.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay registro aun, la pila esta vacia");
			return null;
		}else {
			return (Transfers) transfers.peak();
		}
	}
	
	//PERMITE ESCRIBIR LOS DATOS DEL ULTIMO INGRESO EN LAS CAJAS DE TEXTO DE TRANSLADOS
	public void fillInfoFinal() {
		Transfers transfers = getTransfersFinal();
		if(transfers == null) {
			return;
		}else {
			this.form.txtProductName.setText(transfers.getNameProduct());
			this.form.spnQuantity.setValue(transfers.getQuantity());
		}
	}
	
	//PERMITE LIMPIAR LAS CAJAS DE TEXTO
	public void clear() {
		List<JTextField> textFields = Arrays.asList(this.form.txtProductDescription, this.form.txtProductName);
		textFields.forEach(field -> field.setText(""));
		this.form.listSuppliers.setModel(null);
		this.form.spnQuantity.setValue(0);
		this.form.lblIndicador.setText("X");
		this.form.lblIndicador.setForeground(new Color(255, 0, 0));
	}
	
	//VERIFICA QUE TODOS LOS CAMPOS DE LLENADO ESTEN COMPLETOS ANTES DE PROCEDER
	public boolean validated() {
		if(!this.form.txtProductName.getText().isEmpty() && (int)this.form.spnQuantity.getValue()>0 && this.form.lblIndicador.getText().equals("S"))
			return true;
		return false;
	}
	
	//PERMITE SUMAR A UN DETERMINADO PRODUCTO DADO SU NOMBRE UNICO Y SU PROVEEDOR
	public void addQuantity() {
		if(validated()) {
			
			JOptionPane.showMessageDialog(null, "Estoy lleno");
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
		return cantSumar;
	}
	
	//PERMITE VALIDAR QUE EXISTE CANTIDAD SUFICIENTE PARA RESTAR UNA DETERMINADA CANTIDAD
	public boolean sufficientQuantity() {
		int total = Integer.parseInt(this.form.lblTotal.getText());
		int actual = (int) this.form.spnQuantity.getValue();
		if(total > actual)
			return true;
		
		return false;
	}
	
	//PERMITE ACTUALIZAR EL INDICADOR DEPENDIENDO DE LA CANTIDAD INGRESADA
	public void refreshIndicator() {
		if(sufficientQuantity()) {
			this.form.lblIndicador.setText("S");
			this.form.lblIndicador.setForeground(new Color(0, 0, 255));
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
		Object writeBox = e.getSource();
		if(writeBox == this.form.txtProductName) {
			loadListSupplier();
		}else if(writeBox == this.form.spnQuantity) {
			sufficientQuantity();
		}
		
	}

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
}
