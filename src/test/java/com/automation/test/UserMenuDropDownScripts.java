package com.automation.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.base.SalesForceLogin;
import com.automation.utility.Constants;
import com.automation.utility.ExcelUtils;
import com.automation.utility.PropertiesUtility;


@Listeners(com.automation.utility.SalesForceListenerUtility.class)
public class UserMenuDropDownScripts extends SalesForceLogin {

	@Test
	public void select_UserMenu() {

		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		WebElement usermenuEle= driver.findElement(By.id("userNav"));
		clickElement(usermenuEle,"user menu");

		WebElement userNavmenuEle= driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]"));

		List<WebElement> childs =  userNavmenuEle.findElements(By.tagName("a"));
		List<String> ch=new ArrayList<>();
		for(WebElement child:childs) {
			ch.add(child.getText());
		}

		String expectedStrVale = PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "userprofileoptions");
		List<String> expectedStr = Arrays.asList(expectedStrVale.split(","));

		boolean testcaseStatus = true;
		for(String str : expectedStr) {
			if(!ch.contains(str)) {			
				testcaseStatus = false;
				break;
			}		
		}
		Assert.assertEquals(testcaseStatus,true);

		mylog.info("Completed test exectuion of select_UserMenu");
		extentReportUtility.logTestInfo("Completed test exectuion of select_UserMenu");


	}



	@Test
	public void select_MySetting() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement usermenuEle= driver.findElement(By.id("userNav"));
		clickElement(usermenuEle,"user menu");


		userDropDownList("My Settings");

		WebElement personalEle= driver.findElement(By.xpath("//*[@id=\"PersonalInfo_font\"]"));
		clickElement(personalEle,"Personal menu");

		WebElement loginEle= driver.findElement(By.xpath("//*[@id=\"LoginHistory_font\"]"));
		clickElement(loginEle,"Login History menu");


		WebElement downloadButton= driver.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));

		waitForVisibility1(downloadButton, 30, "choose file button");
		clickElement(downloadButton,"Download Button");


		WebElement displayEle= driver.findElement(By.xpath("//*[@id=\"DisplayAndLayout\"]"));
		clickElement(displayEle,"Display And Layout menu");

		WebElement customEle= driver.findElement(By.xpath("//*[@id=\"CustomizeTabs_font\"]"));
		clickElement(customEle,"CustomizeTabs_font");

		WebElement customAppele = driver.findElement(By.id("p4"));
		selectByValue(customAppele , "02uak000007NPTW");


		WebElement availTabele = driver.findElement(By.id("duel_select_0"));
		selectByValue(availTabele , "report");

		WebElement addele = driver.findElement(By.xpath("//*[@id=\"duel_select_0_right\"]/img"));

		clickElement(addele,"Add arrow button");



		WebElement emailEle= driver.findElement(By.xpath("//*[@id=\"EmailSetup\"]"));
		clickElement(emailEle,"Email menu");

		WebElement emailsettingEle= driver.findElement(By.xpath("//*[@id=\"EmailSettings_font\"]"));
		clickElement(emailsettingEle,"EmailSettings_font");

		String emailNameData = null;
		try {
			emailNameData = ExcelUtils.readDataFromXL(Constants.SFDROPDOWN_XLDATA, Constants.SHEET_NAME, 2, 0);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		WebElement emailnameEle=driver.findElement(By.xpath("//*[@id=\"sender_name\"]"));
		enterText(emailnameEle,emailNameData,"email name");


		String emailAddressData = null;
		try {
			emailAddressData = ExcelUtils.readDataFromXL(Constants.SFDROPDOWN_XLDATA, Constants.SHEET_NAME, 2, 1);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		
		WebElement emailAdderessEle=driver.findElement(By.id("sender_email"));
		enterText(emailAdderessEle,emailAddressData,"mailAdderess");



		WebElement bccyesRadio= driver.findElement(By.xpath("//*[@id=\"auto_bcc1\"]"));
		Assert.assertEquals(false,!bccyesRadio.isSelected() );
		
		mylog.info("passed");

		WebElement saveEle= driver.findElement(By.name("save"));
		clickElement(saveEle,"Save menu");
		
		
		mylog.info("Completed test exectuion of Select_MySetting");
		extentReportUtility.logTestInfo("Completed test exectuion of Select_MySetting");





	}
	@Test
	public void select_DeveloperConsole() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement usermenuEle= driver.findElement(By.id("userNav"));
		clickElement(usermenuEle,"user menu");

		userDropDownList("Developer Console");

		Set<String> allWindows=driver.getWindowHandles();
		System.out.println("before total number of windows="+allWindows.size());
		for(String window:allWindows) {
			System.out.println(window);
		}
		System.out.println("..........."+driver.getWindowHandle());
		String parentWindow=driver.getWindowHandle();


		Set<String> allwindows2=driver.getWindowHandles();
		System.out.println("after total number of windows="+allwindows2.size());
		for(String handle:allwindows2) {

			if(!handle.equals(parentWindow))
				driver.switchTo().window(handle);
		}


		System.out.println("........."+driver.getWindowHandle());

		WebElement closeButtonEle= driver.findElement(By.xpath("//*[@id=\"tool-1180-toolEl\"]"));
		clickElement(closeButtonEle,"close Button new window");

		mylog.info("Completed test exectuion of select_DeveloperConsole");
		extentReportUtility.logTestInfo("Completed test exectuion of select_DeveloperConsole");




	}
	@Test
	public void select_Logout() {
		Login_SalesForce();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement usermenuEle= driver.findElement(By.id("userNav"));
		clickElement(usermenuEle,"user menu");


		WebElement userNavmenuEle= driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]"));
		List<WebElement> childs =  userNavmenuEle.findElements(By.tagName("a"));

		for(WebElement child:childs) {
			if(child.getText().equalsIgnoreCase("Logout")) {
				clickElement(child,"Logout");
			}
		}

		mylog.info("Completed test exectuion of select_Logout");
		extentReportUtility.logTestInfo("Completed test exectuion of select_Logout");


	}


}
