package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConexaoDAO {
	
	
	public Connection conectaDB() {
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/hotel_alura?useTimezone=true&serverTimezone=UTC&user=root&password=root";
			conn = DriverManager.getConnection(url);
			
			System.out.println("conectado com sucesso");
						
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro na ConexãoDAO " + erro.getMessage());
		}
		
		return conn;
	}

}
