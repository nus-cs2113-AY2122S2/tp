package seedu.duke.helper;

public class Parser {

    public String[] commandParser(String userInput) {
        return userInput.trim().split("/info");
    }
}
