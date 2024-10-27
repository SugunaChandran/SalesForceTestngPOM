package com.automation.base;

import java.io.File;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver=null;
	static WebDriverWait wait;

	public static void launchBrowser(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;

		default:
			break;
		}
	}


	public static void goToUrl(String url) throws InterruptedException {
		driver.get(url);
		System.out.println(url + "is entered");
		Thread.sleep(5000);

	}

	public static void enterText(WebElement ele,String data,String objectName) {
		if(ele.isDisplayed()) {
			ele.clear();
			ele.sendKeys(data);
			System.out.println("data is entered in the "+objectName);
		}
		else{
			System.out.println(objectName+" textbox is not diplayed");
		}
	}

	public static void clickElement(WebElement ele,String objectName) {
		if(ele.isEnabled()) {
			ele.click();
			System.out.println(objectName+" button is clicked");
		}
		else{
			System.out.println(objectName+" button is not diplayed");
		}
	}

	public static void selectElement(WebElement ele,String objectName) {
		if(!ele.isSelected()) {
			ele.click();
			System.out.println(objectName+" button is selected");
		}
		else{
			System.out.println(objectName+" button is already selected");
		}
	}


	public static void selectByValue(WebElement ele,String value) {
		Select select=new Select(ele);
		select.selectByValue(value);
	}

	public static void selectByVisibleText(WebElement ele,String value) {
		Select select=new Select(ele);
		select.selectByVisibleText(value);
	}

	public static void selectByIndex(WebElement ele,int value) {
		Select select=new Select(ele);
		select.selectByIndex(value);
	}

	public static void mouseOverOnElement(WebElement ele,String objectName) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println("cursormoved to element="+objectName);
	}
	public static Alert switchToAlert() {
		Alert alert=driver.switchTo().alert();
		System.out.println("switched to an alert");
		return alert;
	}

	public static String getalertText(Alert alert,String objectName) {
		System.out.println("extrcting text in the"+objectName+ "alert");
		String text=alert.getText();
		System.out.println("extrcting text in the"+objectName+ "alert");
		return text;
	}
	public static void acceptAlert(Alert alert) {
		alert.accept();
		System.out.println("Alert accepted");
	}
	public static void cancelAlert(Alert alert) {
		alert.dismiss();
		System.out.println("Alert cancelled");
	}

	public static void userDropDownList(String Option) {
		List<WebElement> childs = driver.findElements(By.xpath("//*[@id=\"userNav-menuItems\"]/a"));

		for(WebElement takeChild:childs) {
			if(takeChild.getText().equalsIgnoreCase(Option)) {
				takeChild.click();
				break;
			}

		}
	}

	public static void waitForVisibility1(WebElement ele,long userNameData,String ObjectName) { 
		System.out.println(ObjectName+ "waiting for visibility for maximum of "+userNameData+ "sec"); 

		wait=new WebDriverWait(driver,userNameData); 

		wait.until(ExpectedConditions.visibilityOf(ele)); 

	} 

	public void waitForAlertToPresent(long timeInSec,String ObjectName) { 
		System.out.println(ObjectName+ "waiting for visibility for maximum of "+timeInSec+ "sec"); 

		wait=new WebDriverWait(driver,timeInSec); 

		wait.until(ExpectedConditions.alertIsPresent()); 

	} 

	public static void waitForElementToClickable(WebElement ele,long timeInSec,String ObjectName) { 
		System.out.println(ObjectName+ "waiting for visibility for maximum of "+timeInSec+ "sec"); 

		wait=new WebDriverWait(driver,timeInSec); 

		wait.until(ExpectedConditions.elementToBeClickable(ele)); 

	} 

	public void waitForVisiTxtTobePresentInElement(WebElement ele,long timeInSec,String txt,String ObjectName) { 
		System.out.println(ObjectName+ "waiting for visibility for maximum of "+timeInSec+ "sec"); 

		wait=new WebDriverWait(driver,timeInSec); 

		wait.until(ExpectedConditions.textToBePresentInElement(ele,txt)); 

	} 
public static void takeScreenShot(String path) {

	TakesScreenshot screenCapture=(TakesScreenshot)driver;
	File src= screenCapture.getScreenshotAs(OutputType.FILE);
	File destFile=new File(path);
	try {
		Files.copy(src, destFile);
		System.out.println("screen captured");
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println("problem occured during screenshot taking");
	}
}


	public static void closeDriver() {
		driver.close();
	}

}


