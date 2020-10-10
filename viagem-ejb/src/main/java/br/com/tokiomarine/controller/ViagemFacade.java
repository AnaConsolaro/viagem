package br.com.tokiomarine.controller;

import java.util.List;

import javax.ejb.Local;

import br.com.tokiomarine.exception.ViagemException;
import br.com.tokiomarine.model.Viagem;

@Local
public interface ViagemFacade {

	List<Viagem> listarTodos();
	
	Viagem buscarPorId(long id);
	
	Viagem criar(Viagem novaViagem, boolean gerarPasseios, Integer numeroPasseios);
	
	Viagem atualizar(Long id, Viagem viagemAtual) throws ViagemException;
	
	Viagem deletar(Viagem viagemAtual);

	void deletarPorId(Long id) throws ViagemException;

}