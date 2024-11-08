package runners.BrowserStack;
import runners.TestRunner.commandline.TestRunnerCommandLineRunner;
import runners.TestRunner.testexecutor.TestExecutorService;
public class BrowserStackLauncher {
    //---------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static String NomInstance="";
    public static String Token="";
    public static String TestPlanName="";
    public static String BrowserStackSDK="";
    public static String URL="";
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        TestRunnerCommandLineRunner TestRunner = new TestRunnerCommandLineRunner(new TestExecutorService());
        System.out.println("Telechargement");
        TestRunner.run(NomInstance, Token, TestPlanName);
        try{
        	Thread.sleep(5000);
        }catch (Exception e){
        }
    }
}
