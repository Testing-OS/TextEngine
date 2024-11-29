package scripts_textengine;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class MainWeb {

	@Test
	public void parcoursOK() throws IOException {

		initWeb("http://www.test.kalios-saas.com/automationpractice/index.html", "parcoursOK");
		clickbytext("Start");
		waitloadingcomplete(null,"3");
		clickbytext("Valider");
		close("");
		//sa.assertAll();

	}

	@Test
	public void CompareScreenshot() throws IOException {

		initWeb("https://magento.softwaretestingboard.com/", "CompareScreenshot");
		comparescreenshot("","HomePage|less|1");
		close("");
		ShowResult();
		//sa.assertAll();
	}
	@Test
	public void parcoursKO() throws IOException {

		initWeb("http://www.test.kalios-saas.com/automationpractice/index.html", "parcoursKO");
		clickbytext("Start");
		waitloadingcomplete(null,"3");
		clickbytext("Valier");
		close("");
		//sa.assertAll();

	}
	@Test
	public void parcoursWarning() throws IOException {

		initWeb("http://www.test.kalios-saas.com/automationpractice/index.html", "parcoursWarning");
		clickbytext("Start");
		waitloadingcomplete(null,"0");
		clickbytext("Valider");
		close("");
		//sa.assertAll();

	}
	@Test
	public void parcours2Warning() throws IOException {

		initWeb("http://www.test.kalios-saas.com/automationpractice/index.html", "parcours2Warning");
		waitloadingcomplete(null,"0");
		clickbytext("Start");
		waitloadingcomplete(null,"0");
		clickbytext("Valider");
		close("");
		//sa.assertAll();

	}
	@Test
	public void parcoursKOetWarning() throws IOException {

		initWeb("http://www.test.kalios-saas.com/automationpractice/index.html", "parcoursKOetWarning");
		clickbytext("Start");
		waitloadingcomplete(null,"0");
		clickbytext("Valder");
		ChangeParcours("change");
		clickbytext("Valder");
		close("");
		//sa.assertAll();

	}
	@Test
	public void Select() throws IOException {

		initWeb("http://www.test.kalios-saas.com/automationpractice/index.html", "Select");
		selectbytext("Select Component", "[2]");
		close("");
		ShowResult();
		//sa.assertAll();

	}

	public static void main(String[] args) throws IOException, InterruptedException {
	MainWeb mw = new MainWeb();
//		mw.parcoursOK();
//		mw.parcours2Warning();
//		mw.parcoursKO();
//		mw.parcoursKOetWarning();
//		mw.parcoursWarning();
		//mw.CompareScreenshot();
		mw.Select();
		//printResultTestCase();

		//UploadInAzureDevOps("testindependant");

		//UploadInAzureDevOps("test2");

		//RunKaliosTestPlan("essai2023","adda3de35af2f5c2d0efed716c0e8e8f" ,"simple", "https://magento.softwaretestingboard.com/");
		//RunKaliosTestPlan("local3","6441e0bd46b4324777f4803416e3f219" ,"percy", "http://127.0.0.1:5500/");
		//TextEngine.printSteps();
		//RunKaliosTestPlan("test20231023","f910a1a8eff9c2dcfa1b20cc8bc1cc4e" ,"exemple_parcours", "https://magento.softwaretestingboard.com/");
		//RunKaliosTestPlanBrowserStack();
	}
}
