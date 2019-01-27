package com.sist.retail.order.dao;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.retail.common.DTO;
import com.sist.retail.vo.ProductStockVo;

@Repository
public class OrderDaoImpl implements OrderDao {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private final String namespace = "com.sist.retail.mappers.master";

	@Override
	public List<?> do_search(DTO dto) {
		String statement = this.namespace + ".order_store_list";

		Map<String, String> searchParam = null;
		searchParam = dto.getParam();

		log.debug("DAO do_search \n" + searchParam);

		int pageSize = 10;
		int pageNum = 1;

		if (null != searchParam.get("page_size")) {
			pageSize = Integer.parseInt(searchParam.get("page_size").toString());
		}
		if (null != searchParam.get("page_num")) {
			pageNum = Integer.parseInt(searchParam.get("page_num").toString());
		}
		
		log.debug("pageSize : " + pageSize);
		log.debug("@@pageNum : " + pageNum);

		List<ProductStockVo> list = this.sqlSessionTemplate.selectList(statement, searchParam);

		return list;
	}

	/**
	 * 발주 신청
	 */
	@Override
	public int do_add(DTO dto) {
		int flag = 0;
		String statement = this.namespace + ".order_application";

		ProductStockVo productStockVo = (ProductStockVo) dto;
		log.debug("statement\n" + statement);

		flag = this.sqlSessionTemplate.insert(statement, productStockVo);

		log.debug("do_add flag:" + flag);
		return flag;
	}

	/**
	 * 발주 신청 지점 출력
	 * 
	 * @param dto
	 * @return
	 */
	public List<?> do_search2(DTO dto) {
		String statement = this.namespace + ".order_print";
		
		Map<String, String> searchParam = null;
		searchParam = dto.getParam();

		log.debug("DAO do_search \n" + searchParam);

		int pageSize = 10;
		int pageNum = 1;

		if (null != searchParam.get("page_size")) {
			pageSize = Integer.parseInt(searchParam.get("page_size").toString());
		}
		if (null != searchParam.get("page_num")) {
			pageNum = Integer.parseInt(searchParam.get("page_num").toString());
		}
		
		log.debug("pageSize : " + pageSize);
		log.debug("@@pageNum : " + pageNum);

		List<ProductStockVo> list = this.sqlSessionTemplate.selectList(statement, searchParam);
		log.debug("do_search2 list : " + list);

		return list;
	}

	/**
	 * 발주 승인
	 * 
	 * @param dto
	 * @return
	 */
	public int do_confirm(DTO dto) {
		int flag = 0;
		String statement = this.namespace + ".order_confirm";
		log.debug("statement\n" + statement);

		ProductStockVo vo = (ProductStockVo) dto;

		flag = this.sqlSessionTemplate.update(statement, vo);
		log.debug("do_confirm flag:" + flag);

		return flag;
	}

	/**
	 * 발주 승인 후 재고량 업데이트
	 * 
	 * @param dto
	 * @return트
	 */
	public int do_stk_update(DTO dto) {
		int flag = 0;
		String statement = this.namespace + ".stock_count_change";
		log.debug("statement\n" + statement);

		ProductStockVo vo = (ProductStockVo) dto;

		flag = this.sqlSessionTemplate.update(statement, vo);
		log.debug("do_stk_update flag:" + flag);

		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		String statement = this.namespace + ".order_delete";

		ProductStockVo productStockVo = (ProductStockVo) dto;
		log.debug("statement\n" + statement);

		flag = this.sqlSessionTemplate.delete(statement, productStockVo);

		return flag;
	}

	/**
	 * 발주 신청 상태 업데이트
	 */
	@Override
	public int do_update(DTO dto) {
		int flag = 0;
		String statement = this.namespace + ".order_state_change";

		ProductStockVo productStockVo = (ProductStockVo) dto;
		log.debug("statement\n" + statement);

		flag = this.sqlSessionTemplate.update(statement, productStockVo);

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
