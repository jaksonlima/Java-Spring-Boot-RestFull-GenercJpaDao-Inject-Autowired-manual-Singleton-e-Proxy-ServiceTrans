package api.restful.restfull.generic.repository.dao;

import api.restful.restfull.context.IContext;
import api.restful.restfull.model.AbstractEntity;
import org.springframework.core.ResolvableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@Transactional(readOnly = true)
public abstract class AbstractRepository<T extends AbstractEntity, I extends Serializable> implements Repository<T, I> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    private final Map<String, Object> mapLockNoWait = Collections.singletonMap("javax.persistence.lock.timeout", (Object) 0);

    public Class<T> getEntityClass() {
        return resolverClass(entityClass);
    }

    private EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setClass(Class clazz) {
        this.entityClass = resolverClass(clazz);
    }

    @Transactional
    public void flush() {
        getEntityManager().flush();
    }

    @Transactional
    public void clear() {
        getEntityManager().clear();
    }

    @Transactional
    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    public T refresh(Integer id) {
        T entity = getReference(id);
        refresh(entity);
        return entity;
    }

    public T getReference(Integer primaryKey) {
        return getEntityManager().getReference(entityClass, primaryKey);
    }

    @Transactional
    public void detach(Object obj) {
        getEntityManager().detach(obj);
    }

    @Transactional
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    @Transactional
    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    public T mergeOrPersist(T entity) {
        T entityDB = find(entity);

        if (entityDB == null) {
            persist(entity);
        } else {
            entity = merge(entity);
        }

        return entity;
    }

    public T find(Number id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    public T find(T entity) {
        Number id = entity.getId();
        return find(id);
    }

    public T findValidate(Integer id) {
        T entity = find(id);

        if (entity == null) {
            throw new RuntimeException("ENTITY_NOT_FOUND " + getEntityName() + id);
        }
        return entity;
    }

    @Transactional
    public T findLock(Integer id) {
        return getEntityManager().find(entityClass, id, LockModeType.PESSIMISTIC_WRITE);
    }

    @Transactional
    public T findLockNoWait(Integer id) {
        return getEntityManager().find(entityClass, id, LockModeType.PESSIMISTIC_WRITE, mapLockNoWait);
    }

    public T findRefresh(Integer id) {
        T entity = find(id);
        if (entity != null) {
            refresh(entity);
        }
        return entity;
    }

    @Transactional
    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    @Transactional
    public void lock(T entity) {
        getEntityManager().lock(entity, LockModeType.PESSIMISTIC_WRITE);
    }

    @Transactional
    public void lockNoWait(T entity) {
        getEntityManager().lock(entity, LockModeType.PESSIMISTIC_WRITE, mapLockNoWait);
    }

    protected Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    protected Query createQuery(StringBuilder query) {
        return createQuery(query.toString());
    }

    protected Query createQuery(String sqlString, Class entityClass) {
        return getEntityManager().createQuery(sqlString, entityClass);
    }

    protected TypedQuery<T> createTypedQuery(String query) {
        return getEntityManager().createQuery(query, getEntityClass());
    }

    protected TypedQuery<T> createTypedQuery(StringBuilder query) {
        return createTypedQuery(query.toString());
    }

    protected <C> TypedQuery<C> createTypedQuery(String sqlString, Class<C> entityClass) {
        return getEntityManager().createQuery(sqlString, entityClass);
    }

    protected Query createNativeQuery(String query) {
        return getEntityManager().createNativeQuery(query, getEntityClass());
    }

    protected Query createNativeQuery(StringBuilder query) {
        return createNativeQuery(query.toString());
    }

    protected Query createNativeQuery(StringBuilder query, Class entityClass) {
        return getEntityManager().createNativeQuery(query.toString(), entityClass);
    }

    protected String getEntityName() {
        if (entityClass.isAnnotationPresent(Entity.class)) {
            Entity annotation = entityClass.getAnnotation(Entity.class);
            if (annotation.name().isEmpty()) {
                return annotation.name();
            }
        }

        return entityClass.getSimpleName();
    }

    protected Class<T> resolverClass(Class<T> clazzType) {
        if (entityClass == null) {
            if (clazzType == null) {
                entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            } else {
                entityClass = clazzType;
            }
        }
        return entityClass;
    }

    protected <R extends Repository> R getRepository(Class<R> classRespository) {
        return IContext.getContext().getBean(classRespository);
    }

    public JpaRepository<T, I> getRepository() {
        final Collection<JpaRepository> beansRepository = IContext.getContext().getBeansInterface(JpaRepository.class);

        return beansRepository.stream()
                .filter(repo -> Stream.of(repo.getClass().getInterfaces())
                        .anyMatch(classInterface -> ResolvableType.forClass(classInterface).as(JpaRepository.class).getGeneric(0).toClass().equals(this.entityClass)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(JpaRepository.class.getSimpleName() + " não encontrado para entidade " + getEntityName()));

    }

}