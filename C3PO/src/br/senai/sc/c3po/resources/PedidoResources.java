package br.senai.sc.c3po.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.senai.sc.c3po.dao.ItemPedidoDAO;
import br.senai.sc.c3po.dao.PedidoDAO;
import br.senai.sc.c3po.dominio.Pedido;

@Path("/pedidos")
public class PedidoResources {

	@Path("{cpfCliente}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> getListaPediso(@PathParam("cpfCliente") Long cpfCliente){
		PedidoDAO dao = new PedidoDAO();
		return dao.listaPedidos(cpfCliente);
	}
	
	@Path("{cpfCliente}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Integer salvaPedido(Pedido pedido, @PathParam("cpfCliente") Long cpfCliente){
		PedidoDAO dao = new PedidoDAO();
		return dao.inserir(pedido);
	}
	
	@Path("{cpfCliente}/{idPedido}/{idProduto}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String alteraQuantidadeItem(Integer quantidade, @PathParam("cpfCliente") Long cpfCliente,
			@PathParam("idPedido") int idPedido, @PathParam("idProduto") int idProduto){
		ItemPedidoDAO dao = new ItemPedidoDAO();
		if (dao.alterarQuantidadeProduto(idPedido, idProduto, quantidade)){
			return "Sucesso!";
		}else{
			return "Não foi possível alterar!";
		}
	}
	
	@Path("{cpfCliente}/{idPedido}/{idProduto}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insereItem(Integer quantidade, @PathParam("cpfCliente") Long cpfCliente,
			@PathParam("idPedido") int idPedido, @PathParam("idProduto") int idProduto){
		ItemPedidoDAO dao = new ItemPedidoDAO();
		if (dao.insereItem(idPedido, idProduto, quantidade)){
			return "Sucesso!";
		}else{
			return "Não foi possível alterar!";
		}
	}
	
	@Path("{cpfCliente}/{idPedido}/{idProduto}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String apagaItem(Integer quantidade, @PathParam("cpfCliente") Long cpfCliente,
			@PathParam("idPedido") int idPedido, @PathParam("idProduto") int idProduto){
		ItemPedidoDAO dao = new ItemPedidoDAO();
		if (dao.apagaItem(idPedido, idProduto, quantidade)){
			return "Sucesso!";
		}else{
			return "Não foi possível alterar!";
		}
	}
	
	@Path("{cpfCliente}/{idPedido}/finalizar}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public String finalizaPedido (@PathParam("cpfCliente") Long cpfCliente,	@PathParam("idPedido") int idPedido){
		PedidoDAO dao = new PedidoDAO();
		if(dao.finalizarPedido(idPedido)){
			return "Sucesso";
		}else{
			return "Não foi possível finalizar pedido";
		}
	}
}
