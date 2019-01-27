package com.sist.retail;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sist.retail.master.*;
import com.sist.retail.sale.dao.SaleDaoImpl;
import com.sist.retail.vo.MasterStoreVo;
import com.sist.retail.vo.ProductStockVo;
import com.sist.retail.vo.SaleProductVo;
import com.sist.retail.vo.SaleVo;
import com.sist.retail.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})

@WebAppConfiguration
public class kangTest {
	private Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Autowired
	private SaleDaoImpl saleDaoImpl;
	
	@Before
	public void setUp() {
		mockMvc=MockMvcBuilders.webAppContextSetup(ctx).build();

	}
	
	//객체생성
	@Test
	public void createBean() {
		assertThat(this.saleDaoImpl, is(notNullValue()));
	}
	@Test
	public void reg(){
		int flag=0;
		SaleVo vo=new SaleVo();
		vo.setAgeCd("A3_01");
		vo.setSexCd("A2_03");
		vo.setStoId("11111");
		vo.setSalCnt(3);
		vo.setPdtNo("00001");
			
		try {
			flag+=this.saleDaoImpl.do_add(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		log.info("add flag:"+flag);
		
		
	}
	@Test
	public void update(){
		int flag=0;
		ProductStockVo vo =new ProductStockVo();
		vo.setPdtNo("00001");
		vo.setStoId("11111");
		vo.setStkCnt(3);
			
		try {
			flag+=this.saleDaoImpl.do_update(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		log.info("add flag:"+flag);
		
		
	}

}
