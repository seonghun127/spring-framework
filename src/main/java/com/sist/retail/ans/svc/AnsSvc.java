package com.sist.retail.ans.svc;

import java.util.List;

import com.sist.retail.common.DTO;

public interface AnsSvc {

	public int do_add(DTO dto); /** 삽입*/
	
	public List<?> do_search(DTO dto); /** 검색*/
	
	public int do_delete(DTO dto);/** 삭제*/
	
	public int do_update(DTO dto);/** 수정*/
}
