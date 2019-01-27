package com.sist.retail.sale.dao;

import java.util.List;

import com.sist.retail.common.DTO;
import com.sist.retail.common.WorkDiv;

public interface SaleDao extends WorkDiv{

	@Override
	List<?> do_search(DTO dto);

	@Override
	int do_add(DTO dto);

	@Override
	int do_delete(DTO dto);

	@Override
	int do_update(DTO dto);

	@Override
	int do_excelDown();

	@Override
	int do_excelUp(List<?> list);

	@Override
	DTO do_searchOne(DTO dto);

	@Override
	int do_getCount();

}
