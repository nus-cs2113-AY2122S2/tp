package seedu.duke;

import seedu.duke.exception.InvalidDateException;
import seedu.duke.exception.InvalidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the controller to parse and write to a save file for packages and
 * reservations
 */
public class Storage {
    private final String filePath;

    static final int travelPackageNameIndex = 0;
    static final int travelPackageIDIndex = 1;
    static final int travelPackageStartDateIndex = 2;
    static final int travelPackageEndDateIndex = 3;
    static final int travelPackageHotelIndex = 4;
    static final int travelPackagePriceIndex = 5;
    static final int travelPackageCountryIndex = 6;
    static final int travelPackageVacanciesIndex = 7;
    static final int travelPackageNumParticipantsIndex = 8;

    static final int reservationPackageIDIndex = 0;
    static final int reservationCustomerNameIndex = 1;
    static final int reservationCustomerNumberIndex = 2;
    static final int reservationNumPaxIndex = 3;

    static final int hotelIDIndex = 0;
    static final int hotelNameIndex = 1;
    static final int hotelCountryIndex = 2;
    static final int hotelPriceIndex = 3;

    /**
     * String representation of the file path to the save file
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the data into text file specified in file_path
     *
     */
    public void savePackageToFile(Packages p) {
        String text = "";
        for (int i = 0; i < p.getSize(); i++) {
            TravelPackage currentPackage = p.getPackage(i);
            text = text + currentPackage.saveTravelPackage() + System.lineSeparator();
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Calls the functions to read saved files, creating a new Package object
     *
     * @return Packages object for Control class
     */
    public Packages createPackages() {
        System.out.println("Loading save file...");
        try {
            ArrayList<TravelPackage> t = parseSavedFile();
            Packages p = new Packages(t);
            System.out.println("Save file loaded!");
            return p;
        } catch (FileNotFoundException e) {
            Packages p = new Packages();
            System.out.println("Save file not found!");
            System.out.println("New save file 'data.txt' created!");
            return p;
        } catch (InvalidInputException e) {
            String errorMessage = e.getMessage();
            String errorDisplay = "ERROR  '" + errorMessage + "'";
            System.out.println(errorDisplay);
            boolean newFileFlag = makeNewSaveFile();
            if (newFileFlag) {
                return new Packages();
            }
            else {
                System.out.println("Thank you for using TARBS. See you again!");
                System.exit(0);
            }
        }
        return null;
    }

    /**
     * Calls the functions to read string data after splitting using regex
     *
     * @return ArrayList<TravelPackage> object for createPackages method
     */
    public ArrayList<TravelPackage> parseSavedFile() throws InvalidInputException,FileNotFoundException {
        ArrayList<TravelPackage> t = new ArrayList<>();
        File pFile = new File(filePath);
        try {
            Scanner s = new Scanner(pFile);
            while (s.hasNext()) {
                try{
                    String currentLine = s.nextLine();
                    String[] arrayElements = currentLine.split("\\$");
                    TravelPackage newPackage = parseTravelPackageFile(arrayElements[0]);
                    if (arrayElements.length > 1){
                        Reservations newReservations = parseReservationFile(arrayElements[1]);
                        newPackage.setReservationList(newReservations);
                    }
                    t.add(newPackage);
                } catch (Exception e) {
                    throw new InvalidInputException(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        return t;
    }

    /**
     * Iterates through the chunk of Reservation string data
     *
     * @return Reservations object
     */
    public Reservations parseReservationFile(String savedReservations) throws InvalidInputException {
        Reservations reservationsList = new Reservations();
        String[] arrayElements = savedReservations.split("%");
        try {
            for (String arrayElement : arrayElements) {
                Reservation newReservation = parseReservation(arrayElement);
                reservationsList.initReservation(newReservation);
            }
            return reservationsList;
        } catch (Exception e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    /**
     * Parses individual reservation strings
     *
     * @return Reservation
     */
    public Reservation parseReservation(String str) throws InvalidInputException {
        try {
            String[] arrayElements = str.split(",");
            int packageID = Integer.parseInt(arrayElements[reservationPackageIDIndex].trim());
            String customerName = arrayElements[reservationCustomerNameIndex].trim();
            String customerNum = arrayElements[reservationCustomerNumberIndex].trim();
            int numPax = Integer.parseInt(arrayElements[reservationNumPaxIndex].trim());
            return new Reservation(packageID, customerName, customerNum, numPax);
        } catch (Exception e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    /**
     * Parses the individual travel packages string
     *
     * @return TravelPackage object
     */
    public TravelPackage parseTravelPackageFile(String str) throws InvalidInputException {
        try{
            String[] arrayElements = str.split("\\|");
            String name = arrayElements[travelPackageNameIndex].trim();
            String sid = arrayElements[travelPackageIDIndex].trim();
            int id = Integer.parseInt(sid);
            String start = arrayElements[travelPackageStartDateIndex].trim();
            LocalDate startDate = LocalDate.from(Parser.PARSE_FORMAT.parse(start));
            String end = arrayElements[travelPackageEndDateIndex].trim();
            LocalDate endDate = LocalDate.from(Parser.PARSE_FORMAT.parse(end));
            if (!dateStartEndValid(startDate, endDate)) {
                throw new InvalidInputException("End date cannot be before start date!");
            }
            String hotel = arrayElements[travelPackageHotelIndex].trim();
            double price = Double.parseDouble(arrayElements[travelPackagePriceIndex].trim());
            if (price <= 0) {
                throw new InvalidInputException("Price should not be less than or equal to 0!");
            }
            String country = arrayElements[travelPackageCountryIndex].trim();
            int vacancies = Integer.parseInt(arrayElements[travelPackageVacanciesIndex].trim());
            int numParticipants = Integer.parseInt(arrayElements[travelPackageNumParticipantsIndex].trim());
            return new TravelPackage(name, id, startDate, endDate, hotel, price, country, vacancies, numParticipants);
        } catch (Exception e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    public boolean makeNewSaveFile() {
        System.out.println("Uh oh, it seems like your file has been corrupted!");
        System.out.println("Would you like to restart with a new file? Your previous data will be wiped out. (Y/N)");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.trim().equalsIgnoreCase("Y")) {
                return true;
            } else if (input.trim().equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Invalid input! Please enter 'Y' or 'N'.");

        }
    }

    public boolean dateStartEndValid(LocalDate startDate, LocalDate endDate) {
        return endDate.isAfter(startDate);
    }

}
