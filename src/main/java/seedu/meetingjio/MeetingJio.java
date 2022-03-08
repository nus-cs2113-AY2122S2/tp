package seedu.meetingjio;

import java.util.Scanner;

public class MeetingJio {
    private static Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello from MeetingJio\n");
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        String input = getInput();
        Parser parser = new Parser(input);
    }

    private static String getInput() {
        return SCANNER.nextLine().trim();
    }
}
