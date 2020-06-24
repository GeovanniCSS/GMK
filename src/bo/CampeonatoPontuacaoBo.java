package bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.CampeonatoPontuacaoDao;
import dao.GenericDao;
import entity.Campeonato;
import entity.CampeonatoPontuacao;

public class CampeonatoPontuacaoBo {

	public void saveOrUpadte(CampeonatoPontuacao CampeonatoPontuacao) throws Exception {
		validarDadosCampeonatoPontuacao(CampeonatoPontuacao);

		GenericDao<CampeonatoPontuacao> gDao = new GenericDao<CampeonatoPontuacao>();
		try {
			gDao.saveOrUpdate(CampeonatoPontuacao);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(CampeonatoPontuacao CampeonatoPontuacao) throws Exception {
		validarDadosCampeonatoPontuacao(CampeonatoPontuacao);

		GenericDao<CampeonatoPontuacao> gDao = new GenericDao<CampeonatoPontuacao>();
		try {
			gDao.remove(CampeonatoPontuacao.class, CampeonatoPontuacao.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<CampeonatoPontuacao> listar(String tipo, CampeonatoPontuacao cp, int id) throws Exception {
		try {
			if (tipo.equals("")) {
				GenericDao<CampeonatoPontuacao> kDao = new GenericDao<CampeonatoPontuacao>();
				return kDao.list(CampeonatoPontuacao.class);
			} else {
				CampeonatoPontuacaoDao campeonatoPontuacaoDao = new CampeonatoPontuacaoDao();
				return campeonatoPontuacaoDao.lista(tipo, cp, id);
			}
		} catch (Exception e) {
			throw new Exception("Nenhuma pontuação");
		}
	}

	public void salvarPontuacao(CampeonatoPontuacao campeonatoPontuacao) throws Exception {
		validarDadosCampeonatoPontuacao(campeonatoPontuacao);
		logicaPosicao(campeonatoPontuacao);
		novaPontuacao(campeonatoPontuacao);
		if (listar("repetido", campeonatoPontuacao, campeonatoPontuacao.getCampeonato().getId()).size() != 0) {
			campeonatoPontuacao.setId(listar("repetido", campeonatoPontuacao, 
					campeonatoPontuacao.getCampeonato().getId()).get(0).getId());
			saveOrUpadte(campeonatoPontuacao);
		} else {
			saveOrUpadte(campeonatoPontuacao);
		}

	}

	private void validarDadosCampeonatoPontuacao(CampeonatoPontuacao CampeonatoPontuacao) throws Exception {
		if (CampeonatoPontuacao.getId() < 0) {
			throw new Exception("Id do CampeonatoPontuacao não pode ser negativo!");
		}else if(CampeonatoPontuacao.getPontuacao()<=0) {
			throw new Exception("A pontuação é inválida");
		}else if(CampeonatoPontuacao.getPosicao()<=0) {
			throw new Exception("A posição é inválida");
		}
	}

	public Map<Integer, Integer> pontosDoCampeonato(Campeonato campeonato) throws Exception {
		Map<Integer, Integer> pontos = new HashMap<>();
		for (CampeonatoPontuacao cp : listar("todosCampeonato", null, campeonato.getId())) {
			pontos.put(cp.getPosicao(), cp.getPontuacao());
		}
		return pontos;
	}

	public void logicaPosicao(CampeonatoPontuacao campeonatoPontuacao) throws Exception {
		if (listar("pontuacaoMaior", campeonatoPontuacao, 0).size() > 0) {
			throw new Exception("A pontuação varia de acordo com a posição,os pontos devem seguir uma ordem");
		}
	}
	public void novaPontuacao(CampeonatoPontuacao campeonatoPontuacao) throws Exception {
		if (listar("novaMenor", campeonatoPontuacao, 0).size() > 0) {
			throw new Exception("Para mudar a pontuação dessa posição é necessário um valor maior");
		}
	}
}
