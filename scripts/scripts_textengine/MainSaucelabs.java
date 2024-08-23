package scripts_textengine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;

import io.appium.java_client.android.AndroidDriver;
import scripts_techniques.Appium.Fonctions;

public class MainSaucelabs {
	
	public static void main(String[] args) {

		
		
		try {
			TextEngine.initSauceLabs("app-release.apk", "test automation","Android", "Samsung_Galaxy_S9_free", null);
			TextEngine.clickbytext("sendkeysbytext");
			TextEngine.sendkeysbytext("plaintext", "kalios");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ((JavascriptExecutor)TextEngine.selfdriver).executeScript("sauce:job-result=" + (TextEngine.sa.wasSuccess() ? "passed" : "failed"));
        
        TextEngine.selfdriver.quit();
        
		//TextEngine.RunKaliosTestPlanSauceLabs("", "", "", "WebdriverIO.ipa", "ios", "iPad_Air_2_free", null);
		//TextEngine.RunKaliosTestPlanSauceLabs("", "", "", "app-release.apk", "Android", "Samsung_Galaxy_S9_free", null);

		//TextEngine.selfdriver.quit();
	}
	
	

}
