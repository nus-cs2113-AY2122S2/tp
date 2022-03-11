package seedu.duke;
import java.util.ArrayList;

public class Help {
    private ArrayList<String[]> desc;

    public Help(){
        this.desc = new ArrayList<>();
        String[] packages = {"View all available packages", "packages"};
        desc.add(packages);
        String[] info = {"View package details", "info {num}"};
        desc.add(info);
        String[] add = {"Add a new package", "add {package_name} {country} {duration} {price} {vacancies}"};
        desc.add(add);
        String[] delete = {"Delete a package", "delete {num}"};
        desc.add(delete);
        String[] reserve = {"Create a reservation for a package", "reserve {package_number} {contact_name} {contact_number} {number_pax}"};
        desc.add(reserve);
        String[] remove = {"Remove a reservation", "remove {reservation_id}"};
        desc.add(remove);
        String[] reservations = {"Shows all reservations for a package", "reservations {package_number}"};
        desc.add(reservations);
    }

    public void printHelp(){
        for (String[] strings : desc) {
            String foo = String.format("%-50s %s", strings[0], strings[1]);
            System.out.println(foo);
        }
    }
}
