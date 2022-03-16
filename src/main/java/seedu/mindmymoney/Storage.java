package seedu.mindmymoney;

import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for handling loading and saving of expenditure lists.
 */
public class Storage {
    private File storageFile;

    public Storage(File storageFile) {
        this.storageFile = storageFile;
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                System.out.println("WARNING: Failed to create save file. "
                        + "You may still use MindMyMoney; however, your expenditure list will not be saved.");
            }
        }
    }

    /**
     * Loads the expenditure list from the save file. If the file does not exist, or if there is
     * an error when reading the file, return an empty list, and print a warning message.
     * @return The saved list.
     */
    public ExpenditureList load() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(storageFile));
            String inputLine;
            ArrayList<Expenditure> expenditureList = new ArrayList<Expenditure>();
            while ((inputLine = bufferedReader.readLine()) != null) {
                expenditureList.add(Expenditure.deserialize(inputLine));
            }
            bufferedReader.close();
            return new ExpenditureList(expenditureList);
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Save file not found. MindMyMoney will create another empty expenditure list.");
            return new ExpenditureList();
        } catch (IOException e) {
            System.out.println("WARNING: Error when reading expenditure list.");
            return new ExpenditureList();
        } catch (MindMyMoneyException e) {
            System.out.println(e.getMessage());
            return new ExpenditureList();
        }
    }

    /**
     * Saves the given expenditure list. If the file does not exist, or if there is an error
     * when saving to the file, print a warning message.
     * @param listToSave The list to save.
     */
    public void save(ExpenditureList listToSave) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(storageFile));
            for (Expenditure expenditure : listToSave.expenditureListArray) {
                String expenditureString = expenditure.serialize();
                bufferedWriter.write(expenditureString, 0, expenditureString.length());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Save file not found. MindMyMoney cannot save your expenditure list.");
        } catch (IOException e) {
            System.out.println("WARNING: Error when saving expenditure list.");
        }
    }
}
