package seedu.allonus.contacts.entry;

public class Field {

    private String field;

    public Field(String field) {
        this.field = field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}
