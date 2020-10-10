package br.com.tokiomarine.model;

import java.util.HashSet;

import org.junit.Test;

import junit.framework.Assert;

public class ViagemTest {

	@Test
	public void deveriaSomarGastosComidaComTotalDe100Test() {
		// criar os objetos utilizados no teste
		// executar o método sob teste
		// verificar o resultado
		
		Viagem viagem = new Viagem();
		
		Gasto gasto = new Gasto();
		
		gasto.setTipo(TipoGasto.COMIDA);
		gasto.setValor(100.00);
		gasto.setEfetivado(true);
		viagem.setGastos(new HashSet<Gasto>());
		viagem.getGastos().add(gasto);
		
		Double total = viagem.totalGastosComida();
		
		Assert.assertEquals(100.0, total);
	}
	
	@Test
	public void removerPasseio() {
		// criar os objetos utilizados no teste
		// executar o método sob teste
		// verificar o resultado
		
		Viagem viagem = new Viagem();
		viagem.setPasseios(new HashSet<Passeio>());
		Passeio passeio = new Passeio();
		viagem.getPasseios().add(passeio);
		
		Atracao atracao = new Atracao();
		passeio.setAtracao(atracao);
		atracao.setNomeAtracao("teste");
		viagem.removerPasseio(atracao);
		
		Assert.assertEquals(0, viagem.getPasseios().size());
		Assert.assertTrue(viagem.getPasseios().isEmpty());
	}
	
	
}
