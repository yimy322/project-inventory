package controllers;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import extras.Input;
import hashTable.HashTable;
import hashTable.NodeHash;
import linkedList.LinkedList;
import models.Category;
import models.Customer;
import models.Order;
import models.OrderDetail;
import models.Product;
import models.Supplier;
import services.CategoryService;
import services.CustomerService;
import services.OrderDetailService;
import services.OrderService;
import services.ProductService;
import services.SupplierService;
import subViews.vSales;

public class SalesController implements ActionListener {

	private vSales form;
	CustomerService customerService = new CustomerService();
	ProductService productService = new ProductService();
	CategoryService categoryService = new CategoryService();
	SupplierService supplierService = new SupplierService();
	OrderService orderService = new OrderService();
	OrderDetailService orderDetailService = new OrderDetailService();
	// trae los registros de la bd
	LinkedList clientes = customerService.findAll();
	HashTable clientesHash = new HashTable(10);// tam del ht

	// un default para el combo
	NodeHash nodeDefault = new NodeHash(0, "<SELECCIONAR>");
	NodeHash noEditable = new NodeHash(0, "No editable");
	LinkedList productos = productService.findAll();
	int cantidad = 0;
	int cantidadElegida = 0;
	int cantidadTotal = 0;
	double totalNeto = 0.00;
	// variables finales
	Customer clienteFinal = null;

	public SalesController(vSales form) {
		this.form = form;
		this.form.btnBuscarCliente.addActionListener(this);
		this.form.cbCategoria.addActionListener(this);
		this.form.cbProveedor.addActionListener(this);
		this.form.cbProducto.addActionListener(this);
		this.form.btnAgregarProducto.addActionListener(this);
		this.form.btnEliminarTodo.addActionListener(this);
		this.form.btnEliminarUltimo.addActionListener(this);
		this.form.btnVenta.addActionListener(this);
		init();
	}

	public void init() {
		Input.onlyNumbers(this.form.txtDni);
		Input.validateMaxLength(this.form.txtDni, 8);
		// se carga la data en el hashtable
		loadDataHashTable();
		loadCategories();
		loadSuppliers();
		// un default para el combo
		this.form.cbProducto.addItem(nodeDefault);
		this.form.txtCantidadProd.setText(cantidadTotal + "");
		this.form.txtTotalProd.setText(totalNeto + "");
	}

	public void searchCustomer() {
		if (this.form.txtDni.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, ingresar un DNI", "Ventas", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int dni = Integer.parseInt(this.form.txtDni.getText());
		NodeHash resultProducto = clientesHash.search(dni);
		if (resultProducto != null) {
			Customer cliente = (Customer) resultProducto.value;
			this.form.txtNombres.setText(cliente.getFirstName());
			this.form.txtApellidos.setText(cliente.getLastName());
			this.form.txtTelefono.setText(cliente.getPhone() + "");
			this.form.txtEmail.setText(cliente.getEmail());
			clienteFinal = cliente;
			return;
		}
		JOptionPane.showMessageDialog(null, "El DNI " + dni + " no existe, por favor verificar.", "Ventas",
				JOptionPane.WARNING_MESSAGE);
		clearCustomer();
	}

	public void loadDataHashTable() {
		for (int i = 0; i < clientes.size(); i++) {
			Customer producto = (Customer) clientes.get(i);
			// se crea el nodo
			NodeHash productoNode = new NodeHash(producto.getDni(), producto);
			// se agrega al hastTable
			clientesHash.addToHashTable(productoNode);
		}
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
			NodeHash nodeProveedor = new NodeHash(proveedor.getIdSupplier(), proveedor);
			form.cbProveedor.addItem(nodeProveedor); // Se agrega cada proveedor al combo
		}
	}

	public void loadProducts() {
		form.cbProducto.removeAllItems();
		form.cbProducto.addItem(nodeDefault);
		for (int i = 0; i < productos.size(); i++) {
			Product producto = (Product) productos.get(i);
			NodeHash nodeProducto = new NodeHash(producto.getIdProduct(), producto);
			// captura los id de los combos
			int indCategorias = searchIdCbm(form.cbCategoria.getSelectedItem());
			int indProveedores = searchIdCbm(form.cbProveedor.getSelectedItem());
			// en caso alguno de ellos tenga dato
			if (indCategorias > 0 || indProveedores > 0) {
				// los dos deben tener data para que se agregue al combo de productos
				if (producto.getIdCategory() == indCategorias && producto.getIdSupplier() == indProveedores) {
					// si matchea se agrega. EN CASO CONTRARIO, NO
					form.cbProducto.addItem(nodeProducto);
				}
			}
		}
	}

	public int searchIdCbm(Object obj) {
		NodeHash selectedItem = (NodeHash) obj;
		return selectedItem.key;
	}

	public void clearCustomer() {
		this.form.txtDni.setText("");
		this.form.txtNombres.setText("");
		this.form.txtApellidos.setText("");
		this.form.txtTelefono.setText("");
		this.form.txtEmail.setText("");
	}

	public void loadQuantity() {
		if (this.form.cbProducto.getSelectedItem() != null) {
			if (!this.form.cbProducto.getSelectedItem().equals(nodeDefault)) {
				this.form.txtCantidad.setEditable(true);
				NodeHash node = (NodeHash) this.form.cbProducto.getSelectedItem();
				Product producto = (Product) node.value;
				cantidad = producto.getQuantity();
				this.form.lblMaximo.setText("Max. " + cantidad);
			} else {
				this.form.txtCantidad.setEditable(false);
				this.form.lblMaximo.setText("Max. 0");
				cantidad = 0;
			}
		}
	}

	public void addProduct() {
		if (this.form.txtCantidad.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe indicar la cantidad", "Ventas", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int cantidadActual = Integer.parseInt(this.form.txtCantidad.getText());
		if (cantidadActual < 0) {
			JOptionPane.showMessageDialog(null, "La cantidad no puede ser negativa", "Ventas",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (cantidadActual > cantidad) {
			JOptionPane.showMessageDialog(null, "La cantidad no puede sobrepasar a la cantidad maxima", "Ventas",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		cantidadElegida = cantidadActual;
		// logica para agregar el producto
		int idProducto = searchIdCbm(form.cbProducto.getSelectedItem());
		// capturar desde la lista
		Product nuevoProducto = searchProductById(idProducto);
		addProducts(nuevoProducto);
		loadProducts();
		this.form.txtCantidad.setText("");
	}

	public Product searchProductById(int idProducto) {
		for (int i = 0; i < productos.size(); i++) {
			Product producto = (Product) productos.get(i);
			if (producto.getIdProduct() == idProducto) {
				return producto;
			}
		}
		return null;
	}

	public void addProducts(Product prod) {
		// se creo una copia del nuevo producto
		Product nuevoProducto = new Product(prod);
		// se calcula el total
		double total = prod.getPrice() * cantidadElegida;
		// se calcula la cantidad restante
		cantidad = prod.getQuantity() - cantidadElegida;
		// aca se actualiza la cantidad del producto
		nuevoProducto.setQuantity(cantidad);
		productos.update(prod, nuevoProducto);
		productos.print();
		// -------
		this.form.lblMaximo.setText("Max. " + cantidad);
		// verificamos que en caso el producto exista se incremente la cantidad
		boolean productoExistente = existProduct(prod);
		// ---
		if (!productoExistente) {
			Object ob[] = new Object[8];
			ob[0] = prod.getIdProduct();
			ob[1] = prod.getName();
			ob[2] = prod.getDescription();
			ob[3] = prod.getPrice();
			ob[4] = cantidadElegida;
			ob[5] = total;
			this.form.model.addRow(ob);
		}
		this.form.jTableProducts.setModel(this.form.model);
		// actualiza la cantidad
		updateQuantity();
		updateTotal();
	}

	public boolean existProduct(Product prod) {
		for (int i = 0; i < this.form.model.getRowCount(); i++) {
			int idProductoTabla = (int) this.form.model.getValueAt(i, 0); // capturamos el id en la primera columna
			if (idProductoTabla == prod.getIdProduct()) {
				// si existe se incremente la cantidad
				int cantidadExistente = (int) this.form.model.getValueAt(i, 4);
				this.form.model.setValueAt(cantidadExistente + cantidadElegida, i, 4); // actualizamos la cantidad
				this.form.model.setValueAt((cantidadExistente + cantidadElegida) * prod.getPrice(), i, 5); // actualizamos
																											// el total
				return true;
			}
		}
		return false;
	}

	// actualiza la cantidad
	public void updateQuantity() {
		cantidadTotal = 0;
		for (int i = 0; i < this.form.model.getRowCount(); i++) {
			int cantPorProducto = (int) this.form.model.getValueAt(i, 4);
			cantidadTotal += cantPorProducto;
		}
		this.form.txtCantidadProd.setText(cantidadTotal + "");
	}

	// actualiza el total
	public void updateTotal() {
		totalNeto = 0.00;
		for (int i = 0; i < this.form.model.getRowCount(); i++) {
			double totalPorProducto = (double) this.form.model.getValueAt(i, 5);
			totalNeto += totalPorProducto;
		}
		this.form.txtTotalProd.setText(totalNeto + "");
	}

	// elimina todo
	public void deleteAll() {
		for (int j = 0; j < this.form.jTableProducts.getRowCount(); j++) {
			this.form.model.removeRow(j);
			j -= 1;
		}
		productos = productService.findAll();
		loadProducts();
		cantidadTotal = 0;
		totalNeto = 0.00;
		this.form.txtCantidadProd.setText(cantidadTotal + "");
		this.form.txtTotalProd.setText(totalNeto + "");
	}

	// metodo para eliminar la ultima fila
	public void deleteRow() {
		int rowCount = this.form.model.getRowCount();
		if (rowCount > 0) {
			int id = (int) this.form.model.getValueAt(rowCount - 1, 0);
			Product productoActual = searchProductById(id);
			Product productoNuevo = productService.selectById(id);
			productos.update(productoActual, productoNuevo);// usa el update de listas enlazadas dobles
			this.form.model.removeRow(rowCount - 1); // elimina la ultima fila
		}
		loadProducts();
		updateQuantity();
		updateTotal();
	}

	public void sales() {
		// se valida que haya elegido el cliente
		if (clienteFinal == null) {
			JOptionPane.showMessageDialog(null, "Debe ingresar al cliente", "Ventas", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// se valida que se haya elegido algun producto
		int rowCount = this.form.model.getRowCount();
		if (rowCount <= 0) {
			JOptionPane.showMessageDialog(null, "Debe elegir algun producto", "Ventas", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null,
				"¿Estás seguro de que deseas realizar la venta?\n" + "- Cliente: " + clienteFinal.getFirstName() + " "
						+ clienteFinal.getLastName() + " - " + clienteFinal.getDni() + "\n" + "- Cantidad de producto:"
						+ cantidadTotal + "\n" + "- Total: S/." + totalNeto + "\n",
				"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		//para mostrar en el ticket
		LinkedList listaProd = new LinkedList();
		if (respuesta == JOptionPane.YES_OPTION) {
			// se guarda la venta
			Order venta = new Order();
			LocalDate fechaActual = LocalDate.now();
			venta.setOrderDate(fechaActual + "");
			venta.setIdCustomer(clienteFinal.getIdCustomer());
			venta.setType(1);// SALIDA
			int idVenta = orderService.save(venta);
			for (int i = 0; i < this.form.model.getRowCount(); i++) {
				int id = (int) this.form.model.getValueAt(i, 0);
				// se busca el producto en la lista enlazada
				Product productoActual = searchProductById(id);
				// se saca el producto de la bd
				Product productoBD = productService.selectById(id);
				int cantidadDiferencia = productoBD.getQuantity() - productoActual.getQuantity();
				productoBD.setQuantity(productoActual.getQuantity());// seteamos al cantidad
				productService.update(productoBD);// actualizamos en la bd
				//cantidad referencia para setear en el ticket
				productoActual.setQuantityRef(cantidadDiferencia);
				listaProd.addLast(productoActual);//se agrega al final
				// registramos el detalle
				OrderDetail ventaDetalle = new OrderDetail();
				ventaDetalle.setUnitPrice(productoActual.getPrice());
				ventaDetalle.setQuantity(cantidadDiferencia);
				ventaDetalle.setTotal(productoActual.getPrice() * cantidadDiferencia);
				ventaDetalle.setIdOrder(idVenta);
				ventaDetalle.setIdProduct(productoActual.getIdProduct());
				ventaDetalle.setIdUser(LoginController.USER.getIdUser());
				orderDetailService.save(ventaDetalle);
			}
			JOptionPane.showMessageDialog(null, "La venta se genero con exito y se genero el ticket", "Ventas",
					JOptionPane.WARNING_MESSAGE);
		}
		generateTicket(totalNeto, listaProd);
		deleteAll();
	}

	public void generateTicket(double total, LinkedList listaProd ) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		int ancho = 400;
		int alto = 600;
		// creamos una imagen
		BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = imagen.createGraphics();

		// color de fondo
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, ancho, alto);
		// color de letra
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Monospaced", Font.PLAIN, 20));// fuente

		g2d.drawString("INVENTARIO", 100, 50);
		g2d.drawString("Fecha: "+dateFormat.format(date), 50, 100);
		g2d.drawString("---------------------------", 50, 150);
		int yPosition = 200;
		for (int i = 0; i < listaProd.size(); i++) {
			Product producto = (Product) listaProd.get(i);
			g2d.drawString(producto.getName()+"      S/."+producto.getPrice()*producto.getQuantityRef(), 50, yPosition);
			yPosition += 30;
		}
		g2d.drawString("---------------------------", 50, yPosition);
		g2d.drawString("Total:          S/."+total, 50, yPosition+30);
		g2d.drawString("Gracias por su compra!", 50, yPosition+60);
		g2d.dispose();
		// instanciamos la clase, el JFileChooser sirve para seleccionar el directorio
		JFileChooser chooser = new JFileChooser();
		// el metodo showsavediaolog es el que muestra el cuadro de dialogo
		chooser.showSaveDialog(this.form);
		chooser.setDialogTitle("Guardar Ticket");
		// capturamos el archivo
		File guardar = chooser.getSelectedFile();
		// validamos que se haya capturado una ruta
		if (guardar != null) {
			guardar = new File(guardar.toString() + ".png");
			try {
				ImageIO.write(imagen, "PNG", guardar);
				System.out.println("Imagen de ticket guardada como: " + guardar.getAbsolutePath());
			} catch (IOException e) {
				System.out.println("Error al guardar la imagen: " + e.getMessage());
			}
		}
		openFile(guardar.toString());
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
		Object press = ae.getSource();
		if (press == this.form.btnBuscarCliente) {
			searchCustomer();
		} else if (press == this.form.cbCategoria) {
			// si no se selecciono nada que no busque
			if (!this.form.cbCategoria.getSelectedItem().equals(nodeDefault))
				loadProducts();
		} else if (press == this.form.cbProveedor) {
			// si no se selecciono nada que no busque
			if (!this.form.cbProveedor.getSelectedItem().equals(nodeDefault))
				loadProducts();
		} else if (press == this.form.cbProducto) {
			// si no se selecciono nada que no busque
			loadQuantity();
		} else if (press == this.form.btnAgregarProducto) {
			addProduct();
		} else if (press == this.form.btnEliminarTodo) {
			deleteAll();
		} else if (press == this.form.btnEliminarUltimo) {
			deleteRow();
		} else if (press == this.form.btnVenta) {
			sales();
		}
	}

	public void showView() {
		form.setVisible(true);
	}

}
