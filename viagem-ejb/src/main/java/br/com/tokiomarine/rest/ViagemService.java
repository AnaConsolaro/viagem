package br.com.tokiomarine.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import br.com.tokiomarine.controller.ViagemFacade;
import br.com.tokiomarine.exception.ViagemException;
import br.com.tokiomarine.model.TipoGasto;
import br.com.tokiomarine.model.Viagem;

@Path(value = "/viagem")
public class ViagemService {
	@Inject
	private ViagemFacade facade;

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public List<Viagem> listaViagem() {
		return facade.listarTodos();
	}

	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Viagem criarViagem(Viagem novaViagem, @QueryParam("gerarpasseios") String gerarPasseios, @QueryParam("numeropasseios") Integer numeroPasseios) {
		return facade.criar(novaViagem, Boolean.valueOf(gerarPasseios), numeroPasseios);
	}

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	@Path(value = "/{id}")
	public Viagem buscarViagemPorId(@PathParam("id") Long id) {
		return facade.buscarPorId(id);
	}

	@POST
	@Produces(value = MediaType.APPLICATION_JSON)
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Path(value = "/{id}")
	public Viagem atualizarViagem(@PathParam("id") Long id, Viagem viagemAtualizada) throws ViagemException {
		return facade.atualizar(id, viagemAtualizada);
	}

	@DELETE
	@Path(value = "/{id}")
	public void deletarViagem(@PathParam("id") Long id) throws ViagemException {
		facade.deletarPorId(id);
	}

	@GET
	@Path(value = "/{id}/gastos")
	@Produces(value = MediaType.APPLICATION_JSON)
	public GastosVO listarGastosViagem(@PathParam("id") Long id, @QueryParam("tipo") String tipo) throws ViagemException {

		GastosVO gastosVO = null;
		Viagem viagemPorId = facade.buscarPorId(id);

		if (StringUtils.isEmpty(tipo) ) {
			gastosVO = GastosVO.criarAPartirDaViagem(viagemPorId);
		} else {

			try {
				gastosVO = GastosVO.criarAPartirDaViagem(viagemPorId, TipoGasto.valueOf(tipo.toUpperCase()));
			} catch (IllegalArgumentException e) {
				throw new ViagemException("Tipo de Gasto Inexistente");
			}
		}
		return gastosVO;
	}

}
