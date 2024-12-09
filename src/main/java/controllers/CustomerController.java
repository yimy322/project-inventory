package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import extras.Input;
import linkedList.LinkedList;
import models.Customer;
import models.Supplier;
import services.CustomerService;
import services.SupplierService;
import subViews.vCustomers;

public class CustomerController implements ActionListener{ //SE IMPLEMENTA LA INTERFAZ ACTIONLISTENER 
	
	private vCustomers form;
	CustomerService customerService = new CustomerService();
	LinkedList clientes = customerService.findAll(); //PARA LISTAR EN LA TABLA 
	
	public CustomerController(vCustomers form) {
		this.form = form;
		this.form.btnGuardar.addActionListener(this); //CUANDO SE LE DA CLICK AL BOTON REACCIONA A UN EVENTO 
		this.form.btnLimpiar.addActionListener(this);
		init(); //ESTAN TODOS LOS METODOS QUE SE VAN INICIAR CUANDO INICIE EL CONSTRUCTOR
	}
	
	public void init() {
		refreshTable();
		Input.onlyNumbers(this.form.txtTelefono);
		Input.validateMaxLength(this.form.txtTelefono, 9);
		Input.onlyNumbers(this.form.txtDni);
		Input.validateMaxLength(this.form.txtDni, 8);
		
	}
	
	public void save() {
		if (validate()) { //VALIDA QUE LOS ESTEN LLENOS Y SI ES TRUE ENTRA AL IF
			Customer cliente = new Customer();
			cliente.setFirstName(this.form.txtNombres.getText());
			cliente.setLastName(this.form.txtApellidos.getText());
			cliente.setPhone(Integer.parseInt(this.form.txtTelefono.getText()));
			cliente.setEmail(this.form.txtEmail.getText());
			cliente.setDni(Integer.parseInt(this.form.txtDni.getText()));
			if (this.form.textId.getText().isEmpty()) { //SE VALIDA QUE LA CAJA DE TEXTO DEL ID ESTE VACIA
				// se valida si guardar o no
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas guardar el registro?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					// se guarda el proveedor
					customerService.save(cliente);
					clear();
					refreshTable();
					JOptionPane.showMessageDialog(null, "Se guardo correctamente", "Clientes",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				// se valida si se va a actualizar 
				int respuesta = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de que deseas actualizar el registro?", "Confirmación",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					// se captura el id
					cliente.setIdCustomer(Integer.parseInt(this.form.textId.getText()));
					// se guarda el proveedor
					customerService.update(cliente);
					clear();
					refreshTable();
					JOptionPane.showMessageDialog(null, "Se actualizo correctamente", "Clientes",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Completa todas las casillas", "Clientes",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	public void refreshTable() {
		// antes de cargar los registros, borramos al data para que no se repita
		for (int j = 0; j < this.form.jTableCustomers.getRowCount(); j++) {
			this.form.model.removeRow(j);
			j -= 1;
		}
		// trae los registros
		clientes = customerService.findAll();
		Object ob[] = new Object[8];
		for (int i = 0; i < clientes.size(); i++) {
			Customer cliente = (Customer) clientes.get(i);
			ob[0] = cliente.getIdCustomer();
			ob[1] = cliente.getFirstName();
			ob[2] = cliente.getLastName();
			ob[3] = cliente.getPhone();
			ob[4] = cliente.getEmail();
			ob[5] = cliente.getDni();
			this.form.model.addRow(ob);
		}
		// seteamos el model al jtable
		this.form.jTableCustomers.setModel(this.form.model);
	}
	
	public boolean validate() { //VALIDA QUE LAS CAJAS DE TEXTO ESTEN LLENAS ANTES DE GUARDAR
		if (!this.form.txtNombres.getText().trim().isEmpty() && !this.form.txtApellidos.getText().trim().isEmpty()
				&& !this.form.txtTelefono.getText().trim().isEmpty()
				&& !this.form.txtEmail.getText().trim().isEmpty()
				&& !this.form.txtDni.getText().trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() { //PARA EL BOTON LIMPIAR
		this.form.textId.setText("");
		this.form.txtNombres.setText("");
		this.form.txtApellidos.setText("");
		this.form.txtTelefono.setText("");
		this.form.txtEmail.setText("");
		this.form.txtDni.setText("");
		this.form.btnGuardar.setText("Guardar");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {// SE EJECUTA HAY UN EVENTO EN LOS COMPONENTES DE LA INTERFAZ GRAFICA
		Object press = ae.getSource();
		if (press == this.form.btnGuardar) {
			save();
		} else if (press == this.form.btnLimpiar) {
			clear();
		} else if (press == this.form.btnExcel) {
			//exportExcel();
		}
		
	}
	
	public void showView() {
		form.setVisible(true);
	}
	
}
