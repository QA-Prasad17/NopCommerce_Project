package stepDefinations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import pageObjects.LoginPage;
import pageObjects.addCustomerPage;
import pageObjects.searchCustomerPage;

public class stepsDefination extends BaseClass {

	public WebDriver driver;
	public LoginPage lp;

	@Before
	public void setup() throws IOException {
		

		logger = Logger.getLogger("nopcommerce");
		PropertyConfigurator.configure("log4j.properties");
		
		// reading properties
				configProp = new Properties();
				FileInputStream configPropfile = new FileInputStream("config.properties");
				configProp.load(configPropfile);

		String br = configProp.getProperty("browser");
		
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			ChromeOptions options = new ChromeOptions();
			// Add argument to run Chrome in incognito mode
			options.addArguments("--incognito");

			// Create a new instance of ChromeDriver with options

			driver = new ChromeDriver(options);
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		} else if (br.equals("edge")) {
			System.setProperty("webdriver.edge .driver",configProp.getProperty("edgepath"));
			driver = new EdgeDriver();
	}

	logger.info("launching Browser");driver.manage().window().maximize();

}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("Opening url");
		driver.get(url);

	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("Providing login details");
		lp.setUserName(email);
		lp.setPassword(password);

	}

	@When("Click on Login")
	public void click_on_login() {
		logger.info("started login process");
		lp.clickLogin();

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {

		if (driver.getPageSource().contains("Login was unsuccessful")) {

			driver.close();
			logger.info("login passed");
			Assert.assertTrue(false);
		} else {
			logger.info("login failed");
			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(3000);

	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		logger.info("Click on logout link");
		lp.clickLogout();

	}

	@Then("close browser")
	public void close_browser() {
		logger.info("closing browser");
		driver.close();

	}

	// customer feature step defination

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		addcust = new addCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addcust.getPageTitle());

	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(2000);
		addcust.clickOnCustomersMenu();

	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		addcust.clickOnCustomersMenuItem();

	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addcust.clickOnAddnew();
		Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addcust.getPageTitle());

	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		String email = randomestring() + "@gmail.com";
		addcust.setEmail(email);
		addcust.setPassword("test1234");
		addcust.setFirstName("QA_Prasad");
		addcust.setLastName("Test");
		addcust.setGender("Male");
		addcust.setDob("12/17/1992");
		addcust.setCompanyName("Textcorp");
		addcust.setCustomerRoles("Guest");
		addcust.setManagerOfVendor("Vendor 2");
		addcust.setAdminContent("Testing from QA");

	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		addcust.clickOnSave();
		Thread.sleep(2000);

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String message) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));

	}

	// Steps for searching customer using email id

	@When("Enter customer Email")
	public void enter_customer_Email() throws InterruptedException {
		searchcust = new searchCustomerPage(driver);

		searchcust.setEmail("victoria_victoria@nopCommerce.com");
		Thread.sleep(3000);
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchcust.clickSearch();
		Thread.sleep(3000);

	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		boolean status = searchcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);

		//// Steps for searching customer using First Name and Last Name
	}

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchcust = new searchCustomerPage(driver);
		searchcust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchcust.setLastName("Terces");

	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status = searchcust.searchCustomerByName("Victoria Terces");

		Assert.assertEquals(true, status);

	}

}
