package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
	public static Connection conectar() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/colegio?useSSL=false&useTimezone=true&serverTimezone=UTC";
			String usr = "root";
			String psw = "Calinrodri12";
			con = (Connection) DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error >> Driver no Instalado!!");
		} catch (SQLException ex) {
			System.out.println("Error >> de conexión con la BD");
		} catch (Exception ex) {
			System.out.println("Error >> general : " + ex.getMessage());
		}
		// &relaxAutoCommit=true
		return con;
	}

}
