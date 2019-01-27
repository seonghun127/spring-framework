package com.sist.retail.master.svc;

import java.util.List;

import com.sist.retail.common.DTO;

public interface MasterSvc {
	/**
	 * 회원가입, 발주 
	 * @param dto
	 * @return int
	 */
	public int do_add(DTO dto); /** 삽입*/
	
	public int do_getCount(); /** 테이블 행 수 리턴*/
	
	public DTO do_login(DTO dto); /** ID,PWD 확인 */
	
	public List<?> do_search(DTO dto);	/** 발주 지점 리스트 출력*/
	List<?> salRank(String div); /** 통계 데이터 */
	
	public int do_addStock(DTO dto); /**회원 가입시 자동 재고 추가 */
}

