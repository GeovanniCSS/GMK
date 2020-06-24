package entity;

import java.time.LocalDate;
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
@Table(name = "tb_campeonato")
public class Campeonato implements EntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	//@Temporal(TemporalType.DATE)
	private LocalDate dataCadastrado;
	private int totalCorridas;
	private boolean situacao;
	//@Column(name = "pontuacaopole")
	private Integer pole;
	//@Column(name = "pontuacaoVoltaMaisRapida")
	private Integer pVMRapida;
	//@Column(name = "pontuacaoLiderancaMaisVoltas")
	private Integer pLideranca;
	
	@ManyToOne
	private Piloto piloto;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="campeonato")
	private List<CampeonatoPontuacao> campeonatoPontuacao;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="campeonato")
	private List<Convite> convite;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="campeonato")
	private List<CorridaCampeonato> corridaCampeonato;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="campeonato")
	private List<PilotoCampeonato> pilotoCampeonato;

	
	public Campeonato() {
		super();
	}
	public Campeonato(int id) {
		this.id=id;
	}
	public Campeonato(int id, String nome, LocalDate dataCadastrado, int totalCorridas, boolean situacao, int pole,
			int pVMRapida, int pLideranca, Piloto piloto) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCadastrado = dataCadastrado;
		this.totalCorridas = totalCorridas;
		this.situacao = situacao;
		this.pole = pole;
		this.pVMRapida = pVMRapida;
		this.pLideranca = pLideranca;
		this.piloto = piloto;
		campeonatoPontuacao = new ArrayList<CampeonatoPontuacao>();
		convite = new ArrayList<Convite>();
		corridaCampeonato = new ArrayList<CorridaCampeonato>();
		pilotoCampeonato = new ArrayList<PilotoCampeonato>();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataCadastrado() {
		return dataCadastrado;
	}

	public void setDataCadastrado(LocalDate dataCadastrado) {
		this.dataCadastrado = dataCadastrado;
	}

	public int getTotalCorridas() {
		return totalCorridas;
	}

	public void setTotalCorridas(int totalCorridas) {
		this.totalCorridas = totalCorridas;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Integer getPole() {
		return pole;
	}

	public void setPole(Integer pole) {
		this.pole = pole;
	}

	public int getpVMRapida() {
		return pVMRapida;
	}

	public void setpVMRapida(int pVMRapida) {
		this.pVMRapida = pVMRapida;
	}

	public int getpLideranca() {
		return pLideranca;
	}

	public void setpLideranca(int pLideranca) {
		this.pLideranca = pLideranca;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public void addCampeonatoPontuacao (CampeonatoPontuacao cp) {
		campeonatoPontuacao.add(cp);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pole;
		result = prime * result + ((dataCadastrado == null) ? 0 : dataCadastrado.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + pLideranca;
		result = prime * result + pVMRapida;
		result = prime * result + ((piloto == null) ? 0 : piloto.hashCode());
		result = prime * result + (situacao ? 1231 : 1237);
		result = prime * result + totalCorridas;
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
		Campeonato other = (Campeonato) obj;
		if (pole != other.pole)
			return false;
		if (dataCadastrado == null) {
			if (other.dataCadastrado != null)
				return false;
		} else if (!dataCadastrado.equals(other.dataCadastrado))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pLideranca != other.pLideranca)
			return false;
		if (pVMRapida != other.pVMRapida)
			return false;
		if (piloto == null) {
			if (other.piloto != null)
				return false;
		} else if (!piloto.equals(other.piloto))
			return false;
		if (situacao != other.situacao)
			return false;
		if (totalCorridas != other.totalCorridas)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
