package br.com.tokiomarine.rest;

import java.io.File;
import java.io.IOException;

import javax.enterprise.inject.Produces;

import org.apache.commons.io.FileUtils;

public class TraducaoServiceProducer {

	@Produces
	public TraducaoService createTraducaoService() {
		try {
			return new TraducaoService(FileUtils.readFileToString(new File("c:\\idioma.txt")));
		} catch (IOException e) {
			return new TraducaoService("portugues");
		}
	}
}
