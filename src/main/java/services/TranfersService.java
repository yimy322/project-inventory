package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import connection.ConnectionDB;
import linkedList.LinkedList;
import models.Transfers;
import pilas.Pilas;

public class TranfersService {

	private static final String SQL_UPDATE_PLUS_QUANTITY = "UPDATE products SET quantity = quantity + ? WHERE name = ? AND id_supplier = ?";
	private static final String SQL_UPDATE_LESS_QUANTITY = "UPDATE products SET quantity = quantity - ? WHERE name = ? AND id_supplier = ? AND quantity >= 0";
	private static final String SQL_SELECT_QUANTITY = "SELECT quantity FROM products WHERE name = ?";
	private static final String SQL_INSERT = "INSERT INTO Transfers (quantity, date_transfers, type_transfers, quantity_old, total, id_product, id_user) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_SELECT = "SELECT p.name, t.*, u.username, s.name as name_supplier FROM transfers t INNER JOIN products p ON t.id_product = p.id_product INNER JOIN users u ON t.id_user = u.id_user INNER JOIN suppliers s ON p.id_supplier = s.id_supplier";
	
	//METODO PERMITE SUMAR CANTIDAD A UN PRODUCTO DADO SU NOMBRE CARACTERISTICO
	public void plusQuantity(String nameProduct, int quantity, int idSupplier) {
		// METODO QUE EJECUTA UN SQL PARA ACTUALIZAR UN REGISTRO DE USUARIOS
		try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_PLUS_QUANTITY)) {
			pstmt.setInt(1, quantity);
			pstmt.setString(2, nameProduct);
			pstmt.setInt(3, idSupplier);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// METODO PERMITE RESTAR CANTIDAD A UN PRODUCTO DADO SU NOMBRE CARACTERISTICO
	public void lessQuantity(String nameProduct, int quantity, int idSupplier) {
		// METODO QUE EJECUTA UN SQL PARA ACTUALIZAR UN REGISTRO DE USUARIOS
		try (Connection conn = ConnectionDB.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_LESS_QUANTITY)) {
			pstmt.setInt(1, quantity);
			pstmt.setString(2, nameProduct);
			pstmt.setInt(3, idSupplier);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//PERMIRE OBTENER LA CANTIDAD REFERENTE A UN PRODUCTO DADO SU NOMBRE
	public int getQuantity(String nameProduct) {
		int quantity = 0;
		try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_QUANTITY); ResultSet rs = stmt.executeQuery()){
			if(rs.next()) {
				quantity = rs.getInt(nameProduct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantity;
	}
	
	//PERMITE INSERTAR O GUARDAR EN BD UN NUEVO REGISTRO O MOVIMINETOS DE PRENDAS
	public void insert(Transfers transfer) {
		try (Connection conn = ConnectionDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
			pstmt.setInt(1, transfer.getQuantity());
			pstmt.setString(2, transfer.getFecha());
			pstmt.setString(3, transfer.getTypeTransfers());
			pstmt.setInt(4, transfer.getQuantityProduct());
			pstmt.setInt(5, transfer.getTotal());
			pstmt.setInt(6, transfer.getIdProduct());
			pstmt.setInt(7, transfer.getIdUser());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//PERMITE LISTAR LOS REGISTRO EN LA BASE DE DATOS EN UNA LISTA DE ENLACE SIMPLE
	public Pilas findAll() {
		Pilas transfers = new Pilas(); 
		try (Connection conn = ConnectionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT); ResultSet rs = stmt.executeQuery()){
			while(rs.next()) {
				int idTransfers = rs.getInt("id_transfers");
				int quantity = rs.getInt("quantity");
				String dateTransfers = rs.getString("date_transfers");
				String typeTransfers = rs.getString("type_transfers");
				int idProduct = rs.getInt("id_product");
				int idUser = rs.getInt("id_user");
				String nameProduct = rs.getString("name");
				int quantityProduct = rs.getInt("quantity_old");
				String userName = rs.getString("username");
				String supplierName = rs.getString("name_supplier");
				int total = rs.getInt("total");
				Transfers transfer = new Transfers(idTransfers, quantity, idUser, idProduct, dateTransfers, typeTransfers, nameProduct, userName, quantityProduct, supplierName, total);
				transfers.push(transfer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transfers;
	}
	
}
