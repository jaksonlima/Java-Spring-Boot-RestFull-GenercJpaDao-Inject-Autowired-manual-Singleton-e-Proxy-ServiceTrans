package api.restful.restfull.context;

import org.springframework.beans.BeanUtils;

import java.util.Collection;

public interface IContext {

    void createBeanSingleton(Class clazz);

    <T> T getBean(Class<T> clazzBean);

    <T> Collection<T> getBeansInterface(Class<T> classInterface);

    static IContext getContext() {
        return BeanUtils.instantiateClass(ContextImpl.class);
    }

}