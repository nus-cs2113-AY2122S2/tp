package werkIt;

import commands.Command;
import commands.ExitCommand;
import commands.InvalidCommandException;
import data.exercises.ExerciseList;
import data.workouts.WorkoutList;

import java.io.IOException;

public class WerkIt {
    private UI ui;
    private Parser parser;
    private ExerciseList exerciseList;
    private WorkoutList workoutList;

    /**
     * Initialises the components of the WerkIt! application.
     */
    public WerkIt() {
        // Initialise Components
        ui = new UI();
        parser = new Parser();
        exerciseList = new ExerciseList();
        workoutList = new WorkoutList();

        ui.printGreetings();

        // Do file imports
        System.out.println("(WIP) File import messages will go here");

        try {
            exerciseList.loadExercises();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initialiseComponents() {
        ui = new UI();
        parser = new Parser();
        exerciseList = new ExerciseList();
        workoutList = new WorkoutList();
    }

    /**
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
