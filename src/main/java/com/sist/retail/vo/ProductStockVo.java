package com.sist.retail.vo;

import java.util.Date;

import com.sist.retail.common.DTO;

public class ProductStockVo extends DTO {
	// 화면 logisticsManagement, masterOrderStores, masterOrderDetail에 대한
	// ProductStockVo (화면설계의 프로그램 목록 시트 참조)

	private String pdtNo; // 상품번호
	private String pdtGb; // 상품군
	private String pdtNm; // 상품명
	private int price; // 가격
	private int stkCnt; // 재고량
	private String mkNm; // 제조사
	private int ordCnt; // 발주수량
	private String ordCd; // 발주상태
	private String ownId; // 점주명
	private String stoId; // 점포번호
	private String stoNm; // 점포명
	private String ordDt; // 발주 날짜
	private String page_size;
	private String page_num;

	public String getPage_size() {
		return page_size;
	}

	public void setPage_size(String page_Size) {
		this.page_size = page_Size;
	}

	public String getPage_num() {
		return page_num;
	}

	public void setPage_num(String page_Num) {
		this.page_num = page_Num;
	}

	@Override
	public String toString() {
		return "ProductStockVo [pdtNo=" + pdtNo + ", pdtGb=" + pdtGb + ", pdtNm=" + pdtNm + ", price=" + price
				+ ", stkCnt=" + stkCnt + ", mkNm=" + mkNm + ", ordCnt=" + ordCnt + ", ordCd=" + ordCd + ", ownId="
				+ ownId + ", stoId=" + stoId + ", stoNm=" + stoNm + ", ordDt=" + ordDt + ", getTotalNo()="
				+ getTotalNo() + "]";
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

	public int getStkCnt() {
		return stkCnt;
	}

	public void setStkCnt(int stkCnt) {
		this.stkCnt = stkCnt;
	}

	public String getMkNm() {
		return mkNm;
	}

	public void setMkNm(String mkNm) {
		this.mkNm = mkNm;
	}

	public int getOrdCnt() {
		return ordCnt;
	}

	public void setOrdCnt(int ordCnt) {
		this.ordCnt = ordCnt;
	}

	public String getOrdCd() {
		return ordCd;
	}

	public void setOrdCd(String ordCd) {
		this.ordCd = ordCd;
	}

	public String getOwnId() {
		return ownId;
	}

	public void setOwnId(String ownId) {
		this.ownId = ownId;
	}

	public String getStoId() {
		return stoId;
	}

	public void setStoId(String stoId) {
		this.stoId = stoId;
	}

	public String getStoNm() {
		return stoNm;
	}

	public void setStoNm(String stoNm) {
		this.stoNm = stoNm;
	}

	public String getOrdDt() {
		return ordDt;
	}

	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}

}
