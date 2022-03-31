package seedu.mindmymoney.data;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.userfinancial.CreditCard;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.util.ArrayList;
import java.util.Scanner;

import static seedu.mindmymoney.helper.SerializerFunctions.SERIALIZATION_CREDIT_CARD_END_MARKER;
import static seedu.mindmymoney.helper.SerializerFunctions.SERIALIZATION_CREDIT_CARD_START_MARKER;
import static seedu.mindmymoney.helper.SerializerFunctions.addListToStringBuilder;
import static seedu.mindmymoney.helper.SerializerFunctions.convertInputToList;


/**
 * Container for Credit Cards.
 */
public class CreditCardList {
    public ArrayList<CreditCard> creditCardListArray;

    public CreditCardList() {
        this(new ArrayList<>());
    }

    public CreditCardList(ArrayList<CreditCard> listArray) {
        this.creditCardListArray = listArray;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return creditCardListArray.isEmpty();
    }

    /**
     * Represents the size of the CreditCard list.
     *
     * @return size of the task list.
     */
    public int size() {
        return creditCardListArray.size();
    }

    /**
     * Retrieves the CreditCard entry at the given index.
     *
     * @param index Index of the CreditCard entry.
     * @return The CreditCard object.
     */
    public CreditCard get(int index) {
        return creditCardListArray.get(index);
    }


    /**
     * Retrieves the CreditCard with the given name.
     *
     * @param name name of credit card to be searched.
     * @return CreditCard object with matching name as parameter.
     */
    public CreditCard get(String name) {
        for (CreditCard i : creditCardListArray) {
            if (i.getNameOfCard().toLowerCase().equals(name.toLowerCase())) {
                return i;
            }
        }
        return null;
    }

    /**
     * Deletes the CreditCard entry from the list.
     *
     * @param index Index of the CreditCard entry to delete.
     */
    public void delete(int index) {
        creditCardListArray.remove(index);
    }

    /**
     * Adds an CreditCard item to the list.
     *
     * @param item The CreditCard item to be added.
     */
    public void add(CreditCard item) {
        creditCardListArray.add(item);
    }

    /**
     * Updates the credit card entry at the given index.
     *
     * @param index Index of the entry to be updated.
     * @param creditCard The new CreditCard entry.
     */
    public void set(int index, CreditCard creditCard) {
        creditCardListArray.set(index, creditCard);
    }

    /**
     * Converts this CreditCardList into a machine-readable format.
     * @return The serialized CreditCardList
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        addListToStringBuilder(SERIALIZATION_CREDIT_CARD_START_MARKER,
                SERIALIZATION_CREDIT_CARD_END_MARKER,
                creditCardListArray,
                sb);
        return sb.toString();
    }

    /**
     * Reads a serialized CreditCardList from the scanner.
     * @param scanner A scanner
     * @returns A CreditCardList
     * @throws MindMyMoneyException if the format is invalid.
     */
    public static CreditCardList deserializeFrom(Scanner scanner) throws MindMyMoneyException {
        CreditCardList creditCardList = new CreditCardList();
        creditCardList.creditCardListArray = convertInputToList(
                SERIALIZATION_CREDIT_CARD_START_MARKER,
                SERIALIZATION_CREDIT_CARD_END_MARKER,
                scanner, CreditCard::deserialize);
        return creditCardList;
    }
}
