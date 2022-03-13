package seedu.duke;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private void run() {
        CommandParser commandParser = new CommandParser();
        boolean shouldExitProgram = false;
        Ui ui = new Ui();
        String userInput;
        while (!shouldExitProgram) {
            try {
                userInput = ui.readUserInput();
                Command command = commandParser.parse(userInput);
                command.execute();
                shouldExitProgram = command.isExit();
            } catch (WrongCommandException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
