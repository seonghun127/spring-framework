package com.sist.retail.sale.svc;

import org.springframework.dao.DataAccessException;

import com.sist.retail.common.DTO;

public interface SaleSvc {
	public int do_saleTx(DTO dto, DTO dto1) throws DataAccessException;
}
