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

import extras.Input;
import linkedList.LinkedList;
import models.Supplier;
import services.SupplierService;
import subViews.vSuppliers;

public class SupplierController implements ActionListener, MouseListener {

	private vSuppliers form;
	SupplierService supplierService = new SupplierService();

	public SupplierController(vSuppliers form) {
		this.form = form;
		this.form.btnGuardar.addActionListener(this);
		this.form.btnLimpiar.addActionListener(this);
		this.form.btnExcel.addActionListener(this);
		this.form.jTableCustomers.addMouseListener(this);
		init();
	}

	public void init() {
		refreshTable();
		Input.onlyNumbers(this.form.txtTelefono);
		Input.validateMaxLength(this.form.txtTelefono, 9);
	}

	public void save() {
		if (validate()) {
			Supplier proveedor = new Supplier();
			proveedor.setName(this.form.txtNombre.getText());
			proveedor.setLastName(this.form.txtApellido.getText());
			proveedor.setPhone(Integer.parseInt(this.form.txtTelefono.getText()));
			proveedor.setAddress(this.form.txtDireccion.getText());
			if (this.form.textId.getText().isEmpty()) {
				// se valida si guardar o no
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas guardar el registro?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					// se guarda el proveedor
					supplierService.save(proveedor);
					clear();
					refreshTable();
					JOptionPane.showMessageDialog(null, "Se guardo correctamente", "Proveedores",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				// se valida si se va a actualizar
				int respuesta = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de que deseas actualizar el registro?", "Confirmación",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					// se captura el id
					proveedor.setIdSupplier(Integer.parseInt(this.form.textId.getText()));
					// se guarda el proveedor
					supplierService.update(proveedor);
					clear();
					refreshTable();
					JOptionPane.showMessageDialog(null, "Se actualizo correctamente", "Proveedores",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Completa todas las casillas", "Proveedores",
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
		LinkedList proveedores = supplierService.findAll();
		Object ob[] = new Object[8];
		for (int i = 0; i < proveedores.size(); i++) {
			Supplier proveedor = (Supplier) proveedores.get(i);
			ob[0] = proveedor.getIdSupplier();
			ob[1] = proveedor.getName();
			ob[2] = proveedor.getLastName();
			ob[3] = proveedor.getPhone();
			ob[4] = proveedor.getAddress();
			this.form.model.addRow(ob);
		}
		// seteamos el model al jtable
		this.form.jTableCustomers.setModel(this.form.model);
	}

	public boolean validate() {
		if (!this.form.txtNombre.getText().trim().isEmpty() && !this.form.txtApellido.getText().trim().isEmpty()
				&& !this.form.txtTelefono.getText().trim().isEmpty()
				&& !this.form.txtDireccion.getText().trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		this.form.textId.setText("");
		this.form.txtNombre.setText("");
		this.form.txtApellido.setText("");
		this.form.txtTelefono.setText("");
		this.form.txtDireccion.setText("");
		this.form.btnGuardar.setText("Guardar");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object press = ae.getSource();
		if (press == this.form.btnGuardar) {
			save();
		} else if (press == this.form.btnLimpiar) {
			clear();
		} else if (press == this.form.btnExcel) {
			exportExcel();
		}
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

	// acciones
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// se captura la fila y se setea la data en las cajas
		int filaSeleccionada = this.form.jTableCustomers.getSelectedRow();
		this.form.textId.setText(this.form.model.getValueAt(filaSeleccionada, 0).toString());
		this.form.txtNombre.setText(this.form.model.getValueAt(filaSeleccionada, 1).toString());
		this.form.txtApellido.setText(this.form.model.getValueAt(filaSeleccionada, 2).toString());
		this.form.txtTelefono.setText(this.form.model.getValueAt(filaSeleccionada, 3).toString());
		this.form.txtDireccion.setText(this.form.model.getValueAt(filaSeleccionada, 4).toString());
		this.form.btnGuardar.setText("Actualizar");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void showView() {
		form.setVisible(true);
	}

}
