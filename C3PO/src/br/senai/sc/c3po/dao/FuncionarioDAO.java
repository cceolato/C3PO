package br.senai.sc.c3po.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.c3po.dominio.Funcionario;

public class FuncionarioDAO extends BaseDAO {

	public List<Funcionario> listaFuncionarios() {
		conectar();
		List<Funcionario> lista = new ArrayList<Funcionario>();
		try {
			ResultSet rs;
			rs = comando.executeQuery("select cpf, nome_funcionario from funcionarios");

			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCpf(rs.getLong("cpf"));
				funcionario.setNomeFuncionario(rs.getString("nome_funcionario"));
				lista.add(funcionario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return lista;
	}

	public Funcionario buscaPorId(Long id) {
		conectar();
		Funcionario funcionario = new Funcionario();
		try {
			ResultSet rs;
			rs = comando
					.executeQuery("select cpf, nome_funcionario from funcionarios where cpf = "
							+ id);
			while (rs.next()) {
				funcionario.setCpf(rs.getLong("cpf"));
				funcionario.setNomeFuncionario(rs.getString("nome_funcionario"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return funcionario;
	}

	public boolean deletar(Funcionario funcionario) {
		conectar();
		try {
			comando.execute("delete from funcionarios where cpf="
					+ funcionario.getCpf());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public boolean inserir(Funcionario funcionario) {
		conectar();
		try {
			comando.execute("insert into funcionarios (cpf, nome_funcionario) values ('"
					+ funcionario.getCpf()
					+ ", '"
					+ funcionario.getNomeFuncionario() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public void atualizar(Funcionario cliente) {
		// TODO Auto-generated method stub

	}

}
