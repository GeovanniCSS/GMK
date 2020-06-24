package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Kartodromo;

public class KartodromoDao {
	
	public List<Kartodromo> listar(String tipo, Kartodromo kartodromo, int id) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q = null;
		if(tipo.equals("encontrar")) {
			q = em.createQuery("SELECT k FROM Kartodromo k WHERE k.id= :parametro");
			q.setParameter("parametro", kartodromo.getId());
		}else if (tipo.equals("cadastro")) {
			q = em.createQuery("SELECT p FROM Kartodromo p WHERE p.nome = :nome");
			q.setParameter("nome", kartodromo.getNome());
		} else if (tipo.equals("login")) {
			q = em.createQuery("SELECT p FROM Kartodromo p WHERE p.nome = :nome AND p.senha = :nome2 ");
			q.setParameter("nome", kartodromo.getNome());
			q.setParameter("nome2", kartodromo.getSenha());
		} else if (tipo.equals("campeonato")) {
			q = em.createQuery("SELECT p FROM Kartodromo p INNER JOIN Convite pc ON "
					+ "p.id=pc.piloto.id INNER JOIN Campeonato c ON  pc.campeonato.id=c.id WHERE c.id = :parametro");
			q.setParameter("parametro", id);
		} else if (tipo.equals("corrida")) {
			q = em.createQuery("SELECT p FROM Kartodromo p INNER JOIN CorridaCampeonato cc ON "
					+ "p.id=cc.piloto.id INNER JOIN Campeonato c ON  cc.campeonato.id=c.id WHERE cc.id = :parametro");
			q.setParameter("parametro", id);
		} else {
			q = em.createQuery("SELECT p FROM Kartodromo p WHERE p.login LIKE :nome");
			q.setParameter("nome", "%" + tipo.toUpperCase() + "%");
		}
		return q.getResultList();
	}

}
