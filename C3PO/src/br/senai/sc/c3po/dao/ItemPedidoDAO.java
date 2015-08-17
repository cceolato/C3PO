package br.senai.sc.c3po.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.c3po.dominio.ItemPedido;

public class ItemPedidoDAO extends BaseDAO{

	public List<ItemPedido> listaItensPedido(Integer id_pedido) {
		conectar();
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		try {
			ResultSet rs;
			rs = comando.executeQuery("select id_pedido, id_produto, quantidade from "
					+ "itens_pedidos where id_pedido = " + id_pedido);

			while (rs.next()) {
				ItemPedido item = new ItemPedido();
				item.setIdPedido(rs.getInt("id_pedido"));
//				item.setIdProduto(rs.getInt("id_produto"));
				item.setQuantidade(rs.getInt("quantidade"));
				item.setProduto(produtoDAO.buscaPorId(rs.getInt("id_produto")));
				lista.add(item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return lista;
	}

	public ItemPedido buscaPorId(int id_pedido, int id_produto) {
		conectar();
		ItemPedido item = new ItemPedido();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		try {
			ResultSet rs;
			rs = comando
					.executeQuery("select id_pedido, id_produto, quantidade from "
							+ "itens_pedidos where id_pedido = " + id_pedido 
							+ " and id_produto = " + id_produto);
			while (rs.next()) {
				item.setIdPedido(rs.getInt("id_pedido"));
//				item.setIdProduto(rs.getInt("id_produto"));
				item.setQuantidade(rs.getInt("quantidade"));
				item.setProduto(produtoDAO.buscaPorId(id_produto));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return item;
	}

	public boolean deletar(ItemPedido item) {
		conectar();
		try {
			comando.execute("delete from itens_pedidos where id_pedido ="
					+ item.getIdPedido() + " and id_produto = " 
					+ item.getProduto().getIdProduto());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public boolean inserir(ItemPedido item) {
		conectar();
		try {
			comando.execute("insert into itens_pedidos(id_pedido, id_produto, quantidade) values ("
					+ item.getIdPedido() + ", "
					+ item.getProduto().getIdProduto() + ", "
					+ item.getQuantidade() + ")");
			PedidoDAO dao = new PedidoDAO();
			dao.atualizarValorPedido(dao.buscaPorId(item.getIdPedido()));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}
	
	public boolean inserir(List<ItemPedido> itens){
		try{
			for (ItemPedido item : itens){
				this.inserir(item);
			}
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void atualizar(ItemPedido item) {
		// TODO Auto-generated method stub

	}

	public boolean deletar(List<ItemPedido> itens) {
		try {
			for (ItemPedido item: itens){
				this.deletar(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean alterarQuantidadeProduto(int idPedido, int idProduto, int quantidade) {
		conectar();
		try {
			comando.execute("update itens_pedidos set quantidade = " + quantidade 
					+ " where id_pedido = " + idPedido + " and id_produto = " 
					+ idProduto);
			PedidoDAO dao = new PedidoDAO();
			dao.atualizarValorPedido(dao.buscaPorId(idPedido));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public boolean insereItem(int idPedido, int idProduto, int quantidade) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ItemPedido item = new ItemPedido();
		item.setIdPedido(idPedido);
		item.setQuantidade(quantidade);
		item.setProduto(produtoDAO.buscaPorId(idProduto));
		return this.inserir(item);
	}
}
