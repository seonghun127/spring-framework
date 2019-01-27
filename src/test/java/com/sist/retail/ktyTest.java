package com.sist.retail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
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

import com.sist.retail.common.DTO;
import com.sist.retail.master.dao.MasterDaoImpl;
import com.sist.retail.notice.dao.NoticeDaoImpl;
import com.sist.retail.vo.MasterStoreVo;
import com.sist.retail.vo.NoticeQnaVo;
import com.sist.retail.vo.NoticeVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

@WebAppConfiguration
public class ktyTest {
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Autowired
	private MasterDaoImpl masterDaoImpl;
	
	@Autowired
	private NoticeDaoImpl noticeDaoImpl;
	
	private List<MasterStoreVo> list;
	
	private List<NoticeVo> list2;
	
/*	@Before
	public void setUp() {
		
		mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
		
		list = Arrays.asList(
				 new MasterStoreVo("00030","신촌점","이지훈","010-000-0000","경기도 부천시 원미구",98.1,98.4,"1111","1",1)
				,new MasterStoreVo("00031","홍대점","김태열","010-000-0001","경기도 부천시 원미",98.2,98.5,"1111","1",1)
				,new MasterStoreVo("00032","강남점","강현구","010-000-0002","경기도 부천시 원",98.3,98.6,"1111","1",1)
				);
				
		log.info("Before : "+list);
	}
	
	//객체생성
	@Test
	public void createBean() {
		assertThat(this.masterDaoImpl, is(notNullValue()));
	}
	

	//회원가입
	@Test
	public void reg(){
		int flag=0;
		
		for(MasterStoreVo vo:list){
			flag+=this.masterDaoImpl.do_add(vo);
		}
		log.info("add flag:"+flag);
		
		assertThat(flag, is(list.size()));
		
	}
	
	//로그인
	@Test
	public void login(){
		MasterStoreVo vo=list.get(0);
		
		MasterStoreVo afterAdd=(MasterStoreVo)masterDaoImpl.do_login(vo);
		log.info("afterAdd : "+afterAdd.getchk());
		
	}
	
	//수정하기 위한 아이디 가져오기
	@Test
	public void search(){
		MasterStoreVo vo = new MasterStoreVo();
		vo.setStoId("00001");
		MasterStoreVo search = (MasterStoreVo) masterDaoImpl.do_searchOne(vo);

		log.info("search : "+search);
	}
	
	//수정하기
	@Test
	public void update(){
		int flag=0;
		MasterStoreVo vo = list.get(0);
		vo.setStoId("00001");
		vo.setStoNm("메롱");
		vo.setPwd("1111");
		vo.setOwnId("김퉤열");
		vo.setPhoNo("01012341234");
		vo.setAdr("여기다");
		
		flag = masterDaoImpl.do_update(vo);
		
		log.info("update Test=============================================================");
		log.info("update"+flag);
		
	}*/
	
	
	@Before
	public void setUp2() {
		
		mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
		
		list2 = Arrays.asList(
				 new NoticeVo(20,"테스트제목","관리자","테스트내용","2017-12-26","A4_01","","","","", ""),
				 new NoticeVo(21,"테스트제목2","관리자","테스트내용2","2017-12-26","A4_01","","","","",""),
				 new NoticeVo(22,"테스트제목3","관리자","테스트내용3","2017-12-26","A4_01","","","","","")
				);
				
		log.info("Before : "+list2);
	}
	
	
	@Test
	//객체생성
	public void createBean2() {
		assertThat(this.noticeDaoImpl, is(notNullValue()));
	}
	
	//게시글 등록
	@Test
	public void write(){
		int flag=0;
		
		for(NoticeVo vo:list2){
			flag+=this.noticeDaoImpl.do_add(vo);
		}
		log.info("add flag:"+flag);
		
		assertThat(flag, is(list.size()));
	}

	//게시글 검색
	public void search2(){
		
	}
	
	//게시글 상세보기
	public void detail(){
		NoticeVo vo = new NoticeVo();
		vo.setMemoNo(20);
		MasterStoreVo search = (MasterStoreVo) noticeDaoImpl.do_search(vo);

		log.info("search : "+search);
	}
	
	//게시글 수정
	public void modify(){
		int flag=0;
		NoticeVo vo = list2.get(0);
		vo.setMemoNo(23);
		vo.setTitle("테스트제목4");
		
		flag = noticeDaoImpl.do_update(vo);
		
		log.info("update"+flag);
	}
	
	//게시글 삭제
	public void delete(){
		
	}
}
	
