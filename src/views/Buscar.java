package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.HospedeDAO;
import DAO.ReservaDAO;
import DTO.HospedeDTO;
import DTO.ReservaDTO;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;


	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		
			
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Número da Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pagamento");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		listarReserva();
	
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Número do Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Número da Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		listarHospede();
				
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (txtBuscar.getText().equals("")) {
					listarHospede();
					listarReserva();
			
				} else {
					listarReservaId();
					listarHospedeSobrenome();
				}
			}
		});
		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int reservasSelecionadas = tbReservas.getSelectedRow();
				int hoespedesSelecionados = tbHospedes.getSelectedRow();

					if (reservasSelecionadas >= 0) {
						try {
							EditarReserva();
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						} finally {
							listarReserva();
						}
					} else if (hoespedesSelecionados >= 0) {
						try {
							EditarHospede();
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						} finally {
							listarHospede();
						}
					}
				}
			});
		
		
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				ExcluirReserva();
				listarHospede();
				listarReserva();
				
			}
			});
		
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	
	}
		private void listarReserva() {
			try {
				ReservaDAO objreservaDAO = new ReservaDAO();
				
				modelo.setNumRows(0);
	
				ArrayList<ReservaDTO> lista = objreservaDAO.BuscarReserva();
				
				for (int num = 0 ; num < lista.size(); num++) {
					modelo.addRow(new Object[] {
							lista.get(num).getIdReserva(),
							lista.get(num).getDataEntrada(),
							lista.get(num).getDataSaida(),
							lista.get(num).getValorReserva(),
							lista.get(num).getFormaPagamento()
					});
				}
				
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro no listar Valores " + erro);
			}
		}
		
		private void listarHospede() {
			try {
				HospedeDAO objhospedeDAO = new HospedeDAO();
				
				modeloHospedes.setNumRows(0);
	
				ArrayList<HospedeDTO> lista = objhospedeDAO.buscarHospede();
				
				for (int num = 0 ; num < lista.size(); num++) {
					modeloHospedes.addRow(new Object[] {
							lista.get(num).getIdHospede(),
							lista.get(num).getNome(),
							lista.get(num).getSobrenome(),
							lista.get(num).getDataNascimento(),
							lista.get(num).getNacionalidade(),
							lista.get(num).getTelefone(),
							lista.get(num).getIdReserva()							
					});
				}
				
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro no listar Valores " + erro);
			}
		}
		
		private void listarHospedeSobrenome() {
			try {
				HospedeDAO objhospedeDAO = new HospedeDAO();
				
				modeloHospedes.setNumRows(0);
	
				ArrayList<HospedeDTO> lista = objhospedeDAO.buscarHospedeSobrenome(txtBuscar.getText());
				
				for (int num = 0 ; num < lista.size(); num++) {
					modeloHospedes.addRow(new Object[] {
							lista.get(num).getIdHospede(),
							lista.get(num).getNome(),
							lista.get(num).getSobrenome(),
							lista.get(num).getDataNascimento(),
							lista.get(num).getNacionalidade(),
							lista.get(num).getTelefone(),
							lista.get(num).getIdReserva()							
					});
				}
				
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro no listar Valores " + erro);
			}
		}
		
		private void listarReservaId() {
			try {
				ReservaDAO objreservaDAO = new ReservaDAO();
				
				modelo.setNumRows(0);
				String texto = txtBuscar.getText();
				
				ArrayList<ReservaDTO> lista = objreservaDAO.buscarReservaId(texto);
				
				for (int num = 0 ; num < lista.size(); num++) {
					modelo.addRow(new Object[] {
							lista.get(num).getIdReserva(),
							lista.get(num).getDataEntrada(),
							lista.get(num).getDataSaida(),
							lista.get(num).getValorReserva(),
							lista.get(num).getFormaPagamento(),
					});
				}
				
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro no listar Valores " + erro);
			}
		}
			
			private void EditarReserva() { 
				int setar = tbReservas.getSelectedRow();
				
				int idReserva = Integer.parseInt(tbReservas.getModel().getValueAt(setar, 0).toString());
				String dataEntrada = (tbReservas.getModel().getValueAt(setar, 1).toString());
				String dataSaida = (tbReservas.getModel().getValueAt(setar, 2).toString());
				String valor = (tbReservas.getModel().getValueAt(setar, 3).toString());
				String formaPagamento = (tbReservas.getModel().getValueAt(setar, 4).toString());
				
				ReservaDTO objreservaDTO = new ReservaDTO();
												
				objreservaDTO.setIdReserva(idReserva);
				objreservaDTO.setDataEntrada(java.sql.Date.valueOf(dataEntrada));
				objreservaDTO.setDataSaida(java.sql.Date.valueOf(dataSaida));
				objreservaDTO.setValorReserva(valor);
				objreservaDTO.setFormaPagamento(formaPagamento);
				
				ReservaDAO objreservaDAO = new ReservaDAO();
				objreservaDAO.alterarReserva(objreservaDTO);
				
				JOptionPane.showMessageDialog(null, "Reserva alterada com sucesso");

			}
		
			
			private void EditarHospede() {
				int setar = tbHospedes.getSelectedRow();
				
				int id = Integer.parseInt(tbHospedes.getModel().getValueAt(setar, 0).toString());
				String nome = (tbHospedes.getModel().getValueAt(setar, 1).toString());
				String sobrenome = (tbHospedes.getModel().getValueAt(setar, 2).toString());
				String datanascimento = (tbHospedes.getModel().getValueAt(setar, 3).toString());
				String nacionalidade = (tbHospedes.getModel().getValueAt(setar, 4).toString());
				String telefone = (tbHospedes.getModel().getValueAt(setar, 5).toString());
				int idReserva = Integer.parseInt(tbHospedes.getModel().getValueAt(setar, 6).toString());
				
				HospedeDTO objhospedeDTO = new HospedeDTO();
				
				objhospedeDTO.setIdHospede(id);
				objhospedeDTO.setNome(nome);
				objhospedeDTO.setSobrenome(sobrenome);
				objhospedeDTO.setDataNascimento(java.sql.Date.valueOf(datanascimento));
				objhospedeDTO.setNacionalidade(nacionalidade);
				objhospedeDTO.setTelefone(telefone);
				objhospedeDTO.setIdReserva(idReserva);				

				HospedeDAO objhospedeDAO = new HospedeDAO();
				
				objhospedeDAO.alterarHospede(objhospedeDTO);
				
				JOptionPane.showMessageDialog(null, "Hóspede alterado com sucesso");
			}
			
			
			private void ExcluirReserva() {
				int setar = tbReservas.getSelectedRow();
				
				int idReserva = Integer.parseInt(tbReservas.getModel().getValueAt(setar, 0).toString());
				
				ReservaDTO objreservaDTO = new ReservaDTO();
				objreservaDTO.setIdReserva(idReserva);
				
				HospedeDTO objhospedeDTO = new HospedeDTO();
				objhospedeDTO.setIdReserva(idReserva);
				
				HospedeDAO objhospedeDAO = new HospedeDAO();
				objhospedeDAO.excluirHospede(objhospedeDTO);
				
				ReservaDAO objreservaDAO = new ReservaDAO();
				objreservaDAO.excluirReserva(objreservaDTO);
				
				JOptionPane.showMessageDialog(null, "Reserva e Hóspede Excluídos.");
			}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
