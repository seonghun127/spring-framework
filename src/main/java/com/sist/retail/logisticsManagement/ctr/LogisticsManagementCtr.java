package com.sist.retail.logisticsManagement.ctr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sist.retail.common.StringUtil;
import com.sist.retail.logisticsManagement.svc.LogisticsManagementSvc;

import com.sist.retail.vo.ProductStockVo;
import com.sist.retail.vo.SaleProductVo;
import com.sist.retail.vo.WeatherVO;

@Controller
@RequestMapping("logisticsManagement")
public class LogisticsManagementCtr
{
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private LogisticsManagementSvc logisticsManagementSvc;
	
	
	
	/**
	 * 재고 조회
	 * @param req
	 * @param model
	 * @return List<?>
	 */
	@RequestMapping(value="search.do", method=RequestMethod.POST)
	public String do_search(HttpServletRequest req, Model model){
		String []city={"서울","부산","대구","인천","광주","대전","울산","경기","강원","충청","전라","경상","제주"};
		WeatherVO weatherVo=new WeatherVO();
		ProductStockVo pVo = new ProductStockVo();
		SaleProductVo sVo1 = new SaleProductVo();
		SaleProductVo sVo2 = new SaleProductVo();
		SaleProductVo wVo = new SaleProductVo();
		String stoId = req.getParameter("stoId");
		wVo.setStoId(stoId);
		wVo=(SaleProductVo) logisticsManagementSvc.do_searchOne(wVo);
		String addr=wVo.getAdr();
		
		Pattern[] p=new Pattern[city.length];
		for(int i=0;i<p.length;i++){
			p[i]=Pattern.compile(city[i]);
		}
		Matcher[] m=new Matcher[city.length];
		for(int i=0;i<m.length;i++){
			m[i]=p[i].matcher(addr);
			if(m[i].find()==true){
				switch (i) {
				case 0:addr="seoul";
				break;
				case 1:addr="busan";
				break;
				case 2:addr="daegu";
				break;
				case 3:addr="incheon";
				break;
				case 4:addr="gwangju";
				break;
				case 5:addr="daejeon";
				break;
				case 6:addr="ulsan";
				break;
				case 7:addr="seoul";
				break;
				case 8:addr="chuncheon";
				break;
				case 9:addr="daejeon";
				break;
				case 10:addr="gwangju";
				break;
				case 11:addr="busan";
				break;
				case 12:addr="jeju";
				break;
				
				
		
				}
			}
		}
		
		try {
			weatherVo=SearchWeather.weather(addr);
			System.out.println("=======날=======씨");
			System.out.println(weatherVo.getWeather());
			log.info("========================");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		// param
		Map<String, String> inParam = new HashMap<String, String>();

		inParam.put("page_size", StringUtil.nvl(req.getParameter("page_size"), "10"));
		inParam.put("page_num", StringUtil.nvl(req.getParameter("page_num"), "1"));
		
		
		pVo.setPage_size(StringUtil.nvl(req.getParameter("page_size"), "10"));
		pVo.setPage_num(StringUtil.nvl(req.getParameter("page_num"), "1"));
		pVo.setParam(inParam);
		pVo.setStoId(stoId);
		//sVo2.setStoId(stoId);
		log.debug("========================");
		log.debug(inParam);
		log.debug("========================");
  
		String group = req.getParameter("group");
		log.info("group:"+group);
		List<ProductStockVo> list;		
		if(group.equals("") ){
			list = (List<ProductStockVo>)logisticsManagementSvc.do_search(pVo);
		}else{
			pVo.setPdtGb(group);
		   // service call
		   list = (List<ProductStockVo>)logisticsManagementSvc.do_search_group(pVo);
		  
		}
		
		log.info("stoId@@@"+stoId);
		sVo1.setPdtGb("라면");
		sVo2.setPdtGb("과자");
		//sVo1.setStoId(stoId);
		//sVo2.setStoId(stoId);
				
		log.info("sVo1:"+sVo1.getPdtGb());
		log.info("sVo2:"+sVo2.getPdtGb());
		List<SaleProductVo> list2 = (List<SaleProductVo>)logisticsManagementSvc.do_search_favo(sVo1);
		log.info("list2.get(0) : " + list2.get(0));
		List<SaleProductVo> list3 = (List<SaleProductVo>)logisticsManagementSvc.do_search_favo(sVo2);
		log.info("list3.get(0) : " + list3.get(0));
		
		int total_cnt = 0;
		if(null != list && list.size() > 0){
			log.info("list.get(0) : " + list.get(0));
			total_cnt = list.get(0).getTotalNo();
			log.info("total_cnt"+total_cnt);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("list2",list2);
		model.addAttribute("list3",list3);
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("Weather", weatherVo);
	    model.addAttribute("group", group);
		return "logisticsManagement/stockView";
	}
	
	@RequestMapping(value="/stockView.do")	
	public String mainPage(HttpServletRequest req,HttpServletResponse response){
			return "logisticsManagement/stockView";
	}
	
	

}
