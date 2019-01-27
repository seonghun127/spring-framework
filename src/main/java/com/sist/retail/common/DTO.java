package com.sist.retail.common;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO : Data Transfer Object
 * @author A_CHU
 *	@version 0.1
 * 	@since 2017/11/27
 */
public class DTO {
	private String regId;			//등록자
	private String regDt;			//등록일
	private String modId;			//수정자
	private String modDt;			//수정일
	
	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	/**
	 * Param :검색
	 */
	private Map param = new HashMap<String, String>();
	
	/**
	 * 글번호
	 */
	private int no;
	
	/**
	 * 총글수
	 */
	private int totalNo;
	
	/**
	 * 이메일
	 * 
	 */
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map getParam() {
		return param;
	}

	public void setParam(Map param) {
		this.param = param;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getTotalNo() {
		return totalNo;
	}

	public void setTotalNo(int totalNo) {
		this.totalNo = totalNo;
	}
	
	
}
