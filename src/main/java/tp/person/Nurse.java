package tp.person;

public class Nurse extends Person{
    protected boolean isOnDuty;
    protected String title;

    public Nurse(String id, String name, String phoneNumber, String email,String title) {
        super(id, name, phoneNumber, email);
        this.title=title;
        isOnDuty = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void markOnDuty() {
        isOnDuty = true;
    }

    public void markOffWork() {
        isOnDuty = false;
    }

    @Override
    public String toString() {
        return "* NURSE " + super.toString();
    }

}
