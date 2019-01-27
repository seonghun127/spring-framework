package com.sist.retail;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
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
import com.sist.retail.logisticsManagement.dao.LogisticsManagementDaoImpl;

import com.sist.retail.vo.ProductStockVo;
import com.sist.retail.vo.SaleProductVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

@WebAppConfiguration
public class kdhTest {
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Autowired
	private LogisticsManagementDaoImpl lmD;
	
	@Before
	public void setUp() {
		mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void createBean() {
		assertThat(this.lmD, is(notNullValue()));
	}
	/**
	 * 전체재고조회
	 */
	@Test
	public void test1() {
		
		List<ProductStockVo> mList = (List<ProductStockVo>) lmD.do_search(new DTO());
		log.debug("====ProductStockVo do_search========");
		log.debug(mList.size());
		log.debug("===================================");
	}
	/**
	 * 상품군별 재고조회
	 */
	@Test
	public void test2() {
		
		List<ProductStockVo> mList = (List<ProductStockVo>) lmD.do_search_group(new DTO());
		log.debug("====ProductStockVo do_search_group========");
		log.debug(mList.size());
		log.debug("===================================");
	}
	/**
	 * 상품군별 인기판매량 조회
	 */
	@Test
	public void test3() {
		
		List<SaleProductVo> mList = (List<SaleProductVo>) lmD.do_search_favo(new DTO());
		log.debug("====ProductStockVo do_search_favo========");
		log.debug(mList.size());
		log.debug("===================================");
	}
	
}
