package com.sist.retail.notice.service;

import java.util.List;

import com.sist.retail.common.DTO;

public interface CommentSvc {

	List<?> do_search(DTO dto);


	int do_add(DTO dto);


	int do_delete(DTO dto);


	int do_update(DTO dto);
	
	//댓글 연쇄 삭제 
	public int do_deleteCasCade(DTO dto);
}
