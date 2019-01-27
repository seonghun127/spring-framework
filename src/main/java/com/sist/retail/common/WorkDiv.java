package com.sist.retail.common;

import java.util.List;

/**
 * 	거래표준 메소드: Dao 모든 interface에 상속. 
 *  ex)public interface MemberDao extends WorkDiv {
 *  	
 *   }
 *  do_search 	: 조회
 *  do_add 		: 삽입
 *  do_delete 	: 삭제
 *  do_update 	: 수정
 *  do_excelDown : 엑셀저장
 *  do_excelUp 	: 엑셀업로드
 *  do_searchOne() : 단건조회 
 * @author A_CHU
 *	@version 0.1
 *	@since 2017/11/27
 */
public interface WorkDiv {
	public List<?> do_search(DTO dto); /** 조회 */
	
	public int do_add(DTO dto); /** 삽입*/
	
	public int do_delete(DTO dto);/** 삭제*/
	
	public int do_update(DTO dto);/** 수정*/
	
	public int do_excelDown();/** 엑셀저장*/
	
	public int do_excelUp(List<?> list);/** 엑셀 업로드*/
	
	public DTO do_searchOne(DTO dto);/** 단건 조회*/
	
	public int do_getCount(); /** 테이블 행 수 리턴*/
}
