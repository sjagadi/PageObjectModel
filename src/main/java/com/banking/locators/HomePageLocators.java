package com.banking.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageLocators 
{
	@FindBy(xpath=("//button[text()='Customer Login']"))
	public WebElement customerLoginBtn;
	
	@FindBy(xpath=("//button[text()='Bank Manager Login']"))
	public WebElement bankManagerBtn;
	
	

}
