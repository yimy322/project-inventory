package connection;

import java.sql.*;

public class ConnectionDB {
//  cadena de conexion con mysql, los parametros puestos son para evitar ciertos errores que pueden aparecer
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/inventory?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//  especificamos el usuario
	private static final String JDBC_USER = "root";
//  aca la contrasena de mysql
	private static final String JDBC_PASSWORD = "";
//  obtenemos la conexion de la base de datos
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
//  cerramos la conexion
	public static void close(Connection conn){
		if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
	}
}
