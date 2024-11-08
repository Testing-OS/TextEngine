//kaliostest Scenarios Export File
//Export date : 2024-09-06 14:24:18
package scripts_metiers;
import org.openqa.selenium.WebDriver;
public class Scenarios {
public static boolean VisualTesting_percy_2680 (WebDriver selenium, int testcase_id, String testcase_label, int scenario_id, String scenario_label, int scenario_index) throws Exception {
boolean status = true;
    //Module Call cliquer sur What needs to be done?
    status = Modules.cliquer_sur_What_needs_to_be_done__3137(selenium, testcase_id,testcase_label,scenario_id,scenario_label, scenario_index, 3137,"cliquer_sur_What_needs_to_be_done?",1);
if (!status) return false;
    //Module Call percy
    status = Modules.percy_3138(selenium, testcase_id,testcase_label,scenario_id,scenario_label, scenario_index, 3138,"percy",2);
if (!status) return false;
return status;
}
}
