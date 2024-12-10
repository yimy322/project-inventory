package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionDB;
import models.Order;

public class OrderService {

	private static final String SQL_INSERT = "INSERT INTO orders(order_date, id_customer, type) VALUES (?,?,?)";

	// para peristir la data
	public int save(Order orden) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		ResultSet rs = null;
		int pkGenerado = -1;
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);//pedimos que retorne el pk
			// seteamos cada valor del parametro, segun el orden de los ?
			stmt.setString(1, orden.getOrderDate());
			stmt.setInt(2, orden.getIdCustomer());
			stmt.setInt(3, orden.getType());
			// se ejecuta y listo
			registros = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            pkGenerado = rs.getInt(1);//EL PRIMER campo es el pk generado
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		return pkGenerado;//retorna el pk del registro
	}

}
