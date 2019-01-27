package com.sist.retail.storeService.svc;

import java.io.IOException;
import java.util.List;

import com.sist.retail.common.DTO;

public interface StoreServiceSaleSvc {
	/**
	 *	
	 * @param dto
	 * @return dto
	 */
	public DTO do_searchOne(DTO dto);
	
	/**
	 * 금일 매출 현황
	 * @param dto
	 * @return
	 */
	public List<?> do_search(DTO dto);
	
	/**
	 * 기간별 매출 현황
	 * @param dto
	 * @return
	 */
	public List<?> do_searchTerm(DTO dto);
	
	/**
	 * 기간별 매출 액셀다운
	 * @throws IOException 
	 */
	public String do_excelDownTermSale(DTO dto) throws IOException;
	
	/**
	 * 금일 매출 액셀다운
	 * @throws IOException 
	 */
	public String do_excelDownTodaySale(DTO dto) throws IOException;
}
