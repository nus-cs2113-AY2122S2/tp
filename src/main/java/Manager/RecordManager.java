package Manager;

import java.util.ArrayList;

/**
 * Manager class that is able to store, edit, and access a list of records.
 */
public class RecordManager {
    private ArrayList<Record> records;

    /**
     * Constructs a <code>RecordManager</code> with an empty list of records.
     */
    public RecordManager() {
        records = new ArrayList<>();
    }

    /**
     * Adds a record to the end of the record list.
     *
     * @param newRecord Record to add to record list.
     */
    public void addRecord(Record newRecord) {
        records.add(newRecord);
    }

    /**
     * Removes the record at specified index.
     *
     * @param index Index (in <code>records</code>) of the record to remove.
     * @throws IndexOutOfBoundsException invalid index is given.
     */
    public void deleteRecord(int index) throws IndexOutOfBoundsException{
        try {
            records.remove(index);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Displays each record in the record list.
     */
    public void showRecords() {
        int recordNum = 0;

        for (Record record : records) {
            System.out.println(++recordNum + record.toString());
        }
    }
}