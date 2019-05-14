package extentreport;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BluestoneScenario3 {
	@Test
	public void scenario1() throws Exception 
	{
		WebDriver driver = new FirefoxDriver();
		
		 // 1.Create an Object of ExtentHtmlReporter
	    ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(new File("C:\\Users\\Rabi\\Documents\\BlueStoneExtentRepots\\regressionreport3.html"));
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
	    ExtentTest test =extent.createTest("BlueStonePageTest");
	    test.log(Status.INFO, "Opening the Firefox Browser");
		driver.get("https://bluestone.com");
		test.log(Status.INFO, "Entered BlueStone page");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act=new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://bluestone.com");
		js.executeScript("window.scrollBy(0,4000)");
		TakesScreenshot sc=(TakesScreenshot)driver;
		File fsrc=sc.getScreenshotAs(OutputType.FILE);
		File dsrc=new File("C:\\Users\\Rabi\\Documents\\BlueStoneExtentRepots\\Screenshot3.png");
		FileUtils.copyFile(fsrc, dsrc);
		
		//4.Attaching Screen Shot
	       test.addScreenCaptureFromPath("C:\\Users\\Rabi\\Documents\\BlueStoneExtentRepots\\Screenshot3.png");
	       test.log(Status.INFO, "doing the scrolling in Homepage");
	     //5.Write it the log details to the html file
	       driver.close();
	       extent.flush();//to store everything in extent report

	}

}
