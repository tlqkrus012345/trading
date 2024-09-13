package com.trading.common.aop;

import com.trading.common.AopForTransaction;
import com.trading.common.CustomSpringELParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
public class DistributedLockAop {

    private static final String REDISSON_KEY_PREFIX = "RLOCK_";

    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;


    @Around("@annotation(com.trading.common.aop.DistributedLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DistributedLock annotation = signature.getMethod().getAnnotation(DistributedLock.class);

        String key = (String) CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), annotation.key());
        RLock lock = redissonClient.getLock(REDISSON_KEY_PREFIX + key);

        try {
            boolean available = lock.tryLock(annotation.waitTime(), annotation.leaseTime(), annotation.timeUnit());
            if (!available) {
                return false;
            }
            log.info("get lock success. key={}", key);
            return aopForTransaction.proceed(joinPoint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
            log.info("unlock success. key={}", key);
        }
    }
}
