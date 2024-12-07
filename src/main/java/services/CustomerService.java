package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionDB;
import linkedList.LinkedList;
import models.Customer;

public class CustomerService {
	
	private static final String SQL_SELECT = "SELECT * FROM customers";
	
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
	
}
