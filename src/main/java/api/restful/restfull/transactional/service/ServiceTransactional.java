package api.restful.restfull.transactional.service;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional
@Service
public @interface ServiceTransactional {

    @AliasFor("transactionManager")
    String value() default "";

    @AliasFor("value")
    String transactionManager() default "";

    @AliasFor(value = "propagation", annotation = Transactional.class)
    Propagation propagation() default Propagation.REQUIRED;

    @AliasFor(value = "isolation", annotation = Transactional.class)
    Isolation isolation() default Isolation.DEFAULT;

    @AliasFor(value = "timeout", annotation = Transactional.class)
    int timeout() default -1;

    @AliasFor(value = "readOnly", annotation = Transactional.class)
    boolean readOnly() default false;

    @AliasFor(value = "rollbackFor", annotation = Transactional.class)
    Class<? extends Throwable>[] rollbackFor() default {};

    @AliasFor(value = "rollbackForClassName", annotation = Transactional.class)
    String[] rollbackForClassName() default {};

    @AliasFor(value = "noRollbackFor", annotation = Transactional.class)
    Class<? extends Throwable>[] noRollbackFor() default {};

    @AliasFor(value = "noRollbackForClassName", annotation = Transactional.class)
    String[] noRollbackForClassName() default {};
}
