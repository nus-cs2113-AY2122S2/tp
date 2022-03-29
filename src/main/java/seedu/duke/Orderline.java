package seedu.duke;

/*
 * Class to handle the Goods information
 *
 * FOR NOW id we can manually input?
 * Ideas - id can track and give to the goods we add automatically
 */
public class Orderline extends Good {


    private String name;
    private int quantity;
    private String description;
    private UnitGood unitGood;
    private Boolean isCheckedOff = false;

    public Orderline() {
//        this.id = id;
//        this.quantity = quantity;
//        this.description = description;
//        this.unitGood = new UnitGood(name,
//                description,
//                0.0F,
//                "piece",
//                -1.0F,
//                -1.0F,
//                false);
    }


    public void checkOff(){
        this.isCheckedOff = true;
    }




//    public void setUnitGoodByName(String name){
//
//    }

}
