package br.senai.sc.c3po.dominio;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pedido {

	private Integer idPedido;
//	private Long cpfCliente;
//	private Long cpfFuncionario;
	private Date dataPedido;
	private Double vlTotalPedido;
	private String status;

	private Cliente cliente;
	private Funcionario funcionario;
    private List<ItemPedido> itensPedido;
	
	public Pedido() {
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

/*	public Long getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(Long cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Long getCpfFuncionario() {
		return cpfFuncionario;
	}

	public void setCpfFuncionario(Long cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
	}*/

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Double getVlTotalPedido() {
		return vlTotalPedido;
	}

	public void setVlTotalPedido(Double vlTotalPedido) {
		this.vlTotalPedido = vlTotalPedido;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String string) {
		this.status = string;
	}
	
}
