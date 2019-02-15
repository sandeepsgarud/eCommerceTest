package eCommerceTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class BrowserStackTest {
	private WebDriver driver;

	@Test
	public void verifyTitleTest() {
		Assert.assertEquals(driver.getTitle(), "SpurQLabs | Software Testing | Test Automation | DevOps | Quality at Speed");
	}
  @BeforeTest
	public void beforeTest() throws MalformedURLException {
		
		final String USERNAME = "sandeepgarud2";
		  final String AUTOMATE_KEY = "Eh4RCREsPsRJssTjtDCA";
		  final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
		  DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "Chrome");
		    caps.setCapability("browser_version", "72.0");
		    caps.setCapability("os", "Windows");
		    caps.setCapability("os_version", "10");
		    caps.setCapability("resolution", "1024x768");
		    driver = new RemoteWebDriver(new URL(URL), caps);
			driver.get("https://spurqlabs.com");

	}


  @AfterTest
	public void afterTest() {
		driver.quit();

	}

}
