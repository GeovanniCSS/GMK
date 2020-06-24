package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_piloto")
public class Piloto implements EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "idPiloto")
	private int id;
	//@Column(name = "nomePiloto")
	private Integer cpf;
	private String nome;
	private String login;
	//@Column(name = "senhaPiloto")
	private String senha;
	private String email;
	//@Temporal(TemporalType.TIMESTAMP)
	private LocalDate dataNascimento;
	private Integer celular;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "piloto")
	private List<PilotoCampeonato> pilotoCampeonato;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "piloto")
	private List<PilotoCorrida> pilotoCorrida;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "piloto")
	private List<Convite> convite;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "piloto")
	private List<CorridaCampeonato> corridaCampeonato;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "piloto")
	private List<Campeonato> campeonato;

	public Piloto() {

	}
	public Piloto(int id) {
		this.id=id;
	}

	public Piloto(int id,Integer cpf, String nome, String login,String email, String senha, LocalDate dataNascimento, int celular) {
		this.id = id;
		this.cpf=cpf;
		this.nome = nome;
		this.login = login;
		this.email=email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.celular = celular;
		pilotoCampeonato = new ArrayList<PilotoCampeonato>();
		pilotoCorrida = new ArrayList<PilotoCorrida>();
		convite = new ArrayList<Convite>();
		corridaCampeonato = new ArrayList<CorridaCampeonato>();
		campeonato = new ArrayList<Campeonato>();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public List<PilotoCampeonato> getPilotoCampeonato() {
		return pilotoCampeonato;
	}

	public List<PilotoCorrida> getPilotoCorrida() {
		return pilotoCorrida;
	}

	public List<Convite> getConvite() {
		return convite;
	}

	public List<CorridaCampeonato> getCorridaCampeonato() {
		return corridaCampeonato;
	}

	public List<Campeonato> getCampeonato() {
		return campeonato;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + celular;
		result = prime * result + cpf;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Piloto other = (Piloto) obj;
		if (celular != other.celular)
			return false;
		if (cpf != other.cpf)
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Piloto [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ ", email=" + email + ", dataNascimento=" + dataNascimento + ", celular=" + celular;
	}
	
	

	

}
