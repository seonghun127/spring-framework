package com.sist.retail.storeService.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.storeService.dao.StoreServiceDao;

@Service
public class StoreServiceSvcImpl implements StoreServiceSvc {
	
	@Autowired
	private StoreServiceDao storeServiceDao;
	
	@Override
	public DTO do_searchOne(DTO dto) {
		return storeServiceDao.do_searchOne(dto);
	}

	@Override
	public List<?> do_search(DTO dto) {
		return storeServiceDao.do_search(dto);
	}

	@Override
	public List<?> do_searchPaging(DTO dto) {
		return storeServiceDao.do_searchPaging(dto);
	}	
}
