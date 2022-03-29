//@@author HansHengGit

package seedu.planitarium.storage;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.Constants;
import seedu.planitarium.money.Expenditure;
import seedu.planitarium.money.Income;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.person.Family;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Storage {
    protected static final String className = Storage.class.getSimpleName();
    protected static final String fileName = className + ".log";
    protected static final ProjectLogger logger = new ProjectLogger(className, fileName);

    private static final String FILE_DIR = "data";
    private static final String FILE_NAME = "PlanITarium.txt";
    private static final String FILE_SEPARATOR = "/";
    private static final String INFO_DELIMITER = "-";
    private static final String ADD_USER = "u";
    private static final String ADD_EXPENDITURE = "e";
    private static final String ADD_INCOME = "i";
    private static final int GET_TYPE = 0;
    private static final int END_OF_TYPE = 1;
    private static final int DELIMIT_COUNT_INCOME = 3;
    private static final int DELIMIT_COUNT_EXPEND = 4;
    private static final int DELIMIT_COUNT = 3;
    private static int groupNumber = 0;
    private static File saveFile;
    private static int numberOfPerson = 0;
    private static String filePath;
    private static Family familyData = new Family();

    /**
     * Constructs a new storage object.
     */
    public Storage() {
        filePath = FILE_DIR + FILE_SEPARATOR + FILE_NAME;
        saveFile = new File(filePath);
        String loggerString = "New Storage constructed";
        logger.log(Level.INFO, loggerString);
    }

    /**
     * Checks if the directory and file exists in the local hard drive, create one
     * if it does not.
     */
    public static void checkFileExists() {
        String loggerString = "Method checkFileExists() called";
        logger.log(Level.INFO, loggerString);
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
        String loggerString = "readSaveFile() called";
        logger.log(Level.INFO, loggerString);
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
        String loggerString = "Method processLine() called";
        logger.log(Level.INFO, loggerString);
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
        String loggerString = "Method addCorrespondingInfo() called";
        logger.log(Level.INFO, loggerString);
        switch (type) {
        case ADD_USER:
            addUserData(info);
            break;
        case ADD_INCOME:
            addIncomeData(info);
            break;
        case ADD_EXPENDITURE:
            addExpenditureData(info);
            break;
        default:
            loadNextGroup();
        }
    }

    /**
     * Increments groupNumber to load the next group and
     * resets the numberOfPerson in the group to 0.
     */
    private static void loadNextGroup() {
        String loggerString = "Method loadNextGroup() called";
        logger.log(Level.INFO, loggerString);
        numberOfPerson = 0;
        groupNumber++;
    }

    /**
     * Takes in the expenditure details to be loaded into
     * the user's current session.
     *
     * @param info The string containing the expenditure details
     */
    private static void addExpenditureData(String info) {
        String loggerString = "Method addExpenditureData() called";
        logger.log(Level.INFO, loggerString);
        String description;
        description = parseInfoGetDescription(info);
        boolean isPermanent;
        Double amount;
        amount = parseInfoGetAmount(info);
        int category;
        category = parseInfoGetCategory(info);
        isPermanent = parseInfoGetPermanent(info);
        familyData.addExpend(numberOfPerson, groupNumber, description, amount,
                category, isPermanent, Constants.FOR_STORAGE);
    }

    /**
     * Takes in the income details to be loaded into
     * the user's current session.
     *
     * @param info The string containing the income details
     */
    private static void addIncomeData(String info) {
        String loggerString = "Method addIncomeData() called";
        logger.log(Level.INFO, loggerString);
        String description;
        description = parseInfoGetDescription(info);
        double amount;
        amount = parseInfoGetAmount(info);
        boolean isPermanent;
        isPermanent = parseInfoGetPermanent(info);
        familyData.addIncome(numberOfPerson, groupNumber, description, amount, isPermanent, Constants.FOR_STORAGE);
    }

    /**
     * Takes in the user's name to be loaded into
     * the user's current session.
     *
     * @param info The string containing the user's name
     */
    private static void addUserData(String info) {
        String loggerString = "Method addUserData() called";
        logger.log(Level.INFO, loggerString);
        familyData.addPerson(groupNumber, info, Constants.FOR_STORAGE);
        numberOfPerson++;
    }

    /**
     * Takes in a string of details from the input and parses
     * the string to get the category of the expenditure being loaded.
     *
     * @param info A string consisting of the details to be added
     * @return The category type of the expenditure
     */
    private static int parseInfoGetCategory(String info) {
        String loggerString = "Method parseInfoGetCategory() called";
        logger.log(Level.INFO, loggerString);
        String[] inputInfo = info.split(INFO_DELIMITER, DELIMIT_COUNT_EXPEND);
        return Integer.parseInt(inputInfo[3].trim());
    }

    /**
     * Takes in a string of details from the input and parses
     * the string to get the description of either the expenditure or income.
     *
     * @param info A string consisting of the details to be added
     * @return The description of expenditure or income to be added
     */
    private static String parseInfoGetDescription(String info) {
        String loggerString = "Method parseInfoGetDescription() called";
        logger.log(Level.INFO, loggerString);
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
        String loggerString = "Method parseInfoGetAmount() called";
        logger.log(Level.INFO, loggerString);
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
        String loggerString = "Method parseInfoGetPermanent() called";
        logger.log(Level.INFO, loggerString);
        String[] inputInfo = info.split(INFO_DELIMITER, DELIMIT_COUNT);
        return Boolean.valueOf(inputInfo[2].trim());
    }

    /**
     * Takes in the set of records of the current session with type Family
     * and writes each record into the local file ordered by group and within
     * each group, ordered by person added.
     *
     * @param familyDataToSave The record of all data in the current session
     */
    public static void saveData(Family familyDataToSave) {
        String loggerString = "Method saveData() called";
        logger.log(Level.INFO, loggerString);
        try {
            FileWriter writeToFile = new FileWriter(filePath);
            for (int i = Constants.SINGULAR; i <= Constants.NUM_GROUPS; i++) {
                storeGroupData(familyDataToSave, writeToFile, i);
            }
            writeToFile.close();
        } catch (IOException e) {
            System.out.println("fail");
        }
    }

    /**
     * Takes in the Family records and get the PersonList of
     * a specific family group by calling getPersonList() for
     * that particular group. Writes the data of the PersonList
     * into the local file.
     *
     * @param familyDataToSave The record of all data in the current session
     * @param writeToFile A pointer to the local file to be written
     * @param currentGroupNumber The group currently being written
     * @throws IOException If there is an error in writing to the file
     */
    private static void storeGroupData(Family familyDataToSave, FileWriter writeToFile,
                                       int currentGroupNumber) throws IOException {
        String loggerString = "Method storeGroupData() called";
        logger.log(Level.INFO, loggerString);
        writeToFile.write(String.valueOf(currentGroupNumber) + System.lineSeparator());
        for (Person person : familyDataToSave.getList(currentGroupNumber).getPersonList()) {
            storePersonListData(writeToFile, person);
        }
    }

    /**
     * Takes in the person data to be written, and writes the
     * income list and expenditure list of that person.
     *
     * @param writeToFile A pointer to the local file to be written
     * @param person The person data to be written
     * @throws IOException If there is an error in writing to the file
     */
    private static void storePersonListData(FileWriter writeToFile, Person person) throws IOException {
        String loggerString = "Method storePersonListData() called";
        logger.log(Level.INFO, loggerString);
        writeToFile.write(String.valueOf(person.saveName()) + System.lineSeparator());
        storeIncomeData(writeToFile, person);
        storeExpenditureData(writeToFile, person);
    }

    /**
     * Takes in the person data and get the expenditure list
     * of that person by calling getExpenditureList(). Stores
     * each expenditure entry into the local file.
     *
     * @param writeToFile A pointer to the local file to be written
     * @param person The person data to be written
     * @throws IOException If there is an error in writing to the file
     */
    private static void storeExpenditureData(FileWriter writeToFile, Person person) throws IOException {
        String loggerString = "Method storeExpenditureData() called";
        logger.log(Level.INFO, loggerString);
        for (Expenditure expenditure : person.getExpenditureList()) {
            writeToFile.write(String.valueOf(expenditure.saveString()) + System.lineSeparator());
        }
    }

    /**
     * Takes in the person data and get the income list
     * of that person by calling getIncomeList(). Stores
     * each income entry into the local file.
     *
     * @param writeToFile A pointer to the local file to be written
     * @param person The person data to be written
     * @throws IOException If there is an error in writing to the file
     */
    private static void storeIncomeData(FileWriter writeToFile, Person person) throws IOException {
        String loggerString = "Method storeIncomeData() called";
        logger.log(Level.INFO, loggerString);
        for (Income income : person.getIncomeList()) {
            writeToFile.write(String.valueOf(income.saveString()) + System.lineSeparator());
        }
    }


    /**
     * Drives the loading of data process by calling checkFileExists()
     * and readSaveFile() to load all data from the local file to
     * a PersonList personDataList which wil be returned to the PersonList
     * in the new Session.
     *
     * @return The PersonList of the data loaded from the local file
     */
    public static Family loadData() {
        String loggerString = "Method loadData() called";
        logger.log(Level.INFO, loggerString);
        checkFileExists();
        readSaveFile();
        return familyData;
    }

}