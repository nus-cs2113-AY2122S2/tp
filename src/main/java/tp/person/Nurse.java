package tp.person;

public class Nurse extends Person {
    protected boolean isOnDuty;
    protected String title;
    protected String wardNumber;


    public Nurse(String id, String name, String phoneNumber, String email, String title, String wardNumber) {
        super(id, name, phoneNumber, email);
        this.title = title;
        this.wardNumber = wardNumber;
        isOnDuty = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(String wardNumber) {
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
        return "* NURSE " + super.toString() + " || TITLE: " + title + " || WardNumber: " + wardNumber;
    }

}
