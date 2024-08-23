package runners.BrowserStack;

import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.JavascriptExecutor;

import scripts_textengine.TextEngine;

public class MyTestWatcher extends BstackRunner implements TestWatcher  {
	

    @Override
    public void testSuccessful(ExtensionContext context) {
        driver = TextEngine.selfdriver;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if((!TextEngine.TestPlan) && TextEngine.cloud.equals("BrowserStack"))
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"OK\"}}");
        	
    }
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        driver = TextEngine.selfdriver;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if((!TextEngine.TestPlan) && TextEngine.cloud.equals("BrowserStack")) {
        try {
            String[] Error = TextEngine.getResult().get(TextEngine.getResult().size() - 1);
            String Message = Error[0] + "-" + Error[1] + "-" + Error[2];
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"" + Message + "\"}}");
        }catch (Exception e){
            jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"" + "KO2" + "\"}}");

        }
       }
    }
	@Override
	public void testAborted(ExtensionContext arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void testDisabled(ExtensionContext arg0, Optional<String> arg1) {
		// TODO Auto-generated method stub
		
	}
}