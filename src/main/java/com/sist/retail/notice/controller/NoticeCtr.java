package com.sist.retail.notice.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sist.retail.common.StringUtil;
import com.sist.retail.notice.service.CommentSvcImpl;
import com.sist.retail.notice.service.NoticeSvcImpl;
import com.sist.retail.vo.CommentVo;
import com.sist.retail.vo.MasterStoreVo;
import com.sist.retail.vo.NoticeVo;

@Controller
@RequestMapping("notice")
public class NoticeCtr {
	private Logger log = Logger.getLogger(this.getClass());
		
	@Autowired
	private NoticeSvcImpl noticeSvcImpl;
	@Autowired
	private CommentSvcImpl commentSvcImpl;
	// 게시글 등록
	@RequestMapping(value="/noticeInsert.do",method = RequestMethod.POST)
	public String do_add(HttpServletRequest req, Model model){
		NoticeVo noticeVo = new NoticeVo();
		int flag = 0;
		HttpSession session=req.getSession();
		String divCd=(String) session.getAttribute("divCd");
		String stoId=(String) session.getAttribute("stoId");
		
		log.debug("Insert divCd:"+divCd);
		
		noticeVo.setStoId(StringUtil.nvl(stoId, ""));
		noticeVo.setTitle((StringUtil.nvl(req.getParameter("title"), "")));
		noticeVo.setCon((StringUtil.nvl(req.getParameter("con"), "")));
		noticeVo.setDivCd(StringUtil.nvl(divCd, ""));
		
		flag=noticeSvcImpl.do_add(noticeVo);
		
		return "notice/notice";
	}
	
	// tiles mapping
	@RequestMapping(value="/noticeInsert.do")
	public String noticeInsert(HttpServletRequest req, Model model){
		return "notice/noticeInsert";
	}

	// 게시글 검색(전체,제목,내용)
	@RequestMapping(value="/search.do")
	public String do_search(HttpServletRequest req, Model model){
		NoticeVo noticeVo = new NoticeVo();
		HttpSession session=req.getSession();
		
		//권한 조회
		MasterStoreVo masterStoreVo=new MasterStoreVo();
		String stoId=(String) session.getAttribute("stoId");
		masterStoreVo.setStoId(StringUtil.nvl(stoId,"11111"));
		String divCd=(String) session.getAttribute("divCd");
		
		masterStoreVo=(MasterStoreVo) noticeSvcImpl.do_searchGnt(masterStoreVo);
		String gntCd=masterStoreVo.getGntCd();
		log.debug("====gntCd:======");
		log.debug("gntCd:"+gntCd);
		
		Map<String, String> inParam = new HashMap<String, String>();
		inParam.put("search_div", StringUtil.nvl(req.getParameter("search_div"), ""));
		inParam.put("search_word", StringUtil.nvl(req.getParameter("search_word"), ""));
		inParam.put("page_num", StringUtil.nvl(req.getParameter("page_num"), "1"));
		inParam.put("divCd",divCd);
		
		noticeVo.setParam(inParam);
		
		// service call
		List<NoticeVo> list = (List<NoticeVo>) noticeSvcImpl.do_search(noticeVo);
		
		// 총글수
		int total_cnt = 0;
		if (null != list && list.size() > 0) {
			log.debug("==============================================");
			log.debug("list.get(0):" + list.get(0).getTotalNo());
			log.debug("==============================================");
			total_cnt = list.get(0).getTotalNo();
		}
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("gntCd",gntCd);
		model.addAttribute("list", list);
		
		return "notice/notice";
	}
	
	// 선택 게시글 보기
	@RequestMapping(value="/do_detail.do", method = RequestMethod.GET, produces = "application/json;charset=utf8")
	public String do_detail(HttpServletRequest req, Model model){
		NoticeVo inVo = new NoticeVo();
		CommentVo cInvo = new CommentVo();
		
		inVo.setMemoNo(Integer.parseInt(StringUtil.nvl(req.getParameter("memoNo"), "")));
		cInvo.setMemoNo(Integer.parseInt(StringUtil.nvl(req.getParameter("memoNo"), "")));
		
		//세션 값 가져오기
		HttpSession session=req.getSession();
		String divCd=(String) session.getAttribute("divCd");
		inVo.setDivCd(divCd);
		
		NoticeVo noticeVo = (NoticeVo) noticeSvcImpl.do_detail(inVo);
		List<CommentVo> clist = (List<CommentVo>) commentSvcImpl.do_search(cInvo);
		
		//작업구분자 수정인지 조회인지.
		String workDiv=req.getParameter("workDiv");
		
		model.addAttribute(StringUtil.nvl(workDiv, ""),"workDiv");
		model.addAttribute("noticeVo", noticeVo);
		model.addAttribute("clist", clist);
		log.debug("clist:");
		log.debug(clist.toString());
		
		return "notice/noticeDetail";
	}
	
	// 게시글 수정
	@RequestMapping(value="/update.do", method = RequestMethod.GET, produces = "application/json;charset=utf8")
	public void do_update(HttpServletRequest req,HttpServletResponse response) throws IOException{
		NoticeVo noticeVo = new NoticeVo();
		int flag=0;
		noticeVo.setMemoNo(Integer.parseInt(StringUtil.nvl(req.getParameter("memoNo"), "")));
		noticeVo.setTitle(StringUtil.nvl(req.getParameter("title"),""));
		noticeVo.setCon(StringUtil.nvl(req.getParameter("con"), ""));
		
		flag=noticeSvcImpl.do_update(noticeVo);
		
		response.sendRedirect("do_detail.do?memoNo="+noticeVo.getMemoNo());
	}
	
	// 게시글 삭제
	@RequestMapping(value = "/do_delete.do", method = RequestMethod.GET, produces = "application/json;charset=utf8")
	public String do_delete(HttpServletRequest req, Model model) throws Exception {
		NoticeVo noticeVo = new NoticeVo();
		CommentVo commentVo = new CommentVo();
		int flag = 0;
		
		noticeVo.setMemoNo(Integer.parseInt(StringUtil.nvl(req.getParameter("memoNo"), "")));
		commentVo.setMemoNo(Integer.parseInt(StringUtil.nvl(req.getParameter("memoNo"), "")));
		
		//댓글 삭제.
		commentSvcImpl.do_deleteCasCade(commentVo);
		
		//게시글 삭제.
		flag = this.noticeSvcImpl.do_delete(noticeVo);
		
		return "notice/notice";
	}

	@RequestMapping(value="/notice.do")	
	public String notice(HttpServletRequest req,Model model){
		NoticeVo noticeVo = new NoticeVo();
		
		//세션 값 가져오기
		HttpSession session=req.getSession();
		String stoId=(String) session.getAttribute("stoId");
		
		//권한 조회
		MasterStoreVo masterStoreVo=new MasterStoreVo();
		masterStoreVo.setStoId(StringUtil.nvl(stoId,"11111"));
		masterStoreVo=(MasterStoreVo) noticeSvcImpl.do_searchGnt(masterStoreVo);
		String gntCd=masterStoreVo.getGntCd();
		log.debug("====gntCd:======");
		log.debug("gntCd:"+gntCd);
		
		Map<String, String> inParam = new HashMap<String, String>();
		inParam.put("search_div", StringUtil.nvl(req.getParameter("search_div"), ""));
		inParam.put("search_word", StringUtil.nvl(req.getParameter("search_word"), ""));
		inParam.put("page_num", StringUtil.nvl(req.getParameter("page_num"), "1"));
		inParam.put("divCd",req.getParameter("divCd"));
		log.debug("=req=divCd:"+req.getParameter("divCd"));
		noticeVo.setParam(inParam);
		
		// service call
		List<NoticeVo> list = (List<NoticeVo>) noticeSvcImpl.do_search(noticeVo);
		
		// 총글수
		int total_cnt = 0;
		if (null != list && list.size() > 0) {
			log.debug("==============================================");
			log.debug("list.get(0):" + list.get(0).getTotalNo());
			log.debug("==============================================");
			total_cnt = list.get(0).getTotalNo();
		}
		model.addAttribute("total_cnt", total_cnt);
		model.addAttribute("gntCd",gntCd);
		String divCd = req.getParameter("divCd");
		//model.addAttribute("divCd", divCd);
		log.debug("divCd:"+req.getParameter("divCd"));
		model.addAttribute("list", list);
		
		return "notice/notice";
	}
	
	// 댓글 등록
	@RequestMapping(value = "/commentInsert.do", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	@ResponseBody
	public String do_insertComment(HttpServletRequest req, Model model) throws Exception {
		JSONObject obj = new JSONObject();
		CommentVo commentVo=new CommentVo();
		
		//세션 
		HttpSession session=req.getSession();
		String memoNo=(String) session.getAttribute("memoNo");
		String stoId =(String) session.getAttribute("stoId");
		
		commentVo.setCmtCon(StringUtil.nvl(req.getParameter("cmtCon"), ""));
		commentVo.setMemoNo(Integer.parseInt(memoNo));
		commentVo.setStoId(stoId);
		
		log.debug("insert comment:"+commentVo.getCmtCon());
		
		int flag = 0;
		
		flag = this.commentSvcImpl.do_add(commentVo);
		obj.put("flag", flag);
		
		return obj.toJSONString();
	}
	// 댓글 삭제
	@RequestMapping(value = "/commentDelete.do", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	@ResponseBody
	public String do_deleteComment(HttpServletRequest req, Model model) throws Exception {
		JSONObject obj = new JSONObject();
		CommentVo commentVo=new CommentVo();
		
		String cmt=StringUtil.nvl(req.getParameter("cmtNo"), "");
		
		int cmtNo=Integer.parseInt(cmt);
		
		log.debug("==Delete cmtNo:"+cmtNo);
		
		commentVo.setCmtNo(cmtNo);
		
		int flag = 0;
		commentVo.setCmtNo(cmtNo);
		flag = this.commentSvcImpl.do_delete(commentVo);
		
		obj.put("flag", flag);
		
		return obj.toJSONString();
	}
	// 댓글 수정
	@RequestMapping(value = "/commentUpdate.do", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	@ResponseBody
	public String do_updateComment(HttpServletRequest req, Model model) throws Exception {
		JSONObject obj = new JSONObject();
		CommentVo commentVo=new CommentVo();
		int flag = 0;
		String scmt=StringUtil.nvl(req.getParameter("cmtNo"), "");
		log.debug("cmtNo:"+scmt);
		
		int cmtNo = Integer.parseInt(scmt);
		
		commentVo.setCmtCon(StringUtil.nvl(req.getParameter("cmtCon"), ""));
		commentVo.setCmtNo(cmtNo);
		
		flag = this.commentSvcImpl.do_update(commentVo);
		
		obj.put("flag", flag);
		
		return obj.toJSONString();
	}	
}