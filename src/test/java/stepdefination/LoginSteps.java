package stepdefination; // Corrected spelling to match the runner

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert; // Imported TestNG Assert

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private WebDriver driver;
	private String url = "https://www.saucedemo.com/";

	@Before
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

		// Pass the options into the ChromeDriver
		driver = new ChromeDriver(options);
	}

	@Given("the user is on the login page")
	public void userOnLoginPage() {
		if (!this.driver.getCurrentUrl().equals(this.url)) {
			this.driver.get(this.url);
		}
	}

	@When("the user enters valid username and password")
	public void userEntersValidCredentials() {
		WebElement userName = this.driver.findElement(By.id("user-name"));
		userName.sendKeys("standard_user");

		WebElement passWord = this.driver.findElement(By.id("password"));
		passWord.sendKeys("secret_sauce");

		WebElement loginBtn = this.driver.findElement(By.id("login-button"));
		loginBtn.click();
	}

	@When("the user enters invalid username and password")
	public void userEntersInvalidCredentials() {
		WebElement userName = this.driver.findElement(By.id("user-name"));
		userName.sendKeys("invalid_user");

		WebElement passWord = this.driver.findElement(By.id("password"));
		passWord.sendKeys("wrong_password");

		WebElement loginBtn = this.driver.findElement(By.id("login-button"));
		loginBtn.click();
	}

	@Then("an error message should be displayed")
	public void errorMessageDisplayed() {
		WebElement errorMsg = this.driver.findElement(By.cssSelector(".error-message-container h3"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message was not displayed!");
	}

	@Then("the user should be redirected to the homepage")
	public void userRedirectedToHomepage() {
		String currentUrl = driver.getCurrentUrl();
		// Replaced native Java assert with TestNG Assert
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html",
				"User was not redirected to the homepage!");
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
