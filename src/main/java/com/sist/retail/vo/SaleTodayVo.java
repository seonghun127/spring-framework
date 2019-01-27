package com.sist.retail.vo;

import com.sist.retail.common.DTO;

public class SaleTodayVo extends DTO {
	// 화면 storeServiceSaleToday에 대한 SaleTodayVo (화면설계의 프로그램 목록 시트 참조)
	
	private String pdtNo;	// 상품번호
	private String pdtGb;	// 상품군
	private String pdtNm;	// 상품명
	private int price;		// 가격
	private int salCnt;		// 수량
	private String stoId;	//	지점번호
	

	@Override
	public String toString() {
		return "SaleTodayVo [pdtNo=" + pdtNo + ", pdtGb=" + pdtGb + ", pdtNm=" + pdtNm + ", price=" + price
				+ ", salCnt=" + salCnt + ", stoId=" + stoId + "]";
	}

	public String getStoId() {
		return stoId;
	}

	public void setStoId(String stoId) {
		this.stoId = stoId;
	}

	public String getPdtNo() {
		return pdtNo;
	}

	public void setPdtNo(String pdtNo) {
		this.pdtNo = pdtNo;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSalCnt() {
		return salCnt;
	}

	public void setSalCnt(int salCnt) {
		this.salCnt = salCnt;
	}

}
