package br.senai.sc.c3po.dominio;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produto {
	
	private Integer idProduto;
	private String descricao;
	private Double vlProduto;

	public Produto() {
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getVlProduto() {
		return vlProduto;
	}

	public void setVlProduto(Double vlProduto) {
		this.vlProduto = vlProduto;
	}

}
