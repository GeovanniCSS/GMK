package bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.GenericDao;
import dao.PilotoCampeonatoDao;
import entity.Campeonato;
import entity.Piloto;
import entity.PilotoCampeonato;
import entity.PilotoCorrida;

public class PilotoCampeonatoBo {

	public void saveOrUpadte(PilotoCampeonato pilotoCampeonato) throws Exception {
		validarDadosPilotoCampeonato(pilotoCampeonato);

		GenericDao<PilotoCampeonato> gDao = new GenericDao<PilotoCampeonato>();
		try {
			gDao.saveOrUpdate(pilotoCampeonato);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(PilotoCampeonato pilotoCampeonato) throws Exception {
		validarDadosPilotoCampeonato(pilotoCampeonato);

		GenericDao<PilotoCampeonato> gDao = new GenericDao<PilotoCampeonato>();
		try {
			gDao.remove(PilotoCampeonato.class, pilotoCampeonato.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void salvarPiloto(String tornar, Piloto piloto, Campeonato campeonato, PilotoCampeonato pc)
			throws Exception {
		PilotoCorridaBo pcB=new PilotoCorridaBo();
		try {
			if (tornar.equals("tornar")) {
				pc.setAdministrador(true);
			} else if (tornar.equals("criador")) {
				pc = new PilotoCampeonato(0, 0, 0, true, piloto, campeonato);
			} else if (tornar.equals("novo") && pilotoRepetido(piloto, campeonato) == false) {
				pc = new PilotoCampeonato(0, 0, 0, false, piloto, campeonato);
				pcB.novoPiloto(pc);
			} else if (pilotoRepetido(piloto, campeonato) == true) {
				throw new Exception("Piloto já cadastrado nesse campeonato");
			}
			saveOrUpadte(pc);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public List<PilotoCampeonato> listar(String tipo, Piloto piloto, int id) throws Exception {
		try {
			if (tipo.equals("")) {
				GenericDao<PilotoCampeonato> kDao = new GenericDao<PilotoCampeonato>();
				return kDao.list(PilotoCampeonato.class);
			} else {
				PilotoCampeonatoDao pcD=new PilotoCampeonatoDao();
				return pcD.listar(tipo, piloto, id);
			}
		} catch (Exception e) {
			throw new Exception("Nenhum piloto do campeonato selecionado");
		}
	}

	private void validarDadosPilotoCampeonato(PilotoCampeonato pilotoCampeonato) throws Exception {
		if (pilotoCampeonato.getId() < 0) {
			throw new Exception("Id do pilotoCampeonato não pode ser negativo!");
		}

	}

	public void atualizarCampeonato(PilotoCorrida piloto) throws Exception {
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		PilotoCampeonato pc = listar("encontrar", piloto.getPiloto(), piloto.getCorridaCampeonato().getCampeonato().getId()).get(0);
		pc.setPontucao(pc.getPontucao() + pilotoCorridaBo.calcularPontuacao(piloto));
		saveOrUpadte(pc);
		atualizarPosicao(piloto);
	}

	private int[] carregarVetor(Campeonato campeonato) throws Exception {
		List<PilotoCampeonato> lista = listar("pilotoCampeonato", null, campeonato.getId());
		int[] vetor = new int[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			vetor[i] = lista.get(i).getPontucao();
		}
		return vetor;
	}

	public Map<Integer, Integer> insertSort(Campeonato campeonato) throws Exception {
		Map<Integer, Integer> listaMap = new HashMap<>();
		int vetor2[] = carregarVetor(campeonato);
		int j, key, i;
		for (j = 1; j < vetor2.length; j++) {
			key = vetor2[j];
			for (i = j - 1; (i >= 0) && (vetor2[i] < key); i--) {
				vetor2[i + 1] = vetor2[i];
			}
			vetor2[i + 1] = key;
		}
		for (int h = 0; h < vetor2.length; h++) {
			listaMap.put(vetor2[h], 1 + h);
		}
		return listaMap;
	}

	public void atualizarPosicao(PilotoCorrida pilotoCorrida) throws Exception {
		
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		for (PilotoCorrida piloto : pilotoCorridaBo.listar("", pilotoCorrida, 0)) {
			PilotoCampeonato pc =listar("encontrar", piloto.getPiloto(),
					piloto.getCorridaCampeonato().getCampeonato().getId()).get(0);
			pc.setPosicao(insertSort(piloto.getCorridaCampeonato().getCampeonato()).get(pc.getPontucao()));
			saveOrUpadte(pc);
		}
	}

	private boolean pilotoRepetido(Piloto piloto, Campeonato campeonato) throws Exception {
		try {
			if (listar("encontrar", piloto, campeonato.getId()).size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception("Erro no pilotoRepetido");
		}
	}
}
