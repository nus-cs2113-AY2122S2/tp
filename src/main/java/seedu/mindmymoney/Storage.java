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
        User savedUser = new User();
        savedUser.setExpenditureListArray(new ExpenditureList());
        savedUser.setCreditCardListArray(new CreditCardList());
        savedUser.setIncomeListArray(new IncomeList());
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(storageFile));
            String inputLine;
            ArrayList<Expenditure> expenditureList = new ArrayList<Expenditure>();
            while ((inputLine = bufferedReader.readLine()) != null) {
                if (inputLine.length() == 0) {
                    continue;
                }
                Command addCommand = Parser.parseCommand(inputLine, savedUser);
                addCommand.executeCommand();
            }
            bufferedReader.close();
            return savedUser;
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Save file not found. MindMyMoney will create another empty expenditure list.");
            return savedUser;
        } catch (IOException | MindMyMoneyException e) {
            System.out.println("WARNING: Error when reading expenditure list: " + e.getMessage());
            return savedUser;
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
            for (Expenditure expenditure : user.getExpenditureListArray().expenditureListArray) {
                String expenditureString = expenditure.getAddCommand();
                bufferedWriter.write(expenditureString, 0, expenditureString.length());
                bufferedWriter.newLine();
            }
            for (CreditCard creditCard: user.getCreditCardListArray().creditCardListArray) {
                String creditCardString = creditCard.getAddCommand();
                bufferedWriter.write(creditCardString, 0, creditCardString.length());
                bufferedWriter.newLine();
            }
            for (Income income : user.getIncomeListArray().incomeListArray) {
                String incomeString = income.getAddCommand();
                bufferedWriter.write(incomeString, 0, incomeString.length());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Save file not found. MindMyMoney cannot save your expenditure list.");
        } catch (IOException | MindMyMoneyException e) {
            System.out.println("WARNING: Error when saving expenditure list: " + e.getMessage());
        }
    }
}
