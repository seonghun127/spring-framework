package com.sist.retail.sale.svc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.sist.retail.common.StringUtil;
import com.sist.retail.vo.SaleTodayVo;

public class TodayExcelDown {
private Logger log=Logger.getLogger(TermExcelDown.class);
	
	//*.xls
	private HSSFWorkbook workbook;
	
	private String filePath;
	private String excelFileName;
	private String changFileName;
	
	private static short firstRow = 5;
	private static short firstCol = 1;
	
	private HSSFWorkbook createExcel(List<SaleTodayVo> data){
		workbook = new HSSFWorkbook();
		
		//0.sheet생성
		HSSFSheet sheet = workbook.createSheet("sheet1");
		//0_1 Font Setting
		HSSFFont  font  = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		titleStyle.setFillPattern(HSSFCellStyle.ALIGN_FILL);
		
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setFont(font);
		
		//1.Header
		HSSFRow   row    = sheet.createRow((short)this.firstRow);
		
		HSSFCell  cell_0 = row.createCell((short)firstCol);
		cell_0.setCellValue("상품번호");
		cell_0.setCellStyle(titleStyle);
		
		HSSFCell  cell_1 = row.createCell((short)1+firstCol);
		cell_1.setCellValue("상품군");
		cell_1.setCellStyle(titleStyle);
		
		HSSFCell  cell_2 = row.createCell((short)2+firstCol);
		cell_2.setCellValue("상품명");
		cell_2.setCellStyle(titleStyle);
		
		HSSFCell  cell_3 = row.createCell((short)3+firstCol);
		cell_3.setCellValue("가격");
		cell_3.setCellStyle(titleStyle);
		
		HSSFCell  cell_4 = row.createCell((short)4+firstCol);
		cell_4.setCellValue("수량");
		cell_4.setCellStyle(titleStyle);
		
		HSSFCell  cell_5 = row.createCell((short)5+firstCol);
		cell_5.setCellValue("매출액");
		cell_5.setCellStyle(titleStyle);
		
		//Style
		HSSFCellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setFont(font);
		
		//2.1.AlignCenter
		HSSFCellStyle contentStyleCenter = workbook.createCellStyle();
		contentStyleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyleCenter.setFont(font);
		
		//2.2.AlignLeft
		HSSFCellStyle contentStyleLeft = workbook.createCellStyle();
		contentStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyleLeft.setFont(font);
		
		//2.3.AlignRight
		HSSFCellStyle contentStyleRight = workbook.createCellStyle();
		contentStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		contentStyleRight.setFont(font);
				
		//null 처리
		if(null == data || data.isEmpty())return workbook;
		
		for(int i=0;i<data.size();i++){
			row = sheet.createRow((short)firstRow+(i+1));
			SaleTodayVo svo=data.get(i);
			
			//상품번호
			cell_0 = row.createCell((short)firstCol+0);
			cell_0.setCellValue(svo.getPdtNo());
			cell_0.setCellStyle(contentStyleCenter);
			
			//상품군
			cell_1 = row.createCell((short)firstCol+1);
			cell_1.setCellValue(svo.getPdtGb());
			cell_1.setCellStyle(contentStyleLeft);
			
			//상품명
			cell_2 = row.createCell((short)firstCol+2);
			cell_2.setCellValue(svo.getPdtNm());
			cell_2.setCellStyle(contentStyleLeft);
			
			//가격
			cell_3 = row.createCell((short)firstCol+3);
			cell_3.setCellValue(svo.getPrice());
			cell_3.setCellStyle(contentStyleLeft);
			
			//수량
			cell_4 = row.createCell((short)firstCol+4);
			cell_4.setCellValue(svo.getSalCnt());
			cell_4.setCellStyle(contentStyleLeft);
			
			//매출액
			cell_5 = row.createCell((short)firstCol+5);
			cell_5.setCellValue(svo.getPrice()*svo.getSalCnt());
			cell_5.setCellStyle(contentStyleLeft);
		}
		return workbook;
	}
	
	/**
	 *  ExcelDownload
	 * @param filePath
	 * @param excelFileName
	 * @param list
	 * @return
	 * @throws IOException
	 */
	public String writeExcel(String filePath
			,String excelFileName,List<SaleTodayVo> list)throws IOException{
		
		this.filePath = filePath;
		this.excelFileName = excelFileName;
		
		FileOutputStream fileOut = setFile(this.filePath,this.excelFileName);
		
		HSSFWorkbook wb= createExcel(list);
		try{
			wb.write(fileOut);
		}catch(IOException io){
			log.debug("==========================");
			log.debug(io.getMessage());
			log.debug("==========================");
			throw io;
		}finally{
			fileOut.close();
			wb.close();
		}
		return changFileName;
		
	}
	/**
	 *  File이 존재 하면 Rename
	 * @param filePath
	 * @param excelFileName
	 * @return FileOutputStream
	 * @throws FileNotFoundException 
	 */
	private FileOutputStream setFile(String filePath,String excelFileName) 
			throws FileNotFoundException{
		 File dir =new File(filePath);
	    if(!dir.exists())	dir.mkdirs();
	    
	    //File이 존재하면
	    String changeFileName = createFile(filePath,excelFileName);
	    FileOutputStream fOut = new FileOutputStream(filePath+File.separator+changeFileName);
	    return fOut;
	}
	
	/**
	 *  FileReName
	 * @param filePath
	 * @param excelFileName
	 * @return
	 */
	private String createFile(String filePath,String excelFileName){
		File file =new File(filePath,excelFileName);
		String changeFileName = excelFileName;
		if(file.isFile() == true){
			changeFileName = System.currentTimeMillis()+"_"+StringUtil.getUUID()+"_"+excelFileName;
		}
		this.changFileName = changeFileName;
		
		return changFileName;
	}
}
