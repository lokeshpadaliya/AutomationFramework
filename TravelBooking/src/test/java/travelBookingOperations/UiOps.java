package travelBookingOperations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

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
		
		switch (Actions.toUpperCase()) 
		{
        case "CLICK":
        	System.out.println(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size());
        	if(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size()!=0 && driver.findElement(this.getObject(ObjIdentifier, ObjType)).isDisplayed())
        	{       		
        	System.out.println("Inside CLICK");
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
        	System.out.println(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size());
        	if(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size()!=0 && driver.findElement(this.getObject(ObjIdentifier, ObjType)).isDisplayed())
        	{       		
        	System.out.println("Inside SETTEXT");
        	driver.findElement(this.getObject(ObjIdentifier, ObjType)).sendKeys(Value);
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
        case "GOTOURL":
        	System.out.println(Value);
        	if(Value!=null)
        	{       		
        	System.out.println("Inside GOTOURL");
        	driver.get(Value);
        	Thread.sleep(5000);
            break;	
        	}
        	else
        	{
    		Flag=1;
    		Thread.sleep(5000);
    		ts.getscreenshot(Obj);
    		break;
        	}
        case "GETTEXT":
        	System.out.println(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size());
        	if(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size()!=0 && driver.findElement(this.getObject(ObjIdentifier, ObjType)).isDisplayed())
        	{       		
        	System.out.println("Inside GETTEXT");
        	driver.findElement(this.getObject(ObjIdentifier, ObjType)).getText();
            break;	
        	}
        	else
        	{
    		Flag=1;
    		Thread.sleep(5000);
    		ts.getscreenshot(Obj);
    		break;
        	}   
        case "MOVETOELEMENT":
        	System.out.println(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size());
        	if(driver.findElements(this.getObject(ObjIdentifier, ObjType)).size()!=0 && driver.findElement(this.getObject(ObjIdentifier, ObjType)).isDisplayed())
        	{       		
        	System.out.println("Inside MOVETOELEMENT");
        	Actions act = new Actions(driver);
       	 	act.moveToElement(driver.findElement(this.getObject(ObjIdentifier, ObjType))).click().build().perform();
       	 	Thread.sleep(2000);	
        	}
        	else
        	{
    		Flag=1;
    		Thread.sleep(5000);
    		ts.getscreenshot(Obj);
    		break;
        	} 	 
        default:
            break;
        }
		
	}
	
	public By getObject(String ObjIdentifier, String ObjType ) throws Exception
	
	{
        if(ObjType.equalsIgnoreCase("XPATH")){     	
        	
            return By.xpath(ObjIdentifier);
        }
        else if(ObjType.equalsIgnoreCase("CLASSNAME")){
            
            return By.className(ObjIdentifier);
            
        }
        else if(ObjType.equalsIgnoreCase("NAME")){
            
            return By.name(ObjIdentifier);
            
        }
        else if(ObjType.equalsIgnoreCase("CSS")){
            
            return By.cssSelector(ObjIdentifier);
            
        }
        else if(ObjType.equalsIgnoreCase("LINK")){
            
            return By.linkText(ObjIdentifier);
            
        }
        else if(ObjType.equalsIgnoreCase("PARTIALLINK")){
            
            return By.partialLinkText(ObjIdentifier);
                  
        }
        else if(ObjType.equalsIgnoreCase("TAGNAME")){
            
            return By.tagName(ObjIdentifier);
                  
        }   
        else
        {
            throw new Exception("Wrong object type");
        }
		
	}

}
