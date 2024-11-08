package scripts_techniques.Appium;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

import org.openqa.selenium.WebDriver;


import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

import au.com.bytecode.opencsv.CSVReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import javax.swing.table.TableColumn;


import org.apache.commons.io.FileUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;

import org.htmlparser.nodes.*;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.LabelTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.SelectTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.tags.TextareaTag;
import org.htmlparser.util.*;
import com.google.gson.*;
import java.util.List;
import java.util.Iterator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import scripts_techniques.Selenium.*;
import scripts_techniques.ktObject;
import scripts_techniques.ktObjects;
import scripts_techniques.Config;
import scripts_techniques.ktProp;

public  class Fonctions  {


	public static void stopRestart (WebDriver driver) {
			System.out.println("Passage dans stop restart");
			driver.close();			
	}

	/**
	 * création du fichier de logs de sYnopsis
	 * @throws IOException 
	 *
	 */	

	public static void createLogFile (String testcase_name) throws IOException {

		System.out.println("création du fichier traces!");
		//**jean**//
		System.out.println("dir export  " + Config.dir_export);
		//**jean**//

        // Create one directory 
       boolean success = ( new File(Config.dir_export)).mkdir(); 
        if (success) { 
           System.out.println( "Directory: " + Config.dir_export + " created" ) ;
 } 
		String strDirectoy = Config.dir_export+ File.separator +testcase_name ; 

		// Create one directory 
		success = ( new File(strDirectoy)).mkdir(); 
		if (success) { 
			System.out.println( "Directory: " + strDirectoy + " created" ) ; 
		} 

		File f; 
		String filename="";

		if (Config.compteur_instance ==2){
		
			f= new File( Config.dir_export+ File.separator+testcase_name + File.separator+testcase_name+ ".OUT" ); 
			f.delete();
			f.createNewFile();
		}
		else 
		{
			int compteur_instance = Config.compteur_instance -2 ;
			f= new File( Config.dir_export+ File.separator+testcase_name + File.separator+testcase_name+ "__" + compteur_instance + ".OUT" );
			f.delete();
			f.createNewFile();
		
		}
	}


		/**
	 * 
	 * Return parameter from csv file
	 * @param strFile
	 * @param linecount
	 * @param columncount
	 * @return
	 */
	
	
	
	public static String getParameter (String strFile, int linecount, int columncount) {
		
		try {
			CSVReader reader = new CSVReader(new FileReader(strFile), ';');
			String [] nextLine;
			int numline = 1;
			String param = "";
			while ((nextLine = reader.readNext()) != null) {
				if (numline==linecount) {
					param = nextLine[columncount-1];
					System.out.println("### valeur trouvée :" + nextLine[columncount-1]);
					String paramDecode = new String(param.getBytes(),"UTF-8");
					return paramDecode.replaceAll("%euro%", "?");
				}
				++numline;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception while reading csv file: " + e);
		}
		return "";
		
	}





	public static int get_lines_parameters (String FileParams) throws IOException{
		int count = 0;
		String file_param   = Config.dir_params +  File.separator + FileParams + ".csv";
		String strLine = "";
		//create BufferedReader to read csv file
		BufferedReader br = new BufferedReader( new FileReader(file_param));

		//read comma separated file line by line
		while( (strLine = br.readLine()) != null)
		{
			count++;
		}
		count = count + 1;

		return count;
	}
	
	
	
		
		
	
	public static void processMyNodes (Node node, Hashtable<String, String> prop_array) throws ParserException
	 {
	     if (node instanceof TextNode)
	     {
	         // downcast to TextNode
	         TextNode text = (TextNode)node;
	         // do whatever processing you want with the text
	         //System.out.println ("????ttextNode"+text.getText ());
	     }
	     if (node instanceof RemarkNode)
	     {
	         // downcast to RemarkNode
	         RemarkNode remark = (RemarkNode)node;
	         // do whatever processing you want with the comment
	     }
	     else if (node instanceof TagNode)
	     {
	    	 
	    	 
	    	TagNode tag = (TagNode) node;
			//tag input type =image
	    	//System.out.println ("pass input tag");		
			if (tag.getAttribute("name")!= null) {
			// 	  System.out.println ("pass name not null");
				prop_array.put("name", tag.getAttribute("name"));
			}
			else prop_array.put("name","");
			if (tag.getAttribute("src")!= null) {
					String regex = 	"^http://[^/]+?/";
					Pattern pattern = Pattern.compile(regex);
			        Matcher matcher = pattern.matcher(tag.getAttribute("src"));
			        String replace = "";
					//extract http://...? part 
			        String src =  matcher.replaceAll (replace);
			        prop_array.put("src",src);
			}
			else prop_array.put("src","");
			
			if (tag.getAttribute("id")!= null)
				prop_array.put("id", tag.getAttribute("id"));
			else prop_array.put("id","");
		
			if (tag.getAttribute("value")!= null) 
				prop_array.put("value", tag.getAttribute("value"));
				else prop_array.put("value","");

	      	if (tag.getAttribute("href")!= null) {
					String regex = 	"^http://[^/]+?/";
					Pattern pattern = Pattern.compile(regex);
			        Matcher matcher = pattern.matcher(tag.getAttribute("href"));
			        String replace = "";
					//extract http://...? part 
			        String href =  matcher.replaceAll (replace);
					prop_array.put("href", href);
			}
	      	else 	prop_array.put("href", "");
				
			if (tag.getAttribute("class")!= null)
					prop_array.put("classname", tag.getAttribute("class"));
			else prop_array.put("class","");
			
			if (tag.getAttribute("title")!= null)
					prop_array.put("title", tag.getAttribute("title"));
			else
					prop_array.put("title","");
					
		
			if (tag.getAttribute("src")!= null) {
					String regex = 	"^http://[^/]+?/";
					Pattern pattern = Pattern.compile(regex);
			        Matcher matcher = pattern.matcher(tag.getAttribute("src"));
			        String replace = "";
					//extract http://...? part 
			        String src =  matcher.replaceAll (replace);
			        prop_array.put("src",src);
				}
				else   prop_array.put("src","");
				
			if (tag.getAttribute("index")!= null)
				prop_array.put("index", tag.getAttribute("index"));
				else
					prop_array.put("index","");

			if (tag.getAttribute("xpath")!= null)
				prop_array.put("xpath", tag.getAttribute("xpath"));
				else
					prop_array.put("xpath","");

	
	         NodeList nl = tag.getChildren ();
	         if (nl != null )
	             for (NodeIterator i = nl.elements (); i.hasMoreNodes(); )
	                 processMyNodes (i.nextNode (),prop_array); 
	         
	 		}  
	    }
 
		public static void parseNode (Node htmlnode, Hashtable<String, String> prop_array) throws ParserException
	 {
		
		TagNode htmltag = (TagNode) htmlnode;
        NodeList nodes = null;
        nodes= htmltag.getChildren();
        
 
        for (int i = 0; i < nodes.size(); i++) {           
        	  Node node = nodes.elementAt(i);  
        	if (node instanceof TagNode)  {
		    	TagNode tag = (TagNode) node;
		    	if (!tag.getRawTagName().equals("html")) {		    				
					if (tag.getAttribute("name")!= null) {
						prop_array.put("name", tag.getAttribute("name"));
					}
					if (tag.getAttribute("src")!= null) {
							String regex = 	"^http://[^/]+?/";
							Pattern pattern = Pattern.compile(regex);
					        Matcher matcher = pattern.matcher(tag.getAttribute("src"));
					        String replace = "";
							//extract http://...? part 
					        String src =  matcher.replaceAll (replace);
					        prop_array.put("src",src);
					}	
					if (tag.getAttribute("id")!= null)
						prop_array.put("id", tag.getAttribute("id"));
                                      //Modif android native
                                       if (tag.getAttribute("androidId")!= null)
                                       prop_array.put("androidId", tag.getAttribute("androidId"));
                                         if (tag.getAttribute("resourceid")!= null)
                                           prop_array.put("resourceid", tag.getAttribute("resourceid"));
                         
                                        //Modif iphone native
                                        if (tag.getAttribute("iPhoneId")!= null)
                                        prop_array.put("iPhoneId", tag.getAttribute("iPhoneId"));
                 
				
					if (tag.getAttribute("value")!= null) 
						prop_array.put("value", tag.getAttribute("value"));
			
			      	if (tag.getAttribute("href")!= null) {
							String regex = 	"^http://[^/]+?/";
							Pattern pattern = Pattern.compile(regex);
					        Matcher matcher = pattern.matcher(tag.getAttribute("href"));
					        String replace = "";
							//extract http://...? part 
					        String href =  matcher.replaceAll (replace);
							prop_array.put("href", href);
					}
					if (tag.getAttribute("class")!= null)
							prop_array.put("classname", tag.getAttribute("class"));
					
					if (tag.getAttribute("title")!= null)
							prop_array.put("title", tag.getAttribute("title"));
					else
							
				
					if (tag.getAttribute("src")!= null) {
							String regex = 	"^http://[^/]+?/";
							Pattern pattern = Pattern.compile(regex);
					        Matcher matcher = pattern.matcher(tag.getAttribute("src"));
					        String replace = "";
							//extract http://...? part 
					        String src =  matcher.replaceAll (replace);
					        prop_array.put("src",src);
						}
						else   prop_array.put("src","");
						
					if (tag.getAttribute("index")!= null)
						prop_array.put("index", tag.getAttribute("index"));
		
					if (tag.getAttribute("xpath")!= null)
						prop_array.put("xpath", tag.getAttribute("xpath"));
if (tag.getAttribute("text")!= null)
					prop_array.put("text", tag.getAttribute("text"));
			     	}
if (tag.getAttribute("frameid")!= null)
					prop_array.put("frameid", tag.getAttribute("frameid"));
			     
	        	}
        	}
	    }	
	
	
 	  /**
	 retrieve obj properties in json obj file
	 */
	 	
	public static Hashtable<String, String> getObjectProperties (String htmlfile_name) {
		 Hashtable<String, String> prop_array= new Hashtable<String, String>();
		//html object file
		String jsonFile  = Config.file_objects;

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile));
			Gson gson = new Gson();
			ktObjects json = gson.fromJson(bufferedReader, ktObjects.class);
			System.out.println (json);
			List <ktObject> objects = json.getListOfObjects();
			Iterator <ktObject> it = objects.iterator();
			while (it.hasNext()) {
				ktObject object = it.next();
				if (htmlfile_name.equals(object.getTechLabel())) {
					System.out.println ("Object trouve dans le fichier json : " + object.getLabel());
					System.out.println (object.getLabel());
					List <ktProp> props =  object.getListOfProps();
					System.out.println (props);
					for (int i=0; i < props.size(); i++) {
						ktProp prop = props.get(i);
						//System.out.println ("prop name " + prop.getPropName());		
					//	System.out.println ("prop name " + prop.getPropValue());
						prop_array.put ( prop.getPropName(), 	prop.getPropValue());							
					}
				}

			}

	} catch (Exception e) {
		
		e.printStackTrace();
	}
	return prop_array;
	}
	
	
	/**
	 * 
	 * @param secsIn
	 * @return
	 */
	
	public static String formatIntoHHMMSS(long secsIn)
	{

	String time = "";
	time = 	String.format("%d s", 
			    TimeUnit.MILLISECONDS.toSeconds(secsIn));

/*		
	long hours = secsIn / 3600,
	remainder = secsIn % 3600,
	minutes = remainder / 60,
	seconds = remainder % 60;

	return ( (hours < 10 ? "0" : "") + hours
	+ ":" + (minutes < 10 ? "0" : "") + minutes
	+ ":" + (seconds< 10 ? "0" : "") + seconds );
	*/
	return time;

	}
	
	
	
	
	/**
	 * 
	 * Log step, status OK
	 * @param teststep
	 * @param selenium
	 * @param time1
	 * @return
	 */
	public static boolean logStepOK (Teststep teststep, WebDriver driverInstance, Date time1) {
		
		Date time2 = new Date();
		teststep.setExecResults("OK",0, "","",Fonctions.formatIntoHHMMSS(time2.getTime()-time1.getTime()));
		try {
			teststep.logStepExec(driverInstance, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	/**
	 * 
	 * Log step, status OK
	 * @param teststep
	 * @param selenium
	 * @param time1
	 * @param xpath
	 * @return
	 */
	public static boolean logStepOK (Teststep teststep, WebDriver driverInstance, Date time1, String xpath) {
		
		Date time2 = new Date();
		teststep.setExecResults("OK",0, "","",Fonctions.formatIntoHHMMSS(time2.getTime()-time1.getTime()));
		try {
			teststep.logStepExec(driverInstance, xpath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	/**
	 * log step, status warning
	 * @param teststep
	 * @param selenium
	 * @param time1
	 * @param warningMsg
	 * @return
	 */
	public static boolean logStepWarning (Teststep teststep, WebDriver driverInstance, Date time1, String warningMsg) {
		Date time2 = new Date();
		teststep.setExecResults("Warning",0, "",warningMsg,Fonctions.formatIntoHHMMSS(time2.getTime()-time1.getTime()));
		try {
			teststep.logStepExec(driverInstance, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * log step, status warning
	 * @param teststep
	 * @param selenium
	 * @param time1
	 * @param warningMsg
	 * @param xpath
	 * @return
	 */
	public static boolean logStepWarning (Teststep teststep, WebDriver driverInstance, Date time1, String warningMsg, String xpath) {
		Date time2 = new Date();
		teststep.setExecResults("Warning",0, "",warningMsg,Fonctions.formatIntoHHMMSS(time2.getTime()-time1.getTime()));
		try {
			teststep.logStepExec(driverInstance, xpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	/**
	 * Log step, status KO
	 * @param teststep
	 * @param selenium
	 * @param time1
	 * @param errorMsg
	 * @return
	 */
	public static boolean logStepKO (Teststep teststep, WebDriver driverInstance,  Date time1, String errorMsg) {
		Date time2 = new Date();
		teststep.setExecResults("KO",0, "",errorMsg,Fonctions.formatIntoHHMMSS(time2.getTime()-time1.getTime()));
		try {
			teststep.logStepExec(driverInstance, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * clean current path log
	 * 
	 */
	public static void cleanTestcaseLog(String testcase_name) {
		System.out.println ("Clean log dir " + Config.dir_export + "/" + testcase_name);
		File Dir = new File (Config.dir_export + "/" + testcase_name);
	    String[] myFiles;
	    myFiles = Dir.list();
	    if (myFiles != null) {
	    for (int i=0; i < myFiles.length;i++ ){
	    	File myFile = new File (Dir, myFiles[i]);
	    	if (!myFile.isDirectory()) {
	    		myFile.delete();
	    	}
	    }
	    }
	    System.out.println ("OK");
	}
	
		
	
	/**
	write end.txt
	*/
	
	public static void writeEndExecutionFile (String testcase_name) {
		
			//write end file execution
			try {		
				StringBuffer buf  = new StringBuffer();
				buf.append ("fin exec");
				java.io.FileWriter endFile = new java.io.FileWriter (Config.dir_export + File.separator + testcase_name + File.separator + "end.txt");
				BufferedWriter out = new BufferedWriter(endFile);
				String str = "";
				str=buf.toString();
				out.write(str);
				out.close();			
				System.out.println ("end execution file written correctly");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println (e.getMessage());
			}		
	}
	
	
	public static void generateVideoFile(String testcase_name) {
		
		//delete cmd file if exists
		File batFile = new File (Config.dir_export + File.separator + testcase_name + File.separator + "createvideo.bat");
		if (!batFile.exists()) {
				batFile.delete();
		}
	
		
		//delete video file if exists
		File video = new File (Config.dir_export + File.separator + testcase_name + File.separator + "exec.mp4");
	 	if (!video.exists()) {
				video.delete();
		}
	
		String cmd = "@echo off;d:";
		cmd += "\r\n";
		cmd += "cd " +  Config.dir_export.replace("/", "\\") + "\\" + testcase_name;
		cmd += "\r\n";
		cmd += "SET PATH=%PATH%;C:\ffmpeg\ffmpeg-20180708-3a2d21b-win64-static\bin";
		cmd += "\r\n";
		cmd += "for  %%a in (*.jpg) do (";
		cmd += "\r\n";
		cmd += "	echo file '%%a' >> " +Config.dir_export.replace("/", "\\") +  "\\"  + testcase_name +  "\\"  + "images.txt";
		cmd += "\r\n";
		cmd += ")";
		cmd += "\r\n";
		cmd += "ffmpeg.exe -r 6 -f concat -safe 0 -i images.txt -c:v libx264 -vf scale=1280:-2 -pix_fmt yuv420p exec.mp4";
		cmd += "\r\n";
		cmd += "del /q " + Config.dir_export.replace("/", "\\") + "\\" + testcase_name + "\\" + "images.txt";
		cmd += "\r\n";
		
			try {
			java.io.FileWriter cmdFile = new java.io.FileWriter (Config.dir_export + File.separator + testcase_name + File.separator + "createvideo.bat");
			BufferedWriter out = new BufferedWriter(cmdFile);
			String str = "";
			str=cmdFile.toString();
			out.write(cmd);
			out.close();
			} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println (e.getMessage());
				}	
		

		try {
			Process p = Runtime.getRuntime().exec("cmd /c start \"\" "  + Config.dir_export + File.separator + testcase_name + File.separator + "createvideo.bat");
			p.waitFor();
				 
		}
		catch (Exception e) {
				e.printStackTrace();
		}
		System.out.println ("Video file generated");
		try {
		Thread.sleep(Config.pause_actions);
		} catch (InterruptedException e) {
			e.printStackTrace();
	}
	}
	
	/**
	*delete video screenshots
	*/
	public static void deleteVideoScreenshots(String testcase_name) {
		
		
		 System.out.println ("Video screenshot deletion...");
		  File logRep = new File (Config.dir_export + File.separator + testcase_name);
		  String[] myFiles;
		  myFiles = logRep.list();
		  if (myFiles != null) {
			for (int i=0; i < myFiles.length;i++ ){
				File myFile = new File (logRep, myFiles[i]);
				if (!myFile.isDirectory()  && myFile.getName().contains(".jpg")) {
					myFile.delete();
				}
			}
		  }
		  System.out.println ("Video screenshots deleted");
	}
		
	
	
	/**
	 * copy current log file in history dir
	 * @param string
	 * @param string2
	 */

	public static void histoTestcaseLog(String testcase_name, String timestamp)  {
		
		
		
		  String histoRep = testcase_name + "___" + timestamp; 
		  String logRep = Config.dir_export + File.separator + testcase_name + File.separator + histoRep;
		 	
		  File  logRepFile =  new File (logRep);
		  //if dir histo dir doesnt exist, create
		  if (!logRepFile.exists()) {
			  	// Create one directory 
				boolean success = ( new File(logRep)).mkdir(); 
				if (success) {
					System.out.println ("Log historisation " +  logRep + " created successfully");	
					logRepFile = new File (logRep);
				}
				else {
					System.out.println ("Error creating Log historisation dir " + logRep);	
					return;
				}
		  }
		  //if already exist (execution without generation), clean it
		  else  {
			  String[] myFiles;
			  myFiles = logRepFile.list();
			  for (int i=0; i < myFiles.length;i++ ){
			    	File myFile = new File (logRep, myFiles[i]);
			    	myFile.delete();	    	
			    }
		  }
		  
		  String logRepRoot = Config.dir_export + File.separator + testcase_name;
		  File logRepRootFile = new File (logRepRoot);
		  String[] myFiles;
		  myFiles = logRepRootFile.list();
		  if (myFiles !=  null) {
		  for (int i=0; i < myFiles.length;i++ ){
		    	File myFile = new File (logRepRootFile, myFiles[i]);
		    	if (!myFile.isDirectory()) {
		    		try {
						FileUtils.copyFileToDirectory(myFile, logRepFile);
					} catch (IOException e) {
						e.printStackTrace();
					}	  
		    	}
		   }}
		  System.out.println ("Log info copied successfully to histo log dir");
		}



public static void handleFrames(WebDriver driver, String frameid) {
		if (frameid!=null) {
		   driver.switchTo().defaultContent();
		   System.out.println ("frame trouvé : " + frameid);
           driver.switchTo().frame (frameid);
		}
	}
	

public static void switchToNewWindow (WebDriver driver) {
	
	java.util.Set <String> handles =driver.getWindowHandles();
	java.util.Iterator<String> it = handles.iterator();
	//iterate through your windows
	while (it.hasNext()){
	String newwin = it.next();
	driver.switchTo().window(newwin);
  }
}


/**
 * switch to driver webview (hybrid apps)
 * @param driver
 */


public static void switchToWebViewContext(AndroidDriver driver) {
	Set<String> availableContexts = driver.getContextHandles();
	System.out.println("Total No of Context Found After we reach to WebView = " + availableContexts.size());
	for (String context : availableContexts) {
		System.out.println("Context Name is " + context);
		if (context.contains("WEBVIEW")) {
			System.out.println("Context avec webview Name is " + context);
			driver.context(context);
			break;
		}
	}
	
}
public static void highLighterMethod(WebDriver driver, WebElement element){
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
}
}
