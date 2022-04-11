package seedu.duke.storage;

import seedu.duke.satisfactionlists.SatisfactionList;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.satisfactionlists.Satisfaction;
import seedu.duke.exceptions.SatisfactionFileNotFoundException;
import seedu.duke.exceptions.InvalidSatisfactionValueException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SatisfactionListFileManager extends FileManager {
    private static final String FILE_PATH = "ListFolder/satisfaction_list.txt";

    private void clearFileContents() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.close();
    }

    public SatisfactionList load() throws HotelLiteManagerException {
        SatisfactionList satisfactionList = new SatisfactionList();
        Satisfaction satisfaction;
        File file = getFile(FILE_PATH);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new SatisfactionFileNotFoundException();
        }

        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(FILE_SEPARATOR);
            String customerName = splitData[0].trim();
            String satisfactionString = splitData[1].trim();
            int satisfactionValue;
            try {
                satisfactionValue = Integer.parseInt(satisfactionString);
            } catch (NumberFormatException e) {
                throw new InvalidSatisfactionValueException();
            }
            satisfaction = new Satisfaction(customerName, satisfactionValue);
            satisfactionList.addSatisfaction(satisfaction);
        }
        return satisfactionList;
    }

    public void save(SatisfactionList satisfactionList) throws IOException {
        clearFileContents();
        FileWriter fw = new FileWriter(FILE_PATH, true);
        int lastIndexOfArrayList = satisfactionList.getSize() - 1;
        String customerName;
        int satisfactionValue;
        Satisfaction satisfaction;
        String satisfactionDetails;
        for (int i = 0; i < satisfactionList.getSize(); i++) {
            satisfaction = satisfactionList.getSatisfaction(i);
            customerName = satisfaction.getCustomerName();
            satisfactionValue = satisfaction.getSatisfactionValue();
            satisfactionDetails = customerName + '|' + satisfactionValue;
            if (i != lastIndexOfArrayList) {
                satisfactionDetails = satisfactionDetails + System.lineSeparator();
            }
            fw.write(satisfactionDetails);
        }
        fw.close();
    }
}
