package com.sist.retail.common;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * AbstractView : 스프링 DispacherServlet 기능을 함
 * 							requestURI에 따라 컨트롤러로 분기하고 로직 처리 후 Resolver를 사용하여
 * 							해당 jsp파일을 찾아 응답하게 되는데 그 사이 시점을 잡아 처리
 * @author A_CHU
 *
 */
@Component
public class DownloadView extends AbstractView {
	private Logger log = Logger.getLogger(this.getClass());
	
	public DownloadView() {
		setContentType("application/download;charset=utf-8");
	}

	/**
	 * 파일 다운로드명
	 * @param fileName
	 * @param request
	 * @param response
	 */
	private void setDownloadFileName(String fileName
			, HttpServletRequest request
			, HttpServletResponse response) throws UnsupportedEncodingException{
		String userAgent = request.getHeader("User-Agent");
		log.debug("User-Agent : "+userAgent);
		log.debug("fileName : "+fileName);
		
		boolean isIe = (userAgent.indexOf("MSIE") != -1);
		log.debug("isIe : "+isIe);
		if(isIe == true){
			fileName = URLEncoder.encode(fileName, "utf-8");
		}else{
			//인코딩 변환
			String docName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
			fileName = new String(docName.getBytes("utf-8"));
		}
		log.debug("fileName : "+fileName);
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
	}
	
	/**
	 * FileDownload Stream 처리
	 */
	private void downloadFile(File downloadFile, HttpServletRequest request, HttpServletResponse response) throws Exception{
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(downloadFile);
		try {
			FileCopyUtils.copy(in, out);
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(null != in) in.close();
			} catch (IOException ioe) {
				throw ioe;
			}
			try {
				if(null != out) out.close();
			} catch (IOException ioe) {
				throw ioe;
			}
		}
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			setResponseContentType(request, response);
			File downloadFile = (File)model.get("downloadFile");
			String orgFileNm = (String)model.get("orgFileNm");
			
			log.debug("=====renderMergedOutputModel=====");
			log.debug("downloadFile : "+downloadFile.getName());
			log.debug("downloadFile.length() : "+downloadFile.length());
			log.debug("orgFileNm : "+orgFileNm);
			log.debug("model : "+model);
			log.debug("=====renderMergedOutputModel=====");
			
			setDownloadFileName(orgFileNm, request, response);
			response.setContentLength((int)downloadFile.length());
			downloadFile(downloadFile,request,response);
			
		} catch (Exception e) {
			throw e;
		}
	}
}
