package stepDefinations;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;
import pageObjects.addCustomerPage;
import pageObjects.searchCustomerPage;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	public addCustomerPage addcust;
	public searchCustomerPage searchcust;
	public static Logger logger;
	public Properties configProp;

	
	//created for generating random string for unique email
	public String randomestring()
	{
	   String generatedString = RandomStringUtils.randomAlphabetic(5);
	   return (generatedString);
	}
}
