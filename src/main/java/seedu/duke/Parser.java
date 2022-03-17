package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.PackagesCommand;
import seedu.duke.command.ErrorCommand;
import seedu.duke.command.ReservationCommand;

public class Parser {
    public static Command parse(String input) {
        String[] inputArray = input.split(" ");
        String commandType = inputArray[0];

        String id;
        int start, end, vacancies;
        double price;
        String name, hotel, country;

        switch (commandType) {
        case "bye":
            return new ByeCommand();

        case "add": //only can have spaces between variables - what if hotel has 2 words?
            final int nameIndex = 1;
            final int startIndex = 2;
            final int endIndex = 3;
            final int hotelIndex = 4;
            final int priceIndex = 5;
            final int countryIndex = 6;
            final int vacanciesIndex = 7;
            name = inputArray[nameIndex];
            start = Integer.parseInt(inputArray[startIndex]);
            end = Integer.parseInt(inputArray[endIndex]);
            hotel = inputArray[hotelIndex];
            price = Double.parseDouble(inputArray[priceIndex]);
            country = inputArray[countryIndex];
            vacancies = Integer.parseInt(inputArray[vacanciesIndex]);
            return new AddCommand(name, start, end, hotel, price, country, vacancies);
        case "delete":
            id = inputArray[1];
            return new DeleteCommand(id);
        case "packages":
            return new PackagesCommand();
        case "reservation":
            return new ReservationCommand();
        default:
            return new ErrorCommand(input);
        }
    }
}
