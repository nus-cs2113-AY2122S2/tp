package seedu.duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.exceptions.InvMgrException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class StorageTest {
    private static final String TEST_DATA_FILE_DELETE_ERR = "Could not clean up the generated test data file!";

    @Test
    void writeData_nullList_throwException() throws InvMgrException {
        Storage testStorage = new Storage("test/data/testData.json");
        assertThrows(NullPointerException.class, () -> testStorage.writeData(null));
    }

    @Test
    void writeData_validList_validJson() throws InvMgrException {
        Storage testStorage = new Storage("test/data/testData.json");
        ArrayList<Item> itemList = new ArrayList<>();
        Item item1 = new Item("Markers", 3, "Drawing");
        Item item2 = new Item("Whiteboard", 1, "To draw on");
        Item item3 = new Item("HDMI Cable", 2, "For connecting displays");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        testStorage.writeData(itemList);

        Storage expectedStorage = new Storage("test/data/expectedData.json");
        assertStorageEquals(testStorage, expectedStorage);
    }

    private void assertStorageEquals(Storage testStorage, Storage expectedStorage) {
        Path testPath = testStorage.getDataPath();
        Path expectedPath = expectedStorage.getDataPath();

        try {
            List<String> testString = Files.readAllLines(testPath);
            List<String> expectedString = Files.readAllLines(expectedPath);
            assertEquals(testString, expectedString);
        } catch (IOException e) {
            fail(e);
        }

        try {
            Files.deleteIfExists(testPath);
        } catch (IOException e) {
            System.err.println(TEST_DATA_FILE_DELETE_ERR);
        }
    }
}
