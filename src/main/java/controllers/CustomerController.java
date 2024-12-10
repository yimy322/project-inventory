package controllers;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import arbol.Arbol;
import arbol.Nodo;
import extras.Input;
import linkedList.LinkedList;
import models.Customer;
import models.Supplier;
import services.CustomerService;
import services.SupplierService;
import subViews.vCustomers;

public class CustomerController implements ActionListener, MouseListener { // SE IMPLEMENTA LA INTERFAZ ACTIONLISTENER

	private vCustomers form;
	CustomerService customerService = new CustomerService();
	LinkedList clientes = customerService.findAll(); // PARA LISTAR EN LA TABLA

	public CustomerController(vCustomers form) {
		this.form = form;
		this.form.btnGuardar.addActionListener(this); // CUANDO SE LE DA CLICK AL BOTON REACCIONA A UN EVENTO
		this.form.btnLimpiar.addActionListener(this);
		this.form.btnExcel.addActionListener(this);
		this.form.btnBuscar.addActionListener(this);
		this.form.jTableCustomers.addMouseListener(this);
		init(); // ESTAN TODOS LOS METODOS QUE SE VAN INICIAR CUANDO INICIE EL CONSTRUCTOR
	}

	public void init() {
		refreshTable();
		Input.onlyNumbers(this.form.txtTelefono);
		Input.validateMaxLength(this.form.txtTelefono, 9);
		Input.onlyNumbers(this.form.txtDni);
		Input.validateMaxLength(this.form.txtDni, 8);

	}

	public void save() {
		if (validate()) { // VALIDA QUE LOS ESTEN LLENOS Y SI ES TRUE ENTRA AL IF
			Customer cliente = new Customer();
			cliente.setFirstName(this.form.txtNombres.getText());
			cliente.setLastName(this.form.txtApellidos.getText());
			cliente.setPhone(Integer.parseInt(this.form.txtTelefono.getText()));
			cliente.setEmail(this.form.txtEmail.getText());
			cliente.setDni(Integer.parseInt(this.form.txtDni.getText()));
			if (this.form.textId.getText().isEmpty()) { // SE VALIDA QUE LA CAJA DE TEXTO DEL ID ESTE VACIA
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
			JOptionPane.showMessageDialog(null, "Completa todas las casillas", "Clientes", JOptionPane.ERROR_MESSAGE);
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

	public boolean validate() { // VALIDA QUE LAS CAJAS DE TEXTO ESTEN LLENAS ANTES DE GUARDAR
		if (!this.form.txtNombres.getText().trim().isEmpty() && !this.form.txtApellidos.getText().trim().isEmpty()
				&& !this.form.txtTelefono.getText().trim().isEmpty() && !this.form.txtEmail.getText().trim().isEmpty()
				&& !this.form.txtDni.getText().trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() { // PARA EL BOTON LIMPIAR
		this.form.textId.setText("");
		this.form.txtNombres.setText("");
		this.form.txtApellidos.setText("");
		this.form.txtTelefono.setText("");
		this.form.txtEmail.setText("");
		this.form.txtDni.setText("");
		this.form.btnGuardar.setText("Guardar");
	}

	public void exportExcel() {
		try {
			// instanciamos la clase, el JFileChooser sirve para seleccionar el directorio
			JFileChooser chooser = new JFileChooser();
			// el metodo showsavediaolog es el que muestra el cuadro de dialogo
			chooser.showSaveDialog(this.form);
			// capturamos el archivo
			File guardar = chooser.getSelectedFile();
			// validamos que se haya capturado una ruta
			if (guardar != null) {
				// le pasamos la extension al archivo, file guardara una cadena
				guardar = new File(guardar.toString() + ".xlsx");
				// aca creamos un libro de excel
				Workbook wb = new XSSFWorkbook();
				// creamos una hoja dentro el libro de excel
				Sheet sheet = wb.createSheet("customer");
				// se crea una fila dentro de la hoja
				Row rowCol = sheet.createRow(0);
				// recoremos las columnas de nuestra tabla
				for (int i = 0; i < this.form.jTableCustomers.getColumnCount(); i++) {
					// creamos las celdas dentro del excel
					Cell cell = rowCol.createCell(i);
					// asignamos un valor a las celdas
					cell.setCellValue(this.form.jTableCustomers.getColumnName(i));
				}
				for (int j = 0; j < this.form.jTableCustomers.getRowCount(); j++) {
					Row row = sheet.createRow(j);
					for (int k = 0; k < this.form.jTableCustomers.getColumnCount(); k++) {
						Cell cell = row.createCell(k);
						if (this.form.jTableCustomers.getValueAt(j, k) != null) {
							cell.setCellValue(this.form.jTableCustomers.getValueAt(j, k).toString());
						}
					}
				}
				// escribimos los resultados en un fichero excel
				FileOutputStream out = new FileOutputStream(new File(guardar.toString()));
				wb.write(out);
				// aca cerramos
				wb.close();
				out.close();
				openFile(guardar.toString());
			} else {
				// en caso de que no se haya guardado me mostrara un mensaje
				JOptionPane.showMessageDialog(null, "Error al generar archivo", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException ie) {
			System.out.println(ie);
		}
	}

	// funcion para abrir el excel una vez lo hayamos guardado
	public void openFile(String file) {
		try {
			File ruta = new File(file);
			// este metodo permite abrir e imprimir ficheros
			Desktop.getDesktop().open(ruta);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void searchCustomer() {
		int dni = 0;
		String input = JOptionPane.showInputDialog(null, "Introduce el DNI:", "Clientes", JOptionPane.QUESTION_MESSAGE);
		try {
			dni = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa solo numeros enteros");
            return;
        }
		//se crea el arbol
		Arbol clientesArbol = new Arbol();
		//se itera y se agrega al arbol
		clientes = customerService.findAll();
		Object ob[] = new Object[8];
		for (int i = 0; i < clientes.size(); i++) {
			Customer cliente = (Customer) clientes.get(i);
			//crea el arbol de DNI
			clientesArbol.insertar(cliente.getDni());
		}
		clientesArbol.imprimirArbol();
		Nodo nodoCliente = clientesArbol.buscar(dni);
		if(nodoCliente!=null){
			JOptionPane.showMessageDialog(null, "El cliente con DNI "+ nodoCliente.valor +" si existe en el sistema", "Clientes", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "El DNI del cliente ingresado no existe", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {// SE EJECUTA HAY UN EVENTO EN LOS COMPONENTES DE LA INTERFAZ GRAFICA
		Object press = ae.getSource();
		if (press == this.form.btnGuardar) {
			save();
		} else if (press == this.form.btnLimpiar) {
			clear();
		} else if (press == this.form.btnExcel) {
			exportExcel();
		}else if (press == this.form.btnBuscar) {
			searchCustomer();
		}

	}

	public void showView() {
		form.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// se captura la fila y se setea la data en las cajas
		int filaSeleccionada = this.form.jTableCustomers.getSelectedRow();
		this.form.textId.setText(this.form.model.getValueAt(filaSeleccionada, 0).toString());
		this.form.txtNombres.setText(this.form.model.getValueAt(filaSeleccionada, 1).toString());
		this.form.txtApellidos.setText(this.form.model.getValueAt(filaSeleccionada, 2).toString());
		this.form.txtTelefono.setText(this.form.model.getValueAt(filaSeleccionada, 3).toString());
		this.form.txtEmail.setText(this.form.model.getValueAt(filaSeleccionada, 4).toString());
		this.form.txtDni.setText(this.form.model.getValueAt(filaSeleccionada, 5).toString());
		this.form.btnGuardar.setText("Actualizar");
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
