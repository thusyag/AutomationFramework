package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.PageBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportFunctions extends PageBase {
    private static String screenShotFolderPath = System.getProperty("user.dir") + "/src/test/resources/screenshots/";

    public static String getTimeStamp(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public static String getFilePath() {

        String filename = getTimeStamp("yyyy-mm-dd_hh-mm-ss")+".png";
        File screenshotFile = ((TakesScreenshot)PageBase.getDriver()).getScreenshotAs(OutputType.FILE);

        File file = new File(screenShotFolderPath + filename);
        try {
            FileUtils.copyFile(screenshotFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
}
