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

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import extras.Input;
import hashTable.HashTable;
import hashTable.NodeHash;
import linkedList.LinkedList;
import models.Category;
import models.Product;
import models.Supplier;
import services.CategoryService;
import services.ProductService;
import services.SupplierService;
import subViews.vProducts;

public class ProductsController implements ActionListener, MouseListener {

	private vProducts form;
	CategoryService categoryService = new CategoryService();
	SupplierService supplierService = new SupplierService();
	ProductService productService = new ProductService();
	// un default para el combo
	NodeHash nodeDefault = new NodeHash(0, "<SELECCIONAR>");
	NodeHash noEditable = new NodeHash(0, "No editable");

	public ProductsController(vProducts form) {
		this.form = form;
		this.form.btnGuardar.addActionListener(this);
		this.form.btnLimpiar.addActionListener(this);
		this.form.btnExcel.addActionListener(this);
		this.form.jTableProducts.addMouseListener(this);
		init();
	}

	public void init() {
		Input.validateMaxLength(this.form.txtDescripcion, 30);
		refreshTable();
		loadCategories();
		loadSuppliers(); // Se llama al metodo para que aparezca los prov en el combo
	}

	public void loadCategories() {
		// cargar categorias
		LinkedList categorias = categoryService.findAll();
		// limpiamos el combo
		form.cbCategoria.removeAllItems();// se duplica en un caso por eso se limpia
		// un default para el combo
		form.cbCategoria.addItem(nodeDefault);
		// iteramos
		for (int i = 0; i < categorias.size(); i++) {
			Category categoria = (Category) categorias.get(i);
			// se crea el nodo
			NodeHash nodeCategoria = new NodeHash(categoria.getIdCategory(), categoria);
			// se agrega el nodo al combo, como tiene to string imprimira el tostring del
			// value en este caso del category
			form.cbCategoria.addItem(nodeCategoria);
		}
	}

	public void loadSuppliers() {
		LinkedList proveedores = supplierService.findAll();// Se obtiene una lista de proveedores de la base de datos
		form.cbProveedor.removeAllItems(); // Se limpia el combo
		// un default para el combo
		form.cbProveedor.addItem(nodeDefault);
		for (int i = 0; i < proveedores.size(); i++) {// Itera la lista de proveedores
			Supplier proveedor = (Supplier) proveedores.get(i); // Retorna objeto de tipo proveedor
			// se crea el nodo
			NodeHash nodeProveedor = new NodeHash(proveedor.getIdSupplier(), proveedor);
			form.cbProveedor.addItem(nodeProveedor); // Se agrega cada proveedor al combo
		}
	}

	// retorna el key del valor del parametro
	public int searchIdCbm(Object obj) {
		NodeHash selectedItem = (NodeHash) obj;
		return selectedItem.key;
	}

	public void save() {
		if (validate()) {
			Product producto = new Product();
			producto.setName(this.form.txtNombre.getText());
			producto.setDescription(this.form.txtDescripcion.getText());
			producto.setPrice(Double.parseDouble(this.form.txtPrecio.getText()));
			producto.setQuantity(Integer.parseInt(this.form.txtCantidad.getText()));
			// se le pasa el valor del combo, y te retornara su clave
			producto.setIdCategory(searchIdCbm(form.cbCategoria.getSelectedItem()));
			producto.setIdSupplier(searchIdCbm(form.cbProveedor.getSelectedItem()));
			if (this.form.textId.getText().isEmpty()) {
				// se valida si guardar o no
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas guardar el registro?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					productService.save(producto);
					clear();
					refreshTable();
					JOptionPane.showMessageDialog(null, "Se guardo correctamente", "Productos",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				// se valida si se va a actualizar
				int respuesta = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de que deseas actualizar el registro?", "Confirmación",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (respuesta == JOptionPane.YES_OPTION) {
					// se captura el id
					producto.setIdProduct(Integer.parseInt(this.form.textId.getText()));
					// se guarda el producto
					productService.update(producto);
					clear();
					refreshTable();
					JOptionPane.showMessageDialog(null, "Se actualizo correctamente", "Productos",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Completa todas las casillas", "Productos", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public void refreshTable() {
		// antes de cargar los registros, borramos al data para que no se repita
		for (int j = 0; j < this.form.jTableProducts.getRowCount(); j++) {
			this.form.model.removeRow(j);
			j -= 1;
		}
		// trae los registros
		LinkedList productos = productService.findAll();
		Object ob[] = new Object[8];
		for (int i = 0; i < productos.size(); i++) {
			Product producto = (Product) productos.get(i);
			ob[0] = producto.getIdProduct();
			ob[1] = producto.getName();
			ob[2] = producto.getDescription();
			ob[3] = producto.getPrice();
			ob[4] = producto.getQuantity();
			ob[5] = producto.getCategory();
			ob[6] = producto.getSname() + " " + producto.getSlastName();
			this.form.model.addRow(ob);
		}
		// seteamos el model al jtable
		this.form.jTableProducts.setModel(this.form.model);
	}

	public void clear() {
		this.form.textId.setText("");
		this.form.txtNombre.setText("");
		this.form.txtDescripcion.setText("");
		this.form.txtPrecio.setText("");
		this.form.txtCantidad.setText("");
		loadCategories();
		loadSuppliers();
		this.form.cbCategoria.setEnabled(true);
		this.form.cbProveedor.setEnabled(true);
		this.form.btnGuardar.setText("Guardar");
	}

	public boolean validate() {
		if (!this.form.txtNombre.getText().trim().isEmpty() && !this.form.txtDescripcion.getText().trim().isEmpty()
				&& !this.form.txtPrecio.getText().trim().isEmpty() && !this.form.txtCantidad.getText().trim().isEmpty()
				&& !this.form.cbCategoria.getSelectedItem().equals(nodeDefault)
				&& !this.form.cbProveedor.getSelectedItem().equals(nodeDefault)) {
			return true;
		} else {
			return false;
		}
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
				for (int i = 0; i < this.form.jTableProducts.getColumnCount(); i++) {
					// creamos las celdas dentro del excel
					Cell cell = rowCol.createCell(i);
					// asignamos un valor a las celdas
					cell.setCellValue(this.form.jTableProducts.getColumnName(i));
				}
				for (int j = 0; j < this.form.jTableProducts.getRowCount(); j++) {
					Row row = sheet.createRow(j);
					for (int k = 0; k < this.form.jTableProducts.getColumnCount(); k++) {
						Cell cell = row.createCell(k);
						if (this.form.jTableProducts.getValueAt(j, k) != null) {
							cell.setCellValue(this.form.jTableProducts.getValueAt(j, k).toString());
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

	//funcion para abrir el excel una vez lo hayamos guardado
	public void openFile(String file) {
		try {
			File ruta = new File(file);
			//este metodo permite abrir e imprimir ficheros
			Desktop.getDesktop().open(ruta);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void showView() {
		form.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// se captura la fila y se setea la data en las cajas
		int filaSeleccionada = this.form.jTableProducts.getSelectedRow();
		this.form.textId.setText(this.form.model.getValueAt(filaSeleccionada, 0).toString());
		this.form.txtNombre.setText(this.form.model.getValueAt(filaSeleccionada, 1).toString());
		this.form.txtDescripcion.setText(this.form.model.getValueAt(filaSeleccionada, 2).toString());
		this.form.txtPrecio.setText(this.form.model.getValueAt(filaSeleccionada, 3).toString());
		this.form.txtCantidad.setText(this.form.model.getValueAt(filaSeleccionada, 4).toString());
		// se le pone el default al combo y se deshabilita
		form.cbCategoria.addItem(noEditable);
		form.cbProveedor.addItem(noEditable);
		this.form.cbCategoria.setSelectedItem(noEditable);
		this.form.cbProveedor.setSelectedItem(noEditable);
		this.form.cbCategoria.setEnabled(false);
		this.form.cbProveedor.setEnabled(false);
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

}
