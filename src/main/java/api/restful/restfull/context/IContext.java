package api.restful.restfull.context;

import java.util.Collection;

public interface IContext {

    void createBeanSingleton(Class clazz);

    void createBeanProxy(Class clazz);

    <T> T getBean(Class<T> clazzBean);

    <T> Collection<T> getBeansInterface(Class<T> classInterface);

    static IContext getContext(){
        return new ContextImpl();
    }
}
