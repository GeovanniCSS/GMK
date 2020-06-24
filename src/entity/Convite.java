package entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_convite")
public class Convite implements EntityBase{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="idConvite")
	private int id;
	private Boolean resposta;
	//@Temporal(TemporalType.DATE)
	private LocalDate dataResposta;
	@ManyToOne
	//@JoinColumn(name ="campeonato")
	private Campeonato campeonato;
	@ManyToOne
	//@JoinColumn(name= "piloto")
	private Piloto piloto;
	
	public Convite() {
		super();
	}

	public Convite(int id, Boolean resposta, LocalDate dataResposta, Campeonato campeonato, Piloto piloto) {
		super();
		this.id = id;
		this.resposta = resposta;
		this.dataResposta = dataResposta;
		this.campeonato = campeonato;
		this.piloto = piloto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isResposta() {
		return resposta;
	}

	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}

	public LocalDate getDataR() {
		return dataResposta;
	}

	public void setDataR(LocalDate dataResposta) {
		this.dataResposta = dataResposta;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campeonato == null) ? 0 : campeonato.hashCode());
		result = prime * result + ((dataResposta == null) ? 0 : dataResposta.hashCode());
		result = prime * result + id;
		result = prime * result + ((piloto == null) ? 0 : piloto.hashCode());
		result = prime * result + (resposta ? 1231 : 1237);
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
		Convite other = (Convite) obj;
		if (campeonato == null) {
			if (other.campeonato != null)
				return false;
		} else if (!campeonato.equals(other.campeonato))
			return false;
		if (dataResposta == null) {
			if (other.dataResposta != null)
				return false;
		} else if (!dataResposta.equals(other.dataResposta))
			return false;
		if (id != other.id)
			return false;
		if (piloto == null) {
			if (other.piloto != null)
				return false;
		} else if (!piloto.equals(other.piloto))
			return false;
		if (resposta != other.resposta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Convite [id=" + id + ", resposta=" + resposta + ", dataResposta=" + dataResposta + ", campeonato="
				+ campeonato.getNome() + ", piloto=" + piloto.getNome() + "]";
	}
	

}
