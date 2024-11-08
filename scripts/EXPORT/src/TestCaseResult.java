package scripts_textengine;

public class TestCaseResult {
    private String testCaseTitle;
    private String automatedTestName;
    private String outcome;
    private String errorMessage;
    private String state;
    private String computerName;
    private String startedDate;
    private String completedDate;
    private String comment;

    public String getOutcome() {
        return outcome;
    }

    public TestCaseResult(String testCaseTitle, String automatedTestName, String outcome, String computerName, String errorMessage, String comment) {
        this.testCaseTitle = testCaseTitle;
        this.automatedTestName = automatedTestName;
        this.outcome = outcome;
        this.state = "Completed";
        this.computerName = computerName;
        this.errorMessage = errorMessage;
        this.comment = comment;

    }

    @Override
    public String toString() {
        return testCaseTitle +" "+ state;
    }
    public String getTestCaseTitle(){
        return testCaseTitle;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
}