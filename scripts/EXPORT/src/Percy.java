//kaliostest Testplan Export File
//Export date : 2024-09-06 14:24:18
package scripts_metiers;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import scripts_metiers.*;
import scripts_techniques.*;
public class Percy  {
public static Test suite() {
	Config.dir_export = Config.dir_export + "/planTests/percy___132___1725632658";
	TestSuite suite = new TestSuite("Percy");
//call test case  VisualTesting percy
	suite.addTestSuite(VisualTesting_percy.class);
	return suite;
}
}
