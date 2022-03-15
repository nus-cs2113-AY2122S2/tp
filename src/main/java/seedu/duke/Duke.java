package seedu.duke;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    SatisfactionList satisfactionList = new SatisfactionList();

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
                command.execute(satisfactionList);
                shouldExitProgram = command.isExit();
            } catch (WrongCommandException error) {
                System.out.println(error.getMessage());
            } catch (HotelLiteManagerException e) {
                ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
