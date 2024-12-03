package models;

public class Roles {
	private int idRole;
	private String role;
	private  int idUser;
	public Roles(int idRole, String role, int idUser) {
		this.idRole = idRole;
		this.role = role;
		this.idUser = idUser;
	}
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
}
