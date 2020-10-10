package br.com.tokiomarine.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tab_viagem", schema = "viagem")
public class Viagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_viagem")
	@GeneratedValue(generator = "seq_viagem", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_viagem", sequenceName = "seq_viagem", schema = "viagem", allocationSize = 1)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_final")
	private Date dataFinal;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	private Date dataInicio;

	private String destino;

	private String origem;

	@OneToMany(mappedBy = "viagem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Passeio> passeios = new HashSet<Passeio>();

	@OneToMany(mappedBy = "viagem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Gasto> gastos;

	public Set<Passeio> getPasseios() {
		return passeios;
	}

	public void setPasseios(Set<Passeio> passeios) {
		this.passeios = passeios;
	}
	
	public void removerPasseio(Atracao atracao)  {
		for (Passeio passeio : passeios) {
			if (passeio.getAtracao().getNomeAtracao().equals(atracao.getNomeAtracao())) {
				passeios.remove(passeio);
			}
		}
	}

	public Set<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(Set<Gasto> gastos) {
		this.gastos = gastos;
	}

	public Double totalGastosTipo(TipoGasto tipo) {
		Double totalGasto = 0.0;
		for (Gasto gasto : gastos) {
			if (gasto.getTipo().equals(tipo) && gasto.isEfetivado()  ) {
				totalGasto += gasto.getValor();
			}
		}
		return totalGasto;
	}
	
	public Double totalGastosComida() {
		return totalGastosTipo(TipoGasto.COMIDA);
	}
	
	public Double totalGastosHotel() {
		return totalGastosTipo(TipoGasto.HOTEL);
	}

	public Double totalGastosPasseio() {
		return totalGastosTipo(TipoGasto.PASSEIOS);
	}
	
	public Double totalGastosTransporte() {
		return totalGastosTipo(TipoGasto.TRANSPORTE);
	}
	
	public Double totalGastos() {
		Double totalGasto = 0.0;
		for (Gasto gasto : gastos) {
			if (gasto.isEfetivado()  ) {
				totalGasto += gasto.getValor();
			}
		}
		return totalGasto;
	}


	public Viagem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigem() {
		return this.origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public void adicionarAtracao(Atracao atracao) {
		Passeio passeio = new Passeio();
		passeio.setId(new PasseioPK(this, atracao));
		passeio.setDataPasseio(new Date());
		passeio.setAtracao(atracao);
		passeios.add(passeio);
	}
	
	public String getTexto() {
	    return this.origem + " para " + this.destino + "!";
	}

}