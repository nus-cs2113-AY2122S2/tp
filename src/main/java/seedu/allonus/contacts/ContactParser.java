package seedu.allonus.contacts;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactParser {

    private static final int MAX_NUMBER_OF_FIELDS = 5;
    private static Logger logger = Logger.getLogger("");

    private static ArrayList<String> getFields(String userInput) {
        String regex = "[nfetd]/.*?(?=([nfetd]/|$))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        ArrayList<String> fields = new ArrayList<>(MAX_NUMBER_OF_FIELDS);

        while (matcher.find()) {
            fields.add(matcher.group().trim());
        }

        return fields;
    }

    private static void isValidField(String fieldContent) throws InvalidContactField {
        if (fieldContent.equals("")) {
            logger.log(Level.FINE, String.format("Invalid contact field: %s", fieldContent));
            throw new InvalidContactField("Invalid contact field(s)!");
        }
    }

    private static Contact parseContactFields(ArrayList<String> fields) throws InvalidContactField {
        String regex = "(?<=[nfetd]/).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        String name = null;
        String faculty = null;
        String email = null;
        String description = null;

        for (String field : fields) {
            matcher = pattern.matcher(field);
            matcher.find();
            String fieldContent = matcher.group().trim();
            isValidField(fieldContent);

            char fieldType = field.charAt(0);
            switch (fieldType) {
            case 'n':
                name = fieldContent;
                break;
            case 'f':
                faculty = fieldContent;
                break;
            case 'e':
                email = fieldContent;
                break;
            case 'd':
                description = fieldContent;
                break;
            default:
                logger.log(Level.FINE, String.format("Invalid contact field: %s", field));
                throw new InvalidContactField("Invalid contact field!");
            }
        }
        assert !name.equals("");
        assert !faculty.equals("");
        assert !email.equals("");
        assert !description.equals("");
        return new Contact(name, faculty, email, description);
    }

    public static Contact parseContact(String userInput) throws InvalidContactField {
        ArrayList<String> fields = getFields(userInput);
        Contact contact = parseContactFields(fields);
        return contact;
    }
}
