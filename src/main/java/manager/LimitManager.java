package manager;

public class LimitManager {
    private static LimitManager limitManager;
    private double limit;

    private LimitManager() {
        limit = 0;
    }

    /**
     * Returns the instance of <code>LimitManager</code> if it has been created. Else, creates and returns an instance
     * of <code>LimitManager</code> using the System Input Stream and Output Stream.
     *
     * @return LimitManager object
     */
    public static LimitManager getLimitManagerInstance() {
        if (limitManager != null) {
            limitManager = new LimitManager();
        }
        return limitManager;
    }

    /**
     * Set limit to specified value.
     * @param limit New limit value
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /** Warning message to user. */
    public void displayWarning(){
        System.out.println("WARNING: You have exceeded the spending limit of " + limit + "!!");
    }

    /**
     * Checks if limit has been exceeded.
     *
     * @param total Value to check against limit
     * @return <code>true</code> if value is greater than limit
     */
    public boolean ifExceedLimit(double total){
        if (total>this.limit) {
            return true;
        } else {
            return false;
        }
    }

}
