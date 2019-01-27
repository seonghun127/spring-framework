package com.sist.retail.storeService.svc;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.retail.common.DTO;
import com.sist.retail.sale.svc.TermExcelDown;
import com.sist.retail.sale.svc.TodayExcelDown;
import com.sist.retail.storeService.dao.StoreServiceSaleDao;
import com.sist.retail.vo.SaleTermVo;
import com.sist.retail.vo.SaleTodayVo;

@Service
public class StoreServiceSaleSvcImpl implements StoreServiceSaleSvc {
	
	@Autowired
	private StoreServiceSaleDao storeServiceSaleDao;
	
	@Override
	public DTO do_searchOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final String PATH = System.getProperty("ROOT")+"props"+File.separator+"Download"+File.separator;
	
	/**
	 * 금일 매출 현황
	 */
	@Override
	public List<?> do_search(DTO dto) {
		// TODO Auto-generated method stub
		return storeServiceSaleDao.do_search(dto);
	}

	@Override
	public List<?> do_searchTerm(DTO dto) {
		// TODO Auto-generated method stub
		return storeServiceSaleDao.do_searchTerm(dto);
	}
	
	/**
	 * 기간별 매출 엑셀다운로드 
	 */
	@Override
	public String do_excelDownTermSale(DTO dto) throws IOException {
		String fileName = "";
		
		//DataRead
		List<SaleTermVo> list = (List<SaleTermVo>) storeServiceSaleDao.do_searchTerm(dto);
				
		TermExcelDown termExcelDown=new TermExcelDown();
		fileName = termExcelDown.writeExcel(PATH,"termSale.xls", list);
		
		return PATH+fileName;
	}
	
	/**
	 * 금일 매출 액셀다운로드
	 */
	@Override
	public String do_excelDownTodaySale(DTO dto) throws IOException {
		String fileName = "";
		
		//DataRead
		List<SaleTodayVo> list = (List<SaleTodayVo>) storeServiceSaleDao.do_search(dto);
		TodayExcelDown todayExcelDown=new TodayExcelDown();
		fileName = todayExcelDown.writeExcel(PATH, "todaySale.xls", list);
		
		return PATH+fileName;
	}
	
}
