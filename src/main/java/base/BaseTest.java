package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport(){
        extent = ExtentReportManager.getReportInstance();
    }

    @AfterSuite
    public void teardownReport(){
        extent.flush();
        String reportPath = ExtentReportManager.reportPath;
        EmailUtils.sendTestReport(reportPath);
    }
    @BeforeMethod
    public void setUp() {
        Log.info("Starting WebDriver...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get("https://admin-demo.nopcommerce.com/login");
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            String testCaseName = result.getMethod().getMethodName();
            String screenshotPath=ExtentReportManager.captureScreenshot(driver,testCaseName);
            test.fail("Test Failed.. Check Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
       if(driver!=null){
           Log.info("Closing Browser");
           driver.quit();
       }
    }
}
