package scripts_textengine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import scripts_techniques.Config;
import scripts_techniques.Selenium.Teststep;
import scripts_techniques.Selenium.Fonctions;

import static scripts_textengine.TextEngine.*;

public class ActionsACoderTE {
	
	 public static boolean  myclickbyid(String prop) throws IOException {
		WebDriver selenium = TextEngine.selfdriver;
		Teststep teststep = initTeststep("id", "myclickbyname", prop, null, TextEngine.nom);
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(selenium, Config.timeout_elements);
		WebElement myObj;
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
		}
		try {
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			myObj.click();

			return Fonctions.logStepOK(teststep, selenium, time1);
															  
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform action in obj : " +e.getMessage());	
		}		
	}
	 
	 public static boolean  mysendbyname(String prop, String param) throws IOException {
			WebDriver selenium = TextEngine.selfdriver;
			Teststep teststep = initTeststep("name", "mysendbyname", prop, param, TextEngine.nom);
			Date time1 = new Date();
			try {
				Thread.sleep(Config.pause_actions);
				} catch (InterruptedException e) {
					e.printStackTrace();
			}
			WebDriverWait wait = new WebDriverWait(selenium, Config.timeout_elements);
			WebElement myObj;
		   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
			if (array_prop_object.isEmpty()){
				return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
			}
			try {
				myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
				myObj.sendKeys(teststep.param);

				return Fonctions.logStepOK(teststep, selenium, time1);
																  
			} catch (Exception e) {
				System.out.println (e.getMessage());
				return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform action in obj : " +e.getMessage());	
			}		
		}
	 
	 public static boolean  mycheckinnertext(String prop, String param) throws IOException {
			WebDriver selenium = TextEngine.selfdriver;
			Teststep teststep = initTeststep("title", "mycheckinnertext", prop, param, TextEngine.nom);
			Date time1 = new Date();
			try {
				Thread.sleep(Config.pause_actions);
				} catch (InterruptedException e) {
					e.printStackTrace();
			}
			WebDriverWait wait = new WebDriverWait(selenium, Config.timeout_elements);
			WebElement myObj;
		   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
			if (array_prop_object.isEmpty()){
				return Fonctions.logStepKO(teststep, selenium, time1, "No such file :"+  teststep.object_attach_name);
			}
			try {

				WebElement myHTML;
				if (array_prop_object.containsKey("title")) {
					myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/head/title[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'"+array_prop_object.get("title").toLowerCase().replaceAll("\\s", "")+"')]/../../body")));
				} else {
					myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
				}
				if (myHTML.getText().contains(param)) {
					return Fonctions.logStepOK(teststep, selenium, time1);
				} else return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Le texte " + param + " n'est pas present sur la page.");

																  
			} catch (Exception e) {
				System.out.println (e.getMessage());
				return Fonctions.logStepKO(teststep, selenium, time1, "Cannot perform action in obj : " +e.getMessage());	
			}		
		}
}
