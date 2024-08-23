package scripts_techniques.Selenium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.nio.charset.StandardCharsets;
import scripts_techniques.*;
import org.openqa.selenium.WebDriver;

public class Teststep  {

	public  int perspect=0;
	public  int struct_type=0;
	public  int application_id=0;
	public  String application_label="";
	public  int testcase_id=0;
	public  String testcase_label="";
	public static int scenario_id=0;
	public  String scenario_label="";
	public  int scenario_index=0;
	public  int module_id=0;
	public  String module_label="";
	public  int module_index=0;
	public  int index_step=0;
	public  int screen_id=0;
	public  String  screen_label="";
	public  String screen_attach_name="";
	public  int object_id=0;
	public  String object_Label="";
	public  String object_attach_name="";
	public  String action_ref="";
	public  String object_type="";
	public  String action_label="";
	public  int action_id=0;
	public  String param="";
	public static String status="OK";
	
	public boolean texteEngine;

	public static int errorCode=0;
	public static String errorLabel="";
	public static String errorMessage="";
	public static String delay="";



	public Teststep(int pperspect, int pstruct_type, int papplication_id, String papplication_label, 
			int ptestcase_id, String ptestcase_label, 
			int pscenario_id,String pscenario_label,  int pscenario_index, 
			int pmodule_id, String pmodule_label,int pmodule_index, 
			int pindex_step, int pscreen_id, String pscreen_label, String pscreen_attach_name, int pobject_id, String pobjectLabel, String pobject_attach_name,
			String paction_ref, String pobject_type, String paction_label, int paction_id) {

		perspect=pperspect;
		struct_type = pstruct_type;
		application_id = papplication_id;
		application_label = papplication_label;
		testcase_id = ptestcase_id;
		testcase_label = ptestcase_label;
		scenario_id = pscenario_id;
		scenario_label = pscenario_label;
		scenario_index = pscenario_index;
		module_id = pmodule_id;
		module_label = pmodule_label;
		module_index = pmodule_index;
		index_step = pindex_step;
		screen_id = pscreen_id;
		screen_label = pscreen_label;
		screen_attach_name = pscreen_attach_name;
		object_id = pobject_id;
		object_Label = pobjectLabel;
		object_attach_name = pobject_attach_name;
		action_ref = paction_ref;
		object_type = pobject_type;
		action_label = paction_label;
		action_id = paction_id;
		texteEngine = false;


	}
//Version Teststep pour TexteEngine
	public Teststep(String act_lab,String label ,String pobject_attach_name,String ptestcase_label, String pparam){
		testcase_label = ptestcase_label;
		object_attach_name=pobject_attach_name;
		param = pparam;
		action_label = act_lab;
		object_Label = label;
		texteEngine = true;

	}



    //Set execution results
	public void setExecResults (String pStatus,int pErrorCode, String pErrorLabel, String pErrorMessage, String pDelay){
		status 		= pStatus;
		errorCode 		= pErrorCode ;
		errorLabel 	= pErrorLabel;
		errorMessage 	= pErrorMessage;
		delay 			= pDelay;
	}

	//log step execution
	public void logStepExec(WebDriver  selenium, String xpath) throws IOException{

		//date
		String format = "yyyy-MM-dd H:mm:ss"; 

		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 

		//System.out.println( formater.format( date ) ); 
        String time = formater.format( date );

		//cal.setTime(new Date());
		FileWriter log_file;

		//log file Open
        BufferedWriter out;

		if (Config.compteur_instance ==2){
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.dir_export+ File.separator+testcase_label + File.separator+testcase_label+ ".OUT", true), StandardCharsets.UTF_8));
		}
		else 
	    {
			int compteur_instance = Config.compteur_instance -2 ;
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.dir_export+ File.separator+testcase_label + File.separator +testcase_label+ "__" + compteur_instance + ".OUT", true), StandardCharsets.UTF_8));
		}

		//log file write status
		String str_log = "";
		StringBuffer sb = new StringBuffer();
	
		sb.append(perspect  + Config.outseparator + struct_type  + Config.outseparator + application_id + Config.outseparator + application_label + Config.outseparator + testcase_id +  Config.outseparator + testcase_label +  Config.outseparator + scenario_id + Config.outseparator);
		sb.append(scenario_label +  Config.outseparator + scenario_index +  Config.outseparator + module_id +  Config.outseparator +  module_label + Config.outseparator +  module_index + Config.outseparator + time + Config.outseparator);
		sb.append(index_step + Config.outseparator + screen_id + Config.outseparator + screen_label + Config.outseparator + object_id + Config.outseparator);
		sb.append(object_Label + Config.outseparator + object_type + Config.outseparator + action_id + Config.outseparator + action_label + Config.outseparator +  param + Config.outseparator);
		if (xpath != null) {
			sb.append(status + Config.outseparator +  errorCode + Config.outseparator + errorLabel + Config.outseparator + errorMessage.replace ("\n", "")  + Config.outseparator + delay + Config.outseparator + xpath + "\r\n");
		} else {
			sb.append(status + Config.outseparator +  errorCode + Config.outseparator + errorLabel + Config.outseparator + errorMessage.replace ("\n", "")  + Config.outseparator + delay + "\r\n");
		}
		str_log=sb.toString();
		out.write(str_log);
		out.close();
		
		String compteur_instance = "";
		int compteur_instance_i = Config.compteur_instance-2 ;
		if (Config.compteur_instance>2){
			compteur_instance="_" + compteur_instance_i + "_";
		}
	
		 if (status.equals("Warning")) {
			
			 System.out.println ("Capture en warning : " + Config.dir_export + File.separator + testcase_label + File.separator + testcase_label + "_" + scenario_index + "_" + scenario_id + "_" + module_index + "_" + index_step + compteur_instance + "_WARNING");
			 BufferedImage screencapture = null;
				try {
					screencapture = new Robot().createScreenCapture(
					 new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (AWTException e) {
					e.printStackTrace();
				}

			     // Save as JPEG
		     File file = new File(Config.dir_export + File.separator + testcase_label + File.separator + testcase_label + "_" +  scenario_index  + "_" + scenario_id + "_" + module_index + "_" + index_step + compteur_instance + "_WARNING");
		     ImageIO.write(screencapture, "jpg", file);
		    
		 }
		 else if (status.equals("KO")) {
			 System.out.println ("Capture en KO : " + Config.dir_export + File.separator + testcase_label + File.separator + testcase_label  + "_" +  scenario_index  + "_" + scenario_id + "_" + module_index + "_" + index_step + compteur_instance + "_KO.jpg.jpg");
			 BufferedImage screencapture = null;
				try {
					screencapture = new Robot().createScreenCapture(
					 new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (AWTException e) {
					e.printStackTrace();
				}

			     // Save as JPEG
		     File file = new File(Config.dir_export + File.separator + testcase_label + File.separator + testcase_label  + "_" + scenario_index + "_" + scenario_id + "_" + module_index + "_" + index_step + compteur_instance +  "_KO.jpg.jpg");
		     ImageIO.write(screencapture, "jpg", file);
		
		   }	

			if (Config.takeScreenshots==1) {
				 System.out.println (Config.dir_export + File.separator + testcase_label + File.separator + "capture__" + Config.compteur_params + ".jpg");
			 BufferedImage screencapture = null;
				try {
					screencapture = new Robot().createScreenCapture(
					 new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (AWTException e) {
					e.printStackTrace();
				}

			     // Save as JPEG
		     File file = new File(Config.dir_export + File.separator + testcase_label + File.separator + "capture-0" + Config.compteur_params + ".jpg");
		     ImageIO.write(screencapture, "jpg", file);
				
				
				
			}


		   
	}

}



