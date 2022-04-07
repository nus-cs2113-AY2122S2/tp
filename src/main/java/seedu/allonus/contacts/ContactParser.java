package seedu.allonus.contacts;

import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.contacts.entry.Description;
import seedu.allonus.contacts.entry.Email;
import seedu.allonus.contacts.entry.Faculty;
import seedu.allonus.contacts.entry.Name;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.allonus.contacts.ContactsManager.checkUniqueName;

/**
 * A class of methods related to the parsing of user input into a Contact object.
 */
public class ContactParser {

    private static final String CONTACTS_PARSER_INVALID_FIELD_MESSAGE =
            "Invalid contact field(s)!";
    private static final String CONTACTS_PARSER_INVALID_FIELD_LOG_MESSAGE =
            "Invalid contact fieldString: %s";
    private static final String CONTACTS_PARSER_ADD_EMPTY_FIELDS_MESSAGE =
            "Adding a contact with all empty fields!";
    private static final String CONTACTS_PARSER_ADD_INVALID_FIELDS_MESSAGE =
            "Adding a contact with missing field(s)!";
    private static final String CONTACTS_PARSER_INVALID_EMAIL_MESSAGE =
            "*** Note: the email you entered might not be in the correct format! ***";

    private static final String CONTACTS_DELIMITERS = "[nfetd]/";
    private static final char NAME_DELIMITER = 'n';
    private static final char FACULTY_DELIMITER = 'f';
    private static final char EMAIL_DELIMITER = 'e';
    private static final char DESCRIPTION_DELIMITER = 'd';

    private static final Logger logger = Logger.getLogger("");
    private static final int MAX_NUMBER_OF_FIELDS = 5;
    private static final int LENGTH_FIELD_AFTER_SPLIT = 2;

    /**
     * Returns index of item in the list using number given by user.
     *
     * @param userInput String of original user input.
     * @return Index of item.
     */
    static int parseNum(String userInput) {
        String stringOfNum = userInput.split(" ", 0)[1];
        return Integer.parseInt(stringOfNum) - 1;
    }

    /**
     * Breaks user input down into individual fields according to specified delimiters.
     *
     * @param userInput String of original user input.
     * @return An ArrayList of String objects that are the individual contact fields.
     */
    static ArrayList<String> getFieldStrings(String userInput) {
        String regex = " " + CONTACTS_DELIMITERS + ".*?(?=( " + CONTACTS_DELIMITERS + "|$))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        ArrayList<String> fieldStrings = new ArrayList<>(MAX_NUMBER_OF_FIELDS);

        while (matcher.find()) {
            String currString = matcher.group().trim();
            fieldStrings.add(currString);
        }
        return fieldStrings;
    }

    private static void isValidFieldString(String fieldString) throws InvalidContactField {
        if (fieldString.split(CONTACTS_DELIMITERS).length != LENGTH_FIELD_AFTER_SPLIT) {
            String loggerMessage = String.format(CONTACTS_PARSER_INVALID_FIELD_LOG_MESSAGE, fieldString);
            logger.log(Level.FINE, loggerMessage);
            throw new InvalidContactField(CONTACTS_PARSER_INVALID_FIELD_MESSAGE);
        }
    }

    /**
     * Updates the fields of a given Contact object.
     *
     * @param contact The Contact object to be updated.
     * @param fieldStrings An ArrayList of strings of fields to be updated.
     * @throws InvalidContactField If any string of a field is invalid.
     */
    static void setContactFields(Contact contact, ArrayList<String> fieldStrings) throws InvalidContactField {
        for (String fieldString : fieldStrings) {
            char fieldType = fieldString.charAt(0);
            isValidFieldString(fieldString);
            String fieldContent = fieldString.split(CONTACTS_DELIMITERS)[1];

            switch (fieldType) {
            case NAME_DELIMITER:
                Name name = contact.getName();
                String prevName = name.toString();
                checkUniqueName(fieldContent, prevName);
                name.setField(fieldContent);
                break;
            case FACULTY_DELIMITER:
                Faculty faculty = contact.getFaculty();
                faculty.setField(fieldContent);
                break;
            case EMAIL_DELIMITER:
                Email email = contact.getEmail();
                email.setField(fieldContent);
                boolean isValidEmailFormat = email.isValidFormat();
                if (!isValidEmailFormat) {
                    System.out.println(CONTACTS_PARSER_INVALID_EMAIL_MESSAGE);
                }
                break;
            case DESCRIPTION_DELIMITER:
                Description description = contact.getDescription();
                description.setField(fieldContent);
                break;
            default:
                String loggerMessage = String.format(CONTACTS_PARSER_INVALID_FIELD_LOG_MESSAGE, fieldString);
                logger.log(Level.FINE, loggerMessage);
                throw new InvalidContactField(CONTACTS_PARSER_INVALID_FIELD_MESSAGE);
            }
        }
    }

    /**
     * Parses given user input into a Contact object.
     *
     * @param userInput String of original user input.
     * @return Contact parsed from the given user input.
     * @throws InvalidContactField If user input contains invalid fields.
     */
    static Contact parseContact(String userInput) throws InvalidContactField {
        Name name = new Name("");
        Faculty faculty = new Faculty("");
        Email email = new Email("");
        Description description = new Description("");
        Contact contact = new Contact(name, faculty, email, description);

        ArrayList<String> fieldStrings = getFieldStrings(userInput);
        if (fieldStrings.isEmpty()) {
            throw new InvalidContactField(CONTACTS_PARSER_ADD_EMPTY_FIELDS_MESSAGE);
        }
        setContactFields(contact, fieldStrings);

        boolean isValidName = name.isValidField();
        boolean isValidFaculty = faculty.isValidField();
        boolean isValidEmail = email.isValidField();
        boolean isValidDescription = description.isValidField();
        boolean hasAllValidFields = isValidName && isValidFaculty && isValidEmail && isValidDescription;
        if (!hasAllValidFields) {
            throw new InvalidContactField(CONTACTS_PARSER_ADD_INVALID_FIELDS_MESSAGE);
        }
        return contact;
    }

}
