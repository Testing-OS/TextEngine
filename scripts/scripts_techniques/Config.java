
package scripts_techniques;
 public class Config {

	//log export 
	
 //Replace C:/kalios with your project folder 
	public static String tunnel_path			=  "C:\\Chemin_vers_le_textengine\\TextEngine";
	
	
	public static String dir_export				= 	tunnel_path+"/results";
	
	
	//params directory
       public static String dir_params				= 	tunnel_path+"/scripts/scripts_donnees";
	
	
	//object files directory
	public static String dir_objects			= 	tunnel_path+"/scripts/objects";
	
	
	public static String path_exe        = "c:/your_path/";
	
	
	public static String dir_config				= 	tunnel_path+"/scripts/drivers";
		

	//ktObj file 
	
	public static String file_objects			= 	dir_objects + "/Objects.ktObjects";

	//additional files
	public static String additional_files			= 	tunnel_path+"/scripts/additional_files";
	public static String  propertyFile = additional_files+"/variables.ini";

 
    public static int takeScreenshots				=   0;
	 public static String appLink;

	 public enum TYPE_Instance {PROD, PREPROD, LOCAL};

	 public static int AzureDevOps = 0;
	 public static String ORGANIZATION = "";
	 public static String PROJECT = "";
	 public static String PAT = "";
	 
	 public static int Jira = 0;
	 public static String JIRA_URL = "https://your_jira_url/rest/api/2/";
	 public static String JIRA_USERNAME = "";
	 public static String JIRA_API_TOKEN = "";
	 public static String JIRA_PROJECT_NAME = "";


	 public static int Percy = 0;
	 public static String PercyToken = "";

	 public static TYPE_Instance Type = TYPE_Instance.PROD;
	
	
 //out separator for log files
	public static String outseparator				="	";
	//param separator in param file
	public static String paramseparator				=";";
	//window attach_timout
	public static long window_attach_timeout		= 15;
	//pause between actions
	public static int pause_actions					= 1000;
	//timeout for elements presence
	public static int timeout_elements				= 10000;
	//timeout for download
	public static int timeout_download				= 10;
	//timeout for download
	public static int timeout_try				= 100;
	
	//timeout for elements presence
	public static int num_retry				= 3;
		
	public static String testcase_status			="OK";
	 
	
	public static int compteur_params				=1;
	public static int compteur_instance				=2; 
 
    public static int  waitBeforeExec           = 1000;
	
	public static int pause_testcases				=1500;

	public static String datapoolFile = additional_files+"/datapool.csv";




    public static final String appium_url               = "http://localhost:4723/wd/hub/";
	public static final String deviceId                 ="emulator-5554";
	public static final String deviceOsVersion          ="9";
     
       //android native param
       public static final String androidId_rootContainer                  ="com.demo:id/drawer_layout";
     
 

}
