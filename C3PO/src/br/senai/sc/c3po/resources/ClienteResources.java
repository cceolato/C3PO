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

import br.senai.sc.c3po.dao.ClienteDAO;
import br.senai.sc.c3po.dominio.Cliente;

@Path("/clientes")
public class ClienteResources {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getListaClientes(){
		ClienteDAO dao = new ClienteDAO();
		return dao.listaClientes();
	}
	
	@Path("{idCliente}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente getCliente(@PathParam("idCliente") Long idCliente){
		ClienteDAO dao = new ClienteDAO();
		return dao.buscaPorId(idCliente);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void salvarCliente(Cliente cliente){
		ClienteDAO dao = new ClienteDAO();
		dao.inserir(cliente);
	}
	
	@Path("{idCLiente}")
	@DELETE
	public void deletarCliente(@PathParam("idCliente") Long idCliente){
		ClienteDAO dao = new ClienteDAO();
		dao.deletar(dao.buscaPorId(idCliente));
	}
	
}
