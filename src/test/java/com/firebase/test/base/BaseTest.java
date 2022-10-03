package com.firebase.test.base;
	
	import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.lang.reflect.Method;
import java.time.Duration;
	import java.util.List;
import java.util.Properties;
import java.util.Set;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;	
	
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import com.firebase.test.Utility.CommonUtilities;
import com.firebase.test.Utility.Constants;
import com.firebase.test.Utility.GenerateReports;


import io.github.bonigarcia.wdm.WebDriverManager;

	public class BaseTest {
		public static WebDriver driver = null;
		public static WebDriverWait wait = null;
		public static Logger logger =LogManager.getLogger(BaseTest.class);
		public  static GenerateReports report=null;

		public static void setDriver(String browser) {

			switch (browser) {
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			default:
				break;

			}

		}
		
		@BeforeTest
		public static void setupBeforeTest() {
			report=GenerateReports.getInstance();
			report.startExtentReport();
		}
	//	@Parameters({ "browsername" })
		
		@BeforeMethod
		public static void SetUp(String browsername, Method m) {

			System.out.println("Before method Execution");
			report.startSingleTestReport(m.getName());
			setDriver("chrome");
			CommonUtilities CU=new CommonUtilities();
			Properties applicationPropertiesFile=CU.loadFile("applicationproperties");
			String url=CU.getApplicationProperty("url", applicationPropertiesFile);
			
			gotoUrl(url);
			waitUntilPageLoads();
		}
		
		
		
		@AfterMethod
		public static void tearDown()
		{
			System.out.println("After method execution");
			logger.info("after method execution is started");
			report.logTestInfo("after method execution is started");
			closeBrowser();
		}
		
		@AfterTest
		public static void tearDownAfterTest() {
			report.endReport();
		}
		
		public static void LoginToSalesForce()
		{
			CommonUtilities CU=new CommonUtilities();
			Properties applicationPropertiesFile=CU.loadFile("applicationproperties");
			//	String url=CU.getApplicationProperty("url", applicationPropertiesFile);
				String usrname=CU.getApplicationProperty("username", applicationPropertiesFile);
			//	String UNM=CU.getApplicationProperty("username", applicationPropertiesFile);
				String pword=CU.getApplicationProperty("password", applicationPropertiesFile);
			//	setDriver("chrome");
			//	gotoUrl(url);
				driver.manage().window().maximize();
				WebElement username=driver.findElement(By.id("username"));		
				enterText(username, usrname, "username");
				WebElement password=driver.findElement(By.id("password"));
				enterText(password, pword, "password");
				WebElement Loginbutton=driver.findElement(By.id("Login"));
				clickElement(Loginbutton, "Login button");
				waitUntilPageLoads();
				
				
		}

		public static String getPageTitle() {
			return driver.getTitle();
		}

		public static void refreshPage() {
			driver.navigate().refresh();
			report.logTestInfo("page got refreshed");
		}
		public static void waitUntilPageLoads() {
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		}

		public static void enterText(WebElement element, String text, String objName) {
			if (element.isDisplayed()) {
				clearElement(element, objName);
				element.sendKeys(text);
				report.logTestInfo("text entered in " + objName + "field");
				
			} else {
				report.logTestFailed("fail: " + objName + " element not displayed");
			}
		}

		public static void clickElement(WebElement element, String objName) {
			if (element.isDisplayed()) {
				element.click();
				report.logTestInfo("pass:" + objName + " element clicked");
				
			} else {
				report.logTestFailed("fail: " + objName + " element not displayed");

			}
		}

		public static void clearElement(WebElement element, String objName) {
			if (element.isDisplayed()) {
				element.clear();
				report.logTestInfo("pass:" + objName + "  element cleared");
			

			} else {
				report.logTestFailed("fail: " + objName + " element not displayed");

			}
		}

		public static WebDriver getDriverInstance() {
			return driver;
		}

		public static void gotoUrl(String url) {
			driver.get(url);
		}

		public static void moveToElement(WebElement element, String objectName) {
			waitUntilVisible(element, objectName);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			System.out.println("moved to " + objectName);

		}

		public static void closeBrowser() {
			driver.close();
		}

		public static void closeAllbrowser() {
			driver.quit();
		}

		public static void waitUntilVisibilityOf(By locator, String objName) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		public static String readText(WebElement element, String objectName) {
			waitUntilVisible(element, objectName);
			String text = element.getText();
			return text;
		}

		public static void waitUntilVisible(WebElement element, String objName) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(element));
		}

		public static void waitUntilAlertIsPresent() {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.alertIsPresent());
		}

		public static void waitUntilElementToBeClickable(By locator, String objName) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		public static Alert switchToAlert() {
			// TODO Auto-generated method stub
			waitUntilAlertIsPresent();
			return driver.switchTo().alert();
		}

		public static void AcceptAlert(Alert alert) {

			System.out.println("Alert accepted");
			alert.accept();

		}

		public static String getAlertText(Alert alert) {

			return alert.getText();

		}

		public static void dismisAlert() {
			waitUntilAlertIsPresent();
			Alert alert = switchToAlert();
			alert.dismiss();
			System.out.println("Alert dismissed");

		}

		public static void selectByTextData(WebElement element, String text, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByVisibleText(text);
			report.logTestInfo(objName + " seelcted " + text);
			

		}

		public static void selectByIndexData(WebElement element, int index, String objName) {
			Select selectCity = new Select(element);
			selectCity.selectByIndex(index);
		}

		public static void selectByValueData(WebElement element, String text) {
			Select selectCity = new Select(element);
			selectCity.selectByValue(text);
		}

		public static void switchToWindowOpned(String mainWindowHandle) {
			Set<String> allWindowHandles = driver.getWindowHandles();
			for (String handle : allWindowHandles) {
				if (!mainWindowHandle.equalsIgnoreCase(handle))
					driver.switchTo().window(handle);
			}
			System.out.println("switched to new window");
		}
		

	}


