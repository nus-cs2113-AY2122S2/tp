package seedu.duke.assets;

import seedu.duke.helper.UI;

import java.util.ArrayList;

public class MedicineList {
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public int getSize() {
        return medicines.size();
    }

    public void add(String[] parameterArray) {
        Medicine newMedicine = new Medicine(medicines.size() + 1, parameterArray[0],
                Integer.parseInt(parameterArray[1]), parameterArray[2], parameterArray[3],
                Integer.parseInt(parameterArray[4]));
        medicines.add(newMedicine);
    }

    public String getMedicineInfo(Medicine medicine) {
        return (Integer.toString(medicine.getMedicineId()) + ": "
                + medicine.getMedicineName() + ", "
                + Integer.toString(medicine.getDosage()) + ", " + medicine.getExpiry() + ", "
                + medicine.getSideEffects() + ", " + Integer.toString(medicine.getQuantity()));
    }

    public void viewMedicine() {
        if (getSize() == 0) {
            UI.printParagraph("There are no medicines currently.");
            return;
        }
        UI.printParagraph("Here is the list of medicines");
        for (Medicine medicine : medicines) {
            UI.printCont(getMedicineInfo(medicine));
        }
        UI.printCont("There are a total of " + Integer.toString(medicines.size())
                + " in the system.");
    }

    //todo: please change logic
    public void viewMedicine(String parameters) {
        int index = Integer.parseInt(parameters);
        getMedicineInfo(medicines.get(index - 1));
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
    public void deleteMedicine(int index) {
        medicines.remove(index);
    }

    public ArrayList<Medicine> getList() {
        return medicines;
    }


}
