package scripts_techniques.Desktop;

import io.appium.java_client.AppiumBy;
import org.apache.commons.lang3.StringUtils;
import org.ini4j.Wini;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import scripts_techniques.Config;
import scripts_techniques.Selenium.Fonctions;
import scripts_techniques.Selenium.Teststep;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Scripts_techniques {

    static char str='\'';
    public static WebDriverWait wait;


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
                String attribute = element.getAttribute("ClassName");
                if(attribute!=null)
                {
                    xpath = "//" + tag + "[@Name=" + str + attribute + str + " or @AutomationId=" + str + attribute + str + "]";
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
                String attribute = element.getAttribute("ClassName");
                if(attribute!=null)
                {
                    xpath = "//" + tag + "[@Name=" + str + attribute + str + " or @AutomationId=" + str + attribute + str + "]";
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

    public static HashMap identifyElementToClick(WebDriver driver, String label)
    {
        String xpath;
        WebElement element;
        String selector;
        HashMap<String, WebElement> list = new HashMap<>();
        String label2 = label;
        label = label.toLowerCase().replaceAll("\\s","");

        try {
            //Button
            xpath = "//Button[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //MenuItem
            xpath = "//MenuItem[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //TreeItem
            xpath = "//TreeItem[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //Group
            xpath = "//Group[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //Button parent
            xpath = "//Button[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]/ancestor::Button";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //MenuItem parent
            xpath = "//MenuItem[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]/ancestor::MenuItem";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //TreeItem parent
            xpath = "//TreeItem[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]/ancestor::TreeItem";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //Group parent
            xpath = "//Group[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]/ancestor::Group";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains Button
            xpath = "//Button[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }

            //contains MenuItem
            xpath = "//MenuItem[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains TreeItem
            xpath = "//TreeItem[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains Group
            xpath = "//Group[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains Button parent
            xpath = "//Button[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::Button";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }

            //contains MenuItem parent
            xpath = "//MenuItem[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::MenuItem";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains TreeItem parent
            xpath = "//TreeItem[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::TreeItem";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains Group parent
            xpath = "//Group[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::Group";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //all
            xpath = "//*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains all
            xpath = "//*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static HashMap identifyElementToClick(WebDriver driver, String property, String context)
    {
        String xpath;
        WebElement element;
        String selector;
        HashMap<String, WebElement> list = new HashMap<>();
        String label2 = property;
        property = property.toLowerCase().replaceAll("\\s","");
        String context2 = context;
        context = context.toLowerCase().replaceAll("\\s","");
        String[] listObj = {"Button", "MenuItem", "TreeItem", "Group"};

        try {
            // follow sibling
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + context + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "')]//parent::*//following-sibling::"+listObj[i]+"[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + property + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + property + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

            // parent follow sibling
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + context + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "')]//parent::*//following-sibling::*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + property + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + property + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + property + "')]//ancestor::"+listObj[i];
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            // contains follow sibling
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//parent::*//following-sibling::"+listObj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            // parent contains follow sibling
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//parent::*//following-sibling::*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]//ancestor::"+listObj[i];
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            // follow
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + context + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "')]//parent::*//following::*//"+listObj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            // parent follow
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '\" + context + \"') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "')]//parent::*//following::*//*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]//ancestor::"+listObj[i];
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            // contains follow
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//parent::*//following::"+listObj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            // parent contains follow
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//parent::*//following::*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]//ancestor::"+listObj[i];
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            // all follow
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//parent::*//following::*//"+listObj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            // all follow sibling
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//parent::*//following::*//"+listObj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

            //all contains follow sibling
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//parent::*//following-sibling::*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

            //all contains follow
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//parent::*//following::*//*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }


        }catch(Exception e) {
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
            //Button
            xpath = "//Button[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //Group
            xpath = "//Group[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //MenuItem
            xpath = "//MenuItem[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //TreeItem
            xpath = "//TreeItem[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }

            //Button parent
            xpath = "//Button[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]/ancestor::Button";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //Group parent
            xpath = "//Group[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]/ancestor::Group";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //MenuItem parent
            xpath = "//MenuItem[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]/ancestor::MenuItem";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //TreeItem parent
            xpath = "//TreeItem[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]/ancestor::TreeItem";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }

            //contains Button
            xpath = "//Button[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains Group
            xpath = "//Group[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains Edit
            xpath = "//Edit[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains MenuItem
            xpath = "//MenuItem[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains TreeItem
            xpath = "//TreeItem[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }

            //contains Button parent
            xpath = "//Button[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::Button";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains Group parent
            xpath = "//Group[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::Group";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains Edit parent
            xpath = "//Edit[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::Edit";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains MenuItem parent
            xpath = "//MenuItem[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::MenuItem";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains TreeItem parent
            xpath = "//TreeItem[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]/ancestor::TreeItem";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //all
            xpath = "//*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + label + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + label + "')]";
            element = findElementsIndex(driver, xpath, param);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }
            //contains all
            xpath = "//*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + label + "')]";
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
    public static HashMap identifyElementsToClickByIndexAvecContext(final WebDriver driver, String context, String property, String index)
    {
        String xpath;
        WebElement element;
        HashMap<String, WebElement> list = new HashMap<>();
        context = context.toLowerCase().replaceAll("\\s","");
        property = property.toLowerCase().replaceAll("\\s","");
        String[] listObj = {"Button", "MenuItem", "TreeItem", "Group"};


        try {
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + " or translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + " or translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + " or translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + "]//following::" + listObj[i] + "[translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + " or translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + " or translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + " or translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + property + str + "]";
                element = findElementsIndex(driver, xpath, index);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            for (int i = 0; i < listObj.length; i++) {
                xpath = "//*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str +  context + str  + ") or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + context + str + ")]//following::*//"+listObj[i]+"[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@content-desc, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ")]";
                element = findElementsIndex(driver, xpath, index);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            for (int i = 0; i < listObj.length; i++) {
            xpath = "//*[translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + " or translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')= " + str + context + str + "]//following::*[translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + " or translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz')=" + str + property + str + "]";
                element = findElementsIndex(driver, xpath, index);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
            for (int i = 0; i < listObj.length; i++) {
            xpath = "//*[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + context + str + " ) or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + context + str + ")]//following::*[contains(translate(@Name, 'ABCDEFGHIJkLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ") or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ ', 'abcdefghijklmnopqrstuvwxyz'), " + str + property + str + ")]";
            element = findElementsIndex(driver, xpath, index);
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
    public static HashMap identifyElementInput(WebDriver driver, String property) {
        String xpath;
        WebElement element;
        HashMap<String, WebElement> list = new HashMap<>();
        property = property.toLowerCase().replaceAll("\\s","");
        String[] listobj = {"Edit", "Document", "ComboBox"};

        try {
            // Elements
            for (int i = 0; i < listobj.length; i++) {
                xpath = "//" + listobj[i] + "[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + property + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + property + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

            // Elements contains
            for (int j = 0; j < listobj.length; j++) {
                xpath = "//" + listobj[j] + "[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public static HashMap identifyElementInput(WebDriver driver, String property, String context) {
        String xpath;
        WebElement element;
        HashMap<String, WebElement> list = new HashMap<>();
        property = property.toLowerCase().replaceAll("\\s","");
        String[] listobj = {"Edit", "Document", "ComboBox"};

        try {
            // Following
            for (int i = 0; i < listobj.length; i++) {
                xpath = "//*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + context + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "')]//following::"+listobj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

            // Following contains
            for (int i = 0; i < listobj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//following::"+listobj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElements(driver, xpath);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
    public static HashMap identifyElementInputByIndex(WebDriver driver, String property, String index) {
        String xpath;
        WebElement element;
        HashMap<String, WebElement> list = new HashMap<>();
        property = property.toLowerCase().replaceAll("\\s","");
        String[] listobj = {"Edit", "Document", "ComboBox"};

        try {
            // Elements
            for (int i = 0; i < listobj.length; i++) {
                xpath = "//" + listobj[i] + "[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + property + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + property + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + property + "')]";
                element = findElementsIndex(driver, xpath, index);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

            // Elements contains
            for (int j = 0; j < listobj.length; j++) {
                xpath = "//" + listobj[j] + "[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElementsIndex(driver, xpath, index);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public static HashMap identifyElementInputByIndex(WebDriver driver, String property, String context, String index) {
        String xpath;
        WebElement element;
        HashMap<String, WebElement> list = new HashMap<>();
        property = property.toLowerCase().replaceAll("\\s","");
        String[] listobj = {"Edit", "Document", "ComboBox"};

        try {
            // Following
            for (int i = 0; i < listobj.length; i++) {
                xpath = "//*[(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz') = '" + context + "') or (translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "') or (translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')= '" + context + "')]//following::"+listobj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElementsIndex(driver, xpath, index);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }

            // Following contains
            for (int i = 0; i < listobj.length; i++) {
                xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + context + "')]//following::"+listobj[i]+"[contains(translate(@Name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@Value, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "') or contains(translate(@AutomationId, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz'), '" + property + "')]";
                element = findElementsIndex(driver, xpath, index);
                if (element != null) {
                    list.put(xpath, element);
                    return list;
                }
            }
        } catch (Exception e) {
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
            xpath = "//*[translate(@Name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@Value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + " or translate(@AutomationId,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + context + str + "]//parent::*//following-sibling::*[translate(@Name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@Value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@AutomationId,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }

            //follow sibling contains
            xpath = "//*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@Value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + ") or contains(translate(@AutomationId,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + context + str + "))]//parent::*//following-sibling::*[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@Name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@Value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@AutomationId,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
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
            xpath = "//*[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@Name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@Value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + " or translate(@AutomationId,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')=" + str + label + str + "]";
            element = findElements(driver, xpath);
            if (element != null) {
                list.put(xpath, element);
                return list;
            }

            //contains
            xpath = "//*[text()[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")] or contains(translate(@Name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@Value,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ") or contains(translate(@AutomationId,'ABCDEFGHIJKLMNOPQRSTUVWXYZ  ','abcdefghijklmnopqrstuvwxyz')," + str + label + str + ")]";
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


    public static boolean  WebObject_clickbytext(final WebDriver driver, Teststep teststep) throws IOException
    {
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, null, time1, "No such file :"+  teststep.object_attach_name);
        }
        try {
            String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
            String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
            WebElement element = null;
            String property;
            HashMap<String, WebElement> list = new HashMap<>();
            String xpath = "";
            if (array_prop_object.get("texte").charAt(0) == '$'){
                try {
                    Wini ini = new Wini(new File(Config.propertyFile));
                    property = ini.get("property", array_prop_object.get("texte").substring(1));
                } catch(Exception e) {
                    System.out.println ("The parameter was not found in the variable file.");
                    return Fonctions.logStepKO(teststep, null, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
                }
            } else {
                property = array_prop_object.get("texte");
            }
            String[] parts= splitString(property);
            if(parts.length == 1)
            {
                if (tryProperty != null && !tryProperty.isEmpty()) {
                    System.out.println(str + parts[0].toLowerCase().replaceAll("\\s", "")+ str);
                    if (tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "")+ str)) {
                        element = findElements(driver, tryProperty);
                        xpath = tryProperty;
                        if (element == null) list = identifyElementToClick(driver, parts[0]);
                    } else list = identifyElementToClick(driver, parts[0]);
                } else list = identifyElementToClick(driver, parts[0]);
            } else {
                if (tryProperty != null && !tryProperty.isEmpty()) {
                    if (tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) && tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str)) {
                        element = findElements(driver, tryProperty);
                        xpath = tryProperty;
                        if (element == null) list = identifyElementToClick(driver, parts[0], parts[1]);
                    } else list = identifyElementToClick(driver, parts[0], parts[1]);
                } else list = identifyElementToClick(driver, parts[0], parts[1]);
            }
            if (list != null) {
                for(Map.Entry<String, WebElement> entry : list.entrySet()) {
                    element = entry.getValue();
                    xpath = entry.getKey();
                }
            }
            if(element != null)
            {
                try {
                    element.click();
                    System.out.println("##### click made on : \n"+ element + "\n");
                    return Fonctions.logStepOK(teststep, null, time1, xpath);
                } catch (Exception e) {
                    System.out.println ("##### Element.click fail #####");
                }
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    System.out.println("##### click made on : \n"+ element + "\n");
                    return Fonctions.logStepOK(teststep, null, time1, xpath);
                } catch (Exception e) {
                    System.out.println ("##### JavascriptExecutor click fail #####");
                }
                return Fonctions.logStepKO(teststep, null, time1, "click Fail");
            } else {
                return Fonctions.logStepKO(teststep, null, time1, "Element is not found");
            }
        } catch (Exception e) {
            System.out.println (e.getMessage());
            return Fonctions.logStepKO(teststep, null, time1, "Cannot perform action in obj : " +e.getMessage());
        }
    }
    public static boolean WebObject_sendkeysbytext(final WebDriver driver, Teststep teststep)
    {
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, null, time1, "No such file :"+  teststep.object_attach_name);
        }
        try {
            String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
            String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
            WebElement element = null;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            String property;
            HashMap<String, WebElement> list = new HashMap<>();
            String xpath = "";
            boolean elementfound = false;
            String parameter;
            if (teststep.param.charAt(0) == '$'){
                try {
                    Wini ini = new Wini(new File(Config.propertyFile));
                    parameter = ini.get("parameter", teststep.param.substring(1));
                } catch(Exception e) {
                    System.out.println ("The parameter was not found in the variable file.");
                    return Fonctions.logStepKO(teststep, null, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
                }
            } else {
                parameter = teststep.param;
            }

            if (array_prop_object.get("texte").charAt(0) == '$'){
                try {
                    Wini ini = new Wini(new File(Config.propertyFile));
                    property = ini.get("property", array_prop_object.get("texte").substring(1));
                } catch(Exception e) {
                    System.out.println ("The property was not found in the variable file.");
                    return Fonctions.logStepKO(teststep, null, time1, "Cannot find the property in the variable file : " +e.getMessage());
                }
            } else {
                property = array_prop_object.get("texte");
            }
            String[] parts= splitString(property);

            if (tryProperty != null && !tryProperty.isEmpty()) {
                switch(parts.length) {
                    case 1:
                        if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || tryProperty.contains("following")) break;
                        element= findElementsForInput(driver, tryProperty, "Edit");
                        if(element == null) {
                            element= findElementsForInput(driver, tryProperty, "textarea");
                        }
                        if(element == null) {
                            element = findElements(driver, tryProperty);
                        }
                        break;
                    case 2:
                        if (parts[1].charAt(0) == '[' && parts[1].charAt(parts[1].length() - 1) == ']') {
                            if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || tryProperty.contains("following")) break;
                            parts[1] = parts[1].substring(1, parts[1].length() - 1);
                            element= findElementsForInputByIndex(driver, tryProperty, "Edit", parts[1]);
                            if(element == null) {
                                element= findElementsForInputByIndex(driver, tryProperty, "textarea", parts[1]);
                            }
                            if(element == null) {
                                element = findElementsIndex(driver, tryProperty, parts[1]);
                            }
                            parts[1] = "[" + parts[1] + "]";
                        } else {
                            if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || !tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "")+ str)) break;
                            element= findElementsForInput(driver, tryProperty, "Edit");
                            if(element == null) {
                                element= findElementsForInput(driver, tryProperty, "textarea");
                            }
                            if(element == null) {
                                element = findElements(driver, tryProperty);
                            }
                        }
                        break;
                    case 3:
                        if (!tryProperty.contains(str + parts[0].toLowerCase().replaceAll("\\s", "") + str) || !tryProperty.contains(str + parts[1].toLowerCase().replaceAll("\\s", "") + str)) break;
                        if (parts[2].charAt(0) == '[' && parts[2].charAt(parts[2].length() - 1) == ']') {
                            parts[2] = parts[2].substring(1, parts[2].length() - 1);
                            element= findElementsForInputByIndex(driver, tryProperty, "Edit", parts[2]);
                            if(element == null) {
                                element= findElementsForInputByIndex(driver, tryProperty, "textarea", parts[2]);
                            }
                            if(element == null) {
                                element = findElementsIndex(driver, tryProperty, parts[1]);
                            }
                            parts[2] = "[" + parts[2] + "]";
                        } else {
                            System.out.println("Error : the third property need to be an index between []");
                            return Fonctions.logStepKO(teststep, null, time1, "Error : the third property need to be an index between []");
                        }
                        break;
                    default:
                        System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
                        return Fonctions.logStepKO(teststep, null, time1, "Error at switch, number of strings from teststep is not between 1 and 3");
                }
                if(element != null) {
                    xpath = tryProperty;
                    elementfound = true;
                }
            }
            if (!elementfound) {
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
                            return Fonctions.logStepKO(teststep, null, time1, "Error : the third property need to be an index between []");
                        }
                        break;
                    default:
                        System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
                        return Fonctions.logStepKO(teststep, null, time1, "Error at switch, number of strings from teststep is not between 1 and 3");
                }
                if (list != null) {
                    for(Map.Entry<String, WebElement> entry : list.entrySet()) {
                        element = entry.getValue();
                        xpath = entry.getKey();
                    }
                }
            }

            if(element != null) {
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
                        return Fonctions.logStepOK(teststep, null, time1, xpath);
                    }
                } else {
                    element.sendKeys(parameter);
                    System.out.println("##### sendKeys made on :\n"+ element+"\n");
                    return Fonctions.logStepOK(teststep, null, time1, xpath);
                }
                return Fonctions.logStepKO(teststep, null, time1, "There is a problem with the parameter");
            } else {
                return Fonctions.logStepKO(teststep, null, time1, "element is empty");
            }
        }catch(Exception e){
            System.out.println(e);
            return Fonctions.logStepKO(teststep, null, time1, "Something when wrong geting the list of elements");
        }
    }

    public static boolean WebObject_clickbyindex(final WebDriver driver, Teststep teststep) throws IOException
    {
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, null, time1, "No such file :"+  teststep.object_attach_name);
        }
        try {
            String file_params = Config.dir_params +  File.separator + teststep.testcase_label + "_xpath_list.csv";
            String tryProperty  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
            WebElement element = null;
            String property;
            HashMap<String, WebElement> list = new HashMap<>();
            String xpath = "";
            boolean elementfound = false;
            String parameter;
            if (teststep.param.charAt(0) == '$'){
                try {
                    Wini ini = new Wini(new File(Config.propertyFile));
                    parameter = ini.get("parameter", teststep.param.substring(1));
                } catch(Exception e) {
                    System.out.println ("The parameter was not found in the variable file.");
                    return Fonctions.logStepKO(teststep, null, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
                }
            } else {
                parameter = teststep.param;
            }
            if (array_prop_object.get("texte").charAt(0) == '$'){
                try {
                    Wini ini = new Wini(new File(Config.propertyFile));
                    property = ini.get("property", array_prop_object.get("texte").substring(1));
                } catch(Exception e) {
                    System.out.println ("The property was not found in the variable file.");
                    return Fonctions.logStepKO(teststep, null, time1, "Cannot find the property in the variable file : " +e.getMessage());
                }
            } else {
                property = array_prop_object.get("texte");
            }
            String[] parts= splitString(property);
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
                        return Fonctions.logStepKO(teststep, null, time1, "Error at switch, number of strings from teststep is not between 1 and 3");
                }
            }
            if (!elementfound) {
                switch(parts.length){
                    case 1:
                        list = identifyElementsToClickByIndex(driver, parts[0], parameter);
                        break;
                    case 2:
                        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< je suis ici");
                        list = identifyElementsToClickByIndexAvecContext(driver, parts[0], parts[1], parameter);
                        break;

                    default:
                        System.out.println("Error at switch, number of strings from teststep is not between 1 and 3");
                        return Fonctions.logStepKO(teststep, null, time1, "Error at switch, number of strings from teststep is not between 1 and 3");
                }
                if (list != null) {
                    for(Map.Entry<String, WebElement> entry : list.entrySet()) {
                        element = entry.getValue();
                        xpath = entry.getKey();
                    }
                }
            }
            if(element != null) {
                try {
                    element.click();
                    System.out.println("##### click made on : \n"+ element + "\n");
                    return Fonctions.logStepOK(teststep, null, time1, xpath);
                } catch (Exception e) {
                    System.out.println ("##### Element.click fail #####");
                }
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    System.out.println("##### click made on : \n"+ element + "\n");
                    return Fonctions.logStepOK(teststep, null, time1, xpath);
                } catch (Exception e) {
                    System.out.println ("##### JavascriptExecutor click fail #####");
                }
                return Fonctions.logStepKO(teststep, null, time1, "click Fail");
            } else {
                return Fonctions.logStepKO(teststep, null, time1, "Element is not found");
            }
        } catch (Exception e) {
            System.out.println (e.getMessage());
            return Fonctions.logStepKO(teststep, null, time1, "Cannot perform action in obj : " +e.getMessage());
        }
    }

    public static boolean clickbyname(WebDriver driver,Teststep teststep) {

            Date time1 = new Date();
            WebDriver driverInstance = driver;
            try {
                Thread.sleep(Config.pause_actions);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
            WebElement myObj;
            Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
            if (array_prop_object.isEmpty()){
                return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
            }
            try {
                try {
                    myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(array_prop_object.get("name"))));

                    myObj.click();
                    System.out.println("##### click made on : \n"+ myObj + "\n");
                    return Fonctions.logStepOK(teststep, null, time1);
                } catch (Exception e) {
                    System.out.println ("##### Element.click fail #####");
                }

                return Fonctions.logStepKO(teststep, null, time1, "click Fail");
            } catch (Exception e) {
                System.out.println (e.getMessage());
                return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());
            }
        }


    public static boolean clickbyxpath(WebDriver driver,Teststep teststep) {
        Date time1 = new Date();
        WebDriver driverInstance = (WebDriver) driver;
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driverInstance, Duration.ofMillis(Config.timeout_elements));
        WebElement myObj;
        Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, driverInstance, time1, "No such file :"+  teststep.object_attach_name);
        }
        try {
            myObj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(array_prop_object.get("xpath"))));
            myObj.click();
            return Fonctions.logStepOK(teststep, driverInstance, time1);
        } catch (Exception e) {
            System.out.println (e.getMessage());
            return Fonctions.logStepKO(teststep, driverInstance, time1, "Cannot perform action in obj : " +e.getMessage());
        }
    }

    public static boolean clickbyAutomationID(WebDriver driver, Teststep teststep) {
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Config.timeout_elements));
        WebElement myObj;
        Hashtable< String, String>  array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, driver, time1, "No such file :"+  teststep.object_attach_name);
        }
        try {
            try {
                myObj = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(array_prop_object.get("AutomationID"))));
                myObj.click();
                System.out.println("##### click made on : \n"+ myObj + "\n");
                return Fonctions.logStepOK(teststep, null, time1);
            } catch (Exception e) {
                System.out.println ("##### Element.click fail #####");
            }

            return Fonctions.logStepKO(teststep, null, time1, "click Fail");
        } catch (Exception e) {
            System.out.println (e.getMessage());
            return Fonctions.logStepKO(teststep, driver, time1, "Cannot perform action in obj : " +e.getMessage());
        }
    }


    public static boolean sendkeysbyName(WebDriver selfdriver, Teststep teststep) throws IOException, InterruptedException {

        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(selfdriver, Duration.ofMillis(Config.timeout_elements));
        WebElement myObj;
        Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, selfdriver, time1, "No such file :"+  teststep.object_attach_name);
        }

            String parameter;
            if (teststep.param.charAt(0) == '$'){
                try {
                    Wini ini = new Wini(new File(Config.propertyFile));
                    parameter = ini.get("parameter", teststep.param.substring(1));
                } catch(Exception e) {
                    System.out.println ("The parameter was not found in the variable file.");
                    return Fonctions.logStepKO(teststep, null, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
                }
            } else {
                parameter = teststep.param;
            }
            try {
                String command = "powershell Set-WinUserLanguageList -LanguageList en-US -Force";
                Process process = Runtime.getRuntime().exec(command);
                process.waitFor();
                boolean write = false;
                List<WebElement> Elem = selfdriver.findElements(AppiumBy.name(array_prop_object.get("name")));
                System.out.println(Elem);
                for (WebElement web : Elem) {
                    if (Objects.equals(web.getAttribute("ClassName"), "Edit") || Objects.equals(web.getAttribute("AriaRole"), "textbox")) {
                        web.sendKeys(teststep.param);
                        write = true;
                        break;

                    }
                }
                String restoreCommand = "powershell Set-WinUserLanguageList -LanguageList fr-FR -Force";
                Process restoreProcess = Runtime.getRuntime().exec(restoreCommand);
                restoreProcess.waitFor();
                if (write)
                    return Fonctions.logStepOK(teststep, selfdriver, time1);
                else
                    return Fonctions.logStepKO(teststep, selfdriver, time1, "Click fail");
            } catch (Exception e) {
                e.printStackTrace();
                String restoreCommand = "powershell Set-WinUserLanguageList -LanguageList fr-FR -Force";
                Process restoreProcess = Runtime.getRuntime().exec(restoreCommand);
                restoreProcess.waitFor();
                return Fonctions.logStepKO(teststep, selfdriver, time1, "Cannot perform action in obj : " +e.getMessage());
            }

    }

    public static boolean sendkeysbyAutomationId(WebDriver selfdriver,Teststep  teststep) throws IOException, InterruptedException {
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(selfdriver, Duration.ofMillis(Config.timeout_elements));
        WebElement myObj;
        Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, selfdriver, time1, "No such file :"+  teststep.object_attach_name);
        }

        String parameter;
        if (teststep.param.charAt(0) == '$'){
            try {
                Wini ini = new Wini(new File(Config.propertyFile));
                parameter = ini.get("parameter", teststep.param.substring(1));
            } catch(Exception e) {
                System.out.println ("The parameter was not found in the variable file.");
                return Fonctions.logStepKO(teststep, null, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
            }
        } else {
            parameter = teststep.param;
        }
        try {
            String command = "powershell Set-WinUserLanguageList -LanguageList en-US -Force";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            selfdriver.findElement(AppiumBy.accessibilityId(array_prop_object.get("AutomationId"))).sendKeys(teststep.param);


            String restoreCommand = "powershell Set-WinUserLanguageList -LanguageList fr-FR -Force";
            Process restoreProcess = Runtime.getRuntime().exec(restoreCommand);
            restoreProcess.waitFor();

                return Fonctions.logStepOK(teststep, selfdriver, time1);

        } catch (Exception e) {
            e.printStackTrace();
            String restoreCommand = "powershell Set-WinUserLanguageList -LanguageList fr-FR -Force";
            Process restoreProcess = Runtime.getRuntime().exec(restoreCommand);
            restoreProcess.waitFor();
            return Fonctions.logStepKO(teststep, selfdriver, time1, "Cannot perform action in obj : " +e.getMessage());
        }
    }

    public static boolean sendkeysbyxpath(WebDriver selfdriver,Teststep teststep) throws IOException, InterruptedException {
        Date time1 = new Date();
        try {
            Thread.sleep(Config.pause_actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(selfdriver, Duration.ofMillis(Config.timeout_elements));
        WebElement myObj;
        Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(teststep.object_attach_name);
        if (array_prop_object.isEmpty()){
            return Fonctions.logStepKO(teststep, selfdriver, time1, "No such file :"+  teststep.object_attach_name);
        }

        String parameter;
        if (teststep.param.charAt(0) == '$'){
            try {
                Wini ini = new Wini(new File(Config.propertyFile));
                parameter = ini.get("parameter", teststep.param.substring(1));
            } catch(Exception e) {
                System.out.println ("The parameter was not found in the variable file.");
                return Fonctions.logStepKO(teststep, null, time1, "Cannot find the parameter in the variable file : " +e.getMessage());
            }
        } else {
            parameter = teststep.param;
        }
        try {
            String command = "powershell Set-WinUserLanguageList -LanguageList en-US -Force";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            selfdriver.findElement(AppiumBy.xpath(array_prop_object.get("xpath"))).sendKeys(teststep.param);


            String restoreCommand = "powershell Set-WinUserLanguageList -LanguageList fr-FR -Force";
            Process restoreProcess = Runtime.getRuntime().exec(restoreCommand);
            restoreProcess.waitFor();

            return Fonctions.logStepOK(teststep, selfdriver, time1);

        } catch (Exception e) {
            e.printStackTrace();
            String restoreCommand = "powershell Set-WinUserLanguageList -LanguageList fr-FR -Force";
            Process restoreProcess = Runtime.getRuntime().exec(restoreCommand);
            restoreProcess.waitFor();
            return Fonctions.logStepKO(teststep, selfdriver, time1, "Cannot perform action in obj : " +e.getMessage());
        }
    }

    public static String DeskNbGetpropertybyName(WebDriver selfdriver,String name, String prop, int Nb) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
            List<WebElement> list = selfdriver.findElements(By.name(name));
            return list.get(Nb - 1).getDomProperty(prop);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String DeskGetpropertybyName(WebDriver selfdriver,String name, String prop) {
        return DeskNbGetpropertybyName(selfdriver,name, prop, 1);
    }

    public static String DeskGetpropertybyAutomationId(WebDriver selfdriver,String Id, String prop) {
        try {
            return selfdriver.findElement(AppiumBy.accessibilityId(Id)).getDomProperty(prop);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String DeskGetpropertybyxpath(WebDriver selfdriver,String xpath, String prop) {
        try {
            return selfdriver.findElement(AppiumBy.xpath(xpath)).getDomProperty(prop);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



}