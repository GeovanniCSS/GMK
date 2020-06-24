package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Convite;
import entity.Piloto;

public class ConviteDao {

	public List<Convite> listar(String tipo, Piloto piloto, int id) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = null;
		if (tipo.equals("convites")) {
			q = em.createQuery("SELECT c FROM Convite c WHERE c.piloto.id = :nome AND c.resposta = null ");
			q.setParameter("nome", piloto.getId());
		} else if (tipo.equals("repetido")) {
			q = em.createQuery(
					"SELECT c FROM Convite c INNER JOIN Piloto p ON" + " p.id=c.piloto.id INNER JOIN Campeonato cc ON"
							+ " c.campeonato.id=cc.id WHERE p.id= :parametro AND cc.id = :parametro2");
			q.setParameter("parametro", piloto.getId());
			q.setParameter("parametro2", id);
		} else if (tipo.equals("aceito")) {
			q = em.createQuery(
					"SELECT c FROM Convite c WHERE c.campeonato.id = :parametro AND c.resposta = true ORDER BY c.dataResposta");
			q.setParameter("parametro", id);
		} else if (tipo.equals("rejeitado")) {
			q = em.createQuery(
					"SELECT c FROM Convite c WHERE c.campeonato.id = :parametro AND c.resposta = false ORDER BY c.dataResposta");
			q.setParameter("parametro", id);
		} else if (tipo.equals("convidados")) {
			q = em.createQuery(
					"SELECT c FROM Convite c WHERE c.campeonato.id = :parametro AND c.resposta = null ORDER BY c.dataResposta");
			q.setParameter("parametro", id);
		}
		return q.getResultList();
	}

}
