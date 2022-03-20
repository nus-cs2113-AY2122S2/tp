package seedu.duke;

import util.exceptions.NullException;
import util.exceptions.WrongCommandException;

import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    private Warehouse warehouse;

    public UserInterface(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        try {
            while (!userInput.equals("bye")) {
                // current implementation is just take 1st value for command
                String command = userInput.split(" ")[0];
                switch (command) {
                case "view":
                    //using flags here to distinguish between different views????
                    String flag = userInput.split(" ")[1];
                    String regex = "id/(?<id>\\d*)";
                    Regex regexMatch = new Regex(userInput, regex);
                    HashMap<String, String> matches = regexMatch.getGroupValues();
                    if (flag.equals("-o")) {
                        // view order with flag "-o"
                        warehouse.viewOrder(matches.get("id"));
                    } else if (flag.equals("-g")) {
                        // view good with flag "-g"
                        warehouse.viewGood(matches.get("id"));
                    } else {
                        // wrong command exception
                        throw new WrongCommandException("view", true);
                    }
                    break;
                case "list":
                    flag = userInput.split(" ")[1];
                    regex = "id/(?<id>\\d*)";
                    regexMatch = new Regex(userInput, regex);
                    matches = regexMatch.getGroupValues();
                    if (flag.equals("-o")) {
                        // list orders with flag "-o"
                        warehouse.listOrders();
                    } else if (flag.equals("-g")) {
                        // list goods with flag "-g"
                        warehouse.listGoods();
                    } else {
                        // wrong command exception
                        throw new WrongCommandException("list", true);
                    }
                    break;
                case "add":
                    flag = userInput.split(" ")[1];

                    if (flag.equals("-o")) {
                        regex = "id/(?<id>\\d*) r/(?<r>.*) a/(?<address>.*)";
                        regexMatch = new Regex(userInput, regex);
                        matches = regexMatch.getGroupValues();
                        warehouse.addOrder(matches.get("id"), matches.get("r"), matches.get("address"));

                    } else if (flag.equals("-g")) {
                        regex = "oid/(?<oid>\\d*) gid/(?<gid>\\d*) n/(?<name>.*) q/(?<qty>\\d*)"
                                + " d/(?<desc>\\.*";
                        regexMatch = new Regex(userInput, regex);
                        matches = regexMatch.getGroupValues();
                        warehouse.addGoods(matches.get("oid"), matches.get("gid"), matches.get("name"),
                                matches.get("qty"), matches.get("desc"));
                    } else {
                        throw new WrongCommandException("add", true);
                    }
                    break;
                case "remove":
                    flag = userInput.split(" ")[1];

                    if (flag.equals("-o")) {
                        regex = "id/(?<id>\\d*)";
                        regexMatch = new Regex(userInput, regex);
                        matches = regexMatch.getGroupValues();
                        warehouse.removeOrder(matches.get("id"));
                    } else if (flag.equals("-g")) {
                        regex = "id/(?<id>\\d*) q/(?<qty>\\d*)";
                        regexMatch = new Regex(userInput, regex);
                        matches = regexMatch.getGroupValues();
                        warehouse.removeGoods(matches.get("id"), matches.get("qty"));
                    } else {
                        throw new WrongCommandException("remove", true);
                    }
                    break;
                case "total":
                    int total = warehouse.totalGoods();
                    System.out.printf("There are %d goods in total.\n", total);
                    break;
                case "help":
                    Commands.help();
                    break;
                default:
                    //error exception here
                    throw new WrongCommandException("", false);
                }
                System.out.println("Another command?");
                userInput = input.nextLine();
            }
        } catch (WrongCommandException wrongCommandException) {
            if (wrongCommandException.isCommand()) {
                String wrongCommand = wrongCommandException.getCommand();
                System.out.printf("%s command was used wrongly. Type help to see examples\n",
                        wrongCommand);
            } else {
                System.out.println("No such command. Type help to see examples");
            }
        } catch (NullException nullException) {
            //catch null exception here
        } finally {
            run();
        }
    }
}
