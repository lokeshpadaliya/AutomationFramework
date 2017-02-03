package utility;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import TravelBookingTest.TravelBooking.ExecutionScript;

public class Screenshot {
	
	public static WebDriver driver =ExecutionScript.driver;
	
	public void getscreenshot(String Obj) throws Exception 
    {	
		String Object= Obj;
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		 // now copy the  screenshot to desired location using copyFile //method
			System.out.println( System.currentTimeMillis()+" I'm in try of screenshot");
		FileUtils.copyFile(src, new File("D:/Selenium/WorkSpace/TravelBooking/"+ Object+"_" + System.currentTimeMillis() + ".jpg"));
		}
		 
		catch (IOException e)
		 {
		  System.out.println(e.getMessage());
		 
		 }
    }
	
}
