package com.sist.retail.notice.dao;

import java.util.List;

import com.sist.retail.common.DTO;
import com.sist.retail.common.WorkDiv;

public interface CommentDao extends WorkDiv {

	@Override
	List<?> do_search(DTO dto);

	@Override
	int do_add(DTO dto);

	@Override
	int do_delete(DTO dto);

	@Override
	int do_update(DTO dto);
	
	/**
	 * 게시글 삭제 시 연쇄 삭제 
	 * @param dto
	 * @return
	 */
	int do_deleteCasCade(DTO dto);
}
