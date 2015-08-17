package br.senai.sc.c3po.dominio;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Funcionario {

	private Long cpf;
	private String nomeFuncionario;

	public Funcionario() {
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	
	
}
