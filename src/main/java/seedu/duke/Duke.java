package seedu.duke;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Duke {
    // private ArrayList<TravelPackage> packages;
    // public TravelPackage(String name, int id, Date startDate, Date endDate, String hotel, double price, String country, int maxParticipants) {

    public static void main(String[] args) {
        ArrayList<TravelPackage> packages = new ArrayList<TravelPackage>();
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        packages.add(new TravelPackage("Experience Korea!", 1, new Date(25032021), new Date(28032021), "Four Seasons", 500.25,"Korea", 20));

        Help helper = new Help();

        boolean endProgram = false;
        System.out.print("Welcome to Travel Agency Booking Reservation System!");

        while (!endProgram) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter command: ");
            String nextLine = sc.nextLine();
            if (nextLine.equals("bye")) {
                endProgram = true;
                System.out.println("Thank you for using TARBS. See you again!");
                break;
            }

            else if  (nextLine.equals("packages")){
                for (int i = 0; i < packages.size(); i++){
                    System.out.println(Integer.toString(i+1) + ". " + packages.get(i).getName());
                }
            }
            else if (nextLine.equals("info")){
                System.out.println("Which package would you like to find out more about?");
                for (int i = 0; i < packages.size(); i++){
                    System.out.println(Integer.toString(i+1) + ". " + packages.get(i).getName());
                }
                String st = sc.nextLine();
                int index = Integer.parseInt(st);
                if (index <= 0 || index > packages.size()){
                    System.out.println("Please enter a valid number.");
                }
                else{
                    packages.get(index-1).printInfo();
                }
            }
            //add [packageName], [id] , [DDMMYYYY] , [DDMMYYYY], [hotel], [price], [country], [maxVacancies]
            else if (nextLine.equals("add")) {
                System.out.println("Enter package name: ");
                String name = sc.nextLine();
                System.out.println("Enter package id: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.println("Enter start date in DDMMYYYY");
                int date1 = Integer.parseInt(sc.nextLine());
                System.out.println("Enter end date in DDMMYYYY");
                int date2 = Integer.parseInt(sc.nextLine());
                System.out.println("Enter hotel name: ");
                String hotel = sc.nextLine();
                System.out.println("Enter price: ");
                double price = Double.parseDouble(sc.nextLine());
                System.out.println("Enter country name: ");
                String country = sc.nextLine();
                System.out.println("Enter max vacancies: ");
                int maxVacancies = Integer.parseInt(sc.nextLine());

                packages.add(new TravelPackage(name, id, new Date(date1), new Date(date2), hotel, price, country, maxVacancies));
            }

            else if (nextLine.equals("help")){
                System.out.println("Here's a guide on how to use TARBS!");
                helper.printHelp();
            }

            else if (nextLine.matches("delete \\d+")) {
                //input must be delete [number] -> delete 1 deletes travel package ID 1
                String[] line = nextLine.split(" ");
                int removeID = Integer.parseInt(line[1]);
                System.out.println(line[1]);

                for (int i = 0; i<packages.size(); i++) {
                    if (packages.get(i).getID() == removeID) {
                        packages.remove(i);
                        break;
                    }
                }
            }

            else if (nextLine.startsWith("reserve")) {
                System.out.println("Enter package number: ");
                int packageNumber = sc.nextInt();
                String str1 = sc.nextLine();
                System.out.println("Enter Person of Contact: ");
                String contactName = sc.nextLine();
                System.out.println("Enter Contact Number: ");
                String contactNumber = sc.nextLine();
                System.out.println("Enter number of pax: ");
                int numOfPax = sc.nextInt();
                //Generate new reservationID
                int reservationID = reservations.size() + 1;
                //Create Reservation class and add into reservations list
                reservations.add(new Reservation(reservationID, packageNumber,contactName,contactNumber,numOfPax));
                System.out.println("Reservation added: " + reservations.get(reservationID-1).getReservationID());
            }
            else if (nextLine.startsWith("reservations")){
                String[] line = nextLine.split(" ");
                int packageID = Integer.parseInt(line[1]);

                for (int i = 0; i<reservations.size(); i++) {
                    if (reservations.get(i).getPackageID() == packageID) {
                        reservations.get(i).toString();
                        break;
                    }
                    else{
                        System.out.println("You don’t have any reservations for the travel package ");
                    }
                }
            }
            else {
                System.out.println("Command not recognized.");
            }
        }
    }

}
