package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.PackagesCommand;

public class Parser {
    public static Command parse(String input) {
        String[] inputArray = input.split(" ");
        String commandType = inputArray[0];

        int id, start, end, vacancies;
        double price;
        String name, hotel, country;

        switch (commandType) {
        case "bye":
            return new ByeCommand();
        case "add":
            final int nameIndex = 1;
            final int idIndex = 2;
            final int startIndex = 3;
            final int endIndex = 4;
            final int hotelIndex = 5;
            final int priceIndex = 6;
            final int countryIndex = 7;
            final int vacanciesIndex = 8;
            name = inputArray[nameIndex];
            id = Integer.parseInt(inputArray[idIndex]);
            start = Integer.parseInt(inputArray[startIndex]);
            end = Integer.parseInt(inputArray[endIndex]);
            hotel = inputArray[hotelIndex];
            price = Double.parseDouble(inputArray[priceIndex]);
            country = inputArray[countryIndex];
            vacancies = Integer.parseInt(inputArray[vacanciesIndex]);
            return new AddCommand(name, id, start, end, hotel, price, country, vacancies);
        case "delete":
            id = Integer.parseInt(inputArray[1]);
            return new DeleteCommand(id);
        case "packages":
            return new PackagesCommand();
        default:
            return new ByeCommand();
        }
    }
}
