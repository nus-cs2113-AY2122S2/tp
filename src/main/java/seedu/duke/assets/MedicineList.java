package seedu.duke.assets;

import java.util.ArrayList;

public class MedicineList {
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public int size() {
        return medicines.size();
    }

    public void add(String[] parameterArray) {
        Medicine newMedicine = new Medicine(medicines.size(), parameterArray[0], Integer.parseInt(parameterArray[1]),
                parameterArray[2], parameterArray[3], Integer.parseInt(parameterArray[4]));
        medicines.add(newMedicine);
    }

    public void getMedicineInfo(Medicine medicine) {
        System.out.println(Integer.toString(medicine.getMedicineId()) + ": "
                + medicine.getMedicineName() + ", "
                + Integer.toString(medicine.getDosage()) + ", " + medicine.getExpiry() + ", "
                + medicine.getSideEffects() + ", " + Integer.toString(medicine.getQuantity()));
    }

    public void viewMedicine() {
        if (size() == 0) {
            System.out.println("There are no medicines currently.");
            return;
        }
        for (Medicine medicine : medicines) {
            getMedicineInfo(medicine);
        }
        System.out.println("There are a total of " + Integer.toString(medicines.size())
                + " in the system.");
    }

    public void viewMedicine(int parameters) {
        getMedicineInfo(medicines.get(parameters - 1));
    }

    /*
    public void viewMedicine(String parameters) {
        if (parameters.equals("name")) {
            for (Medicine medicine : medicines) {
                System.out.println(Integer.toString(medicine.getMedicineId()) + ": "
                        + medicine.getMedicineName());
            }
        }
    }
    */
    public void delete(int number) {
        medicines.remove(number - 1);
    }

    public ArrayList<Medicine> getList() {
        return medicines;
    }

}
