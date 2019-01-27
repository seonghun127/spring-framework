package com.sist.retail.storeService.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sist.retail.common.DTO;
import com.sist.retail.vo.MasterStoreVo;

@Repository
public class StoreServiceDaoImpl implements StoreServiceDao {
	
	private Logger log = Logger.getLogger(this.getClass());
    
   @Autowired
   private JdbcTemplate jdbcTemplate;
   
   @Autowired
   private SqlSessionTemplate sqlSessionTemplate;
   
   private final String namespace ="com.sist.retail.mappers.storeService";  
	
   /**
    * 검색조건 및 검색조건 없을 때 지점정보 조회. 
    */
	@Override
	public List<?> do_search(DTO dto) {
		
		String statement = this.namespace+".do_searchStore";
		
		Map<String,String> searchParam=new HashMap<String, String>();
		MasterStoreVo msVo = (MasterStoreVo) dto;
		if(msVo.getGugun().equals("전체")){
			msVo.setGugun(null);
		}
		searchParam.put("sido", msVo.getSido());
		searchParam.put("gugun", msVo.getGugun());
		
		System.out.println("DAO do_search \n"+searchParam);
		
		return sqlSessionTemplate.selectList(statement, searchParam);
	}
	
	/**
	 * 페이징 처리하여 지점정보 조회
	 * @param dto
	 * @return
	 */
	public List<?> do_searchPaging(DTO dto) {
		String statement = this.namespace+".do_searchStorePaging";
		
		Map<String,String> searchParam=new HashMap<String, String>();
		searchParam = dto.getParam();
		MasterStoreVo msVo =(MasterStoreVo) dto;
		
		searchParam.put("sido", msVo.getSido());
		searchParam.put("gugun", msVo.getGugun());
		
		return sqlSessionTemplate.selectList(statement, searchParam);
	}
	
	/**
	 * 지점 상세보기 
	 * 지점 Update시 화면에 호출해줌. 
	 */
	@Override
	public DTO do_searchOne(DTO dto) {
		String statement = this.namespace+".do_searchOneStore";
		
		try{
	        MasterStoreVo inVo = (MasterStoreVo) dto;
	        log.debug("======================");
	        log.debug("pan:\t"+inVo.getStoId());
	        log.debug("======================");         
	        MasterStoreVo pan = this.sqlSessionTemplate.selectOne(statement, inVo);
	        log.debug("======================");
	        log.debug("return:\t"+pan);
	        log.debug("======================");          
	      
	        return pan;
	        
        }catch(DataAccessException dae){
        	throw dae; 
        }
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
	public int do_getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
