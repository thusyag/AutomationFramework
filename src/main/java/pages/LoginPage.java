package pages;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import utils.MethodBase;
import utils.PageBase;

import java.util.logging.Logger;

public class LoginPage extends PageBase {
    public SoftAssert softAssert;

    private static final Logger LOGGER = (Logger) Logger.getLogger(String.valueOf(LoginPage.class));


    private static String hdrLogin = "logInPanelHeading";
    private static String txtUserName = "txtUsername";
    private static String txtPassword ="txtPassword";
    private static String btnLogin = "btnLogin";
    private static String alert = "spanMessage";

    public static boolean isLoginPageDisplay() {

        return MethodBase.isDisplayed_ById(hdrLogin);
    }
    public static void Login(String username, String password) {
        MethodBase.setText_ByID(txtUserName,username);
        MethodBase.setText_ByID(txtPassword,password);
        MethodBase.clickButton_ById(btnLogin);
    }
}
