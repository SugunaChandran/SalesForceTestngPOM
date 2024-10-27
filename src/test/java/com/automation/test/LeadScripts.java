package com.automation.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.base.SalesForceLogin;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;

@Listeners(com.automation.utility.SalesForceListenerUtility.class)
public class LeadScripts extends SalesForceLogin {
	
	@Test(alwaysRun=true)
	public void check_LeadTabLink() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	
		WebElement leadEle= driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		waitForVisibility1(leadEle,30,"Lead button");
		clickElement(leadEle,"Lead menu");
		
		
		mylog.info("Completed test exectuion of check_LeadTabLink");
		extentReportUtility.logTestInfo("Completed test exectuion of check_LeadTabLink");

	}
	@Test(dependsOnMethods= {"check_LeadTabLink"})
	public void check_LeadsSelectView() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		
		WebElement leadEle= driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leadEle,"Lead menu");
		
		WebElement viewLeadEle= driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		clickElement(viewLeadEle,"Lead menu");

		Select viewEle=new Select(viewLeadEle);	

		List<WebElement> allOptions = viewEle.getOptions();

		List dropDownEntries = new ArrayList<>();		
		for (WebElement op : allOptions) {			
			dropDownEntries.add(op.getText());  
		}
		String expectedStrVale = PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Leadoptions");
		List<String> expectedStr = Arrays.asList(expectedStrVale.split(","));

		boolean testcaseStatus = true;
		for(String str : expectedStr) {
			if(!dropDownEntries.contains(str)) {			
				testcaseStatus = false;
				break;
			}		
		}
		Assert.assertEquals(testcaseStatus,true);

		mylog.info("Completed test exectuion of check_LeadsSelectView");
		extentReportUtility.logTestInfo("Completed test exectuion of check_LeadsSelectView");

}
	@Test(priority=1)
	public void check_TodaysLeadGo() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		WebElement leadEle= driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leadEle,"Lead menu");

		WebElement viewLeadEle= driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		selectByVisibleText(viewLeadEle,"Today's Leads");
		
		WebElement usermenuEle= driver.findElement(By.id("userNav"));
		clickElement(usermenuEle,"user menu");

		userDropDownList("Logout");
		Login_SalesForce();

		WebElement leadEle1= driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leadEle1,"Lead menu");

		WebElement viewButtonEle1= driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input"));
		clickElement(viewButtonEle1,"Go button");
		
		mylog.info("Completed test exectuion of check_TodaysLeadGo");
		extentReportUtility.logTestInfo("Completed test exectuion of check_TodaysLeadGo");

		

	}
    @Test(priority=-1)
	public void select_TodaysLead() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		WebElement leadEle= driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leadEle,"Lead menu");
		//waitForElementToClickable(leadEle,3,"Lead Menu");
		WebElement viewLeadEle= driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		selectByVisibleText(viewLeadEle,"Today's Leads");
		

		WebElement viewButtonEle1= driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[1]/input"));
		clickElement(viewButtonEle1,"Go button");
		
		mylog.info("Completed test exectuion of select_TodaysLead");
		extentReportUtility.logTestInfo("Completed test exectuion of select_TodaysLead");


	}
    
    @Test(priority=2)
	public void new_TodaysLeadSave() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		String leadsLastNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Leads_lastName");
		String leadsCompanyData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Leads_companyName");

		WebElement leadEle= driver.findElement(By.xpath("//*[@id=\"Lead_Tab\"]/a"));
		clickElement(leadEle,"Lead menu");

		WebElement newButtonEle= driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		clickElement(newButtonEle,"New Button");

		WebElement lastNameEle=driver.findElement(By.xpath("//*[@id=\"name_lastlea2\"]"));
		enterText(lastNameEle,leadsLastNameData,"Last name");

		WebElement companyEle=driver.findElement(By.xpath("//*[@id=\"lea3\"]"));
		enterText(companyEle,leadsCompanyData,"Company Name");

		WebElement saveButtonEle= driver.findElement(By.xpath("//*[@id=\"bottomButtonRow\"]/input[1]"));
		clickElement(saveButtonEle,"Save Button");

		mylog.info("Completed test exectuion of new_TodaysLeadSave");
		extentReportUtility.logTestInfo("Completed test exectuion of new_TodaysLeadSave");


	}

}
