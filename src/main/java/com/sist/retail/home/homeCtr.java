package com.sist.retail.home;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class homeCtr {
	@RequestMapping(value="/index.do")
	public String index(Locale locale,Model model){
		return "index";
	}
	
}
