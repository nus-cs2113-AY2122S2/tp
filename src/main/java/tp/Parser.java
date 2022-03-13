package tp;

import java.util.Scanner;

public class Parser {
    public Parser(){

    }

    public static String getCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        return command;
    }


}
