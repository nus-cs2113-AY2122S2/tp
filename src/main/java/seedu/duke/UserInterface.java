package seedu.duke;

import seedu.duke.CommandParsers.*;
import util.exceptions.InvalidFileException;
import util.exceptions.InvalidObjectType;
import util.exceptions.NullException;
import util.exceptions.WrongCommandException;

import java.util.Scanner;

public class UserInterface {
    private Warehouse warehouse;

    private ListParser  listParser;
    private ViewParser viewParser;
    private AddParser addParser;
    private RemoveParser removeParser;
    private TotalParser totalParser;
    private FindParser findParser;


    public UserInterface(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.listParser = new ListParser(warehouse);
        this.findParser = new FindParser(warehouse);
        this.viewParser = new ViewParser(warehouse);
        this.addParser = new AddParser(warehouse);
        this.removeParser = new RemoveParser(warehouse);
        this.totalParser = new TotalParser(warehouse);
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();


        // setting Warehouse capacity
        /*
        boolean isSet = false;
        do {
            Regex capacity = new Regex(userInput, "(?<cap>\\d*)");
            isSet = warehouse.setCapacity(capacity.getGroupValues().get("cap"));
            userInput = input.nextLine();
        } while (!isSet);
        */

        while (!userInput.equals("bye")) {
            // current implementation is just take 1st value for command
            try {
                String command = userInput.split(" ")[0];
                switch (command) {
                case "view":
                    //using flags here to distinguish between different views????
                    viewParser.parse(userInput);
                    break;
                case "find":
                    findParser.parse(userInput);
                    break;
                case "list":
                    listParser.parse(userInput);
                    break;
                case "add":
                    addParser.parse(userInput);
                    break;
                case "remove":
                    removeParser.parse(userInput);
                    break;
                case "total":
                    totalParser.parse(userInput);
                    break;
                case "help":
                    Display.help();
                    break;
                case "storage-capacity":
                    warehouse.getPercentOccupied();
                    break;
                default:
                    //error exception here
                    throw new WrongCommandException("", false);
                }
            } catch (WrongCommandException wrongCommandException) {
                if (wrongCommandException.isCommand()) {
                    String wrongCommand = wrongCommandException.getCommand();
                    System.out.printf("%s command was used wrongly. Type help to see examples\n",
                            wrongCommand);
                    Display.help();
                } else {
                    System.out.println("No such command. Type help to see examples");
                    Display.help();
                }
            } catch (NullException nullException) {
                //catch null exception here
                Display.tryCommandAgain();
            } catch (InvalidFileException e) {
                e.printStackTrace();
            } catch (InvalidObjectType e){
                Display.tryCommandAgain();
            }
            System.out.println("Another command?");
            userInput = input.nextLine();
        }
    }

}
