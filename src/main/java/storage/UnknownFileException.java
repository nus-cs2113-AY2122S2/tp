package storage;

public class UnknownFileException extends Exception {
    public static final String UNKNOWN_FILE_MSG = "Uh on, an unknown file name was encountered.";

    private String unknownFilename;

    public UnknownFileException(String unknownFilename, String errorMessage) {
        super(errorMessage);
        this.unknownFilename = unknownFilename;
    }

    public String getUnknownFilename() {
        return this.unknownFilename;
    }
}
