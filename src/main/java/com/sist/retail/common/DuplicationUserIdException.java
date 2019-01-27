package com.sist.retail.common;

import org.apache.log4j.Logger;

/**
 * ID 중복 예외
 * @author A_CHU
 */
public class DuplicationUserIdException extends RuntimeException {
    Logger log=Logger.getLogger(this.getClass());
	
	public DuplicationUserIdException(){}
	public DuplicationUserIdException(String cause){
		super(cause);
		log.debug("============================");
		log.debug(cause);
		log.debug("============================");		
	}
}
