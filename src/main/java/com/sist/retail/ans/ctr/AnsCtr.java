package com.sist.retail.ans.ctr;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.retail.ans.svc.AnsSvc;
import com.sist.retail.ans.svc.AnsSvcImpl;
import com.sist.retail.common.StringUtil;
import com.sist.retail.qna.svc.QnaSvcImpl;
import com.sist.retail.vo.NoticeQnaVo;

/**
 * 문의사항 게시글, 댓글 관리 컨트롤러
 * 
 * @author 김성훈
 * 
 */

@Controller
public class AnsCtr {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AnsSvc ansSvc;

	/**
	 * 문의사항의 답변 등록
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "notice/ansInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String do_ans_insert(HttpServletRequest request, Model model) {
		JSONObject obj = new JSONObject();
		int flag = 0;
		String ansCon = StringUtil.nvl(request.getParameter("ansCon"), "");
		int qaNo = Integer.parseInt(StringUtil.nvl(request.getParameter("qaNo"), ""));

		log.info("ansCon :" + ansCon);
		log.info("qaNo :" + qaNo);

		NoticeQnaVo vo = new NoticeQnaVo();
		vo.setAnsCon(ansCon);
		vo.setQaNo(qaNo);

		flag = this.ansSvc.do_add(vo);
		log.info("======================");
		log.info("ctr_Ans_insert_flag : " + flag);
		log.info("======================");

		obj.put("flag", flag);

		return obj.toJSONString();
	}

	/**
	 * 문의사항의 답변 삭제
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "notice/ansDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String do_ans_delete(HttpServletRequest request, Model model) {
		int flag = 0;
		int ansNo = Integer.parseInt(StringUtil.nvl(request.getParameter("ansNo"), ""));

		NoticeQnaVo vo = new NoticeQnaVo();
		vo.setAnsNo(ansNo);

		flag = this.ansSvc.do_delete(vo);
		log.info("======================");
		log.info("ctr_ans_delete_flag : " + flag);
		log.info("======================");

		JSONObject obj = new JSONObject();
		obj.put("flag", flag);

		return obj.toJSONString();
	}

	/**
	 * 문의사항의 답변 수정
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "notice/ansUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public String do_ans_update(HttpServletRequest request, Model model) {

		int flag = 0;
		String ansCon = StringUtil.nvl(request.getParameter("ansCon"), "");
		int qaNo = Integer.parseInt(StringUtil.nvl(request.getParameter("qaNo"), ""));
		int ansNo = Integer.parseInt(StringUtil.nvl(request.getParameter("ansNo"), ""));

		NoticeQnaVo vo = new NoticeQnaVo();
		vo.setAnsCon(ansCon);
		vo.setQaNo(qaNo);
		vo.setAnsNo(ansNo);

		flag = this.ansSvc.do_update(vo);
		log.info("======================");
		log.info("ctr_ans_update_flag : " + flag);
		log.info("======================");

		JSONObject obj = new JSONObject();
		obj.put("flag", flag);

		return obj.toJSONString();
	}
}
