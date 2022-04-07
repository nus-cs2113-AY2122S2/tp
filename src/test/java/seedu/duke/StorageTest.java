package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

public class StorageTest {

    private static final String TEST_DATA_FOLDER = "StorageFileTest";
    private static final String NON_EXISTENT_FILE_NAME = "ThisFileDoesNotExist.txt";

    @Test
    public void writeSaveData_expectFileCreated() {
        File testFile = new File("testFile.txt");
        if (testFile.exists()) {
            testFile.delete();
        }
        try {
            Storage storage = new Storage("testFile.txt");
            TravelPackage newTPackage = new TravelPackage(
                    TravelPackage.EXAMPLENAME,
                    TravelPackage.EXAMPLEID,
                    LocalDate.from(Parser.PARSE_FORMAT.parse(TravelPackage.EXAMPLESTART)),
                    LocalDate.from(Parser.PARSE_FORMAT.parse(TravelPackage.EXAMPLEEND)),
                    TravelPackage.EXAMPLEHOTEL,
                    TravelPackage.EXAMPLEPRICE,
                    TravelPackage.EXAMPLECOUNTRY,
                    TravelPackage.EXAMPLEMAX);
            ArrayList<TravelPackage> newTPackageList = new ArrayList<>();
            newTPackageList.add(newTPackage);
            Packages newPackage = new Packages(newTPackageList);
            storage.savePackageToFile(newPackage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(testFile.exists());
    }

    @Test
    public void load_expectPackage() {
        writeSaveData_expectFileCreated();
        try {
            Storage storage = new Storage("testFile.txt");
            Packages newPackage = storage.createPackages();
            TravelPackage newTP = newPackage.getPackage(0);
            assertEquals(newTP.getName(), TravelPackage.EXAMPLENAME);
            assertEquals(newTP.getID(), TravelPackage.EXAMPLEID);
            assertEquals(newTP.getStartDate(), TravelPackage.EXAMPLESTART);
            assertEquals(newTP.getEndDate(), TravelPackage.EXAMPLEEND);
            assertEquals(newTP.getHotel(), TravelPackage.EXAMPLEHOTEL);
            assertEquals(newTP.getPrice(), TravelPackage.EXAMPLEPRICE);
            assertEquals(newTP.getCountry(), TravelPackage.EXAMPLECOUNTRY);
            assertEquals(newTP.getMaxParticipants(), TravelPackage.EXAMPLEMAX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void load_nonExistentFile_returnsEmptyPackage() {
        Storage tempStorage = new Storage(NON_EXISTENT_FILE_NAME);
        Packages actualPackage = tempStorage.createPackages();
        Packages expectedPackage = new Packages();
        assertEquals(actualPackage.getSize(), expectedPackage.getSize());
    }

}

