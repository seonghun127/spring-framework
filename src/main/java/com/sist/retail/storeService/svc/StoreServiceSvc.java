package com.sist.retail.storeService.svc;

import java.util.List;

import com.sist.retail.common.DTO;

public interface StoreServiceSvc {
	/**
	 * 지점 상세보기, 단건 조회
	 * @param dto
	 * @return dto
	 */
	public DTO do_searchOne(DTO dto);
	
	/**
	 * 지점 리스트
	 * @param dto
	 * @return
	 */
	public List<?> do_search(DTO dto);
	
	/**
	 * 지점 리스트 페이징 처리
	 */
	public List<?> do_searchPaging(DTO dto);
}
