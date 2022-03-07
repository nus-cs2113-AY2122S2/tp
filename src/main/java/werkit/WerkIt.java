package werkit;

import commands.Command;
import commands.ExitCommand;
import commands.InvalidCommandException;
import data.exercises.ExerciseList;
import data.workouts.WorkoutList;

import java.io.IOException;

/**
 * This class initiates the various classes/components of WerkIt! and contains the logic code for
 * prompting the user for an input continuously until the user enters the exit command.
 */
public class WerkIt {
    private UI ui;
    private Parser parser;
    private ExerciseList exerciseList;
    private WorkoutList workoutList;

    /**
     * Initialises the components of the WerkIt! application, greets the user, and loads the
     * various files stored in the system's local disk into this instance of WerkIt! (if applicable).
     */
    public WerkIt() {
        // Initialise Components
        ui = new UI();
        exerciseList = new ExerciseList();
        workoutList = new WorkoutList(exerciseList);
        parser = new Parser(ui, exerciseList, workoutList);

        ui.printGreetings();

        // Do file imports
        System.out.println("(WIP) File import messages will go here");

        try {
            exerciseList.loadExercises();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Continuously prompts the user for an input (and thereafter executing the necessary
     * actions) until the exit command is entered.
     * <p>
     * Method adapted from Team Member Alan Low's iP codebase.
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/Sora.java
     */
    public void startContinuousUserPrompt() {
        boolean userWantsToExit = false;
        boolean isFirstPrompt = true;

        do {
            try {
                ui.printUserInputPrompt(isFirstPrompt);
                isFirstPrompt = false;
                String userInput = ui.getUserInput();
                Command newCommand = parser.parseUserInput(userInput);

                if (newCommand instanceof ExitCommand) {
                    userWantsToExit = true;
                    continue;
                }

                newCommand.execute();

            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
            }
        } while (!userWantsToExit);

        // User is exiting the program
        ui.printGoodbye();
    }
}
