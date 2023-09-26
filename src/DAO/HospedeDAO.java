package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.HospedeDTO;
import DTO.ReservaDTO;

public class HospedeDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<HospedeDTO> lista = new ArrayList<HospedeDTO>();
	
	
	public HospedeDTO cadastraHospede(HospedeDTO objHospedeDTO) {
		
		String sql = "INSERT INTO hospedes(nome, sobrenome, datanascimento, nacionalidade, telefone, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
	
		
		conn = new ConexaoDAO().conectaDB();
		
		try {
			pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
			pstm.setString(1, objHospedeDTO.getNome());
			pstm.setString(2, objHospedeDTO.getSobrenome());
			pstm.setDate(3, objHospedeDTO.getDataNascimento());
			pstm.setString(4, objHospedeDTO.getNacionalidade());
			pstm.setString(5, objHospedeDTO.getTelefone());
			pstm.setInt(6, objHospedeDTO.getIdReserva());
						
			pstm.executeUpdate();
			
			try (ResultSet rst = pstm.getGeneratedKeys()){
				while (rst.next()) {
					objHospedeDTO.setIdHospede(rst.getInt(1));
				}
			}

			pstm.close();
			
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null,"Erro ao gravar Hospede DAO: " + erro);
		}
		return objHospedeDTO;
	}
	
	
	public ArrayList<HospedeDTO> buscarHospede() {
		
		String sql = "SELECT * FROM hospedes";
		
		conn = new ConexaoDAO().conectaDB();
		
		try {
			
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			 while(rs.next()){
				 HospedeDTO objhospedeDTO = new HospedeDTO();
				 objhospedeDTO.setIdHospede(rs.getInt("id"));
				 objhospedeDTO.setNome(rs.getString("nome"));
				 objhospedeDTO.setSobrenome(rs.getString("sobrenome"));
				 objhospedeDTO.setDataNascimento(rs.getDate("datanascimento"));
				 objhospedeDTO.setNacionalidade(rs.getString("nacionalidade"));
				 objhospedeDTO.setTelefone(rs.getString("telefone"));
				 objhospedeDTO.setIdReserva(rs.getInt("id_reserva"));
				
				 lista.add(objhospedeDTO);
			 }
			
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro na busca hospede: "+ erro);
		}
		
		return lista;
		
	}
	
	
	public ArrayList<HospedeDTO> buscarHospedeSobrenome(String sobrenomeHospede) {
			
			HospedeDTO objhospedeDTO = new HospedeDTO();

			String sql = "SELECT * FROM hospedes WHERE sobrenome = ?";
			
			conn = new ConexaoDAO().conectaDB();
			System.out.println(sobrenomeHospede);
			try {
				
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, sobrenomeHospede);
				
				pstm.execute();
				rs = pstm.executeQuery();
				
				 while(rs.next()){
					
					 objhospedeDTO.setIdHospede(rs.getInt("id"));
					 objhospedeDTO.setNome(rs.getString("nome"));
					 objhospedeDTO.setSobrenome(rs.getString("sobrenome"));
					 objhospedeDTO.setDataNascimento(rs.getDate("datanascimento"));
					 objhospedeDTO.setNacionalidade(rs.getString("nacionalidade"));
					 objhospedeDTO.setTelefone(rs.getString("telefone"));
					 objhospedeDTO.setIdReserva(rs.getInt("id_reserva"));
					
					 lista.add(objhospedeDTO);
				 }
				
				
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro na busca hospede: "+ erro);
			}
			
			return lista;
		}
	

		public void alterarHospede(HospedeDTO objhospedeDTO) {
			String sql = "UPDATE hospedes set nome = ?, sobrenome = ?, datanascimento = ?, nacionalidade = ?, telefone = ? where id = ?";
			
			conn = new ConexaoDAO().conectaDB();
			
			try {
				pstm = conn.prepareStatement(sql);
				
				pstm.setString(1, objhospedeDTO.getNome());
				pstm.setString(2, objhospedeDTO.getSobrenome());
				pstm.setDate(3, objhospedeDTO.getDataNascimento());
				pstm.setString(4, objhospedeDTO.getNacionalidade());
				pstm.setString(5, objhospedeDTO.getTelefone());
				pstm.setInt(6, objhospedeDTO.getIdHospede());
				
				pstm.execute();
				pstm.close();
				
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "Erro ao alterar hospede DAO: " + erro);
			}
		}
	
		public void excluirHospede(HospedeDTO objhospedeDTO) {
				String sql = "DELETE from hospedes where id_reserva = ?";
			
				conn = new ConexaoDAO().conectaDB();
			
			try {
				pstm = conn.prepareStatement(sql);
				
				pstm.setInt(1, objhospedeDTO.getIdReserva());
								
				pstm.execute();
				pstm.close();
				
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir hospede DAO: " + erro);
			}
		}
		
}
