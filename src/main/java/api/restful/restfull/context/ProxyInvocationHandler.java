package api.restful.restfull.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(ProxyInvocationHandler.class);

    @Override
    public Object invoke(Object objProxy, Method method, Object[] objects) throws Throwable {
        LOGGER.info("Invoked method: {}", method.getName());
        return 42;
    }
}
