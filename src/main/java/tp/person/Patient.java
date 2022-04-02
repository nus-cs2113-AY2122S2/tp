package tp.person;

public class Patient extends tp.person.Person {
    protected String symptom;
    protected String description=" ";
    public Patient(String id, String name, String phoneNumber, String email,String symptom) {
        super(id, name, phoneNumber, email);
        this.symptom=symptom;
    }

    public Patient(String id, String name, String phoneNumber, String email,String symptom,String description) {
        super(id, name, phoneNumber, email);
        this.symptom=symptom;
        this.description=description;
    }


    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    @Override
    public String toString() {
        return "* PATIENT " + super.toString()+" || Symptom: " + symptom+" || Description: " +description;
    }

    public void addDescription(String description) {
        this.description= description;
    }
}
