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

    private static File saveFile;
    private static int numberOfPerson = 0;
    private static String filePath;
    private static PersonList personDataList = new PersonList();

    public Storage() {
        filePath = FILE_DIR + FILE_SEPARATOR + FILE_NAME;
        saveFile = new File(filePath);
    }

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

    private static void processLine(String dataLine) {
        String objectType = dataLine.substring(GET_TYPE, END_OF_TYPE);
        String objectInfo = dataLine.substring(END_OF_TYPE);
        addCorrespondingInfo(objectType, objectInfo.trim());
    }

    private static void addCorrespondingInfo(String type, String info) {

        String description;
        Double amount;
        switch (type) {
        case ADD_USER:
            personDataList.addPerson(info);
            numberOfPerson++;
            break;
        case ADD_INCOME:
            description = parseInfoGetDescription(info);
            amount = parseInfoGetAmount(info);
            personDataList.getPerson(numberOfPerson).addIncome(description, amount);
            break;
        case ADD_EXPENDITURE:
            description = parseInfoGetDescription(info);
            amount = parseInfoGetAmount(info);
            personDataList.getPerson(numberOfPerson).addExpend(description, amount);
            break;
        default:
            break;
        }
    }

    private static String parseInfoGetDescription(String info) {
        String[] inputInfo = info.split(INFO_DELIMITER, 2);
        return inputInfo[0];
    }

    private static Double parseInfoGetAmount(String info) {
        String[] inputInfo = info.split(INFO_DELIMITER, 2);
        return Double.valueOf(inputInfo[1].trim());
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
    
    public static PersonList loadData() {
        checkFileExists();
        readSaveFile();
        return personDataList;
    }

}
