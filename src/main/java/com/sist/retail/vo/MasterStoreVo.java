package com.sist.retail.vo;

import com.sist.retail.common.DTO;

public class MasterStoreVo extends DTO{
	private String stoId;
	private String stoNm;
	private String ownId;
	private String phoNo;
	private String adr  ;
	private double lot  ;
	private double lat  ;
	private String pwd  ;
	private String gntCd;
	private int chk;
	private String sido;
	private String gugun;
	
	public MasterStoreVo() {}
	
	public MasterStoreVo(String stoId, String stoNm, String ownId, String phoNo, String adr, double lot, double lat,
			String pwd, String gntCd, int chk) {
		super();
		this.stoId = stoId;
		this.stoNm = stoNm;
		this.ownId = ownId;
		this.phoNo = phoNo;
		this.adr = adr;
		this.lot = lot;
		this.lat = lat;
		this.pwd = pwd;
		this.gntCd = gntCd;
		this.chk = chk;
	}
	
	@Override
	public String toString() {
		return "MasterStoreVo [stoId=" + stoId + ", stoNm=" + stoNm + ", ownId=" + ownId + ", phoNo=" + phoNo + ", adr="
				+ adr + ", lot=" + lot + ", lat=" + lat + ", pwd=" + pwd + ", gntCd=" + gntCd + ", chk=" + chk
				+ "]";
	}
	
	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
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

	public double getLot() {
		return lot;
	}

	public void setLot(double lot) {
		this.lot = lot;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGntCd() {
		return gntCd;
	}

	public void setGntCd(String gntCd) {
		this.gntCd = gntCd;
	}

	public int getchk() {
		return chk;
	}

	public void setchk(int chk) {
		this.chk = chk;
	}	
}
