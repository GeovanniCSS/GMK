package bo;

import java.util.List;

import dao.CampeonatoDao;
import dao.GenericDao;
import entity.Campeonato;

public class CampeonatoBo {

	public void saveOrUpadte(Campeonato campeonato) throws Exception {
		validarDadosCampeonato(campeonato);

		GenericDao<Campeonato> gDao = new GenericDao<Campeonato>();
		try {
			gDao.saveOrUpdate(campeonato);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(Campeonato campeonato) throws Exception {
		validarDadosCampeonato(campeonato);

		GenericDao<Campeonato> gDao = new GenericDao<Campeonato>();
		try {
			gDao.remove(Campeonato.class, campeonato.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Campeonato> listar(String tipo, Campeonato campeonato, int id) throws Exception {
		try {
			if (tipo.equals("")) {
				GenericDao<Campeonato> kDao = new GenericDao<Campeonato>();
				return kDao.list(Campeonato.class);
			} else {
				CampeonatoDao campeonatoDao = new CampeonatoDao();
				return campeonatoDao.lista(tipo, campeonato, id);
			}
		} catch (Exception e) {
			throw new Exception("Nenhum campeonato encontrado");
		}
	}

	public Campeonato salvarCampeonato(Campeonato campeonato) throws Exception {
		PilotoCampeonatoBo pilotoCampeonatoBo = new PilotoCampeonatoBo();
		validarDadosCampeonato(campeonato);
		try {
			if (listar("repetido", campeonato, 0).size() == 0) {
				saveOrUpadte(campeonato);
				pilotoCampeonatoBo.salvarPiloto("criador", campeonato.getPiloto(), campeonato, null);
			}
		} catch (Exception e) {
			throw new Exception("J� exite um campeonato com esse nome");
		}
		return campeonato;
	}

	public void validarDadosCampeonato(Campeonato campeonato) throws Exception {
		if (campeonato.getId() < 0) {
			throw new Exception("Id do campeonato n�o pode ser negativo!");
		} else if (campeonato.getNome().equals("")) {
			throw new Exception("O nome do campeonato n�o pode ficar em branco!");
		} else if (campeonato.getTotalCorridas() <= 2) {
			throw new Exception("N�mero de baterias minimo � 3!");
		} else if (campeonato.getpLideranca() <= 0 || campeonato.getpLideranca() >= 6) {
			throw new Exception("A pontua��o por mais voltas na lideran�a deve valer entre 1 e 5");
		} else if (campeonato.getpVMRapida() <= 0) {
			throw new Exception("A pontua��o por pole position deve valer entre 1 e 5");
		} else if (campeonato.getPole()<= 0) {
			throw new Exception("A pontua��o pela volta mais r�pida deve valer entre 1 e 5");
		}	
	}
}
