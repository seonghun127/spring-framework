package com.sist.retail.sale.svc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.sale.dao.SaleDao;
import com.sist.retail.sale.dao.SaleDaoImpl;
/**
 *  판매 서비스
 * @author Kang Hyun Gu
 * @since 2017-12-19
 * @version 0.01
 *
 */
@Service
public class SaleSvcImpl implements SaleSvc {
	Logger logger=Logger.getLogger(this.getClass());	
	@Autowired
	SaleDao saleDaoImpl;

	@Override
	public int do_saleTx(DTO dto, DTO dto1) throws DataAccessException {
		// TODO Auto-generated method stub
		/*logger.debug("insert flag:"+saleDaoImpl.do_add(dto));
		logger.debug("update flag:"+saleDaoImpl.do_update(dto1));*/
		int i=-1;
		i = saleDaoImpl.do_add(dto);
		logger.debug("i:"+i);
		i += saleDaoImpl.do_update(dto1);
		
		logger.debug("i:"+i);
		return i;
	}

}
