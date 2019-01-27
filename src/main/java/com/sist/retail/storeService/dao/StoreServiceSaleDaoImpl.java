package com.sist.retail.storeService.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sist.retail.common.DTO;

@Repository
public class StoreServiceSaleDaoImpl implements StoreServiceSaleDao {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
   private JdbcTemplate jdbcTemplate;
	   
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   private final String namespace ="com.sist.retail.mappers.storeService"; 
	
   /**
    * 금일 매출확인.
    */
	@Override
	public List<?> do_search(DTO dto) {
		
		String statement = this.namespace+".do_searchTodaySale";
		
		return sqlSessionTemplate.selectList(statement, dto);
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
	
	/**
	 * 기간별 매출 
	 */
	public List<?> do_searchTerm(DTO dto) {
		String statement = this.namespace+".do_searchTermSale";
		
		return sqlSessionTemplate.selectList(statement, dto);
	}

}
