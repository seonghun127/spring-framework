package com.sist.retail.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.retail.common.DTO;
import com.sist.retail.vo.CommentVo;

@Repository
public class CommentDaoImpl implements CommentDao {
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private final String namespace = "com.sist.retail.mappers.notice";
	
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
	public List<?> do_search(DTO dto) {
		String statement = this.namespace + ".do_searchComment";
		CommentVo commentVo=(CommentVo)dto;
	

		return sqlSessionTemplate.selectList(statement,commentVo);
	}

	@Override
	public int do_add(DTO dto) {
		String statement = this.namespace + ".do_insertComment";
		CommentVo commentVo=(CommentVo)dto;
		return sqlSessionTemplate.insert(statement,commentVo);
	}

	@Override
	public int do_delete(DTO dto) {
		String statement = this.namespace + ".do_deleteComment";
		CommentVo commentVo=(CommentVo)dto;
		return sqlSessionTemplate.delete(statement,commentVo);
	}

	@Override
	public int do_update(DTO dto) {
		String statement = this.namespace + ".do_updateComment";
		CommentVo commentVo=(CommentVo)dto;
		return sqlSessionTemplate.update(statement,commentVo);
	}

	@Override
	public int do_deleteCasCade(DTO dto) {
		String statement=this.namespace+".do_deleteCommentCasCade";
		return sqlSessionTemplate.delete(statement,dto);
	}

}
