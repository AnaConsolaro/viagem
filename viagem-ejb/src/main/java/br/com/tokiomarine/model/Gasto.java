package br.com.tokiomarine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_gastos", schema = "viagem")
public class Gasto {

	@Id
	@Column(name="id_gasto")
	@GeneratedValue(generator = "seq_gastos", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_gastos", sequenceName = "viagem.seq_gastos")
	private Long id;
	
	private Double valor;
	
	@Enumerated(EnumType.STRING)
	private TipoGasto tipo;
	
	private boolean efetivado;
	
	@ManyToOne
	@JoinColumn(name = "id_Viagem") 
	private Viagem viagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoGasto getTipo() {
		return tipo;
	}

	public void setTipo(TipoGasto tipo) {
		this.tipo = tipo;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public boolean isEfetivado() {
		return efetivado;
	}

	public void setEfetivado(boolean efetivado) {
		this.efetivado = efetivado;
	}
	
}
