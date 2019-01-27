package com.sist.retail.master.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sist.retail.common.UnknownUserException;
import com.sist.retail.common.DTO;
import com.sist.retail.common.DuplicationUserIdException;
import com.sist.retail.vo.MasterStoreVo;

@Repository
public class MasterDaoImpl implements MasterDao {
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// myBatis.xml 경로
	private final String namespace = "com.sist.retail.mappers.master";

	@Override
	public List<?> do_search(DTO dto) {
		return null;
	}

	@Override
	public int do_add(DTO dto) throws DataAccessException {
		String statement = this.namespace + ".do_add";
		int flag = 0;

		MasterStoreVo masterStoreVo = (MasterStoreVo) dto;

		log.debug("masterStoreVo\n" + masterStoreVo.toString());

		try {
			// Query문의 몇 행을 수행했는지 Return
			flag = this.sqlSessionTemplate.insert(statement, masterStoreVo);
		} catch (DataAccessException du) {
			log.debug("du: " + du.getMessage());
			throw new DuplicationUserIdException("아이디 중복!");
		}
		return flag;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_update(DTO dto) {
		String statement = this.namespace + ".do_update";
		MasterStoreVo masterStoreVo = (MasterStoreVo)dto;
		int flag = 0;

		flag=this.sqlSessionTemplate.update(statement, masterStoreVo);
		
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
	 * 회원정보를 불러오기 위해 조회
	 */
	@Override
	public DTO do_searchOne(DTO dto) {
		
		String statement = this.namespace+".do_searchOne";
		
		try {
			MasterStoreVo masterStoreVo = (MasterStoreVo) dto;
			
			MasterStoreVo pan = this.sqlSessionTemplate.selectOne(statement, masterStoreVo);

			
			return pan;
		} catch (DataAccessException d) {
			throw new UnknownUserException("ID가 없습니다.");
		}
	}

	/**
	 * 테이블 행 수를 구함.
	 */
	@Override
	public int do_getCount() throws DataAccessException {
		String statement = this.namespace + ".do_getCount";
		int seq = 0;
		try {
			seq = this.sqlSessionTemplate.selectOne(statement);
		} catch (DataAccessException du) {
			log.debug("du: " + du.getMessage());
			throw new DuplicationUserIdException("아이디 중복!");
		}
		log.debug("master.getCount:" + seq);
		return seq;
	}

	@Override
	public DTO do_login(DTO dto) {
		String statement = this.namespace + ".do_login";

		try {
			// 로그인시 조회 값
			MasterStoreVo masterStoreVo = (MasterStoreVo) dto;

			MasterStoreVo pan = this.sqlSessionTemplate.selectOne(statement, masterStoreVo);
			if (null != this.sqlSessionTemplate.selectOne(statement, masterStoreVo)) {
				return pan;
			}
		} catch (DataAccessException du) {
			log.debug("du: " + du.getMessage());
			throw new DuplicationUserIdException("어우 헷갈려!");
		}
		return dto;
	}

	@Override
	public List<?> salRank(String div) {
		if(div.equals("sex")){
			String statement = this.namespace + ".sex_rank";
			return this.sqlSessionTemplate.selectList(statement);
		}
		else if(div.equals("age")){
			String statement = this.namespace + ".age_rank";
			return this.sqlSessionTemplate.selectList(statement);
		}
		else if(div.equals("time")){
			String statement = this.namespace + ".time_rank";
			return this.sqlSessionTemplate.selectList(statement);
		}
		return null;
		
	}
	/**
	 * 회원가입시 자동 지점
	 */
	@Override
	public int do_addStock(DTO dto) {
		String statement = this.namespace + ".do_addStock";
		
		return sqlSessionTemplate.insert(statement, dto);
	}
}
