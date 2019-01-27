package com.sist.retail.order.ctr;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;
import com.sist.retail.common.DTO;
import com.sist.retail.common.StringUtil;
import com.sist.retail.order.dao.OrderDaoImpl;
import com.sist.retail.order.svc.OrderSvc;
import com.sist.retail.order.svc.OrderSvcImpl;
import com.sist.retail.vo.ProductStockVo;

/**
 * 발주 컨트롤러
 * @author 김성훈
 *
 */

@Controller
@RequestMapping("master")
public class OrderCtr {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private OrderSvc orderSvc;

	/**
	 * 발주 데이터 삽입
	 * @param request
	 * @param model
	 * @return String
	 * json으로 데이터를 넘길 예정
	 */
	@RequestMapping("do_order_insert.do")
	@ResponseBody
	public String do_order_insert(HttpServletRequest request, Model model){
		int flag = 0;
		HttpSession session = request.getSession();
		String stoId = (String)session.getAttribute("stoId");
		
		log.info("stoId:!@"+stoId);
		String ordCntList = StringUtil.nvl(request.getParameter("ordCntList"),"");
		String pdtNoList = StringUtil.nvl(request.getParameter("pdtNoList"),"");
		
		log.info("ordCntList:" + ordCntList);
		log.info("pdtNoList:" + pdtNoList);

		List<ProductStockVo> list = new ArrayList<ProductStockVo>();

		Gson gson = new Gson();
		List<String> listGson = gson.fromJson(ordCntList, List.class);
		List<String> listGson2 = gson.fromJson(pdtNoList, List.class);
		
		for (int i = 0; i < listGson.size(); i++) {
			ProductStockVo vo = new ProductStockVo();
			vo.setStoId(stoId);
			vo.setOrdCnt(Integer.parseInt(listGson.get(i)));
			vo.setPdtNo(listGson2.get(i));
			list.add(vo);
		}
		log.info("list:" + list);

		for(ProductStockVo vo : list){
			flag = this.orderSvc.do_add(vo);
		}
		JSONObject obj = new JSONObject();
		obj.put("flag", flag);

		return obj.toJSONString();
	}
	
	/**
	 * 발주 신청 지점 출력
	 * @param req
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping("search.do")
	public String do_order_search(HttpServletRequest request, Model model){
		ProductStockVo inVo = new ProductStockVo();
		// param
		Map<String, String> inParam = new HashMap<String, String>();
		
		String	gugun=StringUtil.nvl(request.getParameter("gugun"),"");
		if(!gugun.equals("전체")){
			gugun=gugun.toString();
			log.info("gugun : "+ gugun);
		}else{
			gugun="";
		}
		String sido = StringUtil.nvl(request.getParameter("sido"), "");
		if(sido.equals("전체")){
			sido="";
		}else{
			sido=sido.toString();
			log.info("sido : "+ sido);
		}
		
		inParam.put("sido", sido);
		inParam.put("gugun", gugun);
		inParam.put("page_size", StringUtil.nvl(request.getParameter("page_size"), "90"));
		inParam.put("page_num", StringUtil.nvl(request.getParameter("page_num"), "1"));

		inVo.setParam(inParam);

		log.info("========================");
		log.info(inParam);
		log.info("========================");
		
		List<ProductStockVo> list = (List<ProductStockVo>) orderSvc.do_search(inVo);
		
		model.addAttribute("list", list);
		
		// 총글수
		int total_cnt = 0;
		if (null != list && list.size() > 0) {
			log.debug("========================");
			log.debug(list.get(0));
			log.debug("========================");
			total_cnt = list.get(0).getTotalNo();
		}
		model.addAttribute("total_cnt", total_cnt);

		return "master/masterOrderStores";
	}
	
	/**
	 * 발주 상품 상세 정보 출력
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value="order_print.do",method=RequestMethod.GET)
	public String do_order_print(HttpServletRequest request, Model model){
		
		Map<String, String> inParam = new HashMap<String, String>();
		//String stoId = StringUtil.nvl(request.getParameter("stoId"),"");
		ProductStockVo inVo = new ProductStockVo();
		//inVo.setStoId(stoId);
		
		inParam.put("stoId", StringUtil.nvl(request.getParameter("stoId"),""));
		inParam.put("page_size", StringUtil.nvl(request.getParameter("page_size"), "10"));
		inParam.put("page_num", StringUtil.nvl(request.getParameter("page_num"), "1"));
		inVo.setParam(inParam);
		
		List<ProductStockVo> orderDetailList = (List<ProductStockVo>) orderSvc.do_search2(inVo);
		
		model.addAttribute("orderDetailList", orderDetailList);
	
		// 총글수
		int total_cnt = 0;
		if (null != orderDetailList && orderDetailList.size() > 0) {
			log.debug("========================");
			log.debug(orderDetailList.get(0));
			log.debug("========================");
			total_cnt = orderDetailList.get(0).getTotalNo();
		}
		model.addAttribute("total_cnt", total_cnt);
		
		return "master/masterOrderDetail";
	}
	
	/**
	 * 발주 확인 (재고 및 발주 상태 업데이트)
	 * @param request
	 * @param model
	 * @return String
	 */
	//@RequestMapping("do_order_approval.do")
	@RequestMapping(value="do_order_approval.do",method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public void do_order_approval(HttpServletRequest request,HttpServletResponse response){
		int flag = 0;
		HttpSession session = request.getSession();
		
		String stoId = (String)session.getAttribute("stoId");
		log.info("stoId:!"+stoId);
		ProductStockVo inVo = new ProductStockVo();
		inVo.setStoId(stoId);
		
		Map<String, String> inParam = new HashMap<String, String>();
		inParam.put("stoId", StringUtil.nvl(stoId,""));
		inParam.put("page_size", StringUtil.nvl(request.getParameter("page_size"), "10"));
		inParam.put("page_num", StringUtil.nvl(request.getParameter("page_num"), "1"));
		inVo.setParam(inParam);
		
		List<ProductStockVo> orderDetailList = (List<ProductStockVo>) orderSvc.do_search2(inVo);
		
		for(ProductStockVo vo : orderDetailList){
			inVo.setOrdCnt(vo.getOrdCnt());
			log.info(inVo.getOrdCnt());
			inVo.setPdtNo(vo.getPdtNo());
			log.info(inVo.getPdtNo());
			inVo.setStoId(stoId);
			
			orderSvc.do_stk_update(inVo);			// 재고량 업데이트
		}
		
		flag = orderSvc.do_confirm(inVo);		// 발주상태 업데이트 (신청 -> 승인)
		flag = this.orderSvc.do_delete(inVo);
		
		
		JSONObject obj = new JSONObject();
		obj.put("flag", flag);

		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 발주 승인
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="order_update.do",method=RequestMethod.POST)
	@ResponseBody
	public void do_order_update(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		ProductStockVo vo = new ProductStockVo();

		int flag = 0;

		vo.setStoId(StringUtil.nvl(request.getParameter("stoId"), ""));
		flag = this.orderSvc.do_update(vo);

		obj.put("flag", flag);

		log.debug("flag:" + flag);
		log.debug("flag:" + obj.toJSONString());
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 페이지 .do등록
	 *
	 */
   @RequestMapping("masterOrderStores.do")
   public String masterOderStores(HttpServletRequest req,HttpServletResponse response) {
   
   	return "master/masterOrderStores";
   }
   @RequestMapping("masterOrderDetail.do")
   public String masterOderDetail(HttpServletRequest req,HttpServletResponse response) {
   
   	return "master/masterOrderDetail";
   }
	
}



