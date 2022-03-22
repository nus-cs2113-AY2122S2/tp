package seedu.splitlah.storage;

import seedu.splitlah.data.Profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {

    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "SplitLah.data";
    private static final String FILE_FULL_PATH = FILE_DIRECTORY + FILE_NAME;

    private Profile profile;

    public Storage() {
        profile = new Profile();
    }
    }
}
