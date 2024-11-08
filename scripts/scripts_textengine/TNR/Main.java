package scripts_textengine.TNR;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class Main {
public static void main(String[] args) throws IOException, InterruptedException {
	ClickByText clickByText = new ClickByText();
	clickByText.clickByText();
	
	SendKeysByText sendKeysByText = new SendKeysByText();
	sendKeysByText.sendKeysByText();
	
	CheckByText checkByText = new CheckByText();
	checkByText.checkByText();
	
	PropertiesActions propertiesActions = new PropertiesActions();
	propertiesActions.propertiesActions();
	
	PageActions pageActions = new PageActions();
	pageActions.pageActions();
	
	TableActions tableActions = new TableActions();
	tableActions.tableActions();
}

@Test
public void clickbytext() throws IOException, InterruptedException {
	ClickByText clickByText = new ClickByText();
	clickByText.clickByText();
	sa.assertAll();
}

@Test
public void sendkeysbytest() throws IOException, InterruptedException {
	SendKeysByText sendKeysByText = new SendKeysByText();
	sendKeysByText.sendKeysByText();
	sa.assertAll();
}

@Test
public void checkbytext() throws IOException, InterruptedException {
	CheckByText checkByText = new CheckByText();
	checkByText.checkByText();
	sa.assertAll();
}

@Test
public void propertiesactions() throws IOException, InterruptedException {
	PropertiesActions propertiesActions = new PropertiesActions();
	propertiesActions.propertiesActions();
	sa.assertAll();
}

@Test
public void pageActions() throws IOException, InterruptedException {
	PageActions pageActions = new PageActions();
	pageActions.pageActions();
	sa.assertAll();
}

@Test
public void tableActions() throws IOException, InterruptedException {
	TableActions tableActions = new TableActions();
	tableActions.tableActions();
	sa.assertAll();
}
}
