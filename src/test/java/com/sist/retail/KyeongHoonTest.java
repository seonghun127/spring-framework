package com.sist.retail;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sist.retail.ans.dao.AnsDaoImpl;
import com.sist.retail.common.DTO;
import com.sist.retail.qna.dao.QnaDaoImpl;
import com.sist.retail.storeService.dao.StoreServiceDao;
import com.sist.retail.storeService.dao.StoreServiceDaoImpl;
import com.sist.retail.storeService.dao.StoreServiceSaleDao;
import com.sist.retail.storeService.dao.StoreServiceSaleDaoImpl;
import com.sist.retail.vo.MasterStoreVo;
import com.sist.retail.vo.NoticeQnaVo;
import com.sist.retail.vo.SaleTermVo;
import com.sist.retail.vo.SaleTodayVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

@WebAppConfiguration
public class KyeongHoonTest {
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Autowired
	private StoreServiceDaoImpl storeServcieDaoImpl;
	
	@Autowired
	private StoreServiceSaleDaoImpl storeServiceSaleDaoImpl;
	
	@Autowired
	private QnaDaoImpl noticeQnaDaoImpl;
	
	@Autowired
	private AnsDaoImpl noticeAnsDaoImpl;
	
	@Before
	public void setUp() {
		mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	/**
	 * 기간별 매출 정보
	 */
	@Test
	public void searchTermSale() {
		SaleTermVo inVo = new SaleTermVo();
		
		String sdt = "2017-12-01";
		String edt = "2017-12-31";
		inVo.setStartDt(sdt);
		inVo.setEndDt(edt);
		inVo.setStoId("11111");
		
		List<SaleTermVo> sList = (List<SaleTermVo>) storeServiceSaleDaoImpl.do_searchTerm(inVo);
		log.info("===================salTermList=============");
		log.info("sList:"+sList);
		log.info("===========================================");
	}
	
	/**
	 * 금일 매출 상세정보 
	 */
	@Test
	public void searchSale() {
		SaleTodayVo inVo = new SaleTodayVo();
		inVo.setStoId("11111");
		List<SaleTodayVo> sList = (List<SaleTodayVo>) storeServiceSaleDaoImpl.do_search(inVo);
		log.info("==========salTodayList============");
		log.info(sList);
		log.info("===========================================");
		
	}
	
	/**
	 * 객체생성 테스트 
	 */
	@Test
	public void createBean() {
		assertThat(this.storeServcieDaoImpl, is(notNullValue()));
		assertThat(this.storeServiceSaleDaoImpl, is(notNullValue()));
		assertThat(this.noticeQnaDaoImpl, is(notNullValue()));
		assertThat(this.noticeAnsDaoImpl, is(notNullValue()));
	}
	
	/**
	 * 전체조회 테스트 
	 */
	@Test
	public void searchStore() {
		
		List<MasterStoreVo> mList = (List<MasterStoreVo>) storeServcieDaoImpl.do_search(new DTO());
		log.info("=====Master Store do_search========");
		log.info(mList);
		log.info("===================================");
	}
	
	/**
	 * 검색조건 테스트 
	 */
	@Test
	public void searchStoreFilter() {
		MasterStoreVo inVo = new MasterStoreVo();
		Map<String,String> searchParam=new HashMap<String, String>();
		searchParam.put("search_div", "10");
		searchParam.put("search_word", "홍대");
		log.debug("DAO do_search \n"+searchParam);
		inVo.setParam(searchParam);
		List<MasterStoreVo> mList = (List<MasterStoreVo>) storeServcieDaoImpl.do_search(inVo);
		log.info("filter search:"+mList.size());
	}
	
	/**
	 * 단건조회 테스트 
	 */
	@Test
	public void searchStoreOne() {
		MasterStoreVo inVo = new MasterStoreVo();
		inVo.setStoId("00001");
		MasterStoreVo mvo = (MasterStoreVo) storeServcieDaoImpl.do_searchOne(inVo);
		System.out.println("mvo:"+mvo);
	}
	
	/**
	 * QnA 리스트 출력
	 */
	@Test
	public void searchQna() {
		List<NoticeQnaVo> list = (List<NoticeQnaVo>) noticeQnaDaoImpl.do_search(new DTO());
		log.info("======serarchQnaList=====");
		log.info(list);
	}
	
	/**
	 * QnA 상세보기
	 */
	@Test
	public void searchOneQna() {
		NoticeQnaVo qvo = new NoticeQnaVo();
		qvo.setQaNo(1);
		NoticeQnaVo notice = (NoticeQnaVo) noticeQnaDaoImpl.do_searchOne(qvo);
		log.info("======serarchQnaDetail=====");
		log.info(notice);
	}
	
	/**
	 * Qna 삭제
	 */
	@Test
	public void deleteQna() {
		NoticeQnaVo qvo = new NoticeQnaVo();
		qvo.setAnsNo(1);
		
		int flag=noticeAnsDaoImpl.do_delete(qvo);
		
		if(flag>0) {
			qvo.setQaNo(1);
			noticeQnaDaoImpl.do_delete(qvo);
		}
		
	}
	
	/**
	 * QnA 등록
	 */
	@Test
	public void insertQna() {
		NoticeQnaVo qvo = new NoticeQnaVo();
		qvo.setQaNo(1);
		qvo.setTitle("원빈");
		qvo.setQaCon("안녕하세요~멍빈빈청아~");
		//qvo.setQaDt(new java.util.Date());
		qvo.setStoId("11111");
		
		int flag = noticeQnaDaoImpl.do_add(qvo);
		log.info("======Insert QnA=====");
		log.info("flag:"+flag);
	}
	
	/**
	 * Ans 등록
	 */
	@Test
	public void insertAns() {
		NoticeQnaVo qvo = new NoticeQnaVo();
		qvo.setAnsNo(1);
		qvo.setAnsCon("원빈님 사랑해요~");
		//qvo.setAnsDt(new java.util.Date());
		qvo.setQaNo(1);
		
		int flag = noticeAnsDaoImpl.do_add(qvo);
		log.info("======Insert Ans=====");
		log.info("flag:"+flag);
	}
	
	/**
	 * QnA 수정
	 */
	@Test
	public void updateQna() {
		NoticeQnaVo qvo = new NoticeQnaVo();
		qvo.setQaNo(1);
		qvo.setTitle("안녕");
		qvo.setQaCon("안녕하세요~멍청아~");
		//qvo.setQaDt(new java.util.Date());
		qvo.setStoId("11111");
		int flag = noticeQnaDaoImpl.do_update(qvo);
		log.info("======update QnA=====");
		log.info("flag:"+flag);
	}
	
	/**
	 * Ans 수정
	 */
	@Test
	@Ignore
	public void updateAns() {
		NoticeQnaVo qvo = new NoticeQnaVo();
		qvo.setAnsNo(1);
		qvo.setAnsCon("네네 선생님");
		//qvo.setAnsDt(new java.util.Date());
		qvo.setQaNo(1);
		
		int flag = noticeAnsDaoImpl.do_update(qvo);
		log.info("======update Ans=====");
		log.info("flag:"+flag);
	}
	
}
