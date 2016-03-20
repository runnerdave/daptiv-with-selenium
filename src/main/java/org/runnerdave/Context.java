package org.runnerdave;

import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Application to demonstrate use of Selenium with a target website
 * 
 * @author David Jiménez runnerdave-at-gmail.com
 *
 */
public class Context {

	private static ResourceBundle myResources = ResourceBundle.getBundle("ResourceBundle");
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
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

		driver.get(myResources.getString("website.url"));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(myResources.getString("user.email"));
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(myResources.getString("user.password"));
		driver.findElement(By.xpath("//*[@id='login-btn']")).click();
		WebElement notification;
		try {
			notification = driver.findElement(By.xpath("//*[@id='closeSystemNotification']"));
			Thread.sleep(1000);
			notification.click();
		} catch (Exception e) {
			System.out.println("no notification, so continue");
		}
		Thread.sleep(5000);
		WebElement timesheetsLink = driver.findElement(By.linkText("Timesheets"));

		timesheetsLink.click();
		Thread.sleep(1000);
		
		//clear method removes previous values in case a run of the program was already made
		driver.findElement(By.xpath("//*[@id='workEntry4']")).clear();
		driver.findElement(By.xpath("//*[@id='workEntry4']")).sendKeys("1");
//		driver.findElement(By.xpath("//*[@id='workEntry16']")).clear();
//		driver.findElement(By.xpath("//*[@id='workEntry16']")).sendKeys("7.2");		
		driver.findElement(By.xpath("//*[@id='workEntry42']")).clear();
		driver.findElement(By.xpath("//*[@id='workEntry42']")).sendKeys("7.2");
		driver.findElement(By.xpath("//*[@id='workEntry43']")).clear();
		driver.findElement(By.xpath("//*[@id='workEntry43']")).sendKeys("7.2");
		driver.findElement(By.xpath("//*[@id='workEntry44']")).clear();
		driver.findElement(By.xpath("//*[@id='workEntry44']")).sendKeys("7.2");
		driver.findElement(By.xpath("//*[@id='workEntry45']")).clear();
		driver.findElement(By.xpath("//*[@id='workEntry45']")).sendKeys("7.2");
		driver.findElement(By.xpath("//*[@id='workEntry46']")).clear();
		driver.findElement(By.xpath("//*[@id='workEntry46']")).sendKeys("6.2");
		
		driver.findElement(By.xpath("//*[@id='SubmitButton']")).click();
		Thread.sleep(5000);

		driver.switchTo().frame(driver
				.findElement(By.xpath("//*[@id='RadWindowContentFrameSingleton_SelectSubmitCommentWindow']")));

		driver.findElement(By.xpath("//*[@id='txtComment']")).sendKeys(myResources.getString("timesheet.submission.box.comment")); 
		
		//uncomment to perform a submission
		//driver.findElement(By.xpath("//*[@id='SubmitButton']")).click();

		Thread.sleep(5000);
		//driver.close();

	}
	


}
