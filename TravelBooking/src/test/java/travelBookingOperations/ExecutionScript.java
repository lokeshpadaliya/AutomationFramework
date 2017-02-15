package travelBookingOperations;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import readWriteTest.ReadExcel;
import readWriteTest.WriteExcel;
import utility.Report;


public class ExecutionScript {
	
	public static WebDriver driver;
	
	@BeforeTest
	public void setup()
	{	
		//driver =new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\eclipse\\"+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	@Test
	public void executor() throws Exception
	{
		ReadExcel rex =new ReadExcel();
		WriteExcel wex=new WriteExcel();
		UiOps uo= new UiOps(driver);
		Report rep=new Report();
		rep.htmlWriter();
		
		Sheet mySuit = rex.readExcel(System.getProperty("user.dir"),"TestCase.xlsx" , "Suit");
		
		int suitRowCount= mySuit.getLastRowNum();
		int k=0;
		for (int i=1; i<=suitRowCount; i++ )	
		{ 	
			Row suitRow=mySuit.getRow(i);
			if(suitRow.getCell(1).toString().equalsIgnoreCase("Y") || suitRow.getCell(1).toString().equalsIgnoreCase("YES"))
			{
			System.out.println(i+">"+suitRow.getCell(0).toString()+" is selected as "+suitRow.getCell(1).toString() );
			String testName= suitRow.getCell(0).toString();
			Sheet mySheet = rex.readExcel(System.getProperty("user.dir"),"TestCase.xlsx" , "KeywordFramework");
		    int rowCount = mySheet.getLastRowNum();
		    for (int j =1; j <=rowCount; j++) {
		        Row row = mySheet.getRow(j);   
		        if(row.getCell(0).toString().equalsIgnoreCase(testName))
		        { 	
		        	System.out.println(testName+" is in Execution");
		        	UiOps.Flag=0;		        	
		        for (k=j; k<rowCount; k++)
		        { 		        
		        	 Row row1 = mySheet.getRow(k+1);
		        if(row1.getCell(0).toString().length()==0){		        	    	
		            uo.operations( row1.getCell(1).toString(), row1.getCell(2).toString(), row1.getCell(3).toString(), row1.getCell(4).toString(), row1.getCell(5).toString(), row1.getCell(6).toString());
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
	        		wex.writeExcel(System.getProperty("user.dir"),"TestCase.xlsx" , "Suit", i, "Fail");
	        		rep.htmlResultWriter(i, row.getCell(0).toString(),"Fail" );
	        	}
		        else
		        {
		        	wex.writeExcel(System.getProperty("user.dir"),"TestCase.xlsx" , "Suit", i, "Pass");
		        	rep.htmlResultWriter(i, row.getCell(0).toString(),"Pass" );
		        }
		     }
		        else
		        {
		        	System.out.println("Test in progress");      
		        }
			}
			}
			else
			{
				wex.writeExcel(System.getProperty("user.dir"),"TestCase.xlsx" , "Suit", i, "NotExecuted");
				Row notExecuted=mySuit.getRow(i);
				rep.htmlResultWriter(i, notExecuted.getCell(0).toString() ,"NotExecuted" );
			}

		} 
		
		System.out.println("End");
		Report.WriteToFile(Report.htmlStringBuilder.toString(), "Results.html");
	}
	
	@AfterTest 	
	public void cleanup()
	{
		driver.quit();
	}
}
