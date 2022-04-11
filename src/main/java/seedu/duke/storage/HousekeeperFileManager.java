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

    /**
     * Returns the Item List which contains the contents stored within the file ListFolder/housekeeper_file.txt.
     *
     * @return the Housekeeper List containing the contents stored within the file ListFolder/housekeeper_file.txt
     * @throws HotelLiteManagerException if the folder that the file is stored in does not exist and we are unable to
     *                                   create it, if the file specified by the file path does not exist and we are
     *                                   unable to create it.
     */
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

    /**
     * This method verifies information of age in the housekeeper_file.txt is valid.
     *
     * @param housekeeperAge Housekeeper age given from the file.
     * @return A valid age number.
     * @throws HotelLiteManagerException If age given is not an integer or within age limit given.
     */
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

    /**
     * This method verifies that the housekeeper's name given from the housekeeper_file.txt does not contain
     * any symbols or digits.
     *
     * @param housekeeperName Housekeeper name given from the file.
     * @return A valid name.
     * @throws HotelLiteManagerException If name contains integer and digits.
     */
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

    /**
     * Deletes all the content currently stored within the file ListFolder/housekeeper_file.txt.
     *
     * @throws IOException if we are unable to write to the file ListFolder/housekeeper_file.txt.
     */
    private void clearFileContents() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.close();
    }

    /**
     * Update the contents stored within the file ListFolder/housekeeper_file.txt with the
     * current content of the housekeeper list.
     *
     * @throws IOException if we are unable to write to the file ListFolder/housekeeper_file.txt.
     */
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
