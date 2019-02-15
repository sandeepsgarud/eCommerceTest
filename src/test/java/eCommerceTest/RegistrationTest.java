package eCommerceTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class RegistrationTest {
	private WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/DriverExe/chromedriver");
		driver = new ChromeDriver();
	}

	// Register new user. This test will pass only once

	@Test
	public void verifyNewUserRegistrationTest() {
		driver.get("http://localhost:8070/home");
		driver.findElement(By.linkText("Registration")).click();
		driver.findElement(By.id("name")).sendKeys("UserFirst");
		driver.findElement(By.id("lastName")).sendKeys("UserLast");
		driver.findElement(By.id("email")).sendKeys("useremail@testing.com");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("username")).sendKeys("testuser");
		driver.findElement(By.cssSelector(".btn")).click();
		String Alertmessage = driver.findElement(By.cssSelector(".alert")).getText();
		Assert.assertEquals(Alertmessage, "User has been registered successfully");

	}

	// Register new user with system time to pass it next time you run
	@Test
	public void verifyNewUserRegistrationWithTimeTest() {
		String uid = Long.toString(System.currentTimeMillis());
		driver.get("http://localhost:8070/home");
		driver.findElement(By.linkText("Registration")).click();
		driver.findElement(By.id("name")).sendKeys("UserFirst");
		driver.findElement(By.id("lastName")).sendKeys("UserLast");
		driver.findElement(By.id("email")).sendKeys("useremail" + uid + "@testing.com");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("username")).sendKeys("testuser" + uid);
		driver.findElement(By.cssSelector(".btn")).click();
		String Alertmessage = driver.findElement(By.cssSelector(".alert")).getText();
		Assert.assertEquals(Alertmessage, "User has been registered successfully");

	}

	// Attempt to register same user that already exists

	@Test
	public void verifyExistingUsernameRegistrationTest() {
		driver.get("http://localhost:8070/home");
		driver.findElement(By.linkText("Registration")).click();
		driver.findElement(By.id("name")).sendKeys("UserFirst");
		driver.findElement(By.id("lastName")).sendKeys("UserLast");
		driver.findElement(By.id("email")).sendKeys("useremail@testing.com");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("username")).sendKeys("testuser");
		driver.findElement(By.cssSelector(".btn")).click();
		String Alertmessage = driver.findElement(By.cssSelector(".alert")).getText();
		Assert.assertEquals(Alertmessage, "There is already a user registered with the username provided");

	}

	// Attempt to submit registration page with no values entered
	// Commented code does not work as the error messages change their sequence.
	
	@Test
	public void verifyRegistrationWithBlankTest() {
		driver.get("http://localhost:8070/home");
		driver.findElement(By.linkText("Registration")).click();
		driver.findElement(By.cssSelector(".btn")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(1) > .alert")).getText(),
				"*Please provide your name");
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(2) > .alert")).getText(),
				"*Please provide your last name");
		Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(3) > .alert")).getText(),
				"*Please provide an email");
		// Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(4)
		// > .alert")).getText(), "*Please provide your password\n" +
		// "*Your password must have at least 5 characters");
		// Assert.assertEquals(driver.findElement(By.cssSelector(".form-group:nth-child(5)
		// > .alert")).getText(), "*Your username must have at least 5 characters\n" +
		// "*Please provide your name");

		Assert.assertTrue(driver.findElement(By.cssSelector(".form-group:nth-child(4) > .alert")).getText()
				.contains("Please provide your password"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".form-group:nth-child(5) > .alert")).getText()
				.contains("Please provide your name"));

	}

	// attempt to register only with first name

	@Test
	public void verifyRegistrationOnlyNameTest() {

		driver.findElement(By.id("name")).sendKeys("UserFirst");
		driver.findElement(By.cssSelector(".btn")).click();
		String message = driver.findElement(By.cssSelector(".form-group:nth-child(2) > .alert")).getText();
		Assert.assertEquals(message, "*Please provide your last name");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
