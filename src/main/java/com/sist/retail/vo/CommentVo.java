package com.sist.retail.vo;

import java.util.Date;

import com.sist.retail.common.DTO;

public class CommentVo extends DTO {
	// noticeDeatil에서 댓글은 여러 개니깐 댓글 vo로 따로뺀다. commentVo (화면설계의 프로그램 목록 시트 참조)
	 
	private int cmtNo;		// 댓글번호
	private String stoId;	// 점포아이디
	private String cmtCon;	// 내용
	private String regDt;		// 등록일
	private int memoNo;		// 공지사항 번호
	private String stoNm; // 점포명
	public String getStoNm() {
		return stoNm;
	}

	public void setStoNm(String stoNm) {
		this.stoNm = stoNm;
	}

	@Override
	public String toString() {
		return "commentVo [cmtNo=" + cmtNo + ", stoId=" + stoId + ", cmtCon=" + cmtCon + ", regDt=" + regDt
				+ ", memoNo=" + memoNo + "]";
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public String getStoId() {
		return stoId;
	}

	public void setStoId(String stoId) {
		this.stoId = stoId;
	}

	public String getCmtCon() {
		return cmtCon;
	}

	public void setCmtCon(String cmtCon) {
		this.cmtCon = cmtCon;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public int getMemoNo() {
		return memoNo;
	}

	public void setMemoNo(int memoNo) {
		this.memoNo = memoNo;
	}

}
