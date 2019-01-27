package com.sist.retail.common;

import org.apache.log4j.Logger;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.util.PatternMatchUtils;

public class NameMatchClassMethodPointcut extends NameMatchMethodPointcut {
	private static Logger log=Logger.getLogger(NameMatchClassMethodPointcut.class);
	
	public void setMappedClassName(String mappedName) {
		//log.debug("0 mappedName :"+mappedName);
		this.setClassFilter(new SimpleClassFilter(mappedName));
	}
	
	static class SimpleClassFilter implements ClassFilter
	{
		private String mappedName;
		private  SimpleClassFilter(String mappedName){
			this.mappedName = mappedName;
		}

		@Override
		public boolean matches(Class<?> clazz) {
			log.info("1 matches mappedName :"+mappedName);
			log.info("2 matches clazz.getSimpleName :"+clazz.getSimpleName()+":"+PatternMatchUtils.simpleMatch(mappedName, clazz.getSimpleName()));
			return PatternMatchUtils.simpleMatch(mappedName, clazz.getSimpleName());
		}
		
	}
}
