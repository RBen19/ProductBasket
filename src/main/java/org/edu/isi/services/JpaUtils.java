package org.edu.isi.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProduitPanierPu");

    public static final EntityManager em = emf.createEntityManager();

    public static EntityManager getEmf() {
        if (em == null) {
            EntityManager em = emf.createEntityManager();
        }
        return em;
    }
    public static void closeEntityManagerFactory() {
        emf.close();
    }
}
