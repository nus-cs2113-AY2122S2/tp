package tp.person;

public class Patient extends Person {
    protected String symptom;
    protected String description = " ";

    public Patient(String id, String name, String phoneNumber, String email, String symptom) {
        super(id, name, phoneNumber, email);
        this.symptom = symptom;
    }

    public Patient(String id, String name, String phoneNumber, String email, String symptom, String description) {
        super(id, name, phoneNumber, email);
        this.description = description;
        this.symptom = symptom;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getDescription() {
        return description;
    }



    @Override
    public String toString() {
        return "* PATIENT " + super.toString() + " || Symptom: " + symptom + " || Description: " + description;
    }

    public void addDescription(String description) {
        this.description = description;
    }
}
