package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Fabrica {
	
	private static EntityManagerFactory em=Persistence.createEntityManagerFactory("folder");
	
	public static EntityManager getEntityManager() {
		return em.createEntityManager();
	}
	public static void close() {
		em.close();
	}
}
