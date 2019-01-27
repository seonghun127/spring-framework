package com.sist.retail.vo;

import java.util.Date;

import com.sist.retail.common.DTO;

public class SaleProductVo extends DTO
{
	private String stoId;    //점포아이디
	private Date salDt;      //판매날짜
	private String pdtNo;    //상품번호
	private int salCnt;     //수량
	private String pdtGb;    //상품군
	private String pdtNm;    //상품명
	private int sumsal;     //상품총 수량
	private String adr;
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public int getSumsal() {
		return sumsal;
	}
	public void setSumsal(int sumsal) {
		this.sumsal = sumsal;
	}
	@Override
	public String toString() {
		return "saleProduct [stoId=" + stoId + ", salDt=" + salDt + ", pdtNo=" + pdtNo + ", salCnt=" + salCnt
				+ ", pdtGb=" + pdtGb + ", pdtNm=" + pdtNm + "]";
	}
	public String getStoId() {
		return stoId;
	}
	public void setStoId(String stoId) {
		this.stoId = stoId;
	}
	public Date getSalDt() {
		return salDt;
	}
	public void setSalDt(Date salDt) {
		this.salDt = salDt;
	}
	public String getPdtNo() {
		return pdtNo;
	}
	public void setPdtNo(String pdtNo) {
		this.pdtNo = pdtNo;
	}
	public int getSalCnt() {
		return salCnt;
	}
	public void setSalCnt(int salCnt) {
		this.salCnt = salCnt;
	}
	public String getPdtGb() {
		return pdtGb;
	}
	public void setPdtGb(String pdtGb) {
		this.pdtGb = pdtGb;
	}
	public String getPdtNm() {
		return pdtNm;
	}
	public void setPdtNm(String pdtNm) {
		this.pdtNm = pdtNm;
	}
	
	

}
