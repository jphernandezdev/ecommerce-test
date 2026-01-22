package com.ecommerce.infrastructure.config;

import com.ecommerce.application.common.annotation.ApplicationTransactional;
import com.ecommerce.infrastructure.exception.ApplicationTransactionException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Aspect
@Component
@RequiredArgsConstructor
public class ApplicationTransactionalAspect {

    private final PlatformTransactionManager transactionManager;

    @Around("@annotation(applicationTransactional)")
    public Object manageTransaction(ProceedingJoinPoint joinPoint, ApplicationTransactional applicationTransactional) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setReadOnly(applicationTransactional.readOnly());

        return transactionTemplate.execute(status -> {
            try {
                return joinPoint.proceed();
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable e) {
                throw new ApplicationTransactionException(e);
            }
        });
    }
}
