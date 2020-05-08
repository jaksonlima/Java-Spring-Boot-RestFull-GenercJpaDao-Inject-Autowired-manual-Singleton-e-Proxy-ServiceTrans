package api.restful.restfull.context;

import api.restful.restfull.generic.dao.ProjetoDao;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.ProxyUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Configuration
public class ContextProxy implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextProxy.applicationContext = applicationContext;

        proxyBean(ProjetoDao.class);
    }

    public static IProxy proxyBean(Class clazz) {
        InvocationHandler handler = (InvocationHandler) new ProxyInvocationHandler();
        IProxy proxy = (IProxy) Proxy.newProxyInstance(IProxy.class.getClassLoader(), new Class[]{IProxy.class}, handler);

        Class<?> userClass = ProxyUtils.getUserClass(clazz);
        Object bean = resolverClass(userClass);
//        BeanDefinitionHolder scopedProxy = ScopedProxyUtils.createScopedProxy("", "", "");

        return proxy;
    }

    public static <T> T getBean(Class<T> clazzBean) {
        return applicationContext.getAutowireCapableBeanFactory().getBean(clazzBean);
    }

    private static Object resolverClass(Class clazz) {
        try {
            if (clazz == null) {
                throw new RuntimeException("Class não encontrada.");
            }
            Constructor constructor = clazz.getDeclaredConstructor();

            if (constructor != null) {
                return constructor.newInstance();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

