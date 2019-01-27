package com.sist.retail.storeService.dao;

import java.util.List;

import com.sist.retail.common.DTO;
import com.sist.retail.common.WorkDiv;

public interface StoreServiceSaleDao extends WorkDiv {
	public List<?> do_searchTerm(DTO dto);
}
