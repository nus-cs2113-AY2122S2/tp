package seedu.duke.assets;

import java.util.ArrayList;

public class MedicineList {
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public void add(String[] parameterArray) {
        Medicine newMedicine = new Medicine(medicines.size(), parameterArray[0], Integer.parseInt(parameterArray[1]),
                parameterArray[2], parameterArray[3], Integer.parseInt(parameterArray[4]));
        medicines.add(newMedicine);
    }

    public int getSize() {
        return medicines.size();
    }

    public void getMedicineInfo(Medicine medicine) {
        System.out.println(Integer.toString(medicine.getMedicineId()) + ": " +
                medicine.getMedicineName() + ", " +
                Integer.toString(medicine.getDosage()) + ", " + medicine.getExpiry() + ", " +
                medicine.getSideEffects() + ", " + Integer.toString(medicine.getQuantity()));
    }

    public void viewMedicine() {
        for (Medicine medicine : medicines) {
            getMedicineInfo(medicine);
        }
        System.out.println("There are a total of " + Integer.toString(medicines.size())
                + " in the system.");
    }

    public void viewMedicine(String parameters) {
        if (parameters.equals("name")) {
            for (Medicine medicine : medicines) {
                System.out.println(Integer.toString(medicine.getMedicineId()) + ": "
                        + medicine.getMedicineName());
            }
        }
    }
}
