package br.com.tokiomarine.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PasseioPK implements Serializable{

	private static final long serialVersionUID = -5951421313346927747L;
	
	@ManyToOne
	@JoinColumn(name = "id_Viagem" ) 
	private Viagem viagem;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_Atracao" )
	private Atracao atracao;
	
	public PasseioPK() {
	}
	
	public PasseioPK(Viagem viagem, Atracao atracao) {
		super();
		this.viagem = viagem;
		this.atracao = atracao;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public Atracao getAtracao() {
		return atracao;
	}

	public void setAtracao(Atracao atracao) {
		this.atracao = atracao;
	}

	
	
	
}
