package seedu.duke.command;

import seedu.duke.Packages;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(Packages packages) {
        final String SEPARATOR = "---------------------------------------------" +
                "--------------------------\n";
        System.out.println(SEPARATOR + "SHOW ALL PACKAGES\nView a list of all available packages\n" +
                "Usage: packages\n" + SEPARATOR + "SHOW CHOSEN PACKAGE\nDisplays the " +
                "detailed information of a specific travel " + "package from\nthe " +
                "'packages' page Able to print out specific details of any package\n" +
                "Format: info {package_id}\n" + "Usage: info 2\n" + SEPARATOR + "ADD PACKAGE\n" +
                "Allows the user to add a new travel package\ninto the list of available packages\n" +
                "Format: add {package_name},{country},{duration},{price},{vacancies}\n" +
                "Usage: add Skiing Trip,Sweden,15/2/2022,19/2/2022,800,100\n" + SEPARATOR +
                "DELETE PACKAGE\nAllows the user to delete an existing travel package\nfrom the " +
                "list of available packages\nFormat: delete {package_id}\nUsage: delete 2\n" +
                SEPARATOR + "SHOW RESERVATIONS\nShows reservations of the user for " +
                "a specified travel package\nfrom the packages page\nFormat: reservations " +
                "{package_id}\nUsage: reservations 3\n" + SEPARATOR + "MAKE RESERVATION" +
                "\nCreates a reservation for a travel package from the packages page\nFormat: " +
                "reserve {package_id},{contact_name},{contact_number},{pax}\nUsage: reserve 3," +
                "John Doe,91234567,3\n" + SEPARATOR + "REMOVE RESERVATION\nDeletes a reservation " +
                "for a travel package from the packages page\nFormat: remove {package_id},{contact_number}" +
                "\nUsage: remove 2,98765432\n" + SEPARATOR + "EXIT PROGRAM\nFormat/Usage: bye\n" + SEPARATOR);
    }
}
