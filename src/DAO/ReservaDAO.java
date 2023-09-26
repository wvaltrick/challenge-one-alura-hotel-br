package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DTO.ReservaDTO;

public class ReservaDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<ReservaDTO> lista = new ArrayList<>();
	
	public ReservaDTO cadastraReserva(ReservaDTO objReservaDTO) {

		String sql = "INSERT INTO reservas(dataentrada, datasaida, valor, formapagamento) VALUES (?, ?, ?, ?)";
		
		conn = new ConexaoDAO().conectaDB();
		
		try {
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
			pstm.setDate(1, objReservaDTO.getDataEntrada());
			pstm.setDate(2, objReservaDTO.getDataSaida());
			pstm.setString(3, objReservaDTO.getValorReserva());
			pstm.setString(4, objReservaDTO.getFormaPagamento());
			
			pstm.executeUpdate();
			
			
			try (ResultSet rst = pstm.getGeneratedKeys()){
				while (rst.next()) {
					objReservaDTO.setIdReserva(rst.getInt(1));
					System.out.println(objReservaDTO.getIdReserva());
				}
			}

			pstm.close();
			
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro ao gravar reserva DAO: " + erro);
		}
		
		return objReservaDTO;
	}
	
	
	public ArrayList<ReservaDTO> BuscarReserva(){
				
		try {
			String sql = "SELECT * FROM reservas";
			
			conn = new ConexaoDAO().conectaDB();

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				ReservaDTO objReservaDTO = new ReservaDTO();
				objReservaDTO.setIdReserva(rs.getInt("id"));
				objReservaDTO.setDataEntrada(rs.getDate("dataentrada"));
				objReservaDTO.setDataSaida(rs.getDate("datasaida"));
				objReservaDTO.setValorReserva(rs.getString("valor"));
				objReservaDTO.setFormaPagamento(rs.getString("formapagamento"));
				
				lista.add(objReservaDTO);				
			}
									
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar reserva DAO: " + erro);
		}
		
		return lista;
		
	}
	
		public ArrayList<ReservaDTO> buscarReservaId(String idReserva) {
				
				ReservaDTO objreservaDTO = new ReservaDTO();

				String sql = "SELECT * FROM reservas WHERE id = ?";
				
				conn = new ConexaoDAO().conectaDB();
				System.out.println(idReserva);
				try {
					
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, idReserva);
					
					pstm.execute();
					rs = pstm.executeQuery();
					
					 while(rs.next()){
						
						 objreservaDTO.setIdReserva(rs.getInt("id"));
						 objreservaDTO.setDataEntrada(rs.getDate("dataentrada"));
						 objreservaDTO.setDataSaida(rs.getDate("datasaida"));
						 objreservaDTO.setValorReserva(rs.getString("valor"));
						 objreservaDTO.setFormaPagamento(rs.getString("formapagamento"));
		
						 lista.add(objreservaDTO);
					 }
					
					
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "Erro na busca reserva: "+ erro);
				}
				
				return lista;
				
			}

		public void alterarReserva(ReservaDTO objreservaDTO) {
			String sql = "UPDATE reservas set dataentrada = ?, datasaida = ?, valor = ?, formapagamento = ? where id = ?";
			
			conn = new ConexaoDAO().conectaDB();
			
			try {
				pstm = conn.prepareStatement(sql);
				
				pstm.setDate(1, objreservaDTO.getDataEntrada());
				pstm.setDate(2, objreservaDTO.getDataSaida());
				pstm.setString(3, objreservaDTO.getValorReserva());
				pstm.setString(4, objreservaDTO.getFormaPagamento());
				pstm.setInt(5, objreservaDTO.getIdReserva());
				
				pstm.execute();
				pstm.close();
				
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "Erro ao alterar reserva DAO: " + erro);
			}
		}

		public void excluirReserva(ReservaDTO objreservaDTO) {
			String sql = "DELETE from reservas where id = ?";
		
			conn = new ConexaoDAO().conectaDB();
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, objreservaDTO.getIdReserva());
			
			pstm.execute();
			pstm.close();
			
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir reserva DAO: " + erro);
		}
	}


}
