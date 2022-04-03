package seedu.mindmymoney.data;

import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.userfinancial.CreditCard;

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
        for (CreditCard creditCard : creditCardListArray) {
            if (creditCard.getNameOfCard().equalsIgnoreCase(name.toLowerCase())) {
                return creditCard;
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
     * Checks if card name is equal.
     *
     * @param creditCardList List of credit card details.
     * @param index Index of credit card list item.
     * @param name Card name to compare with.
     * @return True if card name is equal, false otherwise.
     */
    public static boolean isEqualName(CreditCardList creditCardList, int index, String name) {
        return creditCardList.get(index).getNameOfCard().equals(name);
    }

    /**
     * Checks if cashback is equal.
     *
     * @param creditCardList List of credit card details.
     * @param index Index of credit card list item.
     * @param cashback Cashback to compare with.
     * @return True if cashback is equal, false otherwise.
     */
    public static boolean isEqualCashback(CreditCardList creditCardList, int index, double cashback) {
        return creditCardList.get(index).getCashback() == cashback;
    }

    /**
     * Checks if card limit is equal.
     *
     * @param creditCardList List of credit card details.
     * @param index Index of credit card list item.
     * @param cardLimit Card limit to compare with.
     * @return True if card limit is equal, false otherwise.
     */
    public static boolean isEqualCardLimit(CreditCardList creditCardList, int index, float cardLimit) {
        return creditCardList.get(index).getMonthlyCardLimit() == cardLimit;
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
