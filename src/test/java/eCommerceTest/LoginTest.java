package eCommerceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
	private WebDriver driver;

	// Verify valid user can log in
	@Test
	public void verifyValidLoginTest() {
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.cssSelector(".btn")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Sign Out")).isDisplayed());
		driver.findElement(By.linkText("Sign Out")).click();

	}

	// Verify Invalid user can not log in

	@Test(dataProvider = "InvalidDataProvider")
	public void verifyInvalidLoginBothTest(String username, String password) {
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector(".btn")).click();
		String Alertmessage = driver.findElement(By.cssSelector(".alert")).getText();
		Assert.assertEquals(Alertmessage, "Invalid username and password.");

	}

	@DataProvider
	public Object[][] InvalidDataProvider() {
		return new Object[][] { 
				new Object[] { "InvalidUser", "password" },
				new Object[] { "testuser", "InvalidPassword" }, 
				new Object[] { "InvalidUser", "InvalidPassword" }, 
				};
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/DriverExe/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8070/home");

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
