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
 * @author David Jim�nez runnerdave-at-gmail.com
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
		
		driver.findElement(By.xpath("//*[@id='workEntry0']")).sendKeys("7.2");
		driver.findElement(By.xpath("//*[@id='workEntry1']")).sendKeys("7.2");
		driver.findElement(By.xpath("//*[@id='workEntry2']")).sendKeys("5.2");
		driver.findElement(By.xpath("//*[@id='workEntry37']")).sendKeys("2.0");
		driver.findElement(By.xpath("//*[@id='workEntry38']")).sendKeys("7.2");
		driver.findElement(By.xpath("//*[@id='workEntry39']")).sendKeys("7.2");
		
		driver.findElement(By.xpath("//*[@id='SubmitButton']")).click();
		Thread.sleep(5000);

		driver.switchTo().frame(driver
				.findElement(By.xpath("//*[@id='RadWindowContentFrameSingleton_SelectSubmitCommentWindow']")));

		driver.findElement(By.xpath("//*[@id='txtComment']")).sendKeys(myResources.getString("timesheet.submission.box.comment")); 
		
		//uncomment to perform a submission
		//driver.findElement(By.xpath("//*[@id='SubmitButton']")).click();

		Thread.sleep(5000);
		driver.close();

	}
	


}
