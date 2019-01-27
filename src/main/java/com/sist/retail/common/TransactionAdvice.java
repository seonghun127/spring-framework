package com.sist.retail.common;

import java.sql.SQLException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor {

	private static Logger log = Logger.getLogger(TransactionAdvice.class);
	PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		log.debug("00 transactionManager:"+transactionManager.toString() );
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			Object ret = invocation.proceed();// Object:JoinPoint
			log.debug("0 invocation status"+status.toString() );
			this.transactionManager.commit(status);
			log.debug("1 commit:");
			return ret;

		} catch (RuntimeException e) {
			log.debug("2 rollback:" + e.getMessage());
			this.transactionManager.rollback(status);
			log.debug("2-1 rollback:" + e.getMessage());
			throw e;
		}
	}

}
