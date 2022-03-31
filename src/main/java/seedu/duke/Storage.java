package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the controller to parse and write to a save file for packages and
 * reservations
 */
public class Storage {
    private String filePath;

    /**
     * String representation of the file path to the save file
     *
     * @param   filePath
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
        ArrayList<TravelPackage> t = parseSavedFile();
        Packages p = new Packages(t);
        return p;
    }

    /**
     * Calls the functions to read string data after splitting using regex
     *
     * @return ArrayList<TravelPackage> object for createPackages method
     */
    public ArrayList<TravelPackage> parseSavedFile(){
        ArrayList<TravelPackage> t = new ArrayList<>();
        File pFile = new File(filePath);
        try {
            Scanner s = new Scanner(pFile);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] arrayElements = currentLine.split("\\$");
                TravelPackage newPackage = parseTravelPackageFile(arrayElements[0]);
                Reservations newReservations = parseReservationFile(arrayElements[1]);
                newPackage.setReservationList(newReservations);
                t.add(newPackage);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return t;
    }

    /**
     * Iterates through the chunk of Reservation string data
     *
     * @return Reservations object
     */
    public Reservations parseReservationFile(String str) {
        Reservations rList = new Reservations();
        String[] arrayElements = str.split("%");
        for (int i=0; i< arrayElements.length; i++){
            Reservation newR = parseReservation(arrayElements[i]);
            rList.addReservation(newR);
        }
        return rList;
    }

    /**
     * Parses individual reservation strings
     *
     * @return Reservation
     */
    public Reservation parseReservation(String str){
        String[] arrayElements = str.split(",");
        int packageID = Integer.parseInt(arrayElements[0].trim());
        String customerName = arrayElements[1].trim();
        String customerNum = arrayElements[2].trim();
        int numPax = Integer.parseInt(arrayElements[3].trim());
        Reservation r = new Reservation(packageID, customerName, customerNum, numPax);
        return r;
    }

    /**
     * Parses the individual travel packages string
     *
     * @return TravelPackage object
     */
    public TravelPackage parseTravelPackageFile(String str) {
        String[] arrayElements = str.split("\\|");
        String name = arrayElements[0].trim();
        String sid = arrayElements[1].trim();
        int id = Integer.parseInt(sid);
        String start = arrayElements[2].trim();
        String end = arrayElements[3].trim();
        String hotel = arrayElements[4].trim();
        double price = Double.parseDouble(arrayElements[5].trim());
        String country = arrayElements[6].trim();
        int vacancies = Integer.parseInt(arrayElements[7].trim());
        int numParticipants = Integer.parseInt(arrayElements[8].trim());
        TravelPackage newPackage = new TravelPackage(name, id, start, end, hotel, price, country, vacancies, numParticipants);
        return newPackage;
    }
}

