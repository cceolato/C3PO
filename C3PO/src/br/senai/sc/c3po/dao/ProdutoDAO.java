package br.senai.sc.c3po.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.c3po.dominio.Produto;

public class ProdutoDAO extends BaseDAO {

	public List<Produto> listaProdutos() {
		conectar();
		List<Produto> lista = new ArrayList<Produto>();
		try {
			ResultSet rs;
			rs = comando
					.executeQuery("select id_produto, descricao, vl_produto from produtos");

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setVlProduto(rs.getDouble("vl_produto"));
				lista.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return lista;
	}

	public Produto buscaPorId(int id) {
		conectar();
		Produto produto = new Produto();
		try {
			ResultSet rs;
			rs = comando
					.executeQuery("select id_produto, descricao, vl_produto from produtos where id_produto = "
							+ id);
			while (rs.next()) {
				produto.setIdProduto(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setVlProduto(rs.getDouble("vl_produto"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return produto;
	}

	public boolean deletar(Produto produto) {
		conectar();
		try {
			comando.execute("delete from produtos where id_produto =" + produto.getIdProduto());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public boolean inserir(Produto produto) {
		conectar();
		try {
			comando.execute("insert into produtos (id_produto, descricao, vl_produto) values ('"
					+ produto.getIdProduto() + ", '"
					+ produto.getDescricao() + "' ,'"
					+ produto.getVlProduto() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public void atualizar(Produto produto) {
		// TODO Auto-generated method stub

	}

}
