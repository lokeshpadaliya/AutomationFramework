package TravelBookingTest.TravelBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Screenshot;

public class UiOps {
	
	WebDriver driver;
	
	public static int Flag;
	
	Screenshot ts=new Screenshot();
	
	public UiOps(WebDriver driver)
	{
		this.driver= driver;
	}
	
	public void operations(String Actions,String Obj, String ObjType, String Value, String ObjIdentifier) throws Exception
	{
		System.out.println(Actions + ObjType + Value + ObjIdentifier);
		
		switch (Actions.toUpperCase()) 
		{
        case "CLICK":
            //Perform click
        	System.out.println(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size());
        	if(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size()!=0 && driver.findElement(this.getObject(ObjIdentifier, ObjType)).isDisplayed())
        	{       		
        	System.out.println("test isDisplayed");
        	driver.findElement(this.getObject(ObjIdentifier, ObjType)).click();
            Thread.sleep(3000);
            break;
        		
        	}
        	else
        		{
        		Flag=1;
        		Thread.sleep(5000);
        		ts.getscreenshot(Obj);
        		break;
        		
        		}
        case "SETTEXT":
            //Set text on control
        	driver.findElement(this.getObject(ObjIdentifier, ObjType)).sendKeys(Value);
            break;
            
        case "GOTOURL":
            //Get url of application
        	String URL=Value;
        	driver.get(URL);
        	Thread.sleep(5000);
            break;
        case "GETTEXT":
            //Get text of an element
        	driver.findElement(this.getObject(ObjIdentifier, ObjType)).getText();
            break;
        default:
            break;
        }
		
	}
	
	public By getObject(String ObjIdentifier, String ObjType ) throws Exception
	
	{
		//Properties p= new Properties();
		//Find by xpath
        if(ObjType.equalsIgnoreCase("XPATH")){     	
        	
            return By.xpath(ObjIdentifier);
        }
        //find by class
        else if(ObjType.equalsIgnoreCase("CLASSNAME")){
            
            return By.className(ObjIdentifier);
            
        }
        //find by name
        else if(ObjType.equalsIgnoreCase("NAME")){
            
            return By.name(ObjIdentifier);
            
        }
        //Find by css
        else if(ObjType.equalsIgnoreCase("CSS")){
            
            return By.cssSelector(ObjIdentifier);
            
        }
        //find by link
        else if(ObjType.equalsIgnoreCase("LINK")){
            
            return By.linkText(ObjIdentifier);
            
        }
        //find by partial link
        else if(ObjType.equalsIgnoreCase("PARTIALLINK")){
            
            return By.partialLinkText(ObjIdentifier);
      
            
        }else
        {
            throw new Exception("Wrong object type");
        }
		
	}

}
