package seedu.allonus.contacts;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactParser {

    private static final int MAX_NUMBER_OF_FIELDS = 5;
    private static Logger logger = Logger.getLogger("Foo");

    private static ArrayList<String> getFields(String userInput) {
        String regex = "[nfetd]/.*?(?=([nfetd]/|$))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userInput);
        ArrayList<String> fields = new ArrayList<>(MAX_NUMBER_OF_FIELDS);

        while (matcher.find()) {
            fields.add(matcher.group().trim());
        }

        // System.out.println(fields.toString());
        return fields;
    }

    private static Contact parseContactFields(ArrayList<String> fields) throws InvalidContactField {
        String regex = "(?<=[nfetd]/).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        Contact contact = null;
        for (String field : fields) {
            char fieldType = field.charAt(0);
            switch (fieldType) {
            case 'n':
                matcher = pattern.matcher(field);
                // System.out.println(field);
                matcher.find();
                String name = matcher.group();
                contact = new Contact(name);
                break;
            default:
                logger.log(Level.WARNING, String.format("Invalid contact field: %s", field));
                throw new InvalidContactField("Invalid contact field!");
            }
        }
        return contact;
    }

    public static Contact parseContact(String userInput) throws InvalidContactField {
        ArrayList<String> fields = getFields(userInput);
        Contact contact = parseContactFields(fields);
        return contact;
    }
}
