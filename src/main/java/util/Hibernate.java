package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class Hibernate {

    private static final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("SDA");

    private static EntityManager em = emf.createEntityManager();

    public static EntityManager getEM() {

        if(em == null || !em.isOpen()){
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void closeEMF() {

        if(em != null && em.isOpen()) {
            em.close();
        }

        if(emf != null && emf.isOpen()) {
            emf.close();
        }

    }



//    public static <Entity extends Serializable> void save(Entity e){
//        EntityManager em = getEM();
//        EntityTransaction t = em.getTransaction();
//        t.begin();
//        em.persist(e);
//        t.commit();
//
//        em.close();
//    }


//    public static <Entity extends Serializable> List<Entity> readAll(Class<Entity> entityClass) {
//
//        List<Entity> entities = null;
//
//        // Create an EntityManager
//        EntityManager manager = getEM();
//
//        CriteriaBuilder builder = manager.getCriteriaBuilder();
//        CriteriaQuery query = builder.createQuery(); //=>"SELECT "
//        Root<Entity> root = query.from(entityClass); //=> "SELECT  from E"
//        query.select(root); // "SELECT * from E e"
//
//        entities = manager.createQuery(query).getResultList();
//        manager.close();
//
//        return entities;
//    }

}
