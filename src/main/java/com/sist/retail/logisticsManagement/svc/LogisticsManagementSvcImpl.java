package com.sist.retail.logisticsManagement.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.logisticsManagement.dao.LogisticsManagementDao;


@Service
public class LogisticsManagementSvcImpl implements LogisticsManagementSvc
{
	@Autowired
	private LogisticsManagementDao logisticsManagementDao;

	@Override
	public List<?> do_search_favo(DTO dto) {
		
		return logisticsManagementDao.do_search_favo(dto);
	}
	
	@Override
	public List<?> do_search(DTO dto) {
		
		return logisticsManagementDao.do_search(dto);
	}

	@Override
	public List<?> do_search_group(DTO dto) {
		
		return logisticsManagementDao.do_search_group(dto);
	}
	
	@Override
	public int do_add(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO do_searchOne(DTO dto) {
		// TODO Auto-generated method stub
		return logisticsManagementDao.do_searchOne(dto);
	}

	

	
}
