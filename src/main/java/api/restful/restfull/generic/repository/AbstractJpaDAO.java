package api.restful.restfull.generic.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractJpaDAO<T> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    public Class<T> getEntityClass() {
        return resolverClass(entityClass);
    }

    public void setClass(Class clazz) {
        clazz = resolverClass(clazz);
    }

    public void flush() {
    }

    public void clear() {
    }

    public void refresh(T entity) {
    }

    public T refresh(Integer id) {
        return null;
    }

    public T getReference(Integer primaryKey) {
        return null;
    }

    public void detach(Object obj) {
    }

    public void persist(T entity) {
    }

    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }

    public T mergeOrPersist(T entity) {
        return null;
    }

    public T find(Integer id) {
        return getEntityManager().find(getEntityClass(), id);
    }

    public T find(T entity) {
        return null;
    }

    public T findAndValidate(Integer id) {
        return null;
    }

    public T findLock(Integer id) {
        return null;
    }

    public T findLockNoWait(Integer id) {
        return null;
    }

    public T findRefresh(Integer id) {
        return null;
    }

    public Integer getNextVal(String sequenceName) throws RuntimeException {
        return null;
    }

    public void remove(T entity) {
        getEntityManager().remove(entity);
    }

    public void lock(T entity) {
    }

    public void lockNoWait(T entity) {
    }

    public T find(Class entityClass, Integer id) {
        return null;
    }

    public Query createQuery(StringBuilder query) {
        Class<T> entityClass = getEntityClass();
        return createQuery(query.toString());
    }

    public Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    public TypedQuery<T> createTypedQuery(StringBuilder query) {
        return createTypedQuery(query.toString());
    }

    public TypedQuery<T> createTypedQuery(String query) {
        return getEntityManager().createQuery(query, getEntityClass());
    }

    public <C> TypedQuery<C> createTypedQuery(String sqlString, Class<C> entityClass) {
        return getEntityManager().createQuery(sqlString, entityClass);
    }

    public Query createNativeQuery(StringBuilder query) {
        return getEntityManager().createNativeQuery(query.toString(), getEntityClass());
    }

    public Query createNativeQuery(StringBuilder query, Class entityClass) {
        return getEntityManager().createNativeQuery(query.toString(), entityClass);
    }

    private EntityManager getEntityManager() {
        return entityManager;
    }

    private Class<T> resolverClass(Class<T> clazzType) {
        if (entityClass == null) {
            if (clazzType == null) {
                entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            } else {
                entityClass = clazzType;
            }
        }
        return entityClass;
    }
}