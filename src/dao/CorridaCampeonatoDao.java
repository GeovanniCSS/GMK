package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import bo.CorridaCampeonatoBo;
import entity.CorridaCampeonato;

public class CorridaCampeonatoDao {

	public List<CorridaCampeonato> lista(String tipo, CorridaCampeonato corridaCampeonato, int id)
			throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		Query q = null;

		if (tipo.equals("listaKartodromo")) {
			q = em.createQuery("SELECT cc FROM CorridaCampeonato cc INNER JOIN Kartodromo k "
					+ "ON cc.kartodromo.id=k.id WHERE k.id= :parametro AND cc.data >= :parametro2 ORDER BY cc.data");
			q.setParameter("parametro", id);
			q.setParameter("parametro2", LocalDate.now());
		} else if (tipo.equals("listaPiloto")) {
			q = em.createQuery("SELECT cc FROM CorridaCampeonato cc INNER JOIN PilotoCorrida p "
					+ "ON cc.id=p.corridaCampeonato.id WHERE p.piloto.id= :parametro AND cc.data >= :parametro2 ORDER BY cc.data");
			q.setParameter("parametro", id);
			q.setParameter("parametro2",LocalDate.now());
		} else if (tipo.equals("listaCampeonato")) {
			q = em.createQuery("SELECT cc FROM CorridaCampeonato cc INNER JOIN Campeonato c "
					+ "ON cc.campeonato.id=c.id WHERE c.id= :parametro  AND cc.data >= :parametro2 ORDER BY cc.data");
			q.setParameter("parametro", id);
			q.setParameter("parametro2",LocalDate.now());
		} else if (tipo.equals("corridaCompleta")) {
			q = em.createQuery("SELECT cc FROM CorridaCampeonato cc INNER JOIN Campeonato c "
					+ "ON cc.campeonato.id=c.id WHERE c.id= :parametro  AND cc.data < :parametro2");
			q.setParameter("parametro", id);
			q.setParameter("parametro2",LocalDate.now());
		} else if (tipo.equals("numeroCorrida")) {
			q = em.createQuery("SELECT cc FROM CorridaCampeonato cc INNER JOIN Campeonato c "
					+ "ON cc.campeonato.id=c.id WHERE c.id= :parametro");
			q.setParameter("parametro", id);
		} else if (tipo.equals("corridaMarcada")) {
			q = em.createQuery("SELECT c FROM CorridaCampeonato c INNER JOIN Kartodromo k "
					+ "ON c.kartodromo.id=k.id WHERE k.id= :parametro AND c.hora = :parametro2 AND c.data =:parametro3");
			q.setParameter("parametro", corridaCampeonato.getKartodromo().getId());
			q.setParameter("parametro2", corridaCampeonato.getHora());
			q.setParameter("parametro3", corridaCampeonato.getData());
		} else if (tipo.equals("mesmoHorario")) {
			q = em.createQuery("SELECT cc FROM CorridaCampeonato cc INNER JOIN Campeonato c "
					+ "ON cc.campeonato.id=c.id WHERE c.id= :parametro AND cc.hora = :parametro2 AND cc.data =:parametro3");
			q.setParameter("parametro", corridaCampeonato.getCampeonato().getId());
			q.setParameter("parametro2", corridaCampeonato.getHora());
			q.setParameter("parametro3", corridaCampeonato.getData());
		} else if(tipo.equals("nomeCorrida")) {
			q = em.createQuery("SELECT cc FROM CorridaCampeonato cc INNER JOIN Campeonato c "
					+ "ON cc.campeonato.id=c.id WHERE c.id= :parametro AND cc.nome LIKE :parametro2");
			q.setParameter("parametro", id);
			q.setParameter("parametro2", corridaCampeonato.getNome());
		} else if (tipo.equals("informacoes")) {
			q = em.createQuery("SELECT cc FROM CorridaCampeonato cc where cc.nome = :parametro");
			q.setParameter("parametro", corridaCampeonato.getNome());
		}
		return q.getResultList();
	}

}
