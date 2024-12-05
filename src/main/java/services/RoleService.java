package services;

import java.sql.*;
import javax.swing.JOptionPane;
import connection.ConnectionDB;
import linkedList.LinkedList;
import models.Roles;
import models.User;

public class RoleService {
	private static final String SQL_INSERT = "INSERT INTO roles (rol, id_user) VALUES (?, ?)";
	private static final String SQL_DELETE = "DELETE FROM roles WHERE rol = ? AND id_user = ?";
	private static final String SQL_SELECT = "SELECT * FROM roles";
	
;
	//METODO PERMITE LLAMAR A SQL Y GARDAR EL NUEVO ROL DEL USUARIO
	public void insertRole(Roles role) {
		try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
		        pstmt.setString(1, role.getRole());
		        pstmt.setInt(2, role.getIdUser());
		        pstmt.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Rol agregado correctamente");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	//PERMITE ELIMINAR UNA REGLA DE USUARIO
	public void deleteRole(String rol, int idUser) {
	    try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE)) {
	        pstmt.setString(1, rol);
	        pstmt.setInt(2, idUser);
	        
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            JOptionPane.showMessageDialog(null, "Reasignacion de roles completada");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	//PERMITE LISTAR LOS ELEMENTOS DE LA BASE DE DATOS, RESPECTO A LOS ROLES ASIGNADOS
	public LinkedList findAll() {
		Roles role = null;
		// intancia de la lista
		LinkedList roles = new LinkedList();
		try (Connection conn = ConnectionDB.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(SQL_SELECT)) {
			while (rs.next()) {
				int idRol = rs.getInt("id_rol");
				String rol = rs.getString("rol");
				int idUser = rs.getInt("id_user");
				role = new Roles(idRol, rol, idUser);
				roles.addLast(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// se retorna la lista
		return roles;
	}

}
