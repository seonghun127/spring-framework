package com.sist.retail.logisticsManagement.svc;

import java.util.List;

import com.sist.retail.common.DTO;

public interface LogisticsManagementSvc 
{
	public List<?> do_search(DTO dto); /** 조회 */
	
	public List<?> do_search_group(DTO dto); /** 상품군 조회*/
	
	public List<?> do_search_favo(DTO dto); /**상풍군별 인기순위 조회*/
	
	public int do_add(DTO dto); /** 삽입*/
	
	public int do_delete(DTO dto);/** 삭제*/
	
	public int do_update(DTO dto);/** 수정*/
	public DTO do_searchOne(DTO dto);/** 날씨조회*/

}
