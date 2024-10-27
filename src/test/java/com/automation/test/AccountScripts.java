package com.automation.test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.base.SalesForceLogin;
import com.automation.utility.Constants;
import com.automation.utility.ExcelUtils;
import com.automation.utility.PropertiesUtility;


@Listeners(com.automation.utility.SalesForceListenerUtility.class)
      public class AccountScripts extends SalesForceLogin {
	  @Test
	  public void create_Account() throws InterruptedException {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	

		String userNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "username");

		WebElement accEle= driver.findElement(By.id("Account_Tab"));
		clickElement(accEle,"Account menu");

		WebElement newEle= driver.findElement(By.xpath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input"));
		clickElement(newEle,"button");

		WebElement accnameEle=driver.findElement(By.name("acc2"));
		enterText(accnameEle,"Kala","Account name");

		WebElement typeEle = driver.findElement(By.id("acc6"));
		selectByValue(typeEle , "Technology Partner");

		WebElement custPriorityEle = driver.findElement(By.id("00Nak000005JJDm"));
		selectByValue(custPriorityEle , "High");

		WebElement saveEle= driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
		clickElement(saveEle,"save");
		mylog.info("Completed test exectuion of create_Account");
		extentReportUtility.logTestInfo("Completed test exectuion of create_Account");


	}
    
	  @DataProvider(name="AccountDetails")
		
		public String[][] MyDataprovider(){
		  return (String[][])ExcelUtils.readAllDataFromXLFromXLToArray(Constants.SFACCOUNT_XLDATA, Constants.SHEET_NAMEACC);
	  }
	  
	@Test(dataProvider="AccountDetails")
	public void create_NewView(String viewname,String viewuniquename) throws InterruptedException {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	

		WebElement accEle= driver.findElement(By.id("Account_Tab"));
		clickElement(accEle,"Account menu");

		WebElement createnewViewEle= driver.findElement(By.xpath("//a[text()='Create New View']"));
		clickElement(createnewViewEle,"create new view link");
				
		WebElement viewNameEle=driver.findElement(By.cssSelector("[name='fname']"));
		enterText(viewNameEle,viewname,"View name");

		WebElement viewuniqueNameEle=driver.findElement(By.cssSelector("[name='devname']"));
		enterText(viewuniqueNameEle,viewuniquename,"View unique name");

		WebElement savEle= driver.findElement(By.name("save"));
		clickElement(savEle,"save new view link");

		mylog.info("Completed test exectuion of create_NewView");
		extentReportUtility.logTestInfo("Completed test exectuion of create_NewView");


	}
	@Test
	public void edit_View() throws InterruptedException {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		String newViewData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "newview");

		WebElement accEle= driver.findElement(By.id("Account_Tab"));
		clickElement(accEle,"Account menu");

		WebElement viewEle = driver.findElement(By.cssSelector("select[title='View:']"));
		selectByVisibleText(viewEle,"lathaRai");


		WebElement editEle= driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[1]"));
		clickElement(editEle,"Edit view");


		WebElement newVieweEle=driver.findElement(By.xpath("//*[@id=\"fname\"]"));
		enterText(newVieweEle,newViewData,"New View name");

		WebElement fieldEle = driver.findElement(By.id("fcol1"));
		selectByValue(fieldEle,"ACCOUNT.NAME");

		WebElement operatorEle = driver.findElement(By.id("fop1"));
		selectByIndex(operatorEle,2);

		WebElement valEle=driver.findElement(By.id("fval1"));
		enterText(valEle,"2000","Value");

		WebElement savEle= driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]"));
		clickElement(savEle,"Edited value");

		mylog.info("Completed test exectuion of edit_View");
		extentReportUtility.logTestInfo("Completed test exectuion of edit_View");

	}
	
	@Test
	public void merge_Account() throws InterruptedException {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	

		WebElement accEle= driver.findElement(By.id("Account_Tab"));
		clickElement(accEle,"Account menu");



		WebElement mergAccEle= driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a\r\n"));
		clickElement(mergAccEle,"Account menu");

		WebElement enterAccEle=driver.findElement(By.xpath("//input[@id='srch']\r\n"));
		enterText(enterAccEle,"Burlington","Find acc");

		WebElement findAccEle= driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]"));
		clickElement(findAccEle,"Find Account button");

    	List<WebElement> mergeAccCheck=driver.findElements(By.xpath("//*[@name=\"cid\" ]"));

		for(WebElement check:mergeAccCheck) {
			selectElement(check, "Accounts select check box");
		}


		WebElement nextEle= driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[1]"));
		clickElement(nextEle,"Next button");

		WebElement mergeEle= driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[1]/div/input[2]"));
		clickElement(mergeEle,"Merge button");

		Alert alert = switchToAlert();
		String alertText = getalertText(alert,null);
		System.out.println(alertText);
		acceptAlert(alert);

		mylog.info("Completed test exectuion of merge_Account");
		extentReportUtility.logTestInfo("Completed test exectuion of merge_Account");

	}
	
	@Test
	public void create_AccountReport() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	

		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement accEle= driver.findElement(By.id("Account_Tab"));
		clickElement(accEle,"Account menu");

		WebElement accDaysEle= driver.findElement(By.xpath("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a"));
		clickElement(accDaysEle,"Account with >30 days");

		WebElement fromEle= driver.findElement(By.xpath("//*[@id=\"ext-gen152\"]"));
		clickElement(fromEle,"From");


		WebElement todayFromEle= driver.findElement(By.xpath("//*[@id=\"ext-gen276\"]"));
		clickElement(todayFromEle,"From Date");


		WebElement toEle= driver.findElement(By.xpath("//*[@id=\"ext-gen154\"]"));
		clickElement(toEle,"To");


		WebElement toTodayEle= driver.findElement(By.xpath("//*[@id=\"ext-gen292\"]"));
		clickElement(toTodayEle,"To Date");


		Set<String> allWindows=driver.getWindowHandles();
		System.out.println("before total number of windows="+allWindows.size());
		for(String window:allWindows) {
			System.out.println(window);
		}
		System.out.println("..........."+driver.getWindowHandle());
		String parentWindow=driver.getWindowHandle();

		WebElement savButtonEle= driver.findElement(By.xpath("//*[@id=\"ext-gen49\"]"));
		clickElement(savButtonEle,"Save Button");

		Set<String> allwindows2=driver.getWindowHandles();
		System.out.println("after total number of windows="+allwindows2.size());
		for(String handle:allwindows2) {

			if(!handle.equals(parentWindow))
				driver.switchTo().window(handle);
			break;
		}

		System.out.println("........."+driver.getWindowHandle());
		WebElement reportNameEle=driver.findElement(By.xpath("//*[@id=\"saveReportDlg_reportNameField\"]"));
		enterText(reportNameEle,"Current","Report Name");

		WebElement reportUniqueNameEle=driver.findElement(By.xpath("//*[@id=\"saveReportDlg_DeveloperName\"]"));
		enterText(reportUniqueNameEle,"MonthReport","Report Unique Name");

		WebElement savRepButtonEle= driver.findElement(By.xpath("//*[@id=\"ext-gen295\"]"));
		clickElement(savRepButtonEle,"Save Run Report");

		driver.switchTo().window(parentWindow);
		System.out.println(driver.getWindowHandle());;


		mylog.info("Completed test exectuion of create_AccountReport");
		extentReportUtility.logTestInfo("Completed test exectuion of create_AccountReport");




	}

}
