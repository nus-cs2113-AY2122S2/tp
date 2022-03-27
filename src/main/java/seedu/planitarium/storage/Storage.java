package seedu.planitarium.storage;

import seedu.planitarium.money.Expenditure;
import seedu.planitarium.money.Income;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_DIR = "data";
    private static final String FILE_NAME = "PlanITarium.txt";
    private static final String FILE_SEPARATOR = "/";
    private static final String INFO_DELIMITER = "-";
    private static final String ADD_USER = "u";
    private static final String ADD_EXPENDITURE = "e";
    private static final String ADD_INCOME = "i";
    private static final int GET_TYPE = 0;
    private static final int END_OF_TYPE = 1;
    private static final int DELIMIT_COUNT = 3;

    private static File saveFile;
    private static int numberOfPerson = 0;
    private static String filePath;
    private static PersonList personDataList = new PersonList();

    /**
     * Constructs a new storage object.
     */
    public Storage() {
        filePath = FILE_DIR + FILE_SEPARATOR + FILE_NAME;
        saveFile = new File(filePath);
    }

    /**
     * Checks if the directory and file exists in the local hard drive, create one
     * if it does not.
     */
    public static void checkFileExists() {
        File directory = new File(FILE_DIR);
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (saveFile.createNewFile()) {
                System.out.println("PlanITarium file not found! Creating new file.");
            } else {
                System.out.println("PlanITarium file exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the file at the given file path and scans each
     * line of the file as input and calls processLine() to load
     * the input into each PersonList of the current session.
     */
    private static void readSaveFile() {
        try {
            File readFile = new File(filePath);
            Scanner in = new Scanner(readFile);
            while (in.hasNext()) {
                processLine(in.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the input string from the local file and
     * extract the type of input as well as the details
     * that follow. Calls the addCorrespondingInfo() to
     * add each line to it's corresponding type with its
     * following details.
     *
     * @param dataLine A string that was read from the local file
     */
    private static void processLine(String dataLine) {
        String objectType = dataLine.substring(GET_TYPE, END_OF_TYPE);
        String objectInfo = dataLine.substring(END_OF_TYPE);
        addCorrespondingInfo(objectType, objectInfo.trim());
    }

    /**
     * Takes in the type of each line and its info to be added.
     * Calls the relevant adding operations in PersonList.
     *
     * @param type A string indicating the type of input
     * @param info A string consisting of the details to be added
     */
    private static void addCorrespondingInfo(String type, String info) {
        String description;
        Double amount;
        boolean isPermanent;
        switch (type) {
        case ADD_USER:
            personDataList.addPerson(info);
            numberOfPerson++;
            break;
        case ADD_INCOME:
            description = parseInfoGetDescription(info);
            amount = parseInfoGetAmount(info);
            isPermanent = parseInfoGetPermanent(info);
            personDataList.getPerson(numberOfPerson).addIncome(description, amount, isPermanent);
            break;
        case ADD_EXPENDITURE:
            description = parseInfoGetDescription(info);
            amount = parseInfoGetAmount(info);
            isPermanent = parseInfoGetPermanent(info);
            personDataList.getPerson(numberOfPerson).addExpend(description, amount, isPermanent);
            break;
        default:
            break;
        }
    }

    /**
     * Takes in a string of details from the input and parses
     * the string to get the description of either the expenditure or income.
     *
     * @param info A string consisting of the details to be added
     * @return The description of expenditure or income to be added
     */
    private static String parseInfoGetDescription(String info) {
        String[] inputInfo = info.split(INFO_DELIMITER, DELIMIT_COUNT);
        return inputInfo[0];
    }

    /**
     * Takes in a string of details from the input and parses
     * the string to get the amount of either the expenditure or income.
     *
     * @param info A string consisting of the details to be added
     * @return The amount of expenditure or income to be added
     */
    private static Double parseInfoGetAmount(String info) {
        String[] inputInfo = info.split(INFO_DELIMITER, DELIMIT_COUNT);
        return Double.valueOf(inputInfo[1].trim());
    }

    /**
     * Takes in a string of details from the input and parses
     * the string to get the recurring status of either the expenditure or income.
     *
     * @param info A string consisting of the details to be added
     * @return The recurring status of expenditure or income to be added
     */
    private static boolean parseInfoGetPermanent(String info) {
        String[] inputInfo = info.split(INFO_DELIMITER, DELIMIT_COUNT);
        return Boolean.valueOf(inputInfo[2].trim());
    }

    /**
    public static void saveData(PersonList dataToBeSavedList) throws IOException {
        FileWriter writeToFile = new FileWriter(filePath);
        for (Person person : dataToBeSavedList.getPersonList()) {
            writeToFile.write(String.valueOf(person.saveName()) + System.lineSeparator());
            for (Income income : person.getIncomeList()) {
                writeToFile.write(String.valueOf(income.saveString()) + System.lineSeparator());
            }
            for (Expenditure expenditure : person.getExpenditureList()) {
                writeToFile.write(String.valueOf(expenditure.saveString()) + System.lineSeparator());
            }
        }
        writeToFile.close();
    }
    */

    /**
     * Drives the loading of data process by calling checkFileExists()
     * and readSaveFile() to load all data from the local file to
     * a PersonList personDataList which wil be returned to the PersonList
     * in the new Session.
     *
     * @return The PersonList of the data loaded from the local file
     */
    public static PersonList loadData() {
        checkFileExists();
        readSaveFile();
        return personDataList;
    }

}
