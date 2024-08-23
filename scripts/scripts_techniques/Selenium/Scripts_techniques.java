package scripts_techniques.Selenium;
import com.beust.jcommander.Parameter;
import scripts_techniques.Config;
import scripts_techniques.Selenium.Teststep;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import io.appium.java_client.android.AndroidDriver;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.ini4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

//import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import static io.restassured.RestAssured.given;

import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.*;
import io.restassured.response.ResponseBody;
import io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import java.util.Properties;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.extractor.*;
import org.apache.poi.openxml4j.opc.*;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.hwpf.extractor.WordExtractor;
// import radomik.com.github.resemble.analysis.ResembleAnaylsisOptionsTemplates;
// import radomik.com.github.resemble.gui.ImageWindow;
// import radomik.com.github.resemble.analysis.ResembleAnalysis;
// import radomik.com.github.resemble.analysis.ResembleAnalysisOptions;
// import radomik.com.github.resemble.analysis.ResembleAnalysisResults;
// import radomik.com.github.resemble.parser.ResembleParser;
// import radomik.com.github.resemble.parser.ResembleParserData;
// import radomik.com.github.resemble.utils.ImageUtils;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.TestResultContainer;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;

import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.text.DecimalFormat;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.Expression;

public class Scripts_techniques {
	static char str='"';

	// #######################################
	// ############ SearchElement ############
	// #######################################
	
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
    
	// ############################################
	// ############ Actions properties ############
	// ############################################ 
	
	public static boolean  Webobject_checkbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je verifie id";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
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
					return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : La valeur n'a pas ete trouve : "+  myObj.getAttribute("value"));
				}
			} else {
				if (myObj.getText().equals(parameter)) {
					System.out.println ("The text was found");
					return Fonctions.logStepOK(teststep, selenium, time1);
				} else {
					System.out.println ("Text not found.   found : "+  myObj.getText());
					return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : La valeur n'a pas ete trouve : "+  myObj.getText());		
				}		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_checkbyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je verifie name";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			if (myObj.getAttribute("value") != null) {
				if (myObj.getAttribute("value").equals(parameter)) {
					System.out.println ("The value was found");
					return Fonctions.logStepOK(teststep, selenium, time1);
				} else {
					System.out.println ("Value not found.   found : "+  myObj.getAttribute("value"));
					return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : La valeur n'a pas ete trouve : "+  myObj.getAttribute("value"));
				}
			} else {
				if (myObj.getText().equals(parameter)) {
					System.out.println ("The text was found");
					return Fonctions.logStepOK(teststep, selenium, time1);
				} else {
					System.out.println ("Text not found.   found : "+  myObj.getText());
					return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : La valeur n'a pas ete trouve : "+  myObj.getText());		
				}		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_checkbyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je verifie xpath";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			Fonctions.highLighterMethod(driverInstance, myObj);

			if (myObj.getAttribute("value") != null) {
				if (myObj.getAttribute("value").equals(parameter)) {
					System.out.println ("The value was found");
					return Fonctions.logStepOK(teststep, selenium, time1);
				} else {
					System.out.println ("Value not found.   found : "+  myObj.getAttribute("value"));
					return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : La valeur n'a pas ete trouve : "+  myObj.getAttribute("value"));
				}
			} else {
				if (myObj.getText().equals(parameter)) {
					System.out.println ("The text was found");
					return Fonctions.logStepOK(teststep, selenium, time1);
				} else {
					System.out.println ("Text not found.   found : "+  myObj.getText());
					return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : La valeur n'a pas ete trouve : "+  myObj.getText());		
				}		
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}	
	}
	
	public static boolean  Webobject_clickbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je clique id";
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
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			try {
				myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
				Fonctions.highLighterMethod(driverInstance, myObj);
				myObj.click();
				System.out.println("##### click made on : \n"+ myObj + "\n");
				return Fonctions.logStepOK(teststep, selenium, time1);
			} catch (Exception e) {
				System.out.println ("##### Element.click fail #####");
			}
			try {
				myObj = getShadowElement(driverInstance, "[id="+array_prop_object.get("id")+"]");
				if(myObj != null)
				{
					myObj.click();
					System.out.println("##### click made on : \n"+ myObj + "\n");
					return Fonctions.logStepOK(teststep, selenium, time1);
				}
			} catch (Exception e) {
				System.out.println ("##### click in shadow fail #####");
			}
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue lors du clic.");
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue. ");	
		}		
	}
	
	public static boolean  Webobject_clickbyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je clique name";
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
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichire n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			try {
				myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
				Fonctions.highLighterMethod(driverInstance, myObj);
				myObj.click();
				System.out.println("##### click made on : \n"+ myObj + "\n");
				return Fonctions.logStepOK(teststep, selenium, time1);
			} catch (Exception e) {
				System.out.println ("##### Element.click fail #####");
			}
			try {
				myObj = getShadowElement(driverInstance, "[name="+array_prop_object.get("name")+"]");
				if(myObj != null)
				{
					myObj.click();
					System.out.println("##### click made on : \n"+ myObj + "\n");
					return Fonctions.logStepOK(teststep, selenium, time1);
				}
			} catch (Exception e) {
				System.out.println ("##### click in shadow fail #####");
			}
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue lors du clic.");
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}
	
	public static boolean  Webobject_clickbytitle(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je clique title";
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
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(array_prop_object.get("title"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.click();
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}
	
	public static boolean  Webobject_clickbyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je clique xpath";
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
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.click();
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}
	
	public static boolean  Webobject_keysbyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis xpath";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1),teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}
	
	public static boolean  Webobject_keysbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis id";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1),teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}
	
	public static boolean  Webobject_keysbyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis name";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1),teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_Highlightbyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je highlight name";
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
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
					Fonctions.highLighterMethod(driverInstance, myObj);

			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_Highlightbyclass(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je highlight classe";
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
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(array_prop_object.get("classname"))));
					Fonctions.highLighterMethod(driverInstance, myObj);

			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_Highlightbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je highlight id";
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
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
					Fonctions.highLighterMethod(driverInstance, myObj);

			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_Highlightbyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je highlight xpath";
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
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
					Fonctions.highLighterMethod(driverInstance, myObj);

			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectindexbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionne l'index id";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.id(array_prop_object.get("id"))));
			selectobj .selectByIndex(Integer.parseInt(parameter));
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectindexbyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionne l'index name";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.name(array_prop_object.get("name"))));
			selectobj .selectByIndex(Integer.parseInt(parameter));
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectindexbyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionne l'index xpath";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.xpath(array_prop_object.get("xpath"))));
			selectobj .selectByIndex(Integer.parseInt(parameter));
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectvaluebyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionne la valeur id";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.id(array_prop_object.get("id"))));
			selectobj .selectByValue(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectvaluebyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionnela valeur name";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.name(array_prop_object.get("name"))));
			selectobj .selectByValue(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectvaluebyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionne la valeur xpath";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.xpath(array_prop_object.get("xpath"))));
			selectobj .selectByValue(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectvisibletextbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionne id";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.id(array_prop_object.get("id"))));
			selectobj .selectByVisibleText(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectvisibletextbyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionne name";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.name(array_prop_object.get("name"))));
			selectobj .selectByVisibleText(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectvisibletextbyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je selectionne xpath";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
					Select selectobj = new Select(driverInstance.findElement(By.xpath(array_prop_object.get("xpath"))));
			selectobj .selectByVisibleText(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_sendkeysbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis id";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_sendkeysbyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis name";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_sendkeysbyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis xpath";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectkeysbyid(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis id";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(array_prop_object.get("id"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectkeysbyname(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis name";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean  Webobject_selectkeysbyxpath(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis xpath";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
			Fonctions.highLighterMethod(driverInstance, myObj);
			myObj.sendKeys(parameter);
			return Fonctions.logStepOK(teststep, selenium, time1);
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}
    
	public static boolean WebObject_dragbyxpath(final WebDriver driver, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je glisse xpath";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_elements));
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			WebElement element;
			WebElement destination;
			String property;
			if (array_prop_object.get("xpath").charAt(0) == '$'){
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					property = ini.get("property", array_prop_object.get("xpath").substring(1));
				} catch(Exception e) {
					System.out.println ("The property was not found in the variable file.");
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete n'a pas ete trouvee dans le fichier des variables.");
				}
			} else {
				property = array_prop_object.get("xpath");
			}
			String[] parts = splitString(property);
			
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(parts[0])));
			
			destination = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(parts[1])));
			
			if(element != null && destination != null)
			{
				Fonctions.highLighterMethod(driver, element);
				Fonctions.highLighterMethod(driver, destination);
				try {
					Actions act=new Actions(driver);		
					act.dragAndDrop(element, destination).build().perform();
					System.out.println("##### drag made on "+ element + " to " + destination + "\n");
					return Fonctions.logStepOK(teststep, driver, time1);
				} catch (Exception e) {
					System.out.println ("##### drag fail #####");
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue lors du glissement de l'objet.");
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "L'objet n'a pas ete trouve.");
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue.");	
		}	
	}
	
	public static boolean WebObject_checknbelementbyxpath(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "Je compte les elements";
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
		Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			try{
				driverInstance.findElement(By.xpath(array_prop_object.get("xpath")));
			}catch(Exception e){
				System.out.println("Xpath non valide");
				return Fonctions.logStepKO(teststep, selenium, time1, "Le xpath n'est pas valide");
			}
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				if(!isJVExecution(teststep.testcase_label))deleteDatapoolVarFile();
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			int referenceInteger = 0;
			try{
				referenceInteger = Integer.parseInt(parameter);
			}catch(Exception e){
				System.out.println("Le parametre doit etre le nombre d'occurence voulu");
				return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Le parametre doit etre un nombre");
			}
			System.out.println("Le xpath est cense trouver "+ " "+referenceInteger + " element(s)");
			List<WebElement> list = driverInstance.findElements(By.xpath(array_prop_object.get("xpath")));
			if (list.size() == referenceInteger){
				System.out.println("Le nombre d'element trouve est bien "+ referenceInteger);
				return Fonctions.logStepOK(teststep, selenium, time1);
			}else{
				System.out.println("Le nombre d'element trouves est different du parametre");
				System.out.println("Nombre d'element trouve : " + list.size());
				return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Le nombre d'element(s) trouves est different du parametre");
			}
															
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");	
		}		
	}

	public static boolean WebObject_copybyxpath(WebDriver driver, Teststep teststep) throws IOException {
		WebElement element;
		String value;
		String property;
		String parameter = teststep.param;
		Date time1 = new Date();
		teststep.action_label = "Je copie par xpath";

		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_elements));
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		if (array_prop_object.get("xpath").charAt(0) == '$'){
			try {
				Wini ini = new Wini(new File(Config.propertyFile));
				property = ini.get("property", array_prop_object.get("xpath").substring(1));
			} catch(Exception e) {
				System.out.println ("The property was not found in the variable file.");
				return Fonctions.logStepKO(teststep, driver, time1, "La propriete n'a pas ete trouvee dans le fichier des variables.");
			}
		} else {
			property = array_prop_object.get("xpath");
		}
		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(property)));
			Fonctions.highLighterMethod(driver, element);
			if (element.getAttribute("value")==null) {
				value = element.getText();
			} else {
				value = element.getAttribute("value");
			}
			teststep.param = value;
			try {
				Wini ini = new Wini(new File(Config.propertyFile));
				ini.put("parameter", parameter, value);
				ini.store();
			} catch (Exception e) {
				e.printStackTrace();
				return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
		return Fonctions.logStepOK(teststep, driver, time1);
	}

	public static boolean WebObject_checkformulabyxpath(WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		teststep.action_label = "Je verifie formule";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_elements));
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}

		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			WebElement element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String parameter = teststep.param;
			String value;			
			if (array_prop_object.get("xpath").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("xpath"));
				if (property.equals(array_prop_object.get("xpath"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("xpath") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else {
				property = array_prop_object.get("xpath");
			}

			String teString = " " + teststep.param;
			char[] chars = teString.toCharArray();
			int indexvar = 0;
			int indexvar2 = 0;
			for (int i = 0; i < chars.length; i++) {
				// System.out.println(i + "\n" + indexvar + "\n" + indexvar2 + "\n" + teString);
				if (chars[i] == '{') {
					indexvar = i;
				} else if (chars[i] == '}') {
					indexvar2 = i;
				}
				if (indexvar != 0 && indexvar2 != 0) {
					teString = teString.replaceFirst("\\{[^{]*\\}", getVariableParameter(teString.substring(indexvar, indexvar2)).replaceAll(",", "\\.").replaceAll("[^0-9.]", ""));
					chars = teString.toCharArray();
					indexvar = 0;
					indexvar2 = 0;
					i = 0;
				}
			}
			Expression exp = new ExpressionBuilder(teString).build();
			double result = exp.evaluate();
			System.out.println(result);
			try {
				teststep.param = String.valueOf(result);
				element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
				if (element != null) {
					Fonctions.highLighterMethod(driver, element);
					if (element.getAttribute("value") != null) {
						if (element.getAttribute("value").contains(String.valueOf(result))) {
							System.out.println ("The value was found");
							return Fonctions.logStepOK(teststep, driver, time1);
						} else {
							System.out.println ("Value not found.   found : "+  element.getAttribute("value"));
							return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : La valeur trouvee est : "+  element.getAttribute("value"));
						}
					} else {
						if (element.getText().contains(String.valueOf(result))) {
							System.out.println ("The text was found");
							return Fonctions.logStepOK(teststep, driver, time1);
						} else {
							System.out.println ("Text not found.   found : "+  element.getText());
							return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : La valeur trouvee est : "+  element.getText());		
						}		
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
		return Fonctions.logStepOK(teststep, driver, time1);
	}
	// #######################################
	// ############# ActionsPage #############
	// ####################################### 
	
	public static boolean  Webpage_waitloadingcomplete(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je charge la page";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		boolean state = false;
		String parameter;
		String mail;
		if(teststep.param.length() !=0){ //On vérifie que le paramètre n'est pas null
			
			if (teststep.param.charAt(0) == '$'){ //Gestion du paramètre sous forme de variable
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
				} else parameter = teststep.param; 
				
			if (parameter.equals("Non") || parameter.equals("non") || parameter.equals("No") || parameter.equals("no")) {
				return Fonctions.logStepOK(teststep, selenium, time1); //Si le parametre est non, l'action ne fait rien (renvoi OK)
			}
		}else parameter = ""; //si le paramètre est vide
		String toAddress[] = new String[]{};//Création du tableau qui stockera toutes les adresses mails
		//Recuperation des adresses emails des destinataires
		try {
			Wini ini = new Wini(new File(Config.propertyFile));
			mail = ini.get("email", "destinataires");
			if(mail.length() != 0){ //Vérifie si la variable contient une adresse
			//isMail = true;
				toAddress = mail.split(",");
				int nbdestinataire = 0;
				for (String emails : toAddress) {
					nbdestinataire++;
					System.out.println("destinataire " + nbdestinataire+" : " +emails.trim());
					try { //Test de la validité des adresses mails
						InternetAddress internetAddress = new InternetAddress(emails);
						internetAddress.validate();
					} catch (AddressException ex) {
						System.out.println("email KO");
						return Fonctions.logStepKO(teststep, selenium, time1, "Une adresse mail n'est pas valide.");//retourne KO si l'adresse mail n'est pas valide
					}
				}
			}else System.out.println("Pas d'adresse mail definie"); //La variable est vide
		}
		catch(Exception e){ //la variable n'existe plus
			System.out.println("Pas d'adresse mail definie");
		}
		
		String host = "smtp-fr.securemail.pro";
		String port = "465 ";
		String username = "assistance.client@kalios.com";
		String password = "kalios2023";

        	// Propriétés de la session
        	Properties props = new Properties();
        	props.put("mail.smtp.auth", "true");
        	props.put("mail.smtp.starttls.enable", "true");
        	props.put("mail.smtp.host", host);
        	props.put("mail.smtp.port", port);

			Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
		long startTime = System.currentTimeMillis(); //démarrage du timer
		
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		WebElement myHTML;

		try {
			if (array_prop_object.containsKey("title")) {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/head/title[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'"+array_prop_object.get("title").toLowerCase().replaceAll("\\s", "")+"')]")));
			} else {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
			}
			int count=0;
			while(!state && count<30) {
				state=(((JavascriptExecutor) selenium).executeScript("return document.readyState").equals("complete"));
				count++;
				System.out.println ("Count:" + count + " "+ state);
			}
				System.out.println ("WaitLoad");
				System.out.println ("Page affichée ? " + myHTML.isDisplayed());
			}
		catch (Exception e) {
			System.out.println("page non trouvée");
			try {
            // Création du message
				for (String adresse:toAddress) {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(adresse));
					message.setSubject("Service indisponible");
					message.setText("Nous vous informons que nous avons détecté un problème de disponibilité sur le site " + teststep.application_label + " :"
					+ "\n- nom du parcours : " + teststep.testcase_label
					+ "\n- nom de l'étape : " + teststep.module_label
					+ "\nNous vous conseillons d’informer vos équipes techniques afin de rétablir la situation.");
					System.out.println(host + port);
					// Envoi du message
					Transport.send(message);

					System.out.println("E-mail envoyé avec succès à l'adresse : " + adresse);
				}
			} catch (MessagingException ex) {
				ex.printStackTrace();
				System.err.println("Erreur lors de l'envoi de l'e-mail : " + ex.getMessage());
			}
		return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas charge.");
		}

		long endTime = System.currentTimeMillis();// Arrêter le chronomètre
		long pageLoadTime = endTime - startTime;// Calculer le temps de chargement
		System.out.println("Temps de chargement :" + pageLoadTime + " ms");
		
		if (parameter.equals("")){
			// System.out.println("pas de paramètre");
			return Fonctions.logStepOK(teststep, selenium, time1);
		}
		
		int timeMax;
		try {
			timeMax = Integer.parseInt(parameter)*1000; //conversion en entier
			// System.out.println("Temps maximal" + timeMax);
			if (pageLoadTime<timeMax) {
				return Fonctions.logStepOK(teststep, selenium, time1);
			} 

			else {
				System.out.println("La page a pris trop de temps à charger");
				try {
            // Création du message
					for (String adresse:toAddress) {
						Message message = new MimeMessage(session);
						message.setFrom(new InternetAddress(username));
						message.setRecipient(Message.RecipientType.TO, new InternetAddress(adresse));
						message.setSubject("Service non performant");
						message.setText("Nous vous informons que nous avons détecté un problème de performance sur le site " + teststep.application_label + " :"
					+ "\n- nom du parcours : " + teststep.testcase_label
					+ "\n- nom de l'étape : " + teststep.module_label
					+ "\n- temps de chargement : " + pageLoadTime
					+ "\nNous vous conseillons d’informer vos équipes techniques afin de rétablir la situation.");
						// Envoi du message
						Transport.send(message);
						System.out.println("E-mail envoye avec succes à l adresse : " + adresse);
					}
				} catch (MessagingException ex) {
					ex.printStackTrace();
					System.err.println("Erreur lors de l'envoi de l'e-mail : " + ex.getMessage());
				}
				return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : La page a pris trop de temps a charger ( > " + timeMax + " secondes)");
			}
		}
		catch (NumberFormatException e) {
			return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'est pas un nombre entier");
		}
	}

	public static boolean  Webpage_close(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je ferme";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		WebElement myHTML;
		
		try {
			if (array_prop_object.containsKey("title")) {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/head/title[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'"+array_prop_object.get("title").toLowerCase().replaceAll("\\s", "")+"')]")));
			} else {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
			}
			System.out.println ("WaitLoad");
			System.out.println ("Page affichée ? " + myHTML.isDisplayed());
			driverInstance.close();
			return Fonctions.logStepOK(teststep, selenium, time1);
		}
		catch (Exception e) {
			return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
		}


	}

	public static boolean  WebPage_checkinnertext(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je verifie";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		WebElement myHTML;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
			
		
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			if (array_prop_object.containsKey("title")) {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/head/title[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'"+array_prop_object.get("title").toLowerCase().replaceAll("\\s", "")+"')]/../../body")));
			} else {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
			}
			System.out.println ("Page affichée ? " + myHTML.isDisplayed());
			if (parameter.equals("")){
				return Fonctions.logStepOK(teststep, selenium, time1);
			}
			
			if (myHTML.getText().contains(parameter)) {
				return Fonctions.logStepOK(teststep, selenium, time1);
			} else return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Le texte " + parameter + " n'est pas present sur la page.");
		}
		catch (Exception e) {
			return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
		}
    }
	
	public static boolean WebPage_executer_bat(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "J'execute le bat";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()) {
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :" + teststep.object_attach_name);
		}
		String parameter;
		if (teststep.param.charAt(0) == '$'){
			parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
		} else {
			parameter = teststep.param;
		}
		try {
			if (Pattern.matches("[a-zA-Z0-9_]+\\.bat", parameter)) {
				try {
					String fichier = Config.additional_files + "/" + parameter;
					ProcessBuilder pb = new ProcessBuilder(fichier);
					Process p = pb.start();
					StringBuilder str = new StringBuilder();
					InputStreamReader isr = new InputStreamReader(p.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					String line;
					while ((line = br.readLine()) != null) {
						str.append(line + "\n");
					}
					p.waitFor();
					System.out.println(str);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return Fonctions.logStepKO(teststep, selenium, time1, e.getMessage());
				}
			} else {
				System.out.println("Le fichier n'est pas un fichier .bat");
				return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier .bat n'a pas ete trouve.");
			}

			return Fonctions.logStepOK(teststep, selenium, time1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Un probleme est survenu");
		}
	}
	
	public static boolean WebPage_checkpdf(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "Je verifie le PDF";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()) {
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :" + teststep.object_attach_name);
		}
		String parameter;
		if (teststep.param.charAt(0) == '$'){
			parameter = getVariableParameter(teststep.param);
			if (parameter.equals(teststep.param)) {
				return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
			}
		} else {
			parameter = teststep.param;
		}
		try {
			String[] params = parameter.split("\\|");
			String pdfFile = params[0];
			String txtFile = params[1];

			File existFile = new File(pdfFile);
			for (int i = 0; i < Config.timeout_download; i++) {
				if (existFile.exists()) {
					if (Pattern.matches("[\\p{javaLowerCase}\\p{javaUpperCase}\\w\\-:/]+\\.pdf", pdfFile)) {
						if (Pattern.matches("[\\p{javaLowerCase}\\p{javaUpperCase}\\w\\-]+\\.txt", txtFile)) {
							try {
								String fichierpdf = pdfFile;
								String fichiertxt = Config.additional_files + "/" + txtFile;

								// Récupérer le pdf dans un String
								org.apache.pdfbox.pdmodel.PDDocument pdDoc = org.apache.pdfbox.pdmodel.PDDocument
										.load(new java.io.File(fichierpdf));
								org.apache.pdfbox.text.PDFTextStripper pdfStripper = new org.apache.pdfbox.text.PDFTextStripper();
								String parsedText = pdfStripper.getText(pdDoc);
								pdDoc.close();

								// Envoyer le String dans un fichier txt
								String newtxtfile = Config.additional_files;
								File fichier = File.createTempFile("pdftemp", ".tmp", new File(newtxtfile));
								fichier.deleteOnExit();
								PrintWriter testPrintWriter = new PrintWriter(fichier, "UTF-8");
								testPrintWriter.println(parsedText);
								testPrintWriter.close();

								// Récupérer le contenu du txt du pdf dans un String
								BufferedReader pdfreader = new BufferedReader(new FileReader(fichier));
								String pdfligne, pdfaddligne = "";
								while ((pdfligne = pdfreader.readLine()) != null) {
									pdfaddligne = pdfaddligne.concat(pdfligne + "\n");
								}
								pdfreader.close();
								pdfaddligne = pdfaddligne.substring(0, pdfaddligne.length() - 1);

								// Récupérer le contenu du txt dans un String
								BufferedReader txtreader = new BufferedReader(new FileReader(new File(fichiertxt)));
								String txtligne, txtaddligne = "";
								while ((txtligne = txtreader.readLine()) != null) {
									txtaddligne = txtaddligne.concat(txtligne + "\n");
								}
								txtaddligne = txtaddligne.substring(0, txtaddligne.length() - 1);

								String pdfString = new String(pdfaddligne.getBytes(), "UTF-8");
								String txtString = new String(txtaddligne.getBytes(), "UTF-8");

								if (pdfString.contains(txtString)) {
									System.out.println("Le fichier PDF contient le texte.");
									return Fonctions.logStepOK(teststep, selenium, time1);
								} else {
									System.out.println("Le fichier PDF ne contient pas le texte.");
									return Fonctions.logStepWarning(teststep, selenium, time1,
											"WARNING : Le texte " + txtString + " n'est pas present dans le fichier PDF");
								}

							} catch (Exception e) {
								System.out.println(e.getMessage());
								return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");
							}
						} else if (Pattern.matches(".+", txtFile)) {
							try {
								String fichierpdf = pdfFile;

								// Récupérer le pdf dans un String
								org.apache.pdfbox.pdmodel.PDDocument pdDoc = org.apache.pdfbox.pdmodel.PDDocument
										.load(new java.io.File(fichierpdf));
								org.apache.pdfbox.text.PDFTextStripper pdfStripper = new org.apache.pdfbox.text.PDFTextStripper();
								String parsedText = pdfStripper.getText(pdDoc);
								pdDoc.close();

								// Envoyer le String dans un fichier txt
								String newtxtfile = Config.additional_files;
								File fichier = File.createTempFile("pdftemp", ".tmp", new File(newtxtfile));
								fichier.deleteOnExit();
								PrintWriter testPrintWriter = new PrintWriter(fichier, "UTF-8");
								testPrintWriter.println(parsedText);
								testPrintWriter.close();

								// Récupérer le contenu du txt du pdf dans un String
								BufferedReader pdfreader = new BufferedReader(new FileReader(fichier));
								String pdfligne, pdfaddligne = "";
								while ((pdfligne = pdfreader.readLine()) != null) {
									pdfaddligne = pdfaddligne.concat(pdfligne + "\n");
								}
								pdfreader.close();
								pdfaddligne = pdfaddligne.substring(0, pdfaddligne.length() - 1);

								String pdfstring = new String(pdfaddligne.getBytes(), "UTF-8");
								if (pdfstring.contains(txtFile)) {
									System.out.println("Le fichier PDF contient le texte.");
									return Fonctions.logStepOK(teststep, selenium, time1);
								} else {
									return Fonctions.logStepWarning(teststep, selenium, time1,
									"WARNING : Le texte " + txtFile + " n'est pas present dans le fichier PDF");
								}
							} catch (Exception e) {
								System.out.println(e.getMessage());
								return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");
							}

						} else {
							System.out.println("Not a .txt file");
							return Fonctions.logStepKO(teststep, selenium, time1,
									"Impossible de trouver le fichier TXT");
						}
					} else {
						System.out.println("Not a .pdf file");
						return Fonctions.logStepKO(teststep, selenium, time1, "Impossible de trouver le fichier PDF");
					}
				}
				Thread.sleep(1000);
			}
			System.out.println("Fichier non trouvé");
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'a pas ete trouve.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, selenium, time1, "Un probleme est survenu.");
		}
	}
	
	public static boolean WebPage_checkcsv(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "Je compare les CSV";
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()) {
            return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :" + teststep.object_attach_name);
        }
        try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
            String[] params = parameter.split("\\|");
            String csv1 = params[0],
                    csv2 = params[1];
            String fichier1 = Config.additional_files + "/" + csv1,
                    fichier2 = csv2;
            File csv1File = new File(fichier1),
                    csv2File = new File(fichier2);
            for (int i = 0; i < Config.timeout_download; i++) {
                if (csv2File.exists()) {
                    if (Pattern.matches("[\\p{javaLowerCase}\\p{javaUpperCase}\\w\\-]+\\.csv", csv1)
                            && Pattern.matches("[\\p{javaLowerCase}\\p{javaUpperCase}\\w\\-:/]+\\.csv", csv2)
                            && csv1File.exists()
                            && csv2File.exists()) {
                        try {
                            // PREMIER CSV
                            CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(false)
                                    .build();
                            CSVReader csvReader = new CSVReaderBuilder(new FileReader(csv1File))
                                    .withCSVParser(csvParser)
                                    .build();
                            // Récupérer les dimensions du csv
                            String[] nextline;
                            int j = 0;
                            int k = 0;
                            while ((nextline = csvReader.readNext()) != null) {
                                j = j + 1;
                                k = nextline.length;
                            }
                            csvReader.close();
                            int nb1 = k;
                            int nbl1 = j;
                            // Créer le tableau qui contient le csv
                            csvReader = new CSVReaderBuilder(new FileReader(csv1File)).withCSVParser(csvParser).build();
                            String[][] total = new String[k][j];
                            j = 0;
                            while ((nextline = csvReader.readNext()) != null) {
                                if (nextline != null) {
                                    for (i = 0; i < nextline.length; i++) {
                                        total[i][j] = nextline[i];
                                    }
                                }
                                j = j + 1;
                            }
                            csvReader.close();
                            if (total[0][0].getBytes()[0] == (byte) 0xEF && total[0][0].getBytes()[1] == (byte) 0xBB
                                    && total[0][0].getBytes()[2] == (byte) 0xBF) {
                                if (total[0][0].contains("\"")) {
                                    total[0][0] = total[0][0].substring(4);
                                } else {
                                    total[0][0] = total[0][0].substring(3);
                                }
                            } // Si le fichier est en UTF-8 with BOM, on supprime les 3 premiers octets du
                                // document
                            System.out.println(
                                    "########################################################################");
                            //// Écrire le tableau
                            // for (i = 0; i < total.length; i++) {
                            // System.out.println(Arrays.toString(total[i]));
                            // }
                            // System.out.println(
                            // "########################################################################");
                            // DEUXIEME CSV
                            csvReader = new CSVReaderBuilder(new FileReader(csv2File)).withCSVParser(csvParser)
                                    .build();
                            j = 0;
                            k = 0;
                            while ((nextline = csvReader.readNext()) != null) {
                                j += 1;
                                k = nextline.length;
                            }
                            csvReader.close();
                            int nb2 = k;
                            // Créer le tab
                            csvReader = new CSVReaderBuilder(new FileReader(csv2File)).withCSVParser(csvParser)
                                    .build();
                            String[][] total2 = new String[k][j];
                            j = 0;
                            while ((nextline = csvReader.readNext()) != null) {
                                if (nextline != null) {
                                    for (i = 0; i < nextline.length; i++) {
                                        total2[i][j] = nextline[i];
                                    }
                                }
                                j += 1;
                            }
                            csvReader.close();
                            if (total2[0][0].getBytes()[0] == (byte) 0xEF && total2[0][0].getBytes()[1] == (byte) 0xBB
                                    && total2[0][0].getBytes()[2] == (byte) 0xBF) {
                                if (total2[0][0].contains("\"")) {
                                    total2[0][0] = total2[0][0].substring(4);
                                } else {
                                    total2[0][0] = total2[0][0].substring(3);
                                }
                            } // Si le fichier est en UTF-8 with BOM, on supprime les 3 premiers octets du
                                // document
                                // Écrire le tab
                                // for (i = 0; i < total2.length; i++) {
                                // System.out.println(Arrays.toString(total2[i]));
                                // }
                                // System.out.println(
                                // "########################################################################");
                            // Faire la comparaison
                            System.out.println("Comparaison de " + fichier1 + " et de " + fichier2
                                    + "\n########################################################################");
                            if (nb1 > nb2) {
                                System.out.println("Le fichier de référence contient plus de colonnes (" + nb1
                                        + ") que le fichier téléchargé (" + nb2 + ")");
                                return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Les fichiers sont differents");
                            }
                            if (nbl1 != j) {
                                System.out.println("Le fichier de référence ne contient pas autant de lignes (" + nbl1
                                        + ") que le fichier téléchargé (" + j + ")");
                                return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Les fichiers sont differents");
                            }
                            int nbErreur = 0, nbOk = 0, errLigne, errColonne, test = 0;
                            String errMsg = "", errtmp;
                            for (i = 0; i < total.length; i++) {
                                test = 0;
                                for (int l = 0; l < total2.length; l++) {
                                    errColonne = i + 1;
                                    errtmp = "\nDifférences sur la colonne " + errColonne + " sur les lignes ";
                                    if (total[i][0].equals(total2[l][0])) {// Même nom de colonne
                                        for (int l2 = 0; l2 < nbl1; l2++) {
                                            if (total[i][l2].equals(total2[l][l2])) {
                                                nbOk++;
                                            } else {// Ajout de la cellule qui contient l'erreur au message d'erreur
                                                nbErreur++;
                                                errMsg += errtmp;
                                                errLigne = l2 + 1;
                                                errtmp = errLigne + ", ";
                                                errMsg += errtmp;
                                                errtmp = "";
                                            }
                                        }
                                        if (nbErreur > 0 && errtmp.length() == 0) {
                                            errMsg = errMsg.substring(0, errMsg.length() - 2);
                                        }
                                        l = total2.length;
                                    } else {
                                        test++;
                                    }
                                }
                                if (test >= total2.length) {
                                    System.out.println(
                                            "La colonne " + total[i][0]
                                                    + " du fichier de référence n'est pas dans le fichier téléchargé");
                                    return Fonctions.logStepWarning(teststep, selenium, time1,
                                            "WARNING : Les fichiers sont differents.");
                                }
                            }
                            System.out.println("Sur le fichier de référence :" + errMsg);
                            System.out.println("##################\tBilan d'exécution\t##################");
                            System.out.println("Nombre de cellules contenant des différences : " + nbErreur);
                            System.out.println("Nombre de cellules ok : " + nbOk);
                            if (nbErreur != 0) {
                                return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Les fichiers sont differents.");
                            }
                            return Fonctions.logStepOK(teststep, selenium, time1);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            return Fonctions.logStepKO(teststep, selenium, time1, e.getMessage());
                        }
                    } else {
                        System.out.println("Un des deux fichiers au moins n'existe pas");
                        return Fonctions.logStepKO(teststep, selenium, time1,
                                "Un fichier n'a pas ete trouve.");
                    }
                }
                Thread.sleep(1000);
            }
            System.out.println("Fichier non trouvé");
            return Fonctions.logStepKO(teststep, selenium, time1, "Un fichier n'a pas ete trouve.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");
        }
    }
	
    public static boolean WebPage_wait(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "J'attends";
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length() - 1) == ']') {
                String tempsattente = teststep.param.substring(1, teststep.param.length() - 1);
                System.out.println("Pause de " + tempsattente + " secondes");
                int attente = Integer.parseInt(tempsattente) * 1000;
                Thread.sleep(attente);
                return Fonctions.logStepOK(teststep, selenium, time1);
            } else {
                char str = '"';
                String xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + teststep.param.toLowerCase().replaceAll("\\s", "") + str + ")]]";
                WebElement element = findElements(selenium, xpath);
                System.out.println("Recherche de " + teststep.param + " sur la page :");
                try {
                    WebDriverWait wait = new WebDriverWait(selenium, Duration.ofMillis(10000));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                    System.out.println("Recherche terminé, élément trouvé");
                    return Fonctions.logStepOK(teststep, selenium, time1);
                } catch (Exception e) {
                    return Fonctions.logStepKO(teststep, selenium, time1, "L'objet " + teststep.param + " n'a pas ete trouve.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la pause");
            return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");
        }
    }
	
	public static boolean WebPage_activateframe(WebDriver selenium, Teststep teststep)
	{		
		teststep.action_label = "J'active la frame";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		WebElement myHTML;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			if (array_prop_object.containsKey("title")) {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/head/title[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'"+array_prop_object.get("title").toLowerCase().replaceAll("\\s", "")+"')]/../../body")));
			} else {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
			}
			System.out.println ("Page displayed ? " + myHTML.isDisplayed());
			if (parameter.length() <= 2){
				driverInstance.switchTo().frame(Integer.parseInt(parameter));
			} else {
				driverInstance.switchTo().frame(parameter);
			}
			return Fonctions.logStepOK(teststep, selenium, time1);
		}
		catch (Exception e) {
			return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
		}
	}

	public static boolean WebPage_desactivateframe(WebDriver selenium, Teststep teststep)
	{		
		teststep.action_label = "Je desactive la frame";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		WebElement myHTML;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
	 
	
		try {
			if (array_prop_object.containsKey("title")) {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/head/title[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'"+array_prop_object.get("title").toLowerCase().replaceAll("\\s", "")+"')]/../../body")));
			} else {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
			}
			System.out.println ("Page displayed ? " + myHTML.isDisplayed());
			
			driverInstance.switchTo().defaultContent();
			return Fonctions.logStepOK(teststep, selenium, time1);
		}
		catch (Exception e) {
			return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
		}
	}
	
	public static boolean WebPage_activatetab(WebDriver selenium, Teststep teststep)
	{		
		teststep.action_label = "J'active l'onglet";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		WebElement myHTML;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			if (array_prop_object.containsKey("title")) {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/head/title[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'"+array_prop_object.get("title").toLowerCase().replaceAll("\\s", "")+"')]/../../body")));
			} else {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
			}
			System.out.println ("Page affichée ? " + myHTML.isDisplayed());
			
			//String originalWindow = driver.getWindowHandle();
			int indexList = 0;
			for (String windowHandle : driverInstance.getWindowHandles()) {
				if (parameter.charAt(0) == '[' && parameter.charAt(parameter.length() - 1) == ']'){
					int index = Integer.parseInt(parameter.substring(1, parameter.length() - 1));
					if(indexList == index) {
						driverInstance.switchTo().window(windowHandle);
						return Fonctions.logStepOK(teststep, selenium, time1);
					}
					indexList++;
				} else {
					if(driverInstance.switchTo().window(windowHandle).getTitle().equals(parameter)) {
						driverInstance.switchTo().window(windowHandle);
						return Fonctions.logStepOK(teststep, selenium, time1);
					}
				}
			}
			return Fonctions.logStepKO(teststep, selenium, time1, "L'onglet n'a pas ete trouve.");
		}
		catch (Exception e) {
			return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
		}
	}

	public static boolean WebPage_requeteAPI(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "J'appelle l'API";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		//Get what Type of request do we have to do 
		String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
		String[] params = parameter.split("\\|");
		String requestName = params[0].toLowerCase();
		switch (requestName){
			case "get":
				System.out.println("get");
				String nameOfFile;
				String url;
				if (params[1].charAt(0) == '$'){
					try {
						Wini ini = new Wini(new File(Config.propertyFile));
						url = ini.get("api", params[1].substring(1));
						nameOfFile = params[1].substring(1);
					} catch(Exception e) {
						System.out.println ("The url was not found in the variable file.");
						return Fonctions.logStepKO(teststep, selenium, time1, "L'URL n'a pas ete trouvee dans le fichier des variables.");
					}
				} else {
					url = params[1];
					nameOfFile = params[1];
				}
				// verification de l'URL 
				try{
					//Create request
					RequestSpecification res = given();
					Response response = res.get(url);
				}catch(Exception e){
					System.out.println("L'URL fournie n'est pas valide");
					return Fonctions.logStepKO(teststep, selenium, time1, "L'URL fournie n'est pas valide");
				}
				
				//Get  status code request
				int statusCode= given()
					.when().get(url).getStatusCode();
				System.out.println("The response status is "+statusCode);

				// manage result
				given().when().get(url);
				String path = "additional_files/api/results/"+nameOfFile+".json";
				if (statusCode != 200 && statusCode != 304){
					System.out.println("Un probleme est survenu lors de la requete");
					return Fonctions.logStepKO(teststep, selenium, time1, "Un probleme est survenu lors de la requete.");
				}
				else {
					Response response = given().get(url);
					ResponseBody body = response.getBody();
					String toWrite = body.asString();
					try  {
						PrintWriter out = new PrintWriter(new FileWriter(path));
						out.write(toWrite);
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (statusCode == 304){
						System.out.println("CODE 304 :");
						return Fonctions.logStepOK(teststep, selenium, time1);
					}
					System.out.println("Status Code = 200 OK");
					return Fonctions.logStepOK(teststep, selenium, time1);
				}
			case "post":
				System.out.println("post");
				String pathOfFile;
				if (params[1].charAt(0) == '$'){
					try {
						Wini ini = new Wini(new File(Config.propertyFile));
						parameter = ini.get("api", params[1].substring(1));
					} catch(Exception e) {
						System.out.println ("The parameter was not found in the variable file.");
						return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
					}
				} else {
					parameter = teststep.param;
				}
				pathOfFile = "additional_files/api/queries/"+params[2];
				System.out.println(parameter);
				System.out.println(pathOfFile);
				try{
				// Le fichier d'entrée
				File file = new File(pathOfFile);    
				// Créer l'objet File Reader
				FileReader fr = new FileReader(file);  
				// Créer l'objet BufferedReader        
				BufferedReader br = new BufferedReader(fr);  
				StringBuffer sb = new StringBuffer();    
				String line;
				String allTxt = "";
				while((line = br.readLine()) != null)
				{
					allTxt = allTxt + line;
				}
				fr.close();
				try{
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(allTxt);
					JSONObject jsonObject = null;
					JSONArray jsonArray = null;
					try{
						jsonObject = (JSONObject)obj;
					}catch(Exception e){
						jsonArray = (JSONArray)obj;
					}
					try{
						//Create request
						RequestSpecification res = given();
						Response response = res.get(parameter);
					}catch(Exception e){
						System.out.println("L'URL fournie n'est pas valide");
						return Fonctions.logStepKO(teststep, selenium, time1, "L'URL fournie n'est pas valide");
					}
					Response response;
					if (jsonObject != null){
						System.out.println("Object");
						response = given()
							.header("Content-Type", "application/json")
							.body(jsonObject.toJSONString())
							.post(parameter).then().extract().response();
					}
					else{
						System.out.println("Array");
						String a = jsonArray.toJSONString();
						response = given()
							.header("Content-Type", "application/json")
							.body(a)
							.post(parameter).then().extract().response();
					}
					System.out.println(response.asString());
					System.out.println("The status received: " + response.statusLine());
					int responseCode = response.getStatusCode();
					if (responseCode == 404){
						System.out.println("Erreur dans l'url");
						return Fonctions.logStepKO(teststep, selenium, time1, "Erreur dans l'url");
					}
					if (responseCode != 201){
						System.out.println("Erreur lors de la requete");
						return Fonctions.logStepKO(teststep, selenium, time1, "Erreur lors de la requete");
					}
					else{
						System.out.println("La requete c'est bien deroule");
						return Fonctions.logStepOK(teststep, selenium, time1);
					}
				}catch(ParseException pe){
					System.out.println("Erreur dans le fichier json");
					return Fonctions.logStepKO(teststep, selenium, time1, "Erreur dans le fichier json");
				}
				}catch(IOException e){
					System.out.println("Le Fichier n'a pas ete trouve");
					return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'a pas ete trouve.");
				}
			case "put":
				System.out.println("put");
				String putUrl;
				String filePath;
				if (params[1].charAt(0) == '$'){
					try {
						Wini ini = new Wini(new File(Config.propertyFile));
						putUrl = ini.get("api", params[1].substring(1));
					} catch(Exception e) {
						System.out.println ("The url was not found in the variable file.");
						return Fonctions.logStepKO(teststep, selenium, time1, "L'URL n'a pas ete trouvee dans le fichier des variables.");
					}
				} else {
					putUrl = teststep.param;
				}
				filePath = "additional_files/api/queries/"+params[2];
				System.out.println(putUrl);
				System.out.println(filePath);
				try{
				// Le fichier d'entrée
				File file = new File(filePath);    
				// Créer l'objet File Reader
				FileReader fr = new FileReader(file);  
				// Créer l'objet BufferedReader        
				BufferedReader br = new BufferedReader(fr);  
				StringBuffer sb = new StringBuffer();    
				String line;
				String allTxt = "";
				while((line = br.readLine()) != null)
				{
					allTxt = allTxt + line;
				}
				fr.close();
				try{
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(allTxt);
					JSONObject jsonObject = null;
					JSONArray jsonArray = null;
					try{
						jsonObject = (JSONObject)obj;
					}catch(Exception e){
						jsonArray = (JSONArray)obj;
					}
					try{
						//Create request
						RequestSpecification res = given();
						Response response = res.get(putUrl);
					}catch(Exception e){
						System.out.println("L'URL fournie n'est pas valide");
						return Fonctions.logStepKO(teststep, selenium, time1, "L'URL fournie n'est pas valide");
					}
					Response response;
					if (jsonObject != null){
						System.out.println("Object");
						response = given()
							.header("Content-Type", "application/json")
							.body(jsonObject.toJSONString())
							.put(putUrl).then().extract().response();
					}
					else{
						System.out.println("Array");
						String a = jsonArray.toJSONString();
						response = given()
							.header("Content-Type", "application/json")
							.body(a)
							.put(putUrl).then().extract().response();
					}
					System.out.println(response.asString());
					System.out.println("The status received: " + response.statusLine());
					int responseCode = response.getStatusCode();
					if ( responseCode == 404){
							System.out.println("Erreur dans l'url");
							return Fonctions.logStepKO(teststep, selenium, time1, "Erreur lors de la requete");
						}
					if (responseCode != 200 && responseCode != 204){
						System.out.println("Erreur lors de la requete");
						return Fonctions.logStepKO(teststep, selenium, time1, "Erreur lors de la requete");
					}
					else{
						System.out.println("La requete c'est bien deroule");
						return Fonctions.logStepOK(teststep, selenium, time1);
					}
				}catch(ParseException pe){
					System.out.println("Erreur dans le fichier json");
					return Fonctions.logStepKO(teststep, selenium, time1, "Erreur dans le fichier json");
				}
				}catch(IOException e){
					e.printStackTrace();
					System.out.println("Le Fichier n'a pas ete trouve");
					return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'a pas ete trouve");
				}
		}													
		return Fonctions.logStepOK(teststep, selenium, time1);
	}

	public static boolean WebPage_checkjson(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "Je verifie le JSON";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String parameter;
		if (teststep.param.charAt(0) == '$'){
			parameter = getVariableParameter(teststep.param);
			if (parameter.equals(teststep.param)) {
				return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
			}
		} else {
			parameter = teststep.param;
		}
		String[] params = parameter.split("\\|");
		String pathRef, pathTest;
		pathRef = "additional_files/api/expected_results/" + params[1];
		pathTest = "additional_files/api/results/" + params[0];
		System.out.println("path ref :" + pathRef);
		System.out.println("path test :" + pathTest);
		JSONParser parser = new JSONParser();
		JSONParser parser2 = new JSONParser();
		// Verification de l'existence des fichiers
		File file = new File(pathRef);
		if(!file.isFile()){
			System.out.println("Erreur : absence du fichier reference");
			return Fonctions.logStepKO(teststep, selenium, time1,
								"Erreur : absence du fichier reference ");
		}
		file = new File(pathTest);
		if(!file.isFile()){
			System.out.println("Erreur : absence du document a comparer");
			return Fonctions.logStepKO(teststep, selenium, time1,
								"Erreur : absence du document a comparer");
		}
		int num;
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(pathRef));
			JSONObject objToCompare = (JSONObject) parser2.parse(new FileReader(pathTest));
			num = 1;
		} catch (Exception e) {
			try {
				JSONArray obj = (JSONArray) parser.parse(new FileReader(pathRef));
				JSONArray objToCompare = (JSONArray) parser2.parse(new FileReader(pathTest));
				num = 2;
			} catch (Exception pe) {
				try {
					JSONObject obj = (JSONObject) parser.parse(new FileReader(pathRef));
					JSONArray objToCompare = (JSONArray) parser2.parse(new FileReader(pathTest));
					num = 3;
				} catch (Exception pe2) {
					try {
						JSONArray obj = (JSONArray) parser.parse(new FileReader(pathRef));
						System.out.println(obj.size());
						if (obj.size() != 1) {
							System.out
									.println(
											"Les valeurs du fichier de reference ne sont pas contenues dans le fichier "
													+ params[0]);
							return Fonctions.logStepWarning(teststep, selenium, time1,
									"WARNING : Les valeurs du fichier de reference  ne sont pas contenues dans le fichier "
											+ params[0]);
						} else {
							num = 4;
						}
					} catch (Exception pe3) {
						System.out.println("Erreur : Le format du fichier fourni n'est pas json");
						return Fonctions.logStepWarning(teststep, selenium, time1,
								"WARNING : Le format du fichier fourni n'est pas json");
					}
				}
			}
		}
		try {
			switch (num) {
				case 1:
				case 4: // case Object
					JSONObject obj;
					if (num == 4) {
						JSONArray objtemp = (JSONArray) parser.parse(new FileReader(pathRef));
						obj = (JSONObject) objtemp.get(0);
					} else {
						obj = (JSONObject) parser.parse(new FileReader(pathRef));
					}
					JSONObject objToCompare = (JSONObject) parser2.parse(new FileReader(pathTest));
					int size1 = countJsonKey(obj);
					// System.out.println(size);
					int number = comparejson(obj, objToCompare);
					if (number >= size1) {
						System.out.println(
								"Les valeurs du fichier de reference sont contenues dans le fichier " + params[0]);
						return Fonctions.logStepOK(teststep, selenium, time1);
					} else {
						System.out
								.println(
										"Erreur : Les valeurs du fichier de reference ne sont pas contenues dans le fichier "
												+ params[0]);
						return Fonctions.logStepWarning(teststep, selenium, time1,
								"WARNING : Les valeurs du fichier de reference ne sont pas contenues dans le fichier");
					}
				case 2: // case Array
					int number2 = 0;

					JSONArray obj2 = (JSONArray) parser.parse(new FileReader(pathRef));
					JSONArray objToCompare2 = (JSONArray) parser2.parse(new FileReader(pathTest));

					int count = 0;
					for (int i = 0; i < obj2.size(); i++) {
						number2 = 0;
						int size = countJsonKey((JSONObject) obj2.get(i));
						for (int y = 0; y < objToCompare2.size(); y++) {
							number2 = comparejson((JSONObject) obj2.get(i), (JSONObject) objToCompare2.get(y));
							if (number2 >= size) {
								count++;
								break;
							}
						}
					}
					if (count == obj2.size()) {
						System.out.println(
								"Les valeurs du fichier de reference sont contenues dans le fichier " + params[0]);
						return Fonctions.logStepOK(teststep, selenium, time1);
					} else {
						System.out
								.println("Les valeurs du fichier de reference ne sont pas contenues dans le fichier"
										+ params[0]);
						return Fonctions.logStepWarning(teststep, selenium, time1,
								"WARNING : Les valeurs du fichier de reference ne sont pas contenues dans le fichier");
					}
				case 3: // cas Array/Object
					JSONObject obj3 = (JSONObject) parser.parse(new FileReader(pathRef));
					JSONArray objToCompare3 = (JSONArray) parser2.parse(new FileReader(pathTest));
					int size3 = countJsonKey(obj3);
					int nbr3;
					for (int i = 0; i < objToCompare3.size(); i++) {
						nbr3 = comparejson(obj3, (JSONObject) objToCompare3.get(i));
						if (nbr3 >= size3) {
							System.out.println(
									"Les valeurs du fichier de reference sont contenues dans le fichier " + params[0]);
							return Fonctions.logStepOK(teststep, selenium, time1);
						}
					}
					System.out
							.println("Les valeurs du fichier de reference ne sont pas contenues dans le fichier"
									+ params[0]);
					return Fonctions.logStepWarning(teststep, selenium, time1,
							"WARNING : Les valeurs du fichier de reference ne sont pas contenues dans le fichier"
									+ params[0]);
			}
		} catch (Exception e) {
			return Fonctions.logStepWarning(teststep, selenium, time1,
					"WARNING : Les valeurs du fichier de reference ne sont pas contenues dans le fichier");
		}
		return Fonctions.logStepWarning(teststep, selenium, time1,
				"WARNING : Les valeurs du fichier de reference ne sont pas contenues dans le fichier");

	}

	public static boolean WebPage_checkxls(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "Je verifie le XLS";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()) {
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :" + teststep.object_attach_name);
		}

		try {
			// Vérifier l'existence des fichiers
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			String[] params = parameter.split("\\|");
			String fichier1 = Config.additional_files + "/" + params[0];
			String fichier2 = params[1];

			File file1 = new File(fichier1);
			File file2 = new File(fichier2);
			FileInputStream fileInput1 = new FileInputStream(file1);
			FileInputStream fileInput2 = new FileInputStream(file2);

			if (file1.exists() && file2.exists()) {
				Sheet sh1, sh2;
				if (file1.getAbsolutePath().endsWith(".xlsx")) {
					XSSFWorkbook wb1 = new XSSFWorkbook(file1);
					sh1 = wb1.getSheetAt(0);
				} else {
					HSSFWorkbook wb1 = new HSSFWorkbook(fileInput1);
					sh1 = wb1.getSheetAt(0);
				}
				if (file2.getAbsolutePath().endsWith(".xlsx")) {
					XSSFWorkbook wb2 = new XSSFWorkbook(file2);
					sh2 = wb2.getSheetAt(0);
				} else {
					HSSFWorkbook wb2 = new HSSFWorkbook(fileInput2);
					sh2 = wb2.getSheetAt(0);
				}

				//sh1 = new HSSFWorkbook().createSheet();
				// Récupérer la première ligne de chaque fichier
				int numFirstRow1 = sh1.getFirstRowNum();
				Row firstRow1 = sh1.getRow(numFirstRow1);
				int firstCell1 = firstRow1.getFirstCellNum();
				int lastCell1 = firstRow1.getLastCellNum();

				int numFirstRow2 = sh2.getFirstRowNum();
				Row firstRow2 = sh2.getRow(numFirstRow2);
				int firstCell2 = firstRow2.getFirstCellNum();
				int lastCell2 = firstRow2.getLastCellNum();
				int addToArray = 0;

				/*
				 * Ajouter les index du fichier à tester qui doivent être ignorés (colonnes
				 * absentes sur le fichier de référence)
				 */

				ArrayList<Object> ignoreIndex = new ArrayList<Object>();
				ArrayList<Object> colonnes1 = new ArrayList<Object>();
				ArrayList<Object> colonnes2 = new ArrayList<Object>();

				for (int i = firstCell2; i < lastCell2; i++) {
					Cell cell2 = firstRow2.getCell(i);
					colonnes2.add(cell2.getStringCellValue());
					addToArray = 0;
					for (int j = firstCell1; j < lastCell1; j++) {
						Cell cell1 = firstRow1.getCell(j);
						if (!colonnes1.contains(cell1.getStringCellValue())) {
							colonnes1.add(cell1.getStringCellValue());
						}
						if (cell1.getStringCellValue().equals(cell2.getStringCellValue())) {
							break;
						} else {
							addToArray++;
						}
						if (addToArray == lastCell1) {
							ignoreIndex.add(cell2.getStringCellValue());
						}
					}
				}
				System.out.println("Colonnes du fichier observé à ignorer : " + ignoreIndex);

				for (int i = 0; i < colonnes1.size(); i++) {
					int isok = 0;
					int isnotok = 0;
					for (int j = 0; j < colonnes2.size(); j++) {
						if (colonnes1.get(i).equals(colonnes2.get(j))) {
							isok++;
						} else {
							isnotok++;
						}
						/*
						 * Vérifier la présence des colonnes du fichier de référence dans le fichier
						 * observé
						 */
						if (isnotok == colonnes2.size()) {
							System.out.println(
									"Une colonne du fichier de référence n'existe pas dans le fichier observé : "
											+ colonnes1.get(i));
							return Fonctions.logStepKO(teststep, selenium, time1,
									"Une colonne du fichier de reference n'existe pas dans le fichier observe");
						}
					}
				}

				/* Vérifier que les fichiers ont le même nombre de lignes */
				int numLastRow1 = sh1.getLastRowNum();
				int numLastRow2 = sh2.getLastRowNum();
				if (numLastRow1 != numLastRow2) {
					System.out.println("Les deux fichiers n'ont pas le même nombre de lignes");
					return Fonctions.logStepKO(teststep, selenium, time1,
							"Les deux fichiers n'ont pas le meme nombre de lignes");
				}
				int nbok = 0;
				int nbbad = 0;
				System.out.println("########### DIFFÉRENCES CONSTATÉES ##############");
				for (int i = numFirstRow1 + 1; i <= numLastRow1; i++) {
					Row row1 = sh1.getRow(i);
					Row row2 = sh2.getRow(i);
					firstCell1 = row1.getFirstCellNum();
					lastCell1 = row1.getLastCellNum();
					firstCell2 = row2.getFirstCellNum();
					lastCell2 = row2.getLastCellNum();
					for (int j = firstCell1; j < lastCell1; j++) {
						Cell cell1 = row1.getCell(j);
						Cell cell2 = row2.getCell(j);
						for (int k = firstCell1; k < lastCell1; k++) {
							if (ignoreIndex.contains(firstRow2.getCell(k).getStringCellValue())) {
								continue;
							}
							if (!(firstRow2.getCell(k).getStringCellValue()
									.equals(firstRow1.getCell(j).getStringCellValue()))) {
								continue;
							}
							cell2 = row2.getCell(k);
							// System.out.println("ligne " + i + " : " + j + " " + k);
							boolean equalCells = false;
							if ((cell1 == null) && (cell2 == null)) {
								equalCells = true;
								nbok++;
								continue;
							} else if ((cell1 == null) || (cell2 == null)) {
								equalCells = false;
								System.out.println("Ligne " + i + " colonne " + j + " du fichier de référence, colonne "
										+ k + " du fichier observé, une des deux valeurs est null.");
								nbbad++;
								continue;
							}
							int type1 = cell1.getCellType();
							int type2 = cell2.getCellType();
							try {
								if (type1 == type2) {
									switch (cell1.getCellType()) {
										case Cell.CELL_TYPE_FORMULA:
											if (cell1.getCellFormula().equals(cell2.getCellFormula())) {
												equalCells = true;
											}
											// System.out.println("fomrmula");
											break;
										case Cell.CELL_TYPE_NUMERIC:
											if (cell1.getNumericCellValue() == cell2.getNumericCellValue()) {
												equalCells = true;
											}
											// System.out.println("numeric");
											break;
										case Cell.CELL_TYPE_STRING:
											if (cell1.getStringCellValue().equals(cell2.getStringCellValue())) {
												equalCells = true;
											}
											// System.out.println("string");
											break;
										case Cell.CELL_TYPE_BLANK:
											if (cell2.getCellType() == Cell.CELL_TYPE_BLANK) {
												equalCells = true;
											}
											// System.out.println("blank");
											break;
										case Cell.CELL_TYPE_BOOLEAN:
											if (cell1.getBooleanCellValue() == cell2.getBooleanCellValue()) {
												equalCells = true;
											}
											// System.out.println("boolean");
											break;
										case Cell.CELL_TYPE_ERROR:
											if (cell1.getErrorCellValue() == cell2.getErrorCellValue()) {
												equalCells = true;
											}
											// System.out.println("error");
											break;
										default:
											if (cell1.getStringCellValue().equals(cell2.getStringCellValue())) {
												equalCells = true;
											}
											// System.out.println("default");
											break;
									}
								} else {
									equalCells = false;
								}
							} catch (Exception e) {
								System.out.println("Problème lors de la comparaison d'une cellule");
								return Fonctions.logStepKO(teststep, selenium, time1, "Erreur lors de la comparaison des cellules.");
							}
							if (!equalCells) {
								System.out.println("Ligne " + i + " colonne " + j + " du fichier de référence : "
										+ cell1.toString() + ", colonne " + k + " du fichier observé : "
										+ cell2.toString());
								nbbad++;
							} else {
								nbok++;
								break;
							}
						}
					}
				}
				System.out.println("#################################################");
				System.out.println("Nombre de cellules similaires : " + nbok);
				System.out.println("Nombre de différences : " + nbbad);

				if (nbbad != 0) {
					return Fonctions.logStepWarning(teststep, selenium, time1,
							"WARNING : Le fichier observe presente des differences avec le fichier de reference");
				}
			} else {
				return Fonctions.logStepKO(teststep, selenium, time1, "Un fichier n'a pas ete trouve");
			}

		} catch (Exception e) {
			System.out.println("Un fichier entré en paramètre n'existe pas.");
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");
		}
		return Fonctions.logStepOK(teststep, selenium, time1);
	}

	public static boolean WebPage_getemail(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "Je recupere l'email";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()) {
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :" + teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			String[] params = parameter.split("\\|");
			String fichier = Config.additional_files + "/" + params[0], variable = params[1], ligne, domaine = "",
					utilisateur = "", password = "", expediteur = "", text = "", code = "", result = "";
			File file = new File(fichier);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			int z = 0;
			while ((ligne = bufferedReader.readLine()) != null) {
				if (ligne.contains("email : ")) {
					String[] utilisateurs = ligne.split("email : ");
					utilisateur = utilisateurs[1];
				} else if (ligne.contains("password : ")) {
					String[] passwords = ligne.split("password : ");
					password = passwords[1];
				} else if (ligne.contains("messagerie : ")) {
					String[] domaines = ligne.split("messagerie : ");
					domaine = domaines[1];
				} else if (ligne.contains("expediteur : ")) {
					String[] expediteurs = ligne.split("expediteur : ");
					expediteur = expediteurs[1];
				} else {
					if (z > 0) {
						text += "\n" + ligne;
					} else {
						text += ligne;
					}
					z++;
				}
				// System.out.println(ligne);
			}
			bufferedReader.close();
			// System.out.println(text);
			text = new String(text.getBytes(), "UTF-8");
			// System.out.println(text.length());
			if (text.length() == 0 || !text.contains("<code>") || text.equals("<code>")) {
				System.out.println("---------------------------------");
				System.out.println("Extraction du code KO");
				System.out.println("Aucun code défini dans le fichier " + params[0]);
				System.out.println("---------------------------------");
				return Fonctions.logStepKO(teststep, selenium, time1, "Aucun code defini dans le fichier " + params[0]);
			}
			if ((text.length() - text.replace("<code>", "").length()) / "<code>".length() > 1) {
				System.out.println("---------------------------------");
				System.out.println("Extraction du code KO");
				System.out.println("Plusieurs codes définis dans le fichier " + params[0]);
				System.out.println("---------------------------------");
				return Fonctions.logStepKO(teststep, selenium, time1,
						"Plusieurs codes definis dans le fichier " + params[0]);
			}
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			Properties properties = new Properties();
			properties.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
			properties.setProperty("mail.imap.socketFactory.fallback", "false");
			properties.setProperty("mail.imap.port", "993");
			properties.setProperty("mail.imap.socketFactory.port", "993");
			properties.put("mail.store.protocol", "imap");
			Session emailSession = Session.getDefaultInstance(properties);
			Store store = emailSession.getStore();
			try {
				store.connect(domaine, utilisateur, password);
			} catch (Exception e) {
				System.out.println("---------------------------------");
				System.out.println("Extraction du code KO");
				System.out
						.println("Paramètre(s) de connexion défini(s) dans le fichier " + params[0] + " incorrect(s).");
				System.out.println("---------------------------------");
				return Fonctions.logStepKO(teststep, selenium, time1, "Parametres de connexion incorrects");
			}
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_WRITE);
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
			Message messages[] = emailFolder.search(unseenFlagTerm);

			for (int i = -1, n = messages.length - 1; i < n; n--) {
				/* && messages[n].getFrom()[0].toString().contains(expediteur) */
				Message message = messages[n];
				if (message.getFrom()[0].toString().contains(expediteur)) {

					System.out.println("---------------------------------");
					System.out.println("Email trouvé");
					System.out.println("Sujet du mail : " + message.getSubject());
					System.out.println("Envoyé par : " + message.getFrom()[0]);

					// System.out.println("<" + message.getContentType() + ">");
					int a = 0;
					try {
						MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
						result = getTextFromMimeMultipart(mimeMultipart);
						a = 1;
					} catch (Exception e) {
					}
					try {
						if (result == "" || a == 0) {
							result = message.getContent().toString();
						}
					} catch (Exception e) {
					}
					// System.out.println(result);
					// System.out.println(text);

					String[] textesDebut = text.split("<code");
					String[] textesFin = textesDebut[1].split(">");

					result = result.replaceAll("\r", "");
					textesDebut[0] = textesDebut[0].replaceAll("\r", "");

					if (result.contains(textesDebut[0])) {
						if (textesDebut[0].length() == 0) {
							// Il n'y a pas de texte avant le code dans le fichier txt
							// Il y a du texte après le code dans le fichier txt
							if (result.contains(textesFin[1])) {
								// Le texte après le code correspond
								String[] resultCoupe2 = result.split(textesFin[1]);
								String[] resultCoupeSpace = resultCoupe2[0].split("\\s");
								code = resultCoupeSpace[resultCoupeSpace.length - 1].replaceAll("\\s", " ");
								break;
							} else {
								// Le texte après le code ne correspond pas au contenu du mail
							}
						} else {
							// Le mail contient le texte avant le code du fichier txt
							String[] resultCoupe1 = result.split(textesDebut[0]);
							if (textesFin.length == 0) {
								// Il n'y a pas de texte après le code dans le fichier txt
								String[] resultCoupeSpace = resultCoupe1[1].split("\\s");
								code = resultCoupeSpace[0].replaceAll("\\s", "");
								break;
							} else {
								// Il y a du texte après le code dans le fichier txt
								if (resultCoupe1[1].contains(textesFin[1])) {
									// Le texte après le code correspond
									String[] resultCoupe2 = resultCoupe1[1].split(textesFin[1]);
									code = resultCoupe2[0].replaceAll("\\s", " ");
									break;
								} else {
									// Le texte après le code ne correspond pas au contenu du mail
								}
							}
						}
					} else {
						System.out.println("Pas de correspondance entre le texte du mail et le texte du fichier txt");
					}
				} else {
					// System.out.println("Pas de mail non lu envoyé par "
					// +message.getFrom()[0].toString());
				}
			}

			if (code.length() == 0) {
				System.out.println("---------------------------------");
				System.out.println("Extraction du code KO");
				System.out.println("Aucun mail trouvé avec les paramètres du fichier " + params[0]);
				System.out.println("---------------------------------");
				return Fonctions.logStepKO(teststep, selenium, time1, "Le code n'a pas ete trouve.");
			}
			System.out.println("---------------------------------");
			System.out.println("Extraction du code OK");
			System.out.println("Code extrait du mail : <" + code + ">");
			System.out.println("---------------------------------");
			emailFolder.close(false);
			store.close();

			Wini iniFile = new Wini(new File(Config.additional_files + "/variables.ini"));
			iniFile.put("parameter", variable, code);
			iniFile.store();

		} catch (Exception e) {
			System.out.println(e);
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");
		}

		return Fonctions.logStepOK(teststep, selenium, time1);
	}

	public static boolean WebPage_clickbycoordinates(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "Je clique par coordonnees";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int noCase;
		String nbrligneColonne, tag, temp, coord;
		String[] params;
		int click_x, click_y;
		WebElement elem;
		int xMoveToOrigin, yMoveToOrigin;
		String parameter;
		if (teststep.param.charAt(0) == '$') {
			parameter = getVariableParameter(teststep.param);
			if (parameter.equals(teststep.param)) {
				return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
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
					elem = driverInstance.findElement(By.xpath("//*[" + att + "='" + value + "']"));
				} else {
					// System.out.println("Recherche xpath = " + "//" + tag);
					elem = driverInstance.findElement(By.xpath("//" + tag));
				}
				Dimension canvas_dimensions = elem.getSize();
				String tabCoord[] = coord.split(":");
				int stepx = Integer.parseInt(tabCoord[0]);
				int stepy = Integer.parseInt(tabCoord[1]);
				xMoveToOrigin = canvas_dimensions.getWidth() / 2;
				yMoveToOrigin = canvas_dimensions.getHeight() / 2;
				String tabLineCol[] = nbrligneColonne.split(":");
				int nbLines = Integer.parseInt(tabLineCol[0]);
				int nbCollumns = Integer.parseInt(tabLineCol[1]);
				int yMoveOriginToPoint = canvas_dimensions.getWidth() / nbCollumns;
				int xMoveOriginToPoint = canvas_dimensions.getHeight() / nbLines;
				if (stepx > nbLines || stepx < 1 || stepy > nbCollumns || stepy < 1)
					return Fonctions.logStepWarning(teststep, selenium, time1,
							"WARNING : Erreur coordonnees inacessible dans ce quadrillage de " + nbLines + " lignes et "
									+ nbCollumns + " colonnes ");
				click_y = xMoveOriginToPoint * stepx - xMoveOriginToPoint / 2;
				click_x = yMoveOriginToPoint * stepy - yMoveOriginToPoint / 2;
			} else {
				if (temp.contains(":") && (!temp.contains("@"))) {
					nbrligneColonne = params[1]; // Case Coordinates + Table size
					elem = driverInstance.findElement(By.xpath("//body"));
					Dimension canvas_dimensions = elem.getSize();
					String tabCoord[] = coord.split(":");
					int stepx = Integer.parseInt(tabCoord[0]);
					int stepy = Integer.parseInt(tabCoord[1]);
					xMoveToOrigin = canvas_dimensions.getWidth() / 2;
					yMoveToOrigin = canvas_dimensions.getHeight() / 2;
					String tabLineCol[] = nbrligneColonne.split(":");
					int nbLines = Integer.parseInt(tabLineCol[0]);
					int nbCollumns = Integer.parseInt(tabLineCol[1]);
					if (stepx > nbLines || stepx < 1 || stepy > nbCollumns || stepy < 1)
						return Fonctions.logStepWarning(teststep, selenium, time1,
								"WARNING : Erreur coordonnees inacessible dans ce quadrillage de " + nbLines + " lignes et "
										+ nbCollumns + " colonnes ");
					int yMoveOriginToPoint = canvas_dimensions.getWidth() / nbCollumns;
					int xMoveOriginToPoint = canvas_dimensions.getHeight() / nbLines;
					click_y = xMoveOriginToPoint * stepx - xMoveOriginToPoint / 2;
					click_x = yMoveOriginToPoint * stepy - yMoveOriginToPoint / 2;
				} else {
					tag = params[1]; // Case Coordinates + tag
					if (tag.contains("@")) {
						String tabAttribute[] = tag.split("=");
						String attr = tabAttribute[0];
						String value = tabAttribute[1];
						// System.out.println("Recherche xpath = " + "//*[" + attr + "='" + value +
						// "']");
						elem = driverInstance.findElement(By.xpath("//*[" + attr + "='" + value + "']"));
					} else {
						elem = driverInstance.findElement(By.xpath("//" + tag));
						// System.out.println("Recherche xpath : " + "//" + tag);
					}
					Dimension canvas_dimensions = elem.getSize();
					String tabCoord[] = coord.split(":");
					int stepx = Integer.parseInt(tabCoord[0]);
					int stepy = Integer.parseInt(tabCoord[1]);
					if (stepx > 10 || stepx < 1 || stepy > 10 || stepy < 1)
						return Fonctions.logStepWarning(teststep, selenium, time1,
								"WARNING : Erreur coordonnees inacessibles dans ce quadrillage de 10 lignes et 10 colonnes ");
					xMoveToOrigin = canvas_dimensions.getWidth() / 2;
					yMoveToOrigin = canvas_dimensions.getHeight() / 2;
					int yMoveOriginToPoint = canvas_dimensions.getWidth() / 10;
					int xMoveOriginToPoint = canvas_dimensions.getHeight() / 10;
					click_y = xMoveOriginToPoint * stepx - xMoveOriginToPoint / 2;
					click_x = yMoveOriginToPoint * stepy - yMoveOriginToPoint / 2;
				}
			}
		} else {
			coord = parameter; // Case Coordinate
			elem = driverInstance.findElement(By.xpath("//body"));
			Dimension elem_dimensions = elem.getSize();
			String tabCoord[] = coord.split(":");
			int stepx = Integer.parseInt(tabCoord[0]);
			int stepy = Integer.parseInt(tabCoord[1]);
			if (stepx > 10 || stepx < 1 || stepy > 10 || stepy < 1)
				return Fonctions.logStepWarning(teststep, selenium, time1,
						"WARNING : Erreur coordonnees inacessibles dans ce quadrillage de 10 lignes et 10 colonnes ");
			xMoveToOrigin = elem_dimensions.getWidth() / 2;
			yMoveToOrigin = elem_dimensions.getHeight() / 2;
			int yMoveOriginToPoint = elem_dimensions.getWidth() / 10;
			int xMoveOriginToPoint = elem_dimensions.getHeight() / 10;
			click_y = xMoveOriginToPoint * stepx - xMoveOriginToPoint / 2;
			click_x = yMoveOriginToPoint * stepy - yMoveOriginToPoint / 2;
		}
		JavascriptExecutor js = (JavascriptExecutor) driverInstance;
		WebElement page = driverInstance.findElement(By.tagName("body"));
		int pW = page.getSize().getWidth();
		int pH = page.getSize().getHeight();
		org.openqa.selenium.Rectangle test = elem.getRect();
		int res1 = test.getX() + click_x;
		int res2 = test.getY() + click_y;
		//System.out.println("elem position X: " + test.getX() + " Y: " + test.getY());
		js.executeScript("var div = document.createElement('div');" +
				"div.id = 'cursor';" +
				"div.style.position = 'absolute';" +
				"div.style.left = '" + res1 + "px';" +
				"div.style.top = '" + res2 + "px';" +
				"div.style.width = '15px';" +
				"div.style.height = '15px';" +
				"div.style.border = '4px solid orange';" +
				"div.style.zIndex = '1000';" +
				"div.style.borderRadius = '50%';" +
				"document.body.appendChild(div);");

		WebElement cursor = driverInstance.findElement(By.id("cursor"));
		boolean visible = (boolean) js.executeScript(
				"var  elem = arguments[0],           " +
						"box = elem.getBoundingClientRect(), " +
						"cx = box.left + box.width /2 ,      " +
						"cy = box.top + box.height /2,        " +
						"e = document.elementFromPoint(cx,cy);" +
						"for (; e; e=e.parentElement) {      " +
						"	if (e === elem)                  " +
						"		return true;                 " +
						"}                                   " +
						"return false;",
				cursor);
		if (visible) {
			// System.out.println("Ne pas scroll");
		} else {
			// System.out.println("Scroll neccessaire");
			js.executeScript("arguments[0].scrollIntoView(true)", cursor);
		}
		try {
			Thread.sleep(1000);
			js.executeScript("var elem = document.getElementById('cursor');"
					+ "elem.remove()");
		} catch (Exception e) {

		}
		long scrollX = (long) js.executeScript("return window.scrollX;");
		long scrollY = (long) js.executeScript("return window.scrollY;");
		int actualScrollX = (int) scrollX;
		int actualScrollY = (int) scrollY;
		new Actions(driverInstance)
				.moveByOffset(res1 - actualScrollX, res2 - actualScrollY)
				.click()
				.perform();
		return Fonctions.logStepOK(teststep, selenium, time1);
	}

	public static boolean WebPage_checkdoc(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "Je compare les Doc";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()) {
			return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier n'existe pas :" + teststep.object_attach_name);
		}
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			String[] params = parameter.split("\\|");
			String fichier1 = params[0], fichier2 = params[1], result1 = "", result2 = "";
			File file1 = new File(Config.additional_files + "/" + fichier1);
			File file2 = new File(fichier2);
			// On vérifie que le deuxième paramètre soit un fichier doc ou docx 
			if (Pattern.matches("[\\p{javaLowerCase}\\p{javaUpperCase}\\w\\-:/]+\\.docx?", fichier2)) {
				// On vérifie que le premier paramètre soit un fichier doc, docx ou txt
				if (Pattern.matches("[\\p{javaLowerCase}\\p{javaUpperCase}\\w\\-:/]+\\.docx?", fichier1)
				|| Pattern.matches("[\\p{javaLowerCase}\\p{javaUpperCase}\\w\\-:/]+\\.txt", fichier1)) {
					// System.out.println("Le deuxième paramètre est un fichier doc, docx");
					
					// On vérifie que les deux fichiers existent bel et bien
					if (!file1.exists() || !file2.exists()) {
						System.out.println("Un des deux fichiers entrés en paramètre n'existe pas");
						return Fonctions.logStepKO(teststep, selenium, time1, "Un fichier au moins n'existe pas");
					}

					// Récupérer le contenu du preminer fichier dans un String
					if (file1.getAbsolutePath().endsWith(".docx")) {
						FileInputStream fis = new FileInputStream(file1);
						XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
						XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
						result1 = extractor.getText().replaceAll("\\s", "");
						fis.close();

					} else if(file1.getAbsolutePath().endsWith(".doc")) {
						FileInputStream fis = new FileInputStream(file1);
						HWPFDocument hdoc = new HWPFDocument(fis);
						WordExtractor extractor = new WordExtractor(hdoc);
						result1 = extractor.getText().replaceAll("\\s", "");
						fis.close();

					} else { // NORMALEMENT cas txt
						BufferedReader br = new BufferedReader(new FileReader(file1));
						String line;
						while ((line = br.readLine()) != null) {
							result1 += line;
						}
						result1 = new String(result1.getBytes(), "UTF-8");
						result1 = result1.replaceAll("\\s", "");
					}

					// Récupérer le contenu du deuxième fichier dans un String
					if (file2.getAbsolutePath().endsWith(".docx")) {
						FileInputStream fis2 = new FileInputStream(file2);
						XWPFDocument xdoc2 = new XWPFDocument(OPCPackage.open(fis2));
						XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc2);
						result2 = extractor.getText().replaceAll("\\s", "");
						fis2.close();

					} else {
						FileInputStream fis2 = new FileInputStream(file2);
						HWPFDocument hdoc = new HWPFDocument(fis2);
						WordExtractor extractor = new WordExtractor(hdoc);
						result2 = extractor.getText().replaceAll("\\s", "");
						fis2.close();
					}
					
					if (result1.equals(result2)) {
						System.out.println("Les deux fichers sont égaux.");
						return Fonctions.logStepOK(teststep, selenium, time1);
					} else {
						System.out.println("Les deux fichiers ne sont pas égaux.");
						return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Les fichiers sont differents");
					}

				} else {
					// System.out.println("Le premier paramètre n'est pas un fichier.");
					fichier1 = fichier1.toLowerCase().replaceAll("\\s", "");
					if (file2.getAbsolutePath().endsWith(".docx")) {
						FileInputStream fis = new FileInputStream(file2);
						XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
						XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
						result2 = extractor.getText().replaceAll("\\s", "");
						fis.close();

					} else {
						FileInputStream fis = new FileInputStream(file2);
						HWPFDocument hdoc = new HWPFDocument(fis);
						WordExtractor extractor = new WordExtractor(hdoc);
						result2 = extractor.getText().replaceAll("\\s", "");
						fis.close();
					}


					if (result2.contains(fichier1)) {
						System.out.println("La valeur est contenue dans le fichier");
						return Fonctions.logStepOK(teststep, selenium, time1);
					} else {
						System.out.println("La valeur n'est pas contenue dans le fichier");
						return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : La valeur n'est pas contenue dans le fichier");
					}
				}

			} else {
				System.out.println("Le fichier obtenu n'est pas un .doc ou un .docx");
				return Fonctions.logStepKO(teststep, selenium, time1, "Le fichier obtenu n'est pas un .doc ou .docx");
			}

		} catch (Exception e) {
			System.out.println(e);
			return Fonctions.logStepKO(teststep, selenium, time1, "Une erreur est survenue.");
		}
	}

	public static boolean WebPage_checkinnertextnotpresent(WebDriver selenium, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je verifie l'absence";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		WebElement myHTML;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
			
		
		try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			if (array_prop_object.containsKey("title")) {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/head/title[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'),'"+array_prop_object.get("title").toLowerCase().replaceAll("\\s", "")+"')]/../../body")));
			} else {
				myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
			}
			System.out.println ("Page displayed ? " + myHTML.isDisplayed());
			if (parameter.equals("")){
				return Fonctions.logStepOK(teststep, selenium, time1);
			}
			
			if (myHTML.getText().contains (parameter)) {
				System.out.println ("Text found on page.");
				return Fonctions.logStepWarning(teststep, selenium, time1, "WARNING : Le texte " + parameter + " est present sur la page.");
			} else {
				
				System.out.println ("The Text was not found on page");
				return Fonctions.logStepOK(teststep, selenium, time1);
			}
		}
		catch (Exception e) {
			return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
		}
    }
	
	public static boolean Webpage_open(WebDriver selenium, Teststep teststep) throws IOException {
        teststep.action_label = "J'ouvre";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			driverInstance.get(parameter);
            return Fonctions.logStepOK(teststep, selenium, time1);
        } catch (Exception e) {
            System.out.println("Erreur lors de la pause");
            return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
        }
    }

	public static boolean WebPage_confirmAlert(WebDriver selenium, Teststep teststep)
	{		
		teststep.action_label = "Je confirme l'alerte";
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		WebElement myHTML;
		try {
			Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		try {
			//Wait for the alert to be displayed
			wait.until(ExpectedConditions.alertIsPresent());
			//Store the alert in a variable
			Alert alert = driverInstance.switchTo().alert();

			//Press the Cancel button
			alert.accept();
			return Fonctions.logStepOK(teststep, selenium, time1);
		}
		catch (Exception e) {
			return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
		}
	}
	
	public static boolean  WebPage_FonctionsAnnexes(WebDriver selenium, Teststep teststep) throws IOException {
		teststep.action_label = "J'appuie sur touche";
		//appuyer touche
		Date time1 = new Date();
		WebDriver driverInstance = (WebDriver) selenium;
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
		WebElement myHTML;
		myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));	
		try {
			System.out.println ("Page affichée ? " + myHTML.isDisplayed());
		}
		catch (Exception e) {
				return Fonctions.logStepKO(teststep, selenium, time1, "La page n'a pas ete trouvee.");
		}
		String myParameter = teststep.param;
		try {
		// Crée une instance de la classe Robot
		Robot robot = new Robot();

		switch (myParameter) {

			case "tab": //Dans le cas où le parametre est tab
				robot.keyPress(KeyEvent.VK_TAB); //on presse la touche tab
				robot.keyRelease(KeyEvent.VK_TAB); // puis on la relache
				break;
			case "enter": 
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				break;
			case "echap":
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				break;
			case "up":
				robot.keyPress(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_UP);
				break;
			case "down":
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				break;
			case "left":
				robot.keyPress(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_LEFT);
				break;
			case "right":
				robot.keyPress(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_RIGHT);
				break;
			case "backspace":
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.keyRelease(KeyEvent.VK_BACK_SPACE);
				break;
			case "control":
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case "shift":
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				break;
			case "delete":
				robot.keyPress(KeyEvent.VK_DELETE);
				robot.keyRelease(KeyEvent.VK_DELETE);
				break;
			case "F1":
				robot.keyPress(KeyEvent.VK_F1);
				robot.keyRelease(KeyEvent.VK_F1);
				break;
			case "F2":
				robot.keyPress(KeyEvent.VK_F2);
				robot.keyRelease(KeyEvent.VK_F2);
				break;
			case "F3":
				robot.keyPress(KeyEvent.VK_F3);
				robot.keyRelease(KeyEvent.VK_F3);
				break;
			case "F4":
				robot.keyPress(KeyEvent.VK_F4);
				robot.keyRelease(KeyEvent.VK_F4);
				break;
			case "F5":
				robot.keyPress(KeyEvent.VK_F5);
				robot.keyRelease(KeyEvent.VK_F5);
				break;
			case "F6":
				robot.keyPress(KeyEvent.VK_F6);
				robot.keyRelease(KeyEvent.VK_F6);
				break;
			case "F7":
				robot.keyPress(KeyEvent.VK_F7);
				robot.keyRelease(KeyEvent.VK_F7);
				break;
			case "F8":
				robot.keyPress(KeyEvent.VK_F8);
				robot.keyRelease(KeyEvent.VK_F8);
				break;
			case "F9":
				robot.keyPress(KeyEvent.VK_F9);
				robot.keyRelease(KeyEvent.VK_F9);
				break;
			case "F10":
				robot.keyPress(KeyEvent.VK_F10);
				robot.keyRelease(KeyEvent.VK_F10);
				break;
			case "F11":
				robot.keyPress(KeyEvent.VK_F11);
				robot.keyRelease(KeyEvent.VK_F11);
				break;
			case "F12":
				robot.keyPress(KeyEvent.VK_F12);
				robot.keyRelease(KeyEvent.VK_F12);
				break;

			default:
				return Fonctions.logStepKO(teststep, selenium, time1, "Le parametre n'est pas une touche acceptee."); //Si Myparam ne correspond à aucune valeur on renvoi KO
				
		}
		System.out.println("Press on " + myParameter);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		return Fonctions.logStepOK(teststep, selenium, time1);
		}
	// #######################################
	// ############ ActionsByText ############
	// ####################################### 
	
	public static boolean  WebObject_clickbytext(final WebDriver driver, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je clique";
		Date time1 = new Date();
		String property ="";
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element = null;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			do {
				switch (parts.length) {
					case 1:
						if (tryProperty != null && !tryProperty.isEmpty()) {
							if (tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "")+ str) || datapool) {
								//System.out.println("bon cas");
								element = findElements(driver, tryProperty);
								xpath = tryProperty;
								if (element == null) list = identifyElementToClick(driver, parts[0]);
							} else list = identifyElementToClick(driver, parts[0]);
						} else list = identifyElementToClick(driver, parts[0]);
						break;
					case 2:
						if (tryProperty != null && !tryProperty.isEmpty()) {
							if (tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) || datapool) {
								element = findElements(driver, tryProperty);
								xpath = tryProperty;
								if (element == null) list = identifyElementToClick(driver, parts[0], parts[1]);
							} else list = identifyElementToClick(driver, parts[0], parts[1]);
						} else list = identifyElementToClick(driver, parts[0], parts[1]);
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
				if (element == null && datapool) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts= splitString(property);
				}
			} while (datapool && element == null);
			int stop = 0;
			while (datapool && !xpath.equals("") && !xpath.contains(property.toLowerCase().replaceAll("\\s", "")) && stop < 10) {
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
				parts= splitString(property);
				stop++;
			}
			if(datapool) teststep.object_Label = property;
			if(element != null)
			{
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
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
				return Fonctions.logStepKO(teststep, driver, time1, "Le clic n'a pas ete execute");
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve");
			}
		} catch (Exception e) {
			// System.out.println (e.getMessage());
			// e.printStackTrace();
			return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve");	
		}	
	}

	public static boolean WebObject_sendkeysbytext(final WebDriver driver, Teststep teststep)
	{
		teststep.action_label = "Je saisis";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element = null;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			String parameter;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				switch(parts.length) {
					case 1:
						if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || tryProperty.contains("following") && !datapool) break;
						element= findElementsForInput(driver, tryProperty, "input");
						if(element == null) {
							element= findElementsForInput(driver, tryProperty, "textarea");
						}
						if(element == null) {
							element = findElements(driver, tryProperty);
						}
						break;
					case 2:
						if (parts[1].charAt(0) == '[' && parts[1].charAt(parts[1].length() - 1) == ']') {
							if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || tryProperty.contains("following") && !datapool) break;
							parts[1] = parts[1].substring(1, parts[1].length() - 1);
							element= findElementsForInputByIndex(driver, tryProperty, "input", parts[1]);
							if(element == null) {
								element= findElementsForInputByIndex(driver, tryProperty, "textarea", parts[1]);
							}
							if(element == null) {
								element = findElementsIndex(driver, tryProperty, parts[1]);
							}
							parts[1] = "[" + parts[1] + "]";
						} else {
							if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || !tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "")+ str) && !datapool) break;
							element= findElementsForInput(driver, tryProperty, "input");
							if(element == null) {
								element= findElementsForInput(driver, tryProperty, "textarea");
							}
							if(element == null) {
								element = findElements(driver, tryProperty);
							}
						}
						break;
					case 3:
						if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || !tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) && !datapool) break;
						if (parts[2].charAt(0) == '[' && parts[2].charAt(parts[2].length() - 1) == ']') {
							parts[2] = parts[2].substring(1, parts[2].length() - 1);
							element= findElementsForInputByIndex(driver, tryProperty, "input", parts[2]);
							if(element == null) {
								element= findElementsForInputByIndex(driver, tryProperty, "textarea", parts[2]);
							}
							if(element == null) {
								element = findElementsIndex(driver, tryProperty, parts[1]);
							}
							parts[2] = "[" + parts[2] + "]";
						} else {
							System.out.println("Error : the third property need to be an index between []");
							return Fonctions.logStepKO(teststep, driver, time1, "La troisieme propriete doit etre un index entre crochets []");
						}
						break;
					default:
						System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
						return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre entre 1 et 3.");
				}
				if(element != null) {
					xpath = tryProperty;
					elementfound = true;
				}
			}
			if (!elementfound) {
				do {
					switch(parts.length) {
						case 1:
							list = identifyElementInput(driver, parts[0]);
							break;
						case 2:
							if (parts[1].charAt(0) == '[' && parts[1].charAt(parts[1].length() - 1) == ']') {
								parts[1] = parts[1].substring(1, parts[1].length() - 1);
								list = identifyElementInputByIndex(driver, parts[0], parts[1]);
							} else {
								list = identifyElementInput(driver, parts[0], parts[1]);
							}
							break;
						case 3:
							if (parts[2].charAt(0) == '[' && parts[2].charAt(parts[2].length() - 1) == ']') {
								parts[2] = parts[2].substring(1, parts[2].length() - 1);
								list = identifyElementInputByIndex(driver, parts[0], parts[1], parts[2]);
							} else {
								System.out.println("Error : the third property need to be an index between []");
								return Fonctions.logStepKO(teststep, driver, time1, "La troisieme propriete doit etre un index entre crochets []");
							}
							break;
						default:
							System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
							return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre entre 1 et 3.");
					}
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}
				} while (element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
			}
			if(datapool) teststep.object_Label = property;
			
			if(element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				Fonctions.highLighterMethod(driver, element);
				try {
					element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
				} catch(Exception e) {}
				if (parameter.charAt(0) == '[' && parameter.charAt(parameter.length() - 1) == ']'){
					parameter = parameter.substring(1, parameter.length() - 1);
					String[] param= parameter.split("\\+");
					if (param[0].charAt(0) == 'J') {
						Calendar today = Calendar.getInstance();
						System.out.println(today.getTime().toString());
						if (param.length == 2) {
							today.add(Calendar.DAY_OF_MONTH, Integer.parseInt(param[1]));
							System.out.println(today.getTime().toString());
						}

						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String date = sdf.format(today.getTime()); 
						System.out.println(date);
						element.sendKeys(date);
						System.out.println("##### sendKeys made on :\n"+ element+"\n");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					}
				} else {
					element.sendKeys(parameter);
					System.out.println("##### sendKeys made on :\n"+ element+"\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'est pas bon.");
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "Le champ de saisie n'a pas ete trouve");
			}
		}catch(Exception e){
			System.out.println(e);
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue.");
		}
	}
	
	public static boolean WebObject_checkbytext(final WebDriver driver, Teststep teststep)
	{
		teststep.action_label = "Je verifie";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			String parameter;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				switch (parts.length) {
					case 1:
						if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && !datapool) break;
						element= findElementsForInput(driver, tryProperty, "input");
						if(element == null) {
							element= findElementsForInput(driver, tryProperty, "textarea");
						}
						if(element == null) {
							element = findElements(driver, tryProperty);
						}
						break;
						
					case 2:
						if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || !tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) && !datapool) break;
						element= findElementsForInput(driver, tryProperty, "input");
						if(element == null) {
							element= findElementsForInput(driver, tryProperty, "textarea");
						}
						if(element == null) {
							element = findElements(driver, tryProperty);
						}
						break;
					default:
						System.out.println("Error at switch, number of strings from teststep is not between 1 and 2");
						return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre 1 ou 2");
				}
				if(element != null) {
					xpath = tryProperty;
					elementfound = true;
				}
			}
			if (!elementfound) {
				do {
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
					if (element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}
				} while (element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if(element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				Fonctions.highLighterMethod(driver, element);
				try {
					System.out.println("##### check made on : \n" + element+"\n");
					if (parameter.charAt(0) == '[' && parameter.charAt(parameter.length() - 1) == ']'){
						parameter = parameter.substring(1, parameter.length() - 1);
						String[] param= parameter.split("\\+");
						if (param[0].charAt(0) == 'J') {
							Calendar today = Calendar.getInstance();
							System.out.println(today.getTime().toString());
							if (param.length == 2) {
								today.add(Calendar.DAY_OF_MONTH, Integer.parseInt(param[1]));
								System.out.println(today.getTime().toString());
							}
	
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							String date = sdf.format(today.getTime()); 
							System.out.println(date);
							List<WebElement> l= checkTextInElement(driver, element, date);
							//element.findElements(By.xpath(".//descendant-or-self::*[text()[contains(.,'"+date+"')] or contains(@value,'"+date+"')]"));
							// verify list size
							if ( l.size() > 0){
								System.out.println("<<<<<<<< Text: " + date + " is present. >>>>>>>>");
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							} else {
								try {
									String text_element=element.getAttribute("value");
									System.out.println("TEXT_ELEMENT "+ text_element);
									SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
									Date txtdate = formatter.parse(text_element);
									String textdate = sdf.format(txtdate); 
									System.out.println(textdate);
									if (textdate.equals(date)) {
										System.out.println("<<<<<<<< Text: " + date + " is present. >>>>>>>>");
										return Fonctions.logStepOK(teststep, driver, time1, xpath);
									} else {
										System.out.println("<<<<<<<< Text: " + date + " is not present. >>>>>>>>");
										return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : Le texte " + date +" n'est pas present dans l'objet " + property);	
									}
								} catch(Exception e) {
									System.out.println("<<<<<<<< Text: " + date + " is not present. >>>>>>>>");
									return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : Le texte " + date + " n'est pas present dans l'objet " + property);
								}
							}
						}
					} else {
						List<WebElement> l = checkTextInElement(driver, element, parameter);
						//List<WebElement> l = element.findElements(By.xpath(".//descendant-or-self::*[text()[contains(.,'"+parameter+"')] or contains(@value,'"+parameter+"')]"));
						// verify list size
						if ( l.size() > 0){
							System.out.println("<<<<<<<< Text: " + parameter + " is present. >>>>>>>>");
							return Fonctions.logStepOK(teststep, driver, time1, xpath);
						} else {
							try{
								String text_element=element.getAttribute("value");
								if (text_element.equals(parameter)) {
									System.out.println("<<<<<<<< Text: " + parameter + " is present. >>>>>>>>");
									return Fonctions.logStepOK(teststep, driver, time1, xpath);
								} else {
									System.out.println("<<<<<<<< Text: " + parameter + " is not present. >>>>>>>>");
									return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : Le texte " + parameter + " n'est pas present dans l'objet " + property);	
								}
							} catch(Exception e) {
								System.out.println("<<<<<<<< Text: " + parameter + " is not present. >>>>>>>>");
								return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : Le texte " + parameter + " n'est pas present dans l'objet " + property);	
							}
						}
					}
					
					return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
				}catch(Exception e){
					System.out.println(e);
					return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
				}
			}else {
				return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve");
			}
			}catch(Exception e){
				System.out.println(e);
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
			}
	}

	public static boolean WebObject_hoverbytext(final WebDriver driver, Teststep teststep)
	{	
		teststep.action_label = "Je deplace la souris";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				if (parts.length == 1) {
					if (tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || datapool) {
						element = findElements(driver, tryProperty);
					}
				} else {
					if (tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) || datapool) {
						element = findElements(driver, tryProperty);
					}
				}
				if(element != null) {
					xpath = tryProperty;
					elementfound = true;
				}
			}
			if (!elementfound) {
				do {
					if(parts.length == 1)
					{
						list = identifyElementHover(driver, parts[0]);
					} else {
						list = identifyElementHover(driver, parts[0], parts[1]);
					}
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}
				} while (element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if(element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				Fonctions.highLighterMethod(driver, element);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});",element);
				Thread.sleep(1000);
				Actions actions = new Actions(driver);
				actions.moveToElement(element).moveToElement(element).perform();
				System.out.println("##### hover made on :\n"+ element+"\n");
				return Fonctions.logStepOK(teststep, driver, time1, xpath);
			}else {
				return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve.");
			}
		}catch(Exception e){
			System.out.println(e);
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
		}
		
	}

	public static boolean WebObject_clickrightbytext(final WebDriver driver, Teststep teststep)
	{		
		teststep.action_label = "Je clique a droite";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement label_element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
					System.out.println("Cas datapool");
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
					property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				switch (parts.length) {
					case 1:
						if (tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains("local-name") || datapool){
							String[] trydirection = tryProperty.split("\\|");
							WebElement reference_element = findElements(driver, trydirection[0]);
							label_element = findElementsLabelDirection(driver, reference_element, trydirection[1], "right");
							if(label_element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					case 2:
					if (tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) || datapool){
						String[] trydirection = tryProperty.split("\\|");
						WebElement reference_element = findElements(driver, trydirection[0]);
						label_element = findElementsLabelDirection(driver, reference_element, trydirection[1], "right");
						if(label_element != null) {
							xpath = tryProperty;
							elementfound = true;
						}
					}
					break;
					default:
						break;
				}
				
			}
			if (!elementfound) {
				do {
					list = identifyElementRelative(driver, parts, "right");
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							label_element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (label_element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}
				} while (label_element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if (label_element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				Fonctions.highLighterMethod(driver, label_element);
				try {
					label_element.click();
					System.out.println("##### click right made on : \n"+ label_element + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### Element.click fail #####");
				}
				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", label_element);
					System.out.println("##### click right made on : \n"+ label_element + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### JavascriptExecutor click fail #####");
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
			}
			return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve");
		}catch(Exception e){
			System.out.println(e);
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
		}
	}
	
	public static boolean WebObject_clickbelowbytext(final WebDriver driver, Teststep teststep)
	{		
		teststep.action_label = "Je clique en dessous";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement label_element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				switch (parts.length) {
					case 1:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains("local-name") || datapool){
							String[] trydirection = tryProperty.split("\\|");
							WebElement reference_element = findElements(driver, trydirection[0]);
							label_element = findElementsLabelDirection(driver, reference_element, trydirection[1], "below");
							if(label_element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					case 2:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s","") + str) || datapool) {
							String[] trydirection = tryProperty.split("\\|");
							WebElement reference_element = findElements(driver, trydirection[0]);
							label_element = findElementsLabelDirection(driver, reference_element, trydirection[1], "below");
							if(label_element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					default:
						break;
				}
				
			}
			if (!elementfound) {
				do {
				list = identifyElementRelative(driver, parts, "below");
				if (list != null) {
					for(Map.Entry<String, WebElement> entry : list.entrySet()) {
						label_element = entry.getValue();
						xpath = entry.getKey();
					}
				}
				if (label_element == null && datapool) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts= splitString(property);
				}
				} while (label_element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if (label_element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				Fonctions.highLighterMethod(driver, label_element);
				try {
					label_element.click();
					System.out.println("##### click below made on : \n"+ label_element + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### Element.click fail #####");
				}
				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", label_element);
					System.out.println("##### click below made on : \n"+ label_element + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### JavascriptExecutor click fail #####");
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
			}
			return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve.");
		}catch(Exception e){
			System.out.println(e);
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
		}
	}
	
	public static boolean WebObject_clickleftbytext(final WebDriver driver, Teststep teststep)
	{		
		teststep.action_label = "Je clique a gauche";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement label_element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				switch (parts.length) {
					case 1:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains("local-name") || datapool) {
							String[] trydirection = tryProperty.split("\\|");
							WebElement reference_element = findElements(driver, trydirection[0]);
							label_element = findElementsLabelDirection(driver, reference_element, trydirection[1], "left");
							if(label_element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					case 2:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) || datapool) {
							String[] trydirection = tryProperty.split("\\|");
							WebElement reference_element = findElements(driver, trydirection[0]);
							label_element = findElementsLabelDirection(driver, reference_element, trydirection[1], "left");
							if(label_element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
					default:
						break;
				}
			}
			if (!elementfound) {
				do {
					list = identifyElementRelative(driver, parts, "left");
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							label_element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (label_element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}
				} while (label_element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if (label_element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				Fonctions.highLighterMethod(driver, label_element);
				try {
					label_element.click();
					System.out.println("##### click left made on : \n"+ label_element + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### Element.click fail #####");
				}
				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", label_element);
					System.out.println("##### click left made on : \n"+ label_element + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### JavascriptExecutor click fail #####");
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
			}
			return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve.");
		}catch(Exception e){
			System.out.println(e);
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
		}
	}
	
	public static boolean WebObject_clickabovebytext(final WebDriver driver, Teststep teststep)
	{		
		teststep.action_label = "Je clique au dessus";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement label_element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				switch (parts.length) {
					case 1:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains("local-name") || datapool){
							String[] trydirection = tryProperty.split("\\|");
							WebElement reference_element = findElements(driver, trydirection[0]);
							label_element = findElementsLabelDirection(driver, reference_element, trydirection[1], "above");
							if(label_element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					case 2:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) || datapool){
							String[] trydirection = tryProperty.split("\\|");
							WebElement reference_element = findElements(driver, trydirection[0]);
							label_element = findElementsLabelDirection(driver, reference_element, trydirection[1], "above");
							if(label_element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					default:
						break;
				}
			}
			if (!elementfound) {
				do {
					list = identifyElementRelative(driver, parts, "above");
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							label_element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (label_element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}
				} while (label_element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if (label_element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				Fonctions.highLighterMethod(driver, label_element);
				try {
					label_element.click();
					System.out.println("##### click above made on : \n"+ label_element + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### Element.click fail #####");
				}
				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", label_element);
					System.out.println("##### click above made on : \n"+ label_element + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### JavascriptExecutor click fail #####");
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
			}
			return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve");
		}catch(Exception e){
			System.out.println(e);
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
	}

    public static boolean WebObject_selectbytext(final WebDriver driver, Teststep teststep)
    {       
		teststep.action_label = "Je selectionne";
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
        Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
        }
        try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			String property = "";
			HashMap<String, Boolean> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
            String[] parts = {};
			Boolean datapool = false;
			if(array_prop_object.containsKey("texte")) datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
            if (array_prop_object.containsKey("texte")) {
				if (array_prop_object.get("texte").charAt(0) == '$'){
					property = getVariableProperty(array_prop_object.get("texte"));
					if (property.equals(array_prop_object.get("texte"))) {
						return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
					}
				} else if (datapool) {
					System.out.println("Cas datapool");
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
				} else {
					property = array_prop_object.get("texte");
				}
				parts= splitString(property);
            }
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
            boolean statusElement = true;
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
            switch(parts.length) {
                case 0:
                    try {
                        System.out.println("###### Start tries on dropdown ######");
						if (parameter.charAt(0) == '[' && parameter.charAt(parameter.length() - 1) == ']'){
							System.out.println("Error : You need to set a property when you want to use the index.");
							return Fonctions.logStepKO(teststep, driver, time1, "Il faut renseigner une propriete pour utiliser l'index.");
						}
						if (tryProperty != null && !tryProperty.isEmpty()) {
							if(!tryProperty.contains("parent")){
								statusElement = chooseByTry(driver, tryProperty);
								if(statusElement == true) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
						}
						if (!elementfound) {
							list = chooseByText(driver, parameter, statusElement);
							for(Map.Entry<String, Boolean> entry : list.entrySet()) {
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
								if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s","") + str) && tryProperty.contains(str + parameter + str) && StringUtils.countMatches(tryProperty, "parent")==1 || datapool){
									statusElement =  radioCheckBoxByTry(driver, tryProperty, parameter);
									if(statusElement == true) {
										xpath = tryProperty;
										elementfound = true;
									}
								}
							}
							if (!elementfound) {
								do {
									list = radioCheckBoxByText(driver, parts[0], statusElement, parameter);
									for(Map.Entry<String, Boolean> entry : list.entrySet()) {
										statusElement = entry.getValue();
										xpath = entry.getKey();
									}
									if (!statusElement && datapool) {
										property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
										parts= splitString(property);
									}
								} while (!statusElement && datapool);
								
							}
							if (statusElement == true) {
								if(datapool) {
									if(isJVExecution(teststep.testcase_label)) {
										addXpathDatapool(property, xpath, teststep.testcase_label);
									} else {
										deleteDatapoolVarFile();
									}
								}
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							}
						}catch(Exception e){
							System.out.println(e);
						}
					}
                    try {
                        System.out.println("###### Start tries on dropdown ######");
						if (tryProperty != null && !tryProperty.isEmpty()) {
							if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s","") + str) && tryProperty.contains(str + parameter + str) && StringUtils.countMatches(tryProperty, "parent")==1 || datapool){
								statusElement = chooseByTry(driver, tryProperty, parameter);
								if(statusElement == true) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
						}
						if (!elementfound) {
							do {
								list = chooseByText(driver, parts[0], parameter, statusElement);
								for(Map.Entry<String, Boolean> entry : list.entrySet()) {
									statusElement = entry.getValue();
									xpath = entry.getKey();
								}
								if (!statusElement && datapool) {
									property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
									parts= splitString(property);
								}
							} while (!statusElement && datapool);
							
						}
                        if (statusElement == true) {
							if(datapool) {
								if(isJVExecution(teststep.testcase_label)) {
									addXpathDatapool(property, xpath, teststep.testcase_label);
								} else {
									deleteDatapoolVarFile();
								}
							}
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
									if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && StringUtils.countMatches(tryProperty, "parent")==1 || datapool){
										statusElement =  radioCheckBoxByTryAndIndex(driver, tryProperty, parts[1], parameter);
										if(statusElement == true) {
											xpath = tryProperty;
											elementfound = true;
										}
									}
									
								}
								if (!elementfound) {
									do {
										list = radioCheckBoxByTextAndIndex(driver, parts[0], parts[1], statusElement, parameter);
										for(Map.Entry<String, Boolean> entry : list.entrySet()) {
											statusElement = entry.getValue();
											xpath = entry.getKey();
										}
										if (!statusElement && datapool) {
											property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
											parts= splitString(property);
										}
									} while (!statusElement && datapool);
									
								}
								if (statusElement == true) {
									if(datapool) {
										if(isJVExecution(teststep.testcase_label)) {
											addXpathDatapool(property, xpath, teststep.testcase_label);
										} else {
											deleteDatapoolVarFile();
										}
									}
									return Fonctions.logStepOK(teststep, driver, time1, xpath);
								}
							}catch(Exception e){
								System.out.println(e);
							}
						}
						try {
							System.out.println("###### Start tries on dropdown ######");
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parameter + str) && StringUtils.countMatches(tryProperty, "parent") == 1 || datapool){
									statusElement = chooseByTryAndIndex(driver, tryProperty, parts[1], parameter);
									if(statusElement == true) {
										xpath = tryProperty;
										elementfound = true;
									}
								}
							}
							if (!elementfound) {
								do {
									list = chooseByTextAndIndex(driver, parts[0], parts[1], parameter, statusElement);
									for(Map.Entry<String, Boolean> entry : list.entrySet()) {
										statusElement = entry.getValue();
										xpath = entry.getKey();
									}
									if (!statusElement && datapool) {
										property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
										parts= splitString(property);
									}
								} while (!statusElement && datapool);
								if(datapool) teststep.object_Label = property;
							}
							if (statusElement == true) {
								if(datapool) {
									if(isJVExecution(teststep.testcase_label)) {
										addXpathDatapool(property, xpath, teststep.testcase_label);
									} else {
										deleteDatapoolVarFile();
									}
								}
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
									if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s","") + str) && StringUtils.countMatches(tryProperty, "parent")==2 || datapool){
										statusElement =  radioCheckBoxByTry(driver, tryProperty, parameter);
										if(statusElement == true) {
											xpath = tryProperty;
											elementfound = true;
										}
									}
									
								}
								if (!elementfound) {
									do {
										list = radioCheckBoxByText(driver, parts[0], parts[1], statusElement, parameter);
										for(Map.Entry<String, Boolean> entry : list.entrySet()) {
											statusElement = entry.getValue();
											xpath = entry.getKey();
										}
										if (!statusElement && datapool) {
											property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
											parts= splitString(property);
										}
									} while (!statusElement && datapool);
									if(datapool) teststep.object_Label = property;
								}
								if (statusElement == true) {
									if(datapool) {
										if(isJVExecution(teststep.testcase_label)) {
											addXpathDatapool(property, xpath, teststep.testcase_label);
										} else {
											deleteDatapoolVarFile();
										}
									}
									return Fonctions.logStepOK(teststep, driver, time1, xpath);
								}
							}catch(Exception e){
								System.out.println(e);
							}
						}
						try {
							System.out.println("###### Start tries on dropdown ######");
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s","") + str) && tryProperty.contains(str + parameter + str) && StringUtils.countMatches(tryProperty, "parent")==2 || datapool){
									statusElement = chooseByTry(driver, tryProperty, parameter);
									if(statusElement == true) {
										xpath = tryProperty;
										elementfound = true;
									}
								}
							}
							if (!elementfound) {
								do {
									list = chooseByText(driver, parts[0], parts[1], parameter, statusElement);
									for(Map.Entry<String, Boolean> entry : list.entrySet()) {
										statusElement = entry.getValue();
										xpath = entry.getKey();
									}
									if (!statusElement && datapool) {
										property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
										parts= splitString(property);
									}
								} while (!statusElement && datapool);
								if(datapool) teststep.object_Label = property;
							}
							if (statusElement == true) {
								if(datapool) {
									if(isJVExecution(teststep.testcase_label)) {
										addXpathDatapool(property, xpath, teststep.testcase_label);
									} else {
										deleteDatapoolVarFile();
									}
								}
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
									if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) || datapool){
										statusElement =  radioCheckBoxByTryAndIndex(driver, tryProperty, parts[2], parameter);
										if(statusElement == true) {
											xpath = tryProperty;
											elementfound = true;
										}
									}
								}
								if (!elementfound) {
									do {
										list = radioCheckBoxByTextAndIndex(driver, parts[0], parts[1], parts[2], statusElement, parameter);
										for(Map.Entry<String, Boolean> entry : list.entrySet()) {
											statusElement = entry.getValue();
											xpath = entry.getKey();
										}
										if (!statusElement && datapool) {
											property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
											parts= splitString(property);
										}
									} while (!statusElement && datapool);
									if(datapool) teststep.object_Label = property;
								}
								if (statusElement == true) {
									if(datapool) {
										if(isJVExecution(teststep.testcase_label)) {
											addXpathDatapool(property, xpath, teststep.testcase_label);
										} else {
											deleteDatapoolVarFile();
										}
									}
									return Fonctions.logStepOK(teststep, driver, time1, xpath);
								}
							}catch(Exception e){
								System.out.println(e);
							}
						}
						try {
							System.out.println("###### Start tries on dropdown ######");
							if (tryProperty != null && !tryProperty.isEmpty()) {
								if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parameter + str) || datapool){
									statusElement = chooseByTryAndIndex(driver, tryProperty, parts[2], parameter);
									if(statusElement == true) {
										xpath = tryProperty;
										elementfound = true;
									}	
								}
							}
							if (!elementfound) {
								do {
									list = chooseByTextAndIndex(driver, parts[0], parts[1], parts[2], parameter, statusElement);
									for(Map.Entry<String, Boolean> entry : list.entrySet()) {
										statusElement = entry.getValue();
										xpath = entry.getKey();
									}
									if (!statusElement && datapool) {
										property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
										parts= splitString(property);
									}
								} while (!statusElement && datapool);
								if(datapool) teststep.object_Label = property;
							}
							if (statusElement == true) {
								if(datapool) {
									if(isJVExecution(teststep.testcase_label)) {
										addXpathDatapool(property, xpath, teststep.testcase_label);
									} else {
										deleteDatapoolVarFile();
									}
								}
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							}
						}catch(Exception e){
							System.out.println(e);
						}
					}
                default:
                    System.out.println("Error at switch, number of strings from teststep is not between 0 and 3");
                    return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris entre 0 et 3.");
            }
        }catch(Exception e){
            System.out.println("ERROR : ");
			e.printStackTrace();
            return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
        }
		return Fonctions.logStepKO(teststep, driver, time1, "L'objet n'a pas ete trouve");
	}

	public static boolean WebObject_clickbyindex(final WebDriver driver, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je clique par index";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			String parameter;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				switch (parts.length) {
					case 1:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && StringUtils.countMatches(tryProperty, "parent")==0 || datapool){
							element = findElementsIndex(driver, tryProperty, parameter);
							if(element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					case 2:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s",	"") + str) || datapool){
							element = findElementsIndex(driver, tryProperty, parameter);
							if(element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					case 3:
						if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[2].toLowerCase().replaceAll("\\s", "") + str) || datapool){
							element = findElementsIndex(driver, tryProperty, parameter);
							if(element != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					default:
						System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
						return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris entre 1 et 3.");
				}
			}
			if (!elementfound) {
				do {
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
							return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris entre 1 et 3.");
					}
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}
				} while (element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if(element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
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
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "Le " + parameter + "eme objet " + property + " n'a pas ete trouve.");
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");	
		}	
	}

	public static boolean WebObject_dragbytext(final WebDriver driver, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je glisse";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement destination = null;
			WebElement element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			HashMap<String, WebElement> listdestination = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts = splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				String[] trydrag = tryProperty.split("\\|");
				switch (parts.length) {
					case 2:
						if(trydrag[0].contains(str + parts[0].toLowerCase().replaceAll("\\s","") + str) && trydrag[1].contains(str + parts[1].toLowerCase().replaceAll("\\s","") + str) || datapool){
							element = findElements(driver, trydrag[0]);
							destination = findElements(driver, trydrag[1]);
							if(element != null && destination != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					case 3:
						if (parts[0].charAt(0) == '@') {
							if(trydrag[0].contains(parts[0].toLowerCase().replaceAll("\\s", "")) && trydrag[0].contains(str + parts[1].toLowerCase().replaceAll("\\s","") + str) && trydrag[1].contains(str + parts[2].toLowerCase().replaceAll("\\s", "") + str) || datapool){
								element = findElements(driver, trydrag[0]);
								destination = findElements(driver, trydrag[1]);
								if(element != null && destination != null) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
						} else {
							if(trydrag[0].contains(str + parts[0].toLowerCase().replaceAll("\\s","") + str) && trydrag[1].contains(parts[1].toLowerCase().replaceAll("\\s","")) && trydrag[1].contains(str + parts[2].toLowerCase().replaceAll("\\s","") + str) || datapool){
								element = findElements(driver, trydrag[0]);
								destination = findElements(driver, trydrag[1]);
								if(element != null && destination != null) {
									xpath = tryProperty;
									elementfound = true;
								}
							}
						}
						break;
					case 4:
						if(trydrag[0].contains(parts[0].toLowerCase().replaceAll("\\s","")) && trydrag[0].contains(str + parts[1].toLowerCase().replaceAll("\\s","") + str) && trydrag[1].contains(parts[2].toLowerCase().replaceAll("\\s","")) && trydrag[1].contains(str + parts[3].toLowerCase().replaceAll("\\s","") + str) || datapool){
							element = findElements(driver, trydrag[0]);
							destination = findElements(driver, trydrag[1]);
							if(element != null && destination != null) {
								xpath = tryProperty;
								elementfound = true;
							}
						}
						break;
					default:
					System.out.println("Error at switch");
					return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris de 2 à 4.");
				}
			}
			if (!elementfound) {
				do {
					if (parts[0].charAt(0) == '@')
					{
						list = identifyDragElementByAttr(driver, parts[0], parts[1]);
						if (parts[2].charAt(0) == '@')
						{
							listdestination = identifyDragElementByAttr(driver, parts[2], parts[3]);
						} else {
							listdestination = identifyDragElement(driver, parts[2]);
						}
					} else {
						list = identifyDragElement(driver, parts[0]);
						if (parts[1].charAt(0) == '@')
						{
							listdestination = identifyDragElementByAttr(driver, parts[1], parts[2]);
						} else {
							listdestination = identifyDragElement(driver, parts[1]);
						}
					}
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey()+"|";
						}
					}
					if (listdestination != null) {
						for(Map.Entry<String, WebElement> entry : listdestination.entrySet()) {
							destination = entry.getValue();
							xpath += entry.getKey();
						}
					}
					if (element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}
				} while (element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if(element != null && destination != null)
			{
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				Fonctions.highLighterMethod(driver, element);
				Fonctions.highLighterMethod(driver, destination);
				try {
					Actions act=new Actions(driver);		
					act.dragAndDrop(element, destination).build().perform();
					System.out.println("##### drag made on "+ element + " to " + destination + "\n");
					return Fonctions.logStepOK(teststep, driver, time1, xpath);
				} catch (Exception e) {
					System.out.println ("##### drag fail #####");
				}
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "Un objet au moins n'a pas ete trouve");
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");	
		}	
	}

	public static boolean WebObject_typetext(final WebDriver driver, Teststep teststep)
	{
		teststep.action_label = "Je tape";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element;
			String parameter;
			Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			if (parameter.charAt(0) == '[' && parameter.charAt(parameter.length() - 1) == ']'){
				parameter = parameter.substring(1, parameter.length() - 1);
				String[] param= parameter.split("\\+");
				if (param[0].charAt(0) == 'J') {
					Calendar today = Calendar.getInstance();
					System.out.println(today.getTime().toString());
					if (param.length == 2) {
						today.add(Calendar.DAY_OF_MONTH, Integer.parseInt(param[1]));
						System.out.println(today.getTime().toString());
					}

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					String date = sdf.format(today.getTime()); 
					System.out.println(date);
				new Actions(driver).sendKeys(date).perform();
					System.out.println("##### text input successfully ");
					return Fonctions.logStepOK(teststep, driver, time1);
				}
			} else {
				new Actions(driver).sendKeys(parameter).perform();
				System.out.println("##### text input successfully");
				return Fonctions.logStepOK(teststep, null, time1);
			}
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
		}catch(Exception e){
			System.out.println(e);
			return Fonctions.logStepKO(teststep, driver, time1, "Le champ de saisie n'a pas ete trouve.");
		}
	}

	public static boolean WebObject_checkcheckboxstatus(final WebDriver driver, Teststep teststep)
	{
		teststep.action_label = "Je verifie la checkbox";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			String parameter;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			Boolean datapoolparam = teststep.param.charAt(0) == '[' && teststep.param.charAt(teststep.param.length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else if (datapoolparam) {
				System.out.println("Cas datapool param");
				parameter = testOnDataPool(teststep.param.substring(1, teststep.param.length()-1), teststep);
				teststep.param = parameter;
			} else {
				parameter = teststep.param;
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String[] parts= splitString(property);
			if (datapool && !resetXpathDatapool(tryProperty, array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1))) {
				tryProperty = "";
			}
			if (tryProperty != null && !tryProperty.isEmpty()) {
				if (parts.length == 1) {
					if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && StringUtils.countMatches(tryProperty, "title") == 1 || datapool){
						element= findElementsForSelect(driver, tryProperty, "checkbox");
						if(element == null) {
							element = findElements(driver, tryProperty);
						}
					}
				} else {
					if(tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s","") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str) || datapool){
						element= findElementsForSelect(driver, tryProperty, "checkbox");
						if(element == null) {
							element = findElements(driver, tryProperty);
						}
					}
				}
				if(element != null) {
					xpath = tryProperty;
					elementfound = true;
				}
			}
			if (!elementfound) {
				do {
					if(parts.length == 1)
					{
						list = identifyElementCheckbox(driver, parts[0]);
					} else {
						list = identifyElementCheckbox(driver, parts[0], parts[1]);
					}
					if (list != null) {
						for(Map.Entry<String, WebElement> entry : list.entrySet()) {
							element = entry.getValue();
							xpath = entry.getKey();
						}
					}
					if (element == null && datapool) {
						property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
						parts= splitString(property);
					}	
				} while (element == null && datapool);
				while (datapool && !xpath.equals("") && !xpath.contains(parts[0].toLowerCase().replaceAll("\\s", ""))) {
					property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
					parts = splitString(property);
				}
				if(datapool) teststep.object_Label = property;
			}
			if(element != null) {
				if(datapool) {
					if(isJVExecution(teststep.testcase_label)) {
						addXpathDatapool(property, xpath, teststep.testcase_label);
					} else {
						deleteDatapoolVarFile();
					}
				}
				try {
					System.out.println("##### check made on : \n" + element+"\n");
					try {
						WebElement parent = element.findElement(By.xpath("./.."));
						Fonctions.highLighterMethod(driver, parent);
					} catch(Exception e) {
						System.out.println ("##### Highlight fail #####");
					} 
					switch(parameter) {
						case "Oui":
						case "oui":
						case "yes":
						case "Yes":
							if (element.isSelected()) {
								System.out.println("##### Element is selected :\n"+ element+"\n");
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							} else {
								System.out.println("##### Element is not selected :\n"+ element+"\n");
								return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : La checkbox " + property + " n'est pas checkee.");	
							}
						case "Non":
						case "non":
						case "No":
						case "no":
							if (!element.isSelected()) {
								System.out.println("##### Element is not selected :\n"+ element+"\n");
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							} else {
								System.out.println("##### Element is selected :\n"+ element+"\n");
								return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : La checkbox " + property + " est checkee.");	
							}
						default:
						System.out.println("Error with the parameter");
					}
					return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue.");
				}catch(Exception e){
					System.out.println(e);
					return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue lors de la verification.");
				}
			}else {
				return Fonctions.logStepKO(teststep, driver, time1, "La checkbox " + property +" n'a pas ete trouvee.");
			}
			}catch(Exception e){
				System.out.println(e);
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
			}
	}

	public static boolean WebObject_copybytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		teststep.action_label = "Je copie valeur";
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}

		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			String parameter = teststep.param;
			String value = "";
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}

			try {
				property = property.toLowerCase().replaceAll("\\s", "");
				String[] parts = splitString(property);
				System.out.println(property + "" + parts.length);
				List<WebElement> elements = new ArrayList<WebElement>();
				switch (parts.length) {
					case 1:
						xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]//following::*";
						elements = driver.findElements(By.xpath(xpath));
						if (elements.size() == 0) {
							xpath = "//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + property + str + ")]//following::*";
							elements = driver.findElements(By.xpath(xpath));
						}
						break;
					case 2:
						String context = parts[1];
						String label = parts[0];
						xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following::*//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]//following::*";
						elements = driver.findElements(By.xpath(xpath));
						if (elements.size() == 0) {
							xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")] or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ")]//parent::*//following::*//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@title,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@alt,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]//following::*";
							elements = driver.findElements(By.xpath(xpath));
						}
						break;
					default:
						break;
				}
				
				
				
				System.out.println(elements.size());
				for (WebElement elem : elements) {
					if (elem.getAttribute("value") != null) {
						value = elem.getAttribute("value");
						Fonctions.highLighterMethod(driver, elem);
						break;
					} else if (!elem.getText().equals("")) {
						value = elem.getText();
						Fonctions.highLighterMethod(driver, elem);
						break;
					} else {
						System.out.println("No value and no text for the element");
						value = "";
					}
				}
				System.out.println("Objet trouvé : \n" + xpath);
				System.out.println("Valeur " + value + " copiée dans la variable " + parameter);
			} catch (Exception e) {
				//e.printStackTrace();
				System.out.println("Error while searching element");
				return Fonctions.logStepKO(teststep, driver, time1, "L'element n'a pas ete trouve.");
			}
			
			if (!value.equals("")) {
				teststep.param = value;
				try {
					Wini ini = new Wini(new File(Config.propertyFile));
					ini.put("parameter", parameter, value);
					ini.store();
				} catch (Exception e) {
					System.out.println("issue occured while putting the value");
				}
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "L'element n'a pas ete trouve.");
			}

		} catch (Exception e) {
			// System.out.println(e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");
		}
		return Fonctions.logStepOK(teststep, driver, time1);
	}

	public static boolean WebObject_checkformulabytext(final WebDriver driver, Teststep teststep) throws IOException {
		Date time1 = new Date();
		teststep.action_label = "Je verifie formule";
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
	   	Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}

		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String file_jv = Config.dir_params + File.separator + teststep.testcase_label + "_datapool.csv";
			WebElement element = null;
			String property;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			String parameter = teststep.param;
			String value;
			Boolean datapool = array_prop_object.get("texte").charAt(0) == '[' && array_prop_object.get("texte").charAt(array_prop_object.get("texte").length()-1) == ']';
			String tryProperty = "";
			if (datapool && isJVExecution(teststep.testcase_label)) {
				tryProperty = Fonctions.getParameter(file_jv, Config.compteur_instance, Config.compteur_params);
			} else {
				tryProperty = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			}
			if (array_prop_object.get("texte").charAt(0) == '$'){
				property = getVariableProperty(array_prop_object.get("texte"));
				if (property.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
			} else if (datapool) {
				System.out.println("Cas datapool");
				property = testOnDataPool(array_prop_object.get("texte").substring(1, array_prop_object.get("texte").length()-1), teststep);
			} else {
				property = array_prop_object.get("texte");
			}
			String teString = " " + teststep.param;
			char[] chars = teString.toCharArray();
			int indexvar = 0;
			int indexvar2 = 0;
			for (int i = 0; i < chars.length; i++) {
				// System.out.println(i + "\n" + indexvar + "\n" + indexvar2 + "\n" + teString);
				if (chars[i] == '{') {
					indexvar = i;
				} else if (chars[i] == '}') {
					indexvar2 = i;
				}
				if (indexvar != 0 && indexvar2 != 0) {
					teString = teString.replaceFirst("\\{[^{]*\\}", getVariableParameter(teString.substring(indexvar, indexvar2)).replaceAll(",", "\\.").replaceAll("[^0-9.]", ""));
					chars = teString.toCharArray();
					indexvar = 0;
					indexvar2 = 0;
					i = 0;
				}
			}
			Expression exp = new ExpressionBuilder(teString).build();
			double result = exp.evaluate();
			System.out.println(result);
			try {
				teststep.param = String.valueOf(result);
				String[] parts = splitString(property);
				if (parts.length == 1) {
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
				if (element != null) {
					Fonctions.highLighterMethod(driver, element);
					List<WebElement> l = checkTextInElement(driver, element, String.valueOf(result));
					if ( l.size() > 0){
						System.out.println("<<<<<<<< Text: " + result + " is present. >>>>>>>>");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					} else {
						try{
							String text_element=element.getAttribute("value");
							if (text_element.contains(String.valueOf(result))) {
								System.out.println("<<<<<<<< Text: " + result + " is present. >>>>>>>>");
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							} else {
								System.out.println("<<<<<<<< Text: " + result + " is not present. >>>>>>>>");
								return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : La valeur " + result + " n'a pas ete trouvee.");	
							}
						} catch(Exception e) {
							System.out.println("<<<<<<<< Text: " + result + " is not present. >>>>>>>>");
							return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : La valeur " + result + " n'a pas ete trouvee.");	
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return Fonctions.logStepKO(teststep, driver, time1, e.getMessage());
		}
		return Fonctions.logStepOK(teststep, driver, time1);
	}

	// ######################################
	// ############ ActionsTable ############
	// ######################################
	
	public static boolean WebObject_clicktable(final WebDriver driver, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je clique la cellule";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			String[] property = {};
            if (array_prop_object.containsKey("texte")) {
            	String prop;
				if (array_prop_object.get("texte").charAt(0) == '$'){
					prop = getVariableProperty(array_prop_object.get("texte"));
				if (prop.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
				} else {
					prop = array_prop_object.get("texte");
				}
				property= splitString(prop);
            }
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			String[] param= splitString(parameter);
			WebElement element = null;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			if (tryProperty != null && !tryProperty.isEmpty()) {
				switch(property.length){
					case 0:
						element= findElementsTable(driver,tryProperty , param, "click");
						break;
					case 1:
						element= findElementsTable(driver,tryProperty, param, "click");
						break;
					case 2:
						element= findElementsTable(driver,tryProperty, param, "click");
						break;
					default:
						System.out.println("Error at switch, number of strings from teststep is not between 0 and 2");
						return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris entre 0 et 2.");
				}
				if(element != null) {
					xpath = tryProperty;
					elementfound = true;
				}
			}
			if (!elementfound) {
				switch(property.length){
					case 0:
						list= identifyElementTable(driver, param, "click");
						break;
					case 1:
						list= identifyElementTable(driver, property[0], param, "click");
						break;
					case 2:
						list= identifyElementTable(driver, property[0], property[1], param, "click");
						break;
					default:
						System.out.println("Error at switch, number of strings from teststep is not between 0 and 2");
						return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris entre 0 et 2.");
				}
				if (list != null) {
					for(Map.Entry<String, WebElement> entry : list.entrySet()) {
						element = entry.getValue();
						xpath = entry.getKey()+"|";
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
				return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue.");
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "L'objet " + property + " n'a pas ete trouve");
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");	
		}	
	}

	public static boolean WebObject_checktable(final WebDriver driver, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je verifie la cellule";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			String[] property = {};
            if (array_prop_object.containsKey("texte")) {
            	String prop;
				if (array_prop_object.get("texte").charAt(0) == '$'){
					prop = getVariableProperty(array_prop_object.get("texte"));
					if (prop.equals(array_prop_object.get("texte"))) {
						return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
					}
				} else {
					prop = array_prop_object.get("texte");
				}
				property= splitString(prop);
            }
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			String[] param= splitString(parameter);
			WebElement element = null;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			if (tryProperty != null && !tryProperty.isEmpty()) {
				String[] trytable = tryProperty.split("\\|");
				switch(property.length){
					case 0:
						element= findElementsTable(driver,tryProperty , param, "check");
						break;
					case 1:
						element= findElementsTable(driver,tryProperty, param, "check");
						break;
					case 2:
						element= findElementsTable(driver,tryProperty, param, "check");
						break;
					default:
						System.out.println("Error at switch, number of strings from teststep is not between 0 and 2");
						return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris entre 0 et 2.");
				}
				if(element != null) {
					xpath = tryProperty;
					elementfound = true;
				}
			}
			if (!elementfound) {
				switch(property.length){
					case 0:
						list= identifyElementTable(driver, param, "check");
						break;
					case 1:
						list= identifyElementTable(driver, property[0], param, "check");
						break;
					case 2:
						list= identifyElementTable(driver, property[0], property[1], param, "check");
						break;
					default:
						System.out.println("Error at switch, number of strings from teststep is not between 0 and 2");
						return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris entre 0 et 2.");
				}
				if (list != null) {
					for(Map.Entry<String, WebElement> entry : list.entrySet()) {
						element = entry.getValue();
						xpath = entry.getKey()+"|";
					}
				}
			}
			if(element != null) {
				Fonctions.highLighterMethod(driver, element);
				try {
					System.out.println("##### check made on : \n" + element+"\n");
					List<WebElement> l= element.findElements(By.xpath("./descendant-or-self::*[text()[contains(.,'"+param[2]+"')] or contains(@value,'"+param[2]+"')]"));
					// verify list size
					if ( l.size() > 0){
						System.out.println("<<<<<<<< Text: " + param[2] + " is present. >>>>>>>>");
						return Fonctions.logStepOK(teststep, driver, time1, xpath);
					} else {
						String text_element = element.getAttribute("value");
						if (text_element != null) {
							if (text_element.equals(param[2])) {
								System.out.println("<<<<<<<< Text: " + param[2] + " is present. >>>>>>>>");
								return Fonctions.logStepOK(teststep, driver, time1, xpath);
							}
						}
						System.out.println("<<<<<<<< Text: " + param[2] + " is not present. >>>>>>>>");
						return Fonctions.logStepWarning(teststep, driver, time1, "WARNING : La valeur " + param[2] + " n'a pas ete trouvee dans la cellule " + property);	
					}
				}catch(Exception e){
					System.out.println(e);
					return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue lors de la verification.");
				}
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "La cellule " + property + " n'a pas ete trouvee.");
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");	
		}	
	}

	public static boolean WebObject_sendkeystable(final WebDriver driver, Teststep teststep) throws IOException
	{
		teststep.action_label = "Je saisis dans la cellule";
		Date time1 = new Date();
		try {
			Thread.sleep(Config.pause_actions);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
		if (array_prop_object.isEmpty()){
			return Fonctions.logStepKO(teststep, driver, time1, "Le fichier n'existe pas :"+  teststep.object_attach_name);
		}
		try {
			String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
			String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
			String[] property = {};
            if (array_prop_object.containsKey("texte")) {
            	String prop;
				if (array_prop_object.get("texte").charAt(0) == '$'){
					prop = getVariableProperty(array_prop_object.get("texte"));
				if (prop.equals(array_prop_object.get("texte"))) {
					return Fonctions.logStepKO(teststep, driver, time1, "La propriete " + array_prop_object.get("texte") + " n'a pas ete trouvee dans le fichier des variables");
				}
				} else {
					prop = array_prop_object.get("texte");
				}
				property= splitString(prop);
            }
			String parameter;
			if (teststep.param.charAt(0) == '$'){
				parameter = getVariableParameter(teststep.param);
				if (parameter.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
			} else {
				parameter = teststep.param;
			}
			String[] param= splitString(parameter);
			WebElement element = null;
			HashMap<String, WebElement> list = new HashMap<>();
			String xpath = "";
			boolean elementfound = false;
			if (tryProperty != null && !tryProperty.isEmpty()) {
				String[] trytable = tryProperty.split("\\|");
				switch(property.length){
					case 0:
						element= findElementsTable(driver,tryProperty , param, "sendkeys");
						break;
					case 1:
						element= findElementsTable(driver,tryProperty, param, "sendkeys");
						break;
					case 2:
						element= findElementsTable(driver,tryProperty, param, "sendkeys");
						break;
					default:
						System.out.println("Error at switch, number of strings from teststep is not between 0 and 2");
						return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris de 0 à 2.");
				}
				if(element != null) {
					xpath = tryProperty;
					elementfound = true;
				}
			}
			if (!elementfound) {
			switch(property.length){
				case 0:
					list= identifyElementTable(driver, param, "sendkeys");
					break;
				case 1:
					list= identifyElementTable(driver, property[0], param, "sendkeys");
					break;
				case 2:
					list= identifyElementTable(driver, property[0], property[1], param, "sendkeys");
					break;
				default:
                    System.out.println("Error at switch, number of strings from teststep is not between 0 and 2");
                    return Fonctions.logStepKO(teststep, driver, time1, "Le nombre de proprietes doit etre compris de 0 à 2.");
			}
			if (list != null) {
				for(Map.Entry<String, WebElement> entry : list.entrySet()) {
					element = entry.getValue();
					xpath = entry.getKey()+"|";
				}
			}
		}
			if(element != null) {
				Fonctions.highLighterMethod(driver, element);
				try {
					element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
					element.sendKeys(param[2]);
					System.out.println("##### sendKeys made on :\n"+ element+"\n");
				return Fonctions.logStepOK(teststep, driver, time1, xpath);
				}catch(Exception e){
					System.out.println(e);
					return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue lors de la saisie.");
				}
			} else {
				return Fonctions.logStepKO(teststep, driver, time1, "Le champ de saisie n'a pas ete trouve.");
			}
		} catch (Exception e) {
			System.out.println (e.getMessage());
			return Fonctions.logStepKO(teststep, driver, time1, "Une erreur est survenue");	
		}	
	}

	// #########################################
	// ############ Other functions ############
	// ######################################### 
	
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

	public static WebElement getElementRelative(final WebDriver driver, List<WebElement> elements_list)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement element_to_return = null;
		for(WebElement element: elements_list) {
			try {
			wait.until(ExpectedConditions.visibilityOf(element));
			boolean status = element.isDisplayed();
			element_to_return=element;
			if(status)
			{break;}
			}catch(Exception e){
				System.out.println("		>>> Element not visible : \n "+element+"\n");
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
					wait.until(ExpectedConditions.visibilityOf(element));
					boolean status = element.isDisplayed();
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

	private static int comparejson(JSONObject obj, JSONObject objToCompare) {
		int number = 0;
		int size = objToCompare.size();
		for (Iterator iterator = obj.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String val = (String) obj.get(key).toString();
			if (val.contains("{")) {
				number += comparejson(((JSONObject) obj.get(key)), (JSONObject) objToCompare.get(key));
			} else {
				if (obj.get(key).equals(objToCompare.get(key))) {
					number++;
				} else {
					break;
				}
			}
		}
		return number;
	}

	private static int countJsonKey(JSONObject obj) {
		int nbr = 0;
		for (Iterator iterator = obj.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String val = obj.get(key).toString();
			if (val.contains("{")) {
				nbr += countJsonKey((JSONObject) obj.get(key));
			} else {
				nbr++;
			}
		}
		return nbr;
	}

	public static String getTextFromMimeMultipart(
			MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			// result += "\n\n";
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + /* "\n" + */ bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + /* "\n" + */ org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());

			}
		}
		return result;
	}

	public static String getVariableProperty(String texte) {
		String property = texte;
		try {
			Wini ini = new Wini(new File(Config.propertyFile));
			property = ini.get("property", texte.substring(1));
		} catch(Exception e) {
			System.out.println ("La propriété n'a pas été trouvée dans le fichier des variables.");
		}
		return property;
	}

	public static String getVariableParameter(String texte) {
		String param = texte;
		try {
			Wini ini = new Wini(new File(Config.propertyFile));
			param = ini.get("parameter", texte.substring(1));
		} catch(Exception e) {
			System.out.println ("Le paramètre n'a pas été trouvé dans le fichier des variables.");
		}
		return param;
	}

	public static String testOnDataPool(String colonne, Teststep teststep) {
		String prop = "";
		String existingProp ="";
		String propsaved = "";
		boolean isLastCellOfColumn = false;
		boolean isJV = false;
		boolean isJVKO = false;
		try {
			isJV = isJVExecution(teststep.testcase_label);
			File datapoolVarFile = new File(Config.additional_files + "/datapoolvar.ini");
			datapoolVarFile.createNewFile();
			datapoolVarFile.canWrite();
			try {
				Wini ini = new Wini(datapoolVarFile);
				existingProp = ini.get("datapool", colonne);
				if (existingProp.equals("")) {
					existingProp = null;
				}
			} catch (Exception e) {}
			if (isJV) {
				try {
					Wini ini = new Wini(datapoolVarFile);
					try {
						propsaved = ini.get("datapool", "savedforjv");
						if (propsaved == null) {
							ini.put("datapool", "savedforjv", existingProp);
							ini.store();
						} else if (propsaved.length() == 0){
							ini.put("datapool", "savedforjv", existingProp);
							ini.store();
						}
					} catch (Exception e) {}
				} catch (Exception e) {}
			}

			File csv1File = new File(Config.datapoolFile);
			CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(false).build();
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(csv1File)).withCSVParser(csvParser).build();
			// Récupérer les dimensions du csv
			String[] nextline;
			int j = 0;
			int k = 0;
			while ((nextline = csvReader.readNext()) != null) {
				j = j + 1;
				k = nextline.length;
			}
			csvReader.close();
			int nb1 = k;
			int nbl1 = j;
			// Créer le tableau qui contient le csv
			csvReader = new CSVReaderBuilder(new FileReader(csv1File)).withCSVParser(csvParser).build();
			String[][] total = new String[k][j];
			j = 0;
			while ((nextline = csvReader.readNext()) != null) {
				if (nextline != null) {
					for (int i = 0; i < nextline.length; i++) {
						total[i][j] = nextline[i];
					}
				}
				j = j + 1;
			}
			csvReader.close();
			boolean needreset = true;
			for (int i = 0; i < k; i++) {
				if (total[i][0].equals(colonne)) {
					for (int l = 1; l < j; l++) {
						if (total[i][l].equals(propsaved)) {
							needreset = false;
							break;
						}
					}
				}
			}
			if (needreset) {
				try {
					Wini ini = new Wini(datapoolVarFile);
					ini.put("datapool", "savedforjv", existingProp);
					ini.store();
				} catch (Exception e) {}
			}
			for (int i = 0; i < k; i++) {
				if (total[i][0].equals(colonne)) {
					for (int l = 1; l < j; l++) {
						if (!total[i][l].equals("")) {
							try {
								if (total[i][l+1].equals("") || total[i][l+1] == null) {
									isLastCellOfColumn = true;
								}
							} catch (Exception e) {isLastCellOfColumn = true;}
							
							// System.out.println(existingProp + " vaut\n##########################");
							if (existingProp == null || existingProp.equals("")) {
								prop = total[i][l];
								break;
							}else if(existingProp.equals(propsaved)) {
								prop = "";
								isJVKO = true;
								Wini ini = new Wini(new File(Config.additional_files + "/datapoolvar.ini"));
								ini.remove("datapool", "savedforjv");
								ini.store();
								break;
							}
							else if (!existingProp.equals("") && existingProp.equals(total[i][l]) && !isLastCellOfColumn && !isJV) {
								prop = total[i][l+1];
								break;
							}else if (!existingProp.equals("") && existingProp.equals(total[i][l]) && isJV) {
								if (isLastCellOfColumn) {
									prop = total[i][1];
									break;
								} else {
									prop = total[i][l+1];
									break;
								}
							}
						}
					}
				}
			}

			try {
				if (!prop.equals("")) {
					Wini ini = new Wini(datapoolVarFile);
					ini.put("datapool", colonne, prop);
					ini.store();
				}
			} catch (Exception e) {
				System.out.println("issue at put");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((isLastCellOfColumn && !isJV) || isJVKO) {
			String mail;
			String toAddress[] = new String[]{};//Création du tableau qui stockera toutes les adresses mails
			//Recuperation des adresses emails des destinataires
			try {
				Wini ini = new Wini(new File(Config.propertyFile));
				mail = ini.get("email", "destinataires");
				if(mail.length() != 0){ //Vérifie si la variable contient une adresse
				//isMail = true;
					toAddress = mail.split(",");
					int nbdestinataire = 0;
					for (String emails : toAddress) {
						nbdestinataire++;
						System.out.println("destinataire " + nbdestinataire+" : " +emails.trim());
						try { //Test de la validité des adresses mails
							InternetAddress internetAddress = new InternetAddress(emails);
							internetAddress.validate();
						} catch (AddressException ex) {
							System.out.println("email KO");
						}
					}
				}else System.out.println("Pas d'adresse mail definie"); //La variable est vide
			}
			catch(Exception e){ //la variable n'existe plus
				System.out.println("Pas d'adresse mail definie");
			}
			try {
				String host = "smtp-fr.securemail.pro";
				String port = "465 ";
				String username = "assistance.client@kalios.com";
				String password = "kalios2023";
				// Propriétés de la session
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", port);

				Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
				});

				for (String adresse:toAddress) {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(adresse));
					message.setSubject("Problème sur le datapool");
					message.setText("Nous vous informons que la colonne " + colonne + " du datapool arrive à la fin de ses valeurs");
					// System.out.println(host + port);
					// Envoi du message
					Transport.send(message);

					System.out.println("E-mail envoyé avec succès à l'adresse : " + adresse);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		
		//System.out.println(prop + "\nexit");
		return prop;
		
	}

	public static void deleteDatapoolVarFile() {
		try {
			File datapoolVarFile = new File(Config.additional_files + "/datapoolvar.ini");
			datapoolVarFile.delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void createDatapoolJVxpathFile(String testcase_label) {
		try {
			File file_base = new File(Config.dir_params + "/" + testcase_label + ".csv");
			File file_xpath = new File(Config.dir_params + "/" + testcase_label + "_datapool.csv");
			if (!file_xpath.exists()) {
				file_xpath.createNewFile();
			}
			try {
				CSVReader reader = new CSVReader(new FileReader(file_base), ';');
				CSVReader reader2 = new CSVReader(new FileReader(file_xpath), ';');
				String [] nextLine;
				List<String[]> list = new ArrayList<>();
				String [] firstLine = {};
				int numline = 1;
				String param = "";
				Boolean isEqual = false;
				while ((nextLine = reader.readNext()) != null) {
					if (numline == 1) firstLine = nextLine;
					list.add(nextLine);
					++numline;
				}
				numline = 1;
				while ((nextLine = reader2.readNext()) != null) {
					for (int i = 0; i < firstLine.length; i++) {
						if(numline==1){
							if(firstLine[i].equals(nextLine[i])) {
								isEqual = true;
							} else {
								isEqual = false;
							}
						} 
					}
					++numline;
				}
				// System.out.println(isEqual);
				if (!isEqual) {
					CSVWriter writer = new CSVWriter(new FileWriter(file_xpath), ';');
					writer.writeAll(list);
					writer.close();
				}
			} catch (Exception e) {
				// On essaie d'agir dedans
			}
		} catch (Exception e) {
			// Problème dans la création du fichier
		}
	}

	public static void addXpathDatapool(String property, String xpath, String testcase_label) {
		// System.out.println("############################################\n#######################");
		try {
			File file_params = new File(Config.dir_params + "/" + testcase_label + "_datapool.csv");
			CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(file_params), StandardCharsets.UTF_8),';');
			String [] nextLine;
			List<String[]> list = new ArrayList<>();
			String [] writeLine;
			int numline = 1;
			String param = "";
			while ((nextLine = reader.readNext()) != null) {
				writeLine = nextLine;
				if (numline==Config.compteur_instance) {
					writeLine[Config.compteur_params-1] = xpath;
					System.out.println("### valeur trouvée :" + writeLine[Config.compteur_params-1]);
				}
				list.add(writeLine);
				++numline;
			}
			CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(file_params, StandardCharsets.UTF_8), ';');
			writer.writeAll(list);
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public static boolean resetXpathDatapool(String tryProperty, String colonne) {
		try {
			File datapoolFile = new File(Config.datapoolFile);
			CSVReader reader2 = new CSVReader(new FileReader(datapoolFile), ';');

			String [] nextLine;
			int indexColonne = 0;
			int indexLine = 0;
			boolean isOK = false;

			while ((nextLine = reader2.readNext()) != null) {
				if (indexLine == 0) {
					for (int i = 0; i < nextLine.length; i++) {
						if (nextLine[i].equals(colonne)) {
							indexColonne = i;
						}
					}
				} else {
					if (tryProperty.toLowerCase().replaceAll("\\s", "").contains(nextLine[indexColonne].toLowerCase().replaceAll("\\s", ""))) {
						isOK = true;
						break;
					}
				}
				indexLine ++;
			}
			return isOK;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isJVExecution(String testcase_label) throws IOException {
		File csv1File = new File(Config.dir_params + "/" + testcase_label + ".csv");
		CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(false).build();
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(csv1File)).withCSVParser(csvParser).build();
		// Récupérer les dimensions du csv
		String[] nextline;
		int j = 0;
		int k = 0;
		while ((nextline = csvReader.readNext()) != null) {
			j = j + 1;
			k = nextline.length;
		}
		csvReader.close();
		// System.out.println("Les dimensions du tableau sont " + j + " x " + k);
		boolean isJV = j > 2;
		return isJV;
	}

	public static boolean WebPage_visualtesting(WebDriver selenium, Teststep teststep) throws IOException {
        Date time1 = new Date();
        WebDriver driver = (WebDriver) selenium;
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_elements));
        WebElement myHTML;
        myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
        try {
            System.out.println("Page displayed ? " + myHTML.isDisplayed());
        } catch (Exception e) {
            return Fonctions.logStepKO(teststep, selenium, time1, "Cannot find page");
        }
        String parameters;
        if (teststep.param.charAt(0) == '$') {
            parameters = getVariableParameter(teststep.param);
				if (parameters.equals(teststep.param)) {
					return Fonctions.logStepKO(teststep, driver, time1, "Le parametre n'a pas ete trouve dans le fichier des variables.");
				}
        } else {
            parameters = teststep.param;
        }
        String[] param = parameters.split("\\|");
        String applicationName = param[0];
        String testName = param[1];
        String apiKey, matchlevel;
        EyesRunner runner;
        BatchInfo batch;
        Configuration config;
        ChromeOptions options;
        WebDriver driverApplitools;
        Eyes eyes;
        try {
            runner = new VisualGridRunner(new RunnerOptions().testConcurrency(1));
            batch = new BatchInfo(testName);

            config = new Configuration();

            // Link API KEYS
            apiKey = "";

            try {
                Wini ini = new Wini(new File(Config.propertyFile));
                apiKey = ini.get("applitools", "cle");
            } catch (Exception e) {
                System.out.println("The parameter was not found in the variable file.");
                return Fonctions.logStepKO(teststep, selenium, time1, "Cle api introuvable");
            }
            config.setApiKey(apiKey);
            config.setBatch(batch);

            eyes = new Eyes(runner);
            eyes.setConfiguration(config);

            // Set match level
            matchlevel = param[2].toLowerCase();
            switch (matchlevel) {
                case "strict":
                    eyes.setMatchLevel(MatchLevel.STRICT);
                    break;
                case "layout":
                    eyes.setMatchLevel(MatchLevel.LAYOUT);
                    break;
                case "color":
                    eyes.setMatchLevel(MatchLevel.CONTENT);
                    break;
                case "exact":
                    eyes.setMatchLevel(MatchLevel.EXACT);
                    break;
                case "none":
                    eyes.setMatchLevel(MatchLevel.NONE);
                    break;
                default:
                    eyes.setMatchLevel(MatchLevel.STRICT);
                    break;
            }

            eyes.open(
                    driver,
                    applicationName,
                    testName);

            eyes.check(Target.window().fully());// .withName("Home Page"));
            eyes.closeAsync();
            TestResultsSummary allTestResults = runner.getAllTestResults(false);
            TestResultContainer[] val = allTestResults.getAllResults();
            TestResults res = val[0].getTestResults();
            Boolean diff = res.isDifferent();
            if (diff == false) {
                return Fonctions.logStepOK(teststep, selenium, time1);
            } else {
                return Fonctions.logStepWarning(teststep, selenium, time1,
                        "Difference trouve, rendez vous sur votre espace applitools pour en savoir plus");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // System.out.println("Une erreur s'est produite. Verifier votre cle API");
            return Fonctions.logStepKO(teststep, selenium, time1, "Verifier votre cle api");
        }
    }

    /*public static boolean WebPage_comparescreenshot(WebDriver selenium, Teststep teststep) throws IOException {
        Date time1 = new Date();
        WebDriver driverInstance = (WebDriver) selenium;
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
        WebElement myHTML;
        myHTML = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));
        try {
            System.out.println("Page displayed ? " + myHTML.isDisplayed());
        } catch (Exception e) {
            return Fonctions.logStepKO(teststep, selenium, time1, "Cannot find page");
        }

        // Declaration variables
        TakesScreenshot screenshotDriver;
        BufferedImage img1, img2;
        File file, screenshot, imgFile1, imgFile2, imgFileResult;
        String pathReference, pathResult, path, temp, parameters, opt, pathActualScreenshots;
        String[] tabTemp, spacedParameter;
        ResembleAnalysisOptions options;
        ResembleAnalysisResults results;
        ResembleParserData dataResult;
        Double taux;

        if (teststep.param.charAt(0) == '$') {
            try {
                Wini ini = new Wini(new File(Config.propertyFile));
                parameters = ini.get("parameter", teststep.param.substring(1));
            } catch (Exception e) {
                System.out.println("The parameter was not found in the variable file.");
                return Fonctions.logStepKO(teststep, null, time1,
                        "Cannot find the parameter in the variable file : " + e.getMessage());
            }
        } else {
            parameters = teststep.param;
        }
        // Recuperation des parametres pour savoir quelle option utilisée
        spacedParameter = parameters.split("\\|");
        opt = spacedParameter[1].toLowerCase();

        if (spacedParameter[2].contains(",")) {
            spacedParameter[2] = spacedParameter[2].replace(",", ".");
        }
        taux = Double.parseDouble(spacedParameter[2]);

        // Verification dossier screenshots pour reference exist
        temp = Config.dir_export;
        tabTemp = temp.split("/");

        temp = tabTemp[0] + "/" + tabTemp[1] + "/" + tabTemp[2] + "/" + "scripts" + "/" + "additional_files";
        path = temp + "/screenshots";
        file = new File(path);
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdir();
        }
        file = new File(path + "/references");
        if (file.exists() && file.isDirectory()) {
        } else {
            file.mkdir();
        }
        // Verificaton cas parcours / Plan de Test
        if (Config.dir_export.contains("__")) {
            // Cas plan de test
            String pathtemp = Config.dir_export;
            // Verify directory screeshot exist
            pathtemp += "/" + teststep.scenario_label + "/" + "screenshots";
            file = new File(pathtemp);
            if (file.exists() && file.isDirectory()) {
            } else {
                file.mkdirs();
            }
            // Verification et creation du fichier contenant les screenshot a tester
            file = new File(pathtemp + "/actualscreenshots");
            if (file.exists() && file.isDirectory()) {
            } else {
                file.mkdirs();
            }
            // Renvoie du path trouver
            pathActualScreenshots = pathtemp + "/actualscreenshots";
            pathResult = pathtemp + "/" + spacedParameter[0] + "Result.jpg";
        } else {
            String pathtemp = Config.dir_export + "/" + teststep.scenario_label;
            // Verify directory screeshot exist
            pathtemp += "/" + "screenshots";
            file = new File(pathtemp);
            if (file.exists() && file.isDirectory()) {
            } else {
                file.mkdirs();
            }
            // Verification et creation du fichier contenant les screenshot a tester
            file = new File(pathtemp + "/actualscreenshots");
            if (file.exists() && file.isDirectory()) {
            } else {
                file.mkdirs();
            }

            // Renvoie du path trouver
            pathResult = pathtemp + "/" + spacedParameter[0] + "Result.jpg";
            pathActualScreenshots = pathtemp + "/actualscreenshots";
        }

        // Take a screenshot and named it like parameter
        screenshotDriver = (TakesScreenshot) driverInstance;
        screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
        pathReference = path + "/" + "references" + "/" + spacedParameter[0] + "Reference.jpg";
        // pathResult = path + "/" + spacedParameter[0] + "Result.jpg";
        path = path + "/" + "actualscreenshots" + "/" + spacedParameter[0] + ".jpg";

        FileUtils.copyFile(screenshot, new File(pathActualScreenshots + "/" + spacedParameter[0] + ".jpg"));
        imgFile1 = new File(pathActualScreenshots + "/" + spacedParameter[0] + ".jpg");
        imgFile2 = new File(pathReference);
        imgFileResult = new File(pathResult);

        img1 = ImageUtils.readImage(imgFile1);
        try {
            img2 = ImageUtils.readImage(imgFile2);
        } catch (Exception e) {
            System.out.println("Pas de fichier de reference Creation ");
            FileUtils.copyFile(screenshot, new File(pathReference));
            return Fonctions.logStepOK(teststep, selenium, time1);
        }

        switch (opt) {
            case "antialiasing":
                options = ResembleAnaylsisOptionsTemplates.ignoringAntialiasing();
                break;
            case "color":
                options = ResembleAnaylsisOptionsTemplates.ignoringColors();
                break;
            case "alpha":
                options = ResembleAnaylsisOptionsTemplates.ignoringAlpha();
                break;
            case "less":
                options = ResembleAnaylsisOptionsTemplates.ignoringLess();
                break;
            case "nothing":
                options = ResembleAnaylsisOptionsTemplates.ignoringNothing();
                break;
            default:
                options = ResembleAnaylsisOptionsTemplates.ignoringAntialiasing();
                break;
        }
        results = new ResembleAnalysis(options).analyseImages(img1, img2);
        dataResult = ResembleParser.parse(results.getOutputImage());

        // Test creation de l'image
        try {
            BufferedImage bi = results.getOutputImage();
            ImageIO.write(bi, "png", imgFileResult);
        } catch (IOException e) {
            System.out.println("===========Exception levée===============");
        }

        System.out.println(String.format(" Mismatch percentage: %.2f%%\n", results.getMismatchPercentage()));
        // System.out.println("Taux : " + taux);
        DecimalFormat df = new DecimalFormat("#.##");
        // taux = df.format(taux);
        if (results.getMismatchPercentage() >= taux) {
            System.out.println("Erreur la  difference trop grande ");
            return Fonctions.logStepWarning(teststep, selenium, time1,
                    "Trop de difference visuelle " + results.getMismatchPercentage() + "% de difference");
        } else {
            System.out.println("Comparaison OK");
            return Fonctions.logStepOK(teststep, selenium, time1);
        }
    }*/
}