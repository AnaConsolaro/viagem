package br.com.tokiomarine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "tab_passeios", schema = "viagem")
public class Passeio {
	
	@EmbeddedId
	private PasseioPK id;
	
	@Column(name = "ingresso")
	private boolean ingresso;
	
	@Column(name = "ingressoComprado")
	private boolean ingressoComprado;
	
	@Column(name = "dataPasseio")
	private Date dataPasseio;
	
	@Column(name = "rating")
	private Integer rating;
	
	@ManyToOne
	@JoinColumn(name = "id_Viagem", insertable = false, updatable = false ) 
	private Viagem viagem;
	
	@ManyToOne
	@JoinColumn(name = "id_Atracao", insertable = false, updatable = false )
	private Atracao atracao;

	@JsonIgnore
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

	@JsonIgnore
	public PasseioPK getId() {
		return id;
	}

	public void setId(PasseioPK id) {
		this.id = id;
	}

	public boolean isIngresso() {
		return ingresso;
	}

	public void setIngresso(boolean ingresso) {
		this.ingresso = ingresso;
	}

	public boolean isIngressoComprado() {
		return ingressoComprado;
	}

	public void setIngressoComprado(boolean ingressoComprado) {
		this.ingressoComprado = ingressoComprado;
	}

	public Date getDataPasseio() {
		return dataPasseio;
	}

	public void setDataPasseio(Date dataPasseio) {
		this.dataPasseio = dataPasseio;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	
}
