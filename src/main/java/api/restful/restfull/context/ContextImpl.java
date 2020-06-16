package api.restful.restfull.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;

@Configuration
public class ContextImpl implements ApplicationContextAware, IContext {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void createBeanSingleton(Class clazz) {
        try {
            Object bean = newInstance(clazz);
            ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
            beanFactory.registerSingleton(bean.getClass().getCanonicalName(), bean);
            beanFactory.autowireBean(bean);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public <T> T getBean(Class<T> clazzBean) {
        try {
            return applicationContext.getAutowireCapableBeanFactory().getBean(clazzBean);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public <T> Collection<T> getBeansInterface(Class<T> classInterface) {
        try {
            if (classInterface.isInterface()) {
                final Map<String, T> beansOfType = applicationContext.getBeansOfType(classInterface);

                if (beansOfType != null) {
                    return beansOfType.values();
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Object newInstance(Class clazz) {
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
            throw new RuntimeException(e.getMessage());
        }
    }

}