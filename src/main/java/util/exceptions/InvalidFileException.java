package util.exceptions;

public class InvalidFileException extends Exception {
    String command;
    boolean isCommand;

    public InvalidFileException(String command, boolean isCommand) {
        this.command = command;
        this.isCommand = isCommand;
    }

    public boolean isCommand() {
        return isCommand;
    }

    public String getCommand() {
        return command;
    }
}
