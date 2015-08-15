package br.senai.sc.c3po.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.c3po.dominio.Cliente;

public class ClienteDAO extends BaseDAO {

	public List<Cliente> listaClientes() {
		conectar();
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			ResultSet rs;
			rs = comando
					.executeQuery("select cpf, nome_cliente, endereco, telefone from clientes");

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCpf(rs.getLong("cpf"));
				cliente.setNomeCliente(rs.getString("nome_cliente"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));
				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return lista;
	}

	public Cliente buscaPorId(Long id) {
		conectar();
		Cliente cliente = new Cliente();
		try {
			ResultSet rs;
			rs = comando
					.executeQuery("select cpf, nome_cliente, endereco, telefone from clientes where cpf = "
							+ id);
			while (rs.next()) {
				cliente.setCpf(rs.getLong("cpf"));
				cliente.setNomeCliente(rs.getString("nome_cliente"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return cliente;
	}

	public boolean deletar(Cliente cliente) {
		conectar();
		try {
			comando.execute("delete from clientes where cpf=" + cliente.getCpf());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public boolean inserir(Cliente cliente) {
		conectar();
		try {
			comando.execute("insert into clientes (cpf, nome_cliente, endereco, telefone) values ('"
					+ cliente.getCpf()	+ "', '"
					+ cliente.getNomeCliente()	+ "' ,'"
					+ cliente.getEndereco()	+ "' ,'"
					+ cliente.getTelefone() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public void atualizar(Cliente cliente) {
		// TODO Auto-generated method stub

	}

}
