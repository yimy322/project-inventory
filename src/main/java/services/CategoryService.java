package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionDB;
import linkedList.LinkedList;
import models.Category;

public class CategoryService {

	private static final String SQL_SELECT = "SELECT * FROM categories";

	// traera en una lista enlazada toda la consulta de la tabla
	public LinkedList findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Category categoria = null;
		// intancia de la lista
		LinkedList categorias = new LinkedList();
		try {
			// obtenemos la conexion
			conn = ConnectionDB.getConnection();
			// prepara el query
			stmt = conn.prepareStatement(SQL_SELECT);
			// ejecuta el query
			rs = stmt.executeQuery();
			// se itera y captura la data para ponerla en el obj
			while (rs.next()) {
				int idCategoria = rs.getInt("id_category");
				String name = rs.getString("name");
				String description = rs.getString("description");
				categoria = new Category(idCategoria, name, description);
				// se agrega al final de la lista
				categorias.addLast(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionDB.close(conn);
		}
		// se retorna la lista
		return categorias;
	}

}
