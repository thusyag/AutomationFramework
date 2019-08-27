package tests;


import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import utils.JiraPolicy;
import utils.MethodBase;
import utils.MyScreenRecorder;
import utils.TestBase;

public class Tests extends TestBase {

    @JiraPolicy(logTicketReady=true)
    @Test(priority=1, enabled=true)
    public void testLogin() {
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(LoginPage.isLoginPageDisplay(),"Login page not displayed");
        LoginPage.Login("Admin","admin123");
    }
}
