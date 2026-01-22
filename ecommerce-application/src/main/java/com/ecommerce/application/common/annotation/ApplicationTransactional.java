package com.ecommerce.application.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to identify transactions in the application layer,
 * maintaining hexagonal architecture principles by being independent of frameworks.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApplicationTransactional {

    boolean readOnly() default false;

    String value() default "";

    String transactionManager() default "";
}
