package repository;

import java.io.Serializable;

public class GenericRepository <Entity extends Serializable>extends AbstractRepository<Entity>{

    private Class<Entity> clazz;

    public GenericRepository(Class<Entity> clazz){
        this.clazz = clazz;
    }

    @Override
    public Class<Entity> entityClass (){
        return this.clazz;
    }
}
