package br.senai.sc.c3po.dominio;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemPedido {

	private Integer idPedido;
//	private Integer idProduto;
	private Integer quantidade;
	private Produto produto;
	
	public ItemPedido() {
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

/*	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}*/

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
