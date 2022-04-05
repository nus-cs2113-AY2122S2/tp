package seedu.duke.helper;

public class IdGenerator {
    public static String createID() {
        int min = 10000000;
        int max = 99999999;
        int randomInteger = (int)Math.floor(Math.random()*(max-min+1)+min);
        return Integer.toString(randomInteger);
    }
}
