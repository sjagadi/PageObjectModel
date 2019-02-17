package com.banking.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.banking.base.BaseTest;
import com.banking.locators.HomePageLocators;

public class HomePage extends BaseTest
{
	public HomePageLocators home;
	public HomePage()
	{
		this.home = new HomePageLocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(factory, this.home);
	}
	
	public void ClickCustomerLogin()
	{
		home.customerLoginBtn.click();
		
	}
}
