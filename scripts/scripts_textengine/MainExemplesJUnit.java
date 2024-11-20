package scripts_textengine;

import static scripts_textengine.TextEngine.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class MainExemplesJUnit {
	
	// Il est possible de réutiliser des enchaînements d'actions dans plusieurs parcours
	public void womenTopsJacket() throws IOException, InterruptedException {
		hoverbytext("Women");
		hoverbytext("Tops");
		clickbytext("Jackets");
	}
	
	public void xsBlue() throws IOException, InterruptedException {
		clickbytext("XS");
		clickbytext("blue");
	}
	
	@Test
	public void parcours1() throws IOException, InterruptedException {
		initWeb("https://magento.softwaretestingboard.com/", "parcours1");
		
		womenTopsJacket(); //On appelle un groupe d'actions utilisées dans plusieurs parcours
		
		clickbytext("Juno Jacket");
		
		xsBlue();
		
		clickbytext("Add to cart");
		clickbytext("Shopping cart");
		
		checkbytext("Order total", "77.00");
		sa.assertAll();
	}
	
	@Test
	public void parcours2() throws IOException, InterruptedException {
		initWeb("https://magento.softwaretestingboard.com/", "parcours2");
		
		womenTopsJacket();
		
		clickbytext("Juno Jacket");
		
		xsBlue();
		
		clickbytext("Add to cart");
		clickbytext("My cart");
		clickbytext("View and Edit Cart");
		
		checkbytext("Order total", "77.00");
		sa.assertAll();
	}
	
	@Test
	public void parcours3() throws IOException, InterruptedException {
		initWeb("https://magento.softwaretestingboard.com/", "parcours3");
		
		womenTopsJacket();
		
		clickbytext("[veste]"); // On appelle le datapool pour cliquer sur la veste
		
		xsBlue();

		clickbytext("Add to cart");
		clickbytext("Shopping cart");
		
		checkbytext("Order total", "$prix"); // On appelle une variable pour vérifier le prix
		sa.assertAll();
	}
}
