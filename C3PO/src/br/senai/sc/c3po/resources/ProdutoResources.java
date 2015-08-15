package br.senai.sc.c3po.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.senai.sc.c3po.dao.ProdutoDAO;
import br.senai.sc.c3po.dominio.Produto;

@Path("/produtos")
public class ProdutoResources {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> getListaProdutos(){
		ProdutoDAO dao = new ProdutoDAO();
		return dao.listaProdutos();
	}
	
	
	@Path("{idProduto}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Produto getProduto(@PathParam("idProduto") int idProduto){
		ProdutoDAO dao = new ProdutoDAO();
		return dao.buscaPorId(idProduto);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void salvarProduto(Produto produto){
		ProdutoDAO dao = new ProdutoDAO();
		dao.inserir(produto);
	}
	
	@Path("{idProduto}")
	@DELETE
	public void deletarProduto(@PathParam("idProduto") int idProduto){
		ProdutoDAO dao = new ProdutoDAO();
		dao.deletar(dao.buscaPorId(idProduto));
	}
}
