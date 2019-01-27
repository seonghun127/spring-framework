package com.sist.retail.ans.dao;

import java.util.List;

import com.sist.retail.common.DTO;
import com.sist.retail.common.WorkDiv;

public interface AnsDao extends WorkDiv {

	@Override
	public List<?> do_search(DTO dto) ;

	@Override
	public int do_add(DTO dto) ;

	@Override
	public int do_delete(DTO dto) ;

	@Override
	public int do_update(DTO dto) ;
	
	@Override
	public int do_excelUp(List<?> list) ;

	@Override
	public DTO do_searchOne(DTO dto) ;

	@Override
	public int do_getCount();
	
	// 선택 게시글 불러오기
	public DTO do_detail(DTO dto);
}
