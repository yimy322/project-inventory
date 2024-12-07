package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import connection.ConnectionDB;
import linkedList.LinkedList;
import models.Product;

public class ProductService {
	private static final String SQL_SELECT = "SELECT p.*, c.name as category, s.name as s_name, s.last_name as s_last_name from products p join categories c on p.id_category = c.id_category join suppliers s on p.id_supplier = s.id_supplier";
	private static final String SQL_INSERT = "INSERT INTO products(name, description, price, quantity, id_category, id_supplier) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE products SET name = ?,description = ?,price = ?,quantity = ? WHERE id_product= ?";

	// traera en una lista enlazada toda la consulta de la tabla
	public LinkedList findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Product producto = null;
		// intancia de la lista
		LinkedList productos = new LinkedList();
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			// prepara el query
			stmt = conn.prepareStatement(SQL_SELECT);
			// ejecuta el query
			rs = stmt.executeQuery();
			// se itera y captura la data para ponerla en el obj
			while (rs.next()) {
				int idProduct = rs.getInt("id_product");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");
				int idCategory = rs.getInt("id_category");
				int idSupplier = rs.getInt("id_supplier");
				String category = rs.getString("category");
				String sName = rs.getString("s_name");
				String sLastName = rs.getString("s_last_name");
				producto = new Product(idProduct, name, description, price, quantity, idCategory, idSupplier, category, sName, sLastName);
				// se agrega al final de la lista
				productos.addLast(producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		// se retorna la lista
		return productos;
	}

	public int save(Product producto){
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			// seteamos cada valor del parametro, segun el orden de los ?
			stmt.setString(1, producto.getName());
			stmt.setString(2, producto.getDescription());
			stmt.setDouble(3, producto.getPrice());
			stmt.setInt(4, producto.getQuantity());
			stmt.setInt(5, producto.getIdCategory());
			stmt.setInt(6, producto.getIdSupplier());
			// se ejecuta y listo
			registros = stmt.executeUpdate();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		return registros;
	}

	// para actualizar
	public int update(Product producto) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, producto.getName());
			stmt.setString(2, producto.getDescription());
			stmt.setDouble(3, producto.getPrice());
			stmt.setInt(4, producto.getQuantity());
			stmt.setInt(5, producto.getIdProduct());
			registros = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		return registros;
	}

}
