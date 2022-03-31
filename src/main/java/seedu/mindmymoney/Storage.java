package seedu.mindmymoney;

import seedu.mindmymoney.command.Command;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.Income;
import seedu.mindmymoney.userfinancial.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
     * Loads information from the save file. If the file does not exist, or if there is
     * an error when reading the file, return an empty list, and print a warning message.
     * @return The saved list.
     */
    public User load() {
        try {
            Scanner scanner = new Scanner(storageFile);
            return User.deserializeFrom(scanner);
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Save file not found. MindMyMoney cannot read your saved data.");
            return new User();
        } catch (MindMyMoneyException e) {
            System.out.println("WARNING: Error when reading save data: " + e.getMessage()  + "\n"
                + "MindMyMoney will create a new save file.");
            return new User();
        }
    }

    /**
     * Saves the information associated with the given User. If the file does not exist, or if there is an error
     * when saving to the file, print a warning message.
     * @param user user whose lists need to be saved.
     */
    public void save(User user) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(storageFile));
            bufferedWriter.write(user.serialize());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Save file not found. MindMyMoney cannot read your saved data.");
        } catch (IOException e) {
            System.out.println("WARNING: Error when saving expenditure list: " + e.getMessage() + "\n"
                + "MindMyMoney will create a new save file.");
        }
    }
}
