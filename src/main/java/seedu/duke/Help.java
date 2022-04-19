package seedu.duke;
import seedu.duke.command.*;

import java.util.ArrayList;

public class Help {
    private final ArrayList<String[]> desc;
    public static final String PACKAGE_HELP = "View all available packages";
    public static final String INFO_HELP = "View package details";
    public static final String ADD_HELP = "Add a new package";
    public static final String DELETE_HELP = "Delete a package";
    public static final String RESERVE_HELP = "Create a reservation for a package";
    public static final String REMOVE_HELP = "Remove a reservation";
    public static final String RESERVATIONS_HELP = "Shows all reservations for a package";

    public Help(){
        this.desc = new ArrayList<>();
        String[] packages = {Help.PACKAGE_HELP, PackagesCommand.COMMAND_WORD};
        desc.add(packages);
        String[] info = {Help.INFO_HELP, InfoCommand.COMMAND_WORD};
        desc.add(info);
        String[] add = {Help.ADD_HELP, AddCommand.COMMAND_WORD + "{package_name} {country} {duration} {price} {vacancies}"};
        desc.add(add);
        String[] delete = {Help.DELETE_HELP, DeleteCommand.COMMAND_WORD + "{num}"};
        desc.add(delete);
        String[] reserve = {Help.RESERVE_HELP, ReservationCommand.COMMAND_WORD + "{package_number},{contact_name},{contact_number},{number_pax}"};
        desc.add(reserve);
        String[] remove = {Help.REMOVE_HELP, RemoveReservationCommand.COMMAND_WORD + "{travelpackageID},{contact_number}"};
        desc.add(remove);
        String[] reservations = {Help.RESERVATIONS_HELP, PrintReservationsCommand.COMMAND_WORD + "{package_number}"};
        desc.add(reservations);
    }

    public void printHelp(){
        for (String[] strings : desc) {
            String foo = String.format("%-50s %s", strings[0], strings[1]);
            System.out.println(foo);
        }
    }
}
