package com.automation.test;

import static org.testng.AssertJUnit.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.automation.base.SalesForceLogin;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;


@Listeners(com.automation.utility.SalesForceListenerUtility.class)
public class Contacts extends SalesForceLogin {

	@Test(priority=-1)
	public void create_NewContact() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		String contactLastNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Contacts_lastName");
		String contactCompanyData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Contacts_accName");


		WebElement contactButtonEle= driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactButtonEle,"Contact Tab");

		WebElement newButtonEle= driver.findElement(By.xpath("//input[@name='new']"));
		clickElement(newButtonEle,"New Button");

		WebElement lastNameEle=driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterText(lastNameEle,contactLastNameData,"Last name");

		WebElement accountnameEle=driver.findElement(By.xpath("//input[@id='con4']"));
		enterText(accountnameEle,contactCompanyData,"Accountname");

		WebElement saveButtonEle= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(saveButtonEle,"Save Button");
		mylog.info("Completed test exectuion of create_NewContact");
		extentReportUtility.logTestInfo("Completed test exectuion of create_NewContact");


	}

	@Test(priority=4)
	public void create_NewViewContact() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		
		String contactViewNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Contacts_viewName");
		String contactViewUniqueData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Contacts_viewUniqueName");


		WebElement contactButtonEle= driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactButtonEle,"Contact Tab");

		WebElement createnewViewEle= driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		clickElement(createnewViewEle,"Create New View Link");


		WebElement viewNameEle=driver.findElement(By.xpath("//input[@id='fname']"));
		enterText(viewNameEle,contactViewNameData,"View name");


		WebElement viewUniqueNameEle=driver.findElement(By.xpath("//input[@id='devname']"));
		enterText(viewUniqueNameEle,contactViewUniqueData,"View Unique name");

		WebElement savEle= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savEle,"Save Button");
		mylog.info("Completed test exectuion of create_NewViewContact");
		extentReportUtility.logTestInfo("Completed test exectuion of create_NewViewContact");


	}

	@Test(priority=5)
	public void check_recentlyCreatedContact() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement contactButtonEle= driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactButtonEle,"Contact Tab");

		WebElement recentlyCreatedAccEle = driver.findElement(By.xpath("//*[@id=\"hotlist_mode\"]"));
		clickElement(recentlyCreatedAccEle,"recent created");
		selectByValue(recentlyCreatedAccEle ,"2");
		
		mylog.info("Completed test exectuion of check_recentlyCreatedContact");
		extentReportUtility.logTestInfo("Completed test exectuion of check_recentlyCreatedContact");

	}
	
	@Test(priority=3)
	public void check_MyContact() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement contactButtonEle= driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactButtonEle,"Contact Tab");	

		WebElement viewDropDownEle= driver.findElement(By.xpath("//*[@id=\"fcf\"]"));
		clickElement(viewDropDownEle,"My Contacts");	

		selectByVisibleText(viewDropDownEle,"My Contacts");

		mylog.info("Completed test exectuion of check_MyContact");
		extentReportUtility.logTestInfo("Completed test exectuion of check_MyContact");

	}
	
	@Test
	public void ViewContact() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement contactButtonEle= driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactButtonEle,"Contact Tab");	

		// Locate the table using its ID
		WebElement table = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody"));

		//All row
		List<WebElement> rows = table.findElements(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr"));

		//loop through each row

		int i = 0;
		for (WebElement row : rows) {
			i += 1;
			// Get all columns in the current row
			List<WebElement> cols = row.findElements(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[" + i + "]/th"));

			// If the row has more than one column (to avoid header row)
			if (cols.size() > 0) {
				// Example: Get the first column value (ID column) and check if it's the desired row
				String id = cols.get(0).getText();
				
				Assert.assertNotNull(id, "ID column value should not be null.");

				// If it's the row with ID = 2, click the button in the third column
				if (id.equals("Stumuller, Pat")) {
					WebElement nameLinkEle = driver.findElement(By.xpath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[" + i + "]/th/a"));
					
					clickElement(nameLinkEle,"Contact Name Link");
					break;  // Exit loop after clicking the button

				}
			}
		}
		mylog.info("Completed test exectuion of ViewContact");
		extentReportUtility.logTestInfo("Completed test exectuion of ViewContact");


	}

	@Test(priority=1)

	public void inValid_createNewViewContact() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
		WebElement contactButtonEle= driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactButtonEle,"Contact Tab");

		WebElement createnewViewEle= driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		clickElement(createnewViewEle,"Create New View Link");

		WebElement viewUniqueNameEle=driver.findElement(By.xpath("//input[@id='devname']"));
		enterText(viewUniqueNameEle,"EFGH","View Unique name");

		WebElement savEle= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(savEle,"Save Button");
		String expectedStr="Error: You must enter a value";
		
		WebElement txtEle=driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]"));
		Assert.assertEquals(expectedStr,txtEle.getText());
			
		mylog.info("Test is passed");		
		mylog.info("Completed test exectuion of inValid_createNewViewContact");
		extentReportUtility.logTestInfo("Completed test exectuion of inValid_createNewViewContact");


	}
	@Test(priority=3)
	public void cancel_createNewViewContact() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		String contactViewNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Contacts_viewName1");
		String contactViewUniqueData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Contacts_viewUniqueName1");


		WebElement contactButtonEle= driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactButtonEle,"Contact Tab");

		WebElement createnewViewEle= driver.findElement(By.xpath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]"));
		clickElement(createnewViewEle,"Create New View Link");

		WebElement viewNameEle=driver.findElement(By.xpath("//input[@id='fname']"));
		enterText(viewNameEle,contactViewNameData,"View name");

		WebElement viewUniqueNameEle=driver.findElement(By.xpath("//input[@id='devname']"));
		enterText(viewUniqueNameEle,contactViewUniqueData,"View Unique name");

		WebElement cancelButtonEle= driver.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[2]"));
		clickElement(cancelButtonEle,"Cancel Button");
		
		mylog.info("Completed test exectuion of cancel_createNewViewContact");
		extentReportUtility.logTestInfo("Completed test exectuion of cancel_createNewViewContact");

    }
	
	@Test(dependsOnMethods ="ViewContact")
	public void save_NewButtonContact() {
		Login_SalesForce();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		String contLastNameData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Contacts_lastName1");
		String contCompanyData=PropertiesUtility.readDataFromPropertiesFile(Constants.APPLICATION_PROPERTIES, "Contacts_accName1");


		WebElement contactButtonEle= driver.findElement(By.xpath("//*[@id=\"Contact_Tab\"]/a"));
		clickElement(contactButtonEle,"Contact Tab");

		WebElement newButtonEle= driver.findElement(By.xpath("//input[@name='new']"));
		clickElement(newButtonEle,"New Button");

		WebElement lastNameEle=driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterText(lastNameEle,contLastNameData,"Last name");

		WebElement accountnameEle=driver.findElement(By.xpath("//input[@id='con4']"));
		enterText(accountnameEle,contCompanyData,"Accountname");

		WebElement saveButtonEle= driver.findElement(By.xpath("//input[@name='save']"));
		clickElement(saveButtonEle,"Save Button");


		mylog.info("Completed test exectuion of save_NewButtonContact");
		extentReportUtility.logTestInfo("Completed test exectuion of save_NewButtonContact");




	}

}
