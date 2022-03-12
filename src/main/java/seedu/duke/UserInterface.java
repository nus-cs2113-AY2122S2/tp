package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    private static Scanner input;
    private static String userInput;
    private static ArrayList<Goods> userGoods = new ArrayList<>();

    public static void run() {
        input = new Scanner(System.in);
        userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            // current implementation is just take 1st value for command
            // i am using regex for my own function for now, but can change
            // when more is done
            String command = userInput.split(" ")[0];
            switch (command) {
            case "add":
                String regex = "id/(?<id>\\d*) n/(?<name>.*) q/(?<qty>\\d*)";
                Regex regexMatch = new Regex(userInput, regex);
                HashMap<String, String> matches = regexMatch.getGroupValues();
                Commands.addGood(matches.get("id"), matches.get("name"),
                        matches.get("qty"), userGoods);
                break;
            case "view":
                regex = "id/(?<id>\\d*)";
                regexMatch = new Regex(userInput, regex);
                matches = regexMatch.getGroupValues();
                Commands.viewGood(matches.get("id"), userGoods);
                break;
            case "list":
                Commands.listGoods(userGoods);
                break;
            case "remove":
                String format = "id/(?<id>\\d*) q/(?<qty>\\d*)";
                Regex match = new Regex(userInput, format);
                HashMap<String, String> inputValues = match.getGroupValues();
                Commands.removeGood(inputValues.get("id"), inputValues.get("qty"), userGoods);
                break;
            default:
                //error exception here
                break;
            }
            System.out.println("Another command?");
            userInput = input.nextLine();
        }
    }
}
