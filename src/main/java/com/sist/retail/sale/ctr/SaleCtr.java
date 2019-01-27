package com.sist.retail.sale.ctr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.retail.common.StringUtil;
import com.sist.retail.sale.svc.SaleSvc;
import com.sist.retail.sale.svc.SaleSvcImpl;
import com.sist.retail.vo.ProductStockVo;
import com.sist.retail.vo.SaleVo;

/**
 * 판매 컨트롤러
 * 
 * @author Kang Hyun Gu
 * @since 2017-12-20
 * @version 0.01
 *
 */
@Controller
@RequestMapping("sale")
public class SaleCtr {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SaleSvc saleSvcImpl;

	@RequestMapping(value = "/sale.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public void do_sale(HttpServletRequest req, HttpServletResponse response) {
		int flag = 0;
		JSONObject json = new JSONObject();
		SaleVo salevo = new SaleVo();
		ProductStockVo pvo = new ProductStockVo();
		req.getParameter("map");
		String salCnt = req.getParameter("salCnt");
		if (salCnt == null || salCnt.trim().equals("")) {
			salCnt = "0";
		}
		logger.info("=====stoId" + req.getParameter("stoId"));
		salevo.setPdtNo(StringUtil.nvl(req.getParameter("pdtNo"), ""));
		salevo.setSalCnt(Integer.parseInt(salCnt));
		salevo.setAgeCd(StringUtil.nvl(req.getParameter("age"), ""));
		salevo.setSexCd(StringUtil.nvl(req.getParameter("sex"), ""));
		salevo.setStoId(StringUtil.nvl(req.getParameter("stoId"), ""));
		logger.info(salevo.toString());
		pvo.setStkCnt(Integer.parseInt(salCnt));
		pvo.setPdtNo(StringUtil.nvl(req.getParameter("pdtNo"), ""));
		pvo.setStoId(StringUtil.nvl(req.getParameter("stoId"), ""));
		flag = saleSvcImpl.do_saleTx(salevo, pvo);
		logger.debug("메롱:" + flag);
		if (flag >= 2) {
			json.put("result", "판매성공");
		} else {
			json.put("result", "판매실패입니다.");
		}

		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/sale.do")
	public String sale(HttpServletRequest req, HttpServletResponse response) {
		return "sale/sale";
	}
}
