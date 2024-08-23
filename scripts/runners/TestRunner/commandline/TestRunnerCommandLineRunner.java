package runners.TestRunner.commandline;

import runners.TestRunner.testexecutor.TestExecutorService;
import runners.TestRunner.testexecutor.ZipHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scripts_techniques.Config;


import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static runners.TestRunner.commandline.Command.*;

public class TestRunnerCommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TestRunnerCommandLineRunner.class);
    private final TestExecutorService testExecutorService;

    public TestRunnerCommandLineRunner(TestExecutorService testExecutorService) {
        this.testExecutorService = testExecutorService;
    }


    public void run(String instance, String Token, String TestPlanName) {
        String[] args = {instance, Token,TestPlanName};
        try {
            execute(args);
        } catch (Throwable e) {
            String message = getMessage(e);
            e.printStackTrace();
            LOG.error("Something went wrong: {}", message);
        }
    }

    private String getMessage(Throwable e) {
        String message = e.getMessage();
        Throwable cause = e.getCause();

        while (message == null && cause != null) {
            message = cause.getMessage();
            if (message == null) {
                cause = e.getCause();
            }
        }
        return message;
    }

    private void execute(String[] args) throws IOException, InterruptedException {

        String Instance = args[0];
        String Token = args[1];

        boolean isScriptExecution = false;
        String testPlanName = args[2].replace(" ", "%20");
        Command executionType = TEST_PLAN;

        String extension = ".bat";
        String osExecution = "win";

        String scriptsFolder = Config.tunnel_path+ "/scripts";
        createFolderStructure(scriptsFolder);

        String resultsFolder = Config.dir_export;

        createFolderStructure(resultsFolder + "/planTests");

        testExecutorService.Download(createTestPlanUrl(Instance, testPlanName, Token, osExecution), scriptsFolder, testPlanName, "testplan", extension, false);


    }

    public void Upload(String Instance, String Token){
        if (TEST_PLAN.commandName().equals("testplan")) {
            TestExecutorService.createTxtFileWithImageNames(Config.tunnel_path+ "/scripts");
            ZipHelper.archiveResultFolder(Config.tunnel_path+ "/scripts");
        }
        File fileToUpload = getLatestFilefromDir(Config.tunnel_path + "/results");
        assert fileToUpload != null;
        testExecutorService.upload(createUploadUrl(Instance, Token, TEST_PLAN), fileToUpload.toString());

    }
    public void Upload(String Instance, String Token, int X){
        if (TEST_PLAN.commandName().equals("testplan")) {
            TestExecutorService.createTxtFileWithImageNames(Config.tunnel_path+ "/scripts");
            ZipHelper.archiveResultFolders(Config.tunnel_path+ "/scripts", X);
        }
        List<File> filesToUpload = new ArrayList<>(ZipHelper.getXLastModifiedFile(Config.tunnel_path + "/results", X));

        for (File fileToUpload :
                filesToUpload) {
            assert fileToUpload != null;
            testExecutorService.upload(createUploadUrl(Instance, Token, TEST_PLAN), fileToUpload.toString());
        }

    }

    private static Command getExecutionType(Console console) {
        return Command.fromNumber(
                readFromConsole(
                        console,
                        "Execution type (1 for path, 2 for testplan, 3 for loadtests): ",
                        s -> s.equalsIgnoreCase(TEST_CASE.commandNumber()) || s.equalsIgnoreCase(TEST_PLAN.commandNumber()) || s.equalsIgnoreCase(TEST_PLAN_LOAD.commandNumber())
                )
        );
    }

    private File getLatestFilefromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }


    public boolean hasExtension(String filename) {
        return filename.contains(".");
    }

    public boolean hasDoubleExtension(String filename) {
        return filename.contains(".jpg.jpg");
    }

    public boolean isShExecution(String[] args) {
        return Arrays.stream(args).anyMatch(arg -> arg.equalsIgnoreCase("sh"));
    }

    private static String readFromConsole(Console console, String message, Predicate<String> predicate) {
        boolean isCorrect = false;
        String executionType = null;
        while (!isCorrect) {
            executionType = console.readLine(message);
            isCorrect = predicate.test(executionType);
        }
        return executionType;
    }

    private void createFolderStructure(String pathToCreate) {

        File directory = new File(pathToCreate);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }


    private static String createTestPlanUrl(String instance, String testPlan, String token, String osExecution) {
        testPlan = testPlan.replace(" ", "%20");
        if (Config.Type == Config.TYPE_Instance.LOCAL)
            return "http://" + instance + ".kalioslocal.com/web-instance/web/api/generate-testplan?" + "testplanName=" + testPlan + "&token=" + token + "&osExecution=" + osExecution;
        if (Config.Type == Config.TYPE_Instance.PREPROD)
            return "http://" + instance + ".test.kalios-saas.com/web-instance/web/api/generate-testplan?" + "testplanName=" + testPlan + "&token=" + token+ "&osExecution=" + osExecution;
        if (Config.Type == Config.TYPE_Instance.PROD)
            return "http://" + instance + ".prod.kalios-saas.com/web-instance/web/api/generate-testplan?" + "testplanName=" + testPlan + "&token=" + token+ "&osExecution=" + osExecution;
        else
            return null;
    }

    private static String createTestCaseUrl(String instance, String feature, String testCase, String token, String osExecution) {
        return "http://" + instance + ".kalioslocal.com/web-instance/web/api/generate-testcase?" + "featureName=" + feature + "&testcaseName=" + testCase + "&token=" + token + "&osExecution=" + osExecution;
        //return "http://" + instance + ".test.kalios-saas.com/web-instance/web/api/generate-testcase?" + "featureName=" + feature + "&testcaseName=" + testCase + "&token=" + token+ "&osExecution=" + osExecution;
        // return "http://" + instance + ".prod.kalios-saas.com/web-instance/web/api/generate-testcase?" + "featureName=" + feature + "&testcaseName=" + testCase + "&token=" + token+ "&osExecution=" + osExecution;
    }

    private static String createUploadUrl(String instance, String token, Command command) {
        if (Config.Type == Config.TYPE_Instance.LOCAL)
            return "http://" + instance + ".kalioslocal.com/web-instance/web/api/public/api-import-test-results?token=" + token + "&resultType=" + command.commandName();
        if (Config.Type == Config.TYPE_Instance.PREPROD)
            return "http://" + instance + ".test.kalios-saas.com/web-instance/web/api/public/api-import-test-results?token=" + token + "&resultType=" + command.commandName();
        if (Config.Type == Config.TYPE_Instance.PROD)
            return "http://" + instance + ".prod.kalios-saas.com/web-instance/web/api/public/api-import-test-results?token=" + token + "&resultType=" + command.commandName();
        else
            return null;
    }

    private static String createGetTestcaseUrl(String instance, String feature, String testCase, String token) {
        return "http://" + instance + ".kalioslocal.com/web-instance/web/api/get-testcase?" + "featureName=" + feature + "&testcaseName=" + testCase + "&token=" + token;
        // LOG.info("createGetTestcaseUrl: " + "http://" + instance + ".test.kalios-saas.com/web-instance/web/api/get-testcase?" + "featureName=" + feature + "&testcaseName=" + testCase + "&token=" + token);
        // return "http://" + instance + ".test.kalios-saas.com/web-instance/web/api/get-testcase?" + "featureName=" + feature + "&testcaseName=" + testCase + "&token=" + token;
        // return "http://" + instance + ".prod.kalios-saas.com/web-instance/web/api/get-testcase?" + "featureName=" + feature + "&testcaseName=" + testCase + "&token=" + token;
    }

}
