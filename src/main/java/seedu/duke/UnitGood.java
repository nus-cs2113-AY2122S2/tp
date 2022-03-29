package seedu.duke;

public class UnitGood {
    private int id;

    private String name;
    private String description;
    private Float unitPrice; //in dollars
    private String unitItem;
    private Float baseArea;
    private Float volume;
    private Boolean isPerishable;
    public UnitGood(String name,
                    String description,
                    Float unitPrice,
                    String unitItem,
                    Float baseArea,
                    Float volume,
                    Boolean isPerishable){
        this.name = name;
        this.description = description;
        this.baseArea = baseArea;
        this.unitPrice = unitPrice;
        this.unitItem = unitItem;
        this.volume = volume;
        this.isPerishable = isPerishable;

    }

    public String getSummary(){
        return String.format("%s: %s\n" +
                "Cost: $%.2f/%s",
                    name,description,unitPrice,unitItem
                );
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("%d - %s (%s)",id, name, description);
    }


}
