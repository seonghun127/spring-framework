package com.sist.retail.storeService.ctr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.gson.JsonObject;
import com.sist.retail.common.DTO;
import com.sist.retail.common.StringUtil;
import com.sist.retail.storeService.svc.StoreServiceSaleSvc;
import com.sist.retail.storeService.svc.StoreServiceSvc;
import com.sist.retail.vo.MasterStoreVo;
import com.sist.retail.vo.SaleTermVo;
import com.sist.retail.vo.SaleTodayVo;

@Controller
@RequestMapping("storeService")
public class StoreServiceCtr {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private StoreServiceSvc storeServiceSvc;
	
	@Autowired
	private StoreServiceSaleSvc storeServiceSaleSvc;
	
	@Resource(name="downloadView")
	private View downloadView;
	
	/**
	 * 지점정보 리스트 출력
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/do_search.do", method=RequestMethod.GET)
	public String do_search(HttpServletRequest req, Model model) {
		MasterStoreVo inVo = new MasterStoreVo();
		
		//Param
		inVo.setSido(StringUtil.nvl(req.getParameter("sido"), ""));
		inVo.setGugun(StringUtil.nvl(req.getParameter("gugun"), ""));
		
		List<MasterStoreVo> list 
				= (List<MasterStoreVo>) storeServiceSvc.do_search(inVo);
		model.addAttribute("storelist", list);
		
		//페이징 처리 리스트 결과값.
		Map<String,String> inParam
		   =new HashMap<String,String>();
		
		inParam.put("page_size", StringUtil.nvl(req.getParameter("page_size"),"10"));
		inParam.put("page_num", StringUtil.nvl(req.getParameter("page_num"),"1"));
		
		inVo.setParam(inParam);			
		
		List<MasterStoreVo> plist 
				= (List<MasterStoreVo>) storeServiceSvc.do_searchPaging(inVo);
		
		//총글수 
		int total_cnt = 0;
		if(null != plist && plist.size()>0){
			log.debug("========================");
			log.debug(plist.get(0));
			log.debug("========================");			
			total_cnt = plist.get(0).getTotalNo();
		}
		
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("pagingList", plist);
		
		return "storeService/searchStore";
	}
	
	/**
	 * 지점정보 단건 조회
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/do_searchOne.do" ,method=RequestMethod.POST ,produces="application/json;charset=utf8")
	public String do_searchOne(HttpServletRequest req, Model model) {
		MasterStoreVo inVo = new MasterStoreVo();
		inVo.setStoId(StringUtil.nvl(req.getParameter("stoId"),""));
		
		DTO outVo = storeServiceSvc.do_searchOne(inVo);
		MasterStoreVo outMasterStoreVo = (MasterStoreVo) outVo;
		
		model.addAttribute("storeOne", outMasterStoreVo);
		
		return "";
	}
	
	/**
	 * 금일 매출 확인
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/do_searchTodaySal.do", method=RequestMethod.POST, produces="application/json;charset=utf8")
	public String do_searchSale(HttpServletRequest req, Model model) {
		//세션 값 가져오기
		HttpSession session=req.getSession();
		String stoId=(String) session.getAttribute("stoId");
		
		SaleTodayVo saleTodayVo = new SaleTodayVo();
		saleTodayVo.setStoId(StringUtil.nvl(stoId,"11111"));
		
		List<SaleTodayVo> list
				=(List<SaleTodayVo>) storeServiceSaleSvc.do_search(saleTodayVo);
		
		model.addAttribute("saleList", list);
		
		return "storeService/todaySale";
	}
	
	/**
	 * 금일 매출 액셀 다운
	 * @param req
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/excelDownTodaysale.do")
	public ModelAndView do_exceldownTodaysale(HttpServletRequest req, Model model)
			throws IOException{	
		
		//세션 값 가져오기
		HttpSession session=req.getSession();
		String stoId=(String) session.getAttribute("stoId");
		
		SaleTodayVo saleTodayVo = new SaleTodayVo();
		saleTodayVo.setStoId(StringUtil.nvl(stoId,"11111"));
		
		List<SaleTodayVo> list
				=(List<SaleTodayVo>) storeServiceSaleSvc.do_search(saleTodayVo);
				
		ModelAndView modelAndView=new ModelAndView();
		
		String fileFullPath = this.storeServiceSaleSvc.do_excelDownTodaySale(saleTodayVo);
		File downloadFile=new File(fileFullPath);
		
		modelAndView.setView(downloadView);
		modelAndView.addObject("downloadFile",downloadFile);
		
		//날짜 구하기 
		Calendar sysdate= Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String sys=sdf.format(sysdate.getTime());
		
		modelAndView.addObject("orgFileNm",sys+"todaySale.xls");//저장파일명
		
		return modelAndView;
	}
	/**
	 * 기간별 매출 확인
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/do_searchTermSal.do", method=RequestMethod.POST, produces="application/json;charset=utf8")
	public String do_searchTermSale(HttpServletRequest req, Model model) {
		//세션 값 가져오기
		HttpSession session=req.getSession();
		String stoId=(String) session.getAttribute("stoId");
		
		SaleTermVo saleTermVo = new SaleTermVo();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		saleTermVo.setStartDt(req.getParameter("startDt"));
		saleTermVo.setEndDt(req.getParameter("endDt"));
		saleTermVo.setStoId(StringUtil.nvl(stoId,"11111"));
		
		List<SaleTermVo> list
						=(List<SaleTermVo>) storeServiceSaleSvc.do_searchTerm(saleTermVo);
		
		model.addAttribute("termSaleList", list);
		model.addAttribute("startDt", req.getParameter("startDt"));
		model.addAttribute("endDt", req.getParameter("endDt"));
		
		return "storeService/termSale";
	}
	
	/**
	 * 기간별 매출 액셀 다운
	 * @param req
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/excelDownTermsale.do") 
	public ModelAndView do_exceldownTermsale(HttpServletRequest req, Model model)
			throws IOException{		
		
		HttpSession session=req.getSession();
		String stoId=(String) session.getAttribute("stoId");
		
		SaleTermVo saleTermVo = new SaleTermVo();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		saleTermVo.setStartDt(req.getParameter("startDt"));
		saleTermVo.setEndDt(req.getParameter("endDt"));
		saleTermVo.setStoId(StringUtil.nvl(stoId,"11111"));
		
		ModelAndView modelAndView=new ModelAndView();
		
		String fileFullPath = this.storeServiceSaleSvc.do_excelDownTermSale(saleTermVo);
		File downloadFile=new File(fileFullPath);
		
		modelAndView.setView(downloadView);
		modelAndView.addObject("downloadFile",downloadFile);
		
		//날짜 구하기 
		Calendar sysdate= Calendar.getInstance();
		sdf=new SimpleDateFormat("yyyyMMdd");
		String sys=sdf.format(sysdate.getTime());
		
		modelAndView.addObject("orgFileNm",sys+"termsale.xls");//저장파일명
		
		return modelAndView;
	}
	
	@RequestMapping(value="/searchStore.do")
	public String searchStore(){
		return "storeService/searchStore";
	}
	
	@RequestMapping(value="/termSale.do")
	public String searchTermSal() {
		return "storeService/termSale";
	}
	
	@RequestMapping(value="/todaySale.do")
	public String todaySal(HttpServletRequest req, Model model) {
		//세션 값 가져오기
		HttpSession session=req.getSession();
		String stoId=(String) session.getAttribute("stoId");
		
		SaleTodayVo saleTodayVo = new SaleTodayVo();
		saleTodayVo.setStoId(StringUtil.nvl(stoId,"11111"));
		
		List<SaleTodayVo> list
				=(List<SaleTodayVo>) storeServiceSaleSvc.do_search(saleTodayVo);
		
		model.addAttribute("saleList", list);
		return "storeService/todaySale";
	}
}
