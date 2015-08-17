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

import br.senai.sc.c3po.dao.FuncionarioDAO;
import br.senai.sc.c3po.dominio.Funcionario;

@Path("/funcionarios")
public class FuncionarioResources {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> getListaFuncionarios(){
		FuncionarioDAO dao = new FuncionarioDAO();
		return dao.listaFuncionarios();
	}
	
	
	@Path("{idFuncionario}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Funcionario getFuncionario(@PathParam("idFuncionario") Long idFuncionario){
		FuncionarioDAO dao = new FuncionarioDAO();
		return dao.buscaPorId(idFuncionario);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void salvarFuncionario(Funcionario funcionario){
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.inserir(funcionario);
	}
	
	@Path("{idFuncionario}")
	@DELETE
	public void deletarFuncionario(@PathParam("idFuncionario") Long idFuncionario){
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.deletar(dao.buscaPorId(idFuncionario));
	}
}
