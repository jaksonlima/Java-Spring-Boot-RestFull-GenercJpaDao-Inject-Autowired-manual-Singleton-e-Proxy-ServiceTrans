package api.restful.restfull.generic.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class GenericJpaDAO<T extends Serializable> extends AbstractJpaDAO<T> implements IGenericDAO<T> {
}