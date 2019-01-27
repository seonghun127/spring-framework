package com.sist.retail.qna.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.qna.dao.QnaDao;

@Service
public class QnaSvcImpl implements QnaSvc {
	
	@Autowired
	private QnaDao noticeQnaDao;
	
	@Override
	public int do_add(DTO dto) {
		
		return noticeQnaDao.do_add(dto);
	}

	@Override
	public List<?> do_search(DTO dto) {
		
		return noticeQnaDao.do_search(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		
		return noticeQnaDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {
		
		return noticeQnaDao.do_update(dto);
	}
	
	@Override
	public List<?> do_detail(DTO dto){
		
		return noticeQnaDao.do_detail(dto);
	}

}
