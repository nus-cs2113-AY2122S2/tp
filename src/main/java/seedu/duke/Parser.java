package seedu.duke;

import seedu.duke.command.*;

public class Parser {
    public static Command parse(String input) {
        String[] inputArray = input.split(" ");
        String commandType = inputArray[0];
        System.out.println(commandType);
        int id;
        String start;
        String end;
        int vacancies;
        double price;
        String name;
        String hotel;
        String country;

        switch (commandType) {
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "add":
            String[] temp = input.split(" ", 2); // remove add from string
            String[] temp1 = temp[1].split(","); // process the rest and split by comma

            System.out.println(temp.length);
            if (temp1.length != 8) {
                return new WrongFormatCommand(input);
            } else {
                final int nameIndex = 0;
                final int idIndex = 1;
                final int startIndex = 2;
                final int endIndex = 3;
                final int hotelIndex = 4;
                final int priceIndex = 5;
                final int countryIndex = 6;
                final int vacanciesIndex = 7;
                name = temp1[nameIndex];
                id = Integer.parseInt(temp1[idIndex]);
                start = temp1[startIndex];
                end = temp1[endIndex];
                hotel = temp1[hotelIndex];
                price = Double.parseDouble(temp1[priceIndex]);
                country = temp1[countryIndex];
                vacancies = Integer.parseInt(temp1[vacanciesIndex]);
                return new AddCommand(name, id, start, end, hotel, price, country, vacancies);
            }
        case "delete": // delete TravelPackage by its ID
            id = Integer.parseInt(inputArray[1]);
            return new DeleteCommand(id);
        case "packages":
            return new PackagesCommand();
        case "reserve":
            temp = input.split(" ", 2); // remove reserve from string
            temp1 = temp[1].split(","); // process the rest and split by comma
            int packageID = Integer.parseInt(temp1[0]);
            name = temp1[1];
            String number = temp1[2];
            int pax = Integer.parseInt(temp1[3]);

            return new ReservationCommand(packageID, name, number, pax);

        case "remove": // delete reservation by giving travelpackage ID and contact number.
            temp = input.split(" ", 2);
            temp1 = temp[1].split(",");
            packageID = Integer.parseInt(temp1[0]);
            number = temp1[1];
            return new RemoveReservationCommand(packageID, number);

        case "reservations":
            id = Integer.parseInt(inputArray[1]);
            return new PrintReservationsCommand(id);

        default:
            return new ErrorCommand(input);
        }
    }
}
