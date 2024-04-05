package stepDefinations;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;

import pageObjects.LoginPage;
import pageObjects.addCustomerPage;

public class stepsDefination extends BaseClass {

	public WebDriver driver;
	public LoginPage lp;

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {

		driver.get(url);

	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		lp.setUserName(email);
		lp.setPassword(password);

	}

	@When("Click on Login")
	public void click_on_login() {
		lp.clickLogin();

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {

		if (driver.getPageSource().contains("Login was unsuccessful")) {

			driver.close();
			Assert.assertTrue(false);
		} else {

			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(3000);

	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {

		lp.clickLogout();

	}

	@Then("close browser")
	public void close_browser() {
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
	
	//Steps for searching customer using email id
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
	   
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
	    
	}
	@When("Click on search button")
	public void click_on_search_button() {
	    
	}
	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
	    
	}


}
