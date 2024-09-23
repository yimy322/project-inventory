package connection;

import java.sql.*;

public class ConnectionDB {
//  Cadena de conexion con mysql
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/inventory?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//  Aca especificamos el usuario
	private static final String JDBC_USER = "root";
//  Aca la contraseña de mysql
	private static final String JDBC_PASSWORD = "root@322-ABC";
//  Metodo para obtener la conexion a al base de datos
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
//  cierra la conexion
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
