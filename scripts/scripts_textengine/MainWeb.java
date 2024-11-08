package scripts_textengine;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class MainWeb {

	@Test
	public void parcours1() throws IOException {

		initWeb("http://www.test.kalios-saas.com/automationpractice/index.html", "parcours1");
		clickbytext("Start");
		waitloadingcomplete(null,"3");
		clickbytext("Valider");
		close("");
		sa.assertAll();
		ShowResult();
	}
	@Test
	public void parcours2() throws IOException {

		initWeb("http://www.test.kalios-saas.com/automationpractice/index.html", "parcours2");
		clickbytext("Start");
		waitloadingcomplete(null,"3");
		clickbytext("Valier");
		close("");
		sa.assertAll();
		ShowResult();
	}

//	public static void main(String[] args) throws IOException, InterruptedException {
//	MainWeb mw = new MainWeb();
//		mw.parcours1();
//		mw.parcours2();
//
//		UploadInAzureDevOps("testindependant");
//
//		//UploadInAzureDevOps("test2");
//
//		//RunKaliosTestPlan("essai2023","adda3de35af2f5c2d0efed716c0e8e8f" ,"simple", "https://magento.softwaretestingboard.com/");
//		//RunKaliosTestPlan("local3","6441e0bd46b4324777f4803416e3f219" ,"percy", "http://127.0.0.1:5500/");
//		//TextEngine.printSteps();
//		//RunKaliosTestPlan("test20231023","f910a1a8eff9c2dcfa1b20cc8bc1cc4e" ,"exemple_parcours", "https://magento.softwaretestingboard.com/");
//		//RunKaliosTestPlanBrowserStack();
//	}
}
