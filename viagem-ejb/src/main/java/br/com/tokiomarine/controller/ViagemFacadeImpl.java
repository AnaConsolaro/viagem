package br.com.tokiomarine.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.tokiomarine.exception.ViagemException;
import br.com.tokiomarine.model.Atracao;
import br.com.tokiomarine.model.Viagem;

@Stateless
public class ViagemFacadeImpl implements ViagemFacade {
	@PersistenceContext(name = "viagempu")
	private EntityManager entityManager;

	@Override
	public List<Viagem> listarTodos() {
		return entityManager.createQuery("select v from Viagem v", Viagem.class).getResultList();
	}

	@Override
	public Viagem buscarPorId(long id) {
		return entityManager.find(Viagem.class, id);
	}

	@Override
	public Viagem criar(Viagem novaViagem, boolean gerarPasseios, Integer numeroPasseios) {
		if (gerarPasseios) {
			for (Atracao atracao : listarAtracoes(novaViagem.getDestino(), numeroPasseios)) {
				novaViagem.adicionarAtracao(atracao);
			}
		}

		entityManager.persist(novaViagem);
		return novaViagem;
	}

	@Override
	public Viagem atualizar(Long id, Viagem viagemAtual) throws ViagemException {
		if (buscarPorId(id) == null) {
			throw new ViagemException("Id Inexistente");
		}
		return entityManager.merge(viagemAtual);
	}

	@Override
	public Viagem deletar(Viagem viagemAtual) {
		entityManager.remove(viagemAtual);
		return viagemAtual;
	}

	@Override
	public void deletarPorId(Long id) throws ViagemException {
		Viagem viagemAtual = buscarPorId(id);
		if (viagemAtual == null) {
			throw new ViagemException("Id Inexistente");
		}
		entityManager.remove(viagemAtual);
	}

	private List<Atracao> listarAtracoes(String destino, Integer numeroPasseios) {

		StringBuilder sql = new StringBuilder();
		sql.append("select a.idAtracao from Passeio p join p.atracao a ");
		sql.append("where a.destino = :destino ");
		sql.append("group by a.idAtracao ");
		sql.append("order by avg(p.rating) desc ");

		TypedQuery<Long> query = entityManager.createQuery(sql.toString(), Long.class);
		query.setParameter("destino", destino);
		query.setMaxResults(numeroPasseios);

		List<Atracao> atracoes = new ArrayList<Atracao>();
		for (Long idAtracao : query.getResultList()) {
			atracoes.add(entityManager.find(Atracao.class, idAtracao));
		}
		return atracoes;
	}
}
