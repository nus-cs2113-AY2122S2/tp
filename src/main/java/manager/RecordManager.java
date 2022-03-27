package manager;

import records.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Manager class that is able to store, edit, and access a list of records. */
public class RecordManager {
    private List<Record> records;
    private Storage storage;

    /** Constructs a <code>RecordManager</code> with an empty list of records. */
    public RecordManager(Storage storage) {
        records = new ArrayList<>();
        try {
            loadRecordlist();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adds a record to the end of the record list.
     *
     * @param newRecord Record to add to record list
     */
    public void addRecord(Record newRecord) {
        records.add(newRecord);
    }

    /**
     * Removes the record at specified index.
     *
     * @param index Index (in <code>records</code>) of the record to remove
     * @throws IndexOutOfBoundsException invalid index is given
     */
    public void deleteRecord(int index) throws IndexOutOfBoundsException{
        try {
            records.remove(index);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @return List of records
     */
    public List<Record> getAllRecords() {
        return records;
    }

    /**
     * @param index Index (in <code>records</code>) of the record to retrieve
     * @return Record to retrieve
     */
    public Record getRecordByIndex(int index) {
        return records.get(index);
    }

    /**
     * Calls <code>storage</code> method to load task list from data file.
     *
     * @throws IOException data file cannot be read.
     */
    public void loadRecordlist() throws IOException {
        try {
            records = storage.loadFile();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Calls <code>storage</code> method to save task list into data file.
     */
    public void saveRecordlist() {
        try {
            storage.saveFile((ArrayList<Record>) records);
        } catch (IOException e) {
            System.out.println("Saving failed.");
        }
    }



}
