package com.orangehrmlive.MavenOrangeHRMDemoTest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.orangehrmlive.MavenOrangeHRMDemoTest.Pages.LoginPageObject;
import com.orangehrmlive.MavenOrangeHRMDemoTest.Pages.MainPageObject;
import com.thoughtworks.selenium.Wait;

import org.openqa.selenium.WebElement;

public class OrangeHRMDemoTest {
	
	WebDriver driver;
	String RetryLogin="//*[@id='frmRetryLogin']/fieldset/ul/li";
	String EMPTY="//*[@id='spanMessage']";
	String SAVEDSUCCESSFUL="//*[@id='addSession']/div";
	String NameError="//*[@id='addSession_name-error']";
	String CourseError="//*[@id='addSession_course-error']";
	String SceduleDateError="//*[@id='searchSession_scheduledDate-error']";
	String Welcome="//*[@id='welcome']";
	
	
	@Before
	
	public void setup(){
		driver=new FirefoxDriver();
		driver.navigate().to("http://enterprise.demo.orangehrmlive.com/symfony/web/index.php/auth/login");;
	
		
	}

	
	@After
	
	public void tearDown(){
		driver.close();
	}
	
	@Test
	public void TCID001_LOGIN() throws Exception{
		//negative Test Case
		//Incorrect Username and Password
		System.out.println("Running TCID001_Login");
		System.out.println("");
		
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.login("Bob","Bob");
		
		
		VerifyTextIsPresent(RetryLogin,"TCID001_Login");
	

	
		
		
	}
	@Test
	public void TCID002_LOGIN() throws Exception{
		//negative Test Case
		//Correct Username
		//Incorrect Password
		System.out.println("Running TCID002_Login");
		System.out.println("");
		
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.login("anthony.nolan","bob");
		
		
		VerifyTextIsPresent(RetryLogin,"TCID002_Login");
		
	}
	@Test
	public void TCID003_LOGIN() throws Exception{
		//Negative Test case
		//Username and Password field left blank
		
		System.out.println("Running TCID003_Login");
		System.out.println("");
		
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.login("","");
		
		Wait(2,EMPTY,"Username cannot be empty");
		
		VerifyTextIsPresent(EMPTY,"TCID003_Login");
		
		}
	@Test
	public void TCID004_LOGIN() throws Exception{
		//Ngative Test Case
		//Username field is correct
		//Password field is left blank
		
		System.out.println("Running TCID004_Login");
		System.out.println("");
		
		
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.login("anthony.nolan","");

	
		Wait(2,EMPTY,"Password cannot be empty");
		
		VerifyTextIsPresent(EMPTY,"TCID004_Login");
		
		
		
	}
	
	@Test
	public void TCID005_LOGIN() throws Exception{
		//postive Test Case
		
		System.out.println("Running TCID005_Login");
		System.out.println("");
		
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.login("anthony.nolan","anthony.nolan");
	
		
			Wait(2,Welcome,"Welcome Anthony");
	
			VerifyTextIsPresent(Welcome,"TCID005_Login");
	
	
	
	
	}
	@Test
	public void TCID001_SUP_ADD_TRAINING_SESSION() throws Exception{
	
		//Positive Test Case
		System.out.println("Running TCID001_SUP_ADD_TRAINING_SESSION");
		System.out.println("");
		
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.login("anthony.nolan","anthony.nolan");
		
		MainPageObject mainpage= new MainPageObject(driver);
		mainpage.addtrainingsession()
				.addsessiondetails("John","BP03120")
				.schedulestartdateforsession("Oct","30","2016")
				.duedateforsession("Nov","30","2016")
				.Savetrainingsession();
		
				Wait(2,SAVEDSUCCESSFUL,"Successfully Saved");
	
				VerifyTextIsPresent(SAVEDSUCCESSFUL,"TCID001_SUP_ADD_TRAINING_SESSION");
				
		

		
	}	
	@Test
	public void TCID002_SUP_ADD_TRAINING_SESSION() throws Exception{
		
		System.out.println("Running TCID002_SUP_ADD_TRAINING_SESSION");
		System.out.println("");
		
		
		//Neg Test Case
		//Name,Course, and Schedule Date left blank/default
		
		
		
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.login("anthony.nolan","anthony.nolan");
		
		MainPageObject mainpage= new MainPageObject(driver);
		mainpage.addtrainingsession()
				.addsessiondetails("", "--Select--")
				.duedateforsession("Nov", "24", "2016")
				.Savetrainingsession();
				
		
				Wait(2,NameError,"Required");

				VerifyMultipleTextsArePresent(NameError,CourseError,SceduleDateError,"TCID002_SUP_ADD_TRAINING_SESSION");

	}
		

		
		@Test
		public void TCID003_SUP_ADD_TRAINING_SESSION() throws Exception{
			
			System.out.println("Running TCID003_SUP_ADD_TRAINING_SESSION");
			System.out.println("");
			
			//Old Dates are selected for Schedule Date and Due Date field
			//Name and Course Field are correct
			
			LoginPageObject loginpage = new LoginPageObject(driver);
			loginpage.login("anthony.nolan","anthony.nolan");
			MainPageObject mainpage= new MainPageObject(driver);
			
			mainpage.addtrainingsession().
					addsessiondetails("John", "BP03120").
					schedulestartdateforsession("Jul","25","2001").
					duedateforsession("Nov","30","2001").
					Savetrainingsession();
			
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
					
					VerifyTextIsNotPresent(SAVEDSUCCESSFUL,"TCID003_SUP_ADD_TRAINING_SESSION");
	
			
		
			
		}	
		private boolean TextXpath(String x){
			return driver.findElement(By.xpath(x)).isDisplayed();
		}
		private void VerifyMultipleTextsArePresent(String Xp1, String Xp2,String Xp3,String TCID){
			
			if(TextXpath(Xp1)&&TextXpath(Xp2)&&TextXpath(Xp3)){
				System.out.println("	"+TCID+" PASS");
				System.out.println("");
			}
else{
				System.out.println("	"+TCID+" FAIL");
				System.out.println("");
	}
			
		}
		private void VerifyTextIsNotPresent(String Locator,String TestCaseID){
		
			if(TextXpath(Locator)){
				
				
				System.out.println("	"+TestCaseID+" FAIL");
				System.out.println("");
			}
			else{
				System.out.println("	"+TestCaseID+" PASS");
				System.out.println("");
			}

			
		}
		private void VerifyTextIsPresent(String Locator,String TestCaseID){
			if(TextXpath(Locator)){
				System.out.println("	"+TestCaseID+" PASS");
				System.out.println("");
			}
			else{
				System.out.println("	"+TestCaseID+" Fail");
				System.out.println("");
			}
		}
		private void Wait(int num,String Locator,String Text){
			WebDriverWait wait=new WebDriverWait(driver, num);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(Locator), Text));
		}
		
	
		
}
		
		
		
		
