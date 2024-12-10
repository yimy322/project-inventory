package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionDB;
import models.OrderDetail;

public class OrderDetailService {

	private static final String SQL_INSERT = "INSERT INTO order_details(unit_price, quantity, total, id_order, id_product, id_user) VALUES (?,?,?,?,?,?)";

	// para peristir la data
	public int save(OrderDetail ordenDetalle) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int registros = 0;
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			// seteamos cada valor del parametro, segun el orden de los ?
			stmt.setDouble(1, ordenDetalle.getUnitPrice());
			stmt.setInt(2, ordenDetalle.getQuantity());
			stmt.setDouble(3, ordenDetalle.getTotal());
			stmt.setInt(4, ordenDetalle.getIdOrder());
			stmt.setInt(5, ordenDetalle.getIdProduct());
			stmt.setInt(6, ordenDetalle.getIdUser());
			// se ejecuta y listo
			registros = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		return registros;
	}

}
