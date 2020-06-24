package bo;

import java.util.ArrayList;
import java.util.List;

import dao.GenericDao;
import dao.KartodromoDao;
import entity.Cidade;
import entity.Kartodromo;

public class KartodromoBo {
	
	public void saveOrUpadte(Kartodromo kartodromo) 
			throws Exception {
		GenericDao<Kartodromo> kartodromoDao = new GenericDao<Kartodromo>();
		try {
			kartodromoDao.saveOrUpdate(kartodromo);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(Kartodromo kartodromo) 
			throws Exception {
		validarDadosKartodromo(kartodromo);
		try {
			GenericDao<Kartodromo> kartodromoDao = new GenericDao<Kartodromo>();
			kartodromoDao.remove(Kartodromo.class, kartodromo.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Kartodromo> listar(String tipo,Kartodromo kartodromo,int id) throws Exception {
		try {
			if(tipo.equals("")) {
				GenericDao<Kartodromo> kDao = new GenericDao<Kartodromo>();
				return kDao.list(Kartodromo.class);	
			}else {
				KartodromoDao kartodromoDao=new KartodromoDao();
				return kartodromoDao.listar(tipo, kartodromo,0);
			}
		} catch (Exception e) {
			throw new Exception("Nenhum kartódromo selecionado");
		}
	}	
	
	private void validarDadosKartodromo(Kartodromo kartodromo) throws Exception {
		if (kartodromo.getId() < 0) {
			throw new Exception("Id do kartodromo não pode ser negativo!");
		}
		if (kartodromo.getNome().equals("")) {
			throw new Exception("Nome do kartodromo não pode ficar em branco!");
		}
	}
	public void cadastro(String tipo, Kartodromo kartodromo) throws Exception {
		KartodromoBo kartodromoBo=new KartodromoBo();
		if (kartodromoBo.listar(tipo, kartodromo, 0).size() == 0) {
			
		
		} else {
			throw new Exception("Nome de Kartodromo já esta sendo utilizado!");
		}
	}
	public Kartodromo login(String tipo, Kartodromo kartodromo) throws Exception {
		KartodromoBo kartodromoBo=new KartodromoBo();
		if (kartodromoBo.listar(tipo, kartodromo, 0).size() == 1) {
			return kartodromoBo.listar(tipo, kartodromo, 0).get(0);
		} else {
			throw new Exception("Login incorreto");
		}
	}
	public static List<String> horarios(){
		List<String>lista=new ArrayList<String>();
		for(int i=6;i<24;i++) {
			if(i>=10) {
				lista.add(i+":00");
			}else {
				lista.add("0"+i+":00");
			}
		}
		return lista;
	}

	public Kartodromo proximaTela(String nome, String senha, String Rua, String numero, String complemento, Cidade cidade)
			throws Exception {
		Kartodromo kartodromo = new Kartodromo();
		if (nome.equals("")) {
			throw new Exception("Nome do Kartodromo não pode ficar em branco!");
		} else if (senha.equals("")) {
			throw new Exception("Senha não pode ficar em branco!");
		} else if (Rua.equals("")) {
			throw new Exception("Rua não pode ficar em branco!");
		} else if (complemento.equals("")) {
			throw new Exception("celular não pode ficar em branco!");
		}else {
			try {
				kartodromo.setNome(nome);
				kartodromo.setSenha(senha);
				kartodromo.seteRua(Rua);
				kartodromo.seteNumero(Integer.parseInt(numero));
				kartodromo.seteComplemento(complemento);
				kartodromo.setCidade(cidade);
				
			} catch (Exception e) {
				throw new Exception("erro");
			}
		}
		return kartodromo;
	}


}
