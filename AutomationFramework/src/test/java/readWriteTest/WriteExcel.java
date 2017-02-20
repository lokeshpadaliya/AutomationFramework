package readWriteTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;


	
	public class WriteExcel {
	  
		 
		        public void writeExcel(String path, String fileName, String sheetName, int row, String result) throws Exception
		        {
		                 
		        	FileInputStream inputStream= new FileInputStream(path +"\\"+ fileName); 
		        	
		        	Workbook wb = null;
		    	    
		    	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
		    	    
		    	    if(fileExtensionName.equals(".xlsx")){
		    	   
		    	    	wb = new XSSFWorkbook(inputStream);
		    	    }
		    	  
		    	    else if(fileExtensionName.equals(".xls")){   	       
		    	    	wb = new HSSFWorkbook(inputStream);
		    	    }  
	                Sheet worksheet = wb.getSheet(sheetName);
	                   
	                Cell cell2, cell3= null;
	                
	                cell2 = worksheet.getRow(row).getCell(2); 
	                cell3 = worksheet.getRow(row).getCell(3);
	                Date d=new Date();
	                cell2.setCellValue(result);
	                cell3.setCellValue(d.toString());
	                
		                if(result.equalsIgnoreCase("PASS"))
		                {
			                CellStyle style = wb.createCellStyle();
			                style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			                worksheet.getRow(row).getCell(2).setCellStyle(style);
		                }
		               	if(result.equalsIgnoreCase("NotExecuted"))
	                	{
	                		CellStyle style = wb.createCellStyle();
			                style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
			                style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
			                worksheet.getRow(row).getCell(2).setCellStyle(style);
	                	}
	                	
		            	if(result.equalsIgnoreCase("Fail"))
		            	{		                	
		                	CellStyle style = wb.createCellStyle();
			                style.setFillForegroundColor(IndexedColors.RED.getIndex());
			                style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
			                worksheet.getRow(row).getCell(2).setCellStyle(style);
		                }

	                
	                inputStream.close(); 
	                 
	                FileOutputStream output_file =new FileOutputStream(new File(System.getProperty("user.dir") + "\\TestCase.xlsx")); 
	                  
	                wb.write(output_file);
	                output_file.close(); 
	                wb.close();
	                
   
		        }
		}
		