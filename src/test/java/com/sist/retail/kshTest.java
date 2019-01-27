package com.sist.retail;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sist.retail.order.dao.OrderDaoImpl;
import com.sist.retail.vo.ProductStockVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

@WebAppConfiguration
public class kshTest {
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Autowired
	private OrderDaoImpl	orderDaoImpl ;
	

	
	@Before
	public void setUp() {
		mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
		
	}
	
	/**
	 * 객체생성 테스트 
	 */
	@Test
	public void createBean() {
		assertThat(this.orderDaoImpl, is(notNullValue()));
	}
	
	/**
	 * 발주 신청 지점 출력 테스트 
	 */
	@Test
	public void search() {
		
		Map<String, String> inParam = new HashMap<String, String>();
		inParam.put("firAdr", "서울");
		inParam.put("secAdr", "마포구");
		ProductStockVo vo = new ProductStockVo();
		vo.setParam(inParam);
		
		List<ProductStockVo> mList = (List<ProductStockVo>) orderDaoImpl.do_search(vo);
		log.info("=====Order Store do_search========");
		log.info("list_size is "+mList.size());
		log.info("===================================");
	}
	
	/**
	 * 발주 지점 상세 조회 테스트
	 */
	@Test
	public void search2(){
		ProductStockVo vo = new ProductStockVo();
		vo.setStoId("11111");
		List<ProductStockVo> list = (List<ProductStockVo>) orderDaoImpl.do_search2(vo);
		log.info("=====Order Store do_search2========");
		log.info("list_size is "+list.size());
		log.info("===================================");
	}
	
	/**
	 * 발주 승인 테스트
	 */
	@Test
	public void update(){
		int order_state_update_flag = 0;
		
		ProductStockVo inVo = new ProductStockVo();
		inVo.setStoId("11111");
		
		order_state_update_flag = orderDaoImpl.do_update(inVo);
		log.info("order_state_update_flag : "+order_state_update_flag);
		
		List<ProductStockVo> list = (List<ProductStockVo>) orderDaoImpl.do_search2(inVo);
		for(ProductStockVo vo : list){
			assertThat(vo.getOrdCd(), is("A3_03"));
		}
	}
	
	/**
	 * 발주 확인 테스트 (발주 상태 및 재고량 업데이트)
	 */
	@Test
	public void approval(){
		int stk_update_flag = 0;
		int order_state_update_flag = 0;
		
		ProductStockVo inVo = new ProductStockVo();
		inVo.setStoId("11111");
		
		List<ProductStockVo> orderDetailList = (List<ProductStockVo>) orderDaoImpl.do_search2(inVo);
		log.info("orderDetailList : "+orderDetailList);
		
		for(ProductStockVo vo : orderDetailList){
			inVo.setOrdCnt(vo.getOrdCnt());
			inVo.setPdtNo(vo.getPdtNo());
			inVo.setStoId("11111");
			
			stk_update_flag = orderDaoImpl.do_stk_update(inVo);			// 재고량 업데이트
			log.info("=====================");
			log.info("stk_update_flag = "+stk_update_flag);
			log.info("=====================");
		}
		
		order_state_update_flag = orderDaoImpl.do_confirm(inVo);		// 발주상태 업데이트 (신청 -> 승인)
		log.info("=====================");
		log.info("order_state_update_flag = "+order_state_update_flag);
		log.info("=====================");
	}
}














