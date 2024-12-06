package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import RSMaterialComponent.RSButtonMaterialIconDos;
import linkedList.LinkedList;
import models.Transfers;
import services.TranfersService;
import subViews.vTransfers;

public class TransferController implements ActionListener{
	
	private vTransfers form;
	TranfersService transfersServiceSQL = new TranfersService();
	
	public TransferController(vTransfers form) {
		this.form = form;
		List<RSButtonMaterialIconDos> buttons = Arrays.asList(this.form.btnLess, this.form.btnPlus);
		buttons.forEach(button -> button.addActionListener(this));
		this.form.btnReport.addActionListener(this);
		init();
	}
	
	//PERMITE INICIALIZAR TODO COMO ACTUALIZAR LA TABLA DE PRODUCTOS O LISTA DE PROVEEDORES
	public void init() {
		loadTable();
	}
	
	//PERMITE CARGAR LA TABLA DE PRODUCTOS ACTUALIADOS
	public void loadTable(){
		DefaultTableModel model1 = new DefaultTableModel(new String[] {"Nombre Producto", "Cantidad", "Movimiento", "Total", "Fecha", "Tipo", "Usuario"},0);
		LinkedList transfers = transfersServiceSQL.findAll();
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
	public boolean isComplete() {
		if(!this.form.txtProductDescription.getText().isEmpty() && !this.form.txtProductName.getText().isEmpty() && (int)this.form.spnQuantity.getValue()>0)
			return true;
		return false;
	}
	
	//PERMITE SUMAR A UN DETERMINADO PRODUCTO DADO SU NOMBRE UNICO Y SU PROVEEDOR
	public void addQuantity() {
		if(isComplete()) {
			JOptionPane.showMessageDialog(null, "Estoy lleno");
		}
	}
	
	//PERMITE RESTAR EN CANTIDAD SEGUN SU NOMBRE UNICO Y DETERMINADOS PROVEEDORES
	public void subtractQuantity() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object press = ae.getSource();
		if (press == this.form.btnPlus) {
			addQuantity();
		}
		
	}
	
	
	
	public void showView() {
		form.setVisible(true);
	}
	
}
