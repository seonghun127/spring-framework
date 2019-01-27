package com.sist.retail.vo;

import java.util.Date;

import com.sist.retail.common.DTO;

public class SaleVo extends DTO{
	String stoId; //점포번호
	int salCnt; //수량
	String sexCd;
	String ageCd;
	String pdtNo;
	String sex;
	String rank;
	String pdtNm;
	String time;
	String age;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPdtNm() {
		return pdtNm;
	}
	public void setPdtNm(String pdtNm) {
		this.pdtNm = pdtNm;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public SaleVo(String stoId, int saleCnt, String sexCd, String ageCd, String product) {
		this.stoId = stoId;
		this.salCnt = saleCnt;
		this.sexCd = sexCd;
		this.ageCd = ageCd;
		this.pdtNo = product;
	}
	public SaleVo() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SaleVo [stoId=" + stoId + ", salCnt=" + salCnt + ", sexCd=" + sexCd + ", ageCd=" + ageCd
				+ ", pdtNo=" + pdtNo + "]";
	}
	public String getStoId() {
		return stoId;
	}
	public void setStoId(String storeId) {
		this.stoId = storeId;
	}
	public int getSalCnt() {
		return salCnt;
	}
	public void setSalCnt(int salCnt) {
		this.salCnt = salCnt;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getAgeCd() {
		return ageCd;
	}
	public void setAgeCd(String ageCd) {
		this.ageCd = ageCd;
	}
	public String getPdtNo() {
		return pdtNo;
	}
	public void setPdtNo(String pdtNo) {
		this.pdtNo = pdtNo;
	}
	
}
