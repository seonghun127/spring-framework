package com.sist.retail.qna.ctr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sist.retail.common.StringUtil;
import com.sist.retail.qna.svc.QnaSvc;
import com.sist.retail.vo.NoticeQnaVo;

/**
 * 문의사항 게시글, 댓글 관리 컨트롤러
 * 
 * @author 김성훈
 * 
 */

@Controller
public class QnaCtr {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private QnaSvc qnaSvc;

	@RequestMapping(value = "notice/noticeQna.do")
	public String noticeQna(Locale locale, Model model) {
		return "notice/noticeQna";
	}

	@RequestMapping(value = "notice/noticeQnaDetail.do")
	public String noticeQnaDetail(Locale locale, Model model) {
		return "notice/noticeQnaDetail";
	}
	
	@RequestMapping(value = "notice/noticeQnaInsert.do")
	public String noticeQnaInsert(Locale locale, Model model) {
		return "notice/noticeQnaInsert";
	}

	/**
	 * 문의사항 리스트 출력
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "notice/qnaSearch.do", method = RequestMethod.GET)
	public String do_qna_list(HttpServletRequest request, Model model) {
		
		NoticeQnaVo inVo = new NoticeQnaVo();

		// param
		Map<String, String> inParam = new HashMap<String, String>();
		inParam.put("search_div", StringUtil.nvl(request.getParameter("search_div"), ""));
		inParam.put("search_word", StringUtil.nvl(request.getParameter("search_word"), ""));
		inParam.put("page_size", StringUtil.nvl(request.getParameter("page_size"), "10"));
		inParam.put("page_num", StringUtil.nvl(request.getParameter("page_num"), "1"));

		inVo.setParam(inParam);

		List<NoticeQnaVo> qnaList = (List<NoticeQnaVo>) qnaSvc.do_search(inVo);

		// 총글수
		int total_cnt = 0;
		if (null != qnaList && qnaList.size() > 0) {
			log.debug("========================");
			log.debug(qnaList.get(0));
			log.debug("========================");
			total_cnt = qnaList.get(0).getTotalNo();
		}

		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("qnaList", qnaList);

		return "notice/noticeQna";
	}

	/**
	 * 선택된 문의사항 상세 정보
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "notice/qnaSearchOne.do", method = RequestMethod.GET)
	public String do_qna_list_detail(HttpServletRequest request,HttpServletResponse response ,Model model) {
		
		NoticeQnaVo vo = new NoticeQnaVo();
		
		HttpSession session=request.getSession();
		String qaNo=(String) session.getAttribute("qaNo");
		String stoId = (String) session.getAttribute("stoId");
		log.info("stoId : " + stoId);
		
		vo.setQaNo(Integer.parseInt(StringUtil.nvl(request.getParameter("qaNo"), "")));

		List<NoticeQnaVo> QnaDetailList = new ArrayList<NoticeQnaVo>();
		QnaDetailList = (List<NoticeQnaVo>) qnaSvc.do_detail(vo);
		
		String workDiv = request.getParameter("workDiv");
		model.addAttribute(StringUtil.nvl(workDiv,""),"workDiv");
		
		PrintWriter writer;
		if(stoId.equals(QnaDetailList.get(0).getStoId())||stoId.equals("00001")){
			model.addAttribute("QnaDetailList", QnaDetailList);
			return "notice/noticeQnaDetail";
		}
		else{
			response.setContentType("text/html; charset=UTF-8");
			try {
				 writer = response.getWriter();
				 writer.println("<script type='text/javascript'>");
				 writer.println("alert('본인이 작성한 글만 조회 가능합니다.');");
				 writer.println("history.back();");
				 writer.println("</script>");
				 writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		}
		
		log.info("======================");
		log.info("qa_qna_detail : " + vo.toString());
		log.info("======================");

		return "notice/noticeQnaDetail";
		
	}

	/**
	 * 문의사항 삭제
	 * 
	 * @param request
	 * @param model
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value = "notice/do_qna_delete.do", method = RequestMethod.GET)
	public void do_qna_delete(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		int flag = 0;
		
		NoticeQnaVo vo = new NoticeQnaVo();
		vo.setQaNo(Integer.parseInt(StringUtil.nvl(request.getParameter("qaNo"), "")));

		flag = this.qnaSvc.do_delete(vo);
		log.info("======================");
		log.info("ctr_qna_delete_flag : " + flag);
		log.info("======================");

		response.sendRedirect("qnaSearch.do");
	}

	/**
	 * 문의사항 수정
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "notice/do_qna_update.do", method = RequestMethod.GET)
	public void do_qna_update(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		int flag = 0;
		
		NoticeQnaVo vo = new NoticeQnaVo();
		vo.setQaNo(Integer.parseInt(StringUtil.nvl(request.getParameter("qaNo"), "")));
		vo.setTitle(StringUtil.nvl(request.getParameter("title"), ""));
		vo.setQaCon(StringUtil.nvl(request.getParameter("qaCon"), ""));
		
		flag = this.qnaSvc.do_update(vo);
		log.info("======================");
		log.info("ctr_qna_update_flag : " + flag);
		log.info("======================");

		response.sendRedirect("qnaSearchOne.do?qaNo="+vo.getQaNo());
	}

	/**
	 * 문의사항 등록
	 * 
	 * @param request
	 * @param model
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value = "notice/qnaInsert.do", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public void do_qna_insert(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		int flag = 0;
		HttpSession session=request.getSession();
		
		String title = StringUtil.nvl(request.getParameter("title"), "");
		String qaCon = StringUtil.nvl(request.getParameter("qaCon"), "");
		String stoId=(String) session.getAttribute("stoId");

		NoticeQnaVo vo = new NoticeQnaVo();
		vo.setTitle(title);
		vo.setQaCon(qaCon);
		vo.setStoId(stoId);

		flag = this.qnaSvc.do_add(vo);
		log.info("======================");
		log.info("ctr_qna_insert_flag : " + flag);
		log.info("======================");

		response.sendRedirect("qnaSearch.do");
	}

}
