package manager;


import java.util.ArrayList;

public class LimitManager {
    private double limit;
    public LimitManager() {
        limit = 0;
    }
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
        System.out.println("WARNING: You have exceeded the spending limit!!");
    }
    public boolean ifExceedLimit(double total){
        if (total>this.limit) return true;
        else return false;
    }

}
