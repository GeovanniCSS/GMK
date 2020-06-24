package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pilotoCampeonato")
public class PilotoCampeonato implements EntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "idPilotoCampeonato")
	private int id;
	private Integer posicao;
	private Integer pontucao;
	private Boolean administrador;
	
	@ManyToOne
	private Piloto piloto;
	
	@ManyToOne
	private Campeonato campeonato;

	public PilotoCampeonato() {
		super();
	}

	public PilotoCampeonato(int id, int posicao, int pontucao, boolean administrador, Piloto piloto,
			Campeonato campeonato) {
		super();
		this.id = id;
		this.posicao = posicao;
		this.pontucao = pontucao;
		this.administrador = administrador;
		this.piloto = piloto;
		this.campeonato = campeonato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public int getPontucao() {
		return pontucao;
	}

	public void setPontucao(int pontucao) {
		this.pontucao = pontucao;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (administrador ? 1231 : 1237);
		result = prime * result + ((campeonato == null) ? 0 : campeonato.hashCode());
		result = prime * result + id;
		result = prime * result + ((piloto == null) ? 0 : piloto.hashCode());
		result = prime * result + pontucao;
		result = prime * result + posicao;
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
		PilotoCampeonato other = (PilotoCampeonato) obj;
		if (administrador != other.administrador)
			return false;
		if (campeonato == null) {
			if (other.campeonato != null)
				return false;
		} else if (!campeonato.equals(other.campeonato))
			return false;
		if (id != other.id)
			return false;
		if (piloto == null) {
			if (other.piloto != null)
				return false;
		} else if (!piloto.equals(other.piloto))
			return false;
		if (pontucao != other.pontucao)
			return false;
		if (posicao != other.posicao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PilotoCampeonato [id=" + id + ", posicao=" + posicao + ", pontucao=" + pontucao + ", administrador="
				+ administrador + ", piloto=" + piloto.getLogin() + ", campeonato=" + campeonato.getNome() + "]";
	}
	
}
