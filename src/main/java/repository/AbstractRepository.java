package repository;
import util.Hibernate;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<Entity> {

    public Entity create(Entity e){
        try {
            em().getTransaction().begin();
            em().persist(e);
            em().getTransaction().commit();
        }catch (PersistenceException pe) {
            pe.printStackTrace();
            em().getTransaction().rollback();
        }

        return e;
    }

    public Entity read(long id){

        Entity result = em().find(entityClass(), id);
        return result;
    }

    public Entity read(String strId) {

        Entity result = em().find(entityClass(), strId);

        return result;
    }


    public Entity read(Object objId){

        Entity result = em().find(entityClass(), objId);

        return result;
    }



    public List<Entity> readAll(){

        CriteriaBuilder builder = em().getCriteriaBuilder();
        Class<Entity> clazz = entityClass();
        CriteriaQuery<Entity> query = builder.createQuery(clazz);
        Root<Entity> root = query.from(clazz);
        query.select(root);

        return em().createQuery(query).getResultList();
    }


    public Entity update(Entity e){

        try {
            em().getTransaction().begin();
            em().persist(e);
            em().getTransaction().commit();
        }catch (PersistenceException pe) {
            pe.printStackTrace();
            em().getTransaction().rollback();
        }
        return e;
    }



    public boolean delete(Entity e){

        boolean result = true;
        try {
            em().getTransaction().begin();
            em().remove(e);
            em().getTransaction().commit();
        }catch (PersistenceException pe) {
            pe.printStackTrace();
            result = false;
            em().getTransaction().rollback();
        }
        return result;
    }

    public <Entity extends Serializable> List<Entity> findAllBy(Class<Entity> entityClass, List<String> attributes, List<String> values) {

        List entities = null;

        CriteriaBuilder builder = em().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();
        Root<Entity> root = query.from(entityClass); //=> "SELECT * from Entity"
        query.select(root);

        if(attributes != null && values != null && attributes.size() > 0 && values.size() > 0 &&
                attributes.size() == values.size()) {
            List<Predicate> predicates = new ArrayList<>();

            for (int i = 0; i < attributes.size(); i++) {
                String attr = attributes.get(i);
                String val = values.get(i);
                predicates.add(builder.equal(root.get(attr), val));
            }
            query.where( //=> " WHERE "
                    builder.and(
                            predicates.toArray(new Predicate[predicates.size()])
                    )
            );
        }

        entities = em().createQuery(query).getResultList();

        return entities;

    }

    protected EntityManager em(){
        return Hibernate.getEM();
    }

    public abstract Class<Entity> entityClass();
}
