package com.orangehrmlive.MavenOrangeHRMDemoTest.Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MainPageObject {
private static WebDriver driver;
	

	public MainPageObject(WebDriver aDriver) {
		// TODO Auto-generated constructor stub
	
	
		driver= aDriver;

	}
	public MainPageObject addtrainingsession() {
		Actions builder = new Actions(driver);
		WebElement menu= driver.findElement(By.id("menu_training_defaultTrainingModulePage"));
		
		
		builder.moveToElement(menu).build().perform();
		
		
		
		WebElement menuSessionList= driver.findElement(By.id("menu_training_viewSessionList"));
		menuSessionList.click();
		
		
		driver.findElement(By.id("btnAdd")).click();
		return new MainPageObject(driver);
	}
    public MainPageObject addsessiondetails(String NAME, String COURSE){
driver.findElement(By.id("addSession_name")).sendKeys(NAME);
		
		Select CourseList=new Select(driver.findElement(By.id("addSession_course")));
		
		CourseList.selectByVisibleText(COURSE);
		//Only one course in list on web app "BP03120"
		
		return new MainPageObject(driver);
    	
    	
    }
     public MainPageObject	schedulestartdateforsession(String MONTH,String DAY, String YEAR) throws Exception{
    	 driver.findElement(By.id("searchSession_scheduledDate")).click();
 		
    	 Select Month=new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]")));
    	 		
    	 		Month.selectByVisibleText(MONTH);
    	 		
    	 Select Year=new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]")));
    	 		
    	 		Year.selectByVisibleText(YEAR);
    	 		
    	 		WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
    	 		List<WebElement> rows=dateWidget.findElements(By.tagName("tr"));
    	 		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
    	 		
    	 		for (WebElement cell: columns){
    	 			 
    	 			   if (cell.getText().equals(DAY)){
    	 			   cell.findElement(By.linkText(DAY)).click();
    	 			   break;
    	 			}
    	 		}
    	 		Thread.sleep(1000);
    	 		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    	 		
    	 		return new MainPageObject(driver);
    	 
     }
    
     public MainPageObject duedateforsession(String MONTH,String DAY, String YEAR) throws Exception{
    	 
    	 driver.findElement(By.id("searchSession_dueDate")).click();
 		
 		Select Month=new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]")));
 		
 		Month.selectByVisibleText(MONTH);
 		
 		Select Year=new Select(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]")));
 		
 		Year.selectByVisibleText(YEAR);
 		
 		
 		WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
 		List<WebElement> rows=dateWidget.findElements(By.tagName("tr"));
 		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
 		
 		for (WebElement cell: columns){
 			 
 			   if (cell.getText().equals(DAY)){
 			   cell.findElement(By.linkText(DAY)).click();
 			   break;
 			}
 		}
 		//driver.findElement(By.id("btnSave")).click();
 		
 	//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
 	Thread.sleep(1000);
 	
 		
 	
 		return new MainPageObject(driver);
     }
     
     public MainPageObject Savetrainingsession(){
    	 driver.findElement(By.id("btnSave")).click();
    	 return new MainPageObject(driver);
    	 
     }
}
