package bo;

import java.time.LocalDate;
import java.util.List;

import dao.ConviteDao;
import dao.GenericDao;
import entity.Campeonato;
import entity.Convite;
import entity.Piloto;

public class ConviteBo {

	public void saveOrUpadte(Convite convite) throws Exception {
		validarDadosConvite(convite);

		GenericDao<Convite> gDao = new GenericDao<Convite>();
		try {
			gDao.saveOrUpdate(convite);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void deletar(Convite convite) throws Exception {
		validarDadosConvite(convite);

		GenericDao<Convite> gDao = new GenericDao<Convite>();
		try {
			gDao.remove(Convite.class, convite.getId());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Convite> listar(String tipo, Piloto piloto, int id) throws Exception {
		try {
			if (tipo.equals("")) {
				GenericDao<Convite> cDao = new GenericDao<Convite>();
				return cDao.list(Convite.class);
			} else {
				ConviteDao conviteDao=new ConviteDao();
				return conviteDao.listar(tipo, piloto, id);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void salvarConvite(String tipo, Piloto piloto, Campeonato campeonato) throws Exception {
		try {
			if (listar(tipo, piloto, campeonato.getId()).size() == 0) {
				saveOrUpadte(new Convite(0, null, LocalDate.now(), campeonato, piloto));
			} else {
				throw new Exception("Piloto já recebeu convite");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void respostaConvite(String resposta, int c, Piloto piloto) throws Exception {
		PilotoCampeonatoBo pilotoCampeonatoBo=new PilotoCampeonatoBo();
		if (c >= 0) {
			Convite convite = listar("convites", piloto, 0).get(c);
			convite.setDataR(LocalDate.now());
			if (resposta.equals("aceito")) {
				convite.setResposta(true);
				saveOrUpadte(convite);
				pilotoCampeonatoBo.salvarPiloto("novo", piloto, convite.getCampeonato(), null);
			} else if (resposta.equals("rejeitado")) {
				convite.setResposta(false);
				saveOrUpadte(convite);
			}
		} else {
			throw new Exception("Nenhum convite selecionado");
		}
	}

	private void validarDadosConvite(Convite convite) throws Exception {
		if (convite.getId() < 0) {
			throw new Exception("Id do convite não pode ser negativo!");
		}
	}
}
