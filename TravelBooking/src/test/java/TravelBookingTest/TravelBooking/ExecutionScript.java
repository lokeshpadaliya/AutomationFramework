package TravelBookingTest.TravelBooking;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import readWriteTest.ReadExcel;
import readWriteTest.WriteExcel;


public class ExecutionScript {
	
	public static WebDriver driver;
	@BeforeTest
	
	public void setup()
	{
//		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\eclipse\\"+"chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\eclipse\\geckodriver-v0.13.0-win64\\geckodriver.exe");
		driver =new FirefoxDriver();
	}
	
	
	@Test
	
	public void executor() throws Exception
	{
		ReadExcel rex =new ReadExcel();
		WriteExcel wex=new WriteExcel();
		UiOps uo= new UiOps(driver);
		
		Sheet mySuit = rex.readExcel("D:\\Selenium\\WorkSpace\\TravelBooking\\TravelBooking","TestCase.xlsx" , "Suit");
		
		int suitRowCount= mySuit.getLastRowNum();
		int k=0;
		for (int i=1; i<=suitRowCount; i++ )
			
		{ 	
			Row suitRow=mySuit.getRow(i);
			if(suitRow.getCell(1).toString().equalsIgnoreCase("Y"))
			{
				
			String testName= suitRow.getCell(0).toString();
		
			Sheet mySheet = rex.readExcel("D:\\Selenium\\WorkSpace\\TravelBooking\\TravelBooking","TestCase.xlsx" , "KeywordFramework");
		
		    int rowCount = mySheet.getLastRowNum();
		    
		    
		    for (int j = k+1; j <=rowCount; j++) {
		    	
		        Row row = mySheet.getRow(j);
		        
		        if(row.getCell(0).toString().equalsIgnoreCase(testName))
		        { 
		        	UiOps.Flag=0;
		        	System.out.println(row.getCell(0).toString());
		        	
		        for (k=j; k<rowCount; k++)
		        { 		        
		        	 Row row1 = mySheet.getRow(k+1);
		        if(row1.getCell(0).toString().length()==0){		        	    	
		            uo.operations( row1.getCell(1).toString(), row1.getCell(2).toString(), row1.getCell(3).toString(), row1.getCell(4).toString(), row1.getCell(5).toString());
		            System.out.println("K= "+k);
		        }
		        else{
		             if (row1.getCell(0).toString().equalsIgnoreCase(testName))
		             {  
		                System.out.println("New Testcase->"+row1.getCell(0).toString() +" Started");
		             }
		             else
		            	 break;
		            }
		        }
		        
		        if(UiOps.Flag==1){
	        		wex.writeExcel(i, "Fail");
	        	}
		        else
		        {
		        	wex.writeExcel(i, "Pass");
		        }
		        
		     }
		        else
		        {
		        	if (row.getCell(0).toString().equalsIgnoreCase(testName)) //this may never occur
		                System.out.println("Test in progress");
		             else
		             {System.out.println("I'm in else before break");
		            	 break;}
		        }
		        	
		    
			}
			}
			
			else break;
		} 
	}
	
	@AfterTest 
	
	public void cleanup()
	{
		driver.quit();
	}

	
	

}
