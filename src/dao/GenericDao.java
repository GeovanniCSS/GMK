package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.EntityBase;

public class GenericDao<T extends EntityBase> {

	private static EntityManager em = Fabrica.getEntityManager();

	public void saveOrUpdate(T obj) throws Exception {
		EntityManager em = Fabrica.getEntityManager();
		try {
			em.getTransaction().begin();
			if (obj.getId() == 0) {
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			throw new Exception("Erro persist ou merge \n"+e.getMessage());
		}
	}
	public void remove(Class<T> classe, int id) throws Exception{
		T t = findById(classe, id);
		try{
			em.getTransaction().begin();
			em.remove(t);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception("Erro Deletando \n"+e.getMessage());
		}		
	}
	public T findById(Class<T> classe, int id) {
		return em.find(classe, id);
	}		
	public List<T> list(Class<T> classe) {		
		EntityManager em = Fabrica.getEntityManager();
		
		Query q = em.createQuery("select t from "
				+classe.getSimpleName().toString()+" t");		
		return q.getResultList();
	}	
}
