package scripts_textengine;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import static scripts_textengine.TextEngine.*;

public class MainResolution {


    @Test
    public void parcours1() throws IOException, InterruptedException {

        initWebResolution("http://www.test.kalios-saas.com/automationpractice/index.html", 700, 850);
        clickbytext("Start");
        waitloadingcomplete(null,"3");
        clickbytext("Valider");
        close("");
        sa.assertAll();
    }



}

