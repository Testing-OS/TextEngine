package scripts_textengine.TNR;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class TableActions {
	static String url = "http://www.test.kalios-saas.com/automationpractice/index.html";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		TableActions tableActions = new TableActions();
		tableActions.tableActions();
	}
	
	@Test
	public void tableActions() throws IOException, InterruptedException {
		clickTable();
		ChangeParcours("sendkeystable");
		sendKeysTable();
		ChangeParcours("checktable");
		checkTable();
		sa.assertAll();
	}
	
	public static void clickTable() throws IOException, InterruptedException {
		initWeb(url, "clicktable");
		selectbytext("Select Component", "Table");
		clicktable("Table", "Action|SEO tags");
		close("");
	}
	
	public static void sendKeysTable() throws IOException, InterruptedException {
		initWeb(url, "sendkeystable");
		selectbytext("Select Component", "Table");
		sendkeystable("Table", "Quantity|Holden Charles|5");
		close("");
	}
	
	public static void checkTable() throws IOException, InterruptedException {
		initWeb(url, "checktable");
		selectbytext("Select Component", "Table");
		checktable("Table", "Quantity|Holden Charles|0");
		close("");
	}
}
