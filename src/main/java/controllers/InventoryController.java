package controllers;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import linkedList.LinkedList;
import linkedList.Node;
import models.Product;
import services.ProductService;
import subViews.vInventory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InventoryController implements ActionListener, KeyListener {

	private vInventory form;

	ProductService productService = new ProductService();
	// trae los registros
	LinkedList productos = productService.findAll();

	public InventoryController(vInventory form) {
		this.form = form;
		this.form.btnOrdenar.addActionListener(this);
		this.form.btnOrdenarId.addActionListener(this);
		this.form.btnExcel.addActionListener(this);
		this.form.textBusqueda.addKeyListener(this);
		init();
	}

	public void init() {
		refreshTable();

	}

	public void refreshTable() { // pinta los registros en la tabla de la vista
		// antes de cargar los registros, borramos al data para que no se repita
		for (int j = 0; j < this.form.jTableProducts.getRowCount(); j++) {
			this.form.model.removeRow(j);
			j -= 1;
		}
		Object ob[] = new Object[8];
		if (productos != null) {
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
		}
		// seteamos el model al jtable
		this.form.jTableProducts.setModel(this.form.model);
	}

	// intercambia la data del node, de la lista xd
	private void intercambiar(Node i, Node j) {
		Object temp = i.data;
		i.data = j.data;
		j.data = temp;
	}

	// ordena por nombre
	public void shell() {
		int intervalo, i, j, k;
		int n = productos.size();
		intervalo = n / 2;
		while (intervalo > 0) {
			for (i = intervalo; i < n; i++) {
				j = i - intervalo;
				while (j >= 0) {
					k = j + intervalo;
					Node iNode = productos.getNode(j);
					Node JNode = productos.getNode(k);
					Product primerValor = (Product) productos.get(j);
					Product segundoValor = (Product) productos.get(k);
					// el compareto retorna un int, si es menor significa que esta ordenado
					// alfabeticamente, en caso se cambia de pos
					if (primerValor.getName().compareTo(segundoValor.getName()) < 0)
						j = -1;
					else {
						intercambiar(iNode, JNode);
						j -= intervalo;
					}
				}
			}
			intervalo = intervalo / 2;
		}
	}

	// metodo para ordenar por id
	public void shellById() {
		int intervalo, i, j, k;
		int n = productos.size();
		intervalo = n / 2;
		while (intervalo > 0) {
			for (i = intervalo; i < n; i++) {
				j = i - intervalo;
				while (j >= 0) {
					k = j + intervalo;
					Node iNode = productos.getNode(j);
					Node JNode = productos.getNode(k);
					Product primerValor = (Product) productos.get(j);
					Product segundoValor = (Product) productos.get(k);
					if (primerValor.getIdProduct() < segundoValor.getIdProduct())
						j = -1;
					else {
						intercambiar(iNode, JNode);
						j -= intervalo;
					}
				}
			}
			intervalo = intervalo / 2;
		}
	}

	public void sortByName() {
		shell();
		refreshTable();
	}

	public void sortById() {
		shellById();
		refreshTable();
	}

	// metodo de busqueda binaria
	public int search(String valor) {
		productos = productService.findAll();
		shell();
		int central, bajo, alto;
		Product valorCentral;
		bajo = 0;
		alto = productos.size() - 1;
		while (bajo <= alto) {
			central = (bajo + alto) / 2;
			valorCentral = (Product) productos.get(central);
			if (valorCentral.getName().matches("(?i)(.*)" + valor + "(.*)"))
				return central;
			else if (valor.compareTo(valorCentral.getName()) < 0)
				alto = central - 1;
			else
				bajo = central + 1;
		}
		return -1; // elemento no encontrado
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub

		Object press = ae.getSource();
		if (press == this.form.btnOrdenar) {
			sortByName();
		} else if (press == this.form.btnOrdenarId) {
			sortById();
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
		sortById();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (this.form.textBusqueda.getText().trim().isEmpty()) {
			System.out.println("esta vacio");
			productos = productService.findAll();
			refreshTable();
		} else {
			LinkedList nuevoProductos = new LinkedList();
			Product producto = null;
			try {
				if (search(this.form.textBusqueda.getText()) != -1) {
					producto = (Product) productos.get(search(this.form.textBusqueda.getText()));
					nuevoProductos.addLast(producto);
				}
			} catch (Exception ea) {
				ea.getMessage();
			}
			if (nuevoProductos.size() > 0) {
				productos = nuevoProductos;
			} else {
				productos = null;
			}
			refreshTable();
			System.out.println(search(this.form.textBusqueda.getText()));
		}

	}

}
