package com.sist.retail.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.notice.dao.NoticeDao;

@Service
public class NoticeSvcImpl implements NoticeSvc {

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public int do_add(DTO dto) {
		return noticeDao.do_add(dto);
	}

	@Override
	public List<?> do_search(DTO dto) {
		return noticeDao.do_search(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		return noticeDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		return noticeDao.do_update(dto);
	}

	public DTO do_detail(DTO dto) {
		return noticeDao.do_detail(dto);
	}
	
	public DTO do_searchGnt(DTO dto) {
		return noticeDao.do_searchGnt(dto);
	}
}
