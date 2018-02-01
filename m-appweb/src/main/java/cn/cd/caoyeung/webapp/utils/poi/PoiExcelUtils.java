package cn.cd.caoyeung.webapp.utils.poi;

import java.text.SimpleDateFormat;

//import org.apache.commons.lang.StringUtils;
//import org.apache.poi.hssf.usermodel.DVConstraint;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDataValidation;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.ss.util.CellRangeAddressList;

public class PoiExcelUtils {
//	public static String SHEET_NAME_SPLIT_CHAR="-";
//	public static int MAX_NUM_PRE_PAGE = 60000; // 一个表格的最大写入数量
//
//	public static int START_ROW = 0;
//
//	public static int START_CELL = 0;
//
//	public static HSSFCellStyle getHeadNormalStyle(HSSFWorkbook workbook) {
//		HSSFFont headfont = workbook.createFont();
//		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		headfont.setColor(HSSFColor.BLACK.index);
//		HSSFCellStyle headStyle = workbook.createCellStyle();
//		headStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
//		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		headStyle.setFont(headfont);
//		headStyle.setWrapText(true);
//		headStyle.setBorderBottom((short) 1);
//		headStyle.setBorderLeft((short) 1);
//		headStyle.setBorderTop((short) 1);
//		headStyle.setBorderRight((short) 1);
//		return headStyle;
//	}
//
//	public static HSSFCellStyle getHeadNotNullStyle(HSSFWorkbook workbook) {
//		HSSFFont notNullfont = workbook.createFont();
//		notNullfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		notNullfont.setColor(HSSFColor.RED.index);
//		HSSFCellStyle notNullStyle = workbook.createCellStyle();
//		notNullStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
//		notNullStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		notNullStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		notNullStyle.setFont(notNullfont);
//		notNullStyle.setBorderBottom((short) 1);
//		notNullStyle.setBorderLeft((short) 1);
//		notNullStyle.setBorderTop((short) 1);
//		notNullStyle.setBorderRight((short) 1);
//		return notNullStyle;
//	}
//
//	public static HSSFCellStyle getBodyStyle(HSSFWorkbook workbook) {
//		HSSFCellStyle bodyStyle = workbook.createCellStyle();
//		bodyStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//		bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		bodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//		bodyStyle.setBorderBottom((short) 1);
//		bodyStyle.setBorderLeft((short) 1);
//		bodyStyle.setBorderTop((short) 1);
//		bodyStyle.setBorderRight((short) 1);
//		return bodyStyle;
//	}
//	
//
//    /**
//     * 
//     * @param workbook
//     * @return
//     */
//    public static CellStyle getHeadDisableStyle(Workbook workbook) {
//        Font font = workbook.createFont();
//        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//        font.setColor(IndexedColors.BLACK.getIndex());
//        CellStyle style = workbook.createCellStyle();
//        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        style.setFont(font);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setAlignment(CellStyle.ALIGN_CENTER);
//        style.setBorderBottom((short) 1);
//        style.setBorderLeft((short) 1);
//        style.setBorderTop((short) 1);
//        style.setBorderRight((short) 1);
//        return style;
//    } 
//    
//    public static HSSFCellStyle getBodyNotNullStyle(HSSFWorkbook workbook) {
//		HSSFFont notNullfont = workbook.createFont();
//		notNullfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		notNullfont.setColor(HSSFColor.RED.index);
//		HSSFCellStyle bodyStyle = workbook.createCellStyle();
//		bodyStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//		bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		bodyStyle.setFont(notNullfont);
//		bodyStyle.setBorderBottom((short) 1);
//		bodyStyle.setBorderLeft((short) 1);
//		bodyStyle.setBorderTop((short) 1);
//		bodyStyle.setBorderRight((short) 1);
//		return bodyStyle;
//	}
//	
//	public static HSSFCellStyle getBodyWhiteStyle(HSSFWorkbook workbook) {
//		HSSFCellStyle bodyStyle = workbook.createCellStyle();
//		bodyStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//		bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		bodyStyle.setBorderBottom((short) 1);
//		bodyStyle.setBorderLeft((short) 1);
//		bodyStyle.setBorderTop((short) 1);
//		bodyStyle.setBorderRight((short) 1);
//		return bodyStyle;
//	}
//     
//    /**
//     * @param workbook
//     * @return
//     */
//    public static CellStyle getBodyDisableStyle(Workbook workbook) {
//        Font font = workbook.createFont();
//        font.setColor(IndexedColors.GREY_50_PERCENT.getIndex());
//        CellStyle style = workbook.createCellStyle();
//        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        style.setFont(font);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setAlignment(CellStyle.ALIGN_LEFT);
//        style.setBorderBottom((short) 1);
//        style.setBorderLeft((short) 1);
//        style.setBorderTop((short) 1);
//        style.setBorderRight((short) 1);
//        return style;
//    }
//    
//    /**
//     * @param workbook
//     * @return
//     */
//    public static CellStyle getBodyRefrenceStyle(Workbook workbook) {
//        Font font = workbook.createFont();
//        font.setColor(IndexedColors.GREY_80_PERCENT.getIndex());
//        CellStyle style = workbook.createCellStyle();
//        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        style.setFont(font);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setAlignment(CellStyle.ALIGN_LEFT);
//        style.setBorderBottom((short) 1);
//        style.setBorderLeft((short) 1);
//        style.setBorderTop((short) 1);
//        style.setBorderRight((short) 1);
//        return style;
//    }
//
//	
//	/** 
//	    * 方法名称：SetDataValidation 
//	    * 内容摘要：设置数据有效性 
//	    * @param firstRow 
//	    * @param firstCol 
//	    * @param endRow 
//	    * @param endCol  
//	    */  
//	 public static HSSFDataValidation setDataValidation(String[] textList,int firstRow,  
//			 int firstCol, int endRow, int endCol) {  
//	    //加载下拉列表内容  
//	    DVConstraint constraint = DVConstraint.createExplicitListConstraint(textList);        
//	    CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);
//	    //数据有效性对象  
//	    HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);
//	    return data_validation;  
//	}
//	 
//	public static Object getCellValue(Cell c,Sheet sheet) {
//		Object value = null;
//		CellRangeAddress ca = getMergedRegionByCell(c,sheet);
//		if (ca != null) {
//			int startCol = ca.getFirstColumn();
//			int startRow = ca.getFirstRow();
//			int endCol = ca.getLastColumn();
//			int endRow = ca.getLastRow();
//			for(int i = startRow; i <= endRow; i++) {
//				for(int j = startCol; j <= endCol; j++) {
//					Row r = sheet.getRow(i);
//					Cell cc = r.getCell(j);
//					if(cc != null) {
//						value = PoiExcelUtils.getCellValue(cc);
//					}
//					if(value != null) {
//						return value;
//					}
//				}
//			}
//		}else {
//			value = PoiExcelUtils.getCellValue(c);
//		}
//		return value;
//	}
//	
//	public static CellRangeAddress getMergedRegionByCell(Cell c, Sheet s) {
//		int ri = c.getRowIndex();
//		int ci = c.getColumnIndex();
//		int reginNum = s.getNumMergedRegions();
//		for(int i = 0 ; i < reginNum; i++) {
//			CellRangeAddress ca = s.getMergedRegion(i);
//			int startCol = ca.getFirstColumn();
//			int startRow = ca.getFirstRow();
//			int endCol = ca.getLastColumn();
//			int endRow = ca.getLastRow();
//			if(ri >= startRow && ri <= endRow && ci >= startCol && ci <= endCol) {
//				return ca;
//			}
//		}
//		return null;
//	}
//	 
//	 public static Object getCellValue(Cell c){
//			Object value=null;
//			CellStyle s = c.getCellStyle();
//			short i = s.getDataFormat();
//			switch (c.getCellType())
//		      {
//		      case 3:
//		        break;
//		      case 4:
//		        if (c.getBooleanCellValue())
//		        	value = ("true");
//		        else
//		          value = ("false");
//		        break;
//		      case 0:
//		        if(DateUtil.isCellDateFormatted(c)){
//		        	value=c.getDateCellValue();
//		        	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		        	value = dateformat.format(value);
//		        }else{
//		        	value = c.getNumericCellValue();
//		        }
//		        break;
//		      case 1:
//		        value = StringUtils.trimToEmpty(c.getRichStringCellValue().getString());
//		        break;
//		      case 2:
//		      default:
//		      }
//			return value;
//		}


}
