package entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pilotoCorrida")
public class PilotoCorrida implements EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "idPilotoCorrida")
	private int id;
	// @Column(name = "posicaoLargada")
	private int largada;
	// @Column(name = "posicaoChegada")
	private int chegada;
	// @Column(name = "VoltaMaisRapida")
	private Boolean vMRapida;
	private Boolean pLideranca;
	private Boolean ausente;
	private LocalTime tempoCorrida;
	@ManyToOne
	private Piloto piloto;
	@ManyToOne
	private CorridaCampeonato corridaCampeonato;

	public PilotoCorrida() {
	}

	public PilotoCorrida(int id, int largada, int chegada, Boolean vMRapida, Boolean pLideranca, Boolean ausente,
			LocalTime tempoCorrida, Piloto piloto, CorridaCampeonato corridaCampeonato) {
		super();
		this.id = id;
		this.largada = largada;
		this.chegada = chegada;
		this.vMRapida = vMRapida;
		this.pLideranca = pLideranca;
		this.ausente = ausente;
		this.tempoCorrida = tempoCorrida;
		this.piloto = piloto;
		this.corridaCampeonato = corridaCampeonato;
	}

	public PilotoCorrida(int i, CorridaCampeonato campeonato) {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLargada() {
		return largada;
	}

	public void setLargada(int largada) {
		this.largada = largada;
	}

	public int getChegada() {
		return chegada;
	}

	public void setChegada(int chegada) {
		this.chegada = chegada;
	}

	public boolean isvMRapida() {
		return vMRapida;
	}

	public void setvMRapida(boolean vMRapida) {
		this.vMRapida = vMRapida;
	}

	public boolean ispLideranca() {
		return pLideranca;
	}

	public void setpLideranca(boolean pLideranca) {
		this.pLideranca = pLideranca;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public CorridaCampeonato getCorridaCampeonato() {
		return corridaCampeonato;
	}

	public Boolean getvMRapida() {
		return vMRapida;
	}

	public void setvMRapida(Boolean vMRapida) {
		this.vMRapida = vMRapida;
	}

	public Boolean getpLideranca() {
		return pLideranca;
	}

	public void setpLideranca(Boolean pLideranca) {
		this.pLideranca = pLideranca;
	}

	public Boolean getAusente() {
		return ausente;
	}

	public void setAusente(Boolean ausente) {
		this.ausente = ausente;
	}

	public void setCorridaCampeonato(CorridaCampeonato corridaCampeonato) {
		this.corridaCampeonato = corridaCampeonato;
	}

	public LocalTime getTempoCorrida() {
		return tempoCorrida;
	}

	public void setTempoCorrida(LocalTime tempoCorrida) {
		this.tempoCorrida = tempoCorrida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chegada;
		result = prime * result + ((corridaCampeonato == null) ? 0 : corridaCampeonato.hashCode());
		result = prime * result + id;
		result = prime * result + largada;
		result = prime * result + (pLideranca ? 1231 : 1237);
		result = prime * result + ((piloto == null) ? 0 : piloto.hashCode());
		result = prime * result + (vMRapida ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PilotoCorrida other = (PilotoCorrida) obj;
		if (chegada != other.chegada)
			return false;
		if (corridaCampeonato == null) {
			if (other.corridaCampeonato != null)
				return false;
		} else if (!corridaCampeonato.equals(other.corridaCampeonato))
			return false;
		if (id != other.id)
			return false;
		if (largada != other.largada)
			return false;
		if (pLideranca != other.pLideranca)
			return false;
		if (piloto == null) {
			if (other.piloto != null)
				return false;
		} else if (!piloto.equals(other.piloto))
			return false;
		if (vMRapida != other.vMRapida)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return piloto.getLogin();
	}

}
