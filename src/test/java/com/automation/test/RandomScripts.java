package com.automation.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.automation.base.SalesForceLogin;

public class RandomScripts extends SalesForceLogin {
	
	@Test
	
	public void verify_FirstLastName() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement homeMenuEle= driver.findElement(By.xpath("//*[@id=\"home_Tab\"]/a"));
		clickElement(homeMenuEle,"Home Tab");
		WebElement firstLastNameEle= driver.findElement(By.xpath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a"));
		clickElement(firstLastNameEle,"First and Last Name Tab");
		
		mylog.info("Completed test exectuion of verify_FirstLastName");
		extentReportUtility.logTestInfo("Completed test exectuion of verify_FirstLastName");

		
		
	}

    @Test
	public void verify_TabCustomization() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement plusButtonEle= driver.findElement(By.xpath("//*[@id=\"AllTab_Tab\"]/a/img"));
		clickElement(plusButtonEle,"Plus Tab");
		
		WebElement custTabEle= driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/table/tbody/tr/td[2]/input"));
		clickElement(custTabEle,"Customize My Tab");
		
		WebElement selectedTabEle= driver.findElement(By.xpath("//*[@id=\"duel_select_1\"]/option[12]"));
		selectByVisibleText(selectedTabEle,"Cases");
		
		WebElement removeButtonEle= driver.findElement(By.xpath("//*[@id=\"duel_select_0_left\"]/img"));
		clickElement(removeButtonEle,"Remove");
		
		WebElement savButtonEle= driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		clickElement(savButtonEle,"Save");
		
		WebElement usermenuEle= driver.findElement(By.id("userNav"));
		clickElement(usermenuEle,"user menu");

		
		userDropDownList("Logout");
		
		Login_SalesForce();

		mylog.info("Completed test exectuion of verify_TabCustomization");
		extentReportUtility.logTestInfo("Completed test exectuion of verify_TabCustomization");



}
}
