package com.sist.retail.vo;

import java.util.Date;

import com.sist.retail.common.DTO;

public class NoticeQnaVo extends DTO {
	// noticeQna에 관련된 것들은 모두 noticeQnaVo가 담당함. (화면설계의 프로그램 목록 시트 참조)

	private int qaNo; // 문의번호
	private String title; // 제목
	private String qaCon; // 문의내용
	private String ansCon; // 답변내용
	private String qaDt; // 문의등록일
	private String ansDt; // 답변등록일
	private String stoId; // 점포아이디
	private String stoNm; // 작성자
	private int ansNo; // 답변번호
	private String nextQaNo;	// 다음글 번호
	private String nextTitle; // 다음글 제목
	private String preQaNo;	// 이전글 번호
	private String preTitle;	// 이전글 제목

	public int getAnsNo() {
		return ansNo;
	}

	public void setAnsNo(int ansNo) {
		this.ansNo = ansNo;
	}

	@Override
	public String toString() {
		return "NoticeQnaVo [qaNo=" + qaNo + ", title=" + title + ", qaCon=" + qaCon + ", ansCon=" + ansCon + ", qaDt="
				+ qaDt + ", ansDt=" + ansDt + ", stoId=" + stoId + ", stoNm=" + stoNm + ", ansNo=" + ansNo
				+ ", nextQaNo=" + nextQaNo + ", nextTitle=" + nextTitle + ", preQaNo=" + preQaNo + ", preTitle="
				+ preTitle + "]";
	}

	public int getQaNo() {
		return qaNo;
	}

	public void setQaNo(int qaNo) {
		this.qaNo = qaNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQaCon() {
		return qaCon;
	}

	public void setQaCon(String qaCon) {
		this.qaCon = qaCon;
	}

	public String getAnsCon() {
		return ansCon;
	}

	public void setAnsCon(String ansCon) {
		this.ansCon = ansCon;
	}

	public String getQaDt() {
		return qaDt;
	}

	public void setQaDt(String qaDt) {
		this.qaDt = qaDt;
	}

	public String getAnsDt() {
		return ansDt;
	}

	public void setAnsDt(String ansDt) {
		this.ansDt = ansDt;
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

	public String getNextQaNo() {
		return nextQaNo;
	}

	public void setNextQaNo(String nextQaNo) {
		this.nextQaNo = nextQaNo;
	}

	public String getNextTitle() {
		return nextTitle;
	}

	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}

	public String getPreQaNo() {
		return preQaNo;
	}

	public void setPreQaNo(String preQaNo) {
		this.preQaNo = preQaNo;
	}

	public String getPreTitle() {
		return preTitle;
	}

	public void setPreTitle(String preTitle) {
		this.preTitle = preTitle;
	}
	
}
