package com.banking.runner;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.banking.base.BaseTest;
import com.banking.pages.HomePage;

public class HomePageTest
{
	@BeforeMethod
	public void setUp()
	{
		BaseTest.initConfiguration();
	}
	
	@Test
	public void loginPageTest()
	{
		HomePage homePage = new HomePage();
		homePage.ClickCustomerLogin();
	}
	
	@Test
	public void FailTest()
	{
		Assert.assertTrue(false);
	}
	
	@AfterMethod
	public void tearDown()
	{
		if(BaseTest.driver!=null)
		{
			BaseTest.quitBrowser();
		}
	}
}
