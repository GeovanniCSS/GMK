package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_corridaCampeonato")
public class CorridaCampeonato implements EntityBase{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="idCorridaCampeonato")
	private int id;
	private String nome;
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataCadastrado;
	//@Temporal(TemporalType.DATE)
	private LocalDate data;
	//@Temporal(TemporalType.TIME)
	private LocalTime hora;
	private boolean situacao;
	@ManyToOne
	private Campeonato campeonato;
	@ManyToOne
	private Kartodromo kartodromo;
	@ManyToOne
	private Piloto piloto;
	
	@OneToMany(cascade= CascadeType.ALL,mappedBy="corridaCampeonato")
	private List<PilotoCorrida> pilotoCorrida;
	
	

	public CorridaCampeonato() {
		super();
	}

	public CorridaCampeonato(int id,String nome, LocalDateTime dataCadastrado, LocalDate d,LocalTime lt, boolean situacao,
			Campeonato campeonato, Kartodromo kartodromo, Piloto piloto) {
		this.id = id;
		this.nome=nome;
		this.dataCadastrado = dataCadastrado;
		this.data = d;
		this.hora = lt;
		this.situacao = situacao;
		this.campeonato = campeonato;
		this.kartodromo = kartodromo;
		this.piloto = piloto;
		pilotoCorrida=new ArrayList<PilotoCorrida>();
	}

	public CorridaCampeonato(int i) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDataCadastrado() {
		return dataCadastrado;
	}

	public void setDataCadastrado(LocalDateTime dataCadastrado) {
		this.dataCadastrado = dataCadastrado;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public Kartodromo getKartodromo() {
		return kartodromo;
	}

	public void setKartodromo(Kartodromo kartodromo) {
		this.kartodromo = kartodromo;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PilotoCorrida> getPilotoCorrida() {
		return pilotoCorrida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campeonato == null) ? 0 : campeonato.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((dataCadastrado == null) ? 0 : dataCadastrado.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + id;
		result = prime * result + ((kartodromo == null) ? 0 : kartodromo.hashCode());
		result = prime * result + ((piloto == null) ? 0 : piloto.hashCode());
		result = prime * result + (situacao ? 1231 : 1237);
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
		CorridaCampeonato other = (CorridaCampeonato) obj;
		if (campeonato == null) {
			if (other.campeonato != null)
				return false;
		} else if (!campeonato.equals(other.campeonato))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dataCadastrado == null) {
			if (other.dataCadastrado != null)
				return false;
		} else if (!dataCadastrado.equals(other.dataCadastrado))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (id != other.id)
			return false;
		if (kartodromo == null) {
			if (other.kartodromo != null)
				return false;
		} else if (!kartodromo.equals(other.kartodromo))
			return false;
		if (piloto == null) {
			if (other.piloto != null)
				return false;
		} else if (!piloto.equals(other.piloto))
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
}
