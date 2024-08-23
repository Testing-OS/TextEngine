
package scripts_techniques.Appium;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.lang.*;

import org.apache.commons.lang3.StringUtils;
import org.ini4j.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.*;
import io.appium.java_client.touch.*;
import io.appium.java_client.*;

import scripts_techniques.Selenium.Teststep;
import scripts_techniques.Config;
import scripts_techniques.Appium.Fonctions;
import io.appium.java_client.ios.IOSDriver;

public class Scripts_techniques {
	static char str = '"';

	public static boolean mobileobject_checkbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
			}
			if (myObj.getText().equals(teststep.param)) {
				return Fonctions.logStepOK(teststep, driver, time1);
			} else {
				return Fonctions.logStepWarning(teststep, driver, time1, "Value of property value   found : "+  myObj.getText());		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_checkbyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.id(array_prop_object.get("resourceid")));
			if (myObj.getText().equals(teststep.param)) {
				return Fonctions.logStepOK(teststep, driver, time1);
			} else {
				return Fonctions.logStepWarning(teststep, driver, time1, "Value of property value   found : "+  myObj.getText());		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_checkbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			}
			if (myObj.getText().equals(teststep.param)) {
				return Fonctions.logStepOK(teststep, driver, time1);
			} else {
				return Fonctions.logStepWarning(teststep, driver, time1, "Value of property value   found : "+  myObj.getText());		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_checkbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driverInstance).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			Fonctions.switchToWebViewContext((AndroidDriver) driverInstance);
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					parameter = ini.get("parameter", teststep.param.substring(1));
				} catch(Exception e) {
					System.out.println ("The parameter was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driverInstance, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
				}
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			if (myObj.getAttribute("value") != null) {
				if (myObj.getAttribute("value").equals(parameter)) {
					System.out.println ("The value was found");
					return Fonctions.logStepOK(teststep, selenium, time1);
				} else {
					System.out.println ("Value not found.   found : "+  myObj.getAttribute("value"));
					return Fonctions.logStepWarning(teststep, selenium, time1, "Value not found   found : "+  myObj.getAttribute("value"));
				}
			} else {
				if (myObj.getText().equals(parameter)) {
					System.out.println ("The text was found");
					return Fonctions.logStepOK(teststep, selenium, time1);
				} else {
					System.out.println ("Text not found.   found : "+  myObj.getText());
					return Fonctions.logStepWarning(teststep, selenium, time1, "Value of property innerText   found : "+  myObj.getText());		
				}		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_clickbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
				myObj.click();
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
				myObj.click();
			}
			
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_clickbyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.id(array_prop_object.get("resourceid")));
			myObj.click();
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_clickbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
				myObj.click();
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
				myObj.click();
			}
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_clickbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				try {
				((AndroidDriver)driverInstance).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
				Fonctions.switchToWebViewContext((AndroidDriver) driverInstance);
				myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
				Fonctions.highLighterMethod(driverInstance, myObj);
				myObj.click();
				System.out.println("##### click made on : \n"+ myObj + "\n");
				return Fonctions.logStepOK(teststep, driverInstance, time1);
			} catch (Exception e) {
				System.out.println ("##### Element.click fail #####");
			}
			try {
				myObj = getShadowElement(driverInstance, "[id="+array_prop_object.get("id")+"]");
				if(myObj != null)
				{
					myObj.click();
					System.out.println("##### click made on : \n"+ myObj + "\n");
					return Fonctions.logStepOK(teststep, driverInstance, time1);
				}
			} catch (Exception e) {
				System.out.println ("##### click in shadow fail #####");
			}
			return Fonctions.logStepKO(teststep, driverInstance, time1, "click Fail");
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_keysbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
				myObj.sendKeys(teststep.param);
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
				myObj.sendKeys(teststep.param);
			}
			
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_keysbyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.id(array_prop_object.get("resourceid")));
			myObj.sendKeys(teststep.param);
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_keysbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				System.out.println("Search native elements");
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
				myObj.sendKeys(teststep.param);
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				System.out.println("Search hybrid element");
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
				myObj.sendKeys(teststep.param);
			}
			
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_keysbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driverInstance).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			Fonctions.switchToWebViewContext((AndroidDriver) driverInstance);
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					parameter = ini.get("parameter", teststep.param.substring(1));
				} catch(Exception e) {
					System.out.println ("The parameter was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driverInstance, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
				}
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_clearvaluebyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
			}
			// MobileElement longPress = (MobileElement) new WebDriverWait(driver, 30).
			// 	until(ExpectedConditions.elementToBeClickable(myObj));
			myObj.clear();
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_clearvaluebyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.id(array_prop_object.get("resourceid")));
			myObj.clear();
			return Fonctions.logStepOK(teststep, driver, time1);
		}catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}
	}

	public static boolean mobileobject_clearvaluebyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			}
			// MobileElement longPress = (MobileElement) new WebDriverWait(driver, 30).
			// 	until(ExpectedConditions.elementToBeClickable(myObj));
			myObj.clear();
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_clearvaluebyid(final WebDriver selenium, Teststep teststep) throws IOException
	{
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		WebElement myObj;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driverInstance).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			Fonctions.switchToWebViewContext((AndroidDriver) driverInstance);
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					parameter = ini.get("parameter", teststep.param.substring(1));
				} catch(Exception e) {
					System.out.println ("The parameter was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driverInstance, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
				}
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.clear();
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_doubleClickbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.name(array_prop_object.get("name")));
			//myObj.click();
			// MobileElement element = (MobileElement) new WebDriverWait(driver, 30).
			// 		until(ExpectedConditions.elementToBeClickable(myObj));
			Thread.sleep(1000);
			Point source = myObj.getLocation();
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence tap = new Sequence(finger, 1);
			tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
					PointerInput.Origin.viewport(), source.x, source.y));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(200)));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(40)));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(200)));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			((IOSDriver) driver).perform(Collections.singletonList(tap));
			Thread.sleep(4000);	
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_DoubleClickbyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.id(array_prop_object.get("resourceid")));
			//myObj.click();
			// MobileElement element = (MobileElement) new WebDriverWait(driver, 30).
			// 		until(ExpectedConditions.elementToBeClickable(myObj));
			Thread.sleep(1000);
			Point source = myObj.getLocation();
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence tap = new Sequence(finger, 1);
			tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
					PointerInput.Origin.viewport(), source.x, source.y));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(200)));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(40)));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(200)));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			System.out.println("test");
			((AndroidDriver) driver).perform(Collections.singletonList(tap));
			Thread.sleep(4000);	
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobileobject_doubleclickbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			if (myObj == null) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			}
			Point source = myObj.getLocation();
			Thread.sleep(1000);
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence tap = new Sequence(finger, 1);
			tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
					PointerInput.Origin.viewport(), source.x, source.y));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(200)));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(40)));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(200)));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			String platform = ((HasCapabilities)driver).getCapabilities().getCapability("platformName").toString();
			System.out.println(platform);
			if(platform.equals("iOS"))
				((IOSDriver) driver).perform(Collections.singletonList(tap));
			if(platform.equals("Android"))
				((AndroidDriver) driver).perform(Collections.singletonList(tap));

			Thread.sleep(4000);
			
			//myObj.click();
			// MobileElement element = (MobileElement) new WebDriverWait(driver, 30).
			// 		until(ExpectedConditions.elementToBeClickable(myObj));
				
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	
	public static boolean  mobileobject_longpressbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
	
			// MobileElement longPress = (MobileElement) new WebDriverWait(driver, 30).
			// 	until(ExpectedConditions.elementToBeClickable(myObj));
			new Actions(driver).clickAndHold(myObj).perform();	
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	
	public static boolean  mobileobject_longpressbyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.id(array_prop_object.get("resourceid")));
			
			// MobileElement longPress = (MobileElement) new WebDriverWait(driver, 30).
			// 	until(ExpectedConditions.elementToBeClickable(myObj));
			new Actions(driver).clickAndHold(myObj).perform();	
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean  mobileobject_longpressbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			if (myObj == null) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			}
			// MobileElement longPress = (MobileElement) new WebDriverWait(driver, 30).
			// 	until(ExpectedConditions.elementToBeClickable(myObj));
			new Actions(driver).clickAndHold(myObj).perform();	
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	
	// public static boolean  mobileobject_Scrollbyname(final WebDriver driver, Teststep teststep) throws IOException {
	// 	Date time1 = new Date();
	// 	try {
	// 		Thread.sleep(Config.pause_actions);
	// 		} catch (InterruptedException e) {
	// 			e.printStackTrace();
	// 	}
	// 	WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
	// 	WebElement myObj;
	// 	   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
	// 	if (array_prop_object.isEmpty()){
	// 		return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
	// 	}
	// 	try {
	// 		myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("name"))));
	// 		Point location = myObj.getLocation();
	// 		//Point Center = ((MobileElement) myObj).getCenter();
	// 		int height = myObj.getSize().getHeight();
	// 		new TouchAction((AndroidDriver)driver)
	// 			  .press(new PointOption().withCoordinates(location.x, location.y+height-50))
	// 			  .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
	// 		   .moveTo(new PointOption().withCoordinates(location.x, location.y))
	// 			  .release()
	// 			  .perform();
	// 		return Fonctions.logStepOK(teststep, driver, time1);
	// 	} catch (Exception e) {
	// 		System.out.println (e.getMessage());
	// 		return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
	// 	}		
	// }

	// public static boolean  mobileobject_Scrollbyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
	// 	Date time1 = new Date();
	// 	try {
	// 		Thread.sleep(Config.pause_actions);
	// 		} catch (InterruptedException e) {
	// 			e.printStackTrace();
	// 	}
	// 	WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
	// 	WebElement myObj;
	// 	   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
	// 	if (array_prop_object.isEmpty()){
	// 		return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
	// 	}
	// 	try {
	// 		myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("resourceid"))));
	// 		Point location = myObj.getLocation();
	// 		Point Center = ((MobileElement) myObj).getCenter();
	// 		int height = myObj.getSize().getHeight();
	// 		new TouchAction((AndroidDriver)driver)
	// 			  .press(new PointOption().withCoordinates(Center.x, location.y+height-50))
	// 			  .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
	// 		   .moveTo(new PointOption().withCoordinates(Center.x, location.y))
	// 			  .release()
	// 			  .perform();
	// 		return Fonctions.logStepOK(teststep, driver, time1);
	// 	} catch (Exception e) {
	// 		System.out.println (e.getMessage());
	// 		return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
	// 	}		
	// }

	// public static boolean  mobileobject_Scrollbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
	// 	Date time1 = new Date();
	// 	try {
	// 		Thread.sleep(Config.pause_actions);
	// 		} catch (InterruptedException e) {
	// 			e.printStackTrace();
	// 	}
	// 	WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
	// 	WebElement myObj;
	// 	   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
	// 	if (array_prop_object.isEmpty()){
	// 		return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
	// 	}
	// 	try {
	// 		myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
	// 		Point location = myObj.getLocation();
	// 		// Point Center = ((MobileElement) myObj).getCenter();
	// 		int height = myObj.getSize().getHeight();
	// 		new TouchAction((AndroidDriver)driver)
	// 			  .press(new PointOption().withCoordinates(location.x, location.y+height-50))
	// 			  .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
	// 		   .moveTo(new PointOption().withCoordinates(location.x, location.y))
	// 			  .release()
	// 			  .perform();
	// 		return Fonctions.logStepOK(teststep, driver, time1);
	// 	} catch (Exception e) {
	// 		System.out.println (e.getMessage());
	// 		return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
	// 	}		
	// }
	
	public static boolean  mobileobject_swipeLeftbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
			}
			
	// Point Center = ((MobileElement) myObj).getCenter();
	Actions actions = new Actions(driver);
	actions.clickAndHold(myObj);
	actions.moveByOffset(-500, 0).release();
	actions.build().perform();
	System.out.println("Element swiped to the left");
	return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	
	public static boolean  mobileobject_swipeLeftbyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.id(array_prop_object.get("resourceid")));
			// Point Center = ((MobileElement) myObj).getCenter();
			Actions actions = new Actions(driver);
			actions.clickAndHold(myObj);
			actions.moveByOffset(-500, 0).release();
			actions.build().perform();
			System.out.println("Element swiped to the left");
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	
	public static boolean  mobileobject_swipeLeftbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			}
			
			Actions actions = new Actions(driver);
			actions.clickAndHold(myObj);
			actions.moveByOffset(-500, 0).release();
			actions.build().perform();
			System.out.println("Element swiped to the left");
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	
	public static boolean  mobileobject_swipeRightbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.name(array_prop_object.get("name")));
			}
			//MobileElement element = driver.findElementById("com.kaliostest.test:id/swipe_deck");
			Actions actions = new Actions(driver);
			actions.clickAndHold(myObj);
			actions.moveByOffset(500, 0).release();
			actions.build().perform();
			System.out.println("Element swiped to the right");
	return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	
	public static boolean  mobileobject_swipeRightbyresourceid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			myObj = driver.findElement(By.id(array_prop_object.get("resourceid")));
			//MobileElement element = driver.findElementById("com.kaliostest.test:id/swipe_deck");
			Actions actions = new Actions(driver);
			actions.clickAndHold(myObj);
			actions.moveByOffset(500, 0).release();
			actions.build().perform();
			System.out.println("Element swiped to the right");
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	
	public static boolean  mobileobject_swipeRightbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		   Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			try {
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			} catch (Exception e) {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				myObj = driver.findElement(By.xpath(array_prop_object.get("xpath")));
			}
			
	//MobileElement element = driver.findElementById("com.kaliostest.test:id/swipe_deck");
			Actions actions = new Actions(driver);
			actions.clickAndHold(myObj);
			actions.moveByOffset(500, 0).release();
			actions.build().perform();
			System.out.println("Element swiped to the right");
			
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println ("test");
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : ");	
		}		
	}
	
	public static boolean mobileContainer_wait(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);

		return Fonctions.logStepOK(teststep, driver, time1);
	}

	public static boolean mobilecontainer_RestartApp(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		try {
			((AndroidDriver) driver).resetApp();
			return Fonctions.logStepOK(teststep, driver, time1);
			} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}
	}

//#########################################################################################
//##################################### Action Hybrid #####################################
//#########################################################################################

	public static boolean mobilehybridcontainer_wait(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Fonctions.switchToWebViewContext(((AndroidDriver)driver));
		
		return Fonctions.logStepOK(teststep, driver, time1);
	}

	public static boolean mobilehybridobject_checkbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<  String, String>   array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			if (myObj.getAttribute("value").equals(teststep.param)) {
				return Fonctions.logStepOK(teststep, driver, time1);
			} else {
				return Fonctions.logStepWarning(teststep, driver, time1, "Value of property value   found : "+  myObj.getAttribute("value"));		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobilehybridobject_checkbyid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<  String, String>   array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			if (myObj.getAttribute("value").equals(teststep.param)) {
				return Fonctions.logStepOK(teststep, driver, time1);
			} else {
				return Fonctions.logStepWarning(teststep, driver, time1, "Value of property value   found : "+  myObj.getAttribute("value"));		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobilehybridobject_checkbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<  String, String>   array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			if (myObj.getAttribute("value").equals(teststep.param)) {
				return Fonctions.logStepOK(teststep, driver, time1);
			} else {
				return Fonctions.logStepWarning(teststep, driver, time1, "Value of property value   found : "+  myObj.getAttribute("value"));		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobilehybridobject_clickbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<  String, String>   array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			myObj.click();
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobilehybridobject_clickbyid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<  String, String>   array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			myObj.click();
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobilehybridobject_clickbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<  String, String>   array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			myObj.click();
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}
	}

	public static boolean mobilehybridobject_sendkeysbyname(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<  String, String>   array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			myObj.sendKeys(teststep.param);
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobilehybridobject_sendkeysbyid(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			Thread.sleep(5000);
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			myObj.sendKeys(teststep.param);
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

	public static boolean mobilehybridobject_sendkeysbyxpath(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Config.timeout_elements);
		WebElement myObj;
		Hashtable<  String, String>   array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			Fonctions.switchToWebViewContext(((AndroidDriver)driver));
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			myObj.sendKeys(teststep.param);
			return Fonctions.logStepOK(teststep, driver, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}

//#########################################################################################
//##################################### Action By Text ####################################
//#########################################################################################

	public static boolean MobileObject_clickbytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = null;
		Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			String xpath = "";
			String property;
			HashMap<String, WebElement> list = null;
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);

			// Récupération de la variable
			if (array_prop_object.get("texte").charAt(0) == '$') {
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch (Exception e) {
					System.out.println("Error in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Error in the variable file." + e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");	
			}
			String[] parts = property.split("\\|");
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].isEmpty()) {
					System.out.println("Property or context is null");
					return Fonctions.logStepKO(teststep, driver, time1, "Property or context is null");
				}
			}

			Pattern pattern;
			if (parts.length == 1) {
				pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
			} else {
				pattern = Pattern.compile(parts[1], Pattern.CASE_INSENSITIVE);
			}

			try { // Faire le scroll
				System.out.println("je scroll avec zone morte");
				WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
			
			} catch (Exception e) {
				System.out.println("Scroll with text failed");
				try {
					WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
				} catch (Exception ex) {
					System.out.println("Scroll with content-desc failed");
					// return Fonctions.logStepKO(teststep, null, time1, "Element not found with scroll");
				}
			}

			if (parts.length == 1) {
				if (tryProperty != null && !tryProperty.isEmpty()) {
					if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
						System.out.println("Property is in the saved try");
						element = findElements(driver, tryProperty);
						if (element != null) {
							xpath = tryProperty;
						} else list = tryClickByText(driver, parts[0]);
					} else {
						System.out.println("Property is not in the saved try");
						list = tryClickByText(driver, parts[0]);
					}
				} else {
					list = tryClickByText(driver, parts[0]);
				}
			} else {
				if (tryProperty != null && !tryProperty.isEmpty()) {
					if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
						System.out.println("Property is in the saved try");
						element = findElements(driver, tryProperty);
						if (element != null) {
							xpath = tryProperty;
						} else list = tryClickByText(driver, parts[0], parts[1]);
					} else {
						System.out.println("Property is not in the saved try");
						list = tryClickByText(driver, parts[0], parts[1]);
					}
				} else {
					list = tryClickByText(driver, parts[0], parts[1]);
				}
			}

			if (list != null) {
				for(Map.Entry<String, WebElement> entry : list.entrySet()) {
					element = entry.getValue();
					xpath = entry.getKey();
				}
			} 


			if (element != null) {
				// Faire le click
				System.out.println(">>>>> Element found using the xpath :  \n"+ xpath +"\n");
				element.click();
				System.out.println(">>>>> Click on element");
				return Fonctions.logStepOK(teststep, driver, time1, xpath);
			} else {
				System.out.println("Element not found.");

				try {
					Fonctions.switchToWebViewContext((AndroidDriver) driver);
					// Add use of the saved try
					switch (parts.length) {
						case 1:
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
									System.out.println("Property is in the saved try");
									element = findElements(driver, tryProperty);
									if (element != null) {
										xpath = tryProperty;
									} else list = identifyElementToClick(driver, parts[0]);
								} else {
									System.out.println("Property is not in the saved try");
									list = identifyElementToClick(driver, parts[0]);
								}
							} else {
								list = identifyElementToClick(driver, parts[0]);
							}
							//list = identifyElementToClick(driver, parts[0]);
							break;
						case 2:
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
									System.out.println("Property is in the saved try");
									element = findElements(driver, tryProperty);
									if (element != null) {
										xpath = tryProperty;
									} else list = identifyElementToClick(driver, parts[0], parts[1]);
								} else {
									System.out.println("Property is not in the saved try");
									list = identifyElementToClick(driver, parts[0], parts[1]);
								}
							} else {
								list = identifyElementToClick(driver, parts[0], parts[1]);
							}
							//list = identifyElementToClick(driver, parts[0], parts[1]);
							break;
						default:
							break;
					}

					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (element != null) {
						System.out.println(">>>>> Element found using the xpath :  \n"+ xpath +"\n");
						element.click();
						System.out.println(">>>>> Click on element");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					}
				} catch (Exception e) {
					System.out.println("Something failed in webview");
					return Fonctions.logStepKO(teststep, driver, time1, "Something failed in webview");
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Element not found.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileObject_sendkeysbytext(final WebDriver driver, Teststep teststep) throws IOException {
	Date time1 = new Date();
	try {
		Thread.sleep(Config.pause_actions);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	WebElement element = null;
	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
	if (array_prop_object.isEmpty()){
		return Fonctions.logStepKO(teststep, driver, time1, "No such file : " + teststep.object_attach_name);
	}
	try {
		try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
		String xpath = "";
		String property;
		HashMap<String, WebElement> list = null;
		String file_params = Config.dir_params + File.separator + teststep.testcase_label + "_xpath_list.csv";
		String tryProperty = Fonctions.getParameter(file_params, Config.compteur_instance, Config.compteur_params);
		boolean elementfound = false;
		String parameter;

		// Récupérer la propriété dans une variable
		if (array_prop_object.get("texte").charAt(0) == '$'){
			try {
				Wini ini = new Wini(new File(Config.propertyFile));
				property = ini.get("property", array_prop_object.get("texte").substring(1));
			} catch(Exception e) {
				System.out.println ("The property was not found in the variable file.");
				return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
			}
		} else {
			property = array_prop_object.get("texte");
		}
		String[] parts = property.split("\\|");

		// Récupérer le paramètre dans une variable
		if (teststep.param.charAt(0) == '$'){
			try {
				Wini ini = new Wini(new File(Config.propertyFile));
				parameter = ini.get("parameter", teststep.param.substring(1));
			} catch(Exception e) {
				System.out.println ("The parameter was not found in the variable file.");
				return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
			}
		} else {
			parameter = teststep.param;
		}
		try {
			Pattern pattern;
			if (parts.length == 1) {
				pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
			} else {
				pattern = Pattern.compile(parts[1], Pattern.CASE_INSENSITIVE);
			}

			try { // Faire le scroll
				WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
			} catch (Exception e) {
				System.out.println("Scroll with text failed");
				try {
					WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
				} catch (Exception ex) {
					System.out.println("Scroll with content-desc failed");
					// return Fonctions.logStepKO(teststep, driver, time1, "Element not found with scroll");
				}
			}
			// Utiliser le try sauvegardé
			if (!tryProperty.isEmpty() && tryProperty != null) {
				switch (parts.length) {
					case 1:
						if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
							element = findElements(driver, tryProperty);
							xpath = tryProperty;
							if (element != null) {
								elementfound = true;
							}
						}
						break;
					case 2:
						if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
							element = findElements(driver, tryProperty);
							xpath = tryProperty;
							if (element != null) {
								elementfound = true;
							}
						}
						break;
					default:
						System.out.println("Error : not 1 or 2 fields in property.");
						return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
				}
			}

			// Faire les try
			if (!elementfound) {
				switch (parts.length) {
					case 1:
						list = trySendKeyByText(driver, parts[0]);
						break;
					case 2:
						list = trySendKeyByText(driver, parts[0], parts[1]);
						break;
					default:
						return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
				}
			}
			if (list != null) {
				for(Map.Entry<String, WebElement> entry : list.entrySet()) {
					element = entry.getValue();
					xpath = entry.getKey();
				}
			}

			// Écrire dans l'élément
			if (element != null) {
				System.out.println(">>>>> Element found using the xpath :  \n"+xpath+"\n");
				element.clear();
				element.sendKeys(parameter);
				System.out.println("Keys send in element");
				return Fonctions.logStepOK(teststep, driver, time1, xpath);
			} else {
				System.out.println("Element not found");

				try {
					Fonctions.switchToWebViewContext((AndroidDriver) driver);
					if (!tryProperty.isEmpty() && tryProperty != null) {
						switch (parts.length) {
							case 1:
								if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
									element = findElements(driver, tryProperty);
									xpath = tryProperty;
									if (element != null) {
										elementfound = true;
									}
								}
								break;
							case 2:
								if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
									element = findElements(driver, tryProperty);
									xpath = tryProperty;
									if (element != null) {
										elementfound = true;
									}
								}
								break;
							default:
								System.out.println("Error : not 1 or 2 fields in property.");
								return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
						}
					}
					switch (parts.length) {
						case 1:
							list = identifyElementInput(driver, parts[0]);
							break;
						case 2:
							list = identifyElementInput(driver, parts[0], parts[1]);
							break;					
						default:
							break;
					}
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}

					// Écrire dans l'élément
					if (element != null) {
						System.out.println(">>>>> Element found using the xpath :  \n"+xpath+"\n");
						element.clear();
						element.sendKeys(parameter);
						System.out.println("Keys send in element");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					}
				} catch (Exception e) {
					System.out.println("error while in webview");
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Element not found");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
	}
}

	public static boolean MobileObject_selectbytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			WebElement element = null;
			String[] parts = {};
			if (array_prop_object.containsKey("texte")) {
				if (array_prop_object.get("texte").charAt(0) == '$'){
					try {
						Wini ini = new Wini(new File(Config.propertyFile));
						property = ini.get("property", array_prop_object.get("texte").substring(1));
					} catch(Exception e) {
						System.out.println ("The property was not found in the variable file.");
						return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
					}
				} else {
					property = array_prop_object.get("texte");
				}
				parts= property.split("\\|");
			}
			
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					parameter = ini.get("parameter", teststep.param.substring(1));
				} catch(Exception e) {
					System.out.println ("The parameter was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
				}
			} else {
				parameter = teststep.param;
			}
			if (!parameter.equalsIgnoreCase("yes") && !parameter.equalsIgnoreCase("oui") && !parameter.equalsIgnoreCase("no") && !parameter.equalsIgnoreCase("non")) {
				System.out.println("Parameter is not yes/no/oui/non");
				//return Fonctions.logStepWarning(teststep, driver, time1, "Parameter is not yes/no/oui/non");
			}
			boolean statusElement = true;

			Pattern pattern = Pattern.compile("");
			if (parts.length == 1) {
				pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
			} else if (parts.length == 2){
				if (parts[1].charAt(0) == '[' && parts[1].charAt(parts[1].length() - 1) == ']') {
					pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
				} else {
					pattern = Pattern.compile(parts[1], Pattern.CASE_INSENSITIVE);
				}
			}

			try { // Faire le scroll
				WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
			} catch (Exception e) {
				System.out.println("Scroll with text failed");
				try {
					WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
				} catch (Exception ex) {
					System.out.println("Scroll with content-desc failed");
					// return Fonctions.logStepKO(teststep, driver, time1, "Element not found with scroll");
				}
			}
			
			if (!tryProperty.isEmpty() && tryProperty != null) {
				switch (parts.length) {
					case 0:
						break;
					case 1:
						if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
							element = findElements(driver, tryProperty);
							if (element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					case 2:
						if (parts[1].charAt(0) == '[' && parts[1].charAt(parts[1].length() - 1) == ']') {
							if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str)) {
								element = findElementsIndex(driver, tryProperty, parts[1].substring(1, parts[1].length()-1));
								if (element != null) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
						} else {
							if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
								element = findElements(driver, tryProperty);
								if (element != null) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
						}
						break;
					case 3:
						if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
							element = findElementsIndex(driver, tryProperty, parts[2].substring(1, parts[2].length()-1));
							if (element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					default:
						return Fonctions.logStepKO(teststep, driver, time1, "Not 1 to 3 property");
				}
			}
			if (!elementfound) {
				switch (parts.length) {
					case 0:
						break;
					case 1:
						list = trySelectByText(driver, parts[0]);
						break;
					case 2:
						if (parts[1].charAt(0) == '[' && parts[1].charAt(parts[1].length() - 1) == ']') {
							list = trySelectByTextByIndex(driver, parts[0], parts[1].substring(1, parts[1].length()-1));
						} else {
							list = trySelectByText(driver, parts[0], parts[1]);
						}
						break;
					case 3:
						list = trySelectByTextByIndex(driver, parts[0], parts[1], parts[2].substring(1, parts[2].length()-1));
						break;
					default:
						return Fonctions.logStepKO(teststep, driver, time1, "Not 1 to 3 property");
				}
			}
			
			if (list != null) {
				for(Map.Entry<String, WebElement> entry : list.entrySet()) {
					element = entry.getValue();
					xpath = entry.getKey();
				}
			}

			if (element != null) {
				statusElement = element.getAttribute("checked").equals("true");
				if (!statusElement) {
					if (ignoreSpacesMajMin(parameter).equals("yes") || ignoreSpacesMajMin(parameter).equals("oui")) {
						element.click();
						System.out.println("Select made on \n" + xpath + "\n");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					} 
					if (ignoreSpacesMajMin(parameter).equals("no") || ignoreSpacesMajMin(parameter).equals("non")) {
						System.out.println("Element is already checked");
						return Fonctions.logStepWarning(teststep, driver, time1, "Element is already checked");
					}
				} else {
					if (ignoreSpacesMajMin(parameter).equals("no") || ignoreSpacesMajMin(parameter).equals("non")) {
						element.click();
						System.out.println("Select made on \n" + xpath + "\n");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					}
					if (ignoreSpacesMajMin(parameter).equals("yes") || ignoreSpacesMajMin(parameter).equals("oui")) {
						System.out.println("Element is already unchecked");
						return Fonctions.logStepWarning(teststep, driver, time1, "Element is already unchecked");
					}
				}
			} else {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				HashMap<String, Boolean> list1 = new HashMap<>();
				switch(parts.length) {
				case 0:
					try {
						System.out.println("###### Start tries on dropdown ######");
						if (parameter.charAt(0) == '[' && parameter.charAt(parameter.length() - 1) == ']'){
							System.out.println("Error : You need to set a property when you want to use the index.");
							return Fonctions.logStepKO(teststep, driver, time1, "Error : You need to set a property when you want to use the index.");
						}
						if (tryProperty != null && !tryProperty.isEmpty()) {
							if(!tryProperty.contains("parent") && tryProperty.contains(teststep.param)){
								statusElement = chooseByTry(driver, tryProperty);
								System.out.println("ici");
								if(statusElement == true) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
						}
						if (!elementfound) {
							list1 = chooseByText(driver, parameter, statusElement);
							for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
								statusElement = entry.getValue();
								xpath = entry.getKey();
							}
						}
						if (statusElement == true) {
							return Fonctions.logStepOK(teststep, driver, time1, xpath);
						}
					}catch(Exception e){
						System.out.println(e);
					}
					break;
				case 1:
					if (parameter.equals("Oui") || parameter.equals("oui") || parameter.equals("Yes") || parameter.equals("yes")
					|| parameter.equals("Non") || parameter.equals("non") || parameter.equals("No") || parameter.equals("no")) {
						try {
							System.out.println("###### Start tries on radio and checkbox ######");
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s","") + str) && tryProperty.contains(str + parameter + str) && StringUtils.countMatches(tryProperty, "parent")==1){
									statusElement =  radioCheckBoxByTry(driver, tryProperty, parameter);
									if(statusElement == true) {
										xpath = tryProperty;
										elementfound = true;
									}
								}
							}
							if (!elementfound) {
								list1 = radioCheckBoxByText(driver, parts[0], statusElement, parameter);
								for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
									statusElement = entry.getValue();
									xpath = entry.getKey();
								}
							}
							if (statusElement == true) {
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							}
						}catch(Exception e){
							System.out.println(e);
						}
					}
					try {
						System.out.println("###### Start tries on dropdown ######");
						if (tryProperty != null && !tryProperty.isEmpty()) {
							if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s","") + str) && tryProperty.contains(str + parameter + str) && StringUtils.countMatches(tryProperty, "parent")==1){
								statusElement = chooseByTry(driver, tryProperty, parameter);
								if(statusElement == true) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
						}
						if (!elementfound) {
							list1 = chooseByText(driver, parts[0], parameter, statusElement);
							for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
								statusElement = entry.getValue();
								xpath = entry.getKey();
							}
						}
						if (statusElement == true) {
							return Fonctions.logStepOK(teststep, driver, time1, xpath);
						}
					}catch(Exception e){
						System.out.println(e);
					}
					break;
				case 2:
					if (parts[1].charAt(0) == '[' && parts[1].charAt(parts[1].length() - 1) == ']'){
						parts[1] = parts[1].substring(1, parts[1].length() - 1);
						if (parameter.equals("Oui") || parameter.equals("oui") || parameter.equals("Yes") || parameter.equals("yes")
							|| parameter.equals("Non") || parameter.equals("non") || parameter.equals("No") || parameter.equals("no")) {
							try {
								System.out.println("###### Start tries on radio and checkbox ######");
								if (tryProperty != null && !tryProperty.isEmpty()) {
									if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && StringUtils.countMatches(tryProperty, "parent")==1){
										statusElement =  radioCheckBoxByTryAndIndex(driver, tryProperty, parts[1], parameter);
										if(statusElement == true) {
											xpath = tryProperty;
											elementfound = true;
										}
									}
									
								}
								if (!elementfound) {
									list1 = radioCheckBoxByTextAndIndex(driver, parts[0], parts[1], statusElement, parameter);
									for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
										statusElement = entry.getValue();
										xpath = entry.getKey();
									}
								}
								if (statusElement == true) {
									return Fonctions.logStepOK(teststep, driver, time1, xpath);
								}
							}catch(Exception e){
								System.out.println(e);
							}
						}
						try {
							System.out.println("###### Start tries on dropdown ######");
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parameter + str) && StringUtils.countMatches(tryProperty, "parent") == 1){
									statusElement = chooseByTryAndIndex(driver, tryProperty, parts[1], parameter);
									if(statusElement == true) {
										xpath = tryProperty;
										elementfound = true;
									}
								}
							}
							if (!elementfound) {
								list1 = chooseByTextAndIndex(driver, parts[0], parts[1], parameter, statusElement);
								for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
									statusElement = entry.getValue();
									xpath = entry.getKey();
								}
							}
							if (statusElement == true) {
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							}
						}catch(Exception e){
							System.out.println(e);
						}
					} else {
						if (parameter.equals("Oui") || parameter.equals("oui") || parameter.equals("Yes") || parameter.equals("yes")
						|| parameter.equals("Non") || parameter.equals("non") || parameter.equals("No") || parameter.equals("no")) {
							try {
								System.out.println("###### Start tries on radio and checkbox ######");
								if (tryProperty != null && !tryProperty.isEmpty()) {
									if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s","") + str) && StringUtils.countMatches(tryProperty, "parent")==2){
										statusElement =  radioCheckBoxByTry(driver, tryProperty, parameter);
										if(statusElement == true) {
											xpath = tryProperty;
											elementfound = true;
										}
									}
									
								}
								if (!elementfound) {
									list1 = radioCheckBoxByText(driver, parts[0], parts[1], statusElement, parameter);
									for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
										statusElement = entry.getValue();
										xpath = entry.getKey();
									}
								}
								if (statusElement == true) {
									return Fonctions.logStepOK(teststep, driver, time1, xpath);
								}
							}catch(Exception e){
								System.out.println(e);
							}
						}
						try {
							System.out.println("###### Start tries on dropdown ######");
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s","") + str) && tryProperty.contains(str + parameter + str) && StringUtils.countMatches(tryProperty, "parent")==2){
									statusElement = chooseByTry(driver, tryProperty, parameter);
									if(statusElement == true) {
										xpath = tryProperty;
										elementfound = true;
									}
								}
							}
							if (!elementfound) {
								list1 = chooseByText(driver, parts[0], parts[1], parameter, statusElement);
								for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
									statusElement = entry.getValue();
									xpath = entry.getKey();
								}
							}
							if (statusElement == true) {
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							}
						}catch(Exception e){
							System.out.println(e);
						}
					}
					break;
				case 3:
					if (parts[2].charAt(0) == '[' && parts[2].charAt(parts[2].length() - 1) == ']'){
						parts[2] = parts[2].substring(1, parts[2].length() - 1);
						if (parameter.equals("Oui") || parameter.equals("oui") || parameter.equals("Yes") || parameter.equals("yes")
						|| parameter.equals("Non") || parameter.equals("non") || parameter.equals("No") || parameter.equals("no")) {
							try {
								System.out.println("###### Start tries on radio and checkbox ######");
								if (tryProperty != null && !tryProperty.isEmpty()) {
									if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str)){
										statusElement =  radioCheckBoxByTryAndIndex(driver, tryProperty, parts[2], parameter);
										if(statusElement == true) {
											xpath = tryProperty;
											elementfound = true;
										}
									}
								}
								if (!elementfound) {
									list1 = radioCheckBoxByTextAndIndex(driver, parts[0], parts[1], parts[2], statusElement, parameter);
									for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
										statusElement = entry.getValue();
										xpath = entry.getKey();
									}
								}
								if (statusElement == true) {
									return Fonctions.logStepOK(teststep, driver, time1, xpath);
								}
							}catch(Exception e){
								System.out.println(e);
							}
						}
						try {
							System.out.println("###### Start tries on dropdown ######");
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parameter + str)){
									statusElement = chooseByTryAndIndex(driver, tryProperty, parts[2], parameter);
									if(statusElement == true) {
										xpath = tryProperty;
										elementfound = true;
									}	
								}
							}
							if (!elementfound) {
								list1 = chooseByTextAndIndex(driver, parts[0], parts[1], parts[2], parameter, statusElement);
								for(Map.Entry<String, Boolean> entry : list1.entrySet()) {
									statusElement = entry.getValue();
									xpath = entry.getKey();
								}
							}
							if (statusElement == true) {
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							}
						}catch(Exception e){
							System.out.println(e);
						}
					}
				default:
					System.out.println("Error at switch, number of strings from teststep is not between 0 and 3");
					return Fonctions.logStepKO(teststep, driver, time1, "Error at switch, number of strings from teststep is not between 0 and 3");
			}
				/** if (list1 != null) {
					for(Map.Entry<String, WebElement> entry : list1.entrySet()) {
						element = entry.getValue();
						xpath = entry.getKey();
					}
				}

				if (element != null) {
					statusElement = element.getAttribute("checked").equals("true");
					if (!statusElement) {
						if (ignoreSpacesMajMin(parameter).equals("yes") || ignoreSpacesMajMin(parameter).equals("oui")) {
							element.click();
							System.out.println("Select made on \n" + xpath + "\n");
							return Fonctions.logStepOK(teststep, driver, time1, xpath);
						} 
						if (ignoreSpacesMajMin(parameter).equals("no") || ignoreSpacesMajMin(parameter).equals("non")) {
							System.out.println("Element is already checked");
							return Fonctions.logStepWarning(teststep, driver, time1, "Element is already checked");
						}
					} else {
						if (ignoreSpacesMajMin(parameter).equals("no") || ignoreSpacesMajMin(parameter).equals("non")) {
							element.click();
							System.out.println("Select made on \n" + xpath + "\n");
							return Fonctions.logStepOK(teststep, driver, time1, xpath);
						}
						if (ignoreSpacesMajMin(parameter).equals("yes") || ignoreSpacesMajMin(parameter).equals("oui")) {
							System.out.println("Element is already unchecked");
							return Fonctions.logStepWarning(teststep, driver, time1, "Element is already unchecked");
						}
					}
				}
				System.out.println("Element not found");
				return Fonctions.logStepKO(teststep, driver, time1, "Element not found");
			**/}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
		return Fonctions.logStepKO(teststep, driver, time1, "Return not supposed to happend here");
	}

	public static boolean MobileObject_checkbytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			// Définition des variables
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			String property = "";
			String[] parts = {};
			String parameter ="";
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			WebElement element = null;
			Wini ini = new Wini(new File(Config.propertyFile));

			// Récupérer la propriété si c'est une variable
			if (array_prop_object.get("texte").charAt(0) == '$') {
				try {
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");
			}
			
			parts = property.split("\\|");

			// Récupérer le paramètre si c'est une variable
			if (teststep.param.charAt(0) == '$') {
				try {
					parameter = ini.get("parameter", teststep.param.substring(1));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				parameter = teststep.param;
			}
			
			Pattern pattern;
			if (parts.length == 1) {
				pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
			} else {
				pattern = Pattern.compile(parts[1], Pattern.CASE_INSENSITIVE);
			}

			try { // Faire le scroll
				WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
			} catch (Exception e) {
				System.out.println("Scroll with text failed");
				try {
					WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
				} catch (Exception ex) {
					System.out.println("Scroll with content-desc failed");
					//return Fonctions.logStepKO(teststep, driver, time1, "Element not found with scroll");
				}
			}

			// Utiliser le try sauvegardé
			if (!tryProperty.isEmpty() && tryProperty != null) {
				switch (parts.length) {
					case 1:
						if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && StringUtils.countMatches(tryProperty, "@text") == 1) {
							xpath = tryProperty;
							element = findElements(driver, xpath);
							if (element != null) {
								elementfound = true;
							}
						}
						break;
					case 2:
						if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str) && StringUtils.countMatches(tryProperty, "@text") == 2) {
							xpath = tryProperty;
							element = findElements(driver, xpath);
							if (element != null) {
								elementfound = true;
							}
						}
						break;
					default:
					System.out.println("Number of properties not between 1 and 2");
					return Fonctions.logStepKO(teststep, driver, time1, "Number of properties not between 1 and 2");
				}
			}

			// Faire les try
			if (!elementfound) {
				switch (parts.length) {
					case 1:
						list = tryCheckByText(driver, parts[0]);
						break;
					case 2:
						list = tryCheckByText(driver, parts[0], parts[1]);
						break;
					default:
					System.out.println("Number of properties not between 1 and 2");
					return Fonctions.logStepKO(teststep, driver, time1, "Number of properties not between 1 and 2");
				}
			}
			if (list != null) {
				for(Map.Entry<String, WebElement> entry : list.entrySet()) {
					element = entry.getValue();
					xpath = entry.getKey();
				}
			}

			// Faire la vérification de l'élément
			if (element != null) {
				System.out.println("Check made on \n" + xpath + "\n");
				if (ignoreSpacesMajMin(element.getText()).equals(ignoreSpacesMajMin(parameter))) {
					System.out.println("<<<<<<<< Text: " + parameter + " is present. >>>>>>>>");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} else {
					System.out.println("<<<<<<<< Text: " + parameter + " is not present. >>>>>>>>");
					return Fonctions.logStepWarning(teststep, driver, time1, "Texts are not equals", xpath);
				}
			} else {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				if (tryProperty != null && !tryProperty.isEmpty()) {
					switch (parts.length) {
						case 1:
							if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str)) break;
							element= findElementsForInput(driver, tryProperty, "input");
							if(element == null) {
								element= findElementsForInput(driver, tryProperty, "textarea");
							}
							if(element == null) {
								element = findElements(driver, tryProperty);
							}
							break;
							
						case 2:
							if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || !tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str)) break;
							element= findElementsForInput(driver, tryProperty, "input");
							if(element == null) {
								element= findElementsForInput(driver, tryProperty, "textarea");
							}
							if(element == null) {
								element = findElements(driver, tryProperty);
							}
							break;
						default:
							System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
							return Fonctions.logStepKO(teststep, driver, time1, "Error at switch, number of strings from teststep is not between 1 and 3");
					}
					if(element != null) {
						xpath = tryProperty;
						elementfound = true;
					}
				}
				if (!elementfound) {
					if(parts.length == 1)
					{
						list = identifyElementCheck(driver, parts[0]);
					} else {
						list = identifyElementCheck(driver, parts[0], parts[1]);
					}
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
				}
				if (element != null) {
				System.out.println("Check made on \n" + xpath + "\n");
				if (ignoreSpacesMajMin(element.getText()).equals(ignoreSpacesMajMin(parameter)) || ignoreSpacesMajMin(element.getAttribute("value")).equals(ignoreSpacesMajMin(parameter))) {
					System.out.println("<<<<<<<< Text: " + parameter + " is present. >>>>>>>>");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} else {
					System.out.println("<<<<<<<< Text: " + parameter + " is not present. >>>>>>>>");
					return Fonctions.logStepWarning(teststep, driver, time1, "Texts are not equals", xpath);
				}
			}
				System.out.println("Element not found");
				return Fonctions.logStepKO(teststep, driver, time1, "Element not found");
			}
			
		} catch(Exception e){
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileObject_clickbyindex(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			// Définition des variables
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			String property = "";
			String[] parts = {};
			String parameter ="";
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			WebElement element = null;
			Wini ini = new Wini(new File(Config.propertyFile));

			// Récupérer la propriété si c'est une variable
			if (array_prop_object.get("texte").charAt(0) == '$') {
				try {
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");
			}
			
			parts = property.split("\\|");

			// Récupérer le paramètre si c'est une variable
			if (teststep.param.charAt(0) == '$') {
				try {
					parameter = ini.get("parameter", teststep.param.substring(1));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				parameter = teststep.param;
			}


			Pattern pattern;
			if (parts.length == 1) {
				pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
			} else {
				pattern = Pattern.compile(parts[1], Pattern.CASE_INSENSITIVE);
			}

			try { // Faire le scroll
				WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
			
			} catch (Exception e) {
				System.out.println("Scroll with text failed");
				try {
					WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
				} catch (Exception ex) {
					System.out.println("Scroll with content-desc failed");
					// return Fonctions.logStepKO(teststep, null, time1, "Element not found with scroll");
				}
			}
			if (!tryProperty.isEmpty() && tryProperty != null) {
				switch (parts.length) {
					case 1:
						if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("//following::")) {
							element = findElementsIndex(driver, tryProperty, parameter);
							if (element != null) {
								elementfound = true;
								xpath = tryProperty;
							}
						}
						break;
						
					case 2:
						if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str) && tryProperty.contains("//following::")) {
							element = findElementsIndex(driver, tryProperty, parameter);
							if (element != null) {
								elementfound = true;
								xpath = tryProperty;
							}
						}
						break;
					default:
						System.out.println("Number of properties not betwenn 1 and 2");
						return Fonctions.logStepKO(teststep, driver, time1, "Number of properties not between 1 and 2");
				}
			}

			if (!elementfound) {
				switch (parts.length) {
					case 1:
						list = tryClickByIndex(driver, parts[0], parameter);
						break;
					case 2:
						list = tryClickByIndex(driver, parts[0], parts[1], parameter);
						if (list == null) {
							try {
								WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward(2)"));
							} catch (Exception e) {}
							list = tryClickByIndex(driver, parts[0], parts[1], parameter);
						}
						break;
					default:
						System.out.println("Number of properties not between 1 and 2");
						return Fonctions.logStepKO(teststep, driver, time1, "Number of properties not between 1 and 2");
				}
			}
			if (list != null) {
				for(Map.Entry<String, WebElement> entry : list.entrySet()) {
					element = entry.getValue();
					xpath = entry.getKey();
				}
			}
			if (element != null) {
				System.out.println(">>> Click on element : \n" + xpath + "\n");
				element.click();
				return Fonctions.logStepOK(teststep, driver, time1, xpath);
			} else {
				Fonctions.switchToWebViewContext((AndroidDriver) driver);
				if (tryProperty != null && !tryProperty.isEmpty()) {
					switch (parts.length) {
						case 1:
							if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && StringUtils.countMatches(tryProperty, "parent")==0){
								element = findElementsIndex(driver, tryProperty, parameter);
								if(element != null) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
							break;
						case 2:
							if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s",	"") + str)){
								element = findElementsIndex(driver, tryProperty, parameter);
								if(element != null) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
							break;
						case 3:
							if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[2].toLowerCase().replaceAll("\\s", "") + str)){
								element = findElementsIndex(driver, tryProperty, parameter);
								if(element != null) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
							break;
						default:
							System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
							return Fonctions.logStepKO(teststep, driver, time1, "Error at switch, number of strings from teststep is not between 1 and 3");
					}
				}
				if (!elementfound) {
					switch(parts.length){
						case 1:
							list = identifyElementsToClickByIndex(driver, parts[0], parameter);
							break;
						case 2:
							if (parts[0].charAt(0) == '@'){
								list = identifyElementsToClickByIndex(driver, parts[0], parts[1], parameter);
							} else {
								list = identifyElementsToClickByIndexAvecContext(driver, parts[0], parts[1], parameter);
							}
							break;
						case 3:
							list = identifyElementsToClickByIndex(driver, parts[0], parts[1], parts[2], parameter);
							break;
						default:
							System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
							return Fonctions.logStepKO(teststep, driver, time1, "Error at switch, number of strings from teststep is not between 1 and 3");
					}
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
				}
				if(element != null) {
					Fonctions.highLighterMethod(driver, element);
					try {
						element.click();
						System.out.println("##### click made on : \n"+ element + "\n");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					} catch (Exception e) {
						System.out.println ("##### Element.click fail #####");
					}
					try {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
						System.out.println("##### click made on : \n"+ element + "\n");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					} catch (Exception e) {
						System.out.println ("##### JavascriptExecutor click fail #####");
					}
					return Fonctions.logStepKO(teststep, driver, time1, "click Fail");
				} else {
					return Fonctions.logStepKO(teststep, driver, time1, "Element is not found");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileObject_clearvaluebytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = null;
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file : " + teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			String xpath = "";
			String property;
			HashMap<String, WebElement> list = null;
			String file_params = Config.dir_params + File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty = Fonctions.getParameter(file_params, Config.compteur_instance, Config.compteur_params);
			boolean elementfound = false;

			// Récupérer la propriété dans une variable
			if (array_prop_object.get("texte").charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts = property.split("\\|");

			
			try {
				Pattern pattern;
				if (parts.length == 1) {
					pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
				} else {
					pattern = Pattern.compile(parts[1], Pattern.CASE_INSENSITIVE);
				}

				try { // Faire le scroll
					WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
				} catch (Exception e) {
					System.out.println("Scroll with text failed");
					try {
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
					} catch (Exception ex) {
						System.out.println("Scroll with content-desc failed");
						// return Fonctions.logStepKO(teststep, driver, time1, "Element not found with scroll");
					}
				}
				// Utiliser le try sauvegardé
				if (!tryProperty.isEmpty() && tryProperty != null) {
					switch (parts.length) {
						case 1:
							if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
								element = findElements(driver, tryProperty);
								xpath = tryProperty;
								if (element != null) {
									elementfound = true;
								}
							}
							break;
						case 2:
							if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
								element = findElements(driver, tryProperty);
								xpath = tryProperty;
								if (element != null) {
									elementfound = true;
								}
							}
							break;
						default:
							System.out.println("Error : not 1 or 2 fields in property.");
							return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
					}
				}

				// Faire les try
				if (!elementfound) {
					switch (parts.length) {
						case 1:
							list = trySendKeyByText(driver, parts[0]);
							break;
						case 2:
							list = trySendKeyByText(driver, parts[0], parts[1]);
							break;
						default:
							return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
					}
				}
				if (list != null) {
					for(Map.Entry<String, WebElement> entry : list.entrySet()) {
						element = entry.getValue();
						xpath = entry.getKey();
					}
				}

				// Écrire dans l'élément
				if (element != null) {
					System.out.println(">>>>> Element found using the xpath :  \n"+xpath+"\n");
					element.clear();
					System.out.println("Element cleared");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} else {
					Fonctions.switchToWebViewContext((AndroidDriver) driver);
					if (!tryProperty.isEmpty() && tryProperty != null) {
						switch (parts.length) {
							case 1:
								if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
									element = findElements(driver, tryProperty);
									xpath = tryProperty;
									if (element != null) {
										elementfound = true;
									}
								}
								break;
							case 2:
								if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
									element = findElements(driver, tryProperty);
									xpath = tryProperty;
									if (element != null) {
										elementfound = true;
									}
								}
								break;
							default:
								System.out.println("Error : not 1 or 2 fields in property.");
								return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
						}
					}
					if (!elementfound) {
						switch (parts.length) {
							case 1:
								list = identifyElementInput(driver, parts[0]);
								break;
							case 2:
								list = identifyElementInput(driver, parts[0], parts[1]);
							default:
								break;
						}
					}
					
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (element != null) {
						System.out.println(">>>>> Element found using the xpath :  \n"+xpath+"\n");
						element.clear();
						System.out.println("Element cleared");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					} 
					System.out.println("Element not found");
					return Fonctions.logStepKO(teststep, driver, time1, "Element not found");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileObject_doubleclickbytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = null;
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file : " + teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			String xpath = "";
			String property;
			HashMap<String, WebElement> list = null;
			String file_params = Config.dir_params + File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty = Fonctions.getParameter(file_params, Config.compteur_instance, Config.compteur_params);
			boolean elementfound = false;

			// Récupérer la propriété dans une variable
			if (array_prop_object.get("texte").charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts = property.split("\\|");

			try {
				Pattern pattern;
				if (parts.length == 1) {
					pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
				} else {
					pattern = Pattern.compile(parts[1], Pattern.CASE_INSENSITIVE);
				}

				try { // Faire le scroll
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
				} catch (Exception e) {
					System.out.println("Scroll with text failed");
					try {
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
					} catch (Exception ex) {
						System.out.println("Scroll with content-desc failed");
						//return Fonctions.logStepKO(teststep, driver, time1, "Element not found with scroll");
					}
				}
				// Utiliser le try sauvegardé
				if (!tryProperty.isEmpty() && tryProperty != null) {
					switch (parts.length) {
						case 1:
							if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
								element = findElements(driver, tryProperty);
								xpath = tryProperty;
								if (element != null) {
									elementfound = true;
								}
							}
							break;
						case 2:
							if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
								element = findElements(driver, tryProperty);
								xpath = tryProperty;
								if (element != null) {
									elementfound = true;
								}
							}
							break;
						default:
							System.out.println("Error : not 1 or 2 fields in property.");
							return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
					}
				}

				// Faire les try
				if (!elementfound) {
					switch (parts.length) {
						case 1:
							list = tryClickByText(driver, parts[0]);
							break;
						case 2:
							list = tryClickByText(driver, parts[0], parts[1]);
							break;
						default:
							return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
					}
				}
				if (list != null) {
					for(Map.Entry<String, WebElement> entry : list.entrySet()) {
						element = entry.getValue();
						xpath = entry.getKey();
					}
				}

				if (element != null) {
					System.out.println("Element found with xpath : \n" + xpath + "\n");
					Point source = element.getLocation();
					PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
					Sequence tap = new Sequence(finger, 1);
					tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
					tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
					tap.addAction(new Pause(finger, Duration.ofMillis(200)));
					tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					tap.addAction(new Pause(finger, Duration.ofMillis(40)));
					tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
					tap.addAction(new Pause(finger, Duration.ofMillis(200)));
					tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					AppiumDriver appiumDriver = (AppiumDriver) driver;
					appiumDriver.perform(Collections.singletonList(tap));
					System.out.println("Element double clicked");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} else {
					System.out.println("Element not found");
					return Fonctions.logStepKO(teststep, driver, time1, "Element not found");
				}	
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileObject_longpressbytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = null;
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file : " + teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			String xpath = "";
			String property;
			HashMap<String, WebElement> list = null;
			String file_params = Config.dir_params + File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty = Fonctions.getParameter(file_params, Config.compteur_instance, Config.compteur_params);
			boolean elementfound = false;

			// Récupérer la propriété dans une variable
			if (array_prop_object.get("texte").charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts = property.split("\\|");

			try {
				Pattern pattern;
				if (parts.length == 1) {
					pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);
				} else {
					pattern = Pattern.compile(parts[1], Pattern.CASE_INSENSITIVE);
				}

				try { // Faire le scroll
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
				} catch (Exception e) {
					System.out.println("Scroll with text failed");
					try {
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
					} catch (Exception ex) {
						System.out.println("Scroll with content-desc failed");
						//return Fonctions.logStepKO(teststep, driver, time1, "Element not found with scroll");
					}
				}
				// Utiliser le try sauvegardé
				if (!tryProperty.isEmpty() && tryProperty != null) {
					switch (parts.length) {
						case 1:
							if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && !tryProperty.contains("following::")) {
								element = findElements(driver, tryProperty);
								xpath = tryProperty;
								if (element != null) {
									elementfound = true;
								}
							}
							break;
						case 2:
							if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str) && tryProperty.contains(str + ignoreSpacesMajMin(parts[1]) + str)) {
								element = findElements(driver, tryProperty);
								xpath = tryProperty;
								if (element != null) {
									elementfound = true;
								}
							}
							break;
						default:
							System.out.println("Error : not 1 or 2 fields in property.");
							return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
					}
				}

				// Faire les try
				if (!elementfound) {
					switch (parts.length) {
						case 1:
							list = tryClickByText(driver, parts[0]);
							break;
						case 2:
							list = tryClickByText(driver, parts[0], parts[1]);
							break;
						default:
							return Fonctions.logStepKO(teststep, driver, time1, "Error : not 1 or 2 fields in property");
					}
				}
				if (list != null) {
					for(Map.Entry<String, WebElement> entry : list.entrySet()) {
						element = entry.getValue();
						xpath = entry.getKey();
					}
				}

				if (element != null) {
					System.out.println("Element found with xpath : \n" + xpath + "\n");
					
					Actions act = new Actions(driver);
					act.clickAndHold(element).pause(Duration.ofSeconds(1)).release().perform();
					System.out.println("Element long pressed");

					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} else {
					System.out.println("Element not found");
					return Fonctions.logStepKO(teststep, driver, time1, "Element not found");
				}	
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileObject_scrollbytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file : " + teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			String property;
			// Récupérer la propriété dans une variable
			if (array_prop_object.get("texte").charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");
			}
			Pattern pattern = Pattern.compile(property, Pattern.CASE_INSENSITIVE);

				try { // Faire le scroll
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
						System.out.println("Scroll to the text success");
						return Fonctions.logStepOK(teststep, driver, time1);
				} catch (Exception e) {
					try {
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
						System.out.println("Scroll to the text success");
						return Fonctions.logStepOK(teststep, driver, time1);
					} catch (Exception ex) {
						System.out.println("Scroll not found the element");
						return Fonctions.logStepKO(teststep, driver, time1, "Scroll not found the element");
					}
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileObject_swipeleftbytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = null;
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file : " + teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			String xpath = "";
			String property;
			HashMap<String, WebElement> list = null;
			String file_params = Config.dir_params + File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty = Fonctions.getParameter(file_params, Config.compteur_instance, Config.compteur_params);
			boolean elementfound = false;

			// Récupérer la propriété dans une variable
			if (array_prop_object.get("texte").charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts = property.split("\\|");

			try {
				Pattern pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);

				try { // Faire le scroll
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
				} catch (Exception e) {
					System.out.println("Scroll with text failed");
					try {
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
					} catch (Exception ex) {
						System.out.println("Scroll with content-desc failed");
						//return Fonctions.logStepKO(teststep, driver, time1, "Element not found with scroll");
					}
				}
				// Utiliser le try sauvegardé
				if (!tryProperty.isEmpty() && tryProperty != null) {
					if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str)) {
						element = findElements(driver, tryProperty);
						xpath = tryProperty;
						if (element != null) {
							elementfound = true;
						}
					}
				}

				if (!elementfound) {
					// faire les try
					list = trySwipe(driver, parts[0]);	
				}

				if (list != null) {
					for(Map.Entry<String, WebElement> entry : list.entrySet()) {
						element = entry.getValue();
						xpath = entry.getKey();
					}
				}

				if (element != null) {
					System.out.println("Element found with xpath : \n" + xpath );
					Actions actions = new Actions(driver);
					actions.clickAndHold(element);
					actions.moveByOffset(-500, 0).release();
					actions.build().perform();
					System.out.println("Element swiped to the left");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} else {
					Fonctions.switchToWebViewContext((AndroidDriver) driver);
					list = trySwipeHybrid(driver, parts[0]);
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}

					if (element != null) {
						System.out.println("Element found with xpath : \n" + xpath );
						Actions actions = new Actions(driver);
						actions.clickAndHold(element);
						actions.moveByOffset(-500, 0).release();
						actions.build().perform();
						System.out.println("Element swiped to the left");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					}
					System.out.println("Element not found");
					return Fonctions.logStepKO(teststep, driver, time1, "Element not found");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileObject_swiperightbytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement element = null;
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file : " + teststep.object_attach_name);
		}
		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			String xpath = "";
			String property;
			HashMap<String, WebElement> list = null;
			String file_params = Config.dir_params + File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty = Fonctions.getParameter(file_params, Config.compteur_instance, Config.compteur_params);
			boolean elementfound = false;

			// Récupérer la propriété dans une variable
			if (array_prop_object.get("texte").charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					property = ini.get("property", array_prop_object.get("texte").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the property in the variable file : " +e.getMessage());
				}
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts = property.split("\\|");

			try {
				Pattern pattern = Pattern.compile(parts[0], Pattern.CASE_INSENSITIVE);

				try { // Faire le scroll
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ str + pattern + str + "))"));
				} catch (Exception e) {
					System.out.println("Scroll with text failed");
					try {
						WebElement obj = driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains("+ str + pattern + str + "))"));
					} catch (Exception ex) {
						System.out.println("Scroll with content-desc failed");
						//return Fonctions.logStepKO(teststep, driver, time1, "Element not found with scroll");
					}
				}
				// Utiliser le try sauvegardé
				if (!tryProperty.isEmpty() && tryProperty != null) {
					if (tryProperty.contains(str + ignoreSpacesMajMin(parts[0]) + str)) {
						element = findElements(driver, tryProperty);
						xpath = tryProperty;
						if (element != null) {
							elementfound = true;
						}
					}
				}

				if (!elementfound) {
					// faire les try
					list = trySwipe(driver, parts[0]);	
				}

				if (list != null) {
					for(Map.Entry<String, WebElement> entry : list.entrySet()) {
						element = entry.getValue();
						xpath = entry.getKey();
					}
				}

				if (element != null) {
					System.out.println("Element found with xpath : \n" + xpath );
					Actions actions = new Actions(driver);
					actions.clickAndHold(element);
					actions.moveByOffset(500, 0).release();
					actions.build().perform();
					System.out.println("Element swiped to the right");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} else {
					Fonctions.switchToWebViewContext((AndroidDriver) driver);
					list = trySwipeHybrid(driver, parts[0]);
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (element != null) {
						System.out.println("Element found with xpath : \n" + xpath );
						Actions actions = new Actions(driver);
						actions.clickAndHold(element);
						actions.moveByOffset(500, 0).release();
						actions.build().perform();
						System.out.println("Element swiped to the right");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					}
					System.out.println("Element not found");
					return Fonctions.logStepKO(teststep, driver, time1, "Element not found");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

//#########################################################################################
//##################################### Action Page #######################################
//#########################################################################################

	public static boolean MobileContainer_checkinnertext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_elements));
		String parameter = "";
		String totalTextOfPage = "";
		String textOfPage = "";
		String textToAdd = "";
		List<WebElement> elementList;
		Wini ini = new Wini(new File(Config.propertyFile));

		try {
			try {
				((AndroidDriver)driver).hideKeyboard();
			}
			catch (Exception e) {
				System.out.println ("Keyboard not present");
			}
			// Récupérer le paramètre si c'est une variable
			if (teststep.param.charAt(0) == '$') {
				parameter = ini.get("parameter", teststep.param.substring(1));
			} else {
				parameter = teststep.param;
			}
			try {
				do {
					textOfPage = textToAdd;
					textToAdd = "";
					elementList = driver.findElements(By.xpath("//*"));
					for (WebElement element : elementList) {
						textToAdd += element.getText();
					}
					totalTextOfPage += textToAdd;
					scrollScreen(driver, 1);
					} while (!textOfPage.equals(textToAdd));
			} catch (Exception e) {
				System.out.println("Error with the text exctraction");
			}
			//System.out.println("Texte contenu sur la page : \n" + totalTextOfPage);
			
			if (ignoreSpacesMajMin(totalTextOfPage).contains(ignoreSpacesMajMin(parameter))) {
				System.out.println("Text : " + str + parameter + str + " found in the page");
				return Fonctions.logStepOK(teststep, driver, time1);
			} else {
				System.out.println("Text : " + str + parameter + str + " not found in the page");
				return Fonctions.logStepWarning(teststep, driver, time1, "Text not found");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

	public static boolean MobileContainer_scroll(WebDriver driver, Teststep teststep){
		
		Date time1 = new Date();
		int nb_scroll = 1;
		int percentageX = 50;
		int percentageEndY =  10;
		int percentageStartY = 95;
		
		//faire un wait de x Seconde
		 try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("fdsfs")));
		} catch (Exception e) {
			
		}
		
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
		}
		String parameter;
		if (teststep.param.charAt(0) == '$'){
			try {
				Wini ini = new Wini(new File(Config.propertyFile));
				parameter = ini.get("parameter", teststep.param.substring(1));
			} catch(Exception e) {
				System.out.println ("The parameter was not found in the variable file.");
				return Fonctions.logStepKO(teststep, driver, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
			}
		} else {
			parameter = teststep.param;
		}
		System.out.println("parameter :"+parameter);
		String[] parts= splitString(parameter);
		try {
		switch(parts.length) {
			case 1:
				nb_scroll =  Integer.parseInt(parts[0]);
				break;
			case 2:
				nb_scroll = Integer.parseInt(parts[0]);
				percentageEndY = Integer.parseInt(parts[1]);
				break;
			
			default:
				System.out.println("Error at switch, number of strings from parameter is not between 1 and 2");
			return Fonctions.logStepKO(teststep, driver, time1, "Error at switch, number of parameter is not between 1 and 2");
		
		}
		}catch(NumberFormatException e) {
			return Fonctions.logStepKO(teststep, driver, time1, "Un des paramètres n'est pas un entier");
		}
		
		if (percentageEndY>95 || percentageEndY < 0) {
			System.out.println("La taille de la zone morte doit etre comprise entre 0 et 95");
			return Fonctions.logStepKO(teststep, driver, time1, "La taille de la zone morte doit etre comprise entre 0 et 95");
		}
		
		try {
			((HidesKeyboard) driver).hideKeyboard();
		} catch (Exception e) {
			System.out.println("keyboard not displayed");
		}
		
		int heightOfScreen = driver.manage().window().getSize().getHeight();
		int widthOfScreen = driver.manage().window().getSize().getWidth();
		int centerX = (int) (widthOfScreen * percentageX/100);
		int startY = (int) (heightOfScreen * percentageStartY/100);
		int endY = (int) (heightOfScreen * percentageEndY/100);
		int i;
		for (i = 0; i < nb_scroll; i++) {
			
			try {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
				Sequence swipe = new Sequence(finger,1);
				swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
				swipe.addAction(finger.createPointerDown(0));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),PointerInput.Origin.viewport(),centerX, (int)endY));
				swipe.addAction(finger.createPointerUp(0));
				((AppiumDriver) driver).perform(Arrays.asList(swipe));
			} catch (Exception e) {
				return Fonctions.logStepKO(teststep, driver, time1, "Impossible d’effectuer le scroll : "+ e.getMessage());
			}
			
		}
		System.out.println(i+" scroll sur "+ nb_scroll + " ont été effectué");
		return Fonctions.logStepOK(teststep, driver, time1);
	}
	
	public static boolean MobileContainer_clickbycoordinates(WebDriver selenium, Teststep teststep) throws IOException {
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			try {
			WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(" ")));
			}catch(Exception e) {
				
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String nbrligneColonne, tag, temp, coord;
		String[] params;
		int click_x, click_y;
		WebElement elem;
		String parameter;
		if (teststep.param.charAt(0) == '$') {
			try {
				Wini ini = new Wini(new File(Config.propertyFile));
				parameter = ini.get("parameter", teststep.param.substring(1));
			} catch (Exception e) {
				System.out.println("The parameter was not found in the variable file.");
				return Fonctions.logStepKO(teststep, selenium, time1,
						"Cannot find the parameter in the variable file : " + e.getMessage());
			}
		} else {
			parameter = teststep.param;
		}
		// Parameter recuperation
		if (parameter.contains("|")) {
			params = parameter.split("\\|");
			int taille = params.length;
			coord = params[0];
			temp = params[1];
			if (taille == 3) {
				nbrligneColonne = params[1]; // Case Coordinates + Table Size + tag
				tag = params[2];
				if (tag.contains("@")) {
					String tagAttribute[] = tag.split("=");
					String value = tagAttribute[1];
					String att = tagAttribute[0];
					// System.out.println("Recherche xpath = " + "//*[" + att + "='" + value +
					// "']");
					 try {
						WebDriverWait wait = new WebDriverWait(selenium, Duration.ofSeconds(10));
					    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[" + att + "='" + value + "']")));
						elem = driverInstance.findElement(By.xpath("//*[" + att + "='" + value + "']"));
					} catch (Exception e) {
						return Fonctions.logStepKO(teststep, selenium, time1, "Xpath not found : " + e.getMessage());
					}
				} else {
					// System.out.println("Recherche xpath = " + "//" + tag);
					try {
						elem = driverInstance.findElement(By.xpath("//" + tag));
					} catch (Exception e) {
						return Fonctions.logStepKO(teststep, selenium, time1, "Xpath not found : " + e.getMessage());
					}

				}
				try {
					Dimension canvas_dimensions = elem.getSize();
					String tabCoord[] = coord.split(":");
					int stepx = Integer.parseInt(tabCoord[0]);
					int stepy = Integer.parseInt(tabCoord[1]);
					String tabLineCol[] = nbrligneColonne.split(":");
					int nbLines = Integer.parseInt(tabLineCol[0]);
					int nbCollumns = Integer.parseInt(tabLineCol[1]);
					int xMoveOriginToPoint = canvas_dimensions.getWidth() / nbCollumns;
					int yMoveOriginToPoint = canvas_dimensions.getHeight() / nbLines;
					if (stepx > nbLines || stepx < 1 || stepy > nbCollumns || stepy < 1)
						return Fonctions.logStepWarning(teststep, selenium, time1,
								"Erreur coordonnées inacessible dans ce quadrillage de " + nbLines + " lignes et "
										+ nbCollumns + " colonnes ");
					
					click_x = elem.getLocation().getX() + xMoveOriginToPoint * stepx - xMoveOriginToPoint / 2;
					click_y = elem.getLocation().getY() + yMoveOriginToPoint * stepy - yMoveOriginToPoint / 2;
				} catch (Exception e) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Error when trying to build canva : " + e.getMessage());

				}

			} else {
				if (temp.contains(":") && (!temp.contains("@"))) {
					try {
					nbrligneColonne = params[1]; // Case Coordinates + Table size
					Dimension canvas_dimensions = selenium.manage().window().getSize();
					String tabCoord[] = coord.split(":");
					int stepx = Integer.parseInt(tabCoord[0]);
					int stepy = Integer.parseInt(tabCoord[1]);
					String tabLineCol[] = nbrligneColonne.split(":");
					int nbLines = Integer.parseInt(tabLineCol[0]);
					int nbCollumns = Integer.parseInt(tabLineCol[1]);
					if (stepx > nbLines || stepx < 1 || stepy > nbCollumns || stepy < 1)
						return Fonctions.logStepWarning(teststep, selenium, time1,
								"Erreur coordonnées inacessible dans ce quadrillage de " + nbLines + " lignes et "
										+ nbCollumns + " colonnes ");
					int xMoveOriginToPoint = canvas_dimensions.getWidth() / nbCollumns;
					int yMoveOriginToPoint = canvas_dimensions.getHeight() / nbLines;
					click_x = xMoveOriginToPoint * stepx - xMoveOriginToPoint / 2;
					click_y = yMoveOriginToPoint * stepy - yMoveOriginToPoint / 2;
				} catch (Exception e) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Error when trying to build canva : " + e.getMessage());
				}
				} else {
					tag = params[1]; // Case Coordinates + tag
					if (tag.contains("@")) {
						String tabAttribute[] = tag.split("=");
						String att = tabAttribute[0];
						String value = tabAttribute[1];
						// System.out.println("Recherche xpath = " + "//*[" + attr + "='" + value +
						// "']");
						 try {
								WebDriverWait wait = new WebDriverWait(selenium, Duration.ofSeconds(10));
							    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[" + att + "='" + value + "']")));
							} catch (Exception e) {
							}
						elem = driverInstance.findElement(By.xpath("//*[" + att + "='" + value + "']"));
					} else {
						elem = driverInstance.findElement(By.xpath("//" + tag));
						// System.out.println("Recherche xpath : " + "//" + tag);
					}
					try {
					Dimension canvas_dimensions = elem.getSize();
					String tabCoord[] = coord.split(":");
					int stepx = Integer.parseInt(tabCoord[0]);
					int stepy = Integer.parseInt(tabCoord[1]);
					if (stepx > 10 || stepx < 1 || stepy > 10 || stepy < 1)
						return Fonctions.logStepWarning(teststep, selenium, time1,
								"Erreur coordonnées inacessible dans ce quadrillage de 10 lignes et 10 colonnes ");
					int xMoveOriginToPoint = canvas_dimensions.getWidth() / 10;
					int yMoveOriginToPoint = canvas_dimensions.getHeight() / 10;
					click_x = elem.getLocation().getX() + xMoveOriginToPoint * stepx - xMoveOriginToPoint / 2;
					click_y = elem.getLocation().getY() + yMoveOriginToPoint * stepy - yMoveOriginToPoint / 2;
					} catch (Exception e) {
						return Fonctions.logStepKO(teststep, selenium, time1, "Error when trying to build canva : " + e.getMessage());

					}					

				}
			}
		} else {
			try {
			coord = parameter; // Case Coordinate
			Dimension canvas_dimensions = selenium.manage().window().getSize();
			String tabCoord[] = coord.split(":");
			int stepx = Integer.parseInt(tabCoord[0]);
			int stepy = Integer.parseInt(tabCoord[1]);
			if (stepx > 10 || stepx < 1 || stepy > 10 || stepy < 1)
				return Fonctions.logStepWarning(teststep, selenium, time1,
						"Erreur coordonnées inacessible dans ce quadrillage de 10 lignes et 10 colonnes ");
			int xMoveOriginToPoint = canvas_dimensions.getWidth() / 10;
			int yMoveOriginToPoint = canvas_dimensions.getHeight() / 10;
			click_x = xMoveOriginToPoint * stepx - xMoveOriginToPoint / 2;
			click_y = yMoveOriginToPoint * stepy - yMoveOriginToPoint / 2;
			} catch (Exception e) {
				return Fonctions.logStepKO(teststep, selenium, time1, "Error when trying to build canva : " + e.getMessage());

			}

		}
		
		int res1 = click_x;
		int res2 = click_y;
		//System.out.println("elem position X: " + test.getX() + " Y: " + test.getY());

		//essayer de montrer où on clique

        try {
			Point point = new Point(res1,res2);

			// Créer une action de toucher à ces coordonnées
			PointerInput touchAction = new PointerInput(PointerInput.Kind.TOUCH, "touch");
			Sequence sequence = new Sequence(touchAction, 0);
			sequence.addAction(touchAction.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), point.x, point.y));
			sequence.addAction(touchAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			sequence.addAction(touchAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			// Effectuer l'action de toucher
			((AppiumDriver) selenium).perform(Arrays.asList(sequence));
		} catch (Exception e) {
			System.out.println("Cannot perform on coordinate ("+res1 +" "+res2+"):"  + e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform on coordinate ("+res1 +" "+res2+"):"  + e.getMessage());
		}
     
		System.out.println("click on coordonnée "+res1 +" "+res2);      
		return Fonctions.logStepOK(teststep, selenium, time1);
	}
//#########################################################################################
//##################################### List Try ##########################################
//#########################################################################################

	public static HashMap tryClickByText(WebDriver driver, String property){
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		property = ignoreSpacesMajMin(property);
		String[] listObj = {"Button", "ImageButton", "ImageView", "Spinner", "CheckedTextView"};
		try {
			// Elements
			for (int i = 0; i < listObj.length; i++) {
				xpath = "//android.widget." + listObj[i] + "[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			// Elements contains
			for (int i = 0; i < listObj.length; i++) {
				xpath = "//android.widget." + listObj[i] + "[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			// All
			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			// All contains
			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			// IOS try
			String[] listObjIOS = {"XCUIElementTypeButton","XCUIElementTypeStaticText", "XCUIElementTypeTextField"};
			for (int i = 0; i < listObjIOS.length; i++) {
				xpath = "//" + listObjIOS[i] + "[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			for (int i = 0; i < listObjIOS.length; i++) {
				xpath = "//" + listObjIOS[i] + "[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			xpath = "//*[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap tryClickByText(WebDriver driver, String property, String context) {
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		property = ignoreSpacesMajMin(property);
		context = ignoreSpacesMajMin(context);
		String[] listObj = {"Button", "ImageButton", "ImageView", "Spinner", "CheckedTextView"};

		try {
			// Elements
			for (int i = 0; i < listObj.length; i++) {
				xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str +"]//following::android.widget." + listObj[i] + "[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			// Elements contains
			for (int i = 0; i < listObj.length; i++) {
				xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str +")]//following::android.widget." + listObj[i] + "[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			// All
			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str +"]//following::*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			// All contains
			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str +")]//following::*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			// IOS try
			String[] listObjIOS = {"XCUIElementTypeButton","XCUIElementTypeStaticText", "XCUIElementTypeTextField"};
			for (int i = 0; i < listObjIOS.length; i++) {
				xpath = "//*[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//following::" + listObjIOS[i] + "[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			for (int i = 0; i < listObjIOS.length; i++) {
				xpath = "//*[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//following::" + listObjIOS[i] + "[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			xpath = "//*[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//following::*[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//following::*[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap trySendKeyByText(WebDriver driver, String property) {
		String xpath;
		WebElement element;
		property = ignoreSpacesMajMin(property);
		HashMap<String, WebElement> list = new HashMap<>();
		String[] listobj = {"EditText","AutoCompleteTextView"};

		try {
			// Elements
			for (int i = 0; i < listobj.length; i++) {
				xpath = "//android.widget." + listobj[i] + "[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			// Elements contains
			for (int j = 0; j < listobj.length; j++) {
				xpath = "//android.widget." + listobj[j] + "[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			//IOS TRYS
			String[] IOSlist = {"XCUIElementTypeTextField"};
			for (int i = 0; i < IOSlist.length; i++) {
				xpath = "//" + IOSlist[i] + "[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			for (int i = 0; i < IOSlist.length; i++) {
				xpath = "//" + IOSlist[i] + "[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}
			
		} catch (Exception e) {}
		return null;
	}

	public static HashMap trySendKeyByText(WebDriver driver, String property, String context) {
		String xpath;
		WebElement element;
		property = ignoreSpacesMajMin(property);
		context = ignoreSpacesMajMin(context);
		HashMap<String, WebElement> list = new HashMap<>();
		String[] listobj = {"EditText","AutoCompleteTextView"};

		try {
			// Following
			for (int i = 0; i < listobj.length; i++) {
				xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//following::android.widget." + listobj[i] + "[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			// Following contains
			for (int j = 0; j < listobj.length; j++) {
				xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//following::android.widget." + listobj[j] + "[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}
		} catch (Exception e) {}
		
		return null;
	}

	public static HashMap trySelectByText(WebDriver driver, String property) {
		String xpath;
		WebElement element;
		property = ignoreSpacesMajMin(property);
		HashMap<String, WebElement> list = new HashMap<>();

		try {
			// text
			xpath = "//*[local-name()='android.widget.CheckBox' or local-name()='android.widget.Switch' or local-name()='android.widget.RadioButton' or local-name()='com.google.android.material.chip.Chip'][translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			// contain
			xpath = "//*[local-name()='android.widget.CheckBox' or local-name()='android.widget.Switch' or local-name()='android.widget.RadioButton' or local-name()='com.google.android.material.chip.Chip'][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			//TRY IOS

			String[] IOSlist = {"XCUIElementTypeRadioButton", "XCUIElementTypeRadioButton", "XCUIElementTypeSwitch"};
			for (int i = 0; i < IOSlist.length; i++) {
				xpath = "//" + IOSlist[i] + "[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			for (int i = 0; i < IOSlist.length; i++) {
				xpath = "//" + IOSlist[i] + "[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap trySelectByText(WebDriver driver, String property, String context) {
		String xpath;
		WebElement element;
		property = ignoreSpacesMajMin(property);
		context = ignoreSpacesMajMin(context);
		HashMap<String, WebElement> list = new HashMap<>();

		try {
			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//following::*[local-name()='android.widget.CheckBox' or local-name()='android.widget.Switch' or local-name()='android.widget.RadioButton' or local-name()='com.google.android.material.chip.Chip'][translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//following::*[local-name()='android.widget.CheckBox' or local-name()='android.widget.Switch' or local-name()='android.widget.RadioButton' or local-name()='com.google.android.material.chip.Chip'][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap trySelectByTextByIndex(WebDriver driver, String property, String index) {
		HashMap<String, WebElement> list = new HashMap<>();
		String xpath;
		WebElement element;
		property = ignoreSpacesMajMin(property);

		try {
			xpath = "//*[local-name()='android.widget.CheckBox' or local-name()='android.widget.Switch' or local-name()='android.widget.RadioButton' or local-name()='com.google.android.material.chip.Chip'][translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[local-name()='android.widget.CheckBox' or local-name()='android.widget.Switch' or local-name()='android.widget.RadioButton' or local-name()='com.google.android.material.chip.Chip'][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap trySelectByTextByIndex(WebDriver driver, String property, String context, String index) {
		HashMap list = new HashMap<>();
		String xpath;
		WebElement element;
		property = ignoreSpacesMajMin(property);
		context = ignoreSpacesMajMin(context);

		try {
			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//following::*[local-name()='android.widget.CheckBox' or local-name()='android.widget.Switch' or local-name()='android.widget.RadioButton' or local-name()='com.google.android.material.chip.Chip'][translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//following::*[local-name()='android.widget.CheckBox' or local-name()='android.widget.Switch' or local-name()='android.widget.RadioButton' or local-name()='com.google.android.material.chip.Chip'][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	public static HashMap tryCheckByText(WebDriver driver, String property) {
		WebElement element;
		String xpath;
		property = ignoreSpacesMajMin(property);
		HashMap list = new HashMap<>();

		try {
			xpath = "//*[local-name()='android.widget.EditText' or local-name()='android.widget.AutoCompleteTextView'][translate(@text,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str +"]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[local-name()='android.widget.EditText' or local-name()='android.widget.AutoCompleteTextView'][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str +")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str +" or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]//following::*[local-name()='android.widget.EditText' or local-name()='android.widget.AutoCompleteTextView']";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ")]//following::*[local-name()='android.widget.EditText' or local-name()='android.widget.AutoCompleteTextView']";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			//IOS TRYS
			String[] IOSlist = {"XCUIElementTypeTextField"};
			for (int i = 0; i < IOSlist.length; i++) {
				xpath = "//" + IOSlist[i] + "[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			for (int i = 0; i < IOSlist.length; i++) {
				xpath = "//" + IOSlist[i] + "[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElements(driver, xpath);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			xpath = "//*[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap tryCheckByText(WebDriver driver, String property, String context) {
		HashMap list = new HashMap<>();
		WebElement element;
		String xpath;
		property = ignoreSpacesMajMin(property);
		context = ignoreSpacesMajMin(context);

		try {
			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + "]//following::*[local-name()='android.widget.EditText' or local-name()='andrdoid.widget.AutoCompleteTextView'][translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str +"]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + context + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + context + str + ")]//following::*[local-name()='android.widget.EditText' or local-name()='android.widget.AutoCompleteTextView'][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str +")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap tryClickByIndex(WebDriver driver, String property, String index) {
		HashMap list = new HashMap<>();
		WebElement element;
		String xpath;
		property = ignoreSpacesMajMin(property);

		try {
			xpath = "//*[local-name()='android.widget.Button' or local-name()='android.widget.ImageButton' or local-name()='android.widget.ImageView' or local-name()='android.widget.Spinner' or local-name()='android.widget.CheckedTextView'][translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" +  str + property + str +" or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[local-name()='android.widget.Button' or local-name()='android.widget.ImageButton' or local-name()='android.widget.ImageView' or local-name()='android.widget.Spinner' or local-name()='android.widget.CheckedTextView'][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str  + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
			// IOS try
			String[] listObjIOS = {"XCUIElementTypeButton","XCUIElementTypeStaticText", "XCUIElementTypeTextField"};
			for (int i = 0; i < listObjIOS.length; i++) {
				xpath = "//" + listObjIOS[i] + "[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
				element = findElementsIndex(driver, xpath, index);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			for (int i = 0; i < listObjIOS.length; i++) {
				xpath = "//" + listObjIOS[i] + "[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
				element = findElementsIndex(driver, xpath, index);
				if (element != null) {
					list.put(xpath, element);
					return list;
				}
			}

			xpath = "//*[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap tryClickByIndex(WebDriver driver, String property, String context, String index) {
		HashMap list = new HashMap<>();
		WebElement element;
		String xpath;
		property = ignoreSpacesMajMin(property);
		context = ignoreSpacesMajMin(context);
		
		try {
			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + "]//following::*[local-name()='android.widget.Button' or local-name()='android.widget.ImageButton' or local-name()='android.widget.ImageView' or local-name()='android.widget.Spinner' or local-name()='android.widget.CheckedTextView'][translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str +  context + str  + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + context + str + ")]//following::*[local-name()='android.widget.Button' or local-name()='android.widget.ImageButton' or local-name()='android.widget.ImageView' or local-name()='android.widget.Spinner' or local-name()='android.widget.CheckedTextView'][contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + "]//following::*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + context + str + " ) or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + context + str + ")]//following::*[contains(translate(@text, 'ABCDEFGHIJkLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static HashMap trySwipe(WebDriver driver, String property) {
		HashMap list = new HashMap<>();
		WebElement element;
		String xpath;
		property = ignoreSpacesMajMin(property);

		try {
			xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + " or translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			//TRY IOS
			xpath = "//*[translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "or translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[contains(translate(@label, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")or contains(translate(@name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {}
		return list;
	}

	public static HashMap trySwipeHybrid(WebDriver driver, String label) {
		HashMap list = new HashMap<>();
		WebElement element;
		String xpath;
		label = ignoreSpacesMajMin(label);

		try {
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}

			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) {
				list.put(xpath, element);
				return list;
			}
		} catch (Exception e) {}
		return list;
	}

	//#########################################################################################
//##################################### Other functions ###################################
//#########################################################################################

	public static WebElement findElements(WebDriver driver, String xpath)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list = driver.findElements(By.xpath(xpath));
			if (!list.isEmpty()){
				WebElement element = getElementRelative(driver, list);
				if(element != null)
				{
					return element;
				}
			}
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}catch(Exception e){
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement findElementsIndex(WebDriver driver, String xpath, String param)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				WebElement element = getElementRelativeByIndex(driver, list_elements, Integer.parseInt(param));
				if(element != null)
				{
					return element;
				}
			}
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}catch(Exception e){
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement getElementRelative(final WebDriver driver, List<WebElement> elements_list)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement element_to_return = null;
		for(WebElement element: elements_list) {
			try {
			//wait.until(ExpectedConditions.visibilityOf(element));
			boolean status = true;//element.isDisplayed();
			element_to_return=element;
			if(status)
			{break;}
			}catch(Exception e){
				System.out.println(">>>>> Element not visible : \n "+element+"\n");
			}
		}
		return element_to_return;
	}

	public static WebElement getElementRelativeByIndex(final WebDriver driver, List<WebElement> elements_list, int index)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement element_to_return = null;
		int i = 1;
		if (elements_list.size() >= index) {
			for(WebElement element: elements_list) {
				try {
				//wait.until(ExpectedConditions.visibilityOf(element));
			boolean status = true;//element.isDisplayed();
					if(status)
					{
						if(i == index)
						{
							element_to_return=element;
							System.out.println(">>> Element with index "+i+"/"+elements_list.size()+" found <<<");
							break;
						}
						i++;
					}
				}catch(Exception e){
					System.out.println("		>>> Element not visible : \n "+element+"\n");
				}
			}
		} else {System.out.println("The number of element found is smaller than the index in parameter : "+index);}
		return element_to_return;
	}

	public static String ignoreSpacesMajMin(String property) {
		return property.toLowerCase().replaceAll("\\s", "");
	}

	public static void scrollScreen(WebDriver driver, int nb_scroll){
		
		AppiumDriver appiumDriver = (AppiumDriver) driver;
		((HidesKeyboard) driver).hideKeyboard();
		int heightOfScreen = driver.manage().window().getSize().getHeight();
		int widthOfScreen = driver.manage().window().getSize().getWidth();
		int centerX = (int) (widthOfScreen * 0.5);
		int startY = (int) (heightOfScreen * 0.85);
		int endY = (int) (heightOfScreen * 0.15);
		for (int i = 0; i < nb_scroll; i++) {
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
			Sequence swipe = new Sequence(finger,1);
			swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
			swipe.addAction(finger.createPointerDown(0));
			swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),PointerInput.Origin.viewport(),centerX, (int)endY));
			swipe.addAction(finger.createPointerUp(0));
			appiumDriver.perform(Arrays.asList(swipe));
		}
	}
	
	// #######################################
	// ############ SearchElement ############
	// #######################################
	
	public static WebElement findElementsCell(WebDriver driver, String xpath, WebElement table_element)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list = table_element.findElements(By.xpath(xpath));
			if (!list.isEmpty()){
				WebElement element = getElementRelative(driver, list);
				if(element != null)
				{
					return element;
				}
			}
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}catch(Exception e){
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement findElementsCellIndex(WebDriver driver, String xpath, WebElement table_element, String param)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list = table_element.findElements(By.xpath(xpath));
			if (!list.isEmpty()){
				WebElement element = getElementRelativeByIndex(driver, list, Integer.parseInt(param));
				if(element != null)
				{
					return element;
				}
			}
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}catch(Exception e){
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement findElementsLabelDirection(WebDriver driver, WebElement reference_element, String xpath, String direction)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				System.out.println(">>>>> Element exist on page : \n"+xpath);
				WebElement element = getDirectionElement(driver, reference_element, xpath, direction);
				if(element != null)
				{
					return element;
				}
			} else {
				System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
			}
		}catch(Exception e){
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement findElementsForInput(WebDriver driver, String xpath, String tag)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				System.out.println(">>>>> Element found to do the for request with xpath : \n"+xpath+"\n");
				WebElement element = getElementRelative(driver, list_elements);
				String attribute = element.getAttribute("for");
				if(attribute!=null)
				{
					xpath = "//" + tag + "[@name=" + str + attribute + str + " or @id=" + str + attribute + str + "]";
					list_elements = driver.findElements(By.xpath(xpath));
					if(!list_elements.isEmpty())
					{
						element = getElementRelative(driver, list_elements);
						if(element != null)
						{
							return element;
						}
					}
				}
				System.out.println(">>>>> Element not found using attribute for \n");
			} else {
				System.out.println(">>>>> Element with for not found using the xpath : \n"+xpath+"\n");
			}
		}catch(Exception e){
			System.out.println(">>>>> Element with for not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement findElementsForInputByIndex(WebDriver driver, String xpath, String tag, String index)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				System.out.println(">>>>> Element found to do the for request with xpath : \n"+xpath+"\n");
				WebElement element = getElementRelativeByIndex(driver, list_elements, Integer.parseInt(index));
				String attribute = element.getAttribute("for");
				if(attribute!=null)
				{
					xpath = "//" + tag + "[@name=" + str + attribute + str + " or @id=" + str + attribute + str + "]";
					list_elements = driver.findElements(By.xpath(xpath));
					if(!list_elements.isEmpty())
					{
						element = getElementRelative(driver, list_elements);
						if(element != null)
						{
							return element;
						}
					}
				}
				System.out.println(">>>>> Element not found using attribute for \n");
			} else {
				System.out.println(">>>>> Element with for not found using the xpath : \n"+xpath+"\n");
			}
		}catch(Exception e){
			System.out.println(">>>>> Element with for not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement findElementsForSelect(WebDriver driver, String xpath, String tag)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				System.out.println(">>>>> Element found to do the for request with xpath : \n"+xpath+"\n");
				WebElement element = getElementRelative(driver, list_elements);
				String attribute = element.getAttribute("for");
				if(attribute!=null)
				{
					xpath = "//input[@type='" + tag + "' and (@name=" + str + attribute + str + " or @id=" + str + attribute + str + ")]";
					list_elements = driver.findElements(By.xpath(xpath));
					if(!list_elements.isEmpty())
					{
						element = getElementRelative(driver, list_elements);
						if(element != null)
						{
							return element;
						}
					}
				}
				System.out.println(">>>>> Element not found using attribute for \n");
			} else {
				System.out.println(">>>>> Element with for not found using the xpath : \n"+xpath+"\n");
			}
		}catch(Exception e){
			System.out.println(">>>>> Element with for not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement findElementsForSelectWithIndex(WebDriver driver, String xpath, String tag, String index)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				System.out.println(">>>>> Element found to do the for request with xpath : \n"+xpath+"\n");
				WebElement element = getElementRelative(driver, list_elements);
				String attribute = element.getAttribute("for");
				if(attribute!=null)
				{
					xpath = "//input[@type='" + tag + "' and (@name=" + str + attribute + str + " or @id=" + str + attribute + str + ")]";
					list_elements = driver.findElements(By.xpath(xpath));
					if(!list_elements.isEmpty())
					{
						element = getElementRelativeByIndex(driver, list_elements, Integer.parseInt(index));
						if(element != null)
						{
							return element;
						}
					}
				}
				System.out.println(">>>>> Element not found using attribute for \n");
			} else {
				System.out.println(">>>>> Element with for not found using the xpath : \n"+xpath+"\n");
			}
		}catch(Exception e){
			System.out.println(">>>>> Element with for not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}
	
	public static WebElement findElementsTable(WebDriver driver, String xpath, String[] param, String typeAction)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_try));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
			List<WebElement> list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				WebElement table_element = getElementRelative(driver, list_elements);
				if(table_element != null)
				{
					System.out.println(">>>>> Table found with xpath : \n"+xpath+"\n");
					int colIndex = getColumnIndex(driver, table_element, param);
					int rowIndex = getRowIndex(driver, table_element, param);
					WebElement element = null;
					switch(typeAction) {
						case "click":
							switch(param.length) {
								case 2:
									element = identifyCellElementToClick(driver, table_element, colIndex, rowIndex);
									break;
								case 3:
									if(param[2].charAt(0) == '[' && param[2].charAt(param[2].length() - 1) == ']') {
										String parameter = param[2].substring(1, param[2].length() - 1);
										element = identifyCellElementToClickIndex(driver, table_element, colIndex, rowIndex, parameter);
									} else {
										element = identifyCellElementToClick(driver, table_element, colIndex, rowIndex, param[2]);
									}
									break;
								default:
									System.out.println("Error at switch, number of strings from teststep is not between 2 and 3");
									return null;
							}
							break;
						case "check":
							element = identifyCellElementToCheck(driver, table_element, colIndex, rowIndex);
							break;
						case "sendkeys":
							element = identifyCellElementToSendkeys(driver, table_element, colIndex, rowIndex);
							break;
						default:
							System.out.println("Error at switch, case "+typeAction+" not supported");
							return null;
					}
					return element;
					
				}
			}
			System.out.println(">>>>> Table element not found using the xpath :  \n"+xpath+"\n");
		}catch(Exception e){
			System.out.println(">>>>> Table Element not found using the xpath :  \n"+xpath+"\n");
		}
		return null;
	}

	public static WebElement findShadowElements(WebDriver driver, SearchContext shadowroot, String selector)
	{
		try {
			List<WebElement> list = shadowroot.findElements(By.cssSelector(selector));
			
			if (!list.isEmpty()){
				WebElement element = getElementRelative(driver, list);
				if(element != null)
				{
					return element;
				}
			}
			List<WebElement> hostlist = shadowroot.findElements(By.cssSelector("*"));

			for(WebElement shadowHost: hostlist) {
				try {
					SearchContext shadowRoot = shadowHost.getShadowRoot();
					WebElement shadowElement = findShadowElements(driver, shadowRoot, selector);
					if (shadowElement != null) return shadowElement;
				}catch(Exception e){
				}
			}
		}catch(Exception e){
			System.out.println(e);

		}
		return null;
	}

	// ######################################
	// ############## List Try ##############
	// ######################################
	
	public static HashMap identifyElementToClick(WebDriver driver, String label, String context)
	{
		String xpath;
		WebElement element;
		String selector;
		HashMap<String, WebElement> list = new HashMap<>();
		String label2 = label;
		label = label.toLowerCase().replaceAll("\\s","");
		String context2 = context;
		context = context.toLowerCase().replaceAll("\\s","");
		try {
			// lien follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			// bouton follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::input[(@type='submit' or @type='button') and (translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			// img follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::img[translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// lien parent follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// bouton parent follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// input parent follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input[@type='submit' or @type='button']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// img parent follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//lien contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// bouton contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// input contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::input[(@type='submit' or @type='button') and (text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + "))]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// img contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//lien parent contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// bouton parent contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// input parent contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input[@type='submit' or @type='button']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// img parent contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// lien follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			// bouton follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//input[(@type='submit' or @type='button') and (translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			// img follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//img[translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// lien parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// bouton parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// input parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input[@type='submit' or @type='button']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// img parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//lien contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// bouton contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// input contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//input[(@type='submit' or @type='button') and (text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + "))]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// img contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//lien parent contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// bouton parent contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// input parent contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input[@type='submit' or @type='button']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// img parent contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// all follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// all follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// all contains follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			// all contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			
			System.out.println("\n ########## try in shadow root ########## \n");


			boolean shadowStatus = checkShadowElement(driver);
			if (shadowStatus) {
				//shadow a
				selector = "[value=" + str + context2 + str + "] a[value=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] a[value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] a[value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] a[value=" + str + label2 + str + "], [value=" + str + context2 + str + "] a[title=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[title=" + str + label2 + str + "], [alt=" + str + context2 + str + "] a[title=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] a[title=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] a[title=" + str + label2 + str + "], [value=" + str + context2 + str + "] a[aria-label=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[aria-label=" + str + label2 + str + "], [alt=" + str + context2 + str + "] a[aria-label=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] a[aria-label=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] a[aria-label=" + str + label2 + str + "], [value=" + str + context2 + str + "] a[alt=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[alt=" + str + label2 + str + "], [alt=" + str + context2 + str + "] a[alt=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] a[alt=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] a[alt=" + str + label2 + str + "], [value=" + str + context2 + str + "] a[ng-reflect-name=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[ng-reflect-name=" + str + label2 + str + "], [alt=" + str + context2 + str + "] a[ng-reflect-name=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] a[ng-reflect-name=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] a[ng-reflect-name=" + str + label2 + str + "], [value=" + str + context2 + str + "] a[ng-reflect-value=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[ng-reflect-value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] a[ng-reflect-value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] a[ng-reflect-value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] a[ng-reflect-value=" + str + label2 + str + "], [value=" + str + context2 + str + "] a[ng-reflect-message=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[ng-reflect-message=" + str + label2 + str + "], [alt=" + str + context2 + str + "] a[ng-reflect-message=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] a[ng-reflect-message=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] a[ng-reflect-message=" + str + label2 + str + "], [value=" + str + context2 + str + "] a[svgicon=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[svgicon=" + str + label2 + str + "], [alt=" + str + context2 + str + "] a[svgicon=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] a[svgicon=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] a[svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow button
				selector = "[value=" + str + context2 + str + "] button[value=" + str + label2 + str + "], [title=" + str + context2 + str + "] button[value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] button[value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] button[value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] button[value=" + str + label2 + str + "], [value=" + str + context2 + str + "] button[title=" + str + label2 + str + "], [title=" + str + context2 + str + "] button[title=" + str + label2 + str + "], [alt=" + str + context2 + str + "] button[title=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] button[title=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] button[title=" + str + label2 + str + "], [value=" + str + context2 + str + "] button[aria-label=" + str + label2 + str + "], [title=" + str + context2 + str + "] button[aria-label=" + str + label2 + str + "], [alt=" + str + context2 + str + "] button[aria-label=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] button[aria-label=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] button[aria-label=" + str + label2 + str + "], [value=" + str + context2 + str + "] button[alt=" + str + label2 + str + "], [title=" + str + context2 + str + "] button[alt=" + str + label2 + str + "], [alt=" + str + context2 + str + "] button[alt=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] button[alt=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] button[alt=" + str + label2 + str + "], [value=" + str + context2 + str + "] button[ng-reflect-name=" + str + label2 + str + "], [title=" + str + context2 + str + "] button[ng-reflect-name=" + str + label2 + str + "], [alt=" + str + context2 + str + "] button[ng-reflect-name=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] button[ng-reflect-name=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] button[ng-reflect-name=" + str + label2 + str + "], [value=" + str + context2 + str + "] button[ng-reflect-value=" + str + label2 + str + "], [title=" + str + context2 + str + "] button[ng-reflect-value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] button[ng-reflect-value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] button[ng-reflect-value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] button[ng-reflect-value=" + str + label2 + str + "], [value=" + str + context2 + str + "] button[ng-reflect-message=" + str + label2 + str + "], [title=" + str + context2 + str + "] button[ng-reflect-message=" + str + label2 + str + "], [alt=" + str + context2 + str + "] button[ng-reflect-message=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] button[ng-reflect-message=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] button[ng-reflect-message=" + str + label2 + str + "], [value=" + str + context2 + str + "] button[svgicon=" + str + label2 + str + "], [title=" + str + context2 + str + "] button[svgicon=" + str + label2 + str + "], [alt=" + str + context2 + str + "] button[svgicon=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] button[svgicon=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] button[svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow input
				selector = "[value=" + str + context2 + str + "] input[value=" + str + label2 + str + "], [title=" + str + context2 + str + "] input[value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] input[value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] input[value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] input[value=" + str + label2 + str + "], [value=" + str + context2 + str + "] input[title=" + str + label2 + str + "], [title=" + str + context2 + str + "] input[title=" + str + label2 + str + "], [alt=" + str + context2 + str + "] input[title=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] input[title=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] input[title=" + str + label2 + str + "], [value=" + str + context2 + str + "] input[aria-label=" + str + label2 + str + "], [title=" + str + context2 + str + "] input[aria-label=" + str + label2 + str + "], [alt=" + str + context2 + str + "] input[aria-label=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] input[aria-label=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] input[aria-label=" + str + label2 + str + "], [value=" + str + context2 + str + "] input[alt=" + str + label2 + str + "], [title=" + str + context2 + str + "] input[alt=" + str + label2 + str + "], [alt=" + str + context2 + str + "] input[alt=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] input[alt=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] input[alt=" + str + label2 + str + "], [value=" + str + context2 + str + "] input[ng-reflect-name=" + str + label2 + str + "], [title=" + str + context2 + str + "] input[ng-reflect-name=" + str + label2 + str + "], [alt=" + str + context2 + str + "] input[ng-reflect-name=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] input[ng-reflect-name=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] input[ng-reflect-name=" + str + label2 + str + "], [value=" + str + context2 + str + "] input[ng-reflect-value=" + str + label2 + str + "], [title=" + str + context2 + str + "] input[ng-reflect-value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] input[ng-reflect-value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] input[ng-reflect-value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] input[ng-reflect-value=" + str + label2 + str + "], [value=" + str + context2 + str + "] input[ng-reflect-message=" + str + label2 + str + "], [title=" + str + context2 + str + "] input[ng-reflect-message=" + str + label2 + str + "], [alt=" + str + context2 + str + "] input[ng-reflect-message=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] input[ng-reflect-message=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] input[ng-reflect-message=" + str + label2 + str + "], [value=" + str + context2 + str + "] input[svgicon=" + str + label2 + str + "], [title=" + str + context2 + str + "] input[svgicon=" + str + label2 + str + "], [alt=" + str + context2 + str + "] input[svgicon=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] input[svgicon=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] input[svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow img
				selector = "[value=" + str + context2 + str + "] img[value=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] img[value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] img[value=" + str + label2 + str + "], [value=" + str + context2 + str + "] img[title=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[title=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[title=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] img[title=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] img[title=" + str + label2 + str + "], [value=" + str + context2 + str + "] img[aria-label=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[aria-label=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[aria-label=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] img[aria-label=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] img[aria-label=" + str + label2 + str + "], [value=" + str + context2 + str + "] img[alt=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[alt=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[alt=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] img[alt=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] img[alt=" + str + label2 + str + "], [value=" + str + context2 + str + "] img[ng-reflect-name=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[ng-reflect-name=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[ng-reflect-name=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] img[ng-reflect-name=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] img[ng-reflect-name=" + str + label2 + str + "], [value=" + str + context2 + str + "] img[ng-reflect-value=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[ng-reflect-value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[ng-reflect-value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] img[ng-reflect-value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] img[ng-reflect-value=" + str + label2 + str + "], [value=" + str + context2 + str + "] img[ng-reflect-message=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[ng-reflect-message=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[ng-reflect-message=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] img[ng-reflect-message=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] img[ng-reflect-message=" + str + label2 + str + "], [value=" + str + context2 + str + "] img[svgicon=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[svgicon=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[svgicon=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] img[svgicon=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] img[svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow a contain
				selector = "[value*=" + str + context2 + str + "] a[value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] a[value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] a[value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] a[value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] a[value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] a[title*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] a[title*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] a[title*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] a[title*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] a[title*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] a[aria-label*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] a[aria-label*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] a[aria-label*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] a[aria-label*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] a[aria-label*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] a[alt*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] a[alt*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] a[alt*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] a[alt*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] a[alt*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] a[ng-reflect-name*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] a[ng-reflect-name*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] a[ng-reflect-name*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] a[ng-reflect-name*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] a[ng-reflect-name*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] a[ng-reflect-value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] a[ng-reflect-value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] a[ng-reflect-value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] a[ng-reflect-value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] a[ng-reflect-value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] a[ng-reflect-message*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] a[ng-reflect-message*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] a[ng-reflect-message*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] a[ng-reflect-message*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] a[ng-reflect-message*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] a[svgicon*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] a[svgicon*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] a[svgicon*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] a[svgicon*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] a[svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow button contain
				selector = "[value*=" + str + context2 + str + "] button[value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] button[value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] button[value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] button[value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] button[value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] button[title*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] button[title*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] button[title*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] button[title*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] button[title*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] button[aria-label*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] button[aria-label*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] button[aria-label*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] button[aria-label*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] button[aria-label*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] button[alt*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] button[alt*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] button[alt*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] button[alt*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] button[alt*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] button[ng-reflect-name*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] button[ng-reflect-name*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] button[ng-reflect-name*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] button[ng-reflect-name*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] button[ng-reflect-name*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] button[ng-reflect-value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] button[ng-reflect-value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] button[ng-reflect-value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] button[ng-reflect-value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] button[ng-reflect-value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] button[ng-reflect-message*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] button[ng-reflect-message*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] button[ng-reflect-message*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] button[ng-reflect-message*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] button[ng-reflect-message*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] button[svgicon*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] button[svgicon*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] button[svgicon*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] button[svgicon*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] button[svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow input contain
				selector = "[value*=" + str + context2 + str + "] input[value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] input[value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] input[value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] input[value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] input[value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] input[title*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] input[title*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] input[title*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] input[title*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] input[title*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] input[aria-label*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] input[aria-label*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] input[aria-label*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] input[aria-label*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] input[aria-label*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] input[alt*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] input[alt*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] input[alt*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] input[alt*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] input[alt*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] input[ng-reflect-name*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] input[ng-reflect-name*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] input[ng-reflect-name*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] input[ng-reflect-name*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] input[ng-reflect-name*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] input[ng-reflect-value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] input[ng-reflect-value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] input[ng-reflect-value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] input[ng-reflect-value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] input[ng-reflect-value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] input[ng-reflect-message*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] input[ng-reflect-message*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] input[ng-reflect-message*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] input[ng-reflect-message*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] input[ng-reflect-message*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] input[svgicon*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] input[svgicon*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] input[svgicon*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] input[svgicon*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] input[svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow img contain
				selector = "[value*=" + str + context2 + str + "] img[value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] img[value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] img[value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] img[value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] img[value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] img[title*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] img[title*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] img[title*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] img[title*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] img[title*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] img[aria-label*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] img[aria-label*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] img[aria-label*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] img[aria-label*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] img[aria-label*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] img[alt*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] img[alt*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] img[alt*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] img[alt*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] img[alt*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] img[ng-reflect-name*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] img[ng-reflect-name*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] img[ng-reflect-name*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] img[ng-reflect-name*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] img[ng-reflect-name*=" + str + label2 + str + "], [value=" + str + context2 + str + "] img[ng-reflect-value=" + str + label2 + str + "], [title=" + str + context2 + str + "] img[ng-reflect-value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] img[ng-reflect-value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] img[ng-reflect-value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] img[ng-reflect-value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] img[ng-reflect-message*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] img[ng-reflect-message*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] img[ng-reflect-message*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] img[ng-reflect-message*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] img[ng-reflect-message*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] img[svgicon*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] img[svgicon*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] img[svgicon*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] img[svgicon*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] img[svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow all
				selector = "[value=" + str + context2 + str + "] [value=" + str + label2 + str + "], [title=" + str + context2 + str + "] [value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] [value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] [value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] [value=" + str + label2 + str + "], [value=" + str + context2 + str + "] [title=" + str + label2 + str + "], [title=" + str + context2 + str + "] [title=" + str + label2 + str + "], [alt=" + str + context2 + str + "] [title=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] [title=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] [title=" + str + label2 + str + "], [value=" + str + context2 + str + "] [aria-label=" + str + label2 + str + "], [title=" + str + context2 + str + "] [aria-label=" + str + label2 + str + "], [alt=" + str + context2 + str + "] [aria-label=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] [aria-label=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] [aria-label=" + str + label2 + str + "], [value=" + str + context2 + str + "] [alt=" + str + label2 + str + "], [title=" + str + context2 + str + "] [alt=" + str + label2 + str + "], [alt=" + str + context2 + str + "] [alt=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] [alt=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] [alt=" + str + label2 + str + "], [value=" + str + context2 + str + "] [ng-reflect-name=" + str + label2 + str + "], [title=" + str + context2 + str + "] [ng-reflect-name=" + str + label2 + str + "], [alt=" + str + context2 + str + "] [ng-reflect-name=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] [ng-reflect-name=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] [ng-reflect-name=" + str + label2 + str + "], [value=" + str + context2 + str + "] a[ng-reflect-value=" + str + label2 + str + "], [title=" + str + context2 + str + "] a[ng-reflect-value=" + str + label2 + str + "], [alt=" + str + context2 + str + "] [ng-reflect-value=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] [ng-reflect-value=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] [ng-reflect-value=" + str + label2 + str + "], [value=" + str + context2 + str + "] [ng-reflect-message=" + str + label2 + str + "], [title=" + str + context2 + str + "] [ng-reflect-message=" + str + label2 + str + "], [alt=" + str + context2 + str + "] [ng-reflect-message=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] [ng-reflect-message=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] [ng-reflect-message=" + str + label2 + str + "], [value=" + str + context2 + str + "] [svgicon=" + str + label2 + str + "], [title=" + str + context2 + str + "] [svgicon=" + str + label2 + str + "], [alt=" + str + context2 + str + "] [svgicon=" + str + label2 + str + "], [aria-label=" + str + context2 + str + "] [svgicon=" + str + label2 + str + "], [placeholder=" + str + context2 + str + "] [svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow all contain
				selector = "[value*=" + str + context2 + str + "] [value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] [value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] [value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] [value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] [value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] [title*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] [title*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] [title*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] [title*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] [title*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] [aria-label*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] [aria-label*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] [aria-label*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] [aria-label*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] [aria-label*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] [alt*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] [alt*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] [alt*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] [alt*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] [alt*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] [ng-reflect-name*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] [ng-reflect-name*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] [ng-reflect-name*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] [ng-reflect-name*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] [ng-reflect-name*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] [ng-reflect-value*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] [ng-reflect-value*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] [ng-reflect-value*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] [ng-reflect-value*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] [ng-reflect-value*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] [ng-reflect-message*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] [ng-reflect-message*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] [ng-reflect-message*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] [ng-reflect-message*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] [ng-reflect-message*=" + str + label2 + str + "], [value*=" + str + context2 + str + "] [svgicon*=" + str + label2 + str + "], [title*=" + str + context2 + str + "] [svgicon*=" + str + label2 + str + "], [alt*=" + str + context2 + str + "] [svgicon*=" + str + label2 + str + "], [aria-label*=" + str + context2 + str + "] [svgicon*=" + str + label2 + str + "], [placeholder*=" + str + context2 + str + "] [svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}
			}
			}catch(Exception e) {
				System.out.println(e);
			}
		return null;
	}

	public static HashMap identifyElementToClick(WebDriver driver, String label)
	{
		String xpath;
		WebElement element;
		String selector;
		HashMap<String, WebElement> list = new HashMap<>();
		String label2 = label;
		label = label.toLowerCase().replaceAll("\\s","");
		try {
			// a
			xpath = "//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			// button
			xpath = "//button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// input
			xpath = "//input[(@type='submit' or @type='button') and (translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// img
			xpath = "//img[translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// lien parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// bouton parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// input parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input[@type='submit' or @type='button']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// img parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//lien contains
			xpath = "//a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// bouton contains
			xpath = "//button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// input contains
			xpath = "//input[(@type='submit' or @type='button') and (text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + "))]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			// img contains
			xpath = "//img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			
			//lien parent contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// bouton parent contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			// input parent contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input[@type='submit' or @type='button']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			// img parent contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// all
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//all contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-message,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@svgicon,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			System.out.println("\n ########## try in shadow root ########## \n");

			boolean shadowStatus = checkShadowElement(driver);
			if (shadowStatus) {
				//shadow a
				selector = "a[value=" + str + label2 + str + "], a[title=" + str + label2 + str + "], a[aria-label=" + str + label2 + str + "], a[alt=" + str + label2 + str + "], a[ng-reflect-name=" + str + label2 + str + "], a[ng-reflect-value=" + str + label2 + str + "], a[ng-reflect-message=" + str + label2 + str + "], a[svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow button
				selector = "button[value=" + str + label2 + str + "], button[title=" + str + label2 + str + "], button[aria-label=" + str + label2 + str + "], button[alt=" + str + label2 + str + "], button[ng-reflect-name=" + str + label2 + str + "], button[ng-reflect-value=" + str + label2 + str + "], button[ng-reflect-message=" + str + label2 + str + "], button[svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow input
				selector = "input[value=" + str + label2 + str + "], input[title=" + str + label2 + str + "], input[aria-label=" + str + label2 + str + "], input[alt=" + str + label2 + str + "], input[ng-reflect-name=" + str + label2 + str + "], input[ng-reflect-value=" + str + label2 + str + "], input[ng-reflect-message=" + str + label2 + str + "], input[svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow img
				selector = "img[value=" + str + label2 + str + "], img[title=" + str + label2 + str + "], img[aria-label=" + str + label2 + str + "], img[alt=" + str + label2 + str + "], img[ng-reflect-name=" + str + label2 + str + "], img[ng-reflect-value=" + str + label2 + str + "], img[ng-reflect-message=" + str + label2 + str + "], img[svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow a contain
				selector = "a[value*=" + str + label2 + str + "], a[title*=" + str + label2 + str + "], a[aria-label*=" + str + label2 + str + "], a[alt*=" + str + label2 + str + "], a[ng-reflect-name*=" + str + label2 + str + "], a[ng-reflect-value*=" + str + label2 + str + "], a[ng-reflect-message*=" + str + label2 + str + "], a[svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow button contain
				selector = "button[value*=" + str + label2 + str + "], button[title*=" + str + label2 + str + "], button[aria-label*=" + str + label2 + str + "], button[alt*=" + str + label2 + str + "], button[ng-reflect-name*=" + str + label2 + str + "], button[ng-reflect-value*=" + str + label2 + str + "], button[ng-reflect-message*=" + str + label2 + str + "], button[svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow input contain
				selector = "input[value*=" + str + label2 + str + "], input[title*=" + str + label2 + str + "], input[aria-label*=" + str + label2 + str + "], input[alt*=" + str + label2 + str + "], input[ng-reflect-name*=" + str + label2 + str + "], input[ng-reflect-value*=" + str + label2 + str + "], input[ng-reflect-message*=" + str + label2 + str + "], input[svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow img contain
				selector = "img[value*=" + str + label2 + str + "], img[title*=" + str + label2 + str + "], img[aria-label*=" + str + label2 + str + "], img[alt*=" + str + label2 + str + "], img[ng-reflect-name*=" + str + label2 + str + "], img[ng-reflect-value*=" + str + label2 + str + "], img[ng-reflect-message*=" + str + label2 + str + "], img[svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow all
				selector = "[value=" + str + label2 + str + "], [title=" + str + label2 + str + "], [aria-label=" + str + label2 + str + "], [alt=" + str + label2 + str + "], a[ng-reflect-name=" + str + label2 + str + "], [ng-reflect-value=" + str + label2 + str + "], [ng-reflect-message=" + str + label2 + str + "], [svgicon=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}

				//shadow all contain
				selector = "[value*=" + str + label2 + str + "], [title*=" + str + label2 + str + "], [aria-label*=" + str + label2 + str + "], [alt*=" + str + label2 + str + "], [ng-reflect-name*=" + str + label2 + str + "], [ng-reflect-value*=" + str + label2 + str + "], [ng-reflect-message*=" + str + label2 + str + "], [svgicon*=" + str + label2 + str + "]";
				element = getShadowElement(driver, selector);
				if (element != null) { 
					list.put(selector, element);
					return list;
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

	public static HashMap identifyElementInput(final WebDriver driver, String label, String context)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s", "");
		context = context.toLowerCase().replaceAll("\\s", "");
		try {
			//input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::input[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//textarea follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::textarea[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//parent input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//parent textarea follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow sibling ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}


			//for textarea follow sibling ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input follow sibling
			xpath =	"//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea follow sibling
			xpath =	"//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//for contains input follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//contains parent input follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent textarea follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input follow sibling ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
            element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow sibling ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
            element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//input[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//textarea follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//textarea[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//parent input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//parent textarea follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input follow
			xpath =	"//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea follow
			xpath =	"//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//contains parent input follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent textarea follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input follow ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
            element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
            element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

	public static HashMap identifyElementInputByIndex(final WebDriver driver, String label, String context, String index)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s", "");
		context = context.toLowerCase().replaceAll("\\s", "");
		try {
			//input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::input[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//textarea follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::textarea[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//parent input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//parent textarea follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow sibling ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow sibling ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input follow sibling
			xpath =	"//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea follow sibling
			xpath =	"//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//for contains input follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input follow sibling ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
            element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow sibling ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
            element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//contains parent input follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent textarea follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//input[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//textarea follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//textarea[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//parent input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//parent textarea follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input follow
			xpath =	"//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea follow
			xpath =	"//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//contains parent input follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent textarea follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input follow ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
            element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
            element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e);
		}			
		
		return null;
	}

	public static HashMap identifyElementInput(final WebDriver driver, String label)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s", "");
		try {
			//input
			xpath = "//input[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//textarea
			xpath = "//textarea[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//parent input
			xpath = "//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//parent textarea
			xpath = "//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// ancêtre label for input
			xpath = "//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInput(driver, xpath, "input");
			if(element != null){
				list.put(xpath, element);
				return list;
			}

			// ancêtre label for textarea
			xpath = "//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInput(driver, xpath, "textarea");
			if(element != null){
				list.put(xpath, element);
				return list;
			}

			//contains input
			xpath = "//input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea
			xpath = "//textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent input
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent textarea
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			// ancêtre label input
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
			element = findElementsForInput(driver, xpath, "input");
			if(element != null){
				list.put(xpath, element);
				return list;
			}

			// ancêtre label textarea
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),\" + str + label + str + \")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),\" + str + label + str + \") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),\" + str + label + str + \") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),\" + str + label + str + \") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),\" + str + label + str + \") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),\" + str + label + str + \") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),\" + str + label + str + \")]//ancestor::label";
			element = findElementsForInput(driver, xpath, "textarea");
			if(element != null){
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
				System.out.println(e);
		}
		
		return null;
	}

	public static HashMap identifyElementInputByIndex(final WebDriver driver, String label, String index)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s", "");
		try {
			//input
			xpath = "//input[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//textarea
			xpath = "//textarea[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//parent input
			xpath = "//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//parent textarea
			xpath = "//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea ancestor label
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//ancestor::label";
			element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input
			xpath = "//input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea
			xpath = "//textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent input
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent textarea
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
			element = findElementsForInputByIndex(driver, xpath, "input", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea ancestor label
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@ng-reflect-model,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//ancestor::label";
			element = findElementsForInputByIndex(driver, xpath, "textarea", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
				System.out.println(e);
		}
		
		return null;
	}

	public static HashMap identifyElementCheck(final WebDriver driver, String label, String context)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		context = context.toLowerCase().replaceAll("\\s","");
		try {
			//input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::input[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//textarea follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::textarea[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//textarea parent follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//for contains input follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input parent follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea parent follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//input[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//textarea follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//textarea[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//textarea parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textrea follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input parent follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea parent follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains all follow sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//all follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains all follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e);
		}		
		
		return null;
	}
	
	public static HashMap identifyElementCheck(final WebDriver driver, String label) 
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		try {
			//input
			xpath = "//input[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//textarea
			xpath = "//textarea[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for input
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for textarea
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//parent input
			xpath = "//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//parent textarea
			xpath = "//*[translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains input
			xpath = "//input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains textarea
			xpath = "//textarea[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains input
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForInput(driver, xpath, "input");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains textarea
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForInput(driver, xpath, "textarea");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent input
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains parent textarea
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//textarea";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//contains all
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
				System.out.println(e);
		}
		
		return null;
	}

	public static HashMap identifyElementHover(final WebDriver driver, String label, String context)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s", "");
		context = context.toLowerCase().replaceAll("\\s", "");
		try {
			// follow sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//follow sibling contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//follow contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e+ " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyElementHover(final WebDriver driver, String label)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s", "");
		try {
			//
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e+ " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyElementRelative(final WebDriver driver, String[] parts, String direction)
	{
		HashMap<String, WebElement> list = new HashMap<>();
		try {
			String label = parts[0];
			label = label.toLowerCase().replaceAll("\\s","");
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			List<WebElement> list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				WebElement reference_element = getElementRelative(driver, list_elements);
				if(reference_element != null)
				{
					System.out.println("xpath used: "+ xpath);
					String xpathTry = xpath+"|";
					HashMap<String, WebElement> listLabel = new HashMap<>();
					WebElement element = null;
					if(parts.length == 2)
					{
						listLabel = identifyLabelElement(driver, reference_element, parts[1], direction);
						if (listLabel != null) {
							for(Map.Entry<String, WebElement> entry : listLabel.entrySet()) {
								element = entry.getValue();
								xpathTry += entry.getKey();
							}
						}
					} else {
						xpath = "//*[local-name()='button' or local-name()='img' or local-name()='input' or local-name()='a']";
						element = getDirectionElement(driver, reference_element, xpath, direction);
						xpathTry += xpath;
					}
					if (element != null) { 
						list.put(xpathTry, element);
						return list;
					}
				}
			}
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");
				
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			list_elements = driver.findElements(By.xpath(xpath));
			if(!list_elements.isEmpty())
			{
				WebElement reference_element = getElementRelative(driver, list_elements);
				if(reference_element != null)
				{
					System.out.println("xpath used: "+ xpath);
					String xpathTry = xpath+"|";
					HashMap<String, WebElement> listLabel = new HashMap<>();
					WebElement element = null;
					if(parts.length == 2)
					{
						listLabel = identifyLabelElement(driver, reference_element, parts[1], direction);
						if (listLabel != null) {
							for(Map.Entry<String, WebElement> entry : listLabel.entrySet()) {
								element = entry.getValue();
								xpathTry += entry.getKey();
							}
						}
					} else {
						xpath = "//*[local-name()='button' or local-name()='img' or local-name()='input' or local-name()='a']";
						element = getDirectionElement(driver, reference_element, xpath, direction);
						xpathTry += xpath;
					}
					if (element != null) { 
						list.put(xpathTry, element);
						return list;
					}
				}
			}
			System.out.println(">>>>> Element not found using the xpath :  \n"+xpath+"\n");

		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

	public static HashMap identifyLabelElement(final WebDriver driver, WebElement reference_element, String label, String direction)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s", "");
		try {
			//lien
			xpath = "//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button
			xpath = "//button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input
			xpath = "//input[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img
			xpath = "//img[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//lien parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
				
			//lien contains
			xpath = "//a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains
			xpath = "//button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains
			xpath = "//input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//img contains
			xpath = "//img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//lien contains parent
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains parent
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains parent
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img contains parent
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//all
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsLabelDirection(driver, reference_element, xpath, direction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

	//find dropdown param
	public static HashMap identifyElementChoose(final WebDriver driver, String param)
    {
		HashMap<String, WebElement> list = new HashMap<>();
		param = param.toLowerCase().replaceAll("\\s","");
        try {
			//option
			String xpath = "//option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            WebElement element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option contains
			xpath = "//option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

	//find dropdown texte + param
    public static HashMap identifyElementChoose(final WebDriver driver, String label, String param)
    {
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		param = param.toLowerCase().replaceAll("\\s","");
        try {
			//option
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            WebElement element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//option follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
	//find dropdown texte with index + param
    public static HashMap identifyElementChooseWithIndex(final WebDriver driver, String label, String param, String index)
    {
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		param = param.toLowerCase().replaceAll("\\s","");
		System.out.println("dropdown texte with index + param");
        try {
			//option
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            WebElement element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//option follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

	//find dropdown texte + context + param
    public static HashMap identifyElementChoose(final WebDriver driver, String label, String context, String param)
    {
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		context = context.toLowerCase().replaceAll("\\s","");
		param = param.toLowerCase().replaceAll("\\s","");
        try {
			//option follow-sibling
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            WebElement element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow-sibling contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//option follow-sibling follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow-sibling contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
	//find dropdown texte + context with index + param
    public static HashMap identifyElementChooseWithIndex(final WebDriver driver, String label, String context, String param, String index)
    {
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		context = context.toLowerCase().replaceAll("\\s","");
		param = param.toLowerCase().replaceAll("\\s","");
        try {
			//option follow-sibling
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            WebElement element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow-sibling contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//option follow-sibling follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow-sibling contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + param + str + "]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + param + str + ")]";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

	//find dropdown texte + index
    public static HashMap identifyElementChooseByIndex(final WebDriver driver, String label, String param)
    {
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		param = param.toLowerCase().replaceAll("\\s","");
        try {
			//option
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option";
            WebElement element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
	//find dropdown texte + context + index
	public static HashMap identifyElementChooseByIndex(final WebDriver driver, String label, String context, String param)
    {
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		context = context.toLowerCase().replaceAll("\\s","");
		param = param.toLowerCase().replaceAll("\\s","");
        try {
			//option follow-sibling
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option";
            WebElement element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow-sibling contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow-sibling follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow-sibling contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//option follow contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::option";
            element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

	//find checkbox radio texte + context
    public static HashMap identifyElementRadioCheckBox(final WebDriver driver, String label, String context)
    {
		HashMap<String, WebElement> list = new HashMap<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        label = label.toLowerCase().replaceAll("\\s","");
		context = context.toLowerCase().replaceAll("\\s","");
        try {
			
            //for radio
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            WebElement element = findElementsForSelect(driver, xpath, "radio");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            element = findElementsForSelect(driver, xpath, "checkbox");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains radio
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForSelect(driver, xpath, "radio");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForSelect(driver, xpath, "checkbox");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

            //sans for radio
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='radio']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
            //sans for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='checkbox']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains radio
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='radio']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='checkbox']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for follow radio checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::input[@type='radio' or @type='checkbox']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains follow radio checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::input[@type='radio' or @type='checkbox']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
	//find checkbox radio texte + context with index
	public static HashMap identifyElementRadioCheckBoxWithIndex(final WebDriver driver, String label, String context, String index)
    {
		HashMap<String, WebElement> list = new HashMap<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        label = label.toLowerCase().replaceAll("\\s","");
		context = context.toLowerCase().replaceAll("\\s","");
        try {
			
            //for radio
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            WebElement element = findElementsForSelectWithIndex(driver, xpath, "radio", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            element = findElementsForSelectWithIndex(driver, xpath, "checkbox", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains radio
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForSelectWithIndex(driver, xpath, "radio", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForSelectWithIndex(driver, xpath, "checkbox", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

            //sans for radio
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='radio']";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
            //sans for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='checkbox']";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains radio
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='radio']";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='checkbox']";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for follow radio checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::input[@type='radio' or @type='checkbox']";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains follow radio checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::input[@type='radio' or @type='checkbox']";
            element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

	//find checkbox radio texte
    public static HashMap identifyElementRadioCheckBox(final WebDriver driver, String label)
    {
		HashMap<String, WebElement> list = new HashMap<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        label = label.toLowerCase().replaceAll("\\s","");
        try {
			
			//for radio
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			WebElement element = findElementsForSelect(driver, xpath, "radio");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            element = findElementsForSelect(driver, xpath, "checkbox");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//for contains radio
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForSelect(driver, xpath, "radio");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForSelect(driver, xpath, "checkbox");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for radio
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='radio']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='checkbox']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains radio
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='radio']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='checkbox']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for follow radio checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::input[@type='radio' or @type='checkbox']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains follow radio checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::input[@type='radio' or @type='checkbox']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
	//find checkbox radio texte with index
	public static HashMap identifyElementRadioCheckBoxWithIndex(final WebDriver driver, String label, String index)
    {
		HashMap<String, WebElement> list = new HashMap<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        label = label.toLowerCase().replaceAll("\\s","");
        try {
			
			//for radio
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			WebElement element = findElementsForSelectWithIndex(driver, xpath, "radio", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            element = findElementsForSelectWithIndex(driver, xpath, "checkbox", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
			//for contains radio
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForSelectWithIndex(driver, xpath, "radio", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForSelectWithIndex(driver, xpath, "checkbox", index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for radio
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='radio']";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='checkbox']";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains radio
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='radio']";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='checkbox']";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for follow radio checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::input[@type='radio' or @type='checkbox']";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains follow radio checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::input[@type='radio' or @type='checkbox']";
			element = findElementsIndex(driver, xpath, index);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

	public static HashMap identifyElementsToClickByIndex(final WebDriver driver, String label, String param)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		try {
			//img
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following-sibling::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following-sibling::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following-sibling::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following-sibling::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//img contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following-sibling::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following-sibling::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following-sibling::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following-sibling::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//img contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyElementsToClickByIndexAvecContext(final WebDriver driver, String context, String label, String param)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		context = context.toLowerCase().replaceAll("\\s","");
		label = label.toLowerCase().replaceAll("\\s","");
		try {
			//img
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//img[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//input[(@type='submit' or @type='button') and (translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//input[(@type='submit' or @type='button') and (text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + "))]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a parent contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::img[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::input[(@type='submit' or @type='button') and (translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a parent follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::input[(@type='submit' or @type='button') and (text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + "))]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a parent contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::img[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::input[(@type='submit' or @type='button') and (translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//img contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::input[(@type='submit' or @type='button') and (text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + "))]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a parent contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following-sibling::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyElementsToClickByIndex(final WebDriver driver, String property, String value, String param)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		value = value.toLowerCase().replaceAll("\\s","");
		try {
			
			//img
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a 
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button 
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input 
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//img contains 
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a contains 
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains 
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains 
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyElementsToClickByIndex(final WebDriver driver, String property, String value, String label, String param)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		value = value.toLowerCase().replaceAll("\\s","");
		label = label.toLowerCase().replaceAll("\\s","");
		try {
			
			//img
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//img[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//input[(@type='submit' or @type='button') and (translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a parent
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//img contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//input[(@type='submit' or @type='button') and (text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + "))]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//img parent contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//a parent contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//button parent contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//input parent contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input[@type='submit' or @type='button']";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//all contains
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsIndex(driver, xpath, param);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyElementTable(final WebDriver driver, String[] param, String typeAction)
	{
		HashMap<String, WebElement> list = new HashMap<>();
		try {
			//table
			String xpath = "//table";
			WebElement element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyElementTable(final WebDriver driver, String property, String value, String[] param, String typeAction)
	{
		HashMap<String, WebElement> list = new HashMap<>();
		value = value.toLowerCase().replaceAll("\\s","");
		try {
			
			//table
			String xpath = "//table[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]";
			WebElement element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//table contains 
			xpath = "//table[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]";
			element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyElementTable(final WebDriver driver, String label, String[] param, String typeAction)
	{
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		try {
			//table
			String xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//table";
			WebElement element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//table contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//table";
			element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//table follow-sibling
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following-sibling::table";
			element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//table contains follow-sibling
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following-sibling::table";
			element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//table follow
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::table";
			element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//table contains follow
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::table";
			element = findElementsTable(driver, xpath, param, typeAction);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static WebElement identifyCellElementToClick(WebDriver driver, WebElement table_element, int colIndex, int rowIndex)
	{
		String xpath;
		WebElement element;
		try {
			if (rowIndex != 0) {
				//a
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//a";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
			
				//button
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//button";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//input
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//input";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//img
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//img";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
			} else {
				//all
				xpath = ".//thead//th";
				element = findElementsCellIndex(driver, xpath, table_element, String.valueOf(colIndex));
				if (element != null) return element;
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static WebElement identifyCellElementToClick(WebDriver driver, WebElement table_element, int colIndex, int rowIndex, String label)
	{
		String xpath;
		WebElement element;
		WebElement th_element;
		label = label.toLowerCase().replaceAll("\\s","");
		try {
			if (rowIndex != 0) {
				//a
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
			
				//button
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//input
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//input[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//img
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//img[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
				
				//parent a
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::a";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
			
				//parent button
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::button";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//parent input
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::input";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//parent img
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]/ancestor::img";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//a contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//a[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
			
				//button contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//button[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//input contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//input[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//img contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//img[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
				
				//parent a contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::a";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
			
				//parent button contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::button";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//parent input contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::input";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//parent img contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]/ancestor::img";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//all
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;

				//all contains
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
				element = findElementsCell(driver, xpath, table_element);
				if (element != null) return element;
			} else {
				//all
				xpath = ".//thead//th";
				th_element = findElementsCellIndex(driver, xpath, table_element, String.valueOf(colIndex));
				if (th_element != null) {
					xpath = ".//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
					element = findElementsCell(driver, xpath, th_element);
					if (element != null) return element;
				}
				//all contains
				xpath = ".//thead//th";
				th_element = findElementsCellIndex(driver, xpath, table_element, String.valueOf(colIndex));
				if (th_element != null) {
					xpath = ".//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
					element = findElementsCell(driver, xpath, th_element);
					if (element != null) return element;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static WebElement identifyCellElementToClickIndex(WebDriver driver, WebElement table_element, int colIndex, int rowIndex, String param)
	{
		String xpath;
		WebElement element;
		WebElement th_element;
		try {
			if (rowIndex != 0) {
				//a
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//a";
				element = findElementsCellIndex(driver, xpath, table_element, param);
				if (element != null) return element;
			
				//button
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//button";
				element = findElementsCellIndex(driver, xpath, table_element, param);
				if (element != null) return element;

				//input
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//input";
				element = findElementsCellIndex(driver, xpath, table_element, param);
				if (element != null) return element;

				//img
				xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//img";
				element = findElementsCellIndex(driver, xpath, table_element, param);
				if (element != null) return element;
			} else {
				//a
				xpath = ".//thead//th";
				th_element = findElementsCellIndex(driver, xpath, table_element, String.valueOf(colIndex));
				if (th_element != null) {
					xpath = ".//a";
					element = findElementsCellIndex(driver, xpath, th_element, param);
					if (element != null) return element;
				}
				//button
				xpath = ".//thead//th";
				th_element = findElementsCellIndex(driver, xpath, table_element, String.valueOf(colIndex));
				if (th_element != null) {
					xpath = ".//button";
					element = findElementsCellIndex(driver, xpath, th_element, param);
					if (element != null) return element;
				}
				//input
				xpath = ".//thead//th";
				th_element = findElementsCellIndex(driver, xpath, table_element, String.valueOf(colIndex));
				if (th_element != null) {
					xpath = ".//input";
					element = findElementsCellIndex(driver, xpath, th_element, param);
					if (element != null) return element;
				}
				//img
				xpath = ".//thead//th";
				th_element = findElementsCellIndex(driver, xpath, table_element, String.valueOf(colIndex));
				if (th_element != null) {
					xpath = ".//img";
					element = findElementsCellIndex(driver, xpath, th_element, param);
					if (element != null) return element;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static WebElement identifyCellElementToCheck(WebDriver driver, WebElement table_element, int colIndex, int rowIndex)
	{
		try {
			
			//input
			String xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//input";
			WebElement element = findElementsCell(driver, xpath, table_element);
			if (element != null) return element;
		
			//textarea
			xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//textarea";
			element = findElementsCell(driver, xpath, table_element);
			if (element != null) return element;

			//cell
			xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]";
			element = findElementsCell(driver, xpath, table_element);
			if (element != null) return element;
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static WebElement identifyCellElementToSendkeys(WebDriver driver, WebElement table_element, int colIndex, int rowIndex)
	{
		try {
			
			//input
			String xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//input";
			WebElement element = findElementsCell(driver, xpath, table_element);
			if (element != null) return element;
		
			//textarea
			xpath = ".//tbody/tr[" + rowIndex + "]/td[" + colIndex + "]//textarea";
			element = findElementsCell(driver, xpath, table_element);
			if (element != null) return element;
		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyDragElementByAttr(final WebDriver driver, String property, String value)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		value = value.toLowerCase().replaceAll("\\s","");
		try {

			//element
			xpath = "//*[translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + value + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//element contains 
			xpath = "//*[contains(translate(" + property + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + value + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

		}catch(Exception e){
			System.out.println(e.getMessage() + " Element not found");
		}
		
		return null;
	}

	public static HashMap identifyDragElement(WebDriver driver, String label)
	{
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
		label = label.toLowerCase().replaceAll("\\s","");
		try {

			// all
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		
			//all contains
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}

    public static List<WebElement> checkTextInElement(final WebDriver driver, WebElement element, String param)
    {
		String xpath;
		List<WebElement> list = new ArrayList<>();
        try {
			//descendant
			xpath = ".//descendant-or-self::*[text()=" + str + param + str + " or @value=" + str + param + str + "]";
            list = element.findElements(By.xpath(xpath));
			if (!list.isEmpty()) return list;

			//descendant contains
			xpath = ".//descendant-or-self::*[text()[contains(.," + str + param + str + ")] or contains(@value," + str + param + str + ")]";
            list = element.findElements(By.xpath(xpath));
			if (!list.isEmpty()) return list;

			//follow sibling
			xpath = "./parent::*//following-sibling::*[text()=" + str + param + str + " or @value=" + str + param + str + "]";
            list = element.findElements(By.xpath(xpath));
			if (!list.isEmpty()) return list;

			//follow sibling contains
			xpath = "./parent::*//following-sibling::*[text()[contains(.," + str + param + str + ")] or contains(@value," + str + param + str + ")]";
            list = element.findElements(By.xpath(xpath));
			if (!list.isEmpty()) return list;

			//follow
			xpath = "./parent::*//following::*[text()=" + str + param + str + " or @value=" + str + param + str + "]";
            list = element.findElements(By.xpath(xpath));
			if (!list.isEmpty()) return list;

			//follow contains
			xpath = "./parent::*//following::*[text()[contains(.," + str + param + str + ")] or contains(@value," + str + param + str + ")]";
            list = element.findElements(By.xpath(xpath));
			if (!list.isEmpty()) return list;
        }catch(Exception e){
            System.out.println(e);
        }
        return list;
    }

	//dropdown param
    public static boolean chooseByTry(final WebDriver driver, String xpath)
    {
        WebElement element= findElements(driver, xpath);
        if(element != null)
        {   
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			}
			try {
				element.click();
				System.out.println("##### select made on : \n"+ element + "\n");
				return true;
			} catch (Exception e) {
				System.out.println ("##### Element.select fail #####");
			}
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("##### select made on : \n"+ element + "\n");
				return true;
			} catch (Exception e) {
				System.out.println ("##### JavascriptExecutor select fail #####");
			}
        }
        return false;
    }

	public static boolean chooseByTry(final WebDriver driver, String xpath, String param)
    {
		WebElement element;
        if (param.charAt(0) == '[' && param.charAt(param.length() - 1) == ']'){
			param = param.substring(1, param.length() - 1);
			element = findElementsIndex(driver, xpath, param);
		} else {
			element= findElements(driver, xpath);
		}
        if(element != null)
        {   
			try {
				element.click();
				System.out.println("##### select made on : \n"+ element + "\n");
				return true;
			} catch (Exception e) {
				System.out.println ("##### Element.select fail #####");
			}
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("##### select made on : \n"+ element + "\n");
				return true;
			} catch (Exception e) {
				System.out.println ("##### JavascriptExecutor select fail #####");
			}
        }
        return false;
    }

	public static boolean chooseByTryAndIndex(final WebDriver driver, String xpath, String index, String param)
    {
		WebElement element;
        if (param.charAt(0) == '[' && param.charAt(param.length() - 1) == ']'){
			param = param.substring(1, param.length() - 1);
			element= findElementsIndex(driver, xpath, param);
		} else {
			element= findElementsIndex(driver, xpath, index);
		}
        if(element != null)
        {   
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			}
			try {
				element.click();
				System.out.println("##### select made on : \n"+ element + "\n");
				return true;
			} catch (Exception e) {
				System.out.println ("##### Element.select fail #####");
			}
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("##### select made on : \n"+ element + "\n");
				return true;
			} catch (Exception e) {
				System.out.println ("##### JavascriptExecutor select fail #####");
			}
        }
        return false;
    }

	//dropdown param
    public static HashMap chooseByText(final WebDriver driver, String param, boolean statusElement)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
        listElement = identifyElementChoose(driver, param);
		WebElement element = null;
		String xpath = "";
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {   
			try {
				element.click();
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### Element.select fail #####");
			}
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### JavascriptExecutor select fail #####");
			}
        }
        list.put("", false);
        return list;
    }
    
	//dropdown texte + param
    public static HashMap chooseByText(final WebDriver driver, String label, String param, boolean statusElement)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
		WebElement element = null;
		String xpath = "";
		if (param.charAt(0) == '[' && param.charAt(param.length() - 1) == ']'){
			param = param.substring(1, param.length() - 1);
			listElement= identifyElementChooseByIndex(driver, label, param);
		} else {
			listElement= identifyElementChoose(driver, label, param);
		}
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {   
			try {
				element.click();
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### Element.select fail #####");
			}
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### JavascriptExecutor select fail #####");
			}
        }
        list.put("", false);
        return list;
    }

	//dropdown texte with index + param
	public static HashMap chooseByTextAndIndex(final WebDriver driver, String label, String index, String param, boolean statusElement)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
		WebElement element = null;
		String xpath = "";
		if (param.charAt(0) == '[' && param.charAt(param.length() - 1) == ']'){
			param = param.substring(1, param.length() - 1);
			listElement= identifyElementChooseByIndex(driver, label, param);
		} else {
			listElement= identifyElementChooseWithIndex(driver, label, param, index);
		}
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {   
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			}
			try {
				element.click();
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### Element.select fail #####");
			}
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### JavascriptExecutor select fail #####");
			}
        }
        list.put("", false);
        return list;
    }
    
	//dropdown texte + context + param
    public static HashMap chooseByText(final WebDriver driver, String label, String context, String param, boolean statusElement)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
		WebElement element = null;
		String xpath = "";
		if (param.charAt(0) == '[' && param.charAt(param.length() - 1) == ']'){
			param = param.substring(1, param.length() - 1);
			listElement= identifyElementChooseByIndex(driver, label, context, param);
		} else {
			listElement= identifyElementChoose(driver, label, context, param);
		}
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {   
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			}
			try {
				element.click();
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### Element.select fail #####");
			}
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### JavascriptExecutor select fail #####");
			}
        }
        list.put("", false);
        return list;
    }

	//dropdown texte + context with index + param
	public static HashMap chooseByTextAndIndex(final WebDriver driver, String label, String context, String index, String param, boolean statusElement)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
		WebElement element = null;
		String xpath = "";
		if (param.charAt(0) == '[' && param.charAt(param.length() - 1) == ']'){
			param = param.substring(1, param.length() - 1);
			listElement= identifyElementChooseByIndex(driver, label, context, param);
		} else {
			listElement= identifyElementChooseWithIndex(driver, label, context, param, index);
		}
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {   
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			}
			try {
				element.click();
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### Element.select fail #####");
			}
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("##### select made on : \n"+ element + "\n");
				if (element != null) { 
					list.put(xpath, true);
					return list;
				}
			} catch (Exception e) {
				System.out.println ("##### JavascriptExecutor select fail #####");
			}
        }
        list.put("", false);
        return list;
    }
    
	//checkbox radio texte + context + param
    public static HashMap radioCheckBoxByText(final WebDriver driver, String label, String context, boolean statusElement, String param)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
        WebElement element = null;
		String xpath = "";
		listElement = identifyElementRadioCheckBox(driver, label, context);
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {  
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			} 
            switch(param) {
				case "Oui":
				case "oui":
				case "yes":
				case "Yes":
					if (!element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already selected :\n"+ element+"\n");
					break;
				case "Non":
				case "non":
				case "No":
				case "no":
					if (element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already unselected :\n"+ element+"\n");
					break;
				default:
				System.out.println("Error with the parameter");
				list.put("", false);
        		return list;
			}
        }
        list.put("", false);
        return list;
    }
    
	//checkbox radio texte + context with index + param
	public static HashMap radioCheckBoxByTextAndIndex(final WebDriver driver, String label, String context, String index, boolean statusElement, String param)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
        WebElement element = null;
		String xpath = "";
		listElement = identifyElementRadioCheckBoxWithIndex(driver, label, context, index);
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {   
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			}
            switch(param) {
				case "Oui":
				case "oui":
				case "yes":
				case "Yes":
					if (!element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already selected :\n"+ element+"\n");
					break;
				case "Non":
				case "non":
				case "No":
				case "no":
					if (element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already unselected :\n"+ element+"\n");
					break;
				default:
				System.out.println("Error with the parameter");
				list.put("", false);
        		return list;
			}
        }
        list.put("", false);
        return list;
    }

	public static boolean radioCheckBoxByTry(final WebDriver driver, String xpath, String param)
    {
        WebElement element= findElementsForSelect(driver, xpath, "radio");
		if(element == null) {
			element= findElementsForSelect(driver, xpath, "checkbox");
		}
		if(element == null) {
			element = findElements(driver, xpath);
		}
        if(element != null)
        {   
			switch(param) {
				case "Oui":
				case "oui":
				case "yes":
				case "Yes":
					if (!element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							return true;
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							return true;
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already selected :\n"+ element+"\n");
					break;
				case "Non":
				case "non":
				case "No":
				case "no":
					if (element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							return true;
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							return true;
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already unselected :\n"+ element+"\n");
					break;
				default:
				System.out.println("Error with the parameter");
				return false;
			}
        }
        return false;
    }

	public static boolean radioCheckBoxByTryAndIndex(final WebDriver driver, String xpath, String index, String param)
    {
        WebElement element= findElementsForSelectWithIndex(driver, xpath, "radio", index);
		if(element == null) {
			element= findElementsForSelectWithIndex(driver, xpath, "checkbox", index);
		}
		if(element == null) {
			element = findElementsIndex(driver, xpath, index);
		}
        if(element != null)
        {   
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			}
			switch(param) {
				case "Oui":
				case "oui":
				case "yes":
				case "Yes":
					if (!element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							return true;
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							return true;
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already selected :\n"+ element+"\n");
					break;
				case "Non":
				case "non":
				case "No":
				case "no":
					if (element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							return true;
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							return true;
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already unselected :\n"+ element+"\n");
					break;
				default:
				System.out.println("Error with the parameter");
				return false;
			}
        }
        return false;
    }

	//checkbox radio texte + param
    public static HashMap radioCheckBoxByText(final WebDriver driver, String label, boolean statusElement, String param)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
        WebElement element = null;
		String xpath = "";
		listElement = identifyElementRadioCheckBox(driver, label);
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {   
			switch(param) {
				case "Oui":
				case "oui":
				case "yes":
				case "Yes":
					if (!element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already selected :\n"+ element+"\n");
					break;
				case "Non":
				case "non":
				case "No":
				case "no":
					if (element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already unselected :\n"+ element+"\n");
					break;
				default:
				System.out.println("Error with the parameter");
				list.put("", false);
        		return list;
			}
        }
        list.put("", false);
        return list;
    }

	//checkbox radio texte with index + param
	public static HashMap radioCheckBoxByTextAndIndex(final WebDriver driver, String label, String index, boolean statusElement, String param)
    {
		HashMap<String, WebElement> listElement = new HashMap<>();
		HashMap<String, Boolean> list = new HashMap<>();
        WebElement element = null;
		String xpath = "";
		listElement = identifyElementRadioCheckBoxWithIndex(driver, label, index);
		if (listElement != null) {
			for(Map.Entry<String, WebElement> entry : listElement.entrySet()) {
				element = entry.getValue();
				xpath = entry.getKey();
			}
		}
        if(element != null)
        {   
			try {
				WebElement parent = element.findElement(By.xpath("./.."));
				Fonctions.highLighterMethod(driver, parent);
			} catch(Exception e) {
				System.out.println ("##### Highlight fail #####");
			}
			switch(param) {
				case "Oui":
				case "oui":
				case "yes":
				case "Yes":
					if (!element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already selected :\n"+ element+"\n");
					break;
				case "Non":
				case "non":
				case "No":
				case "no":
					if (element.isSelected()) {
						try {
							element.click();
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### Element.select fail #####");
						}
						try {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
							System.out.println("##### select made on : \n"+ element + "\n");
							if (element != null) { 
								list.put(xpath, true);
								return list;
							}
						} catch (Exception e) {
							System.out.println ("##### JavascriptExecutor select fail #####");
						}
					} else System.out.println("##### Element already unselected :\n"+ element+"\n");
					break;
				default:
				System.out.println("Error with the parameter");
				list.put("", false);
        		return list;
			}
        }
        list.put("", false);
        return list;
    }
	
	public static HashMap identifyElementCheckbox(final WebDriver driver, String label)
    {
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        label = label.toLowerCase().replaceAll("\\s","");
        try {

			//for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            element = findElementsForSelect(driver, xpath, "checkbox");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
			element = findElementsForSelect(driver, xpath, "checkbox");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='checkbox']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='checkbox']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for follow checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::input[@type='checkbox']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains follow checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::input[@type='checkbox']";
			element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

	public static HashMap identifyElementCheckbox(final WebDriver driver, String label, String context)
	
    {
		String xpath;
		WebElement element;
		HashMap<String, WebElement> list = new HashMap<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        label = label.toLowerCase().replaceAll("\\s","");
		context = context.toLowerCase().replaceAll("\\s","");
        try {

			//for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            element = findElementsForSelect(driver, xpath, "checkbox");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
            element = findElementsForSelect(driver, xpath, "checkbox");
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
			
            //sans for checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//input[@type='checkbox']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//input[@type='checkbox']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for follow checkbox
			xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//parent::*//following::input[@type='checkbox']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}

			//sans for contains follow checkbox
			xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//parent::*//following::input[@type='checkbox']";
            element = findElements(driver, xpath);
			if (element != null) { 
				list.put(xpath, element);
				return list;
			}
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

	public static WebElement getDirectionElement(final WebDriver driver, WebElement reference_element, String xpath, String direction)
	{
		try {
			WebElement element;
			switch(direction) {
				case "right":
					try {
						element = driver.findElement(with(By.xpath(xpath)).toRightOf(reference_element));
						if (element != null) {
							return element;
						}
					}catch(Exception e){
						System.out.println(">>>>> Element not found to the right of : \n"+reference_element+"\n");
					}
					break;
				case "above":
					try {
						element = driver.findElement(with(By.xpath(xpath)).above(reference_element));
						if (element != null) {
							return element;
						}
					}catch(Exception e){
						System.out.println(">>>>> Element not found above of : \n"+reference_element+"\n");
					}
					break;
				case "left":
					try {
						element = driver.findElement(with(By.xpath(xpath)).toLeftOf(reference_element));
						if (element != null) {
							return element;
						}
					}catch(Exception e){
						System.out.println(">>>>> Element not found to the left of : \n"+reference_element+"\n");
					}
					break;
				case "below":
					try {
						element = driver.findElement(with(By.xpath(xpath)).below(reference_element));
						if (element != null) {
							return element;
						}
					}catch(Exception e){
						System.out.println(">>>>> Element not found to the below of : \n"+reference_element+"\n");
					}
					break;
				default:
					return null;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static int getColumnIndex(WebDriver driver, WebElement table_element, String[] param) {
		int colIndex = 0;
		boolean isfound = false;
		if(param[0].charAt(0) != '[' && param[0].charAt(param[0].length() - 1) != ']') {
			List<WebElement> rows = table_element.findElements(By.tagName("tr"));
			int j = 1;
			for (WebElement row : rows) {
				List<WebElement> cols = table_element.findElements(By.xpath("//tr[" + j + "]/*[self::th or self::td]"));
				int i = 1;
				for (WebElement col : cols) {
					List<WebElement> cels = table_element.findElements(By.xpath("//tr[" + j + "]/*[self::th or self::td][" + i + "]//descendant-or-self::*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'" + param[0].toLowerCase().replaceAll("\\s", "") + "')]]"));
					WebElement cel = getElementRelative(driver, cels);
					if (cel != null) {
						colIndex = i;
						isfound =true;
						System.out.println(">>>>> Text "+param[0]+" is found in column : "+colIndex);
						break;
					}
					i++;
				}
				if (isfound == true) {
					break;
				}
				j++;
			}
		} else {
			colIndex = Integer.parseInt(param[0].substring(1, param[0].length() - 1));
			System.out.println(">>>>> Search in column : "+colIndex);
		}
		return colIndex;
	}

	public static int getRowIndex(WebDriver driver, WebElement table_element, String[] param) {
		int rowIndex = 0;

		if(param[1].charAt(0) != '[' && param[1].charAt(param[1].length() - 1) != ']') {
			List<WebElement> rows = table_element.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
			int i = 1;
			for (WebElement row : rows) {
				List<WebElement> row_elements = row.findElements(By.xpath("//tr[" + i + "]//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'" + param[1].toLowerCase().replaceAll("\\s", "") + "')]]"));
				WebElement row_element = getElementRelative(driver, row_elements);
				if (row_element != null) {
					rowIndex = i;
					System.out.println(">>>>> Text "+param[1]+" is found in row : " + rowIndex + "\n");
					break;
				}
				i++;
			}
		} else {
			rowIndex = Integer.parseInt(param[1].substring(1, param[1].length() - 1));
			System.out.println(">>>>> Search in row : " + rowIndex + "\n");
		}

		return rowIndex;
	}

	public static WebElement getShadowElement(WebDriver driver, String selector) {
		List<WebElement> hostlist = driver.findElements(By.cssSelector("*"));

		for(WebElement shadowHost: hostlist) {
			try {
				SearchContext shadowRoot = shadowHost.getShadowRoot();
				WebElement element = findShadowElements(driver, shadowRoot, selector);
				if (element != null) return element;
			}catch(Exception e){
			}
		}
		System.out.println(">>>>> Element not found using the selector :  \n"+selector+"\n");
		return null;
	}

	public static boolean checkShadowElement(WebDriver driver) {
		List<WebElement> hostlist = driver.findElements(By.cssSelector("*"));
		boolean status = false;
		for(WebElement shadowHost: hostlist) {
			try {
				SearchContext shadowRoot = shadowHost.getShadowRoot();
				status = true;
			}catch(Exception e){
			}
		}
		return status;
	}
	public static boolean mobileobject_movejaugebyxpath(WebDriver selenium, Teststep teststep) {
		Date time1 = new Date();
	    try {
	        Thread.sleep(Config.pause_actions);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    WebElement element = null;
	    Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
	    if (array_prop_object.isEmpty()){
	        return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
	    }
	    try {
	        try {
	            ((AndroidDriver)selenium).hideKeyboard();
	        }
	        catch (Exception e) {
	            System.out.println ("Keyboard not present");
	        }
	        String xpath = "";
	        String property;
	        HashMap<String, WebElement> list = null;
	        String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
	        String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
	        String parameter;
	        // Récupération de la variable
	        if (array_prop_object.get("xpath").charAt(0) == '$') {
	            try {
	                Wini ini = new Wini(new File(Config.propertyFile));
	                property = ini.get("property", array_prop_object.get("texte").substring(1));
	            } catch (Exception e) {
	                System.out.println("Error in the variable file.");
	                return Fonctions.logStepKO(teststep, selenium, time1, "Error in the variable file." + e.getMessage());
	            }
	        } else {
	            property = array_prop_object.get("xpath");	
	        }
	        if (teststep.param.charAt(0) == '$'){
	            try {
	                Wini ini = new Wini(new File(Config.propertyFile));
	                parameter = ini.get("parameter", teststep.param.substring(1));
	            } catch(Exception e) {
	                System.out.println ("The parameter was not found in the variable file.");
	                return Fonctions.logStepKO(teststep, selenium, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
	            }
	        } else {
	            parameter = teststep.param;
	        }
	        
	        String[] parts = parameter.split("\\|");

	        int fin;
			try {
				fin = Integer.parseInt(parts[0]);
			} catch (NumberFormatException e1) {
				return Fonctions.logStepKO(teststep, selenium, time1, "Le paramètre fourni n'est pas un entier");

			}
			if(fin<0 || fin >100)
				return Fonctions.logStepKO(teststep, selenium, time1, "Le 1er paramètre doit etre un pourcentage entre 0 et 100");
	        
	        try {
                WebDriverWait wait = new WebDriverWait(selenium, Duration.ofSeconds(7));
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(property)));
            }catch(Exception e) {
            	return Fonctions.logStepKO(teststep, selenium, time1, "Element is not found");
            }
	        
	        System.out.println(element);
			int endbar = element.getSize().getWidth();
			int yAxis = element.getLocation().getY() + (element.getSize().getHeight() / 2);
		
	        int startbar;
	        if(parameter.contains("|")) {
	        	if(parts[1].equals("gauche") || parts[1].equals("Gauche"))
	        		startbar = element.getLocation().getX() + 10;
	        	else if(parts[1].equals("droite") || parts[1].equals("Droite"))
	        		startbar = element.getLocation().getX() + endbar - 10;
	        	else 
	        		return Fonctions.logStepKO(teststep, selenium, time1, "Le 2e paramètre doit etre droite ou gauche");
	        }
	        else
	        	startbar = element.getLocation().getX() + 10;
	        
			
			System.out.println(startbar+" "+endbar+" "+yAxis);
			int end = (int) (endbar * fin/100);
			
			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence dragAndDrop = new Sequence(finger, 1);
			dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startbar, yAxis));
			dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(3000), PointerInput.Origin.viewport(), end, yAxis));
			dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	
	        ((AppiumDriver) selenium).perform(Arrays.asList(dragAndDrop));
	   }catch(Exception e) {
	        return Fonctions.logStepKO(teststep, selenium, time1, "Error");
	   }
        return Fonctions.logStepOK(teststep, selenium, time1);
	}

	public static String[] splitString(String texte)
	{
		String initial_string = texte;
		if (texte.charAt(0) == '@' || texte.contains("|@")) {
			initial_string = initial_string.replace('=', '|');
			String[] parts = initial_string.split("\\|");
			
			return parts;
		} else {
			String[] parts = initial_string.split("\\|");
			if(parts[0].isEmpty()){
				String[] newparts = new String[parts.length-1];
				for(int i=0, k=0;i<parts.length;i++){
					if(!parts[i].isEmpty()){
						newparts[k]=parts[i];
						k++;
					}
				}
				return newparts;
			} else {
				return parts;
			}
		}
	}
}