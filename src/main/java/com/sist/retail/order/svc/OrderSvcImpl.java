package com.sist.retail.order.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.order.dao.OrderDaoImpl;

@Service
public class OrderSvcImpl implements OrderSvc {

	@Autowired
	private OrderDaoImpl orderDaoImpl;
	
	@Override
	public int do_add(DTO dto) {
		return orderDaoImpl.do_add(dto);
	}

	@Override
	public int do_getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> do_search(DTO dto) {
		return orderDaoImpl.do_search(dto);
	}
	
	@Override
	public List<?> do_search2(DTO dto) {
		return orderDaoImpl.do_search2(dto);
	}
	
	@Override
	public int do_update(DTO dto) {
		return orderDaoImpl.do_update(dto);
	} 
	
	@Override
	public int do_confirm(DTO dto){
		return orderDaoImpl.do_confirm(dto);
	}
	
	@Override
	public int do_stk_update(DTO dto){
		return orderDaoImpl.do_stk_update(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return orderDaoImpl.do_delete(dto);
	}

}
