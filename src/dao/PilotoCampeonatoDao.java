package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Piloto;
import entity.PilotoCampeonato;

public class PilotoCampeonatoDao {

	public List<PilotoCampeonato> listar(String tipo, Piloto piloto, int id) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q = null;
		if (tipo.equals("todos")) {
			q = em.createQuery("SELECT pc FROM PilotoCampeonato pc INNER JOIN Piloto p "
					+ "ON pc.piloto.id=p.id INNER JOIN Campeonato c ON pc.campeonato.id=c.id");
		} else if (tipo.equals("encontrar")) {
			q = em.createQuery("SELECT pc FROM PilotoCampeonato pc INNER JOIN Piloto p "
					+ "ON pc.piloto.id=p.id INNER JOIN Campeonato c "
					+ "ON pc.campeonato.id=c.id WHERE p.id= :parametro AND c.id= :parametro2 ");
			q.setParameter("parametro", piloto.getId());
			q.setParameter("parametro2", id);
		}else if(tipo.equals("pilotoCampeonato")) {
			q = em.createQuery("SELECT pc FROM PilotoCampeonato pc INNER JOIN Piloto p "
					+ "ON pc.piloto.id=p.id INNER JOIN Campeonato c ON pc.campeonato.id=c.id WHERE c.id = :parametro ORDER BY pc.posicao");
			q.setParameter("parametro", id);
		}
		return q.getResultList();
	}

}
