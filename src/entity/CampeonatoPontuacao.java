package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_campeonatoPontuacao")
public class CampeonatoPontuacao implements EntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "idCampeonatoPontuacao")
	private int id;
	private int posicao;
	private int pontuacao;
	@ManyToOne
	private Campeonato campeonato;
	
	public CampeonatoPontuacao() {
	}

	public CampeonatoPontuacao(int id, int posicao, int pontuacao, Campeonato campeonato) {
		this.id = id;
		this.posicao = posicao;
		this.pontuacao = pontuacao;
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

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
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
		result = prime * result + ((campeonato == null) ? 0 : campeonato.hashCode());
		result = prime * result + id;
		result = prime * result + pontuacao;
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
		CampeonatoPontuacao other = (CampeonatoPontuacao) obj;
		if (campeonato == null) {
			if (other.campeonato != null)
				return false;
		} else if (!campeonato.equals(other.campeonato))
			return false;
		if (id != other.id)
			return false;
		if (pontuacao != other.pontuacao)
			return false;
		if (posicao != other.posicao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CampeonatoPontuacao [id=" + id + ", posicao=" + posicao + ", pontuacao=" + pontuacao + ", campeonato="
				+ campeonato + "]";
	}
	
	
}
