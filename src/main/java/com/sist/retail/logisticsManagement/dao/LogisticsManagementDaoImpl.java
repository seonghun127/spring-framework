package com.sist.retail.logisticsManagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.retail.common.DTO;
import com.sist.retail.vo.MasterStoreVo;
import com.sist.retail.vo.ProductStockVo;
import com.sist.retail.vo.SaleProductVo;

@Repository
public class LogisticsManagementDaoImpl implements LogisticsManagementDao 
{
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//myBatis.xml 경로
	private final String namespace = "com.sist.retail.mappers.logisticsManagement";
	
	public List<?> do_search_group(DTO dto)
	{
		ProductStockVo pVo = (ProductStockVo)dto;
		//String statement = this.namespace+".do_search";
		//상품군별로 재고 출력 if문으로 받아서 처리할것 ?
		String statement = this.namespace+".do_search_group";
		
		return sqlSessionTemplate.selectList(statement,pVo);
	}
	
	
	@Override
	public List<?> do_search(DTO dto) {
		String statement = this.namespace+".do_search";
		Map<String,String> searchParam=new HashMap<String, String>();
		searchParam = dto.getParam();
		//ProductStockVo pVo = (ProductStockVo)dto;
		return sqlSessionTemplate.selectList(statement,dto);
	}
	@Override
	public List<?> do_search_favo(DTO dto) {
		String statement = this.namespace+".do_search_favo";
		SaleProductVo sVo = (SaleProductVo) dto;
		return sqlSessionTemplate.selectList(statement,sVo);
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
		String statement = this.namespace+".do_search_one";
		SaleProductVo sVo = (SaleProductVo) dto;
		return sqlSessionTemplate.selectOne(statement,sVo);
	}

	@Override
	public int do_getCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	

}
