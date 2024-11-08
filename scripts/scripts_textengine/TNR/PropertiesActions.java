package scripts_textengine.TNR;


import java.io.IOException;
import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class PropertiesActions {
	static String url = "http://www.test.kalios-saas.com/automationpractice/index.html";

	public static void main(String[] args) throws IOException, InterruptedException {
		PropertiesActions propertiesActions = new PropertiesActions();
		propertiesActions.propertiesActions();
	}
	
	@Test
	public void propertiesActions() throws IOException, InterruptedException {
		checkById();
		ChangeParcours("checkByName");
		checkByName();
		ChangeParcours("checkByXpath");
		checkByXpath();
		ChangeParcours("clickbyid_bouton");
		clickbyid_bouton();
		ChangeParcours("clickbyname_bouton");
		clickbyname_bouton();
		ChangeParcours("clickbyxpath_bouton");
		clickbyxpath_bouton();
		ChangeParcours("sendkeysById");
		sendkeysById();
		ChangeParcours("sendkeysByName");
		sendkeysByName();
		ChangeParcours("sendkeysByXpath");
		sendkeysByXpath();
		sa.assertAll();
	}

	public static void checkById() throws IOException, InterruptedException {
		initWeb(url, "checkbyid");
		selectbytext("Select Component", "CheckByIdNameXpath");
		checkbyid("checkid", "Prénom");
		close("");
	}
	
	public static void checkByName() throws IOException, InterruptedException {
		initWeb(url, "checkbyname");
		selectbytext("Select Component", "CheckByIdNameXpath");
		checkbyname("checkname", "Nom");
		close("");
	}
	
	public static void checkByXpath() throws IOException, InterruptedException {
		initWeb(url, "checkbyxpath");
		selectbytext("Select Component", "CheckByIdNameXpath");
		checkbyxpath("//*[@id='colonnexpath']//li/input", "E-mail");
		close("");
	}
	
	
	public static void clickbyid_bouton() throws IOException, InterruptedException {
		initWeb(url, "clickbyid_bouton");
		selectbytext("Select Component", "ClickByIdNameXpath");
		clickbyid("boutonid");
		close("");
	}
	
	public static void clickbyname_bouton() throws IOException, InterruptedException {
		initWeb(url, "clickbyname_bouton");
		selectbytext("Select Component", "ClickByIdNameXpath");
		clickbyname("boutonname");
		close("");
	}
	
	public static void clickbyxpath_bouton() throws IOException, InterruptedException {
		initWeb(url, "clickbyxpath_bouton");
		selectbytext("Select Component", "ClickByIdNameXpath");
		clickbyxpath("//*[@id=\"colonnexpath\"]/div/li[1]/span/button");
		close("");
	}
	
	public static void sendkeysById() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbyid");
		selectbytext("Select Component", "SendKeysByIdNameXpath");
		sendkeysbyid("inputtextid", "test");
		close("");
	}
	
	public static void sendkeysByName() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbyname");
		selectbytext("Select Component", "SendKeysByIdNameXpath");
		sendkeysbyname("inputtextname", "test");
		close("");
	}
	
	public static void sendkeysByXpath() throws IOException, InterruptedException {
		initWeb(url, "sendkeysbyxpath");
		selectbytext("Select Component", "SendKeysByIdNameXpath");
		sendkeysbyxpath("//*[@id=\"colonnexpath\"]/div/li/label/input", "test");
		close("");
	}
}
