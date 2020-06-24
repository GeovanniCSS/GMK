package bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import dao.GenericDao;
import dao.PilotoCorridaDao;
import entity.Campeonato;
import entity.CorridaCampeonato;
import entity.Piloto;
import entity.PilotoCampeonato;
import entity.PilotoCorrida;

public class PilotoCorridaBo {

	public void saveOrUpadte(PilotoCorrida pilotoCorrida) throws Exception {
		validarDadosPilotoCorrida(pilotoCorrida);

		GenericDao<PilotoCorrida> gDao = new GenericDao<PilotoCorrida>();
		try {
			gDao.saveOrUpdate(pilotoCorrida);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(PilotoCorrida pilotoCorrida) throws Exception {
		validarDadosPilotoCorrida(pilotoCorrida);

		GenericDao<PilotoCorrida> gDao = new GenericDao<PilotoCorrida>();
		try {
			gDao.remove(PilotoCorrida.class, pilotoCorrida.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<PilotoCorrida> listar(String tipo, PilotoCorrida pilotoCorrida, int id) throws Exception {
		try {
			if (tipo.equals("")) {
				GenericDao<PilotoCorrida> kDao = new GenericDao<PilotoCorrida>();
				return kDao.list(PilotoCorrida.class);
			} else {
				PilotoCorridaDao pilotoCorridaDao = new PilotoCorridaDao();
				return pilotoCorridaDao.listar(tipo, pilotoCorrida, id);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void chamarPiloto(CorridaCampeonato corridaCampeonato) throws Exception {
		try {
			PilotoCampeonatoBo pilotoCampeonatoBo = new PilotoCampeonatoBo();
			for (PilotoCampeonato piloto : pilotoCampeonatoBo.listar("pilotoCampeonato", null,
					corridaCampeonato.getCampeonato().getId())) {
				saveOrUpadte(new PilotoCorrida(0, 0, 0, false, false, false, tempoCorrida(""), piloto.getPiloto(),
						corridaCampeonato));
			}
		} catch (Exception e) {
			throw new Exception("Erro ao chamar os pilotos para corrida");
		}
	}

	public void salvarResultados(String tipo, PilotoCorrida pilotoCorrida) throws Exception {
		CorridaCampeonatoBo ccB = new CorridaCampeonatoBo();
		PilotoCampeonatoBo pilotoCampeonatoBo = new PilotoCampeonatoBo();
		if (listar("posicao", pilotoCorrida, pilotoCorrida.getCorridaCampeonato().getId()).size() > 0) {
			throw new Exception("Posição já registrada");
		}
		try {
			saveOrUpadte(pilotoCorrida);
			pilotoCampeonatoBo.atualizarCampeonato(pilotoCorrida);
			ccB.mudarCorrida(pilotoCorrida.getCorridaCampeonato());
		} catch (Exception e) {
			throw new Exception("Erro ao salvar resultado");
		}
	}

	public void pegarResultado(String largada, String chegada, String tempoCorrida, boolean VMR, boolean LM,
			boolean ausente, PilotoCorrida pilotoCorrida) throws Exception {
		posicaoPiloto(pilotoCorrida);
		if (ausente) {
			pilotoCorrida.setAusente(true);
			salvarResultados("todosCorrida", pilotoCorrida);
		} else if (chegada.equals("") | chegada.equals("0")) {
			throw new Exception("Posição de chegada inválida");
		} else if (tempoCorrida.equals("") | tempoCorrida.equals("0")) {
			throw new Exception("Tempo de corrida inválido");
		} else if (largada.equals("") | largada.equals("0")) {
			throw new Exception("Posição de largada inválida");
		} else {
			pilotoCorrida.setTempoCorrida(tempoCorrida(tempoCorrida));
			pilotoCorrida.setChegada(Integer.parseInt(chegada));
			pilotoCorrida.setLargada(Integer.parseInt(largada));
			pilotoCorrida.setvMRapida(VMR);
			pilotoCorrida.setpLideranca(LM);
			salvarResultados("todosCorrida", pilotoCorrida);
		}
	}

	private void validarDadosPilotoCorrida(PilotoCorrida pilotoCorrida) throws Exception {
		if (pilotoCorrida.getId() < 0) {
			throw new Exception("Id do pilotoCorrida não pode ser negativo!");
		}

	}

	public int calcularPontuacao(PilotoCorrida pilotoCorrida) throws Exception {
		CampeonatoBo campeonatoBo = new CampeonatoBo();
		CampeonatoPontuacaoBo campeonatoPontuacaoBo = new CampeonatoPontuacaoBo();
		PilotoCorrida pc = listar("piloto", pilotoCorrida, pilotoCorrida.getCorridaCampeonato().getId()).get(0);
		Campeonato campeonato = campeonatoBo
				.listar("selecionado", null, pilotoCorrida.getCorridaCampeonato().getCampeonato().getId()).get(0);
		Map<Integer, Integer> pontos = campeonatoPontuacaoBo
				.pontosDoCampeonato(pilotoCorrida.getCorridaCampeonato().getCampeonato());

		int pontuacao = 0;
		if (pc.getAusente()) {
			return pontuacao;
		}
		if (pc.getpLideranca()) {
			pontuacao += campeonato.getpLideranca();
		}
		if (pc.getvMRapida()) {
			pontuacao += campeonato.getpVMRapida();
		}
		if (pc.getLargada() == 1) {
			pontuacao += campeonato.getPole();
		}
		if (pontos.containsKey(pc.getChegada())) {
			pontuacao += pontos.get(pc.getChegada());
		}
		return pontuacao;
	}

	private void posicaoPiloto(PilotoCorrida pilotoCorrida) throws Exception {
		PilotoCampeonatoBo pilotoCampeonatoBo = new PilotoCampeonatoBo();
		if (pilotoCorrida.getChegada() > pilotoCampeonatoBo
				.listar("", pilotoCorrida.getPiloto(), pilotoCorrida.getCorridaCampeonato().getCampeonato().getId())
				.size()
				|| pilotoCorrida.getChegada() > pilotoCorrida.getCorridaCampeonato().getKartodromo().getNumMax()) {
			throw new Exception("Posição maior que o número de posições disponíveis");
		}

	}

	public int calcularVitorias(String tipo, Piloto piloto, Campeonato campeonato) throws Exception {
		PilotoCorrida pc = new PilotoCorrida(0, 0, 0, false, false, false, tempoCorrida(""), piloto, null);
		if (tipo.equals("")) {
			return 0;
		} else {
			return listar(tipo, pc, campeonato.getId()).size();
		}

	}

	public String sinal(boolean sinal) {
		if (sinal) {
			return "+";
		} else {
			return "-";
		}
	}

	private static LocalTime tempoCorrida(String tempo) throws Exception {
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		try {
			if (tempo.equals("")) {
				return LocalTime.parse("00:00:00", formatterHora);
			} else {
				return LocalTime.parse(tempo, formatterHora);
			}
		} catch (Exception e) {
			throw new Exception("Erro ao salvar o tempo de corrida");
		}
	}

	public void novoPiloto(PilotoCampeonato pilotoCampeonato) throws Exception {
		CorridaCampeonatoBo corridaCampeonatoBo = new CorridaCampeonatoBo();
		List<CorridaCampeonato> lista = corridaCampeonatoBo.listar("numeroCorrida", null,
				pilotoCampeonato.getCampeonato().getId());
		if (lista.size() > 0) {
			for (CorridaCampeonato cc : lista) {
				if (cc.getData().isAfter(LocalDate.now())
						&& listar("todosCorrida", null, cc.getId()).size() <= cc.getKartodromo().getNumMax()) {
					saveOrUpadte(new PilotoCorrida(0, 0, 0, false, false, false, tempoCorrida(""),
							pilotoCampeonato.getPiloto(), cc));
				}
			}
		}
	}

	public void apagarPilotoDaCorrida(CorridaCampeonato corridaCampeonato) throws Exception {
		for (PilotoCorrida pilotoCorrida : listar("todosCorrida", null, corridaCampeonato.getId())) {
			deletar(pilotoCorrida);
		}

	}
}
