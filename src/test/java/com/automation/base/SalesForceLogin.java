package com.automation.base;



import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertiesUtility;


public class SalesForceLogin extends BaseTest{
	
	protected Logger mylog=LogManager.getLogger(SalesForceLogin.class);
	protected ExtentReportsUtility extentReportUtility = ExtentReportsUtility.getInstance();

	@BeforeMethod
	@Parameters("browser name")
	public void setUpBeforeMethod(@Optional("chrome") String name) throws InterruptedException {
		
		launchBrowser("chrome");
		String url=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES,"url");
		goToUrl(url);
		
	}
	@AfterMethod
	public void tearDownAfterMethod() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);		
		closeDriver();
		mylog.debug("teardown after test method");
	}

	@Test
	public static void Login_SalesForce() {
		
		String userNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "username");
		String passwordData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "password");
		WebElement usernameEle=driver.findElement(By.name("username"));
		waitForVisibility1(usernameEle,30,"username");
		enterText(usernameEle,userNameData,"user name");


		WebElement pwdEle=driver.findElement(By.id("password"));
		enterText(pwdEle,passwordData,"password");
		WebElement loginEle= driver.findElement(By.name("Login"));
		clickElement(loginEle,"Login");

	}

}


