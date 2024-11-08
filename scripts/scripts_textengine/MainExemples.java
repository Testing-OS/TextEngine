package scripts_textengine;

import static scripts_textengine.TextEngine.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MainExemples {
	
	// Il est possible de réutiliser des enchaînements d'actions dans plusieurs parcours
	public static void womenTopsJacket() throws IOException, InterruptedException {
		hoverbytext("Women");
		hoverbytext("Tops");
		clickbytext("Jackets");
	}
	
	public static void xsBlue() throws IOException, InterruptedException {
		clickbytext("XS");
		clickbytext("blue");
	}
	
	public static void parcours1() throws IOException, InterruptedException {
		initWeb("https://magento.softwaretestingboard.com/", "parcours1");
		
		womenTopsJacket(); //On appelle un groupe d'actions utilisées dans plusieurs parcours
		
		clickbytext("Juno Jacket");
		
		xsBlue();
		
		clickbytext("Add to cart");
		clickbytext("Shopping cart");
		
		checkbytext("Order total", "77.00");
	}
	
	public static void parcours2() throws IOException, InterruptedException {
		initWeb("https://magento.softwaretestingboard.com/", "parcours2");
		
		womenTopsJacket();
		
		clickbytext("Juno Jacket");
		
		xsBlue();
		
		clickbytext("Add to cart");
		clickbytext("My cart");
		clickbytext("View and Edit Cart");
		
		checkbytext("Order total", "77.00");
	}
	
	public static void parcours3() throws IOException, InterruptedException {
		initWeb("https://magento.softwaretestingboard.com/", "parcours3");
		
		womenTopsJacket();
		
		clickbytext("[veste]"); // On appelle le datapool pour cliquer sur la veste
		
		xsBlue();

		clickbytext("Add to cart");
		clickbytext("Shopping cart");
		
		checkbytext("Order total", "$prix"); // On appelle une variable pour vérifier le prix
	}
	
	public static void parcours_upload_Jira() throws IOException, InterruptedException {
		parcours1();
		createJiraTicket("parcours1");
		// On peut exécuter un parcours puis uploader les résultats dans un ticket Jira. 
		// Il est possible d'appeler createJiraTicket() à la suite du parcours
		// Ici, on va simplement exécuter le parcours1 puis uploader ses résultats
		// Il faut aller remplir les différentes variables dans le fichier Config.java
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException{
		parcours1();
		ChangeParcours("parcours2");
		parcours2();
		ChangeParcours("parcours3");
		parcours3();
		ChangeParcours("parcours_upload_Jira");
		parcours_upload_Jira();
	}
	
	
}
