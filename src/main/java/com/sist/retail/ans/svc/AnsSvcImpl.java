package com.sist.retail.ans.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.ans.dao.AnsDao;

@Service
public class AnsSvcImpl implements AnsSvc {

	@Autowired
	private AnsDao ansDao;

	@Override
	public int do_add(DTO dto) {

		return ansDao.do_add(dto);
	}

	@Override
	public List<?> do_search(DTO dto) {

		return null;
	}

	@Override
	public int do_delete(DTO dto) {

		return ansDao.do_delete(dto);
	}

	@Override
	public int do_update(DTO dto) {

		return ansDao.do_update(dto);
	}

}
