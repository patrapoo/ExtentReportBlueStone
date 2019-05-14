package extentreport;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;

public class BluestoneScenario1 {
	String text,a[];
	@Test
	public void scenario1() throws Exception
	{
		WebDriver driver = new FirefoxDriver();
		
		Actions act=new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		 // 1.Create an Object of ExtentHtmlReporter
	    ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(new File("C:\\Users\\Rabi\\Documents\\BlueStoneExtentRepots\\regressionreport2.html"));
	    htmlReporter.config().setTheme(Theme.DARK);//3 statements for changing title,heading
	    htmlReporter.config().setDocumentTitle("Test Yantra");
	    htmlReporter.config().setReportName("Regression Test Suite1");
	    
	    //2.Create an object of ExtentReports class and attach report and create a test
	    ExtentReports extent =new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    extent .setSystemInfo("Environment", "Test");//extra information about the test
	    extent.setSystemInfo("Eng_name", "pooja");
	    extent.setSystemInfo("Build_No", "1.2");
	    extent.setSystemInfo("Platform", "Windows 10");
	    
	    //3.create a test
	    ExtentTest test =extent.createTest("HomePageTest");
	    test.log(Status.INFO, "Opening the Firefox Browser");
		driver.get("https://bluestone.com");
		test.log(Status.INFO, "Entered BlueStone page");
		driver.findElement(By.xpath("//div[@class='container']/descendant::a[text()='Rings ']")).click();
		act.moveToElement(driver.findElement(By.xpath("//div[@id='top-filter']/descendant::section[@id='Gold Purity-form']/child::span"))).perform();
		text=driver.findElement(By.xpath("//div[@id='top-filter']/descendant::section[@id='Gold Purity-form']/child::span/following-sibling::div/descendant::span[text()=' 22k ']")).getText();
		
		 a=text.split(" ");
		/*
		 * // System.out.println("count of 22k is "+a[1]); test.log(Status.INFO,
		 * "Count of 22k gold purity is "+a[1]);
		 */
		 
		   TakesScreenshot sc=(TakesScreenshot)driver;
	    	File fsrc=sc.getScreenshotAs(OutputType.FILE);
	    	File dsrc=new File("C:\\Users\\Rabi\\Documents\\BlueStoneExtentRepots\\Screenshot1.png");
	        FileUtils.copyFile(fsrc,dsrc);
	    	
		//4.Attaching Screen Shot
	       test.addScreenCaptureFromPath("C:\\Users\\Rabi\\Documents\\BlueStoneExtentRepots\\Screenshot1.png");
		 driver.close();
	}

}
