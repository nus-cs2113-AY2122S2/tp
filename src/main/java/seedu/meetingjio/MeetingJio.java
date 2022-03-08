package seedu.meetingjio;

import java.util.Scanner;

public class MeetingJio {

    public static void main(String[] args) {
        System.out.println("Hello from MeetingJio\n");
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
