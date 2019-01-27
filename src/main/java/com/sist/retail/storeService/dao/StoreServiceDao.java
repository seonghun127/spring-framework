package com.sist.retail.storeService.dao;

import java.util.List;

import com.sist.retail.common.DTO;
import com.sist.retail.common.WorkDiv;

public interface StoreServiceDao extends WorkDiv {
	/**
	 * 페이징 처리 지점 리스트 
	 * @param dto
	 * @return
	 */
	public List<?> do_searchPaging(DTO dto);
}
