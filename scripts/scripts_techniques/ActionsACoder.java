
package scripts_techniques;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.openqa.selenium.support.ui.*;
import java.util.Date;
import java.util.Hashtable;
import java.io.IOException;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import java.time.Duration;
import scripts_techniques.Selenium.Fonctions;
import scripts_techniques.Selenium.Teststep;

public class ActionsACoder {
	
	//ADD YOUR ACTIONS HERE
	
	//Action WebObject_myclick
	 public static boolean myclick(WebDriver selenium, Teststep teststep) throws IOException {
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		WebElement myObj;
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')='Women']")));
	                                            
	                            return Fonctions.logStepOK(teststep, selenium, time1);
	                            
	                    
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}//Fin action WebObject_myclick

}
