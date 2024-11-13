package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDB;
import linkedList.LinkedList;
import models.User;

public class UserService {
	
	private static final String SQL_SELECT = "SELECT * FROM users";
	
	//traera en una lista enlazada toda la consulta de la tabla
	public LinkedList findAll(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User usuario = null;
		//intancia de la lista
		LinkedList usuarios = new LinkedList();
		try {
			//obtenemos la conexion
			conn = ConnectionDB.getConnection();
			//prepara el query
			stmt = conn.prepareStatement(SQL_SELECT);
			//ejecuta el query
			rs = stmt.executeQuery();
			//se itera y captura la data para ponerla en el obj
			while(rs.next()) {
				int idUser = rs.getInt("id_user");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int phone = rs.getInt("phone");
				String email = rs.getString("email");
				usuario = new User(idUser, username, password, firstName, lastName, phone, email);
				//se agrega al final de la lista
				usuarios.addLast(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionDB.close(conn);		
		}
		//se retorna la lista
		return usuarios;
	}
	
}
