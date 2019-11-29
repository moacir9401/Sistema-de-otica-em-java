
/**
 * Moacir Afonso Alves
 */

package DAO;

import java.util.ArrayList;
import javax.persistence.*;

public class Conexao {

	private static EntityManagerFactory emf;

	private static EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("otica");
		}
		return emf.createEntityManager();
	}

	public static void Save(Object obj) {

		EntityManager em = getEntityManager();

		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();

		em.close();
	}

	public static void Update(Object obj) {

		EntityManager em = getEntityManager();
		try {

			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
		} 
		catch (Exception e) {
			em.getTransaction().rollback();

		} finally {
			em.close();
		}
	}

	public static Object Show(Object obj, int id) {
		
		EntityManager em = getEntityManager();
		
		return em.find(obj.getClass(), id);
		
	}

	public static void Remove(Object obj, int id) {
		// TODO Realiza a exclus�o de de um registro no banco, fazendo a busca pelo id
		EntityManager em = getEntityManager();

		obj = em.find(obj.getClass(), id);
		em.getTransaction().begin();
		em.remove(obj);
		em.getTransaction().commit();

		em.close();

	}

	public static ArrayList<?> Show(String sqlQuery) {
		// TODO realiza a consulta no banco de dados e retorna em um arrayList
		ArrayList<?> result;
		EntityManager em = getEntityManager();

		Query query = em.createQuery(sqlQuery);
		result = (ArrayList<?>) query.getResultList();
		em.close();
		return result;
	}


}
