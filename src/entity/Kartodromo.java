package entity;

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
@Table(name="tb_kartodromo")
public class Kartodromo implements EntityBase {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String senha;
	//@Column(name="enderecoRua")
	private String eRua;
	//@Column(name="enderecoNumero")
	private int eNumero;
	//@Column(name="enderecoComplemento")
	private String eComplemento;
	//@Column(name="numeroMinimo")
	private int numMinimo;
	//@Column(name="numeroMaximo")
	private int numMax;
	private int telefone;
	private int whatsapp;
	private String urlSite;
	private boolean situacao;
	@ManyToOne
	private Cidade cidade;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "kartodromo")
	private List<CorridaCampeonato> corridaCampeonato;
	
	public Kartodromo() {
	}
	public Kartodromo(int id) {
		this.id=id;
	}
	public Kartodromo(int id, String nome,String senha, String eRua, int eNumero, String eComplemento, int telefone, int whatsapp,
			String urlSite, boolean situacao, Cidade cidade,int numMinimo,int numMax) {
		this.id = id;
		this.nome = nome;
		this.senha=senha;
		this.eRua = eRua;
		this.eNumero = eNumero;
		this.eComplemento = eComplemento;
		this.telefone = telefone;
		this.whatsapp = whatsapp;
		this.urlSite = urlSite;
		this.situacao = situacao;
		this.cidade = cidade;
		this.numMinimo=numMinimo;
		this.numMax=numMax;
		corridaCampeonato=new ArrayList<CorridaCampeonato>();
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


	public String geteRua() {
		return eRua;
	}


	public void seteRua(String eRua) {
		this.eRua = eRua;
	}


	public int geteNumero() {
		return eNumero;
	}


	public void seteNumero(int eNumero) {
		this.eNumero = eNumero;
	}


	public String geteComplemento() {
		return eComplemento;
	}


	public void seteComplemento(String eComplemento) {
		this.eComplemento = eComplemento;
	}


	public int getTelefone() {
		return telefone;
	}


	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}


	public int getWhatsapp() {
		return whatsapp;
	}


	public void setWhatsapp(int whatsapp) {
		this.whatsapp = whatsapp;
	}


	public String getUrlSite() {
		return urlSite;
	}


	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}


	public boolean isSituacao() {
		return situacao;
	}


	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}


	public Cidade getCidade() {
		return cidade;
	}


	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public int getNumMinimo() {
		return numMinimo;
	}
	public void setNumMinimo(int numMinimo) {
		this.numMinimo = numMinimo;
	}
	public int getNumMax() {
		return numMax;
	}
	public void setNumMax(int numMax) {
		this.numMax = numMax;
	}
	public List<CorridaCampeonato> getCorridaCampeonato() {
		return corridaCampeonato;
	}
	

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((eComplemento == null) ? 0 : eComplemento.hashCode());
		result = prime * result + eNumero;
		result = prime * result + ((eRua == null) ? 0 : eRua.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + numMax;
		result = prime * result + numMinimo;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + (situacao ? 1231 : 1237);
		result = prime * result + telefone;
		result = prime * result + ((urlSite == null) ? 0 : urlSite.hashCode());
		result = prime * result + whatsapp;
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
		Kartodromo other = (Kartodromo) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (eComplemento == null) {
			if (other.eComplemento != null)
				return false;
		} else if (!eComplemento.equals(other.eComplemento))
			return false;
		if (eNumero != other.eNumero)
			return false;
		if (eRua == null) {
			if (other.eRua != null)
				return false;
		} else if (!eRua.equals(other.eRua))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numMax != other.numMax)
			return false;
		if (numMinimo != other.numMinimo)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (situacao != other.situacao)
			return false;
		if (telefone != other.telefone)
			return false;
		if (urlSite == null) {
			if (other.urlSite != null)
				return false;
		} else if (!urlSite.equals(other.urlSite))
			return false;
		if (whatsapp != other.whatsapp)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return nome;
	}
	
}
