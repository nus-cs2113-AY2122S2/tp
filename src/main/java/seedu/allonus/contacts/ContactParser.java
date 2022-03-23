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

public class ContactParser {

    private static final String CONTACTS_PARSER_INVALID_FIELD_MESSAGE =
            "Invalid contact field(s)!";

    private static final String CONTACTS_DELIMITERS = "[nfetd]/";
    public static final char NAME_DELIMITER = 'n';
    public static final char FACULTY_DELIMITER = 'f';
    public static final char EMAIL_DELIMITER = 'e';
    public static final char DESCRIPTION_DELIMITER = 'd';

    private static Logger logger = Logger.getLogger("");
    private static final int MAX_NUMBER_OF_FIELDS = 5;

    /**
     * Returns index of item in the list using number given by user.
     *
     * @param userInput String of original user input.
     * @return ind Index of item.
     */
    static int parseNum(String userInput) {
        String stringOfNum = userInput.split(" ", 0)[1];
        return Integer.parseInt(stringOfNum) - 1;
    }

    static ArrayList<String> getFieldStrings(String userInput) {
        String regex = CONTACTS_DELIMITERS + ".*?(?=(" + CONTACTS_DELIMITERS + "|$))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        ArrayList<String> fieldStrings = new ArrayList<>(MAX_NUMBER_OF_FIELDS);

        while (matcher.find()) {
            fieldStrings.add(matcher.group().trim());
        }
        return fieldStrings;
    }

    private static void isValidFieldString(String fieldString) throws InvalidContactField {
        if (fieldString.split(CONTACTS_DELIMITERS).length != 2) {
            logger.log(Level.FINE, String.format("Invalid contact fieldString: %s", fieldString));
            throw new InvalidContactField(CONTACTS_PARSER_INVALID_FIELD_MESSAGE);
        }
    }

    static void setContactFields(Contact contact, ArrayList<String> fieldStrings) throws InvalidContactField {
        for (String fieldString : fieldStrings) {
            char fieldType = fieldString.charAt(0);
            isValidFieldString(fieldString);
            String fieldContent = fieldString.split(CONTACTS_DELIMITERS)[1];

            switch (fieldType) {
            case NAME_DELIMITER:
                contact.getName().setField(fieldContent);
                break;
            case FACULTY_DELIMITER:
                contact.getFaculty().setField(fieldContent);
                break;
            case EMAIL_DELIMITER:
                contact.getEmail().setField(fieldContent);
                break;
            case DESCRIPTION_DELIMITER:
                contact.getDescription().setField(fieldContent);
                break;
            default:
                logger.log(Level.FINE, String.format("Invalid contact field: %s", fieldString));
                throw new InvalidContactField(CONTACTS_PARSER_INVALID_FIELD_MESSAGE);
            }
        }
    }

    static Contact parseContact(String userInput) throws InvalidContactField {
        Contact contact = new Contact(new Name(""), new Faculty(""),
                new Email(""), new Description(""));
        ArrayList<String> fieldStrings = getFieldStrings(userInput);
        setContactFields(contact, fieldStrings);
        return contact;
    }

}
