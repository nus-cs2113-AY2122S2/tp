package werkIt;

import commands.Command;
import commands.InvalidCommandException;

public class WerkIt {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        // Initialise Components
        UI ui = new UI();
        


        String userInput = "workout /wut russian twist /reps 1";
        Parser parser = new Parser();
        try {
            Command newCommand = parser.parseUserInput(userInput);
            newCommand.execute();
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getThrowingClass());
        }
    }
}
