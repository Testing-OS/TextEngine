//kaliostest Components Export File
//Export date : 2024-09-06 14:24:18
package scripts_metiers;
import org.openqa.selenium.WebDriver;
	import java.io.IOException;
	import java.io.File;
import scripts_techniques.*;
public class Modules {
public static boolean cliquer_sur_What_needs_to_be_done__3137 (WebDriver selenium, int testcase_id, String testcase_label, int scenario_id, String scenario_label, int scenario_index,int module_id, String module_label, int module_index) throws IOException {
	boolean status = true;
	String file_params = Config.dir_params +  File.separator + testcase_label + ".csv";
    //testStep Attributes
    Teststep teststep_1 = new Teststep(0,5,47,"elearning_matthieu",testcase_id,testcase_label,scenario_id,scenario_label,scenario_index,module_id,module_label, module_index,1,139,"default","default_Ecran.htm",1541,"Ecran","default_Ecran.htm","WebPage","Wait Loading complete","Wait Loading complete",13435);
    //param call
 	teststep_1.param  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
   	 //exec script call
		status = Scripts_techniques.Webpage_waitloadingcomplete(selenium, teststep_1);
    //parametrer count increase
 	Config.compteur_params    = Config.compteur_params +1;
	if (!status)  return false;
    //testStep Attributes
    Teststep teststep_2 = new Teststep(0,5,47,"elearning_matthieu",testcase_id,testcase_label,scenario_id,scenario_label,scenario_index,module_id,module_label, module_index,2,139,"default","default_Ecran.htm",3049,"What needs to be done?","default_What_needs_to_be_done_.htm","WebObject","sendkeysbytext","sendkeysbytext",13416);
    //param call
 	teststep_2.param  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
	if (!teststep_2.param.equals("")) {
   	 //exec script call
		status = Scripts_techniques.WebObject_sendkeysbytext(selenium, teststep_2);
	}
    //parametrer count increase
 	Config.compteur_params    = Config.compteur_params +1;
	if (!status)  return false;
	return true;
}
public static boolean percy_3138 (WebDriver selenium, int testcase_id, String testcase_label, int scenario_id, String scenario_label, int scenario_index,int module_id, String module_label, int module_index) throws IOException {
	boolean status = true;
	String file_params = Config.dir_params +  File.separator + testcase_label + ".csv";
    //testStep Attributes
    Teststep teststep_1 = new Teststep(0,5,47,"elearning_matthieu",testcase_id,testcase_label,scenario_id,scenario_label,scenario_index,module_id,module_label, module_index,1,139,"default","default_Ecran.htm",1541,"Ecran","default_Ecran.htm","WebPage","visualtesting","visualtesting",14331);
    //param call
 	teststep_1.param  = Fonctions.getParameter(file_params,Config.compteur_instance, Config.compteur_params);
	if (!teststep_1.param.equals("")) {
   	 //exec script call
		status = Scripts_techniques.WebPage_visualtesting(selenium, teststep_1);
	}
    //parametrer count increase
 	Config.compteur_params    = Config.compteur_params +1;
	if (!status)  return false;
	return true;
}
}
