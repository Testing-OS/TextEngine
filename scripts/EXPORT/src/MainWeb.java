package scripts_textengine;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class MainWeb {

//@Test
//public void parcours1() throws IOException, InterruptedException {
//
//	initWeb("http://www.test.kalios-saas.com/automationpractice/index.html");
//	clickbytext("Start");
//	waitloadingcomplete(null,"3");
//	clickbytext("Valider");
//	close("");
//	sa.assertAll();
//	}
	public static void main(String[] args) {
		RunKaliosTestPlan("essai2023","adda3de35af2f5c2d0efed716c0e8e8f" ,"percy", "http://127.0.0.1:5500/");
		//RunKaliosTestPlanBrowserStack();
	}
}