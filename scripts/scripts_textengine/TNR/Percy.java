package scripts_textengine.TNR;

import java.io.IOException;
import static scripts_textengine.TextEngine.*;

public class Percy {
    public static void main(String[] args) throws IOException {
        initWeb("https://magento.softwaretestingboard.com/", "parcours_magento");
        hoverbytext("Women");
        hoverbytext("Tops");
        clickbytext("Jacket");
        visualtesting("", "Tops-Jackets");
        close("");
        getPercyResult();
    }
}
