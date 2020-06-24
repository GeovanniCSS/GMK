package bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.CorridaCampeonatoDao;
import dao.GenericDao;
import entity.Campeonato;
import entity.CorridaCampeonato;
import entity.Kartodromo;
import entity.Piloto;

public class CorridaCampeonatoBo {

	public void saveOrUpadte(CorridaCampeonato corridaCampeonato) throws Exception {
	
		GenericDao<CorridaCampeonato> gDao = new GenericDao<CorridaCampeonato>();
		try {
			gDao.saveOrUpdate(corridaCampeonato);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(CorridaCampeonato corridaCampeonato) throws Exception {
		
		GenericDao<CorridaCampeonato> gDao = new GenericDao<CorridaCampeonato>();
		try {
			gDao.remove(CorridaCampeonato.class, corridaCampeonato.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<CorridaCampeonato> listar(String tipo, CorridaCampeonato corridaCampeonato, int id)
			throws Exception {
		try {
			if (tipo.equals("")) {
				GenericDao<CorridaCampeonato> ccDao = new GenericDao<CorridaCampeonato>();
				return ccDao.list(CorridaCampeonato.class);
			} else {
				CorridaCampeonatoDao corridaCampeonatoDao=new CorridaCampeonatoDao();
				return corridaCampeonatoDao.lista(tipo, corridaCampeonato, id);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void marcarCorrida(String nome, LocalDate data, String horario, Kartodromo k, Piloto piloto,
			Campeonato campeonato) throws Exception {
		CorridaCampeonato cc = new CorridaCampeonato(0, nome, LocalDateTime.now(), data, LocalTime.parse(horario), true,
				campeonato, k, piloto);
		if (nome.equals("")) {
			throw new Exception("Nome da corrida não pode ficar em branco!");
		} else if (horario.equals("")) {
			throw new Exception("Horario em Branco!");
		} else if (numeroDePilotos(cc)) {
			throw new Exception("O número de pilotos não está de acordo com o previsto pelo " + k.getNome() + "!");
		//}else if (cc.getData().isBefore(LocalDate.now())) {
			//throw new Exception("O data desta corrida já passou");
		}
		try {
			salvarCorrida(cc);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	public void apagarCorrida(CorridaCampeonato corridaCampeonato) throws Exception {
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		try {
			pilotoCorridaBo.apagarPilotoDaCorrida(corridaCampeonato);
			deletar(corridaCampeonato);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falha ao apagar a corrida");
		}
	}
	
	private void validarDadosCorridaCampeonato(CorridaCampeonato corridaCampeonato) throws Exception {
		if (corridaCampeonato.getId() < 0) {
			throw new Exception("Id do corridaCampeonato não pode ser negativo!");
		}
		if (corridaCampeonato.getNome().equals("")) {
			throw new Exception("Nome do corridaCampeonato não pode ficar em branco!");
		}
	}

	private boolean numeroCorridas(Campeonato campeonato) throws Exception {
		if (listar("numeroCorrida", null, campeonato.getId()).size() == campeonato
				.getTotalCorridas()) {
			throw new Exception("O número de corridas já atingiu o limite");
		} else {
			return false;
		}
	}

	private boolean numeroDePilotos(CorridaCampeonato corridaCampeonato) throws Exception {
		PilotoCampeonatoBo pilotoCampeonatoBo=new PilotoCampeonatoBo();
		KartodromoBo kartodromoBo=new KartodromoBo();
		Kartodromo k = kartodromoBo.listar("encontrar", corridaCampeonato.getKartodromo(),0).get(0);
		int total = pilotoCampeonatoBo.listar("pilotoCampeonato", null, corridaCampeonato.getCampeonato().getId())
				.size();
		if (k.getNumMax() < total || k.getNumMinimo() > total) {
			return true;
		}
		return false;
	}

	private void corridaMarcada(CorridaCampeonato corridaCampeonato) throws Exception {
		if (listar("corridaMarcada", corridaCampeonato, 0).size() > 0) {
			throw new Exception("Já tem uma corrida marcada nesse Kartódromo nessa data e hórario");
		}
	}
	private void mesmoHorario(CorridaCampeonato corridaCampeonato) throws Exception {
		if (listar("mesmoHorario", corridaCampeonato, 0).size() > 0) {
			throw new Exception("Já tem uma corrida marcada nesse hórario nesse campeonato");
		}
	}

	private void salvarCorrida(CorridaCampeonato corridaCampeonato) throws Exception {
		PilotoCorridaBo pilotoCorridaBo=new PilotoCorridaBo();
		numeroCorridas(corridaCampeonato.getCampeonato());
		nomeCorrida(corridaCampeonato);
		mesmoHorario(corridaCampeonato);
		corridaMarcada(corridaCampeonato);
		saveOrUpadte(corridaCampeonato);
		pilotoCorridaBo.chamarPiloto(corridaCampeonato);
	}

	public void mudarCorrida(CorridaCampeonato corridaCampeonato) throws Exception {
		corridaCampeonato.setSituacao(false);
		saveOrUpadte(corridaCampeonato);
	}

	public static String dataTela(LocalDate date) {
		String data = String.valueOf(date);
		String vetor[] = data.split("-");
		String resultado = vetor[2] + "/" + vetor[1] + "/" + vetor[0];
		return resultado;

	}

	public static List<String> dia() {
		List<String> lista = new ArrayList<String>();
		for (int i = 1; i < 32; i++) {
			if (i < 10) {
				lista.add("0" + i);
			} else {
				lista.add("" + i);
			}
		}
		return lista;
	}

	public static List<String> mes() {
		List<String> lista = new ArrayList<String>();
		for (int i = 1; i < 13; i++) {
			if (i < 10) {
				lista.add("0" + i);
			} else {
				lista.add("" + i);
			}
		}
		return lista;
	}

	public static List<Integer> ano() {
		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 2020; i < 2022; i++) {
			lista.add(i);
		}
		return lista;
	}

	public static LocalDate dataCorrida(String dia, String mes, int ano) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		return LocalDate.parse(ano + "-" + mes + "-" + dia, formatador);
	}

	private void nomeCorrida(CorridaCampeonato corrida) throws Exception {
		if (listar("nomeCorrida", corrida, corrida.getCampeonato().getId()).size() > 0) {
			throw new Exception("Já existe uma bateria nesse campeonato com esse nome");
		}
	}
}
