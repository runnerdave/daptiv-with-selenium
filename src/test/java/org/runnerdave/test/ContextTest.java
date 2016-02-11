package org.runnerdave.test;

import java.util.ResourceBundle;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import junit.framework.TestCase;

public class ContextTest extends TestCase {

	private ResourceBundle myResources = ResourceBundle.getBundle("ResourceBundle");
	private WebDriver driver;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ContextTest(String testName) {
		super(testName);
		//set browser
		String browser = myResources.getString("driver.browser");
		switch (browser) {
		case "FF":
			driver = new FirefoxDriver();
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		}
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testLogin() {
		
		
		driver.get(myResources.getString("website.url"));
		driver.manage().window().maximize();
		
		driver.get(myResources.getString("website.url"));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(myResources.getString("user.email"));
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(myResources.getString("user.password"));
		driver.findElement(By.xpath("//*[@id='login-btn']")).click();
		
		String dashboardTitleText = driver.findElement(By.xpath("//*[@id='AppName']")).getText();		
		
		assertTrue(dashboardTitleText.equalsIgnoreCase("Dashboard"));
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(5000);
		driver.close();
	}
}
