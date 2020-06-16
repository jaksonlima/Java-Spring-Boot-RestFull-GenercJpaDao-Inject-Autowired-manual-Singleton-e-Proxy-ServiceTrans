package api.restful.restfull.generic.repository.dao;

import api.restful.restfull.model.AbstractEntity;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericRepository<T extends AbstractEntity, I extends Serializable> extends AbstractRepository<T, I> implements IGenericRepository<T> {
}