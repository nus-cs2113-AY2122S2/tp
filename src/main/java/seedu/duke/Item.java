package seedu.duke;

public class Item {
    private String Name;
    private int Pax;

    public Item(String Name, int Pax) {
        setName(Name);
        setPax(Pax);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPax() {
        return Pax;
    }

    public void setPax(int pax) {
        Pax = pax;
    }
}
