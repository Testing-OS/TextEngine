package scripts_textengine;


import browserstack.shaded.com.google.gson.JsonParser;
import browserstack.shaded.com.google.gson.JsonObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.*;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import okhttp3.*;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import runners.BrowserStack.BrowserStackLauncher;
import runners.BrowserStack.BstackRunner;
import runners.TestRunner.commandline.TestRunnerCommandLineRunner;
import runners.TestRunner.testexecutor.TestExecutorService;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.windows.WindowsDriver;
import org.assertj.core.api.SoftAssertions;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import org.openqa.selenium.WebDriver;

import java.io.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import io.appium.java_client.HasSettings;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.openqa.selenium.support.ui.WebDriverWait;
import scripts_techniques.Config;
import scripts_techniques.Selenium.*;

//import static org.junit.Assert.fail;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class TextEngine {

    public static WebDriver selfdriver;
    public static WebDriverWait wait;
    static String nom;
    static String csvfile;
    static String csvfilebuff;
    public static String Support = "";
    public static boolean TestPlan = false;
    public static String cloud = "non";
    public static int largeur = -1;
    public static int hauteur = -1;
    private final static String SaucelabsUser = "";
    private final static String SauceLabsPassword = "";
    private static String CurrentBuild;
    private static int RUN_ID;
    private static final String AZURE_DEVOPS_URL = "https://dev.azure.com/" + Config.ORGANIZATION + "/" + Config.PROJECT;
    private static final String API_VERSION = "7.1-preview.6";
    private static String instance="";
    private static String token="";
    private static String idTestPlan="";

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();
    
    private static final HashMap<Integer, List<scripts_techniques.Teststep>> Step = new HashMap<>();

    public static ArrayList<String[]> getResult() {
        return Result;
    }
    public static ArrayList<TestCaseResult> ResultAzure = new ArrayList<>();

    private static ArrayList<String[]> Result;

    public static void initFlex(String URL, String parcours) throws IOException, InterruptedException {
//        File file = new File(Config.dir_config + "/geckodriver.exe");
//        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath()); // Remplacez par le chemin réel
//
//        // Configurer les options de Firefox
//        FirefoxOptions options = new FirefoxOptions();
//        options.setHeadless(false); // Si vous voulez exécuter le navigateur en mode headless, mettez true
//
//        // Initialiser le driver
//        selfdriver = new FirefoxDriver(options);
//
//        //Create the Selenium implementation
//        selfdriver.get(URL);
//        selfdriver.manage().window().maximize();
//        Support = "Web";
//        Result = new ArrayList<>();
//        nom = nomparcours(parcours);
//        CreateBatFile(getClassMain());
        initWeb(URL, parcours);
		
		
    }
    
    public static void addStep(int testcaseId, scripts_techniques.Teststep t){
        if(Step.containsKey(testcaseId))
            Step.get(testcaseId).add(t);
        else {
            ArrayList<scripts_techniques.Teststep> l = new ArrayList<>();
            l.add(t);
            Step.put(testcaseId,l);
        }    
    }
    public static void printSteps(){
        for (Map.Entry<Integer, List<scripts_techniques.Teststep>> entry : Step.entrySet()) {
            Integer key = entry.getKey();
            List<scripts_techniques.Teststep> value = entry.getValue();

            System.out.println("Parcours: " + key);
            System.out.println("Step:");
            for (scripts_techniques.Teststep step : value) {
                System.out.println("\t" + step.status + " " +step.module_label);
            }
        }
    }

    public static void CreateResult(){
         Result = new ArrayList<>();
    }
    public static void CreateBatFile(String fic){
        //fic est le nom du fichier main a executer
        String Nfic = fic.replace('.', '_');
        System.out.println(Nfic);
        File bat = new File(Config.tunnel_path+File.separator+"scripts"+File.separator+"Batfiles"+File.separator+Nfic+".bat");
        String cmd = "@echo off\n" +
                    "cd "+ Config.tunnel_path+"/scripts\n" +
                    "echo compilation en cours\n" +
                    "call ant -DUSER_DIR=. build\n" +
                    "call ant -DNOM_CLASS_MAIN="+fic+" run";
        try {
            java.io.FileWriter cmdFile = new java.io.FileWriter (bat);
            BufferedWriter out = new BufferedWriter(cmdFile);
            out.write(cmd);
            out.close();
        } catch (IOException e) {
            System.out.println (e.getMessage());
        }

    }
    
    private static void CreateBatFileTestPlanBrowserStack(){
        //fic est le nom du fichier main a executer
        String Nfic = BrowserStackLauncher.TestPlanName.replace('.', '_')+ "_BrowserStack";
        System.out.println(Nfic);
        File bat = new File(Config.tunnel_path+File.separator+"scripts"+File.separator+"Batfiles"+File.separator+Nfic+".bat");
        String cmd = "@echo off\r\n" + 
        		"setlocal enabledelayedexpansion\r\n" + 
        		"\r\n"+
        		"rem ---------------------------------------Champs a completer----------------------------------------------------------------------------------------------------\r\n" + 
        		"set \"chemin="+Config.tunnel_path + "\\scripts\"\r\n" + 
        		"set \"NomInstance="+BrowserStackLauncher.NomInstance+"\"\r\n" + 
        		"set \"Token="+BrowserStackLauncher.Token+"\"\r\n" + 
        		"set \"TestPlanName="+BrowserStackLauncher.TestPlanName+"\"\r\n" + 
        		"set \"URL="+BrowserStackLauncher.URL+"\"\r\n" + 
        		"set \"BrowserStackSDK="+BrowserStackLauncher.BrowserStackSDK+"\"\r\n"+
        		"rem -------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"+ 
        		"set \"search_NomInstance=public static String NomInstance=\"\r\n" + 
        		"set \"search_Token=public static String Token=\"\r\n" + 
        		"set \"search_TestPlanName=public static String TestPlanName=\"\r\n" + 
        		"set \"search_URL=public static String URL=\"\r\n" + 
        		"set \"search_BrowserStackSDK=public static String BrowserStackSDK=\"\r\n" + 
        		"\r\n" + 
        		"set \"newLine_NomInstance=    public static String NomInstance=\"%NomInstance%\";\"\r\n" + 
        		"set \"newLine_Token=    public static String Token=\"%Token%\";\"\r\n" + 
        		"set \"newLine_TestPlanName=    public static String TestPlanName=\"%TestPlanName%\";\"\r\n" + 
        		"set \"newLine_URL=    public static String URL=\"%URL%\";\"\r\n" + 
        		"set \"newLine_BrowserStackSDK=    public static String BrowserStackSDK=\"%BrowserStackSDK%\";\"\r\n" + 
        		"\r\n" + 
        		"set \"file=%chemin%\\runners\\BrowserStack\\BrowserStackLauncher.java\"\r\n" +
        		"set \"tempfile=%chemin%\\runners\\BrowserStack\\tempfile.java\"\r\n" + 
        		"\r\n" + 
        		"(for /f \"delims=\" %%i in ('type \"%file%\"') do (\r\n" + 
        		"    set \"line=%%i\"\r\n" + 
        		"    if \"!NomInstance!\" neq \"\" (\r\n" + 
        		"        if \"!line:%search_NomInstance%=!\" neq \"!line!\" (\r\n" + 
        		"            echo !newLine_NomInstance!\r\n" + 
        		"        ) else if \"!Token!\" neq \"\" (\r\n" + 
        		"            if \"!line:%search_Token%=!\" neq \"!line!\" (\r\n" + 
        		"                echo !newLine_Token!\r\n" + 
        		"            ) else if \"!TestPlanName!\" neq \"\" (\r\n" + 
        		"                if \"!line:%search_TestPlanName%=!\" neq \"!line!\" (\r\n" + 
        		"                    echo !newLine_TestPlanName!\r\n" + 
        		"                ) else if \"!URL!\" neq \"\" (\r\n" + 
        		"                    if \"!line:%search_URL%=!\" neq \"!line!\" (\r\n" + 
        		"                        echo !newLine_URL!\r\n" + 
        		"                    ) else if \"!BrowserStackSDK!\" neq \"\" (\r\n" + 
        		"                        if \"!line:%search_BrowserStackSDK%=!\" neq \"!line!\" (\r\n" + 
        		"                            echo !newLine_BrowserStackSDK!\r\n" + 
        		"                        ) else (\r\n" + 
        		"                            echo !line!\r\n" + 
        		"                        )\r\n" + 
        		"                    ) else (\r\n" + 
        		"                        echo !line!\r\n" + 
        		"                    )\r\n" + 
        		"                ) else (\r\n" + 
        		"                    echo !line!\r\n" + 
        		"                )\r\n" + 
        		"            ) else (\r\n" + 
        		"                echo !line!\r\n" + 
        		"            )\r\n" + 
        		"        ) else (\r\n" + 
        		"            echo !line!\r\n" + 
        		"        )\r\n" + 
        		"    ) else (\r\n" + 
        		"        echo !line!\r\n" + 
        		"    )\r\n" + 
        		")) > \"%tempfile%\"\r\n" + 
        		"\r\n" + 
        		"del \"%file%\"\r\n" + 
        		"ren \"%tempfile%\" \"BrowserStackLauncher.java\"\r\n" + 
        		"\r\n" + 
        		"cd \"%chemin%\"\r\n" + 
        		"echo compilation en cours\r\n" + 
        		"call ant -DUSER_DIR=. build\r\n" + 
        		"call ant -DNOM_CLASS_MAIN=runners.BrowserStack.BrowserStackLauncher run\r\n" + 
        		"call ant -DUSER_DIR=. build\r\n" +
        		"call ant -DNOM_CLASS_MAIN=runners.BrowserStack.PlanTestBrowserStack -DJAVA_AGENT=-javaagent:%BrowserStackSDK% run\r\n";

        try {
            java.io.FileWriter cmdFile = new java.io.FileWriter (bat);
            BufferedWriter out = new BufferedWriter(cmdFile);
            out.write(cmd);
            out.close();
        } catch (IOException e) {
            System.out.println (e.getMessage());
        }

    }
    
    public static String getClassMain() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(TextEngine.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getClassName();
            }
        }
        return null;
    }

    public static String CutFinalNumber(String string){
        Pattern pattern = Pattern.compile("_(\\d+)$");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            string = string.replaceFirst("_(\\d+)$", "");
        }
        return string;
    }

    public static void initMobileApp(String PathApp) throws IOException, InterruptedException {
        initMobileApp(PathApp, "");
    }

    public static void initWeb(String URL) {
        initWeb(URL, "");
    }
    public static void initDesktop(String PathApp) throws IOException, InterruptedException {
        initDesktop(PathApp, "");
    }

    public static void initWebMobile(String URL) throws IOException, InterruptedException {
        initWeb(URL, "");
    }
    
    public static void initBrowserStack(String URL) throws IOException, InterruptedException {
    	initBrowserStack(URL, "");
    }
    
    public static void initWebResolution(String URL, int largeur, int hauteur) throws IOException, InterruptedException {
    	initWebResolution(URL, "", largeur, hauteur);
    }


    public static void initMobileApp(String PathApp, String nomparcours) throws IOException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Config.deviceId);
        File app = new File(PathApp);
        if (!app.exists()) {
            throw new RuntimeException("File not found : " + app.getAbsolutePath());
        }
        final String appium_url = Config.appium_url;
        final String deviceId = Config.deviceId;
        final String deviceOsVersion = Config.deviceOsVersion;
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, deviceOsVersion);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "200");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("chromedriverExecutable", Config.dir_config + "/driverMobile/chromedriver.exe");
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        selfdriver = new AndroidDriver(new URL(appium_url), capabilities);
        Thread.sleep(Config.waitBeforeExec);
        Support = "Mobile";
        Result = new ArrayList<>();
        nom = nomparcours(nomparcours);
        CreateBatFile(getClassMain());


    }
    public static void initMobileAppIOSBrowserStack(String nomparcours){
        XCUITestOptions options = new XCUITestOptions();
        BstackRunner.userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String) BstackRunner.browserStackYamlMap.get("userName");
        BstackRunner.accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) BstackRunner.browserStackYamlMap.get("accessKey");
        options.setCapability("appium:app", "bs");
        options.setCapability("appium:deviceName", "iPhone 14 Pro");
        options.setCapability("appium:platformVersion", "16");
        options.setCapability("sessionName", nomparcours);

        try {
            selfdriver = new IOSDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", BstackRunner.userName , BstackRunner.accessKey)), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        TextEngine.cloud = "BrowserStack";
        TextEngine.Support="Mobile";
        TextEngine.nom = TextEngine.nomparcours(nomparcours);
        TextEngine.CreateBatFile(TextEngine.getClassMain());
        TextEngine.CreateResult();
    }
    public static void initMobileAppAndroidBrowserStack(String nomparcours){
    	BstackRunner bs = new BstackRunner();
        UiAutomator2Options options = new UiAutomator2Options();
        BstackRunner.userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String) BstackRunner.browserStackYamlMap.get("userName");
        BstackRunner.accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) BstackRunner.browserStackYamlMap.get("accessKey");
        options.setCapability("appium:app", "bs://cfe9923202d69c76d1801f3c47da3964f8f77798");
        options.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
        options.setCapability("appium:platformVersion", "12.0");
        options.setCapability("automationVersion", "1.69.0");
        options.setCapability("sessionName", nomparcours);


        try {
            selfdriver = new AndroidDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", BstackRunner.userName , BstackRunner.accessKey)), options);
            AndroidDriver AD = (AndroidDriver) selfdriver;
            //((HasSettings) AD).setSetting("enforceXPath1",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextEngine.cloud = "BrowserStack";
        TextEngine.Support="Mobile";
        TextEngine.nom = TextEngine.nomparcours(nomparcours);
        TextEngine.CreateBatFile(TextEngine.getClassMain());
        TextEngine.CreateResult();
    }

    public static void initWebMobile(String URL, String nomparcours) throws IOException, InterruptedException {
        final String appium_url = Config.appium_url;
        final String deviceOsVersion = Config.deviceOsVersion;
        DesiredCapabilities capabilities = new DesiredCapabilities();


        //browser name comes from kaliostest
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Config.deviceId);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, deviceOsVersion);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Config.window_attach_timeout);
        capabilities.setCapability("chromedriverExecutable", Config.dir_config + "/driverMobile/chromedriver.exe");
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        AndroidDriver driver = new AndroidDriver(new URL(appium_url), capabilities);
        selfdriver = driver;
        Thread.sleep(Config.waitBeforeExec);
        driver.get(URL);
        Support = "Web";
        Result = new ArrayList<>();
        nom = nomparcours(nomparcours);
        CreateBatFile(getClassMain());


    }

    public static String nomparcours(String nom) {
        if (nom.equals("") || nom == null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
            nom = currentDateTime.format(formatter);
            System.out.println(nom);
        }
        if(!TestPlan)
        	CreateXpathListFile(nom);
        return nom;
    }
    
    public static void CreateXpathListFile(String nom) {
    	 csvfile = Config.dir_params + File.separator + nom + "_xpath_list.csv";
         csvfilebuff = Config.dir_params + File.separator + nom + "_xpath_list_buffer.csv";
         try {
             //Creation des fichiers .OUT/xpathlist/xpathlistbuffer
             Config.compteur_instance = 2;
             Fonctions.createLogFile(nom);
             Config.compteur_instance = 1;
             Fonctions.createEmptyFile(csvfile);
             Fonctions.createEmptyFile(csvfilebuff);
             //Couper-Coller du fichier xpathlist_buffer dans le fichier xpathlist
             Fonctions.CutandPaste(csvfilebuff, csvfile);
         }catch (Exception e){
             e.printStackTrace();
         }
    }

    public static void ChangeParcours(String newname) {
        nom = newname;
    }

    public static void initWeb(String URL, String nomparcours){
        //initialisation de tous les paramètres du chromedriver et de selenium
        File file = new File(Config.dir_config + "/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        chromePrefs.put("download.prompt_for_download", false);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-search-engine-choice-screen");
        options.setAcceptInsecureCerts(true);
        selfdriver = new ChromeDriver(options);
        //Create the Selenium implementation
        selfdriver.get(URL);
        selfdriver.manage().window().maximize();
        Support = "Web";
        Result = new ArrayList<>();
        nom = nomparcours(nomparcours);
        CreateBatFile(getClassMain());
    }
    public static void initBrowserStack(String URL, String parcours){
    	BstackRunner BS = new BstackRunner();

        MutableCapabilities capabilities = new MutableCapabilities();
        BstackRunner.userName = System.getenv("BROWSERSTACK_USERNAME") != null ? System.getenv("BROWSERSTACK_USERNAME") : (String)BstackRunner.browserStackYamlMap.get("userName");
        BstackRunner.accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ? System.getenv("BROWSERSTACK_ACCESS_KEY") : (String) BstackRunner.browserStackYamlMap.get("accessKey");
        HashMap<String, Object> bStackOptions = new HashMap<>();
        bStackOptions.put("source", "junit5:sample-master:v1.2");
        capabilities.setCapability("bstack:options", bStackOptions);
        capabilities.setCapability("sessionName", parcours);
        try {
            selfdriver = new RemoteWebDriver(
                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", BstackRunner.userName, BstackRunner.accessKey)), capabilities);
            selfdriver.get(URL);
            selfdriver.manage().window().maximize();

        }catch (Exception e){
            e.printStackTrace();
        }
        TextEngine.cloud = "BrowserStack";
        Result = new ArrayList<>();
        nom = nomparcours(parcours);
        Support = "Web";
    }
    
    public static void initSauceLabs(String nom_appli, String parcours, String platformName, String devicename, String platformVersion, String Build){
   	
 
    	MutableCapabilities caps = new MutableCapabilities();
    		
		caps.setCapability("appium:app", "storage:filename="+nom_appli);  // The filename of the mobile app
		caps.setCapability("appium:deviceName", devicename);
		//caps.setCapability("appium:noReset", "true");
		
		if (platformVersion!=null && platformVersion!="")
			caps.setCapability("appium:platformVersion", platformVersion);
		
		caps.setCapability("appium:appiumVersion", "1.22.0");
		
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("username", SaucelabsUser);
		sauceOptions.setCapability("accessKey",SauceLabsPassword);
		sauceOptions.setCapability("name", parcours);
		sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
		sauceOptions.setCapability("cacheId", "jnc0x1256");
		if(Build!=null)
			sauceOptions.setCapability("build", Build);
		caps.setCapability("sauce:options", sauceOptions);
        
        TextEngine.cloud = "SauceLabs";
        TextEngine.Support="Mobile";
        TextEngine.nom = TextEngine.nomparcours(parcours);
        TextEngine.CreateBatFile(TextEngine.getClassMain());
        TextEngine.CreateResult();
		
		if(platformName.equalsIgnoreCase("ios")) {
    		caps.setCapability("platformName", platformName);
    		caps.setCapability("appium:automationName", "xcuitest");
    		try {
    			URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
    			TextEngine.selfdriver = new IOSDriver(url, caps);

    		} catch (MalformedURLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
		else if(platformName.equalsIgnoreCase("Android")) {
    		caps.setCapability("platformName", platformName);
    		caps.setCapability("appium:automationName", "UiAutomator2");
    		try {
    			URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
    			TextEngine.selfdriver = new AndroidDriver(url, caps);

    		} catch (MalformedURLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
		else
			throw new IllegalArgumentException("La platforme doit etre soit \"ios\" soit \"android\"");
		
    }
    
    public static void initSauceLabs(String nom_appli, String parcours, String platformName, String devicename, String platformVersion){
    	initSauceLabs(nom_appli,parcours, platformName, devicename, platformVersion, null);
    }
    
    public static void initWebResolution(String URL, String nomparcours, int largeur, int hauteur){
        //initialisation de tous les paramètres du chromedriver et de selenium
        File file = new File(Config.dir_config + "/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
        chromePrefs.put("download.prompt_for_download", false);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--window-size=" + largeur + "," + hauteur);

        options.setAcceptInsecureCerts(true);
        selfdriver = new ChromeDriver(options);
        //Create the Selenium implementation
        selfdriver.get(URL);
        //selfdriver.manage().window().setSize(new Dimension(1024, 768));
        Support = "Web";
        Result = new ArrayList<>();
        nom = nomparcours(nomparcours);
        CreateBatFile(getClassMain());
    }
    private static Class<?> compiler() throws IOException {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(new String[]{Config.tunnel_path + "/scripts/scripts_metiers/Scenarios.java", Config.tunnel_path + "/scripts/scripts_metiers/Modules.java"});
        Iterable<String> options = Arrays.asList("-d", Config.tunnel_path + "\\classes\\production\\TextEngine");
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits);
        boolean compilationSuccessful = task.call();

        try {
            fileManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (compilationSuccessful) {
            try {
                File repertoireClasses = new File(Config.tunnel_path + "\\classes\\production\\TextEngine");
                URL[] urls = { repertoireClasses.toURI().toURL() };
                ClassLoader classLoader = new URLClassLoader(urls);
                Class<?> maClasse = classLoader.loadClass("scripts_metiers.Scenarios");
                return maClasse;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String GetTestPlanName(String NomPlanTest){
        String dir = null;
        try{
            BufferedReader r = new BufferedReader(new FileReader(Config.tunnel_path+ "/scripts/scripts_metiers/"+ NomPlanTest + ".java"));
            dir = r.readLine();
            while(!dir.contains("Config.dir")){
                dir = r.readLine();
            }
            r.close();
            String[] sp = dir.split("\"");
            int i = 0;
            while(!sp[i].contains(NomPlanTest)){
                i++;
            }
            dir = sp[i];
            System.out.println(dir);
            return dir;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    private static String[] GetScenariosParameter(String Scenarios){
        String dir = null;
        try{
            BufferedReader r = new BufferedReader(new FileReader(Config.tunnel_path+ "/scripts/scripts_metiers/"+ Scenarios + ".java"));
            dir = r.readLine();
            while(!dir.contains("status = Scenarios.")){
                dir = r.readLine();
            }
            r.close();
            int debut = dir.indexOf('('); // Cherche la première parenthèse ouvrante
            int fin = dir.indexOf(')', debut); // Cherche la première parenthèse fermante après la parenthèse ouvrante

            if (debut != -1 && fin != -1 && debut < fin) {
                // Extrait la sous-chaîne entre les parenthèses
                dir = dir.substring(debut + 1, fin);
            } else {
                // Aucune paire de parenthèses trouvée, retourne une chaîne vide ou un message d'erreur
                dir = "";
            }

            System.out.println(dir);
            return dir.replace(" ","").split(",");
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void RunKaliosTestPlanBrowserStack(){
        //Récupération Fichiers pour TestPlan
    	BstackRunner vs = new BstackRunner();
    	CreateBatFileTestPlanBrowserStack();
    	TestPlan = true;
        instance = BrowserStackLauncher.NomInstance;
        token = BrowserStackLauncher.Token;
        TestRunnerCommandLineRunner TestRunner = new TestRunnerCommandLineRunner(new TestExecutorService());

        //Exécution Plan de Test
        
        
        ExecuteKaliosTestPLanBS(BrowserStackLauncher.URL, BrowserStackLauncher.TestPlanName);
        
        int NbParcours = ((ArrayList<String[]>) BstackRunner.browserStackYamlMap.get("platforms")).size();


        //Envoi des résultats dans Kalios
        
        TestRunner.Upload(BrowserStackLauncher.NomInstance, BrowserStackLauncher.Token, NbParcours);

    }
    public static void RunKaliosTestPlanMobileAndroidBrowserStack(){
        //Récupération Fichiers pour TestPlan
    	BstackRunner vs = new BstackRunner();
    	CreateBatFileTestPlanBrowserStack();
    	TestPlan = true;
        instance = BrowserStackLauncher.NomInstance;
        token = BrowserStackLauncher.Token;
        TestRunnerCommandLineRunner TestRunner = new TestRunnerCommandLineRunner(new TestExecutorService());
        //Exécution Plan de Test
        ExecuteKaliosTestPLanMobileAndroidBS(BrowserStackLauncher.TestPlanName);
        int NbParcours = ((ArrayList<String[]>) BstackRunner.browserStackYamlMap.get("platforms")).size();
        //Envoi des résultats dans Kalios
        TestRunner.Upload(BrowserStackLauncher.NomInstance, BrowserStackLauncher.Token, NbParcours);
    }
    public static void RunKaliosTestPlanMobileIOSBrowserStack(){
        //Récupération Fichiers pour TestPlan
    	BstackRunner vs = new BstackRunner();
    	CreateBatFileTestPlanBrowserStack();
    	TestPlan = true;
        instance = BrowserStackLauncher.NomInstance;
        token = BrowserStackLauncher.Token;
        TestRunnerCommandLineRunner TestRunner = new TestRunnerCommandLineRunner(new TestExecutorService());
        //Exécution Plan de Test
        ExecuteKaliosTestPLanMobileIOSBS(BrowserStackLauncher.TestPlanName);
        int NbParcours = ((ArrayList<String[]>) BstackRunner.browserStackYamlMap.get("platforms")).size();
        //Envoi des résultats dans Kalios
        TestRunner.Upload(BrowserStackLauncher.NomInstance, BrowserStackLauncher.Token, NbParcours);
    }
    
    public static void RunKaliosTestPlan(String Instance, String Token, String testPlanName, String URL){
        //Récupération Fichiers pour TestPlan
    	TestPlan = true;
        instance = Instance;
        token = Token;
        TestRunnerCommandLineRunner TestRunner = new TestRunnerCommandLineRunner(new TestExecutorService());
        //Telechargement des fichiers sources
        TestRunner.run(Instance, Token, testPlanName);
        String[] res = {};
        try {
			res = getResolutions();
			
		} catch (Exception e) {
			System.out.println("Resolutions not get correctly");
			System.out.println(e.getMessage());
		}
        if (res.length != 0) {
			for (int i = 0; i < res.length; i++) {
	        	String[] resol = res[i].split("x");
	        	largeur = Integer.valueOf(resol[0]);
	        	hauteur = Integer.valueOf(resol[1]);
				System.out.println("Largeur " + largeur + "\nHauteur " + hauteur);
				//index=0;
				ExecuteKaliosTestPLan(URL, testPlanName);
				TestRunner.Upload(Instance, Token);
	        }
		} else {
			ExecuteKaliosTestPLan(URL, testPlanName);
			TestRunner.Upload(Instance, Token);
		}


        UploadAzureDevOps(testPlanName);
        
		
              
        

    }

    private static int ExecuteKaliosTestPLan(String URL, String NomPlanTest)  {
        String dir = GetTestPlanName(NomPlanTest.replace(" ", "_").replace(".", "_"));
        Method[] PlandeTest = null;
        try {
            Class<?> maClasse = compiler();
            PlandeTest = maClasse.getDeclaredMethods();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!Config.dir_export.contains("planTests"))
            Config.dir_export = Config.dir_export + dir;

        String str = Config.dir_export;
        String lastDigits = str.substring(str.length() - 10);
        if (lastDigits.matches("\\d+")) {
            str = str.substring(0, str.length() - 10) + System.currentTimeMillis();
        }
        Config.dir_export = str;



        System.out.println(Config.dir_export);
        System.out.println(PlandeTest.length);
        for (Method m : PlandeTest) {
            System.out.println(m.getName());
        }


        int index = 0;
        for(Method parcours : PlandeTest){
            index++;
            System.out.println("parcours " + index + "sur "+ PlandeTest.length);
            String methodName = parcours.getName();
            methodName = CutFinalNumber(methodName);
            try {
                initFlex(URL, methodName);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            boolean result = LaunchTest(parcours);


            selfdriver.quit();

        }

        if (Config.Percy == 1) {
            //CreateXpathListFile("Resultat_Percy");
            getPercyResult(Config.PercyToken, CutFinalNumber(PlandeTest[PlandeTest.length-1].getName()));
        }

        return index;
    }
    
    public static void RunKaliosTestPlanSauceLabs(String Instance, String Token, String testPlanName, String URL,String platformName, String deviceName, String platformVersion){
        //Récupération Fichiers pour TestPlan
    	TestPlan = true;
        instance = Instance;
        token = Token;
        TestRunnerCommandLineRunner TestRunner = new TestRunnerCommandLineRunner(new TestExecutorService());
        //Telechargement des fichiers sources
        TestRunner.run(Instance, Token, testPlanName);
        
      
  
       
		ExecuteKaliosTestPLanSauceLabs(URL, testPlanName, platformName, deviceName, platformVersion);
		
		TestRunner.Upload(Instance, Token);
		
    }
    private static int ExecuteKaliosTestPLanSauceLabs(String URL, String NomPlanTest, String platformName, String deviceName, String platformVersion)  {
        String dir = GetTestPlanName(NomPlanTest.replace(" ", "_").replace(".", "_"));
        Method[] PlandeTest = null;
        try {
            Class<?> maClasse = compiler();
            PlandeTest = maClasse.getDeclaredMethods();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        long timestamp = System.currentTimeMillis();
        if(!Config.dir_export.contains("planTests"))
        	Config.dir_export = Config.dir_export + dir;

        String str = Config.dir_export;
        String lastDigits = str.substring(str.length() - 10);
        if (lastDigits.matches("\\d+")) {
            str = str.substring(0, str.length() - 10) + timestamp;
        }
        Config.dir_export = str.substring(0, str.length() - 3);



        System.out.println(Config.dir_export);
        System.out.println(PlandeTest.length);
        for (Method m : PlandeTest) {
            System.out.println(m.getName());
        }
       
        
        int index = 0;
			for(Method parcours : PlandeTest){
			index++;
        	System.out.println("parcours " + index + "sur "+ PlandeTest.length);
            String methodName = parcours.getName();
            Pattern pattern = Pattern.compile("_(\\d+)$");
            Matcher matcher = pattern.matcher(methodName);
            
            Timestamp tms = new Timestamp(timestamp);
      		// Passing the value in the Date class constructor
      		Date date = new Date(tms.getTime());
      		
      		SimpleDateFormat formatteur = new SimpleDateFormat("HH:mm:ss - dd/MM");

            CurrentBuild = NomPlanTest +" " + formatteur.format(date);

            if (matcher.find()) {
                methodName = methodName.replaceFirst("_(\\d+)$", "");
            }
			try {
				initSauceLabs(URL, methodName, platformName, deviceName, platformVersion, CurrentBuild);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
            JavascriptExecutor jse = (JavascriptExecutor) selfdriver;
  
            
            boolean result = LaunchTest(parcours);
            if(TextEngine.cloud.equals("SauceLabs")){
	            if (result){
	
					try {		
						jse.executeScript("sauce:job-result=passed");
						
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }
	            else {
					try {
						jse.executeScript("sauce:job-result=failed");
		           
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
            }
    
            selfdriver.quit();
        }

        return index;
    }
    private static int ExecuteKaliosTestPLanBS(String URL, String NomPlanTest)  {
        String dir = GetTestPlanName(NomPlanTest.replace(" ", "_"));
        Method[] PlandeTest = null;
        try {
            Class<?> maClasse = compiler();
            PlandeTest = maClasse.getDeclaredMethods();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!Config.dir_export.contains("planTests"))
        	Config.dir_export = Config.dir_export + dir;

            String str = Config.dir_export;
            String lastDigits = str.substring(str.length() - 10);
            if (lastDigits.matches("\\d+")) {
                str = str.substring(0, str.length() - 10) + System.currentTimeMillis();
            }
            Config.dir_export = str;




        System.out.println(Config.dir_export);
        System.out.println(PlandeTest.length);
        for (Method m : PlandeTest) {
            System.out.println(m.getName());
        }
        int index=0;
        for(Method parcours : PlandeTest){
			index++;
        	System.out.println("parcours " + index + "sur "+ PlandeTest.length);
        	
            String methodName = parcours.getName();
            Pattern pattern = Pattern.compile("_(\\d+)$");
            Matcher matcher = pattern.matcher(methodName);

            if (matcher.find()) {
                methodName = methodName.replaceFirst("_(\\d+)$", "");
            }
            try {
				initBrowserStack(URL, methodName);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            //initBrowserStack(URL, methodName);
            JavascriptExecutor jse = (JavascriptExecutor) selfdriver;
            boolean result = LaunchTest(parcours);
            if(TextEngine.cloud.equals("BrowserStack")){
	            if (result){
	
					try {
		                Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
		                JSONObject json;
						json = (JSONObject) new JSONParser().parse((String) response);
						String Reason = (String) json.get("reason");
						System.out.println("Raison : "+ Reason);
						if(Reason==null) {
			                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\"}}");
						}
			            else if(Reason.equals("OK")){
			                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\"}}");
			            }
						
					} catch (ParseException e) {
							e.printStackTrace();
							}
	              }
	            else {
					try {
						Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
			            JSONObject json;
						json = (JSONObject) new JSONParser().parse((String) response);
						String Reason = (String) json.get("reason");
		                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \""+Reason+"\"}}");
		           
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
            }
        }
            UploadAzureDevOps(NomPlanTest);

        return index;
    }
    private static int ExecuteKaliosTestPLanMobileAndroidBS(String NomPlanTest)  {
        String dir = GetTestPlanName(NomPlanTest.replace(" ", "_"));
        Method[] PlandeTest = null;
        try {
            Class<?> maClasse = compiler();
            PlandeTest = maClasse.getDeclaredMethods();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //if(!Config.dir_export.contains("planTests"))
        	Config.dir_export = Config.dir_export + dir;

           // String str = Config.dir_export;
            //String lastDigits = str.substring(str.length() - 10);
            //if (lastDigits.matches("\\d+")) {
              //  str = str.substring(0, str.length() - 10) + System.currentTimeMillis();
            //}
           // Config.dir_export = str;




        System.out.println(Config.dir_export);
        System.out.println(PlandeTest.length);
        for (Method m : PlandeTest) {
            System.out.println(m.getName());
        }
        int index=0;
        for(Method parcours : PlandeTest){
			index++;
        	System.out.println("parcours " + index + "sur "+ PlandeTest.length);
            String methodName = parcours.getName();
            Pattern pattern = Pattern.compile("_(\\d+)$");
            Matcher matcher = pattern.matcher(methodName);

            if (matcher.find()) {
                methodName = methodName.replaceFirst("_(\\d+)$", "");
            }

            initMobileAppAndroidBrowserStack(methodName);
            JavascriptExecutor jse = (JavascriptExecutor) selfdriver;
            boolean result = LaunchTest(parcours);
            
	            if (result){

					try {
		                Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
		                JSONObject json;
						json = (JSONObject) new JSONParser().parse((String) response);
						String Reason = (String) json.get("reason");
						System.out.println("Raison : "+ Reason);
						if(Reason==null) {
			                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\"}}");
						}
			            else if(Reason.equals("OK")){
			                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\"}}");
			            }

					} catch (ParseException e) {
							e.printStackTrace();
							}
	              }
	            else {
					try {
						Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
			            JSONObject json;
						json = (JSONObject) new JSONParser().parse((String) response);
						String Reason = (String) json.get("reason");
		                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \""+Reason+"\"}}");

					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
            
        }
        return index;
    }
    private static int ExecuteKaliosTestPLanMobileIOSBS(String NomPlanTest)  {
        String dir = GetTestPlanName(NomPlanTest.replace(" ", "_"));
        Method[] PlandeTest = null;
        try {
            Class<?> maClasse = compiler();
            PlandeTest = maClasse.getDeclaredMethods();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //if(!Config.dir_export.contains("planTests"))
        	Config.dir_export = Config.dir_export + dir;

           // String str = Config.dir_export;
            //String lastDigits = str.substring(str.length() - 10);
            //if (lastDigits.matches("\\d+")) {
              //  str = str.substring(0, str.length() - 10) + System.currentTimeMillis();
            //}
           // Config.dir_export = str;




        System.out.println(Config.dir_export);
        System.out.println(PlandeTest.length);
        for (Method m : PlandeTest) {
            System.out.println(m.getName());
        }
        int index=0;
        for(Method parcours : PlandeTest){
			index++;
        	System.out.println("parcours " + index + "sur "+ PlandeTest.length);
            String methodName = parcours.getName();
            Pattern pattern = Pattern.compile("_(\\d+)$");
            Matcher matcher = pattern.matcher(methodName);

            if (matcher.find()) {
                methodName = methodName.replaceFirst("_(\\d+)$", "");
            }

            initMobileAppIOSBrowserStack(methodName);
            JavascriptExecutor jse = (JavascriptExecutor) selfdriver;
            boolean result = LaunchTest(parcours);
            
	            if (result){

					try {
		                Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
		                JSONObject json;
						json = (JSONObject) new JSONParser().parse((String) response);
						String Reason = (String) json.get("reason");
						System.out.println("Raison : "+ Reason);
						if(Reason==null) {
			                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\"}}");
						}
			            else if(Reason.equals("OK")){
			                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\"}}");
			            }

					} catch (ParseException e) {
							e.printStackTrace();
							}
	              }
	            else {
					try {
						Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
			            JSONObject json;
						json = (JSONObject) new JSONParser().parse((String) response);
						String Reason = (String) json.get("reason");
		                jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \""+Reason+"\"}}");

					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
            
        }
        return index;
    }

    private static boolean LaunchTest(Method parcours){
        String methodName = parcours.getName();
        Pattern pattern = Pattern.compile("_(\\d+)$");
        Matcher matcher = pattern.matcher(methodName);
        String[] ScenarioParam = null;
        String num = "";
        try{
            String[] FindNum = methodName.split("_");
            num = FindNum[FindNum.length-1];
            System.out.println("Numero test : "+ num);
        }catch (Exception e){

        }

        if (matcher.find()) {
            methodName = methodName.replaceFirst("_(\\d+)$", "");
            String FicMethode = methodName;
            if (Character.isLetter(methodName.charAt(0))) {
                FicMethode = Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
            }
            ScenarioParam = GetScenariosParameter(FicMethode);
        }
        System.out.println(ScenarioParam);
        Object[] parametres = {selfdriver, Integer.parseInt(ScenarioParam[1]), methodName, Integer.parseInt(ScenarioParam[3]), methodName, Integer.parseInt(ScenarioParam[5])};

        Fonctions.cleanTestcaseLog (methodName);
        boolean result = false;
        String Infodevice ="";
        try {
            System.out.println ("Demarrage du parcours");
            int num_instances = Fonctions.get_lines_parameters(methodName);
            Config.compteur_instance = 2;
            while (Config.compteur_instance<num_instances) {
                Config.compteur_params=1;
                Fonctions.createLogFile(methodName);
				JSONObject json;
                Date time1 = new Date();
                if(TextEngine.cloud.equals("BrowserStack")) {
	                try {
	                    JavascriptExecutor jse = (JavascriptExecutor) selfdriver;
	    				Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
	    				json = (JSONObject) new JSONParser().parse((String) response);
	    				System.out.println(json);
	    				String BuildName = (String) json.get("build_name");
	                    Teststep t = new Teststep("NomBuild","NomBuild" ,"NomBuild.htm",methodName, BuildName);
	                    Fonctions.logStepOK(t, selfdriver, time1);
	                    HasCapabilities Hc = (HasCapabilities) selfdriver;
	                    if(Support.equals("Mobile"))
	                    	Infodevice = Hc.getCapabilities().getPlatformName()+" "+((String) json.get("os_version"))+" - "+((String) json.get("device"));
	                    else if(Support.equals("Web")) {
	                    	Infodevice = Hc.getCapabilities().getPlatformName()+" "+((String) json.get("os_version"))+"-"+((String) json.get("device"))+" - "+ ((String) json.get("browser"))+"/"+ ((String) json.get("browser_version"));
	                    }
	                    else
	                    	Infodevice = Hc.getCapabilities().getPlatformName().toString();
	                    Teststep t2 = new Teststep("nomDevice","nomDevice" ,"nomDevice.htm",methodName, Infodevice.replace("null", ""));
	                    Fonctions.logStepOK(t2, selfdriver, time1);
	                    
	    			} catch (ParseException Pe) {
	    				System.out.println("erreur lors de la recuperation des noms des devices");
	    				Pe.printStackTrace();
	    			}
                }
                else if(TextEngine.cloud.equals("SauceLabs")) {
                	try {

    				String BuildName = "Build" + CurrentBuild;
                    Teststep t = new Teststep("NomBuild","NomBuild" ,"NomBuild.htm",methodName, BuildName);
                    Fonctions.logStepOK(t, selfdriver, time1);
                    HasCapabilities Hc = (HasCapabilities) selfdriver;
                    if(Support.equals("Mobile"))
                    	Infodevice = Hc.getCapabilities().getPlatformName()+" "+(Hc.getCapabilities().getCapability("appium:testobject_device_name"))+" - "+(Hc.getCapabilities().getCapability("platformVersion"));
                    else
                    	Infodevice = Hc.getCapabilities().getPlatformName().toString();
                    Teststep t2 = new Teststep("nomDevice","nomDevice" ,"nomDevice.htm",methodName, Infodevice.replace("null", ""));
                    Fonctions.logStepOK(t2, selfdriver, time1);
                    
    			} catch (Exception Pe) {
    				System.out.println("erreur lors de la recuperation des noms des devices");
    				Pe.printStackTrace();
    			}
                }
                else {
                RemoteWebDriver r = (RemoteWebDriver) selfdriver;
                if(Support.equals("Web"))
                	Infodevice = r.getCapabilities().getPlatformName()+"-"+r.getCapabilities().getCapability("deviceName")+"-"+r.getCapabilities().getBrowserName()+"/"+ r.getCapabilities().getBrowserVersion();
                if(Support.contentEquals("Mobile"))
                	Infodevice = r.getCapabilities().getPlatformName()+"-"+r.getCapabilities().getCapability("deviceName");
                else
                	Infodevice = r.getCapabilities().getPlatformName().toString();
                
                Teststep t = new Teststep("nomDevice","nomDevice" ,"nomDevice.htm",methodName, Infodevice);
                Fonctions.logStepOK(t, selfdriver, time1);
                }
                
                if(largeur != -1 && hauteur != -1) {
                	Teststep t2 = new Teststep("Resolution","Resolution" ,"Resolution.htm",methodName, largeur + "x" + hauteur);
                	Fonctions.logStepOK(t2, selfdriver, time1);
                }
                for (Object param : parametres) {
                	System.out.println("paramètre : " + param.toString());
                }
                Object resultat = parcours.invoke(null, parametres);
                result = (Boolean) resultat;
                if (Config.AzureDevOps == 1) {



                    String status = "OK";
                    String message = "";

                    if(Step.containsKey(Integer.parseInt(num))) {
                        String link ="";
                        if (TestPlan) {
                            link= getLinkResult(instance, token, Config.dir_export+"/"+methodName+"/");
                        }

                        for (scripts_techniques.Teststep t : Step.get(Integer.parseInt(num))) {
                            if(Objects.equals(t.status, "KO")) {
                                status = "KO";
                                message = t.errorMessage;
                                ResultAzure.add(new TestCaseResult(methodName, num, "Failed", Infodevice, message,"[Lien vers résultat]("+link+")"));
                                break;
                            }
                            else if(Objects.equals(t.status, "Warning")) {
                                status = "Warning";
                                message = t.errorMessage;
                                ResultAzure.add(new TestCaseResult(methodName, num, "Warning", Infodevice, message,"[Lien vers résultat]("+link+")"));
                                break;
                            }
                        }
                        if(status.equals("OK"))
                            ResultAzure.add(new TestCaseResult(methodName, num, "Passed", Infodevice, "","[Lien vers résultat]("+link+")"));
                        System.out.println("Result link: "+link);
                    }
                }
                if (Config.Jira == 1) {
                	if(Step.containsKey(Integer.parseInt(num))) {
                        String link ="";
                        String status = "Test ok";
                        if (TestPlan) {
                            link= getLinkResult(instance, token, Config.dir_export+"/"+methodName+"/");
                        }
                        scripts_techniques.Teststep teststep = null;
                        for (scripts_techniques.Teststep t : Step.get(Integer.parseInt(num))) {
                        	teststep = t;
                            if(Objects.equals(t.status, "KO")) {
                            	status = "Test ko";
                            	String ticket = getJiraIssue(t.testcase_label);
                            	if (ticket == null) {
									createJiraTicket(link, status, t);
								} else {
									updateJiraIssue(link, status, t, ticket);
								}
                                break;
                            }
                            else if(Objects.equals(t.status, "Warning")) {
                               status = "Test warning";
                               String ticket = getJiraIssue(t.testcase_label);
                               if (ticket == null) {
									createJiraTicket(link, status, t);
								} else {
									updateJiraIssue(link, status, t, ticket);
								}
                                break;
                            }
                        }
                        if(status.equals("Test ok")) {
                        	String ticket = getJiraIssue(teststep.testcase_label);
                        	if (ticket == null) {
								createJiraTicket(link, status, teststep);
							} else {
								updateJiraIssue(link, status, teststep, ticket);
							}
                        }
                        System.out.println("Result link: "+link);
                    }
				}
                Config.compteur_instance = Config.compteur_instance+1;
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

        private static String SearchObject(String NatureAction, String prop) throws FileNotFoundException {
        String techlabel = "";
        //Recuperation de tous les techlabels existants
        File jsonFile = new File(Config.file_objects);
        List<String> ListTechLabels = Fonctions.extractTechLabels(jsonFile);
        boolean exist = false;
        // On regarde si la propriété donné correspond à un objet existant
        for (String techLab : ListTechLabels) {
            //On regarde si la prop donné n'est pas directement un techlabel qui existe
            if (Objects.equals(techLab, prop)) {
                techlabel = prop;
                exist = true;
                break;
            }
            //Recuperation de toutes les propriété de chaque objet
            Hashtable<String, String> array_prop_object = Fonctions.getObjectProperties(techLab);
            //On regarde si la prop donné correspond a une valeur déja existante dans un des objets
            if (Objects.equals(array_prop_object.get(NatureAction), prop)) {
                techlabel = techLab;
                System.out.println("objet trouvé");
                exist = true;
                break;
            }
        }
        //Si la prop n'est pas trouvé dans les objets deja existant
        if (!exist) {
            //Si la propriete non trouvé est un techlabel
            if (prop.length() >= 4) {
                if (prop.substring(prop.length() - 4).equals(".htm")) {
                    System.out.println("L'objet déclaré n'existe pas");
                    return "";
                }
            }
            //Sinon on crée un objet
            boolean sucess = Fonctions.AddNewObject(NatureAction, prop);
            if (sucess) {
                System.out.println("Objet " + prop + "crée");
                //Le format du techlabel de l'objet crée sera dans le format indiqué ci dessous
                techlabel = NatureAction + "_" + prop + ".htm";
                return techlabel;
            } else return "";
        }
        return techlabel;
    }


        public static String[] getResolutions() throws InvalidFileFormatException, IOException {
        	String[] resolutions = {};
        	Wini ini = new Wini(new File(Config.propertyFile));
			String res = ini.get("resolution", "res");
			
			resolutions = res.split(",");
			for (int i = 0; i < resolutions.length; i++) {
				System.out.println(resolutions[i]);
			}
        	return resolutions;
        }


    static SoftAssertions sa = new SoftAssertions();
    public static SoftAssertions getSoftAssertions() {
    	return sa;
    }
    
    public static void createJiraTicket(String link, String status, scripts_techniques.Teststep t) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String jsonString = createTicketJSON(link, status, t);

        // Encodage des informations d'identification en Base64
        String auth = Config.JIRA_USERNAME + ":" + Config.JIRA_API_TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Créer la requête HTTP
        Request request = new Request.Builder()
                .url(Config.JIRA_URL+"issue")
                .header("Authorization", authHeader)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .post(RequestBody.create(MediaType.parse("application/json"), jsonString))
                .build();
        //System.out.println(request.toString());
        // Exécuter la requête
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Ticket créé avec succès: " + response.body().string());
            } else {
                System.out.println("Erreur lors de la création du ticket. Code: " + response.code());
                System.out.println(response.body().string());
            }
        }
    }
    
    public static String createTicketJSON(String link, String status, scripts_techniques.Teststep t) {// Création de l'objet JSON pour le projet    	
    	com.google.gson.JsonObject project = new com.google.gson.JsonObject();
        project.addProperty("key", Config.JIRA_PROJECT_NAME);

        // Création de l'objet JSON pour le type d'issue
        com.google.gson.JsonObject issueType = new com.google.gson.JsonObject();
        issueType.addProperty("name", status);

        // Création de l'objet JSON pour les champs
        com.google.gson.JsonObject fields = new com.google.gson.JsonObject();
        fields.add("project", project);
        fields.addProperty("summary", t.testcase_label);
        fields.addProperty("description", t.errorMessage + "\nLien vers les résultats: " + link );
        fields.add("issuetype", issueType);

        // Création de l'objet JSON principal
        com.google.gson.JsonObject jiraIssue = new com.google.gson.JsonObject();
        jiraIssue.add("fields", fields);

        // Convertir l'objet JSON en chaîne
        Gson gson = new Gson();
        String json = gson.toJson(jiraIssue);

        return json;
    }
    
    public static String getJiraIssue(String summary) throws IOException {
    	OkHttpClient client = new OkHttpClient();
    	String ticket = null;

        // Encodage des informations d'identification en Base64
        String auth = Config.JIRA_USERNAME + ":" + Config.JIRA_API_TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Créer la requête HTTP
        Request request = new Request.Builder()
                .url(Config.JIRA_URL + "search?jql=summary ~ \"" + summary + "\"")
                .header("Authorization", authHeader)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        
        // Exécuter la requête
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
            	String jsonResponse = response.body().string();
            	ticket = parseAndPrintIssues(jsonResponse);
                System.out.println("Ticket récupéré avec succès: " + jsonResponse);
            } else {
                System.out.println("Erreur lors de la récupération du ticket. Code: " + response.code());
                System.out.println(response.body().string());
            }
        }
        return ticket;
    }
    
    public static void updateJiraIssue(String link, String status, scripts_techniques.Teststep t, String ticket) throws IOException {
    	OkHttpClient client = new OkHttpClient();

        String jsonString = createTicketJSON(link, status, t);

        // Encodage des informations d'identification en Base64
        String auth = Config.JIRA_USERNAME + ":" + Config.JIRA_API_TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Créer la requête HTTP
        Request request = new Request.Builder()
                .url(Config.JIRA_URL + "issue/" + ticket)
                .header("Authorization", authHeader)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .put(RequestBody.create(MediaType.parse("application/json"), jsonString))
                .build();
        //System.out.println(request.toString());
        // Exécuter la requête
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
            	String jsonResponse = response.body().string();
                System.out.println("Ticket modifié avec succès: " + jsonResponse);
            } else {
                System.out.println("Erreur lors de la modification du ticket. Code: " + response.code());
                System.out.println(response.body().string());
            }
        }
    }
    
    private static String parseAndPrintIssues(String jsonResponse) {
        Gson gson = new Gson();
        com.google.gson.JsonObject jsonObject = gson.fromJson(jsonResponse, com.google.gson.JsonObject.class);
        JsonArray issues = jsonObject.getAsJsonArray("issues");

        for (int i = 0; i < issues.size(); i++) {
        	com.google.gson.JsonObject issue = issues.get(i).getAsJsonObject();
            String id = issue.get("id").getAsString();
            String key = issue.get("key").getAsString();
            String issueSummary = issue.getAsJsonObject("fields").get("summary").getAsString();
            System.out.println("ID: " + id + ", Key: " + key + ", Summary: " + issueSummary);
            return key;
        }
		return null;
    }

    public static String[] Status(Teststep t) {

        String[] result = new String[3];
        result[0] = t.action_label;
        result[1] = t.status;
        result[2] = t.errorMessage;
        Result.add(result);
        if (t.status == "OK")
            return result;
        else if (t.status == "Warning")
            return result;
        else {
            System.out.println(t.errorMessage);
            sa.fail(t.errorMessage);
            return result;

        }
    }
    public static void ShowResult(){
        StringBuilder sb = new StringBuilder("Results : \n");
        for (String[] s:
             Result) {
            sb.append(s[0]).append("-> ").append(s[1]).append(": ").append(s[2]).append("\n");

        }

        System.out.println(sb);
    }


    // ############################################
    // ############ Actions properties ############
    // ############################################


    public static String[] checkbyid(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "checkbyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_checkbyid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkbyname(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "checkbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.Webobject_checkbyname(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.mobileobject_checkbyname(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);

    }

    public static String[] checkbyxpath(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "checkbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.Webobject_checkbyxpath(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.mobileobject_checkbyxpath(selfdriver, t);
        else
            System.out.println("Erreur Init Action");

        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickbyid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "clickbyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_clickbyid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickbyname(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "clickbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.Webobject_clickbyname(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.mobileobject_clickbyname(selfdriver, t);
        else if (Support == "Desktop")
            scripts_techniques.Desktop.Scripts_techniques.clickbyname(selfdriver, t);
        else
            System.out.println("Erreur Init Action");

        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickbytitle(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "title";
        final String NomAction = "clickbytitle";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_clickbytitle(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickbyxpath(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "clickbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.Webobject_clickbyxpath(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.mobileobject_clickbyxpath(selfdriver, t);
        else if (Support == "Desktop")
            status = scripts_techniques.Desktop.Scripts_techniques.clickbyxpath(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] Highlightbyname(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "Highlightbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_Highlightbyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] Highlightbyclass(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "classname";
        final String NomAction = "Highlightbyclass";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_Highlightbyclass(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] Highlightbyid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "Highlightbyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_Highlightbyid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] Highlightbyxpath(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "Highlightbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_Highlightbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectindexbyid(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "selectindexbyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectindexbyid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectindexbyname(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "selectindexbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectindexbyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectindexbyxpath(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "selectindexbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectindexbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectvaluebyid(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "selectvaluebyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectvaluebyid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectvaluebyname(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "selectvaluebyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectvaluebyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectvaluebyxpath(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "selectvaluebyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectvaluebyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectvisibletextbyid(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "selectvisibletextbyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectvisibletextbyid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectvisibletextbyname(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "selectvisibletextbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectvisibletextbyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectvisibletextbyxpath(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "selectvisibletextbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectvisibletextbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] sendkeysbyid(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "sendkeysbyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.Webobject_sendkeysbyname(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.mobileobject_keysbyid(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] sendkeysbyname(String prop, String param) throws IOException, InterruptedException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "sendkeybyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.Webobject_sendkeysbyname(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.mobileobject_keysbyname(selfdriver, t);
        else if (Support == "Desktop")
            status = scripts_techniques.Desktop.Scripts_techniques.sendkeysbyName(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] sendkeysbyxpath(String prop, String param) throws IOException, InterruptedException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "sendkeybyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.Webobject_sendkeysbyxpath(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.mobileobject_keysbyxpath(selfdriver, t);
        else if (Support == "Desktop")
            status = scripts_techniques.Desktop.Scripts_techniques.sendkeysbyxpath(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectkeysbyid(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "selectkeysbyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectkeysbyid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectkeysbyname(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "selectkeysbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectkeysbyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectkeysbyxpath(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "selectkeysbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webobject_selectkeysbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] dragbyxpath(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "dragbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_dragbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checknbelementbyxpath(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "checknbelementbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_checknbelementbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }
    // #######################################
    // ############# ActionsPage #############
    // #######################################

    public static String[] waitloadingcomplete(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "title";
        final String NomAction = "waitloadingcomplete";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webpage_waitloadingcomplete(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] close(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "title";
        final String NomAction = "close";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
        try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webpage_close(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkinnertext(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "title";
        final String NomAction = "checkinnertext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.WebPage_checkinnertext(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.MobileContainer_checkinnertext(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] executer_bat(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "exe";
        final String NomAction = "executer_bat";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_executer_bat(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkpdf(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "checkpdf";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_checkpdf(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkcsv(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "checkcsv";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_checkcsv(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] wait(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "wait";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.WebPage_wait(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.mobileContainer_wait(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }
    public static String[] scroll(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "Page";
        final String NomAction = "scroll";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        
        if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.MobileContainer_scroll(selfdriver, t);
        else {
        	String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Action not available on this device";
            sa.fail("Action not available on this device");
            return result;
        }
        	
        
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] activateframe(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "activateframe";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_activateframe(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] desactivateframe(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "desactivateframe";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_desactivateframe(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] activatetab(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "activatetab";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_activatetab(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }
    
    

    public static String[] requeteAPI(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "requeteAPI";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_requeteAPI(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkjson(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "checkjson";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_checkjson(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkxls(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "checkxls";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_checkxls(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] getemail(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "getemail";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_getemail(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickbycoordinates(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "clickbycoordinates";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}

        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
        	status = Scripts_techniques.WebPage_clickbycoordinates(selfdriver, t);
        else if (Support == "Mobile")
        	status = scripts_techniques.Appium.Scripts_techniques.MobileContainer_clickbycoordinates(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkdoc(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "checkdoc";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_checkdoc(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkinnertextnotpresent(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "checkinnertextnotpresent";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_checkinnertextnotpresent(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] open(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "open";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.Webpage_open(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] confirmAlert(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "confirmAlert";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_confirmAlert(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] FonctionsAnnexes(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "page";
        final String NomAction = "FonctionsAnnexes";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_FonctionsAnnexes(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    // #######################################
    // ############ ActionsByText ############
    // #######################################

    public static String[] clickbytext(String prop) throws IOException {

        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "clickbytext";
        String parcours_name = nom;
        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }

        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        //Appel du clickbytext original
        if (Support == "Web")
            status = scripts_techniques.Selenium.Scripts_techniques.WebObject_clickbytext(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.MobileObject_clickbytext(selfdriver, t);
        else if (Support == "Desktop")
            status = scripts_techniques.Desktop.Scripts_techniques.WebObject_clickbytext(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] sendkeysbytext(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "sendkeysbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.WebObject_sendkeysbytext(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.MobileObject_sendkeysbytext(selfdriver, t);
        else if (Support == "Desktop")
            status = scripts_techniques.Desktop.Scripts_techniques.WebObject_sendkeysbytext(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkbytext(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "checkbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.WebObject_checkbytext(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.MobileObject_checkbytext(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] hoverbytext(String prop) throws FileNotFoundException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "hoverbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_hoverbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickrightbytext(String prop) throws FileNotFoundException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "clickrightbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_clickrightbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickbelowbytext(String prop) throws FileNotFoundException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "clickbelowbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_clickbelowbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickleftbytext(String prop) throws FileNotFoundException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "clickleftbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_clickleftbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickabovebytext(String prop) throws FileNotFoundException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "clickabovebytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_clickabovebytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] selectbytext(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "selectbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.WebObject_selectbytext(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.MobileObject_selectbytext(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickbyindex(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "clickbyindex";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        if (Support == "Web")
            status = Scripts_techniques.WebObject_clickbyindex(selfdriver, t);
        else if (Support == "Mobile")
            status = scripts_techniques.Appium.Scripts_techniques.MobileObject_clickbyindex(selfdriver, t);
        else if (Support == "Desktop")
            status = scripts_techniques.Desktop.Scripts_techniques.WebObject_clickbyindex(selfdriver, t);
        else
            System.out.println("Erreur Init Action");
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] dragbytext(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "dragbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_dragbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] typetext(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "typetext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_typetext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checkcheckboxstatus(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "checkcheckboxstatus";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_checkcheckboxstatus(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    // ######################################
    // ############ ActionsTable ############
    // ######################################

    public static String[] clicktable(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "table";
        final String NomAction = "clicktable";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_clicktable(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] checktable(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "table";
        final String NomAction = "checktable";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_checktable(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] sendkeystable(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "table";
        final String NomAction = "sendkeystable";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebObject_sendkeystable(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] visualtesting(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "visualtesting";
        final String NomAction = "visualtesting";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = Scripts_techniques.WebPage_visualtesting(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    // ######################################
    // ########### ActionsMobile ############
    // ######################################

    public static String[] checkbyresourceid(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "resourceid";
        final String NomAction = "checkbyresourceid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_checkbyresourceid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clickbyresourceid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "resourceid";
        final String NomAction = "clickbyresourceid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_clickbyresourceid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] sendkeysbyresourceid(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "resourceid";
        final String NomAction = "sendkeysbyresourceid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_keysbyresourceid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] RestartApp(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "app";
        final String NomAction = "RestartApp";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobilecontainer_RestartApp(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clearvaluebyname(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "clearvaluebyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_clearvaluebyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clearvaluebyresourceid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "resourceid";
        final String NomAction = "clearvaluebyresourceid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_clearvaluebyresourceid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clearvaluebyxpath(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "clearvaluebyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_clearvaluebyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clearvaluebyid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "id";
        final String NomAction = "clearvaluebyid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_clearvaluebyid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] doubleClickbyname(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "doubleClickbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_doubleClickbyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] doubleClickbyresourceid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "resourceid";
        final String NomAction = "doubleClickbyresourceid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_DoubleClickbyresourceid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] doubleclickbyxpath(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "doubleclickbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_doubleclickbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] longpressbyname(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "longpressbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_longpressbyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] longpressbyresourceid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "resourceid";
        final String NomAction = "longpressbyresourceid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_longpressbyresourceid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] longpressbyxpath(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "longpressbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_longpressbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] swipeLeftbyname(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "swipeLeftbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_swipeLeftbyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] swipeLeftbyresourceid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "resourceid";
        final String NomAction = "swipeLeftbyresourceid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_swipeLeftbyresourceid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] swipeLeftbyxpath(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "swipeLeftbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_swipeLeftbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] swipeRightbyname(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "name";
        final String NomAction = "swipeRightbyname";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_swipeRightbyname(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] swipeRightbyresourceid(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "resourceid";
        final String NomAction = "swipeRightbyresourceid";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_swipeRightbyresourceid(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] swipeRightbyxpath(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "swipeRightbyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.mobileobject_swipeRightbyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] clearvaluebytext(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "clearvaluebytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.MobileObject_clearvaluebytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] doubleclickbytext(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "doubleclickbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.MobileObject_doubleclickbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] longpressbytext(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "longpressbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.MobileObject_longpressbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] scrollbytext(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "scrollbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.MobileObject_scrollbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] swipeleftbytext(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "swipeleftbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.MobileObject_swipeleftbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static String[] swiperightbytext(String prop) throws IOException {
        boolean status = true;
        final String NatureAction = "texte";
        final String NomAction = "swiperightbytext";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        status = scripts_techniques.Appium.Scripts_techniques.MobileObject_swiperightbytext(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    // ############################################
    // ############# Actions desktop ##############
    // ############################################

    public static void initDesktop(String Link, String nomparcours) throws IOException, InterruptedException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("app", Link);
        cap.setCapability("platformName", "Windows");
        cap.setCapability("deviceName", "WindowsPC");
        cap.setCapability("newCommandTimeout", 200);


        Support = "Desktop";
        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), cap);
        selfdriver = driver;
        selfdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(selfdriver, Duration.ofSeconds(10));
        Result = new ArrayList<>();
        nom = nomparcours(nomparcours);
        CreateBatFile(getClassMain());

    }


    public static String[] clickbyAutomationID(String id) throws FileNotFoundException {
        boolean status = true;
        final String NatureAction = "AutomationID";
        final String NomAction = "clickbyAutomationID";
        String parcours_name = nom;
        String techlabel = SearchObject(NatureAction, id);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }

        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, null);
        Config.compteur_instance = 2;
        //Appel du clickbytext original
        status = scripts_techniques.Desktop.Scripts_techniques.clickbyAutomationID(selfdriver, t);

        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }


    public static String[] sendkeysbyAutomationId(String id, String param) throws IOException, InterruptedException {
        boolean status = true;
        final String NatureAction = "AutomationId";
        final String NomAction = "sendkeysbyAutomationId";
        String parcours_name = nom;
        String techlabel = SearchObject(NatureAction, id);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }

        //Initialiser Teststep
        String label;
try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        //Appel du clickbytext original
        status = scripts_techniques.Desktop.Scripts_techniques.sendkeysbyAutomationId(selfdriver, t);

        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }
    
    public static String[] movejaugebyxpath(String prop, String param) throws IOException {
        boolean status = true;
        final String NatureAction = "xpath";
        final String NomAction = "movejaugebyxpath";
        String parcours_name = nom;

        String techlabel = SearchObject(NatureAction, prop);
        if (Objects.equals(techlabel, "")) {
            String[] result = new String[3];
            result[0] = NomAction;
            result[1] = "KO";
            result[2] = "Don't find Object";
            sa.fail("Don't find Object");
            return result;
        }
        //Initialiser Teststep
        String label;
        try {
			label = techlabel.split("_")[1].split("\\.")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			label = techlabel.split("\\.")[0];
		}
        Teststep t = new Teststep(NomAction, label ,techlabel, parcours_name, param);
        Config.compteur_instance = 2;
        //status = scripts_techniques.Appium.Scripts_techniques.mobileobject_movejaugebyxpath(selfdriver, t);
        Config.compteur_instance = 1;
        //Si le clique a reussi
        if (status)
            Fonctions.SaveXpath(nom, techlabel, csvfilebuff);

        Config.compteur_params++;
        return Status(t);
    }

    public static void UploadAzureDevOps(String testplanName){
        if (Config.AzureDevOps==1) {
            try {
                createTestRun(testplanName);
                uploadTestResults(ResultAzure);
                updateTestRunState("Completed");
            }
            catch (Exception e){
                System.out.println("Erreur lors de la remonté des resultats dans Azure DevOps");
            }
        }
    }
    public static String getLinkResult(String instance, String token, String dirTestPlan){
        OkHttpClient client = new OkHttpClient();
        String type;
        if(Config.Type == Config.TYPE_Instance.PROD)
            type= "prod";
        else if(Config.Type == Config.TYPE_Instance.PREPROD)
            type= "test";
        else
            return "";

        String keyword = "planTests";
        int index = dirTestPlan.indexOf(keyword);

        if (index != -1)
            dirTestPlan = dirTestPlan.substring(index);
        String testplan = dirTestPlan.split("/")[1];

        System.out.println(dirTestPlan);
        String separator = "___";

        String[] parts = testplan.split(separator);

        String IdtestPlan = parts[parts.length-2];


        String url = "http://"+instance+"."+type+".kalios-saas.com/web-instance/web/api/public/sharable-testplan-results-create" +
                "?token="+token +
                "&testplan="+IdtestPlan +
                "&dir_testplan="+dirTestPlan +
                "&dataset=0";
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String tokenlink = response.body().string();
            JsonObject jo = JsonParser.parseString(tokenlink).getAsJsonObject();

            String linkresult = jo.get("token").getAsString();
            tokenlink = "http://"+instance+"."+type+".kalios-saas.com/web-instance/web/public/synopsis-modelisation/testplan-results-demo/"+linkresult;
            return tokenlink;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void createTestRun(String name) throws IOException{

        String createRunUrl = String.format("https://dev.azure.com/%s/%s/_apis/test/runs?api-version=7.1-preview.3", Config.ORGANIZATION, Config.PROJECT);

        JsonObject createRunPayload = new JsonObject();
        createRunPayload.addProperty("name", name);
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String formattedNow = now.format(formatter);
        createRunPayload.addProperty("startedDate", formattedNow);
        createRunPayload.addProperty("automated", true);


        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), String.valueOf(createRunPayload));

        String credentials = Credentials.basic("", Config.PAT);

        Request request = new Request.Builder()
                .url(createRunUrl)
                .header("Authorization", credentials)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JsonObject jo = JsonParser.parseString(response.body().string()).getAsJsonObject();

                RUN_ID = Integer.parseInt(jo.get("id").getAsString());

            } else {
                System.out.println("Failed to create test run. Status code: " + response.code()+response.message());
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void uploadTestResults(List<TestCaseResult> testResults) throws IOException {
        String url = AZURE_DEVOPS_URL + "/_apis/test/Runs/" + RUN_ID + "/results?api-version=" + API_VERSION;

        for (TestCaseResult tc : testResults) {
            ArrayList<TestCaseResult> tempo = new ArrayList<>();
            tempo.add(tc);
            String json = gson.toJson(tempo);

            String credentials = Credentials.basic("", Config.PAT);

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("Authorization", credentials)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Erreur lors de l'ajout des résultats de test : " + response);
                }

                String responseBody = response.body().string();

                if (!tc.getOutcome().equals("Passed")) {
                    ObjectMapper objectMapper = new ObjectMapper();

                    JsonNode jsonResponse = objectMapper.readTree(responseBody);

                    JsonNode results = jsonResponse.get("value");

                    if (results.isArray() && results.size() > 0) {
                        JsonNode firstResult = results.get(0);

                        int id = firstResult.get("id").asInt();
                        System.out.println(Config.dir_export);
                        Optional<Path> path = findLatestJpgFile(Config.dir_export + "/" +tc.getTestCaseTitle());
                        if(path.isPresent())
                            attachTestFile(RUN_ID, id, path.get().toString());

                    } else {
                        System.out.println("Aucun résultat trouvé.");
                    }
                }

            }
        }
    }
    public static Optional<Path> findLatestJpgFile(String directoryPath) throws IOException {
        Path dir = Paths.get(directoryPath);
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Le chemin spécifié n'est pas un répertoire : " + directoryPath);
        }

        try (Stream<Path> files = Files.walk(dir)) {
            return files
                    .filter(file -> file.toString().toLowerCase().endsWith(".jpg"))
                    .filter(Files::isRegularFile)  // Filtrer uniquement les fichiers réguliers
                    .max(Comparator.comparingLong(file -> {
                        try {
                            return Files.getLastModifiedTime(file).toMillis();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }));
        }
    }
    public static void updateTestRunState(String newState) throws IOException {
        String url = AZURE_DEVOPS_URL + "/_apis/test/Runs/" + RUN_ID + "?api-version=6.0-preview.3";

        String json = "{ \"state\": \"" + newState + "\" }";

        String credentials = Credentials.basic("", Config.PAT);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .patch(body)
                .header("Authorization", credentials)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la mise à jour de l'état du test run : " + response);
            }

            String responseBody = response.body().string();
        }
    }
    private static void attachTestFile(int runId, int testResultId, String FILE_PATH) throws IOException {

        String API_VERSION = "7.1-preview.1";
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String url = String.format("https://dev.azure.com/%s/%s/_apis/test/runs/%d/results/%d/attachments?api-version=%s", Config.ORGANIZATION, Config.PROJECT, runId, testResultId, API_VERSION);
        byte[] xmlContent = Files.readAllBytes(Paths.get(FILE_PATH));
        String xmlContentBase64 = Base64.getEncoder().encodeToString(xmlContent);

        ObjectNode payload = mapper.createObjectNode();
        payload.put("fileName", "screenshot.jpg");
        payload.put("comment", "Screenshot");
        payload.put("attachmentType", "GeneralAttachment");
        payload.put("stream", xmlContentBase64);

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), payload.toString());
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic("", Config.PAT))
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        }
    }

    public static void getPercyResult(String token, String parcoursName){
        
                Teststep t = new Teststep("getResult", "getResult" ,"getResult.htm", parcoursName, token);
                Config.compteur_instance = 2;
        
                WebPage_getResult(selfdriver,t);
                Config.compteur_instance = 1;
        
            }

        public static boolean WebPage_getResult(WebDriver selenium, Teststep t) {
        Date time1 = new Date();
        executeCommand("npx percy exec:stop");
        System.out.println("data:");

        Map<String, Object> result = GetData(t.param);
        if(result==null)
            return Fonctions.logStepKO(t, selfdriver, time1,"Un probleme est survenue lors de la remontée des résultats");
        if(((ArrayList<String>)result.get("changes")).isEmpty())
            return Fonctions.logStepOK(t, selenium, time1);
        StringBuilder sb = new StringBuilder();
        for(String nom:((ArrayList<String>)result.get("changes"))){
            sb.append(nom + "; ");
        }
        return Fonctions.logStepWarning(t, selfdriver, time1,"Un changement à été détécté sur les captures suivantes : "+sb.toString());
    }
    private static void executeCommand(String command) {
        try {
            // Utiliser ProcessBuilder pour exécuter la commande
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("cmd.exe", "/c", "cd Batfiles && "+ command);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            // Lire la sortie de la commande
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // Attendre la fin de l'exécution de la commande
            int exitCode = process.waitFor();
            System.out.println("\nCommande exécutée avec le code de sortie : " + exitCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static Map<String, Object> GetData(String token){

        Map<String, Object> Data = new HashMap<>();
        Map<String, String> param = new HashMap<>();
        param.put("project_slug", "your_project_slug");
        System.out.println(token);
        String response = CallAPI("https://percy.io/api/v1/projects",token, param);
        Gson gson = new Gson();
        com.google.gson.JsonObject jsonResponse = gson.fromJson(response, com.google.gson.JsonObject.class);
        String projectID = jsonResponse.getAsJsonObject("data").get("id").getAsString();
        Data.put("project_id", projectID);
        System.out.println("project_id: "+ projectID);

        param = new HashMap<>();
        param.put("project_id", projectID);
        response = CallAPI("https://percy.io/api/v1/builds", token, param);
        jsonResponse = gson.fromJson(response, com.google.gson.JsonObject.class);
        String buildID = jsonResponse.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
        String noBuild = jsonResponse.get("data").getAsJsonArray().get(0).getAsJsonObject().get("attributes").getAsJsonObject().get("build-number").getAsString();
        Data.put("build_id", buildID);
        Data.put("noBuild", noBuild);

        System.out.println("buildID: "+ buildID);

        String state;
        try {
            do {
                response = CallAPI("https://percy.io/api/v1/builds/"+buildID, token, null);
                jsonResponse = gson.fromJson(response, com.google.gson.JsonObject.class);
                state = jsonResponse.get("data").getAsJsonObject().get("attributes").getAsJsonObject().get("state").getAsString();

            } while (!(state.equals("failed")||state.equals("finished")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("state: "+ state);
        Data.put("state", state);
        param= new HashMap<>();
        param.put("build_id", buildID);
        response = CallAPI("https://percy.io/api/v1/snapshots", token, param);
        jsonResponse = gson.fromJson(response, com.google.gson.JsonObject.class);
        JsonArray JA = jsonResponse.get("data").getAsJsonArray();
        ArrayList<String> changes = new ArrayList<>();
        for(JsonElement s:JA){
            String name= s.getAsJsonObject().get("attributes").getAsJsonObject().get("name").getAsString();
            String RS= s.getAsJsonObject().get("attributes").getAsJsonObject().get("review-state").getAsString();
            System.out.println(name +" -> "+RS);
            if(!RS.equals("approved"))
                changes.add(name +" -> "+RS);
        }
        Data.put("changes", changes);

        return Data;
    }
    public static String CallAPI(String URL, String token, Map<String, String> parameter){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(40, TimeUnit.SECONDS);
        builder.readTimeout(40, TimeUnit.SECONDS);
        builder.writeTimeout(40, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(URL).newBuilder();
        if(parameter!=null) {
            for (Map.Entry<String, String> param : parameter.entrySet()) {
                urlBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("Authorization", "Token token=" + token)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = null;
            if (response.isSuccessful()) {
                responseBody = response.body().string();

            } else {
                System.out.println("Response KO: " + response.body().string());
            }
            return responseBody;
        }catch (SocketTimeoutException Se){
            System.out.println("Erreur lors de la connexion au serveur");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String[] DeskGetpropertybyName(String name, String prop) {
//        try {
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
//            return selfdriver.findElement(By.name(name)).getDomProperty(prop);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//
//    public static String[] DeskGetpropertybyAutomationId(String Id, String prop) {
//        try {
//            return selfdriver.findElement(AppiumBy.accessibilityId(Id)).getDomProperty(prop);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//
//    public static String[] DeskGetpropertybyxpath(String xpath, String prop) {
//        try {
//            return selfdriver.findElement(AppiumBy.xpath(xpath)).getDomProperty(prop);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//
//
}
//
//



