package scripts_textengine;


import java.io.IOException;

import org.junit.jupiter.api.Test;

import runners.BrowserStack.BstackRunner;

import static scripts_textengine.TextEngine.*;

public class MainBrowserStack extends BstackRunner{
	
	@Test
	public void parcours() throws IOException, InterruptedException {
        initBrowserStack("https://magento.softwaretestingboard.com/","parcours 1");
        hoverbytext("Women");
        hoverbytext("Tops");
        clickbytext("Jacket");
        sa.assertAll();
	}
	
	public static void main(String[] args) {RunKaliosTestPlanBrowserStack();}
   
} 
