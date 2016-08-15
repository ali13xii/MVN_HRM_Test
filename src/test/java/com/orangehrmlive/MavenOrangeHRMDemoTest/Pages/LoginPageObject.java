package com.orangehrmlive.MavenOrangeHRMDemoTest.Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {
	private static WebDriver driver;
	

	public LoginPageObject(WebDriver aDriver) {
		// TODO Auto-generated constructor stub
	
	
		driver= aDriver;

	}


	public LoginPageObject login(String USERNAME, String PASSWORD) throws Exception {
		// TODO Auto-generated method stub
		driver.findElement(By.id("txtUsername")).sendKeys(USERNAME);
		driver.findElement(By.id("txtPassword")).sendKeys(PASSWORD);
		
		driver.findElement(By.id("btnLogin")).click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
	//	Thread.sleep(2000);
		return new LoginPageObject(driver);
	}

}
