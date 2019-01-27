package com.sist.retail.vo;

import com.sist.retail.common.DTO;

public class SaleTermVo extends DTO {
	private String salDt;
	
	private int totalSal;
	
	private String startDt;
	
	private String endDt;
	
	private String pdtNo;
	
	private String stoId;
	
	@Override
	public String toString() {
		return "SaleTermVo [salDt=" + salDt + ", totalSal=" + totalSal + ", startDt=" + startDt + ", endDt=" + endDt
				+ ", pdtNo=" + pdtNo + ", stoId=" + stoId + "]";
	}

	public String getPdtNo() {
		return pdtNo;
	}

	public void setPdtNo(String pdtNo) {
		this.pdtNo = pdtNo;
	}

	public String getStoId() {
		return stoId;
	}

	public void setStoId(String stoId) {
		this.stoId = stoId;
	}

	public String getSalDt() {
		return salDt;
	}

	public void setSalDt(String salDt) {
		this.salDt = salDt;
	}

	public int getTotalSal() {
		return totalSal;
	}

	public void setTotalSal(int totalSal) {
		this.totalSal = totalSal;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	} 
	
	
	
}
