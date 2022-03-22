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

    static ArrayList<String> getFields(String userInput) {
        String regex = CONTACTS_DELIMITERS + ".*?(?=(" + CONTACTS_DELIMITERS + "|$))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        ArrayList<String> fields = new ArrayList<>(MAX_NUMBER_OF_FIELDS);

        while (matcher.find()) {
            fields.add(matcher.group().trim());
        }
        return fields;
    }

    private static void isValidField(String field) throws InvalidContactField {
        if (field.split(CONTACTS_DELIMITERS).length != 2) {
            logger.log(Level.FINE, String.format("Invalid contact field: %s", field));
            throw new InvalidContactField(CONTACTS_PARSER_INVALID_FIELD_MESSAGE);
        }
    }

    private static Contact parseContactFields(ArrayList<String> fields) throws InvalidContactField {
        Name name = null;
        Faculty faculty = null;
        Email email = null;
        Description description = null;

        for (String field : fields) {
            char fieldType = field.charAt(0);
            isValidField(field);
            String fieldContent = field.split(CONTACTS_DELIMITERS)[1];

            switch (fieldType) {
            case NAME_DELIMITER:
                name = new Name(fieldContent);
                break;
            case FACULTY_DELIMITER:
                faculty = new Faculty(fieldContent);
                break;
            case EMAIL_DELIMITER:
                email = new Email(fieldContent);
                break;
            case DESCRIPTION_DELIMITER:
                description = new Description(fieldContent);
                break;
            default:
                logger.log(Level.FINE, String.format("Invalid contact field: %s", field));
                throw new InvalidContactField(CONTACTS_PARSER_INVALID_FIELD_MESSAGE);
            }
        }
        return new Contact(name, faculty, email, description);
    }

    public static Contact parseContact(String userInput) throws InvalidContactField {
        ArrayList<String> fields = getFields(userInput);
        Contact contact = parseContactFields(fields);
        return contact;
    }
}
