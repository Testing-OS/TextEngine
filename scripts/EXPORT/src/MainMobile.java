package scripts_textengine;

import runners.TestRunner.commandline.TestRunnerCommandLineRunner;
import runners.TestRunner.testexecutor.TestExecutorService;

import java.io.IOException;

public class MainMobile {

    public static void main(String[] args) throws IOException, InterruptedException {

        TextEngine.initMobileApp("C:/Android/app-release.apk", "parcours 4");
        TextEngine.clickbytext("send keys by text");
        TextEngine.sendkeysbytext("Hint in PlainText", "kalios test");

        TextEngine.ShowResult();





    }
}
