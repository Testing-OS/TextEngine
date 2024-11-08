package scripts_textengine;

import java.io.IOException;

public class MainPercy {
    public static void main(String[] args) throws IOException {
        TextEngine.initWeb("https://magento.softwaretestingboard.com/", "parcours_magento");
        TextEngine.hoverbytext("Women");
        TextEngine.hoverbytext("Tops");
        TextEngine.clickbytext("Jacket");
        TextEngine.visualtesting("", "Tops-Jackets");
        TextEngine.close("");
        TextEngine.getPercyResult();
    }
}
