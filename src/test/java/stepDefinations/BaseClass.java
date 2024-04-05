package stepDefinations;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;
import pageObjects.addCustomerPage;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public addCustomerPage addcust;

	
	//created for generating random string for unique email
	public String randomestring()
	{
	   String generatedString = RandomStringUtils.randomAlphabetic(5);
	   return (generatedString);
	}
}
