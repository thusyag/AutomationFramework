package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class TestBase extends PageBase {
    public SoftAssert softAssert;
    public static ExtentReports extentReport = new ExtentReports(System.getProperty("user.dir") + "/src/test/resources/reports/ExtentReportResults.html");
    public static ExtentTest extentTest;
    private static final Logger LOGGER = Logger.getLogger(TestBase.class);

    @BeforeTest
    public void beforeTest() {

        System.out.println("Test Running " + this.getClass().toString());
    }

    @BeforeMethod
    public void loadBrowser(Method method) {
        LOGGER.info("Initiate Browser");

        try {
            PageBase.initiateDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        extentTest=extentReport.startTest("Start Report : "+ method.getName());
//        driver.manage().deleteAllCookies();
        LOGGER.info("Browser Initiated");

    }

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void nameBefore(Method method) {

        LOGGER.info("Test name: " + method.getName());
    }
    @AfterMethod(alwaysRun=true)
    public void endTest(ITestResult result) throws Exception {
        if(!result.isSuccess()){
            extentReport.endTest(extentTest);
            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(ExtentReportFunctions.getFilePath()));
                extentTest.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed", result.getThrowable().getMessage());
            }
              else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
            } else {
                extentTest.log(LogStatus.PASS, "Test passed");
            }

        }
        LOGGER.info("Closing Browser");
        PageBase.closeDriver();
        LOGGER.info("Browser Closed");
//        SendEmail.SendEmail();
 //       LOGGER.info("Email successfully Send");

    }


    @AfterMethod
    public void afterMethod(Method method, ITestResult result) {
        LOGGER.info("Executed test case name:" + method.getName() + " Execution Results : " + result.toString());
    }

    //report generation

    @AfterSuite
    public void cleanUp() {
        extentReport.flush();

    }
}
