package services;

import java.sql.*;
import javax.swing.JOptionPane;
import connection.ConnectionDB;
import linkedList.LinkedList;
import models.User;

public class UserService {
	
	private static final String SQL_SELECT = "SELECT * FROM users";
	private static final String SQL_INSERT = "INSERT INTO users(username, password, first_name, last_name, phone, email) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE users u SET u.username = ?, u.password = ?, u.first_name = ?, u.last_name = ?, u.phone = ?, u.email = ? WHERE u.id_user = ?";
	private static final String SQL_DELETE = "DELETE FROM users WHERE id_user = ?";

	//SELECT * FROM users ORDER BY id_user DESC LIMIT 1;

	
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
	
	//METODO QUE EJECUTA EL SQL PARA GUARDAR UN NUEVO REGISTRO
	public void insertUser(User user) {
		try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setInt(5, user.getPhone());
			pstmt.setString(6, user.getEmail());
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
		}catch(java.sql.SQLIntegrityConstraintViolationException e1) {
			JOptionPane.showMessageDialog(null, "Los nombres de los usuarios deben ser unicos", "Advertencia",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//METODO QUE EJECUTA UN SQL PARA ACTUALIZAR UN REGISTRO DE USUARIOS
	public void updateUser(User user) {
		try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setInt(5, user.getPhone());
			pstmt.setString(6, user.getEmail());
			pstmt.setInt(7, user.getIdUser());
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//METODO PERMITE ELIMINAR EL REGISTRO DE USUARIO SELECCIONADO
	public void deleteUser(int idUser) {
	    try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE)) {
	        pstmt.setInt(1, idUser);
	        
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró un usuario con ID: " + idUser);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


}
