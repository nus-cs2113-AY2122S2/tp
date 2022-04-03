package seedu.duke.storage;


import seedu.duke.exceptions.InvalidHousekeeperProfileException;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.exceptions.HousekeeperFileNotFoundException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.exceptions.InvalidAgeException;
import seedu.duke.exceptions.OverAgeException;
import seedu.duke.exceptions.UnderAgeException;
import seedu.duke.exceptions.NameNotStringException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HousekeeperFileManager extends FileManager {
    private static final String FILE_PATH = "ListFolder/housekeeper_file.txt";
    private static final int MIN_AGE_ACCEPTED = 21;
    private static final int MAX_AGE_ACCEPTED = 60;

    public HousekeeperList load() throws HotelLiteManagerException {
        HousekeeperList housekeeperList = new HousekeeperList();
        Housekeeper housekeeper;
        File file = getFile(FILE_PATH);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new HousekeeperFileNotFoundException();
        }
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(FILE_SEPARATOR);
            String housekeeperName = splitData[0];
            String housekeeperAge = splitData[1];
            String name;
            name = getName(housekeeperName);
            int ageNumber;
            ageNumber = getAgeNumber(housekeeperAge);
            housekeeper = new Housekeeper(name, ageNumber);
            housekeeperList.addHousekeeper(housekeeper);
            String housekeeperAvailability = splitData[2].trim();
            if (!(housekeeperAvailability.equals("N/A"))) {
                housekeeperList.addAvailabilityInList(name, housekeeperAvailability);
            }
        }
        return housekeeperList;
    }


    private int getAgeNumber(String housekeeperAge) throws HotelLiteManagerException {
        int ageNumber;
        String age;
        try {
            age = housekeeperAge.trim();
            ageNumber = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new InvalidAgeException();
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidAgeException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidAgeException();
        }
        if (ageNumber < MIN_AGE_ACCEPTED) {
            throw new UnderAgeException();
        }
        if (ageNumber > MAX_AGE_ACCEPTED) {
            throw new OverAgeException();
        }
        return ageNumber;
    }

    private String getName(String housekeeperName) throws HotelLiteManagerException {
        String name;
        try {
            name = housekeeperName.trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidHousekeeperProfileException();
        }
        if (name.isEmpty()) {
            throw new InvalidHousekeeperProfileException();
        }
        if (!name.matches("^([a-z]|\\s|[A-Z])+$")) {
            throw new NameNotStringException();
        }
        return name;
    }

    private void clearFileContents() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.close();
    }

    public void save(HousekeeperList housekeeperList) throws IOException {
        clearFileContents();
        FileWriter fileWriter = new FileWriter(FILE_PATH, true);
        for (int i = 0; i < housekeeperList.getTotalHousekeeper(); i++) {
            Housekeeper housekeeper = housekeeperList.getHousekeeper(i);
            fileWriter.write(housekeeper.toFileString() + System.lineSeparator());
        }
        fileWriter.close();
    }

}
