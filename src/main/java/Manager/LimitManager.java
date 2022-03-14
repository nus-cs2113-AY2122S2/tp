package Manager;


public class LimitManager {
    private double limit;
    public void setLimit(double limit) {
        this.limit = limit;
    }
    public double getLimit(){
        return limit;
    }
    public void deleteLimit(){
        this.limit=0;
    }
    public void displayWarning(){
        System.out.println("You have exceeded the spending limit!!");
    }
    public boolean ifExceedLimit(double total){
        if (total>this.limit) return true;
        else return false;
    }

}
