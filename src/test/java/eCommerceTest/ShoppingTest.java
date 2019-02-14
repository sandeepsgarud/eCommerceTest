package eCommerceTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ShoppingTest {
	private WebDriver driver;
	private HomeTest homeTest;

  @Test
  public void verifyShoppingCartOneProductTest() {
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.cssSelector(".btn")).click();
		driver.findElement(By.cssSelector(".panel-default:nth-child(1) .btn")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".btn-primary")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".btn-danger")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(.,'Total: 35.75')])")).isDisplayed());
		driver.findElement(By.cssSelector(".btn-danger")).click();
		driver.findElement(By.linkText("Shop")).click();
		String[] newstock = driver.findElement(By.cssSelector(".panel-default:nth-child(1) .col-md-9")).getText().split(":");
		int newstockCount = Integer.parseInt(newstock[1].trim());
		Assert.assertEquals(newstockCount, 0);
		Assert.assertFalse(homeTest.isElementPresent(By.xpath("//button[@type='button']")));
		driver.findElement(By.linkText("Sign Out")).click();

  }
  
  @Test
  public void verifyShoppingCartMultipleProductTest() {
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.cssSelector(".btn")).click();
		driver.findElement(By.cssSelector(".panel-default:nth-child(2) .btn")).click();
		driver.findElement(By.linkText("Shop")).click();
		driver.findElement(By.cssSelector(".panel-default:nth-child(3) .btn")).click();
		Assert.assertTrue(driver.findElement(By.xpath("(//*[contains(.,'Total: 1534.50')])")).isDisplayed());
		driver.findElement(By.cssSelector(".btn-danger")).click();
		driver.findElement(By.linkText("Sign Out")).click();
  }
  
  @Test
  public void verifyShoppingCartOutofStockTest() throws InterruptedException {
	  	String[] stock = driver.findElement(By.cssSelector(".panel-default:nth-child(3) .col-md-9")).getText().split(":");
		int stockCount = Integer.parseInt(stock[1].trim());	
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.cssSelector(".btn")).click();
		for (int i=0; i<stockCount; i++)
		{
			driver.findElement(By.linkText("Shop")).click();
			driver.findElement(By.cssSelector(".panel-default:nth-child(3) .btn")).click();
			
		}
		driver.findElement(By.cssSelector(".btn-danger")).click();
		driver.findElement(By.linkText("Shop")).click();
		String[] newstock = driver.findElement(By.cssSelector(".panel-default:nth-child(3) .col-md-9")).getText().split(":");
		int newstockCount = Integer.parseInt(newstock[1].trim());
		Assert.assertEquals(newstockCount, 0);
		Assert.assertFalse(homeTest.isElementPresent(By.xpath("//button[@type='button']")));
		driver.findElement(By.linkText("Sign Out")).click();
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
