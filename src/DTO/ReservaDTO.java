package DTO;

import java.sql.Date;

public class ReservaDTO {

	private Integer idReserva;
	private Date dataEntrada;
	private Date dataSaida;
	private String valorReserva;
	private String formaPagamento;
	
	public Integer getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public String getValorReserva() {
		return valorReserva;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public void setValorReserva(String valorReserva) {
		this.valorReserva = valorReserva;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
}
