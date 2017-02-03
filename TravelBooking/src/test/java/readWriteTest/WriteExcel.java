package readWriteTest;

import java.io.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

	
	public class WriteExcel {
	  
		 
		        public void writeExcel(int row, String result) throws Exception
		        {
		                 
		        	FileInputStream fsIP= new FileInputStream(new File("D:\\Selenium\\WorkSpace\\TravelBooking\\TravelBooking\\TestCase.xlsx")); //Read the spreadsheet that needs to be updated
	                  
	                XSSFWorkbook wb = new XSSFWorkbook(fsIP); //Access the workbook
	                  
	                XSSFSheet worksheet = wb.getSheet("Suit"); //Access the worksheet, so that we can update / modify it.
	                  
	                Cell cell = null; // declare a Cell object
	                
	                cell = worksheet.getRow(row).getCell(2);   // Access the second cell in second row to update the value
	                  
	                cell.setCellValue(result);  // Get current cell value value and overwrite the value
	                
	                if(result.equalsIgnoreCase("PASS"))
	                {
	                CellStyle style = wb.createCellStyle();
	                style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	                worksheet.getRow(row).getCell(2).setCellStyle(style);
	                }
	                else
	                {
	                	CellStyle style = wb.createCellStyle();
		                style.setFillForegroundColor(IndexedColors.RED.getIndex());
		                style.setFillPattern(CellStyle.SOLID_FOREGROUND); 
		                worksheet.getRow(row).getCell(2).setCellStyle(style);
	                }
	                  
	                fsIP.close(); //Close the InputStream
	                 
	                FileOutputStream output_file =new FileOutputStream(new File("D:\\Selenium\\WorkSpace\\TravelBooking\\TravelBooking\\TestCase.xlsx"));  //Open FileOutputStream to write updates
	                  
	                wb.write(output_file); //write changes
	                wb.close();
	                output_file.close();  //close the stream 
   
		        }
		}
		