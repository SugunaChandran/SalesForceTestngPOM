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
import com.automation.utility.ExcelUtils;
import com.automation.utility.PropertiesUtility;

@Listeners(com.automation.utility.SalesForceListenerUtility.class)
public class OpportunitiesScripts extends SalesForceLogin {

	@Test(alwaysRun = true)
	public void opp_Dropdown() {
		Login_SalesForce();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		WebElement optEle = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));   
		clickElement(optEle,"Dropdown");

		WebElement viewoptEle = driver.findElement(By.xpath("//*[@id=\"fcf\"]")); 
	
		Select viewOppEle=new Select(viewoptEle);	

		List<WebElement> allOptions = viewOppEle.getOptions();

		List dropDownEntries = new ArrayList<>();		
		for (WebElement op : allOptions) {			
			dropDownEntries.add(op.getText());  
		}
	
		String expectedStrVale = PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "opportunitiesoptions");
		List<String> expectedStr = Arrays.asList(expectedStrVale.split(","));

		boolean testcaseStatus = true;
		for(String str : expectedStr) {
			if(!dropDownEntries.contains(str)) {			
				testcaseStatus = false;
				break;
			}		
		}
		Assert.assertEquals(testcaseStatus,true);

		mylog.info("Completed test exectuion of select_UserMenu");
		extentReportUtility.logTestInfo("Completed test exectuion of select_UserMenu");

	}

	
	@Test(dependsOnMethods = {"opp_Dropdown"} )
	public void create_NewOpportunity() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement optEle = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));   
		clickElement(optEle,"Opportunities link");	

		WebElement newEle = driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));   
		clickElement(newEle,"new button");	

		String oppNameData = null;
		try {
			oppNameData = ExcelUtils.readDataFromXL(Constants.SFOPPORTUNITIES_XLDATA, Constants.SHEET_NAMEOPP, 1, 0);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		WebElement oppNameEle=driver.findElement(By.xpath("//*[@id=\"opp3\"]"));
		enterText(oppNameEle,oppNameData,"Opportunity name");
		
		String accNameData = null;
		try {
			accNameData = ExcelUtils.readDataFromXL(Constants.SFOPPORTUNITIES_XLDATA, Constants.SHEET_NAMEOPP, 1, 1);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		WebElement accNameEle=driver.findElement(By.xpath("//*[@id=\"opp4\"]"));
		enterText(accNameEle,accNameData,"Account name");

		WebElement closeDateEle=driver.findElement(By.xpath("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/span/span/a"));
		clickElement(closeDateEle,"Close Date");

		WebElement stageEle=driver.findElement(By.xpath("//*[@id=\"opp11\"]"));
		selectByValue(stageEle,"Prospecting");

		WebElement probabilityEle=driver.findElement(By.xpath("//*[@id=\"opp12\"]"));
		enterText(probabilityEle,"20","Probability");

		WebElement leadSourceEle=driver.findElement(By.xpath("//*[@id=\"opp6\"]"));
		selectByValue(leadSourceEle,"Web");

		WebElement saveEle = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));   
		clickElement(saveEle,"Save button");
		
		mylog.info("Completed test exectuion of create_NewOpportunity");
		extentReportUtility.logTestInfo("Completed test exectuion of create_NewOpportunity");



	}
	
	@Test(priority=1)
	public void test_OppPipelineReport() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		

		WebElement optEle = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));  
		clickElement(optEle,"Dropdown");

		WebElement optPipeLineEle = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a"));  
		waitForVisibility1(optPipeLineEle,30,"Opportunity pipeline");
		clickElement(optPipeLineEle,"Opportunity pipeline");

		mylog.info("Completed test exectuion of test_OppPipelineReport");
		extentReportUtility.logTestInfo("Completed test exectuion of test_OppPipelineReport");

	}
	
@Test(priority=2)
	public void test_OppStuckReport() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement optEle = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));  
		waitForVisibility1(optEle,30,"Opportunities link");
		clickElement(optEle,"Opportunities link");

		WebElement stuckPipeLineEle = driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a"));  
		waitForVisibility1(stuckPipeLineEle,30,"Stuck Opportunity");
		clickElement(stuckPipeLineEle,"Stuck Opportunity");
		mylog.info("Completed test exectuion of test_OppStuckReport");
		extentReportUtility.logTestInfo("Completed test exectuion of test_OppStuckReport");



	}
	@Test(priority=3)
	public void test_QuarterlySummaryReport() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement optEle = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));  
		waitForVisibility1(optEle,30,"Opportunities link");
		clickElement(optEle,"Opportunities link");

		WebElement intervalEle = driver.findElement(By.xpath("//*[@id=\"quarter_q\"]"));
		selectByVisibleText(intervalEle,"Current and Next FQ");


		WebElement includeAllEle = driver.findElement(By.xpath("//*[@id=\"open\"]"));
		selectByVisibleText(includeAllEle,"All Opportunities");

		WebElement runRepEle = driver.findElement(By.xpath("//*[@id=\"lead_summary\"]/table/tbody/tr[3]/td/input"));   
		clickElement(runRepEle,"Run Report");

		WebElement opt1Ele = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));  
		waitForVisibility1(opt1Ele,30,"Opportunities link");
		clickElement(opt1Ele,"Opportunities link");

		WebElement interval1Ele = driver.findElement(By.xpath("//*[@id=\"quarter_q\"]"));
		selectByVisibleText(interval1Ele,"Current and Next FQ");


		WebElement includeOpenEle = driver.findElement(By.xpath("//*[@id=\"open\"]"));
		selectByVisibleText(includeOpenEle,"Open Opportunities");

		WebElement runOpenRepEle = driver.findElement(By.xpath("//*[@id=\"lead_summary\"]/table/tbody/tr[3]/td/input"));   
		clickElement(runOpenRepEle,"Run Report");


		mylog.info("Completed test exectuion of test_QuarterlySummaryReport");
		extentReportUtility.logTestInfo("Completed test exectuion of test_QuarterlySummaryReport");


	}

}
