package com.sist.retail.notice.dao;

import java.util.List;
import java.util.Map;

import com.sist.retail.notice.*;
import com.sist.retail.vo.MasterStoreVo;
import com.sist.retail.vo.NoticeVo;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.sist.retail.common.*;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private final String namespace = "com.sist.retail.mappers.notice";
	
	// 게시글 검색(제목,내용)
	@Override
	public List<?> do_search(DTO dto) {
		String statement = this.namespace + ".do_search";

		Map<String, String> searchParam = null;
		searchParam = dto.getParam();

		return sqlSessionTemplate.selectList(statement, searchParam);
	}

	// 게시글 등록
	@Override
	public int do_add(DTO dto) throws DataAccessException {
		String statement = this.namespace + ".do_add";
		int flag = 0;
		NoticeVo noticeVo = (NoticeVo) dto;

		try {
			flag = this.sqlSessionTemplate.update(statement, noticeVo);
		} catch (DataAccessException du) {
			throw new DuplicationUserIdException("게시글 등록 오류");
		}
		return flag;
	}

	// 게시글 삭제
	@Override
	public int do_delete(DTO dto) {
		String statement = this.namespace + ".do_delete";
		int flag = 0;
		NoticeVo noticeVo = (NoticeVo) dto;

		try {
			flag = this.sqlSessionTemplate.update(statement, noticeVo);
		} catch (DataAccessException du) {
			throw new DuplicationUserIdException("게시글 삭제 오류");
		}
		return flag;
	}

	// 선택 게시글 불러오기
	@Override
	public DTO do_detail(DTO dto) {
		String statement = this.namespace + ".do_detail";
		
		NoticeVo noticeVo = (NoticeVo) dto;
		
		try{
	        noticeVo = (NoticeVo) dto;
        } catch (DataAccessException du) {
			throw new DuplicationUserIdException("선택 게시글 불러오기 오류");
		}
		return this.sqlSessionTemplate.selectOne(statement, noticeVo);
	}

	// 게시글 수정
	@Override
	public int do_update(DTO dto) {
		String statement = this.namespace + ".do_update";
		int flag = 0;
		NoticeVo noticeVo = (NoticeVo) dto;

		try {
			flag = this.sqlSessionTemplate.update(statement, noticeVo);
		} catch (DataAccessException du) {
			throw new DuplicationUserIdException("게시글 수정 오류");
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

		return null;
	}

	@Override
	public int do_getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//권한 불러오기
	@Override
	public DTO do_searchGnt(DTO dto) {
		String statement = this.namespace + ".do_searchGnt";
		return sqlSessionTemplate.selectOne(statement,dto);
	}

}
