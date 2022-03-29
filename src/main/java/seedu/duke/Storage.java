//package seedu.duke;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Scanner;
//
///**
// * Represents the controller to parse and write to a save file for packages and
// * reservations
// */
//public class Storage {
//    private String packages_filePath;
//    private String reservations_filePath;
//
//    /**
//     * String representation of the file path to the save file
//     *
//     * @param package_path,
//     *            reservations_path
//     */
//    public Storage(String package_path, String reservations_path) {
//        this.packages_filePath = package_path;
//        this.reservations_filePath = reservations_path;
//    }
//
//    /**
//     * Writes the tasks in task list to the save file
//     *
//     */
//    public void convertListToFile(Packages p, Reservations r) {
//        String text = "";
//        for (int i = 0; i < p.getSize(); i++) {
//            TravelPackage currentPackage = p.getPackage(i);
//            text = text + currentPackage.toSave() + System.lineSeparator();
//        }
//        try {
//            FileWriter fw = new FileWriter(packages_filePath);
//            fw.write(text);
//            fw.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        String resText = "";
//        for (int i = 0; i < r.getReservationSize(); i++) {
//            Reservation currentReservation = r.getReservation(i);
//            resText = resText + currentReservation.toSave() + System.lineSeparator();
//        }
//        try {
//            FileWriter fw = new FileWriter(reservations_filePath);
//            fw.write(resText);
//            fw.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    /**
//     * Calls the functions to read packages and reservations saved files
//     *
//     * @return Packages object for Control class
//     */
//    public Packages createPackages() {
//        ArrayList<TravelPackage> t = parseTravelPackageFile();
//        Packages p = new Packages(t);
//        return p;
//    }
//
//    public Reservations createReservatons() {
//        ArrayList<Reservation> r = parseReservationFile();
//        Reservations reservations = new Reservations(r);
//        return reservations;
//    }
//
//    /**
//     * Parses the saved reservation file
//     *
//     * @return Arraylist of Reservations
//     */
//    public ArrayList<Reservation> parseReservationFile() {
//        File rFile = new File(reservations_filePath);
//        ArrayList<Reservation> r = new ArrayList<>();
//        try {
//            Scanner s = new Scanner(rFile);
//            while (s.hasNext()) {
//                String currentLine = s.nextLine();
//                String[] arrayElements = currentLine.split("\\|");
//
//                String packageID = arrayElements[0].trim();
//                int packageId2 = Integer.parseInt(packageID);
//                String customerName = arrayElements[1].trim();
//                String customerNum = arrayElements[2].trim();
//                int numPax = Integer.parseInt(arrayElements[4].trim());
//                Reservation newReservation = new Reservation(packageId2, customerName, customerNum,
//                        numPax);
//                r.add(newReservation);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        return r;
//    }
//
//    /**
//     * Parses the saved travel packages file
//     *
//     * @return Arraylist of TravelPackage
//     */
//    public ArrayList<TravelPackage> parseTravelPackageFile() {
//        ArrayList<TravelPackage> t = new ArrayList<>();
//        File pFile = new File(packages_filePath);
//        try {
//            Scanner s = new Scanner(pFile);
//            while (s.hasNext()) {
//                String currentLine = s.nextLine();
//                String[] arrayElements = currentLine.split("\\|");
//                String name = arrayElements[0].trim();
//                String sid = arrayElements[1].trim();
//                int id = Integer.parseInt(sid);
//                String start = arrayElements[2].trim();
//                String end = arrayElements[3].trim();
//                String hotel = arrayElements[4].trim();
//                double price = Double.parseDouble(arrayElements[5].trim());
//                String country = arrayElements[6].trim();
//                int vacancies = Integer.parseInt(arrayElements[7].trim());
//                TravelPackage newPackage = new TravelPackage(name, id, start, end, hotel, price,
//                        country, vacancies);
//                t.add(newPackage);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        return t;
//    }
//}
