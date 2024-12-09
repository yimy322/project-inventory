package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionDB;
import linkedList.LinkedList;
import models.Customer;
import models.Supplier;

public class CustomerService {

	private static final String SQL_SELECT = "SELECT * FROM customers";
	private static final String SQL_INSERT = "INSERT INTO customers(first_name, last_name, phone, email, dni) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE customers SET first_name = ?,last_name = ?,phone = ?,email = ?, dni = ? WHERE id_customer= ?";

	public LinkedList findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer cliente = null;
		// intancia de la lista
		LinkedList clientes = new LinkedList();
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			// prepara el query
			stmt = conn.prepareStatement(SQL_SELECT);
			// ejecuta el query
			rs = stmt.executeQuery();
			// se itera y captura la data para ponerla en el obj
			while (rs.next()) {
				int idCustomer = rs.getInt("id_customer");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int phone = rs.getInt("phone");
				String email = rs.getString("email");
				int dni = rs.getInt("dni");
				cliente = new Customer(idCustomer, firstName, lastName, phone, email, dni);
				// se agrega al final de la lista
				clientes.addLast(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		// se retorna la lista
		return clientes;
	}

	// para peristir la data
	public int save(Customer cliente) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			// seteamos cada valor del parametro, segun el orden de los ?
			stmt.setString(1, cliente.getFirstName());
			stmt.setString(2, cliente.getLastName());
			stmt.setInt(3, cliente.getPhone());
			stmt.setString(4, cliente.getEmail());
			stmt.setInt(5, cliente.getDni());
			// se ejecuta y listo
			registros = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		return registros;
	}

	// para actualizar
	public int update(Customer cliente) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, cliente.getFirstName());
			stmt.setString(2, cliente.getLastName());
			stmt.setInt(3, cliente.getPhone());
			stmt.setString(4, cliente.getEmail());
			stmt.setInt(5, cliente.getDni());
			stmt.setInt(6, cliente.getIdCustomer());
			registros = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		return registros;
	}
}
