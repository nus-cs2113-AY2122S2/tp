package seedu.mindmymoney.data;

import seedu.mindmymoney.MindMyMoneyException;

import java.util.HashMap;

/** Class for storing a list of key-value pairs. This list can be serialized
 *  to a string. */
public class PropertyList {
    private final HashMap<String, String> properties;

    public PropertyList() {
        properties = new HashMap<String, String>();
    }

    /**
     * Converts the PropertyList into a String using a machine-readable format.
     * @return The serialized PropertyList
     */
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (String key : properties.keySet()) {
            String serializedKeyValue = String.format(" %s: %s ",
                    quoteString(key), quoteString(properties.get(key)));
            sb.append(serializedKeyValue);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return serialize();
    }

    /**
     * Adds a key-value pair to the PropertyList.
     * @param key The key
     * @param value The value.
     */
    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    /**
     * Retrieves the value associated with the given property. If the property does not exist,
     * throw a MindMyMoneyException whose message is the property.
     * @param property The property whose value to retrieve.
     * @return The value.
     * @throws MindMyMoneyException if the property is not in the PropertyList.
     */
    public String getValue(String property) throws MindMyMoneyException {
        String value = properties.get(property);
        if (value == null) {
            throw new MindMyMoneyException(property);
        }
        return value;
    }

    /**
     * Replaces all double quotes and backslashes in a string with versions safe to use in
     * a quoted string.
     * @param string The string to process.
     * @return The string, with quotes and backslashes escaped.
     */
    private static String escapeQuotesInString(String string) {
        return string.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    /**
     * Quotes a string. This puts it between double quotes, while adding backslash characters to any
     * double quotes and backslashes the string has.
     * @param string The string to quote.
     * @return A quoted string.
     */
    private static String quoteString(String string) {
        return String.format("\"%s\"", escapeQuotesInString(string));
    }

    /**
     * Converts a quoted string into its original form.
     * @param string The string to unquote.
     * @return An unquoted string.
     * @throws MindMyMoneyException if the format is invalid.
     */
    private static String unquoteString(String string) throws MindMyMoneyException {
        String unescaped = string.replace("\\\"", "\"")
                .replace("\\\\", "\\");
        if (unescaped.length() < 2 || unescaped.charAt(0) != '\"'
                || unescaped.charAt(unescaped.length() - 1) != '\"') {
            throw new MindMyMoneyException("Invalid unquoted string: " + string);
        }
        return unescaped.substring(1, unescaped.length() - 1);
    }

    /**
     * Reads a quoted string from the given string, starting at the given position.
     * @param string The string to read from.
     * @param startPos The start position.
     * @return A quoted string.
     * @throws MindMyMoneyException if the format is invalid.
     */
    private static String readQuotedString(String string, int startPos) throws MindMyMoneyException {
        if (string.charAt(startPos) != '\"') {
            throw new MindMyMoneyException(string.substring(startPos) + " does not start with quoted string");
        }
        int endPos = startPos + 1;
        while (endPos < string.length()) {
            if (string.charAt(endPos) == '\\') {
                endPos += 2;
                continue;
            }
            endPos += 1;
            if (string.charAt(endPos - 1) == '\"') {
                break;
            }
        }
        return string.substring(startPos, endPos);
    }

    /**
     * Parses the output of serialize.
     * @param string A serialized PropertyList.
     * @return A PropertyList containing the same key-value pairs.
     * @throws MindMyMoneyException if the format is invalid.
     */
    public static PropertyList deserialize(String string) throws MindMyMoneyException {
        PropertyList plist = new PropertyList();
        int propertyStart = 0;
        while (propertyStart < string.length()) {
            propertyStart = consumeWhitespace(string, propertyStart);
            String quotedKey = readQuotedString(string, propertyStart);
            propertyStart += quotedKey.length();

            propertyStart = consumeWhitespace(string, propertyStart);
            if (propertyStart == string.length() || string.charAt(propertyStart) != ':') {
                throw new MindMyMoneyException("Expecting to start with colon, got "
                        + string.substring(propertyStart));
            }
            propertyStart++;

            propertyStart = consumeWhitespace(string, propertyStart);
            String quotedValue = readQuotedString(string, propertyStart);
            propertyStart += quotedValue.length();

            propertyStart = consumeWhitespace(string, propertyStart);

            String rawKey = unquoteString(quotedKey);
            String rawValue = unquoteString(quotedValue);
            plist.addProperty(rawKey, rawValue);
        }
        return plist;
    }

    /**
     * Moves an index into a string forward, until that index does not point to whitespace.
     * @param string The string
     * @param propertyStart The index
     * @return The index, after having been moved forward.
     */
    private static int consumeWhitespace(String string, int propertyStart) {
        while (isStartAtWhitespace(string, propertyStart))  {
            propertyStart++;
        }
        return propertyStart;
    }

    /**
     * Checks if an index into a string points to whitespace.
     * @param string The string
     * @param propertyStart The index
     * @return Whether or not the index is in range of the string, and produces a valid index.
     */

    private static boolean isStartAtWhitespace(String string, int propertyStart) {
        return propertyStart < string.length() && string.charAt(propertyStart) == ' ';
    }
}
