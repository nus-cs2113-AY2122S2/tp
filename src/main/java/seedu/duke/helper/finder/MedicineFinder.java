package seedu.duke.helper.finder;

import seedu.duke.assets.Medicine;

import java.util.ArrayList;

public class MedicineFinder {

    public static ArrayList<Medicine> findMedicineById(ArrayList<Medicine> medicines, String requiredId) {
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getMedicineId().equals(requiredId)) {
                medicineArrayList.add(medicine);
            }
        }
        return getMedicines(medicineArrayList);
    }

    public static ArrayList<Medicine> findMedicineByName(ArrayList<Medicine> medicines, String requiredName) {
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getMedicineName().contains(requiredName)) {
                medicineArrayList.add(medicine);
            }
        }
        return getMedicines(medicineArrayList);
    }

    public static ArrayList<Medicine> findMedicineByDosage(ArrayList<Medicine> medicines, int requiredDosage) {
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getDosage() == requiredDosage) {
                medicineArrayList.add(medicine);
            }
        }
        return getMedicines(medicineArrayList);
    }

    public static ArrayList<Medicine> findMedicineByExpiry(ArrayList<Medicine> medicines, String requiredExpiry) {
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getExpiry().equals(requiredExpiry)) {
                medicineArrayList.add(medicine);
            }
        }
        return getMedicines(medicineArrayList);
    }

    public static ArrayList<Medicine> findMedicineBySideEffects(ArrayList<Medicine> medicines,
                                                                String requiredSideEffects) {
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getSideEffects().equals(requiredSideEffects)) {
                medicineArrayList.add(medicine);
            }
        }
        return getMedicines(medicineArrayList);
    }

    public static ArrayList<Medicine> findMedicineByQuantity(ArrayList<Medicine> medicines, int requiredQuantity) {
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (medicine.getQuantity() == requiredQuantity) {
                medicineArrayList.add(medicine);
            }
        }
        return getMedicines(medicineArrayList);
    }

    private static ArrayList<Medicine> getMedicines(ArrayList<Medicine> medicineArrayList) {
        if (medicineArrayList.isEmpty()) {
            return null;
        } else {
            return medicineArrayList;
        }
    }

}


