package seedu.duke;

/*
 * Class to handle the Goods information
 *
 * FOR NOW id we can manually input?
 * Ideas - id can track and give to the goods we add automatically
 */
public class Orderline extends Good {
    private int quantityFulfilled = 0;
    private Boolean isCheckedOff = false;

    public Orderline() {
    }


    public void checkOff(){
        this.isCheckedOff = true;
    }

    public int getQuantityFulfilled(){
        return quantityFulfilled;
    }

    public void setQuantityFulfilled(int quantityFulfilled) {
        this.quantityFulfilled = quantityFulfilled;
    }

    //    public void setUnitGoodByName(String name){
//
//    }

}
