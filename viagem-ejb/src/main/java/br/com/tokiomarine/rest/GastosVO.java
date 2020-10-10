package br.com.tokiomarine.rest;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.tokiomarine.model.Gasto;
import br.com.tokiomarine.model.TipoGasto;
import br.com.tokiomarine.model.Viagem;

public class GastosVO implements Serializable {

	private static final long serialVersionUID = 4253602472696488965L;
	
	private List<Gasto> listaGastos;
	private Double totalGastos;
	
	public List<Gasto> getListaGastos() {
		return listaGastos;
	}
	public void setListaGastos(List<Gasto> listaGastos) {
		this.listaGastos = listaGastos;
	}
	public Double getTotalGastos() {
		return totalGastos;
	}
	public void setTotalGastos(Double totalGastos) {
		this.totalGastos = totalGastos;
	}
	
	public GastosVO(List<Gasto> listaGastos, Double totalGastos) {
		super();
		this.listaGastos = listaGastos;
		this.totalGastos = totalGastos;
	}
	
	public static GastosVO criarAPartirDaViagem(Viagem viagem) {
		return new GastosVO(Lists.newArrayList(viagem.getGastos()), viagem.totalGastos());
	}
	
	public static GastosVO criarAPartirDaViagem(Viagem viagem, TipoGasto tipoGasto) {
		return new GastosVO(Lists.newArrayList(viagem.getGastos()), viagem.totalGastosTipo(tipoGasto));
	}
}
