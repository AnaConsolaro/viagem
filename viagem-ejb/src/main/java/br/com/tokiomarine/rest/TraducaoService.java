package br.com.tokiomarine.rest;

import java.util.HashMap;
import java.util.Map;

public class TraducaoService {

	private String idioma;
	private Map<String , String> traducoes = new HashMap<String, String>();
	
	public TraducaoService(String idioma) {
		super();
		this.idioma = idioma;
		init();
	}
	
	private void init( ) {
		traducoes.put("um", "one");
	}
	
	public String traduzir(String palavra) {
		return traducoes.get(palavra);
	}
}

