package seedu.simplst;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


class StorageTest {
    Warehouse warehouse = new Warehouse(0);
    Boolean status = warehouse.restoreWarehouseState();

    @Test
    void checkRestore() {
        assertTrue(status);
    }

    @Test
    void checkSave() throws IOException {
        this.status = warehouse.saveWarehouseState();
        assertTrue(status);
    }
}
