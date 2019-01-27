package com.sist.retail.logisticsManagement.dao;


import java.util.List;

import com.sist.retail.common.DTO;
import com.sist.retail.common.WorkDiv;

public interface LogisticsManagementDao extends WorkDiv 
{
	public List<?> do_search_group(DTO dto);
	public List<?> do_search_favo(DTO dto); 

}
