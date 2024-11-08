package scripts_textengine;

public class TestCaseResult {
    private String testCaseTitle;
    private String automatedTestName;
    private TestCase TestCase;
    private String outcome;

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    private String errorMessage;
    private String state;
    private String computerName;
    private String startedDate;
    private String completedDate;
    private String comment;
    public TestCase getTestcase() {
        return TestCase;
    }

    public void setTestcase(TestCase testcase) {
        this.TestCase = testcase;
    }

    public String getOutcome() {
        return outcome;
    }

    public TestCaseResult(String testCaseTitle, String automatedTestName, String outcome, String computerName, String errorMessage, String comment) {
        this.testCaseTitle = testCaseTitle;
        TestCase = new TestCase(Integer.parseInt(automatedTestName));
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

    public String getComment() {
        return comment;
    }

    public static class TestCase{
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private int id;
        public TestCase(int id){
            this.id = id;
        }


    }
}