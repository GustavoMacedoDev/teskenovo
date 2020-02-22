package br.com.devmedia.curso.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaUtil {

	private static EntityManagerFactory emf;
	
	public static EntityManager getEntityManager() {
		
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("TeskePU");
		}
		return emf.createEntityManager();
	}
	
	
}
