package com.sist.retail.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.notice.dao.CommentDao;

@Service
public class CommentSvcImpl implements CommentSvc {
	@Autowired
	private CommentDao commentDao;

	@Override
	public List<?> do_search(DTO dto) {
		// TODO Auto-generated method stub
		return commentDao.do_search(dto);
	}

	@Override
	public int do_add(DTO dto) {
		// TODO Auto-generated method stub
		return commentDao.do_add(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return commentDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return commentDao.do_update(dto);
	}

	@Override
	public int do_deleteCasCade(DTO dto) {
		// TODO Auto-generated method stub
		return commentDao.do_deleteCasCade(dto);
	}
	
}
