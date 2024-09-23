package main;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionDB;

public class Main {

	public static void main(String[] args) {
		try {
			Connection con = ConnectionDB.getConnection();
			System.out.println("Prueba de conexion exitosa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
