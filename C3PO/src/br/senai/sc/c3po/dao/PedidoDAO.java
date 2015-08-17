package br.senai.sc.c3po.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.c3po.dominio.ItemPedido;
import br.senai.sc.c3po.dominio.Pedido;

public class PedidoDAO extends BaseDAO {

	public List<Pedido> listaPedidos(Long cpfCliente) {
		conectar();
		ClienteDAO clienteDAO = new ClienteDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ItemPedidoDAO itemDAO = new ItemPedidoDAO();
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			ResultSet rs;
			rs = comando.executeQuery("select id_pedido, cpf_funcionario, cpf_cliente, "
					+ "data_pedido, vl_total_pedido, status from pedidos where cpf_cliente = " + cpfCliente);

			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setIdPedido(rs.getInt("id_pedido"));
//				pedido.setCpfCliente(rs.getLong("cpf_cliente"));
//				pedido.setCpfFuncionario(rs.getLong("cpf_funcionario"));
				pedido.setDataPedido(rs.getDate("data_pedido"));
				pedido.setVlTotalPedido(rs.getDouble("vl_total_pedido"));
				pedido.setStatus(rs.getString("status"));
				pedido.setCliente(clienteDAO.buscaPorId(rs.getLong("cpf_cliente")));
				pedido.setFuncionario(funcionarioDAO.buscaPorId(rs.getLong("cpf_funcionario")));
				pedido.setProdutos(itemDAO.listaItensPedido(pedido.getIdPedido()));
				lista.add(pedido);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return lista;
	}

	public Pedido buscaPorId(int id) {
		conectar();
		Pedido pedido = new Pedido();
		ClienteDAO clienteDAO = new ClienteDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ItemPedidoDAO itemDAO = new ItemPedidoDAO();
		try {
			ResultSet rs;
			rs = comando
					.executeQuery("select id_pedido, cpf_cliente, cpf_funcionario, data_pedido, "
							+ "vl_total_pedido, status from pedidos where id_pedido = "	+ id);
			while (rs.next()) {
				pedido.setIdPedido(rs.getInt("id_pedido"));
//				pedido.setCpfCliente(rs.getLong("cpf_cliente"));
//				pedido.setCpfFuncionario(rs.getLong("cpf_funcionario"));
				pedido.setDataPedido(rs.getDate("data_pedido"));
				pedido.setVlTotalPedido(rs.getDouble("vl_total_pedido"));
				pedido.setStatus(rs.getString("status"));
				pedido.setCliente(clienteDAO.buscaPorId(rs.getLong("cpf_cliente")));
				pedido.setFuncionario(funcionarioDAO.buscaPorId(rs.getLong("cpf_funcionario")));
				pedido.setProdutos(itemDAO.listaItensPedido(pedido.getIdPedido()));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return pedido;
	}

	public boolean deletar(Pedido pedido) {
		conectar();
		ItemPedidoDAO itemDAO = new ItemPedidoDAO();
		try {
			itemDAO.deletar(pedido.getProdutos());
			comando.execute("delete from pedidos where id_pedido = "
					+ pedido.getIdPedido());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fechar();
		}
		return true;
	}

	public Integer inserir(Pedido pedido) {
		conectar();
		ItemPedidoDAO itemDAO = new ItemPedidoDAO();
		int lastKey = 0;
		try {
			comando.execute("insert into pedidos (cpf_cliente, cpf_funcionario, "
					+ "data_pedido, vl_total_pedido, status) values ("
					+ pedido.getCliente().getCpf()	+ ", "
					+ pedido.getFuncionario().getCpf() + ", "
					+ pedido.getDataPedido() + ", '"
					+ pedido.getVlTotalPedido() +  ", '"
					+ pedido.getStatus() +"')", Statement.RETURN_GENERATED_KEYS);
			ResultSet keys = comando.getGeneratedKeys();    
			while (keys.next()) {    
			     lastKey = keys.getInt(1);    
			}   
			pedido.setIdPedido(lastKey);
			itemDAO.inserir(pedido.getProdutos());
			this.atualizarValorPedido(pedido);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return lastKey;
	}

	public void atualizarValorPedido(Pedido pedido) {
		Double valor = 0.0;
		for (ItemPedido item : pedido.getProdutos()){
			valor += item.getProduto().getVlProduto() * item.getQuantidade();
		}
		conectar();
		try {
			comando.execute("update pedidos set vl_total_pedido = " + valor);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
}
