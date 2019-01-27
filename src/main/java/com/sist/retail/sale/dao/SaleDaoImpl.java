package com.sist.retail.sale.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sist.retail.common.DTO;
import com.sist.retail.common.DuplicationUserIdException;
import com.sist.retail.vo.ProductStockVo;
import com.sist.retail.vo.SaleProductVo;
import com.sist.retail.vo.SaleVo;;

/**
 * 판매 DAO
 * 
 * @author Kang Hyun Gu
 * @since 2017-12-19
 * @version 0.01
 *
 */
@Repository
public class SaleDaoImpl implements SaleDao {
	private Logger log = Logger.getLogger(this.getClass());


	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private final String namespace = "com.sist.retail.mappers.sale";

	@Override
	public List<?> do_search(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_add(DTO dto) {
		String statement = this.namespace + ".do_sale";
		SaleVo saleVo = (SaleVo) dto;
		int flag = 0;

		try {
			flag = this.sqlSessionTemplate.update(statement, saleVo);
		} catch (DataAccessException du) {
			throw du;
		}

		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_update(DTO dto) {
		String statement = this.namespace + ".do_update";
		ProductStockVo vo = (ProductStockVo) dto;
		int flag = 0;

		try {
			flag = this.sqlSessionTemplate.update(statement, vo);
		} catch (DataAccessException du) {
			throw du;
		}

		return flag;
	}

	@Override
	public int do_excelDown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_excelUp(List<?> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO do_searchOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
