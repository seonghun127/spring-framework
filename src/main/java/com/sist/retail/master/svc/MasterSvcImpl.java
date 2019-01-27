package com.sist.retail.master.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.master.dao.MasterDaoImpl;


@Service
public class MasterSvcImpl implements MasterSvc {

	@Autowired
	private MasterDaoImpl masterDaoImpl;
	
	@Override
	public int do_add(DTO dto) {
		return masterDaoImpl.do_add(dto);
	}
	
	@Override
	public int do_getCount() {
		return masterDaoImpl.do_getCount();
	}

	@Override
	public DTO do_login(DTO dto) {
		return masterDaoImpl.do_login(dto);
	}

	public int do_update(DTO dto) {
		return masterDaoImpl.do_update(dto);
	}

	public DTO do_searchOne(DTO dto) {
		return masterDaoImpl.do_searchOne(dto);
	}

	@Override
	public List<?> do_search(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> salRank(String div) {
		// TODO Auto-generated method stub
		return masterDaoImpl.salRank(div);
	}

	@Override
	public int do_addStock(DTO dto) {
		return masterDaoImpl.do_addStock(dto);
	}

}
