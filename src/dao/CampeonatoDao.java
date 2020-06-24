package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Campeonato;
import entity.Piloto;

public class CampeonatoDao {

	public List<Campeonato> lista(String tipo, Campeonato campeonato, int id) {
		EntityManager em = Fabrica.getEntityManager();
		Query q = null;
		if (tipo.equals("repetido")) {
			q = em.createQuery("SELECT c FROM Campeonato c WHERE c.nome = :nomeC ");
			q.setParameter("nomeC", campeonato.getNome());
		} else if (tipo.equals("administrar") && campeonato.getNome().equals("")) {
			q = em.createQuery("SELECT c FROM Campeonato c INNER JOIN PilotoCampeonato pc ON "
					+ "c.id=pc.campeonato.id INNER JOIN Piloto p ON "
					+ "pc.piloto.id=p.id WHERE p.id = :parametro AND pc.administrador=true ORDER BY dataCadastrado DESC");
			q.setParameter("parametro", id);
		} else if (tipo.equals("administrar")) {
			q = em.createQuery("SELECT c FROM Campeonato c INNER JOIN PilotoCampeonato pc ON "
					+ "c.id=pc.campeonato.id INNER JOIN Piloto p ON "
					+ "pc.piloto.id=p.id WHERE p.id = :parametro AND pc.administrador=true AND c.nome LIKE :parametro2 "
					+ "ORDER BY dataCadastrado DESC");
			q.setParameter("parametro", id);
			q.setParameter("parametro2", "%" + campeonato.getNome().toUpperCase() + "%");
		} else if (tipo.equals("selecionado")) {
			q = em.createQuery("SELECT c FROM Campeonato c WHERE c.id= :parametro");
			q.setParameter("parametro", id);
		} else if (tipo.equals("participo") && campeonato.getNome().equals("")) {
			q = em.createQuery("SELECT DISTINCT c FROM Campeonato c INNER JOIN PilotoCampeonato pc ON "
					+ "c.id=pc.campeonato.id INNER JOIN Piloto p ON "
					+ "pc.piloto.id=p.id WHERE p.id= :parametro ORDER BY dataCadastrado DESC");
			q.setParameter("parametro", id);
		} else if (tipo.equals("participo")) {
			q = em.createQuery("SELECT DISTINCT c FROM Campeonato c INNER JOIN PilotoCampeonato pc ON "
					+ "c.id=pc.campeonato.id INNER JOIN Piloto p ON "
					+ "pc.piloto.id=p.id WHERE p.id= :parametro AND c.nome LIKE :parametro2 ORDER BY dataCadastrado DESC");
			q.setParameter("parametro", id);
			q.setParameter("parametro2", "%" + campeonato.getNome().toUpperCase() + "%");
		}
		return q.getResultList();
	}
}
