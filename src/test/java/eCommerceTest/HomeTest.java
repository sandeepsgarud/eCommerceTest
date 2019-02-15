package eCommerceTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomeTest {
	private WebDriver driver;


	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/DriverExe/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8070/home");

	}
	
	// Title verification test
	@Test
	public void verifyTitleTest() {
		Assert.assertEquals(driver.getTitle(), "Shop");
	}
	// Verify all the links in header
	@Test
	public void verifyLinksTest() {
		Assert.assertTrue(driver.findElement(By.partialLinkText("Shop")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Login")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Registration")).isDisplayed());
	}
	//Verify all the products on first page. Verify buy button does not show up as not logged in. verify navigation links
	@Test
	public void verifyProductListTest() {
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Soap')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Tooth Brush')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Shirt')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Office Bag')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Bottle')]")).isDisplayed());
		Assert.assertFalse(isElementPresent(By.xpath("//button[@type='button']")));
		Assert.assertTrue(driver.findElement(By.className("pagination")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("next")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("last »")).isDisplayed());
	}
	
	//Verify all the products on second page. Verify buy button does not show up as not logged in. verify navigation links

	@Test
	public void verifyProductListOnNextPageTest() {
		  driver.findElement(By.linkText("next")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Wrist Watch')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Mobile Phone')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Shampoo')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Leather Wallets')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(.,'Camera')]")).isDisplayed());
		Assert.assertFalse(isElementPresent(By.xpath("//button[@type='button']")));
		Assert.assertTrue(driver.findElement(By.className("pagination")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("« first")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("previous")).isDisplayed());

	}
	
	public boolean isElementPresent(By locatorKey) {
	    try {
	        driver.findElement(locatorKey);
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}
	
	
}
