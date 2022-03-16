package seedu.duke.assets;

import java.util.ArrayList;

public class MedicineList {
    private ArrayList<Medicine> medicines = new ArrayList<>();

    public int size() {
        return medicines.size();
    }

    public void add(String[] parameterArray) {
        Medicine newMedicine = new Medicine(medicines.size(),parameterArray[0],Integer.parseInt(parameterArray[1]),
                parameterArray[2],parameterArray[3],Integer.parseInt(parameterArray[4]));
        medicines.add(newMedicine);
    }

    public void delete(int number) {
        medicines.remove(number - 1);
    }
}
