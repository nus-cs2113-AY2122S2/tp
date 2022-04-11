package seedu.duke.command;
import seedu.duke.Packages;
import seedu.duke.TravelPackage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;
public class PrintAllCommand extends Command {
    public static final String COMMAND_WORD = "all";
    public ArrayList<TravelPackage> sortedPackages;

    public PrintAllCommand() {
        this.sortedPackages = new ArrayList<>();
    }

    public void execute(Packages p) {
        for (int i = 0; i < p.getSize(); i++) {
            sortedPackages.add(p.getPackage(i));
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Sort packages by:");
        System.out.println("1. Date (Earliest to Latest) ");
        System.out.println("2. Number of vacancies left");
        System.out.println("3. Price");
        System.out.println("4. Return to main menu.");
        while (true){
            try {
                int input = in.nextInt();
                if (input != 1 && input != 2 && input != 3 && input !=4) {
                    throw new InputMismatchException();
                }
                if (input == 4) {
                    break;
                }

                if (input == 1) {
                    sortByDate();
                    printSortedPackages();
                    break;
                }

                if (input == 2) {
                    sortByVacancies();
                    printSortedPackages();
                    break;
                }

                if (input == 3) {
                    sortByPrice();
                    printSortedPackages();
                    break;
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Please enter 1,2,3 or 4!");
                in.nextLine();
            }
        }
    }

    public void sortByDate() {
        for (int i = 1; i < this.sortedPackages.size(); i++) {
            int  k = i;
            for (int j = i-1; j>=0; j--) {
                if (sortedPackages.get(k).getLocalStartDate().isBefore(sortedPackages.get(j).getLocalStartDate())){
                    Collections.swap(sortedPackages,k,j);
                    k = k - 1;
                }
                else {
                    continue;
                }
            }
        }
    }
    public void sortByPrice() {
        for (int i = 1; i < this.sortedPackages.size(); i++) {
            int  k = i;
            for (int j = i-1; j>=0; j--) {
                if (sortedPackages.get(k).getPrice() < (sortedPackages.get(j).getPrice())){
                    Collections.swap(sortedPackages,k,j);
                    k = k - 1;
                }
                else {
                    continue;
                }
            }
        }
    }
    public void sortByVacancies() {
        for (int i = 1; i < this.sortedPackages.size(); i++) {
            int  k = i;
            for (int j = i-1; j>=0; j--) {
                if ((sortedPackages.get(k).getMaxParticipants() - sortedPackages.get(k).getNumParticipants())
                    > (sortedPackages.get(j).getMaxParticipants() - sortedPackages.get(j).getNumParticipants())){
                    Collections.swap(sortedPackages,k,j);
                    k = k - 1;
                }
                else {
                    continue;
                }
            }
        }
    }

    public void printSortedPackages(){
        if (this.sortedPackages.size() == 0) {
            System.out.println("No packages found!");
        }
        for (int i = 0; i < this.sortedPackages.size(); i++) {
            System.out.println(
                    i + 1 + ". PackageID-" + sortedPackages.get(i).getID() + " | " + sortedPackages.get(i).getCountry() + " - " + sortedPackages.get(i).getName());
        }
    }

}
