package scripts_textengine.TNR;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class PageActions {
	static String url = "http://www.test.kalios-saas.com/automationpractice/index.html";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		PageActions pageActions = new PageActions();
		//pageActions.pageActions();
		checkPDF();
		ShowResult();
	}
	
	@Test
	public void pageActions() throws IOException, InterruptedException {
		checkCSV();
		ChangeParcours("checkpdf");
		checkPDF();
		ChangeParcours("executerbat");
		executerBAT();
		ChangeParcours("waitloadingcomplete");
		waitLoadingComplete();
		ChangeParcours("checkinnertext");
		checkInnerText();
		ChangeParcours("activateframe");
		activateFrame();
		ChangeParcours("activatetab");
		activateTab();
		sa.assertAll();
	}

	public static void checkCSV() throws IOException, InterruptedException {
		initWeb(url, "checkcsv");
		checkcsv("", "JournaldesVentes.csv|C:/Users/User/Downloads/JournalDesVentes1.csv");
		close("");
	}
	
	public static void checkPDF() throws IOException, InterruptedException {
		initWeb(url, "checkpdf");
		checkpdf("", "C:/Users/User/Downloads/facturepdf.pdf|facturetxt.txt");
		close("");
	}
	
	public static void executerBAT() throws IOException, InterruptedException {
		initWeb(url, "executerbat");
		executer_bat("", "fichiercommand.bat");
		close("");
	}
	
	public static void waitLoadingComplete() throws IOException, InterruptedException {
		initWeb(url, "waitloadincomplete");
		waitloadingcomplete("Automation practice", "");
		close("");
	}
	
	public static void checkInnerText() throws IOException, InterruptedException {
		initWeb(url, "checkinnertext");
		checkinnertext("Automation practice", "WELCOME TO AUTOMATION PRACTICE");
		close("");
	}
	
	public static void activateFrame() throws IOException, InterruptedException {
		initWeb(url, "activateframe");
		selectbytext("Select Component", "ActivateFrame");
		activateframe("", "0");
		clickbytext("bouton");
		desactivateframe("", "");
		clickbytext("Click me");
		close("");
	}
	
	public static void activateTab() throws IOException, InterruptedException {
		initWeb(url, "activatetab");
		selectbytext("Select Component", "ActivateTab");
		activatetab("", "Activate Tab");
		clickbytext("bouton");
		sendkeysbytext("input", "test");
		close("");
	}
}
