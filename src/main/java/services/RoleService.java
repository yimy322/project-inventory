package services;

import java.sql.*;
import javax.swing.JOptionPane;
import connection.ConnectionDB;
import models.Roles;

public class RoleService {
	private static final String SQL_INSERT = "INSERT INTO roles (rol, id_user) VALUES (?, ?)";
	private static final String SQL_DELETE = "DELETE FROM roles WHERE rol = ? AND id_user = ?";
;
	//METODO PERMITE LLAMAR A SQL Y GARDAR EL NUEVO ROL DEL USUARIO
	public void insertRole(Roles role) {
		try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
		        pstmt.setString(1, role.getRole());
		        pstmt.setInt(2, role.getIdRole());
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
	            JOptionPane.showMessageDialog(null, "Rol eliminado correctamente");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró el rol '" + rol + "' para el usuario con ID: " + idUser);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
}
