package seedu.duke;

public class Item {
    private String name;
    private int pax;

    public Item(String name, int pax) {
        setName(name);
        setPax(pax);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }
}
