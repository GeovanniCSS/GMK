package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Piloto;

public class PilotoDao {

	public List<Piloto> listar(String tipo, Piloto piloto, int id) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q = null;
		if (tipo.equals("cadastro")) {
			q = em.createQuery("SELECT p FROM Piloto p WHERE p.login = :nome");
			q.setParameter("nome", piloto.getLogin());
		} else if (tipo.equals("login")) {
			q = em.createQuery("SELECT p FROM Piloto p WHERE p.login = :nome AND p.senha = :nome2 ");
			q.setParameter("nome", piloto.getLogin());
			q.setParameter("nome2", piloto.getSenha());
		} else if (tipo.equals("campeonato")) {
			q = em.createQuery("SELECT p FROM Piloto p INNER JOIN Convite pc ON "
					+ "p.id=pc.piloto.id INNER JOIN Campeonato c ON  pc.campeonato.id=c.id WHERE c.id = :parametro");
			q.setParameter("parametro", id);
		} else if (tipo.equals("corrida")) {
			q = em.createQuery("SELECT p FROM Piloto p INNER JOIN CorridaCampeonato cc ON "
					+ "p.id=cc.piloto.id INNER JOIN Campeonato c ON  cc.campeonato.id=c.id WHERE cc.id = :parametro");
			q.setParameter("parametro", id);
		} else {
			q = em.createQuery("SELECT p FROM Piloto p WHERE p.login LIKE :nome");
			q.setParameter("nome", "%" + tipo.toUpperCase() + "%");
		}
		return q.getResultList();
	}
}
