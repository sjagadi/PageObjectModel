package com.banking.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest 
{
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static String browser;
	public static Logger logger = LogManager.getLogger(com.banking.base.BaseTest.class);

	public static void initConfiguration() 
	{
		if (Constants.browser.equals("firefox")) 
		{
			driver = new FirefoxDriver();
		} 
		else if (Constants.browser.equals("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			driver = new ChromeDriver(options);
			logger.debug("Launching browser");
		} 
		else if (Constants.browser.equals("ie")) 
		{

			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.get(Constants.testsiteurl);
		driver.manage().window().maximize();
	}
	
	public static void click(WebElement element) 
	{

		element.click();
		//log.debug("Clicking on an Element : "+element);
		//test.log(LogStatus.INFO, "Clicking on : " + element);
	}
	
	public static void quitBrowser()
	{
		driver.quit();
	}
}
