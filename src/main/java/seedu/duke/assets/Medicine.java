package seedu.duke.assets;

public class Medicine {
    private int medicineId;
    private String medicineName;
    private int dosage; //in milligrams
    private String expiry;
    private String sideEffects;
    private int quantity;

    public Medicine(int medicineId, String medicineName, int dosage, String expiry,
                    String sideEffects, int quantity) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.expiry = expiry;
        this.sideEffects = sideEffects;
        this.quantity = quantity;
    }
}
