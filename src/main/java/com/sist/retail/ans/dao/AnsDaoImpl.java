package com.sist.retail.ans.dao;

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
public class AnsDaoImpl implements AnsDao {

	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String namespace ="com.sist.retail.mappers.noticeQna";
	
	@Override
	public List<?> do_search(DTO dto){
		return null;
	}
		
	@Override
	public int do_add(DTO dto) {
		String statement=this.namespace+".do_insertAns";
		int flag=0;
		
		try{
			flag = this.sqlSessionTemplate.update(statement,dto);
		}catch(DataAccessException du){
			throw new DuplicationUserIdException("게시판 삽입 오류");
		}
		return flag;	
	}

	@Override
	public int do_delete(DTO dto) {
		int flag = 0;
		String statement = this.namespace+".do_deleteAns";

		NoticeQnaVo qVo = (NoticeQnaVo)dto;
		
		log.debug("statement\n" + statement.toString());
		log.debug("NoticeQnaVo\n" + qVo.toString());

		flag = this.sqlSessionTemplate.update(statement,qVo);
		log.debug("do_delete flag:"+flag);
		return flag;
	}

	@Override
	public int do_update(DTO dto) {
		String statement= this.namespace+".do_updateAns";
		
		int flag = 0;
		NoticeQnaVo qVo = (NoticeQnaVo) dto;
		log.debug("NoticeQnaVo\n" + qVo.toString());

		flag = this.sqlSessionTemplate.update(statement,qVo);

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

	@Override
	public DTO do_detail(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
