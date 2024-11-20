package scripts_textengine;

import static scripts_textengine.TextEngine.*;

import java.io.IOException;

import scripts_techniques.*;

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
		for (int i = Config.compteur_instance; i < Fonctions.get_lines_parameters("parcours2"); i++, Config.compteur_instance++, Config.compteur_params = 1) {
			// Cette boucle permet de lancer plusieurs fois le parcours, une éxecution par jeu de valeur
			
			initWeb("https://magento.softwaretestingboard.com/", "parcours2");
			
			womenTopsJacket();
			
			clickbytext("Juno Jacket");
			
			xsBlue();
			
			clickbytext("Add to cart");
			clickbytext("My cart");
			clickbytext("View and Edit Cart");
			
			checkbytext("Order total", "77.00");
			
			sendkeysbytext("Search", "JEUVALEUR"); //On appelle ici le jeu de valeur pour saisir une valeur
			myExempleCodedAction("");
		}
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
	
	
	public static void main(String[] args) throws IOException, InterruptedException{
		parcours1();
		parcours2();
		parcours3();
	}
	
	
}
