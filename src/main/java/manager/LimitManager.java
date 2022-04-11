package manager;

import java.io.IOException;

public class LimitManager {
    private static LimitManager limitManager;
    private double limit;
    private Storage storage;
    /** Constructs a <code>RecordManager</code> with an empty list of records. */
    public LimitManager() {
        try {
            limit = 0;
            loadLimit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the instance of <code>LimitManager</code> if it has been created. Else, creates and returns an instance
     * of <code>LimitManager</code> using the System Input Stream and Output Stream.
     *
     * @return LimitManager object
     */
    public static LimitManager getLimitManagerInstance() {
        if (limitManager == null) {
            limitManager = new LimitManager();
        }
        return limitManager;
    }

    /**
     * Set limit to specified value.
     *
     * @param limit New limit value
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /**
     * @return Limit value.
     */
    public double getLimit() {
        return limit;
    }

    /**
     * Checks if limit has been exceeded.
     *
     * @param total Value to check against limit
     * @return <code>true</code> if value is greater than limit
     */
    public boolean isExceedLimit(double total){
        if (total>this.limit) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calls <code>storage</code> method to load limit from data file.
     *
     * @throws IOException data file cannot be read.
     */
    public void loadLimit() throws IOException {
        try {
            limit = storage.loadLimitFile();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Calls <code>storage</code> method to save limit into data file.
     */
    public void saveLimit() {
        try {
            storage.saveLimitFile(limit);
        } catch (IOException e) {
            System.out.println("Saving failed.");
        }
    }

}
