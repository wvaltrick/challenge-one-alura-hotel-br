package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DTO.UsuarioDTO;

public class UsuarioDAO {
	
	Connection conn;
		
	public ResultSet autenticaUser(UsuarioDTO objUsuarioDTO) {
		
		conn = new ConexaoDAO().conectaDB();
		
		try {
			
			String sql = "SELECT * FROM usuario WHERE usuario = ? and senha = ?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, objUsuarioDTO.getNomeUser());
			pstm.setString(2, objUsuarioDTO.getSenhaUser());
			
			ResultSet rs = pstm.executeQuery();
			return rs;
			
			
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
			return null;
			
		}
		
	}

}
