package com.automation.test;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.base.SalesForceLogin;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;

@Listeners(com.automation.utility.SalesForceListenerUtility.class)
public class LoginScripts extends SalesForceLogin {

	private Logger mylog = LogManager.getLogger(LoginScripts.class);

	@Test (priority=1)
	public void invalid_Login() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		

		String userNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "invalid_username");
		String passwordData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "invalid_password");
		
		WebElement usernameEle=driver.findElement(By.name("username"));
		waitForVisibility1(usernameEle,30,"invalid_username");
		enterText(usernameEle,userNameData,"invalid user name");


		WebElement pwdEle=driver.findElement(By.id("password"));
		enterText(pwdEle,passwordData,"password");
		
		WebElement loginEle= driver.findElement(By.name("Login"));
		clickElement(loginEle,"Login");

		mylog.info("Completed test exectuion of invalid_Login");
		extentReportUtility.logTestInfo("Completed test exectuion of invalid_Login");

	}

	@Test(priority=5)
	public void valid_Login() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		

		String userNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "username");
		String passwordData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "password");
		WebElement usernameEle=driver.findElement(By.name("username"));
		waitForVisibility1(usernameEle,30,"username");
		enterText(usernameEle,userNameData,"user name");

		WebElement pwdEle=driver.findElement(By.id("password"));
		enterText(pwdEle,passwordData,"password");
		WebElement loginEle= driver.findElement(By.name("Login"));
		clickElement(loginEle,"Login");
		mylog.info("Completed test exectuion of valid_Login");
		extentReportUtility.logTestInfo("Completed test exectuion of valid_Login");

	}
   @Test(priority=2)
	public void rememberme_Login() throws InterruptedException {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		

		String userNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "username");
		String passwordData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "password");
		WebElement usernameEle=driver.findElement(By.name("username"));
		waitForVisibility1(usernameEle,30,"username");
		enterText(usernameEle,userNameData,"user name");

		WebElement pwdEle=driver.findElement(By.id("password"));
		enterText(pwdEle,passwordData,"password");

		WebElement remEle=driver.findElement(By.xpath("//*[@id=\"rememberUn\"]"));
		clickElement(remEle,"Rememberme check box");


		WebElement loginEle= driver.findElement(By.name("Login"));
		clickElement(loginEle,"Login");

		
		WebElement usermenuEle= driver.findElement(By.xpath("//*[@id=\"userNav\"]"));
		clickElement(usermenuEle,"user menu");

		userDropDownList("Logout");
		mylog.info("Logout button is clicked");
		mylog.info("Completed test exectuion of rememberme_Login");
		extentReportUtility.logTestInfo("Completed test exectuion of remember_Login");


	}
     @Test(priority=3)
	public void forgotpwd_invalid() throws InterruptedException {

		driver.manage().window().maximize();	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		

		String forgotUserNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "forgot_username");
        WebElement forgotPwdEle= driver.findElement(By.xpath("//*[@id=\"forgot_password_link\"]\r\n"));
		waitForVisibility1(forgotPwdEle,30,"username");
		clickElement(forgotPwdEle,"Login");

	
		WebElement forgotusernameEle=driver.findElement(By.xpath("//*[@id=\"un\"]"));
		enterText(forgotusernameEle,forgotUserNameData,"forgot user name");

		WebElement continueEle= driver.findElement(By.xpath("//*[@id=\"continue\"]\r\n"));
		clickElement(continueEle,"continue button");

		mylog.info("Completed test exectuion of forgotpwd_invalid");
		extentReportUtility.logTestInfo("Completed test exectuion of forgotpwd_invalid");


	}
     
     @Test(priority=4)

	public void forgotpwd_validation() throws InterruptedException {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		

		String userNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "usernamenew");
		String passwordData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "passwordnew");
		WebElement usernameEle=driver.findElement(By.name("username"));
		waitForVisibility1(usernameEle,30,"username");
		enterText(usernameEle,userNameData,"user name");

		WebElement pwdEle=driver.findElement(By.id("password"));
		enterText(pwdEle,"passwordData","password");

        WebElement loginEle= driver.findElement(By.name("Login"));
		clickElement(loginEle,"Login");
				
		WebElement errEle= driver.findElement(By.xpath("//*[@id=\"error\"]\r\n"));

         Assert.assertEquals("Please check your username and password. If you still can't log in, contact your Salesforce administrator.", errEle.getText());

		mylog.info("Completed test exectuion of forgotpwd_valid");
		extentReportUtility.logTestInfo("Completed test exectuion of forgotpwd_valid");


	}

}
