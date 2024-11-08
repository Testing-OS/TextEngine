package scripts_textengine;

//import com.sun.deploy.uitoolkit.impl.text.TextWindow;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runners.BrowserStack.BstackRunner;

//import javax.xml.soap.Text;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainBrowserStackMobile extends BstackRunner {

	public static void main(String[] args) throws IOException {
		System.out.println("debut test");

        TextEngine.RunKaliosTestPlanMobileAndroidBrowserStack();

	}
    @Test
    public void test()throws IOException, InterruptedException {
       TextEngine.initMobileAppAndroidBrowserStack("parcours 1");


       TextEngine.clickbytext("Alert");
        TextEngine.clickbytext("OK");
        TextEngine.clickbytext("Text");
        TextEngine.clickbytext("Enter a text");
        TextEngine.sendkeysbytext("Enter a text", "kalios");

        //TextEngine.clickbyresourceid("com.example.appkalios:id/sendkeysbytext");
        //TextEngine.clickbytext("SEND KEYS BY TEXT");
        //TextEngine.sendkeysbytext("Search Wikipedia", "kalios test");
        //TextEngine.clickbytext("Best Picture");
        //TextEngine.selfdriver.quit();

    }
}
