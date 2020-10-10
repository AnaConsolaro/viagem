package br.com.tokiomarine.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_atracoes", schema = "viagem")
public class Atracao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TAB_ATRACOES_IDATRACAO_GENERATOR", sequenceName = "VIAGEM.SEQ_ATRACOES")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAB_ATRACOES_IDATRACAO_GENERATOR")
	@Column(name = "idAtracao")
	private Long idAtracao;

	@Column(name = "nomeAtracao")
	private String nomeAtracao;

	@Column(name = "destino")
	private String destino;

	public String getNomeAtracao() {
		return nomeAtracao;
	}

	public void setNomeAtracao(String nomeAtracao) {
		this.nomeAtracao = nomeAtracao;
	}

	public Atracao() {
	}

	public Long getIdAtracao() {
		return this.idAtracao;
	}

	public void setIdAtracao(Long idAtracao) {
		this.idAtracao = idAtracao;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

}