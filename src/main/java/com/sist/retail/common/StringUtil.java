package com.sist.retail.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

//필요한 function 사용
public class StringUtil {
	static Logger log = Logger.getLogger(StringUtil.class);
	
	public static String idFormat(int storeNo) {
		int maxStoreNo = storeNo + 1;
		
		if(maxStoreNo<10) {
			System.out.println("value is 0000"+maxStoreNo);
			return "0000"+maxStoreNo;
		} else if(maxStoreNo<100) {
			System.out.println("value is 000"+maxStoreNo);
			return "000"+maxStoreNo;
		} else if(maxStoreNo<1000) {
			System.out.println("value is 00"+maxStoreNo);
			return "00"+maxStoreNo;
		} else if(maxStoreNo<10000) {
			System.out.println("value is 0"+maxStoreNo);
			return "0"+maxStoreNo;
		} else {
			System.out.println("value is "+maxStoreNo);
			return ""+maxStoreNo;
		}
	}
	
	/**
	 * 숫자면 true
	 * @param strInput
	 * @return true
	 */
	public static boolean isDigit(String strInput) {
		return Pattern.matches("^[0-9]*$", strInput);
	}

	/**
	 * 이 알파벳이면 true
	 * @param strInput
	 * @return true
	 */
	public static boolean isAlphabet(String strInput) {
		return Pattern.matches("^[a-zA-Z]*$", strInput);
	}

	/**
	 * 한글이면 true
	 * @param strInput
	 * @return true
	 */
	public static boolean isHangul(String strInput) {
		return Pattern.matches("^([가-힣]*$)", strInput);
	}

	/**
	 * 알파벳숫자,특수문자 '_'아니면 true
	 * @param strInput
	 * @return true
	 */
	public static boolean isIDCheck(String strInput) {
		String pattern = "^[a-zA-Z0-9_]{8,10}$";

		boolean flag = Pattern.matches(pattern, strInput);
		log.debug("isAlphabetDigit:" + flag);
		return flag;
	}

	/**
	 * NVL
	 * @param str
	 * @param defValue
	 * @return String
	 */
	public static boolean isNull(String str) {
		boolean flag = false;
		if (null == str || str.trim().equals("")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 32bit random함수
	 * @return 32bit random
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 날짜 format
	 * @param format ex) yyyyMMddhhmmss // yyyy-MM-dd
	 * @return String
	 */
	public static String currDate(String format) {
		Date date = new Date();
		// format null 처리
		if (null == format || format.equals(""))
			format = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}

	/**
	 * Paging처리
	 * @param maxNum_i
	 * @param currPageNoIn_i
	 * @param rowsPerPage_i
	 * @param bottomCount_i
	 * @param url_i
	 * @param scriptName_i
	 * @return
	 */
	public static String renderPaging(int maxNum_i, int currPageNoIn_i, int rowsPerPage_i, int bottomCount_i,
			String url_i, String scriptName_i) {
		int maxNum = 0; // 총 갯수
		int currPageNo = 1; // 현재 페이지 번호 : page_num
		int rowPerPage = 10; // 한페이지에 보여질 행수 : page_size
		int bottomCount = 10; // 바닥에 보여질 페이지 수: 10

		maxNum = maxNum_i;
		currPageNo = currPageNoIn_i;
		rowPerPage = rowsPerPage_i;
		bottomCount = bottomCount_i;

		String url = url_i; // 호출 URL
		String scriptName = scriptName_i; // 호출 자바스크립트

		int maxPageNo = ((maxNum - 1) / rowPerPage) + 1;
		int startPageNo = ((currPageNo - 1) / bottomCount) * bottomCount + 1;//
		int endPageNo = ((currPageNo - 1) / bottomCount + 1) * bottomCount;
		int nowBlockNo = ((currPageNo - 1) / bottomCount) + 1;
		int maxBlockNo = ((maxNum - 1) / bottomCount) + 1;
		
		int inx = 0;
		StringBuilder html = new StringBuilder();
		if (currPageNo > maxPageNo) {
			return "";
		}

		html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">   \n");
		html.append("<tr>                       \n");
		html.append("<td class=\"list_num\">                                                                    \n");
		html.append("<ul class=\"pagination pagination-sm\">                                                  \n");
		// <<
		if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
			html.append("<li><a href=\"javascript:" + scriptName + "( '" + url + "', 1 );\">  \n");
			html.append("&laquo;   \n");
			html.append("</a></li>      \n");
		}

		// <
		if (startPageNo > bottomCount) {
			html.append("<li><a href=\"javascript:" + scriptName + "( '" + url + "'," + (startPageNo - 1) + ");\"> \n");
			html.append("<        \n");
			html.append("</a></li>     \n");
		}

		// 1 2 3 ... 10 (숫자보여주기)
		for (inx = startPageNo; inx <= maxPageNo && inx <= endPageNo; inx++) {

			if (inx == currPageNo) {
				html.append("<li class='active'><a href='#'>" + inx + "</a></li>");
			} else {
				html.append("<li><a href=\"javascript:" + scriptName + "('" + url + "'," + inx
						+ ");\" class=\"num_text\">" + inx + "</a></li> \n");
			}
		}

		// >
		if (maxPageNo >= inx) {
			html.append("<li><a href=\"javascript:" + scriptName + "('" + url + "'," + ((nowBlockNo * bottomCount) + 1)
					+ ");\"> \n");
			html.append(">                       \n");
			html.append("</a></li>              \n");
		}

		// >>
		if (maxPageNo >= inx) {
			html.append("<li><a href=\"javascript:" + scriptName + "('" + url + "'," + maxPageNo + ");\">      \n");
			html.append("&raquo;     \n");
			html.append("</a></li>    \n");
		}
		html.append("</ul>  \n");
		html.append("</td>   \n");
		html.append("</tr>   \n");
		html.append("</table>   \n");

		return html.toString();
	}

	public static String nvl(String str, String defValue) {
		String retStr = "";
		if (null == str || str.equals("")) {
			retStr = defValue;
		} else {
			retStr = str.trim();
		}
		return retStr;
	}
}