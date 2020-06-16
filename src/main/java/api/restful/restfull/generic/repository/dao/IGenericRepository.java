package api.restful.restfull.generic.repository.dao;

import api.restful.restfull.model.AbstractEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepository<T extends AbstractEntity> extends IOperations<T> {
}