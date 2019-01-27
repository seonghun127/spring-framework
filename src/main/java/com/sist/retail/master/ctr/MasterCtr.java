package com.sist.retail.master.ctr;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sist.retail.common.DTO;
import com.sist.retail.common.StringUtil;
import com.sist.retail.master.dao.MasterDaoImpl;
import com.sist.retail.master.svc.MasterSvcImpl;
import com.sist.retail.vo.MasterStoreVo;
import com.sist.retail.vo.SaleVo;

@Controller
@RequestMapping("master")
public class MasterCtr {
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Autowired
	private MasterDaoImpl masterDaoImpl;
	
	@Autowired
	private MasterSvcImpl masterSvcImpl;
	
	// 가입
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public String do_add(HttpServletRequest req, Model model) {
		MasterStoreVo masterStoreVo = new MasterStoreVo();
		int flag = 0;
		int storeNo_count = masterDaoImpl.do_getCount();
		// jsp에서
		masterStoreVo.setStoId(StringUtil.idFormat(storeNo_count));
		masterStoreVo.setPwd(StringUtil.nvl(req.getParameter("pwd"), "1111"));
		masterStoreVo.setStoNm(StringUtil.nvl(req.getParameter("stoNm"), ""));
		masterStoreVo.setOwnId(StringUtil.nvl(req.getParameter("ownId"), ""));
		masterStoreVo.setPhoNo(StringUtil.nvl(req.getParameter("phoNo"), ""));
		masterStoreVo.setAdr(StringUtil.nvl(req.getParameter("adr"), ""));
		masterStoreVo.setLat(Double.parseDouble(StringUtil.nvl(req.getParameter("lat"), "")));
		masterStoreVo.setLot(Double.parseDouble(StringUtil.nvl(req.getParameter("lot"), "")));
		masterStoreVo.setGntCd("1");
		
		flag = masterSvcImpl.do_add(masterStoreVo);
		masterSvcImpl.do_addStock(masterStoreVo);

		return "master/mainPage";
	}

	// 로그인
	@RequestMapping(value = "/login.do")
	public void do_login(HttpServletRequest req, HttpServletResponse rep) {
		HttpSession session = req.getSession();
		JSONObject obj = new JSONObject();
		MasterStoreVo masterStoreVo = new MasterStoreVo();
		masterStoreVo.setStoId(StringUtil.nvl(req.getParameter("stoId"), ""));
		masterStoreVo.setPwd(StringUtil.nvl(req.getParameter("pwd"), ""));
	   session.setAttribute("stoId", masterStoreVo.getStoId());

		MasterStoreVo checkVo = new MasterStoreVo();
		checkVo = (MasterStoreVo) masterDaoImpl.do_login(masterStoreVo);
		session.setAttribute("gntCd", checkVo.getGntCd());
		
		StringUtil.nvl(String.valueOf(checkVo.getchk()),"0");
		obj.put("check", checkVo.getchk());
		
		rep.setContentType("text/html;charset=UTF-8");
		try {
			rep.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		
	//로그인한 아이디 회원정보 가져오기
	@RequestMapping(value = "/beforeUpdate.do", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public String do_initupdate(HttpServletRequest req,Model model){
		
		MasterStoreVo inVo = new MasterStoreVo();	
		inVo.setStoId(StringUtil.nvl(req.getParameter("stoId"),""));
		DTO outVo = masterSvcImpl.do_searchOne(inVo);
		MasterStoreVo outLoginVo=(MasterStoreVo)outVo;
		model.addAttribute("masterStoreVo", outVo);
	
		return "master/masterUpdate";
	}
	
	//회원정보 수정
	@RequestMapping(value = "/masterStoreReg.do", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public void do_update(HttpServletRequest req,HttpServletResponse response){
		HttpSession session = req.getSession();
		MasterStoreVo masterStoreVo = new MasterStoreVo();
		JSONObject obj = new JSONObject();
		int flag=0;
		masterStoreVo.setStoId(StringUtil.nvl(req.getParameter("stoId"),""));
		masterStoreVo.setStoNm(StringUtil.nvl(req.getParameter("stoNm"), ""));
		masterStoreVo.setPwd(StringUtil.nvl(req.getParameter("pwd"), ""));
		masterStoreVo.setOwnId(StringUtil.nvl(req.getParameter("ownId"), ""));
		masterStoreVo.setPhoNo(StringUtil.nvl(req.getParameter("phoNo"), ""));
		masterStoreVo.setAdr(StringUtil.nvl(req.getParameter("adr"), ""));
		session.setAttribute("stoId", masterStoreVo.getStoId());
		flag=masterSvcImpl.do_update(masterStoreVo);
		obj.put("flag", flag);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("flag:"+obj.toJSONString());
	}
	

	@RequestMapping(value="/mainPage.do")	
	public String mainPage(HttpServletRequest req,HttpServletResponse response){
			return "master/mainPage";
	}
	@RequestMapping(value="/rank.do")	
	public void rank(HttpServletRequest req,HttpServletResponse response){
		String div=StringUtil.nvl(req.getParameter("div"), "");
		JSONArray  jsonar=new JSONArray();
		List<SaleVo> ranklist=new ArrayList<SaleVo>();
		ranklist=(List<SaleVo>) masterSvcImpl.salRank(div);
		if(div.equals("sex")){
			for(SaleVo vo:ranklist){
				JSONObject json =new JSONObject();
				json.put("sex", vo.getSex());
				json.put("salCnt", vo.getSalCnt());
				json.put("pdtNm", vo.getPdtNm());
				json.put("rank", vo.getRank());
				jsonar.add(json);
			}
			
		}
		else if(div.equals("age")){
			for(SaleVo vo:ranklist){
				JSONObject json =new JSONObject();
				json.put("age", vo.getAge());
				json.put("salCnt", vo.getSalCnt());
				json.put("pdtNm", vo.getPdtNm());
				json.put("rank", vo.getRank());
				jsonar.add(json);
			}
			
		}
		else if(div.equals("time")){
			for(SaleVo vo:ranklist){
				JSONObject json =new JSONObject();
				json.put("time", vo.getTime());
				json.put("salCnt", vo.getSalCnt());
				json.put("pdtNm", vo.getPdtNm());
				json.put("rank", vo.getRank());
				jsonar.add(json);
			}
			
		}
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(jsonar.toJSONString());
		try {
			response.getWriter().print(jsonar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value="/masterData.do")	
	public String masterData(HttpServletRequest req,HttpServletResponse response){
		return "master/masterData";
	}
	
	@RequestMapping(value="/masterStoreReg.do")	
	public String masterStoreReg(HttpServletRequest req,Model model){
		
		MasterStoreVo masterStoreVo = new MasterStoreVo();
		int storeNo_count = masterDaoImpl.do_getCount();
		masterStoreVo.setStoId(StringUtil.idFormat(storeNo_count));
		masterStoreVo.setPwd("1111");
		model.addAttribute("masterStoreVo",masterStoreVo);
		
		return "master/masterStoreReg";
	}
}

