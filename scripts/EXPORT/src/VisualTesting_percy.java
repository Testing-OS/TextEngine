//kaliostest Testcase Export File
//Export date : 2024-09-06 14:24:18
//Testcase VisualTesting_percy
package scripts_metiers;
import scripts_techniques.*;
import java.io.File;
		import java.util.concurrent.TimeUnit;
		import org.openqa.selenium.*;
		import org.openqa.selenium.firefox.*;
		import org.openqa.selenium.chrome.*;
		import org.openqa.selenium.edge.*;
		import org.openqa.selenium.safari.SafariDriver;
		import org.openqa.selenium.ie.InternetExplorerDriver;
		import org.openqa.selenium.opera.OperaDriver;
		import org.openqa.selenium.edge.EdgeDriver;
		import org.openqa.selenium.edge.EdgeOptions;
		import org.openqa.selenium.WebDriver;
		import org.openqa.selenium.remote.DesiredCapabilities;
		import junit.framework.*;
		import java.util.Map;
		import java.util.HashMap;
		import org.openqa.selenium.remote.CapabilityType;
public class VisualTesting_percy extends TestCase {
	//drivers declaration
	public static WebDriver driverInstance;
	public static boolean JunitLaunch;
	public static WebDriver selfdriver;
	public VisualTesting_percy(String name) {
		super(name);
	}
	//methode appelee uniquement en mode lancement Junit, au lancement
	protected void setUp() throws Exception {
		JunitLaunch=true;
		super.setUp();
	}
	//methode appelee uniquement en mode lancement Junit, � la fermeture
	protected void tearDown() throws Exception {
		System.out.println ("Passage teardown");
	}
	//main method
	public static boolean launch() throws Exception {
		File file = new File(Config.dir_config + "/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );
		chromePrefs.put("download.prompt_for_download", false);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--remote-allow-origins=*"); 
		options.addArguments("--disable-search-engine-choice-screen"); 
		options.setAcceptInsecureCerts(true); 
		WebDriver selenium = new ChromeDriver(options);
		VisualTesting_percy.selfdriver = selenium;
		//Create the Selenium implementation
		selenium.get("https://magento.softwaretestingboard.com/");
		selenium.manage().window().maximize();
		//call scenarios
		boolean status = true;
		//call scenario VisualTesting percy
    	status = Scenarios.VisualTesting_percy_2680(selenium, 2685, "VisualTesting_percy",2680,"VisualTesting_percy",1);
    	if (status == false) {
			return status;
		}
			return status;
	}
	public void testPlanGo() {
		try {
		main(null);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
	//FONCTION MAIN
	public static void main(String[] args) throws InterruptedException {
  		Fonctions.cleanTestcaseLog ("VisualTesting_percy");
		Scripts_techniques.deleteDatapoolVarFile();
		Scripts_techniques.createDatapoolJVxpathFile("VisualTesting_percy");
		boolean result = false;
		try {
			System.out.println ("Demarrage du parcours");
			int num_instances = Fonctions.get_lines_parameters("VisualTesting_percy");
			Config.compteur_instance = 2;
			while (Config.compteur_instance<num_instances) {
				Config.compteur_params=1;
				Fonctions.createLogFile("VisualTesting_percy");
				result = launch();
				if (result==false) {
					Fonctions.stopRestart(VisualTesting_percy.selfdriver);
				}
				Config.compteur_instance = Config.compteur_instance+1;
			}
			if (JunitLaunch) 
	assertEquals (result, true);
			Scripts_techniques.deleteDatapoolVarFile();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
}
