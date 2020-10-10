package br.com.tokiomarine.rest;

import javax.inject.Inject;

public class TraducaoServiceMaiusculo {
	@Inject
	private TraducaoService traducaoService;
	
	public String traduzir(String palavra) {
		return traducaoService.traduzir(palavra).toUpperCase();
		
	}
	
}
