package com.sist.retail.order.svc;

import java.util.List;

import com.sist.retail.common.DTO;

public interface OrderSvc {
	
	public int do_add(DTO dto); /** 삽입*/
	
	public int do_getCount(); /** 테이블 행 수 리턴*/
	
	public List<?> do_search(DTO dto);	/** 발주 지점 리스트 출력*/
	
	public List<?> do_search2(DTO dto);
	
	public int do_update(DTO dto);
	
	public int do_confirm(DTO dto);
	
	public int do_stk_update(DTO dto);
	
	public int do_delete(DTO dto);
	
}

