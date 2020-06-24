package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.PilotoCorrida;

public class PilotoCorridaDao {
	public List<PilotoCorrida> listar(String tipo, PilotoCorrida pilotoCorrida, int id) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = null;
		if (tipo.equals("todosCorrida")) {
			q = em.createQuery(
					"SELECT p FROM PilotoCorrida p WHERE p.corridaCampeonato.id = :parametro  AND p.chegada = 0 AND "
							+ "p.ausente = false ORDER BY p.piloto.login");
			q.setParameter("parametro", id);
		} else if (tipo.equals("salvos")) {
			q = em.createQuery(
					"SELECT p FROM PilotoCorrida p WHERE p.corridaCampeonato.id = :parametro AND p.chegada != 0 ORDER BY p.chegada");
			q.setParameter("parametro", id);
		} else if (tipo.equals("piloto")) {
			q = em.createQuery("SELECT p FROM PilotoCorrida p WHERE p.id = :parametro");
			q.setParameter("parametro", pilotoCorrida.getId());
		} else if (tipo.equals("posicao")) {
			q = em.createQuery(
					"SELECT p FROM PilotoCorrida p WHERE (p.corridaCampeonato.id = :parametro) AND (p.chegada= :parametro2  "
							+ " OR p.largada= :parametro3)");
			q.setParameter("parametro", id);
			q.setParameter("parametro2", pilotoCorrida.getChegada());
			q.setParameter("parametro3", pilotoCorrida.getLargada());
		} else if (tipo.equals("VMR")) {
			q = em.createQuery(
					"SELECT p FROM PilotoCorrida p WHERE p.piloto.id= :parametro AND p.corridaCampeonato.campeonato.id= :parametro2 AND p.vMRapida= true");
			q.setParameter("parametro", pilotoCorrida.getPiloto().getId());
			q.setParameter("parametro2", id);
		} else if (tipo.equals("ML")) {
			q = em.createQuery(
					"SELECT p FROM PilotoCorrida p WHERE p.piloto.id = :parametro AND p.corridaCampeonato.campeonato.id= :parametro2 AND p.pLideranca= true");
			q.setParameter("parametro", pilotoCorrida.getPiloto().getId());
			q.setParameter("parametro2", id);
		} else if (tipo.equals("primeiro")) {
			q = em.createQuery(
					"SELECT p FROM PilotoCorrida p WHERE p.piloto.id = :parametro AND p.corridaCampeonato.campeonato.id= :parametro2 AND p.chegada= 1");
			q.setParameter("parametro", pilotoCorrida.getPiloto().getId());
			q.setParameter("parametro2", id);
		} else if (tipo.equals("Pole")) {
			q = em.createQuery(
					"SELECT p FROM PilotoCorrida p WHERE p.piloto.id = :parametro AND p.corridaCampeonato.campeonato.id= :parametro2 AND p.largada=1");
			q.setParameter("parametro", pilotoCorrida.getPiloto().getId());
			q.setParameter("parametro2", id);
		}
		return q.getResultList();
	}

}
