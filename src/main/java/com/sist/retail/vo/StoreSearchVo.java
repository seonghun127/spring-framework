package com.sist.retail.vo;

public class StoreSearchVo {
	// 화면 storeServiceSearch에 대한 StoreSearchVo() (화면설계의 프로그램 목록 시트 참조)
	
	private String stoNm;	// 매장명
	private String ownId;	// 점주명
	private String phoNo;	// 연락처
	private String adr;		// 주소

	@Override
	public String toString() {
		return "StoreSearchVo [stoNm=" + stoNm + ", ownId=" + ownId + ", phoNo=" + phoNo + ", adr=" + adr + "]";
	}

	public String getStoNm() {
		return stoNm;
	}

	public void setStoNm(String stoNm) {
		this.stoNm = stoNm;
	}

	public String getOwnId() {
		return ownId;
	}

	public void setOwnId(String ownId) {
		this.ownId = ownId;
	}

	public String getPhoNo() {
		return phoNo;
	}

	public void setPhoNo(String phoNo) {
		this.phoNo = phoNo;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

}
