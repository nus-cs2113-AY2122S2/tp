package seedu.simplst;

import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class is to help with cleaning up code involving regex
 * Creating an Instance of Regex will take in the string input
 * and the regex pattern to match that input
 *
 * This class has a Getter Function to return a HashMap of regex
 * matches. Please name your capture groups.
 *
 * Eg: String regex = "(?<id>\d*)";
 * Regex regexMatch = new Regex(input, regex);
 *
 * This will give a HashMap of <k,v> as <id,id_value>
 * Refer to UserInterface.run() -> case "add" for another example
 */
public class MatchKeywords {
    private static String input;
    private static String regex;
    private HashMap<String, String> groupValues;

    public MatchKeywords(String input, String regex) throws MissingFlagException, EmptyFieldException {
        this.input = input;
        this.regex = regex;
        this.groupValues = findMatch();
    }

    public static String getInput() {
        return input;
    }

    /*
     * Main driver to create HashMap for group matches
     * Key: Group Name, Value: Match as string
     *
     * @return HashMap with key, value pair of group and its match
     * @throws //If no match is found should throw something?
     * Current implementation is just to put no value with
     * corresponding keys
    */
    private HashMap<String,String> findMatch() throws MissingFlagException, EmptyFieldException {
        HashMap<String, String> hashMap = new HashMap<>();
        ArrayList<String> groupNames = findGroup(regex);
        Matcher matcher = regexMatching(regex, input);

        // run matcher class to check regex on input string
        boolean hasMatch = matcher.find();
        for (String groupName: groupNames) {
            if (!hasMatch) { //missing certain flags
                hashMap.clear();
                throw new MissingFlagException();
            } else {
                if ((groupName.equals("sku") || groupName.equals("name") || groupName.equals("oid")
                        || groupName.equals("qty") || groupName.equals("r") || groupName.equals("size")
                        || groupName.equals("addr")) && matcher.group(groupName).isBlank()) { //check compulsory fields
                    hashMap.clear();
                    throw new EmptyFieldException();
                }
                hashMap.put(groupName, matcher.group(groupName));
            }
        }
        return hashMap;
    }

    /**
     * Match the given input with the given String Regex.
     *
     * @param regex Given regex pattern
     * @param input Given input to match regex against
     * @return Matcher class after matching the input with regex
     */
    private Matcher regexMatching(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    /**
     * Finds the group names within the regex pattern.
     * To be used as key name for HashMap matches
     *
     * @param regex the regex pattern used
     * @return ArrayList of group names
     */
    private ArrayList<String> findGroup(String regex) {
        ArrayList<String> groups = new ArrayList<>();
        String[] groupNames = regex.split("\\(?<");

        for (String groupName : groupNames) {
            if (!groupName.contains(">")) {
                continue;
            }
            int index = groupName.indexOf(">");
            String name = groupName.substring(0, index);
            groups.add(name);
        }

        return groups;
    }

    public HashMap<String, String> getGroupValues() {
        return groupValues;
    }
}
