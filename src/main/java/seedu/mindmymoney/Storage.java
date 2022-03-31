package seedu.mindmymoney;

import seedu.mindmymoney.userfinancial.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class for handling loading and saving of expenditure lists.
 */
public class Storage {
    private File storageFile;

    public Storage(File storageFile) throws MindMyMoneyException {
        this.storageFile = storageFile;
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                throw new MindMyMoneyException("WARNING: Failed to create save file. "
                        + "You may still use MindMyMoney; however, your data may not be saved.");
            }
        }
    }

    /**
     * Loads information from the save file. If the file does not exist, or if there is
     * an error when reading the file, return an empty list, and print a warning message.
     * @return The saved list.
     * @throws MindMyMoneyException if an error occurs while reading the file, or if the file has an invalid format.
     */
    public User load() throws MindMyMoneyException {
        try {
            Scanner scanner = new Scanner(storageFile);
            User savedUser = User.deserializeFrom(scanner);
            scanner.close();
            return savedUser;
        } catch (FileNotFoundException e) {
            throw new MindMyMoneyException("WARNING: Save file not found. MindMyMoney cannot read your saved data.");
        } catch (MindMyMoneyException e) {
            throw new MindMyMoneyException("WARNING: Error when reading save data: " + e.getMessage()  + "\n"
                + "MindMyMoney will create a new save file, possibly overwriting the existing file.\n"
                + "If you have important data stored there, make a copy of the current save file.");
        }
    }

    /**
     * Saves the information associated with the given User. If the file does not exist, or if there is an error
     * when saving to the file, print a warning message.
     * @param user User whose lists need to be saved.
     * @throws MindMyMoneyException if an error occurs while saving.
     */
    public void save(User user) throws MindMyMoneyException {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(storageFile));
            bufferedWriter.write(user.serialize());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            throw new MindMyMoneyException("WARNING: Save file not found. MindMyMoney cannot save your data.");
        } catch (IOException e) {
            throw new MindMyMoneyException("WARNING: Error when saving expenditure list: " + e.getMessage() + "\n");
        }
    }
}
