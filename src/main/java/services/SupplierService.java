package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionDB;
import linkedList.LinkedList;
import models.Supplier;

public class SupplierService {

	private static final String SQL_SELECT = "SELECT * FROM suppliers";
	private static final String SQL_INSERT = "INSERT INTO suppliers(name, address, phone, last_name) VALUES (?,?,?,?)";

	// traera en una lista enlazada toda la consulta de la tabla
	public LinkedList findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Supplier proveedor = null;
		// intancia de la lista
		LinkedList proveedores = new LinkedList();
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			// prepara el query
			stmt = conn.prepareStatement(SQL_SELECT);
			// ejecuta el query
			rs = stmt.executeQuery();
			// se itera y captura la data para ponerla en el obj
			while (rs.next()) {
				int idSupplier = rs.getInt("id_supplier");
				String name = rs.getString("name");
				String address = rs.getString("address");
				int phone = rs.getInt("phone");
				String lastName = rs.getString("last_name");
				proveedor = new Supplier(idSupplier, name, address, phone, lastName);
				// se agrega al final de la lista
				proveedores.addLast(proveedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		// se retorna la lista
		return proveedores;
	}
	
	//para peristir la data
	public int save(Supplier proveedor) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			//obtenemos la conexion
			conn = ConnectionDB.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			//seteamos cada valor del parametro, segun el orden de los ?
			stmt.setString(1, proveedor.getName());
			stmt.setString(2, proveedor.getAddress());
			stmt.setInt(3, proveedor.getPhone());
			stmt.setString(4, proveedor.getLastName());
			//se ejecuta y listo
			registros = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDB.close(conn);	
		}
		return registros;
	}

}
