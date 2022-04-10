package seedu.duke.command;

import seedu.duke.Packages;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String PACKAGES_FORMAT = "SHOW ALL PACKAGES\n" +
                                                 "View a list of all available packages\n" +
                                                 "Usage: packages\n";
    public static final String INFO_FORMAT = "SHOW CHOSEN PACKAGE\n" +
                                                "Displays the detailed information of a specific travel package from\nthe " +
                                                "'packages' page Able to print out specific details of any package\n" +
                                                "Format: info {package_id}\n" +
                                                "Usage: info 2\n";

    public static final String ADD_FORMAT = "ADD PACKAGE\n" +
                                            "Allows the user to add a new travel package\ninto the list of available packages\n" +
                                            "Format: add {package_name},{id},{date1},{date2},{hotel},{price},\n" +
                                            "            {country},{vacancies}\n" +
                                            "Usage: add Skiing Trip,1,15/02/2022,19/02/2022,Hotel1,100,Sweden,10\n";

    public static final String DELETE_FORMAT = "DELETE PACKAGE\n" +
                                                "Allows the user to delete an existing travel package\nfrom the list of available packages\n" +
                                                "Format: delete {package_id}\n" +
                                                "Usage: delete 2\n";

    public static final String RESERVATIONS_FORMAT = "SHOW RESERVATIONS\n" +
                                                    "Shows reservations of the user for a specified travel package\nfrom the packages page\n" +
                                                    "Format: reservations {package_id}\n" +
                                                    "Usage: reservations 3\n";

    public static final String RESERVE_FORMAT = "MAKE RESERVATION\n" +
                                                "Creates a reservation for a travel package from the packages page\n" +
                                                "Format: reserve {package_id},{contact_name},{contact_number},{pax}\n" +
                                                "Usage: reserve 3,John Doe,91234567,3\n" ;

    public static final String REMOVE_FORMAT = "REMOVE RESERVATION\n" +
                                                "Deletes a reservation for a travel package from the packages page\n" +
                                                "Format: remove {package_id},{contact_number}" +
                                                "\nUsage: remove 2,98765432\n";

    public static final String ADD_HOTEL_FORMAT = "ADD HOTEL TO ITINERARY\n" +
                                                "Adds Hotel Information to the Itinerary\n" +
                                                "Format: addHotel {hotel_id},{hotel_name},{country},{price},{package_id}" +
                                                "\nUsage: addHotel 1,Hotel99,Singapore,100,1\n";

    public static final String HOTELS_FORMAT = "VIEW HOTELS\n" +
                                                "Show hotels for a specified travel package\n" +
                                                "Format: hotels {package_number}" +
                                                "\nUsage: hotels 1\n";


    @Override
    public void execute(Packages packages) {
        final String SEPARATOR = "---------------------------------------------" +
                "--------------------------\n";
        System.out.println(SEPARATOR +
                            PACKAGES_FORMAT + SEPARATOR +
                            INFO_FORMAT + SEPARATOR +
                            ADD_FORMAT + SEPARATOR +
                            DELETE_FORMAT + SEPARATOR +
                            RESERVATIONS_FORMAT + SEPARATOR +
                            RESERVE_FORMAT + SEPARATOR +
                            REMOVE_FORMAT + SEPARATOR +
                            ADD_HOTEL_FORMAT + SEPARATOR +
                            HOTELS_FORMAT + SEPARATOR);
    }
}
