package tp.person;

//@@author sethlxk
public class Nurse extends Person {
    protected boolean isOnDuty;
    protected String title;
    protected int wardNumber;

    //@@author sethlxk
    public Nurse(String id, String name, String phoneNumber, String email, String title, int wardNumber) {
        super(id, name, phoneNumber, email);
        this.title = title;
        this.wardNumber = wardNumber;
        isOnDuty = false;

        //@@author cczhouqi
        assert wardNumber == -1 : "Default ward number should be set to -1";
    }

    //@@author sethlxk
    public String getTitle() {
        return title;
    }

    public void editTitle(String title) {
        this.title = title;
    }

    public int getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(int wardNumber) {
        this.wardNumber = wardNumber;
    }

    public void markOnDuty() {
        isOnDuty = true;
    }

    public void markOffWork() {
        isOnDuty = false;
    }

    @Override
    public String toString() {
        return "* NURSE " + super.toString() + " || Title: " + title + " || Ward: " + wardNumber;
    }

}
