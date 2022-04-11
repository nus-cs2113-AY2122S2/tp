package seedu.simplst;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


class StorageTest {
    Warehouse w = new Warehouse(0);
    Boolean status = w.restoreWarehouseState();
    @Test
    void checkRestore(){
        assertTrue(status);
    }

    @Test
    void checkSave() throws IOException {
        this.status = w.saveWarehouseState();
        assertTrue(status);
    }
}
