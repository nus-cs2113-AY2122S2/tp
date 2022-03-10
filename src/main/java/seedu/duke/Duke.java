package seedu.duke;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Duke {
    // private ArrayList<TravelPackage> packages;
    // public TravelPackage(String name, int id, Date startDate, Date endDate, String hotel, double price, String country, int maxParticipants) {
    
    public static void main(String[] args) {
        ArrayList<TravelPackage> packages = new ArrayList<TravelPackage>();
        packages.add(new TravelPackage("Experience Korea!", 1, new Date(25032021), new Date(28032021), "Four Seasons", 500.25,"Korea", 20));
        
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
                    packages.get(i).printInfo();
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

            else if (nextLine.startsWith("help")) {
                //print guide info for first time users 
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
                // add,remove or list all reservations for a travel package

            }

            else {
                System.out.println("Command not recognized.");
            }

            
        }



    
    }
    
}

