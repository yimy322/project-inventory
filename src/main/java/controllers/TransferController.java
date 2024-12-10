package controllers;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import RSMaterialComponent.RSButtonMaterialIconDos;
import colas.Colas;
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
import java.time.format.DateTimeFormatter;

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
		DefaultTableModel model = new DefaultTableModel(new String[] {"Nombre Producto", "Cantidad Producto", "Movimiento", "Total", "Proveedor", "Fecha", "Tipo", "Usuario"},0);
		
		Pilas transfers = transfersServiceSQL.findAll();
		transfers = insercionSortByDate(transfers);
		for(int i=0; i<transfers.size(); i++) {
			Transfers t = (Transfers) transfers.get(i);
				model.addRow(new Object[] {
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
		this.form.tableTransfers.setModel(model);
	}
	
	//PERMITE ACTUALIZAR ULTIMO ELEMENTO AGREGADO
	public Transfers getTransfersFinal() {
		Pilas transfers = transfersServiceSQL.findAll();
		if(transfers.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No hay registro aun, la pila esta vacia");
			return null;
		}else {
			transfers = insercionSortByDate(transfers);
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
			this.form.lblID.setText(String.valueOf(transfer.getIdTransfers()));
			loadListSuppliers();
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
		this.form.lblID.setText("0");
	}
	
	//VERIFICA QUE TODOS LOS CAMPOS DE LLENADO ESTEN COMPLETOS ANTES DE PROCEDER
	public boolean validated() {
		if(!this.form.txtProductName.getText().isEmpty() && (int)this.form.spnQuantity.getValue()>0)
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
		Colas products = getListProducts();
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
					Transfers transfers = new Transfers(0, cant, LoginController.USER.getIdUser(), product.getIdProduct(), sqlTimestamp.toString(), "INGRESO", product.getName(), LoginController.USER.getUsername(), product.getQuantity(), product.getSname(), (cant+product.getQuantity()));
					//INGRESAMOS LOS DATOS A LA BASE DE DATOS, REGISTRO DE TRANSLADOS
					transfersServiceSQL.insert(transfers);
					//PERMITE SUMAR O ACTUALIZAR LOS DATOS EN BASE DE DATOS RESPECTO A UN PRODUCTO
					transfersServiceSQL.plusQuantity(nameP, cant, product.getIdSupplier());
					loadTable();				
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Translado registrado correctamente");
		clear();
	}
	
	//PERMITE ORDENAR LA PILA SEGUN LA CANTIDAD EXISTENTE DE MENOR A MENOS --USANDO EL ALGORITMO DE ORDENAMIENTO POR INSERCCION
	public Colas insercionSort(Colas lista) {
		int n = lista.size();
	    for (int i = 1; i < n; i++) {
	        int j = i;
	        while (j > 0) {
	            // Obtener los elementos actuales
	            Product current = (Product) lista.get(j);
	            Product previous = (Product) lista.get(j - 1);

	            // Comparar cantidades y realizar intercambio si es necesario
	            if (previous.getQuantity() > current.getQuantity()) {
	                lista.exchage_position(j, j - 1);
	            } else {
	                break; // Salir del bucle si ya está en el lugar correcto
	            }

	            j--; // Reducir el índice para continuar revisando hacia atrás
	        }
	    }
	    return lista;
	}
	
	//permite ORDENAR LA TABLA EN ORDDEN DESCENDENTE POR LA FECHA MAS RECIENTE A LA MAS ANTIGUA
	public Pilas insercionSortByDate(Pilas lista) {
	    int n = lista.size();
	    for (int i = 1; i < n; i++) {
	        int j = i;
	        while (j > 0) {
	            // Obtener los elementos actuales
	            Transfers current = (Transfers) lista.get(j);
	            Transfers previous = (Transfers) lista.get(j - 1);

	            //FORMATEAMOS LA FECHA EN FORMATO STRING A UN FORMATO DE FECHA
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime dateTimeCurrent = LocalDateTime.parse(current.getFecha(), formatter);
	            LocalDateTime dateTimePrevious = LocalDateTime.parse(previous.getFecha(), formatter);
	            // Obtener las fechas (asegúrate de que `getDateTime` devuelva un LocalDateTime)
	            LocalDateTime currentDate = (LocalDateTime) dateTimeCurrent;
	            LocalDateTime previousDate = (LocalDateTime) dateTimePrevious;

	            // Comparar las fechas y realizar intercambio si es necesario
	            if (previousDate.isBefore(currentDate)) { //Para orden ascendente previousDate.isAfter(currentDate)
	            	lista.exchage_position(j, j - 1);	  //Para orden descendente previousDate.isBefore(currentDate)
	            } else {
	                break; // Salir del bucle si ya está en el lugar correcto
	            }

	            j--; // Reducir el índice para continuar revisando hacia atrás
	        }
	    }
	    return lista;
	}

	
	//PERMITE RESTAR EN CANTIDAD SEGUN SU NOMBRE UNICO Y DETERMINADOS PROVEEDORES
	public void subtractQuantity() {
		if(!validated()) {
			JOptionPane.showMessageDialog(null, "Complete los todos los campos por favor!");
			return;
		}
		if(this.form.listSuppliers.getModel() == null) {
			return;
		}
		int general = Integer.parseInt(this.form.lblTotal.getText());
		int cantRestar = (int) this.form.spnQuantity.getValue();
		if(cantRestar > general) {
			JOptionPane.showMessageDialog(null, "La cantidad a restar supera la cantidad actual");
			return;
		}
		
		Object[] selectedItems = this.form.listSuppliers.getSelectedValues();
		Colas products = getListProducts();
		
		
		int itemsCant = selectedItems.length;
		int prodsCant = products.size();
		
		Object[] option = {"POR IGUAL", "POR GLOBAL"};
		int opcion = JOptionPane.showOptionDialog(null, "Elige un formato", "EXPORTAR", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

		switch(opcion) {
			case 0:
				int confir1 = JOptionPane.showConfirmDialog(null, "Esta seguro de descontar "+cantRestar+ " para cada producto seleccionado", "Alerta", JOptionPane.YES_NO_OPTION);
				if(confir1 == JOptionPane.YES_OPTION) {
					subtractEqually(itemsCant, prodsCant);
					JOptionPane.showMessageDialog(null, "Translado registrado correctamente");
				}
				break;
			case 1:
				int confir2 = JOptionPane.showConfirmDialog(null, "Esta seguro de querer descontar "+cantRestar+" globalmente", "Alerta", JOptionPane.YES_NO_OPTION);
				if(confir2 == JOptionPane.YES_OPTION) {
					subtractUnequal(itemsCant, prodsCant, cantRestar);
					JOptionPane.showMessageDialog(null, "Translado registrado correctamente");
				}
				break;
		}

		clear();
	}
	
	//METODO PERMITE RESTAR UNA MISMA CANTIDAD PARA TODOS LOS ITEMS SELECCIONADOS
	public void subtractEqually(int itemsCant, int prodsCant) {
		Object[] selectedItems = this.form.listSuppliers.getSelectedValues();
		Colas products = getListProducts();
		for(int i=0; i<itemsCant; i++) {
			for (int j = 0; j < prodsCant; j++) {
				Product product = (Product) products.get(j);
				if(this.form.txtProductName.getText().toLowerCase().equals(product.getName().toLowerCase()) && selectedItems[i].toString().toLowerCase().equals(product.getSname().toLowerCase().toString())) {
					String nameP = this.form.txtProductName.getText();
					int cant = (int) this.form.spnQuantity.getValue();
					if(cant > product.getQuantity()) {
						JOptionPane.showMessageDialog(null, "La cantidad a restar supera la cantidad actual de un producto seleccionado");
						return;
					}
					//permite encontrar la hora y fecha actual en formato mysql
					LocalDateTime currentDateTime = LocalDateTime.now();
					Timestamp sqlTimestamp = Timestamp.valueOf(currentDateTime);
					//ISNTANCIAMOS LA CLASE TRANSEFERS Y LLENAMOS CON DATOS
					Transfers transfers = new Transfers(0, cant, LoginController.USER.getIdUser(), product.getIdProduct(), sqlTimestamp.toString(), "SALIDA", product.getName(), LoginController.USER.getUsername(), product.getQuantity(), product.getSname(), (product.getQuantity()-cant));
					//INGRESAMOS LOS DATOS A LA BASE DE DATOS, REGISTRO DE TRANSLADOS
					transfersServiceSQL.insert(transfers);
					//PERMITE SUMAR O ACTUALIZAR LOS DATOS EN BASE DE DATOS RESPECTO A UN PRODUCTO
					transfersServiceSQL.lessQuantity(nameP, cant, product.getIdSupplier());
					loadTable();
				}
			}
		}
	}
	
	//PERMITE DESCONTAR UNA CANTIDAD DIFERENTE PARA CADA ITEM SELECCIONADO, DEPENDIENDO DE LA CANTIDAD A RESTAR Y LA EXISTENCIA DEL PRODUCTO
	public void subtractUnequal(int itemsCant, int prodsCant, int cantRestar) { 
	    Object[] selectedItems = this.form.listSuppliers.getSelectedValues();
	    Colas products = getListProducts();
	    int restarTemp = cantRestar;

	    for (int i = 0; i < itemsCant; i++) { 
	        for (int j = 0; j < prodsCant; j++) { 
	            Product product = (Product) products.get(j);

	            // Verifica si el producto y el proveedor coinciden
	            if (this.form.txtProductName.getText().toLowerCase().equals(product.getName().toLowerCase()) && 
	                selectedItems[i].toString().toLowerCase().equals(product.getSname().toLowerCase())) { 
	                
	                String nameP = this.form.txtProductName.getText();

	                if (cantRestar <= 0) {
	                    break; // Si no queda nada por restar, termina el proceso
	                }

	                // Cantidad a restar del producto actual
	                int cantidadARestar = Math.min(product.getQuantity(), cantRestar);

	                // Actualiza la cantidad restante
	                cantRestar -= cantidadARestar;

	                // Calcula los valores finales
	                LocalDateTime currentDateTime = LocalDateTime.now();
	                Timestamp sqlTimestamp = Timestamp.valueOf(currentDateTime);

	                Transfers transfers = new Transfers(0, cantidadARestar, LoginController.USER.getIdUser(), product.getIdProduct(), sqlTimestamp.toString(), 
	                    "SALIDA", product.getName(), LoginController.USER.getUsername(), product.getQuantity(), product.getSname(), (product.getQuantity() - cantidadARestar));

	                // Inserta el registro de transferencia en la base de datos
	                transfersServiceSQL.insert(transfers);

	                // Actualiza la cantidad del producto en la base de datos
	                transfersServiceSQL.lessQuantity(nameP, cantidadARestar, product.getIdSupplier());

	                // Recarga la tabla
	                loadTable();
	            }
	        }
	    }
	}

	
	//PERMITE RETORNAR UNA LISTA DE PERSONALIZADA SEGUN LA BUSQUEDA
	public void loadListSuppliers() {
		if (!this.form.txtProductName.getText().isEmpty()) {
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			Colas products = getListProducts();
			products = insercionSort(products);
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
	
	// PERMITE RETORNAR UNA LISTA DE PERSONALIZADA SEGUN LA BUSQUEDA
	public Colas getListProducts() {
		Colas colasP = new Colas();
		if (!this.form.txtProductName.getText().isEmpty()) {
			LinkedList products = productServiceSQL.findAll();
			for (int i = 0; i < products.size(); i++) {
				Product product = (Product) products.get(i);
				if (this.form.txtProductName.getText().toLowerCase().equals(product.getName().toLowerCase())) {
					colasP.enqueue(product);
				}
			}
		}
		return colasP;
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
				for (int i = 0; i < this.form.tableTransfers.getColumnCount(); i++) {
					// creamos las celdas dentro del excel
					Cell cell = rowCol.createCell(i);
					// asignamos un valor a las celdas
					cell.setCellValue(this.form.tableTransfers.getColumnName(i));
				}
				for (int j = 0; j < this.form.tableTransfers.getRowCount(); j++) {
					Row row = sheet.createRow(j);
					for (int k = 0; k < this.form.tableTransfers.getColumnCount(); k++) {
						Cell cell = row.createCell(k);
						if (this.form.tableTransfers.getValueAt(j, k) != null) {
							cell.setCellValue(this.form.tableTransfers.getValueAt(j, k).toString());
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

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object press = ae.getSource();
		if (press == this.form.btnPlus) {
			addQuantity();
		}else if(press == this.form.btnUpdateFinal) {
			fillInfoFinal();
		}else if(press == this.form.btnLess) {
			subtractQuantity();
		}else if(press == this.form.btnReport) {
			exportExcel();
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
		loadListSuppliers();
	}

	//permite controlar los eventos referentes a los clik en el Jlist
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Object pressItem = e.getSource();
		if(pressItem == this.form.listSuppliers) {
			this.form.lblTotal.setText(String.valueOf(getQuantityProduct()));
		}
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
