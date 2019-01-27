package com.sist.retail.vo;

import java.util.*;
import com.sist.retail.common.DTO;

public class NoticeVo extends DTO{

	private int memoNo;
	private String title;
	private String stoId;
	private String con;
	private String regDt;
	private String divCd;
	private String nextMemoNo;
	private String nextTitle;
	private String preMemoNo;
	private String preTitle;
	private String stoNm;
	
	public NoticeVo(){}
	
	public NoticeVo(int memoNo, String title, String stoId, String con, String regDt, String divCd, String nextMemoNo,
			String nextTitle, String preMemoNo, String preTitle, String stoNm) {
		this.memoNo = memoNo;
		this.title = title;
		this.stoId = stoId;
		this.con = con;
		this.regDt = regDt;
		this.divCd = divCd;
		this.nextMemoNo = nextMemoNo;
		this.nextTitle = nextTitle;
		this.preMemoNo = preMemoNo;
		this.preTitle = preTitle;
		this.stoNm = stoNm;
	}

	@Override
	public String toString() {
		return "NoticeVo [memoNo=" + memoNo + ", title=" + title + ", stoId=" + stoId + ", con=" + con + ", regDt="
				+ regDt + ", divCd=" + divCd + ", nextMemoNo=" + nextMemoNo + ", nextTitle=" + nextTitle
				+ ", preMemoNo=" + preMemoNo + ", preTitle=" + preTitle + ", stoNm=" + stoNm + "]";
	}

	public int getMemoNo() {
		return memoNo;
	}

	public void setMemoNo(int memoNo) {
		this.memoNo = memoNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStoId() {
		return stoId;
	}

	public void setStoId(String stoId) {
		this.stoId = stoId;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getDivCd() {
		return divCd;
	}

	public void setDivCd(String divCd) {
		this.divCd = divCd;
	}

	public String getNextMemoNo() {
		return nextMemoNo;
	}

	public void setNextMemoNo(String nextMemoNo) {
		this.nextMemoNo = nextMemoNo;
	}

	public String getNextTitle() {
		return nextTitle;
	}

	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}

	public String getPreMemoNo() {
		return preMemoNo;
	}

	public void setPreMemoNo(String preMemoNo) {
		this.preMemoNo = preMemoNo;
	}

	public String getPreTitle() {
		return preTitle;
	}

	public void setPreTitle(String preTitle) {
		this.preTitle = preTitle;
	}

	public String getStoNm() {
		return stoNm;
	}

	public void setStoNm(String stoNm) {
		this.stoNm = stoNm;
	}

	
}