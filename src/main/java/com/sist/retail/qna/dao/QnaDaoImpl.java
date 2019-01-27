package com.sist.retail.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.sist.retail.common.DTO;
import com.sist.retail.common.DuplicationUserIdException;
import com.sist.retail.common.WorkDiv;
import com.sist.retail.vo.NoticeQnaVo;
import com.sist.retail.vo.NoticeVo;

@Repository
public class QnaDaoImpl implements QnaDao {
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private final String namespace = "com.sist.retail.mappers.noticeQna";

	/**
	 * QnA 목록 보기
	 */
	@Override
	public List<?> do_search(DTO dto) {

		String statement = this.namespace + ".do_searchQna";

		Map<String, String> searchParam = null;
		
		searchParam = dto.getParam(); 
		log.debug("DAO do_search \n" + searchParam); 
		String searchWord = searchParam.get("search_word");
		
		int pageSize = 10; int pageNum = 1;
		
		if(null != searchParam.get("page_size")){ pageSize =
		Integer.parseInt(searchParam.get("page_size").toString()); } if(null
		!= searchParam.get("page_num")){ pageNum =
		Integer.parseInt(searchParam.get("page_num").toString()); }
		
		log.debug("pageSize : " + pageSize); log.debug("@@pageNum : " + pageNum);
		

		List<NoticeQnaVo> list = this.sqlSessionTemplate.selectList(statement, searchParam);
		
		log.info("========================");
		log.info("list.size() : " + list.size());
		log.info("========================");

		return list;
	}

	/**
	 * 문의 등록
	 */
	@Override
	public int do_add(DTO dto) {
		String statement = this.namespace + ".do_insertQna";
		int flag = 0;

		try {
			flag = this.sqlSessionTemplate.insert(statement, dto);
		} catch (DataAccessException du) {
			throw new DuplicationUserIdException("게시판 삽입 오류");
		}
		return flag;
	}

	/**
	 * 문의 삭제
	 */
	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		String statement = this.namespace + ".do_deleteQna";
		flag = this.sqlSessionTemplate.update(statement, dto);
		log.debug("do_delete flag:" + flag);

		return flag;
	}

	/**
	 * 문의 수정
	 */
	@Override
	public int do_update(DTO dto) {
		String statement = this.namespace + ".do_updateQna";
		
		int flag = 0;

		flag = this.sqlSessionTemplate.update(statement, dto);

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

	/**
	 * 문의 상세 보기
	 */
	@Override
	public DTO do_searchOne(DTO dto) {
		return null;
	}

	@Override
	public int do_getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> do_detail(DTO dto) {
		String statement = this.namespace + ".do_searchOneQna";
		
		List<NoticeQnaVo> list = this.sqlSessionTemplate.selectList(statement, dto);
		
		return list;
	}

}
