package seedu.duke;

public class ParsingUtils {
    private static final String DELIMITER = "/";

    public int countSlashes(String userInput) {
        int slashCount = 0;
        for (int i = 0; i < userInput.length(); i++) {
            String curChar = Character.toString(userInput.charAt(i));
            if (curChar.equals(DELIMITER)) {
                slashCount += 1;
            }
        }
        return slashCount;
    }

    public boolean isAlpha(String name) {
        String nameWithoutSpaces = name.replaceAll("\\s+", "");
        return nameWithoutSpaces.matches("[a-zA-Z]+");
    }
}
