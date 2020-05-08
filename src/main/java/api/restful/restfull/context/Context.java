package api.restful.restfull.context;

import api.restful.restfull.generic.dao.ProjetoDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;

@Configuration
public class Context implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Context.applicationContext = applicationContext;

        createBean(ProjetoDao.class);
    }

    public static void createBean(Class clazz) {
        Object bean = resolverClass(clazz);
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
        beanFactory.registerSingleton(bean.getClass().getCanonicalName(), bean);
        beanFactory.autowireBean(bean);
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
