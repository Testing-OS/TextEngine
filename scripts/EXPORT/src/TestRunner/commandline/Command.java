package runners.TestRunner.commandline;

import java.util.stream.Stream;

public enum Command {
    TEST_CASE("1", "testcase"),
    TEST_PLAN("2", "testplan"),
    TEST_PLAN_LOAD("3", "testplan_load");

    private final String commandNumber;
    private final String commandName;

    Command(String commandNumber, String commandName) {
        this.commandNumber = commandNumber;
        this.commandName = commandName;
    }

    public String commandNumber() {
        return commandNumber;
    }

    public String commandName() {
        return commandName;
    }

    public static Command fromName(String commandName) {
        return Stream.of(Command.values())
                .filter(command -> command.commandName.equals(commandName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid command: " + commandName));
    }

    public static Command fromNumber(String commandNumber) {
        return Stream.of(Command.values())
                .filter(command -> command.commandNumber.equals(commandNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid command: " + commandNumber));
    }

}
