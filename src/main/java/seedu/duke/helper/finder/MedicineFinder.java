package seedu.duke.helper.finder;

import seedu.duke.assets.Medicine;
import seedu.duke.assets.MedicineList;

public class MedicineFinder {

    public static Medicine findMedicineById(MedicineList medicineLists, String requiredId) {
        for (Medicine medicine : medicineLists.getList()) {
            if (medicine.getMedicineId().equals(requiredId)) {
                System.out.println("Index of Medicine Id in list base 0 = "
                        + medicineLists.getList().indexOf(medicine));
                return medicine;
            }
        }
        return null;
    }

    public static Medicine findMedicineByName(MedicineList medicineLists, String requiredName) {
        for (Medicine medicine : medicineLists.getList()) {
            if (medicine.getMedicineName().contains(requiredName)) {
                System.out.println("Index of Medicine Name in list base 0 = "
                        + medicineLists.getList().indexOf(medicine));
                return medicine;
            }
        }
        return null;
    }

    public static Medicine findMedicineByDosage(MedicineList medicineLists, int requiredDosage) {
        for (Medicine medicine : medicineLists.getList()) {
            if (medicine.getDosage() == requiredDosage) {
                System.out.println("Index of Dosage in list base 0 = "
                        + medicineLists.getList().indexOf(medicine));
                return medicine;
            }
        }
        return null;
    }

    public static Medicine findMedicineByExpiry(MedicineList medicineLists, String requiredExpiry) {
        for (Medicine medicine : medicineLists.getList()) {
            if (medicine.getExpiry().equals(requiredExpiry)) {
                System.out.println("Index of Expiry in list base 0 = "
                        + medicineLists.getList().indexOf(medicine));
                return medicine;
            }
        }
        return null;
    }

    public static Medicine findMedicineBySideEffects(MedicineList medicineLists, String requiredSideEffects) {
        for (Medicine medicine : medicineLists.getList()) {
            if (medicine.getSideEffects().equals(requiredSideEffects)) {
                System.out.println("Index of Side Effects in list base 0 = "
                        + medicineLists.getList().indexOf(medicine));
                return medicine;
            }
        }
        return null;
    }

    public static Medicine findMedicineByQuantity(MedicineList medicineLists, int requiredQuantity) {
        for (Medicine medicine : medicineLists.getList()) {
            if (medicine.getQuantity() == requiredQuantity) {
                System.out.println("Index of Quantity in list base 0 = "
                        + medicineLists.getList().indexOf(medicine));
                return medicine;
            }
        }
        return null;
    }

}


