package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.CampeonatoPontuacao;

public class CampeonatoPontuacaoDao {

	public List<CampeonatoPontuacao> lista(String tipo, CampeonatoPontuacao campeonatoPontuacao, int id) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = null;
		if (tipo.equals("todosCampeonato")) {
			q = em.createQuery("SELECT c FROM CampeonatoPontuacao c WHERE c.campeonato.id = :parametro ORDER BY c.posicao");
			q.setParameter("parametro", id);
		} else if (tipo.equals("repetido")) {
			q = em.createQuery(
					"SELECT c FROM CampeonatoPontuacao c WHERE c.campeonato.id = :parametro AND c.posicao = :parametro2");
			q.setParameter("parametro", campeonatoPontuacao.getCampeonato().getId());
			q.setParameter("parametro2", campeonatoPontuacao.getPosicao());
		} else if (tipo.equals("pontuacaoMaior")) {
			q = em.createQuery(
					"SELECT c FROM CampeonatoPontuacao c WHERE c.campeonato.id = :parametro AND c.posicao < :parametro2 "
					+ "AND c.pontuacao <= :parametro3");
			q.setParameter("parametro", campeonatoPontuacao.getCampeonato().getId());
			q.setParameter("parametro2", campeonatoPontuacao.getPosicao());
			q.setParameter("parametro3", campeonatoPontuacao.getPontuacao());
		} else if (tipo.equals("novaMenor")) {
			q = em.createQuery(
					"SELECT c FROM CampeonatoPontuacao c WHERE c.campeonato.id = :parametro AND c.posicao > :parametro2 "
					+ "AND c.pontuacao >= :parametro3");
			q.setParameter("parametro", campeonatoPontuacao.getCampeonato().getId());
			q.setParameter("parametro2", campeonatoPontuacao.getPosicao());
			q.setParameter("parametro3", campeonatoPontuacao.getPontuacao());
		}

		return q.getResultList();
	}
}
